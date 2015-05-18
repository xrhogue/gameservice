/**
 * HibernateUtil.java
 */
package com.bogie.util;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtil
 *
 * @author Richard Hogue
 */
public class HibernateUtil<T extends Serializable>
{
    protected static final Logger   logger = Logger.getLogger(HibernateUtil.class);

    SessionFactory  sessionFactory = null;
    
    private Integer id;
    
    /**
     * Creates a default Hibernate Utility object not associated with a configuration file
     */
    public HibernateUtil()
    {
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
    @SuppressWarnings("unchecked")
    public T get(final String queryStr, final T filter)
    {
        Session session = null;
        List<T> items = null;
        
        try
        {
            session = getSession();
        
            session.beginTransaction();
            
            Query   query = session.createQuery(queryStr);
            
            if (filter != null)
            {
                query.setProperties(filter);
            }
            
            items = query.setMaxResults(1).list();
        }
        catch (Exception e)
        {
            handleException(queryStr, session, e);
        }
        catch (Throwable t)
        {
            handleThrowable(session, t);
        }
        finally
        {            
            if (session != null && session.isOpen())
            {
                session.close();
            }
        }
        
        if (items != null && items.size() >= 1)
        {
            return items.get(0);
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
        Session session = null;
        
        try
        {
            session = getSession();
            
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            handleException(object.toString(), session, e);
        }
        catch (Throwable t)
        {
            handleThrowable(session, t);
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
            handleException(object.toString(), session, e);
        }
        catch (Throwable t)
        {
            handleThrowable(session, t);
        }
        
        return object;
    }
    
    private void handleException(final String prefix, Session session, Exception e)
    {
        session.getTransaction().rollback();

        logger.error(prefix + ": " + e.getMessage() + (e.getCause() != null ? " - " + e.getCause().getMessage() : ""));
        
        e.printStackTrace();
    }

    private void handleThrowable(Session session, Throwable t)
    {
        session.getTransaction().rollback();

        logger.error(t.getMessage(), t);
        
        t.printStackTrace();
    }

    private Session getSession()
    {
        return getSessionFactory().getCurrentSession();
    }
    
    private SessionFactory getSessionFactory()
    {
        if (sessionFactory != null)
        {
            return sessionFactory;
        }
        
        Configuration                   configuration = new Configuration().configure(new File("game.cfg.xml"));
        StandardServiceRegistryBuilder  builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        
        sessionFactory = configuration.buildSessionFactory(builder.build());
        
        return sessionFactory;
    }
}
