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
import org.hibernate.HibernateException;
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
    public static final String  DIALECT_H2 = "org.hibernate.dialect.H2Dialect";
    public static final String  DIALECT_MYSQL = "org.hibernate.dialect.MySQLDialect";
    public static final String  DIALECT_DEFAULT = DIALECT_MYSQL;
    public static final String  DRIVER_CLASS_H2 = "org.h2.Driver";
    public static final String  DRIVER_CLASS_MYSQL = "com.mysql.jdbc.Driver";
    public static final String  DRIVER_CLASS_DEFAULT = DRIVER_CLASS_MYSQL;
    public static final String  URL_DEFAULT = "jdbc:mysql://localhost:3306/";
    public static final String  HOST_DEFAULT = "localhost";
    public static final String  PORT_DEFAULT = "3306";
    public static final String  DATABASE_DEFAULT = "asas";
    public static final String  USER_NAME_DEFAULT = "root";
    public static final String  PASSWORD_DEFAULT = "";
    public static final String  DEFAULT_CONFIG_FILE_NAME = "resources" + File.separator + "hibernate.cfg.xml";
    
    protected static final Logger   logger = Logger.getLogger(HibernateService.class);

    private static HibernateUtil<Serializable>  hibernateUtil = new HibernateUtil<Serializable>(DEFAULT_CONFIG_FILE_NAME, getOverrideProperties());
    
    /**
     * Creates the schema based on the supplied configuration
     * 
     * @param configurationResource the name of the configuration resource containing the mapping for the annotated classes
     * @throws SQLException if unable to access the database
     * @throws ClassNotFoundException if the database driver does not exist
     */
    public static void createSchema(final String configurationResource) throws ClassNotFoundException, SQLException
    {
        createSchema(configurationResource, null);
    }
    
    /**
     * Creates the schema based on the supplied configuration
     * 
     * @param configurationResource the name of the configuration resource containing the mapping for the annotated classes
     * @param databaseName the name of the database to use for the creation
     * @throws SQLException if unable to access the database
     * @throws ClassNotFoundException if the database driver does not exist
     */
    public static void createSchema(final String configurationResource, String databaseName) throws ClassNotFoundException, SQLException
    {
        createSchema(configurationResource, ConfigUtil.getProperties(), databaseName);
    }
    
    /**
     * Creates the schema based on the supplied configuration
     * 
     * @param configurationResource the name of the configuration resource containing the mapping for the annotated classes
     * @param configurationProperties the configuration properties for the connection
     * @param databaseName the name of the database to use for the creation
     * @throws SQLException if unable to access the database
     * @throws ClassNotFoundException if the database driver does not exist
     */
    public static void createSchema(final String configurationResource, final Properties configurationProperties, String databaseName) throws ClassNotFoundException, SQLException
    {
        Configuration   configuration = getConfiguration(configurationResource, configurationProperties, databaseName);
        JDBCUtil        jdbcUtil = getJDBCUtil(configuration);        

        if (StringUtils.isBlank(databaseName))
        {
            databaseName = ConfigUtil.getDatabaseName(configuration.getProperty(URL_PROPERTY));
        }
        
        jdbcUtil.execute("drop schema if exists "  + databaseName);
        jdbcUtil.execute("create schema " + databaseName);        
        jdbcUtil.close();
        
        new SchemaExport(configuration).create(logger.isDebugEnabled(), true);
    }
    
    /**
     * Updates the schema based on the supplied configuration
     * 
     * @param configurationResource the name of the configuration resource containing the mapping for the annotated classes
     * @throws SQLException if unable to access the database
     * @throws ClassNotFoundException if the database driver does not exist
     */
    public static void updateSchema(final String configurationResource) throws ClassNotFoundException, SQLException
    {
        updateSchema(configurationResource, null);
    }
    
    /**
     * Updates the schema based on the supplied configuration
     * 
     * @param configurationResource the name of the configuration resource containing the mapping for the annotated classes
     * @param databaseName the name of the database to use for the update
     * @throws SQLException if unable to access the database
     * @throws ClassNotFoundException if the database driver does not exist
     */
    public static void updateSchema(final String configurationResource, String databaseName) throws ClassNotFoundException, SQLException
    {
        updateSchema(configurationResource, ConfigUtil.getProperties(), databaseName);
    }
    
    /**
     * Updates the schema based on the supplied configuration
     * 
     * @param configurationResource the name of the configuration resource containing the mapping for the annotated classes
     * @param configurationProperties the configuration properties for the connection
     * @param databaseName the name of the database to use for the update
     * @throws SQLException if unable to access the database
     * @throws ClassNotFoundException if the database driver does not exist
     */
    public static void updateSchema(final String configurationResource, final Properties configurationProperties, String databaseName) throws ClassNotFoundException, SQLException
    {
        Configuration   configuration = getConfiguration(configurationResource, configurationProperties, databaseName);
        JDBCUtil        jdbcUtil = getJDBCUtil(configuration);
        
        if (StringUtils.isBlank(databaseName))
        {
            databaseName = ConfigUtil.getDatabaseName(configuration.getProperty(URL_PROPERTY));
        }
        
        if (!jdbcUtil.databaseExists(databaseName))
        {
            jdbcUtil.close();
            createSchema(configurationResource, configurationProperties, databaseName);
        }
        else
        {
            jdbcUtil.close();
            new SchemaUpdate(configuration).execute(logger.isDebugEnabled(), true);
        }
    }
    
    public static void verifySchema(final String configurationResource) throws ClassNotFoundException, SQLException
    {
        verifySchema(configurationResource, ConfigUtil.getProperties());
    }
    
    public static void verifySchema(final String configurationResource, final Properties configurationProperties) throws ClassNotFoundException, SQLException
    {
        verifySchema(configurationResource, 
                     configurationProperties, 
                     ConfigUtil.getDatabaseName(configurationProperties.getProperty(ConfigUtil.CONNECTION_URL_PROPERTY)));
    }
    
    public static void verifySchema(final String configurationResource,
                                    final Properties configurationProperties,
                                    String databaseName) throws ClassNotFoundException, SQLException
    {
        if (StringUtils.isBlank(databaseName))
        {
            databaseName = ConfigUtil.getDatabaseName(configurationProperties.getProperty(ConfigUtil.CONNECTION_URL_PROPERTY));
        }
        
        JDBCUtil    jdbcUtil = getJDBCUtil(getConfiguration(configurationResource, configurationProperties, databaseName));
        Boolean     databaseExists = jdbcUtil.databaseExists(databaseName);

        logger.info((databaseExists ? "Updating" : "Creating") + " '" + databaseName + "' Schema with " + getConfigurationResourceName(configurationResource) + " configuration");

        if (StringUtils.isNotBlank(configurationResource))
        {
            updateSchema(configurationResource, configurationProperties, databaseName);
        }
        else
        {
            if (!databaseExists)
            {
                jdbcUtil.execute("create schema " + databaseName);
            }
        }
        
        jdbcUtil.close();
    }

    private static String getConfigurationResourceName(final String configurationResource)
    {
        if (StringUtils.isBlank(configurationResource))
        {
            return "UNKNOWN";
        }
        
        Integer endIndex = configurationResource.indexOf(".");
        
        if (endIndex < 0)
        {
            endIndex = configurationResource.length();
        }
        
        return configurationResource.substring(0, endIndex).toUpperCase();
    }

    /**
     * Initialize a new hibernate utility class based on the configuration
     * @param configurationFileName the configuration file name
     */
    public static void init(final String configurationFileName)
    {
        hibernateUtil = new HibernateUtil<Serializable>(configurationFileName, getOverrideProperties());        
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

    private static Configuration getConfiguration(final String configurationResource, final Properties configurationProperties, final String databaseName)
    {
        Properties      overrideProperties = getOverrideProperties(configurationProperties, databaseName);
        Configuration   configuration = new Configuration();

        if (StringUtils.isNotBlank(configurationResource))
        {
            File    configurationFile = new File(configurationResource);
            
            try
            {
                configuration = configurationFile.exists() ? new Configuration().configure(configurationFile) : new Configuration().configure(configurationResource);
            }
            catch (HibernateException e)
            {
                logger.error(e.getMessage(), e);
            }
        }
        
        for (Object key : overrideProperties.keySet())
        {
            String  name = key.toString();

            configuration.setProperty(name, overrideProperties.getProperty(name));
        }
        
        return configuration;
    }

    private static JDBCUtil getJDBCUtil(Configuration configuration) throws ClassNotFoundException, SQLException
    {
        JDBCUtil        jdbcUtil = new JDBCUtil(configuration.getProperty(DRIVER_CLASS_PROPERTY),
                                                ConfigUtil.getRootConnectionUrl(configuration.getProperty(URL_PROPERTY)),
                                                configuration.getProperty(USER_NAME_PROPERTY),
                                                configuration.getProperty(PASSWORD_PROPERTY));
        return jdbcUtil;
    }
   
    private static Properties getOverrideProperties()
    {
        return getOverrideProperties(null);
    }
    
    private static Properties getOverrideProperties(final String databaseName)
    {
        return getOverrideProperties(ConfigUtil.getProperties(), databaseName);
    }
    
    private static Properties getOverrideProperties(final Properties configProperties, final String databaseName)
    {
        Properties  properties = new Properties();
        
        if (configProperties != null)
        {
            String      url = configProperties.getProperty(ConfigUtil.CONNECTION_URL_PROPERTY);
            
            if (StringUtils.isNotBlank(databaseName) && !configProperties.getProperty(ConfigUtil.DIALECT_PROPERTY).equals(DIALECT_H2))
            {
                url = ConfigUtil.getRootConnectionUrl(url) + "/" + databaseName;
            }
            
            overrideProperty(properties, DIALECT_PROPERTY, configProperties.getProperty(ConfigUtil.DIALECT_PROPERTY));
            overrideProperty(properties, DRIVER_CLASS_PROPERTY, configProperties.getProperty(ConfigUtil.DRIVER_CLASS_PROPERTY));
            overrideProperty(properties, URL_PROPERTY, url);
            overrideProperty(properties, USER_NAME_PROPERTY, configProperties.getProperty(ConfigUtil.USER_NAME_PROPERTY));
            overrideProperty(properties, PASSWORD_PROPERTY, configProperties.getProperty(ConfigUtil.PASSWORD_PROPERTY));
        }
        
        return properties;
    }
    
    private static void overrideProperty(Properties properties, final String key, final String value)
    {
        if (StringUtils.isNotBlank(value))
        {
            properties.setProperty(key, value);
        }
    }
}
