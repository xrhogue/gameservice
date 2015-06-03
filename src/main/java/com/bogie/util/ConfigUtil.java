/**
 * ConfigUtil.java
 */
package com.bogie.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.bogie.common.service.HibernateService;

/**
 * ConfigUtil
 *
 * @author Richard Hogue
 */
public class ConfigUtil
{
    public static final String  ACCOUNT_ID_PROPERTY = "account_id";
    public static final String  API_KEY_PROPERTY = "api_key";

    public static final String  DIALECT_PROPERTY = "dialect";
    public static final String  DRIVER_CLASS_PROPERTY = "driver_class";
    public static final String  CONNECTION_URL_PROPERTY = "connection_url";
    public static final String  DATABASE_PROPERTY = "database";
    public static final String  USER_NAME_PROPERTY = "user_name";
    public static final String  PASSWORD_PROPERTY = "password";

    public static final String  WEB_SOCKET_DEBUG_PROPERTY = "web_socket_debug";

    public static final String  DIALECT_DEFAULT = "org.hibernate.dialect.MySQLDialect";

    public static final String  DEFAULT_CONFIG_PROPERTIES_FILE_NAME = "config.properties";
    public static final String  DEFAULT_CONFIG_PATH = ".";

    protected static final Logger   logger = Logger.getLogger(ConfigUtil.class);
    
    private String  propertiesFileName;
    
    /**
     * Default constructor
     */
    public ConfigUtil()
    {
        this(DEFAULT_CONFIG_PROPERTIES_FILE_NAME);
    }
    
    /**
     * Default constructor
     * @param propertiesFileName the properties file containing the properties
     */
    public ConfigUtil(final String propertiesFileName)
    {
        this.propertiesFileName = propertiesFileName;
    }
    
    /**
     * Gets the properties located in the default properties file
     * @return a Properties object containing the name-value pairs of properties
     */
    public static Properties getProperties()
    {
        return getProperties(DEFAULT_CONFIG_PROPERTIES_FILE_NAME);
    }    
    
    /**
     * Gets the properties located in the named properties file
     * @param propertiesFileName the properties file containing the properties
     * @return a Properties object containing the name-value pairs of properties
     */
    public static Properties getProperties(final String propertiesFileName)
    {
        File    propertiesFile = getFile(propertiesFileName);
        
        if (propertiesFile == null)
        {
            return null;
        }
        
        Properties  properties = new Properties();
        
        try
        {
            properties.load(new FileReader(propertiesFile));
        }
        catch (Exception e)
        {
            logger.error("Error reading properties file '" + propertiesFileName + "': " + e.getMessage(), e);
            
            return null;
        }
        
        return properties;
    }
    
    /**
     * Gets a property from the default configuration file
     * @param property the property to retrieve
     * @return the property value if set, or null if not
     */
    public static String getProperty(final String property)
    {
        return getProperty(property, null);
    }
    
    /**
     * Gets a property from the local configuration file
     * @param property the property to retrieve
     * @return the property value if set, or null if not
     */
    public String getLocalProperty(final String property)
    {
        return getProperty(propertiesFileName, property, null);
    }
    
    /**
     * Gets a property from the default configuration file
     * @param property the property to retrieve
     * @param defaultValue the default value of the property if not set
     * @return the property value if set, or the default value
     */
    public static String getProperty(final String property, final String defaultValue)
    {
        return getProperties(DEFAULT_CONFIG_PROPERTIES_FILE_NAME).getProperty(property, defaultValue);
    }
    
    /**
     * Gets a property from the local configuration file
     * @param property the property to retrieve
     * @param defaultValue the default value of the property if not set
     * @return the property value if set, or the default value
     */
    public String getLocalProperty(final String property, final String defaultValue)
    {
        return getProperty(propertiesFileName, property, defaultValue);
    }
    
    /**
     * Gets a property from the named configuration file
     * @param propertiesFileName the properties file containing the properties
     * @param property the property to retrieve
     * @param defaultValue the default value of the property if not set
     * @return the property value if set, or the default value
     */
    public static String getProperty(final String propertiesFileName, final String property, final String defaultValue)
    {
        return getProperties(propertiesFileName).getProperty(property, defaultValue);
    }
    
    /**
     * Gets the root connection url string (excluding the database name)
     * @param connectionUrl the full connection url string
     * @return the root connection url string
     */
    public static String getRootConnectionUrl(final String connectionUrl)
    {
        if (StringUtils.isBlank(connectionUrl))
        {
            return connectionUrl;
        }
        
        int lastSeparator = connectionUrl.lastIndexOf("/");
        
        if (lastSeparator <= 0)
        {
            return connectionUrl;            
        }
        
        if (connectionUrl.charAt(lastSeparator - 1) == '/')
        {
            return connectionUrl;
        }
        
        return connectionUrl.substring(0, lastSeparator);
    }
    
    /**
     * Gets the database name from the connection url string
     * @param connectionUrl the url string containing the database name
     * @return the database name
     */
    public static String getDatabaseName(final String connectionUrl)
    {
        if (StringUtils.isBlank(connectionUrl))
        {
            return null;
        }
        
        int lastSeparator = connectionUrl.lastIndexOf("/");
        
        if (lastSeparator <= 0)
        {
            return null;            
        }
        
        if (connectionUrl.charAt(lastSeparator - 1) == '/')
        {
            return null;
        }
        
        return connectionUrl.substring(lastSeparator + 1);
    }
    
    /**
     * Gets the default hibernate override properties
     * @return the default hibernate override properties
     */
    public static Properties getDefaultOverrideProperties()
    {
        return getDefaultOverrideProperties(null);
    }
    
    /**
     * Gets the local hibernate override properties
     * @return the local hibernate override properties
     */
    public Properties getLocalOverrideProperties()
    {
        return getLocalOverrideProperties(null);
    }
    
    /**
     * Gets the default hibernate override properties modified by a specific database name
     * @param databaseName the database name to use with the override properties
     * @return the hibernate override properties
     */
    public static Properties getDefaultOverrideProperties(final String databaseName)
    {
        return getDefaultOverrideProperties(DEFAULT_CONFIG_PROPERTIES_FILE_NAME, databaseName);
    }
    
    /**
     * Gets the local hibernate override properties modified by a specific database name
     * @param databaseName the database name to use with the override properties
     * @return the local hibernate override properties
     */
    public Properties getLocalOverrideProperties(final String databaseName)
    {
        return getDefaultOverrideProperties(propertiesFileName, databaseName);
    }
    
    /**
     * Gets the default hibernate override properties modified by a specific database name
     * @param propertiesFileName the properties file containing the properties
     * @param databaseName the database name to use with the override properties
     * @return the hibernate override properties
     */
    public static Properties getDefaultOverrideProperties(final String propertiesFileName, final String databaseName)
    {
        Properties  properties = new Properties();
        String      url = ConfigUtil.getProperty(propertiesFileName, ConfigUtil.CONNECTION_URL_PROPERTY, null);
        
        if (StringUtils.isNotBlank(databaseName))
        {
            url = getRootConnectionUrl(url) + "/" + databaseName;
        }
        
        overrideProperty(properties, HibernateService.DIALECT_PROPERTY, ConfigUtil.getProperty(propertiesFileName, ConfigUtil.DIALECT_PROPERTY, null));
        overrideProperty(properties, HibernateService.DRIVER_CLASS_PROPERTY, ConfigUtil.getProperty(propertiesFileName, ConfigUtil.DRIVER_CLASS_PROPERTY, null));
        overrideProperty(properties, HibernateService.URL_PROPERTY, url);
        overrideProperty(properties, HibernateService.USER_NAME_PROPERTY, ConfigUtil.getProperty(propertiesFileName, ConfigUtil.USER_NAME_PROPERTY, null));
        overrideProperty(properties, HibernateService.PASSWORD_PROPERTY, ConfigUtil.getProperty(propertiesFileName, ConfigUtil.PASSWORD_PROPERTY, null));
        
        return properties;
    }
    
    public static String getCustomPropertiesFileName(final Properties overrideProperties) throws IOException
    {
        Properties  properties = getProperties();
        
        if (overrideProperties != null)
        {
            properties.putAll(overrideProperties);
        }
        
        File        file = File.createTempFile("config", "properties");
        FileWriter  fileWriter = new FileWriter(file);
        
        properties.store(fileWriter, null);
        
        return file.getAbsolutePath();
    }
    
    private static void overrideProperty(Properties properties, final String key, final String value)
    {
        if (StringUtils.isNotBlank(value))
        {
            properties.setProperty(key, value);
        }
    }
   
    private static File getFile(final String fileName)
    {
        if (StringUtils.isBlank(fileName))
        {
            return null;
        }
        
        File    file = new File(fileName);
        
        if (!file.exists())
        {
            if (file.getAbsolutePath().equalsIgnoreCase(fileName))
            {
                return null;
            }
            
            file = new File(DEFAULT_CONFIG_PATH, fileName);
            
            if (!file.exists())
            {
                return null;
            }
        }
        
        return file;
    }
}
