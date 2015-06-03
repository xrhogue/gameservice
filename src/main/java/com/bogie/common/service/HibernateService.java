/**
 * HibernateService.java
 */
package com.bogie.common.service;

import java.io.File;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

import com.bogie.util.ConfigUtil;
import com.bogie.util.HibernateUtil;
import com.bogie.util.JDBCUtil;

/**
 * HibernateService
 *
 * Provides service-level functionality for the Hibernate Layer
 * 
 * @author Richard Hogue
 */
public class HibernateService
{
    public static final String  DIALECT_PROPERTY = "hibernate.dialect";
    public static final String  DRIVER_CLASS_PROPERTY = "hibernate.connection.driver_class";
    public static final String  URL_PROPERTY = "hibernate.connection.url";
    public static final String  USER_NAME_PROPERTY = "hibernate.connection.username";
    public static final String  PASSWORD_PROPERTY = "hibernate.connection.password";
    public static final String  DEFAULT_CONFIG_FILE_NAME = "resources" + File.separator + "hibernate.cfg.xml";
    
    protected static final Logger   logger = Logger.getLogger(HibernateService.class);

    private static HibernateUtil<Serializable>  hibernateUtil = new HibernateUtil<Serializable>();
    
    /**
     * Creates the schema based on the supplied configuration
     * 
     * @param configurationFileName the name of the configuration file containing the mapping for the annotated classes
     * @throws SQLException if unable to access the database
     * @throws ClassNotFoundException if the database driver does not exist
     */
    public static void createSchema(final String configurationFileName) throws ClassNotFoundException, SQLException
    {
        createSchema(configurationFileName, null);
    }
    
    /**
     * Creates the schema based on the supplied configuration
     * 
     * @param configurationFileName the name of the configuration file containing the mapping for the annotated classes
     * @param databaseName the name of the database to use for the creation
     * @throws SQLException if unable to access the database
     * @throws ClassNotFoundException if the database driver does not exist
     */
    public static void createSchema(final String configurationFileName, String databaseName) throws ClassNotFoundException, SQLException
    {
        Configuration   configuration = getConfiguration(configurationFileName, databaseName);
        JDBCUtil        jdbcUtil = new JDBCUtil(configuration.getProperty(DRIVER_CLASS_PROPERTY),
                                                getRootUrl(configuration.getProperty(URL_PROPERTY)),
                                                configuration.getProperty(USER_NAME_PROPERTY),
                                                configuration.getProperty(PASSWORD_PROPERTY));        

        if (StringUtils.isBlank(databaseName))
        {
            databaseName = getDatabaseName(configuration.getProperty(URL_PROPERTY));
        }
        
        jdbcUtil.execute("drop database if exists "  + databaseName);
        jdbcUtil.execute("create schema " + databaseName);        
        jdbcUtil.close();
        
        new SchemaExport(configuration).create(false /*logger.isDebugEnabled()*/, true);
    }
    
    /**
     * Creates the schema based on the supplied configuration
     * 
     * @param configurationFileName the name of the configuration file containing the mapping for the annotated classes
     * @param databaseName the name of the database to use for the creation
     * @throws SQLException if unable to access the database
     * @throws ClassNotFoundException if the database driver does not exist
     */
    public static void createSchema(final String configurationFileName,
                                    final String driverClass,
                                    final String connectionUrl,
                                    final String userName,
                                    final String password) throws ClassNotFoundException, SQLException
    {
        String          databaseName = getDatabaseName(connectionUrl);
        Configuration   configuration = getConfiguration(configurationFileName, databaseName);
        JDBCUtil        jdbcUtil = new JDBCUtil(driverClass,
                                                getRootUrl(connectionUrl),
                                                userName,
                                                password);        

        jdbcUtil.execute("drop database if exists "  + databaseName);
        jdbcUtil.execute("create schema " + databaseName);        
        jdbcUtil.close();
        
        new SchemaExport(configuration).create(logger.isDebugEnabled(), true);
    }
    
    /**
     * Updates the schema based on the supplied configuration
     * 
     * @param configurationFileName the name of the configuration file containing the mapping for the annotated classes
     * @throws SQLException if unable to access the database
     * @throws ClassNotFoundException if the database driver does not exist
     */
    public static void updateSchema(final String configurationFileName) throws ClassNotFoundException, SQLException
    {
        updateSchema(configurationFileName, null);
    }
    
    /**
     * Updates the schema based on the supplied configuration
     * 
     * @param configurationFileName the name of the configuration file containing the mapping for the annotated classes
     * @param databaseName the name of the database to use for the update
     * @throws SQLException if unable to access the database
     * @throws ClassNotFoundException if the database driver does not exist
     */
    public static void updateSchema(final String configurationFileName, String databaseName) throws ClassNotFoundException, SQLException
    {
        Configuration   configuration = getConfiguration(configurationFileName, databaseName);
        JDBCUtil        jdbcUtil = new JDBCUtil(configuration.getProperty(DRIVER_CLASS_PROPERTY),
                                                getRootUrl(configuration.getProperty(URL_PROPERTY)),
                                                configuration.getProperty(USER_NAME_PROPERTY),
                                                configuration.getProperty(PASSWORD_PROPERTY));
        
        if (StringUtils.isBlank(databaseName))
        {
            databaseName = getDatabaseName(configuration.getProperty(URL_PROPERTY));
        }
        
        if (!jdbcUtil.databaseExists(databaseName))
        {
            createSchema(configurationFileName, databaseName);
        }
        else
        {
            new SchemaUpdate(configuration).execute(logger.isDebugEnabled(), true);
        }
    }
    
    /**
     * Gets a persisted hibernate object based on the query
     * @param query the hibernate database query
     * @return a single hibernate object, or null if no match found
     */
    public static Serializable get(final String query)
    {
        return get(query, null);
    }
    
    /**
     * Gets a persisted hibernate object based on the query and filter
     * @param query the hibernate database query
     * @param filter the hibernate object filter used by the query
     * @return a single hibernate object, or null if no match found
     */
    public static Serializable get(final String query, final Serializable filter)
    {
        return hibernateUtil.get(query, filter);
    }
    
    /**
     * Saves or updates a Serializable object to the database based on the supplied configuration file. If the object already exists
     * (based on the value in the field annotated with @Id), it will update the object instead.
     * 
     * @param object the object to save or update
     * @return the saved or updated object
     */
    public static Serializable saveOrUpdate(final Serializable object)
    {
        return hibernateUtil.saveOrUpdate(object);
    }

    private static Configuration getConfiguration(final String configurationFileName, final String databaseName)
    {
        Configuration   configuration = new Configuration().configure(new File(configurationFileName));
        Properties      overrideProperties = getOverrideProperties(databaseName);
        
        for (Object key : overrideProperties.keySet())
        {
            String  name = key.toString();

            configuration.setProperty(name, overrideProperties.getProperty(name));
        }
        
        return configuration;
    }
    
    private static Properties getOverrideProperties(final String databaseName)
    {
        Properties  properties = new Properties();
        String      url = ConfigUtil.getProperty(ConfigUtil.CONNECTION_URL_PROPERTY);
        
        if (StringUtils.isNotBlank(databaseName))
        {
            url = getRootUrl(url) + "/" + databaseName;
        }
        
        overrideProperty(properties, DIALECT_PROPERTY, ConfigUtil.getProperty(ConfigUtil.DIALECT_PROPERTY));
        overrideProperty(properties, DRIVER_CLASS_PROPERTY, ConfigUtil.getProperty(ConfigUtil.DRIVER_CLASS_PROPERTY));
        overrideProperty(properties, URL_PROPERTY, url);
        overrideProperty(properties, USER_NAME_PROPERTY, ConfigUtil.getProperty(ConfigUtil.USER_NAME_PROPERTY));
        overrideProperty(properties, PASSWORD_PROPERTY, ConfigUtil.getProperty(ConfigUtil.PASSWORD_PROPERTY));
        
        return properties;
    }
    
    private static void overrideProperty(Properties properties, final String key, final String value)
    {
        if (StringUtils.isNotBlank(value))
        {
            properties.setProperty(key, value);
        }
    }
    
    private static String getRootUrl(final String url)
    {
        if (StringUtils.isBlank(url))
        {
            return url;
        }
        
        int lastSeparator = url.lastIndexOf("/");
        
        if (lastSeparator <= 0)
        {
            return url;            
        }
        
        if (url.charAt(lastSeparator - 1) == '/')
        {
            return url;
        }
        
        return url.substring(0, lastSeparator);
    }
    
    private static String getDatabaseName(final String url)
    {
        if (StringUtils.isBlank(url))
        {
            return null;
        }
        
        int lastSeparator = url.lastIndexOf("/");
        
        if (lastSeparator <= 0)
        {
            return null;            
        }
        
        if (url.charAt(lastSeparator - 1) == '/')
        {
            return null;
        }
        
        return url.substring(lastSeparator + 1);
    }
}
