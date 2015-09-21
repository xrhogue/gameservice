/**
 * HibernateUtil.java
 */
package com.bogie.util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

/**
 * HibernateUtil
 *
 * @author Richard Hogue
 */
public class HibernateUtil<T extends Serializable>
{
    protected static final Logger   logger = Logger.getLogger(HibernateUtil.class);

    private static Map<Integer, SessionFactory>         sessionFactoryMap = new HashMap<Integer, SessionFactory>();
//    private static Map<Integer, EntityManagerFactory>   entityManagerFactoryMap = new HashMap<Integer, EntityManagerFactory>();
    
    private String          configurationResource;
    private Properties      overrideProperties;
    private Integer         id;
    
    /**
     * Creates a default Hibernate Utility object not associated with a configuration file
     */
    public HibernateUtil()
    {
        this(null);
    }
    
    /**
     * Creates a Hibernate Utility object associated with a configuration resource
     * @param configurationResource the associated configuration resource
     */
    public HibernateUtil(final String configurationResource)
    {
        this(configurationResource, ConfigUtil.getDefaultOverrideProperties());
    }
    
    /**
     * Creates a Hibernate Utility object associated with a configuration resource, and optional overrides
     * @param configurationResource the associated configuration resource
     * @param overrideProperties a collection of optional override properties to replace/update the configuration file properties
     */
    public HibernateUtil(final String configurationResource, final Properties overrideProperties)
    {
        this.configurationResource = configurationResource;
        this.overrideProperties = overrideProperties;
    }
    
    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * Gets a persisted hibernate object based on the query
     * @param queryStr the hibernate database query
     * @return a single hibernate object, or null if no match found
     */
    public T get(final String queryStr)
    {
        return get(queryStr, null);
    }
    
    /**
     * Gets a persisted hibernate object based on the query and filter
     * @param queryStr the hibernate database query
     * @param filter the hibernate object filter used by the query
     * @return a single hibernate object, or null if no match found
     */
    public T get(final String queryStr, T filter)
    {
        List<T> items = find(queryStr, filter, 1);
        
        if (items != null && items.size() >= 1)
        {
            return items.get(0);
        }
        
        return null;
    }
    
    /**
     * Gets a list of persisted hibernate objects based on the query
     * @param queryStr the hibernate database query
     * @return a list of hibernate objects, or null if no objects found
     */
    public List<T> find(final String queryStr)
    {
        return find(queryStr, null);
    }
    
    /**
     * Gets a list of persisted hibernate objects based on the query and filter
     * @param queryStr the hibernate database query
     * @param filter the hibernate object filter used by the query
     * @return a list of hibernate objects, or null if no objects found
     */
    public List<T> find(final String queryStr, T filter)
    {
        return find(queryStr, filter, null);
    }
    
    /**
     * Gets a list of persisted hibernate objects based on the query, filter, and maxResults
     * @param queryStr the hibernate database query
     * @param filter the hibernate object filter used by the query
     * @param maxResults the maximum number of results to retrieve
     * @return a list of hibernate objects, or null if no objects found
     */
    @SuppressWarnings("unchecked")
    public List<T> find(final String queryStr, T filter, final Integer maxResults)
    {
        Session session = null;
        
        if (StringUtils.isBlank(queryStr))
        {
            return null;
        }
        
        try
        {
            session = getSession();
        
            session.beginTransaction();
            
            Query   query = session.createQuery(queryStr);
            
            if (filter != null)
            {
                query.setProperties(filter);
            }
            
            if (maxResults != null)
            {
                query.setMaxResults(maxResults);
            }
            
            return query.list();
        }
        catch (Exception e)
        {
            handleException(queryStr, session, e);
        }
        catch (Throwable t)
        {
            handleThrowable(queryStr, session, t);
        }
        finally
        {            
            cleanup(session);
        }
        
        return null;
    }

    /**
     * Saves a hibernate object to the database. 
     * @param object the hibernate object to persist
     * @return the persisted hibernate object
     */
    public T save(T object)
    {
        return save(object, false);
    }

    /**
     * Saves a hibernate object to the database. 
     * @param object the hibernate object to persist
     * @param ignoreDuplicates true to ignore save errors if the object already exists
     * @return the persisted hibernate object
     */
    @SuppressWarnings("unchecked")
    public T save(T object, final Boolean ignoreDuplicates)
    {
        Session session = null;
        
        try
        {
            session = getSession();
            
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        }
        catch (ConstraintViolationException e)
        {
            if (ignoreDuplicates && e.getCause().getMessage().startsWith("Duplicate entry '") && e.getCause().getMessage().endsWith("' for key 'PRIMARY'"))
            {
                object = (T)session.get(object.getClass(), session.getIdentifier(object));
            }
            else
            {
                handleException(object, session, e);
            }
        }
        catch (Exception e)
        {            
            handleException(object, session, e);
        }
        catch (Throwable t)
        {
            handleThrowable(object, session, t);
        }
        finally
        {            
            cleanup(session);
        }
        
        return object;
    }
    
    /**
     * Saves or updates a hibernate object to the database. If the object does not already exist based on its Id property, it will create a new one.
     * If it already exists based on that Id value, it will update the existing one.
     * @param object the hibernate object to persist
     * @return the persisted hibernate object
     */
    public T saveOrUpdate(T object)
    {
        Session session = null;
        
        try
        {
            session = (Session)getSession();
            
            session.beginTransaction();          
            session.saveOrUpdate(object);
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            handleException(object, session, e);
        }
        catch (Throwable t)
        {
            handleThrowable(object, session, t);
        }
        finally
        {            
            cleanup(session);
        }
        
        return object;
    }

    /**
     * Merges a hibernate object to the database. 
     * @param object the hibernate object to persist
     * @return the persisted hibernate object
     */
    @SuppressWarnings("unchecked")
    public T merge(T object)
    {
        Session session = null;
        
        try
        {
            session = getSession();
            
            session.beginTransaction();
            object = (T)session.merge(object);
            //session.getTransaction().commit();
        }
        catch (Exception e)
        {
            handleException(object, session, e);
        }
        catch (Throwable t)
        {
            handleThrowable(object, session, t);
        }
        finally
        {            
            cleanup(session);
        }
        
        return object;
    }
    
    private void handleException(final Object prefix, Session session, Exception e)
    {
        handleThrowable(prefix != null ? prefix.toString() : "Null object", session, e);
    }
    
    private void handleException(final String prefix, Session session, Exception e)
    {
        handleThrowable(prefix, session, e);
    }
    
    private void handleThrowable(final Object prefix, Session session, Throwable t)
    {
        handleThrowable(prefix != null ? prefix.toString() : "Null object", session, t);
    }

    private void handleThrowable(final String prefix, Session session, Throwable t)
    {
        if (session != null && session.getTransaction() != null)
        {
            session.getTransaction().rollback();
        }
        
        logger.error(prefix + ": " + t.getMessage() + (t.getCause() != null ? " - " + t.getCause().getMessage() : ""), t);
        
        t.printStackTrace();
    }

    private void cleanup(Session session)
    {
        if (session != null && session.isOpen())
        {
            session.close();
        }
    }

    private Session getSession()
    {
        return getSessionFactory().getCurrentSession();
//        return (Session)getEntityManager().getDelegate();
    }

//    private EntityManager getEntityManager()
//    {
//        return getEntityManagerFactory().createEntityManager();
//    }
//    
//    private EntityManagerFactory getEntityManagerFactory()
//    {
//        Integer                 entityManagerFactoryId = getEntityManagerFactoryId();
//        EntityManagerFactory    entityManagerFactory = entityManagerFactoryMap.get(entityManagerFactoryId);
//        
//        if (entityManagerFactory != null)
//        {
//            return entityManagerFactory;
//        }
//        
//        entityManagerFactory = Persistence.createEntityManagerFactory(configurationResource, getOverridePropertiesMap());
//        
//        entityManagerFactoryMap.put(entityManagerFactoryId, entityManagerFactory);
//        
//        return entityManagerFactory;
//    }
    
    private SessionFactory getSessionFactory()
    {
        Integer         sessionFactoryId = getSessionFactoryId();
        SessionFactory  sessionFactory = sessionFactoryMap.get(sessionFactoryId);
        
        if (sessionFactory != null)
        {
            return sessionFactory;
        }
        
        Configuration                   configuration = getConfiguration();
        StandardServiceRegistryBuilder  builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        
        sessionFactory = configuration.buildSessionFactory(builder.build());
        
        sessionFactoryMap.put(sessionFactoryId, sessionFactory);
        
        return sessionFactory;
    }
    
//    private Integer getEntityManagerFactoryId()
//    {
//        return configurationResource.hashCode() + getOverridePropertiesHashCode();
//    }
    
    private Integer getSessionFactoryId()
    {
        return configurationResource.hashCode() + getOverridePropertiesHashCode();
    }
    
    private Integer getOverridePropertiesHashCode()
    {
        Integer hashCode = 0;
        
        if (overrideProperties != null)
        {
            for (Object key : overrideProperties.keySet())
            {
                String  name = key.toString();
                String  value = overrideProperties.getProperty(name);
                
                hashCode += name.hashCode() + (StringUtils.isNotBlank(value) ? value.hashCode() : 0);                
            }
        }
        
        return hashCode;
    }
    
    private Configuration getConfiguration()
    {
        Configuration   configuration = new File(configurationResource).exists() ? new Configuration().configure(new File(configurationResource)) : new Configuration().configure(configurationResource);
        
        if (overrideProperties != null)
        {
            for (Object key : overrideProperties.keySet())
            {
                String  name = key.toString();
                String  value = overrideProperties.getProperty(name);
                
                if (StringUtils.isBlank(value))
                {
                    value = "";
                }
                
                configuration.setProperty(name, value);
            }
        }
        
        return configuration;
    }
    
//    private Map<String, Object> getOverridePropertiesMap()
//    {
//        Map<String, Object> overridePropertiesMap = new HashMap<String, Object>();
//        
//        for (Object key : overrideProperties.keySet())
//        {
//            overridePropertiesMap.put(key.toString(), overrideProperties.get(key));
//        }
//        
//        return overridePropertiesMap;
//    }
}
