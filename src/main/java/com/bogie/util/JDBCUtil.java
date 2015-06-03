/**
 * JDBCUtil.java
 */
package com.bogie.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.bogie.common.model.Column;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

/**
 * JDBCUtil
 *
 * @author Richard Hogue
 */
public class JDBCUtil
{
    public static final String BLANK_NAME = "name cannot be blank";
    public static final String BLANK_SAFE_NAME = "name cannot be blank (original before conversion: '%s')";

    protected static final Logger   logger = Logger.getLogger(JDBCUtil.class);

    public static final String  MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    
    private String[] commonDrivers = {
            MYSQL_DRIVER
    };
    
    private Connection  connection = null;
    
    /**
     * Default constructor
     * @throws ClassNotFoundException if the driver class does not exist
     * @throws SQLException if the connection cannot be created
     */
    public JDBCUtil() throws ClassNotFoundException, SQLException
    {
        this(null,
             ConfigUtil.getProperty(ConfigUtil.CONNECTION_URL_PROPERTY),
             ConfigUtil.getProperty(ConfigUtil.USER_NAME_PROPERTY),
             ConfigUtil.getProperty(ConfigUtil.PASSWORD_PROPERTY));
    }
    
    /**
     * Constructor
     * @param propertiesFileName the properties file to use for registration
     * @throws ClassNotFoundException if the driver class does not exist
     * @throws SQLException if the connection cannot be created
     */
    public JDBCUtil(final String propertiesFileName) throws ClassNotFoundException, SQLException
    {
        ConfigUtil  configUtil = new ConfigUtil(propertiesFileName);
        
        register(configUtil.getLocalProperty(ConfigUtil.DRIVER_CLASS_PROPERTY));
        
        if (StringUtils.isNotBlank(configUtil.getLocalProperty(ConfigUtil.CONNECTION_URL_PROPERTY)) &&
            StringUtils.isNotBlank(configUtil.getLocalProperty(ConfigUtil.USER_NAME_PROPERTY)))
        {
            connection = getConnection(configUtil.getLocalProperty(ConfigUtil.CONNECTION_URL_PROPERTY),
                                       configUtil.getLocalProperty(ConfigUtil.USER_NAME_PROPERTY),
                                       configUtil.getLocalProperty(ConfigUtil.PASSWORD_PROPERTY));
        }
    }
    
    /**
     * Constructor
     * @param url the connection url string
     * @param userName the connection user name
     * @param password the connection password
     * @throws ClassNotFoundException if the driver class does not exist
     * @throws SQLException if the connection cannot be created
     */
    public JDBCUtil(final String url, final String userName, final String password) throws ClassNotFoundException, SQLException
    {
        this(null, url, userName, password);
    }
    
    /**
     * Constructor
     * @param driver the driver class to use for registration
     * @param url the connection url string
     * @param userName the connection user name
     * @param password the connection password
     * @throws ClassNotFoundException if the driver class does not exist
     * @throws SQLException if the connection cannot be created
     */
    public JDBCUtil(final String driver, final String url, final String userName, final String password) throws ClassNotFoundException, SQLException
    {
        register(driver);
        
        if (StringUtils.isNotBlank(url) && StringUtils.isNotBlank(userName))
        {
            connection = getConnection(url, userName, password);
        }
    }

    /**
     * Closes the currently open connection
     */
    public void close()
    {
        try
        {
            if (connection != null && !connection.isClosed())
            {
                connection.close();
            }
            
            connection = null;
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage(), e);
        }
    }
    
    /**
     * Executes a general SQL statement
     * @param statement the SQL statement to execute on the JDBC instance
     * @throws SQLException if there is an error executing the SQL statement
     */
    public void execute(final String statement) throws SQLException
    {
        getConnection().prepareStatement(statement).execute();
    }

    /**
     * Executes a general SQL query statement
     * @param query the SQL query to execute on the JDBC instance
     * @return the results of the query
     * @throws SQLException if there is an error executing the SQL statement
     */
    public ResultSet query(final String query) throws SQLException
    {
        return getConnection().prepareStatement(query).executeQuery();
    }
    
    /**
     * checks for the existence of a database by name using the current connection
     * @param databaseName the name of the database to check for
     * @return true if the database exists, false otherwise
     * @throws SQLException if unable to execute the SQL query
     */
    public Boolean databaseExists(final String databaseName) throws SQLException
    {
        ResultSet       resultSet = query("show databases");
        List<String>    databaseNames = new ArrayList<String>();
        
        while (resultSet.next())
        {
            databaseNames.add(resultSet.getString(1));
        }
        
        return databaseNames.contains(databaseName);
    }
    
    /**
     * checks for the existence of a table by name using the current connection
     * @param tableName the name of the table to check for
     * @return true if the table exists, false otherwise
     * @throws SQLException if unable to execute the SQL query
     */
    public Boolean tableExists(final String tableName) throws SQLException
    {
        return tableExists(null, tableName);
    }
        
    /**
     * checks for the existence of a table by name using the current connection
     * @param databaseName the name of the database containing the table
     * @param tableName the name of the table to check for
     * @return true if the table exists, false otherwise
     * @throws SQLException if unable to execute the SQL query
     */
    public Boolean tableExists(final String databaseName, final String tableName) throws SQLException
    {
        try
        {
            if (StringUtils.isNotBlank(databaseName))
            {
                execute("use `" + databaseName + "`");
            }
        }
        catch (SQLException e)
        {
            if (e.getMessage().startsWith("Unknown database"))
            {
                return false;
            }
            
            throw e;
        }
        
        ResultSet       resultSet = query("show tables");
        List<String>    tableNames = new ArrayList<String>();
        
        while (resultSet.next())
        {
            tableNames.add(resultSet.getString(1));
        }
        
        return tableNames.contains(tableName);
    }
        
    /**
     * checks for the existence of a column by name using the current connection
     * @param databaseName the name of the database containing the table
     * @param tableName the name of the table containing the column
     * @param columnName the name of the column to check for
     * @return true if the column exists, false otherwise
     * @throws SQLException if unable to execute the SQL query
     */
    public Boolean columnExists(final String databaseName, final String tableName, final String columnName) throws SQLException
    {
        try
        {
            execute("use `" + databaseName + "`");
            
            ResultSet       resultSet = query("desc `" + tableName +"`");
            List<String>    columnNames = new ArrayList<String>();
            
            while (resultSet.next())
            {
                columnNames.add(resultSet.getString(1));
            }
            
            return columnNames.contains(columnName);
        }
        catch (SQLException e)
        {
            if (e.getMessage().startsWith("Unknown database") || e.getMessage().endsWith("doesn't exist"))
            {
                return false;
            }
            
            throw e;
        }
    }

    public void createSchema(final String tableName, final List<Column> columns) throws SQLException
    {
        execute(getCreateTable(tableName) + " " + getTableInfo(columns) + ";");
    }
    
    public Integer insert(final String tableName, final List<Column> columns, final List<Object> record) throws SQLException
    {
        PreparedStatement   preparedStatement = connection.prepareStatement(getInsertStatement(tableName, columns));
        
        getConnection().setAutoCommit(false);
        
        initPreparedStatement(preparedStatement, columns, record).execute();

        getConnection().commit();
        
        return  preparedStatement.getUpdateCount();
    }
    
    public int[] insertBatch(final String tableName, final List<Column> columns, final List<List<Object>> data) throws SQLException
    {
        PreparedStatement   preparedStatement = connection.prepareStatement(getInsertStatement(tableName, columns));
        
        getConnection().setAutoCommit(false);
        
        int[]   results = initPreparedStatementBatch(preparedStatement, columns, data).executeBatch();
        
        getConnection().commit();
        
        return results;
    }
    
    private String getInsertStatement(final String tableName, final List<Column> columns)
    {
        return getInsertTable(tableName) + " " + getInsert(columns);
    }
    
    private String getInsert(final List<Column> columns)
    {
        String  insertColumns = "";
        String  insertValues = "";
        
        for (Column column : columns)
        {
            if (insertColumns.length() > 0)
            {
                insertColumns += ",";
                insertValues += ",";
            }
            
            insertColumns += "`" + getSafeName(column.getName()) + "`";
            insertValues += "?";
        }
        
        return "(" + insertColumns + ") values (" + insertValues + ")";
    }
    
    private PreparedStatement initPreparedStatement(PreparedStatement preparedStatement, final List<Column> columns, final List<Object> record) throws SQLException
    {
        Integer index = 1;
        
        for (Object value : record)
        {
            int sqlType = columns.get(index - 1).getType().getSQLType();
            
            if (value == null)
            {
                preparedStatement.setNull(index, sqlType);
            }
            else
            {
                preparedStatement.setObject(index, value, sqlType);
            }
            
            index++;
        }
        
        return preparedStatement;
    }
    
    private PreparedStatement initPreparedStatementBatch(PreparedStatement preparedStatement, final List<Column> columns, final List<List<Object>> data) throws SQLException
    {
        for (List<Object> record : data)
        {
            Integer index = 1;
            
            for (Object value : record)
            {
                int sqlType = columns.get(index - 1).getType().getSQLType();
                
                if (value == null)
                {
                    preparedStatement.setNull(index, sqlType);
                }
                else
                {
                    preparedStatement.setObject(index, value, sqlType);
                }
                
                index++;
            }
            
            preparedStatement.addBatch();
        }
        
        return preparedStatement;
    }

    private String getCreateTable(final String tableName)
    {
        return "create table `" + getSafeName(tableName) + "`";
    }
    
    private String getInsertTable(final String tableName)
    {
        return "insert into `" + getSafeName(tableName) + "`";
    }
    
    private String getTableInfo(final List<Column> columns)
    {
        String  tableInfo = "(";
        
        for (Column column : columns)
        {
            if (tableInfo.length() > 1)
            {
                tableInfo += ",";
            }
            
            tableInfo += getTableInfo(column);
        }
        
        return tableInfo + ")";
    }
    
    private String getTableInfo(final Column column)
    {
        return getColumnName(column.getName()) + " " + getColumnType(column) + " " + getColumnOptions(column.getLength());
    }
    
    private String getColumnName(final String name)
    {
        return "`" + getSafeName(name) + "`";
    }
    
    private String getSafeName(final String name)
    {
        if (StringUtils.isBlank(name))
        {
            throw new RuntimeException(BLANK_NAME);
        }
        
        String  safeName = name.replaceAll("[^a-zA-Z0-9#\\s]", " ").replaceAll("\\s+", " ").trim().replaceAll(" ", "_").toLowerCase();
        
        if (StringUtils.isBlank(safeName))
        {
            throw new RuntimeException(String.format(BLANK_SAFE_NAME, name));
        }
        
        return (safeName.length() > 32 ? safeName.substring(0, 31) : safeName);
    }
    
    private String getColumnType(final Column column)
    {
        switch (column.getType())
        {
            case DATE:
                return "datetime";
                
            case DATETIME:
                return "datetime";
                
            case DOUBLE:
                return "double";
                
            case INTEGER:
                return "int(11)";
                
            case LONGTEXT:
                return "longtext";
                
            case STRING:
            case UNKNOWN:
                return "varchar(255)";
        }
        
        throw new IllegalArgumentException("Unhandled column type");
    }
    
    private String getColumnOptions(final Integer length)
    {
        if (length > 255)
        {
            return "";
        }
        
        return "DEFAULT NULL";
    }
    
    private Connection getConnection(final String url, final String userName, final String password) throws SQLException
    {
        try
        {
            return DriverManager.getConnection(url, userName, password);
        }
        catch (MySQLSyntaxErrorException e)
        {
            if (e.getMessage().startsWith("Unknown database"))
            {
                return DriverManager.getConnection(ConfigUtil.getRootConnectionUrl(url), userName, password);
            }
            
            throw e;
        }
    }
    
    private void register(final String driver) throws ClassNotFoundException
    {
        if (StringUtils.isBlank(driver))
        {
            for (String commonDriver : commonDrivers)
            {
                try
                {
                    register(commonDriver);
                    
                    return;
                }
                catch (ClassNotFoundException e)
                {
                    logger.debug("'" + commonDriver +"' does not exist. continuing");
                }
            }
            
            throw new ClassNotFoundException("No common database drivers found");
        }
        else
        {
            Class.forName(driver);
        }
    }
    
    private Connection getConnection()
    {
        if (connection != null)
        {
            return connection;
        }
        
        throw new RuntimeException("connection is closed!");
    }
}
