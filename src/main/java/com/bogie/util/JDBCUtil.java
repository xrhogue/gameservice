/**
 * JDBCUtil.java
 */
package com.bogie.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.bogie.common.model.Column;
import com.bogie.common.model.Table;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

/**
 * JDBCUtil
 *
 * @author Richard Hogue
 */
public class JDBCUtil
{
    public static final String  DRIVER_CLASS_H2 = "org.h2.Driver";
    public static final String  DRIVER_CLASS_MYSQL = "com.mysql.jdbc.Driver";

    public enum Type
    {
        MYSQL("mysql", "MySQL", DRIVER_CLASS_MYSQL),
        H2("h2", "H2", DRIVER_CLASS_H2);
        
        private String  name;;
        private String  label;
        private String  driverClass;
        
        private Type(final String name, final String label, final String driverClass)
        {
            this.name = name;
            this.label = label;
            this.driverClass = driverClass;
        }
        
        public static Type get(final String name)
        {
            for (Type databaseType : values())
            {
                if (databaseType.getName().toUpperCase().equals(name.toUpperCase()))
                {
                    return databaseType;
                }
            }
            
            return null;
        }
        
        public String getName()
        {
            return name;
        }
        
        public String getLabel()
        {
            return label;
        }
        
        public String getDriverClass()
        {
            return driverClass;
        }

        /**
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString()
        {
            return getLabel();
        }       
    }

    public static final String BLANK_NAME = "name cannot be blank";
    public static final String BLANK_SAFE_NAME = "name cannot be blank (original before conversion: '%s')";

    protected static final Logger   logger = Logger.getLogger(JDBCUtil.class);
    
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
        this(ConfigUtil.getProperties(propertiesFileName));
    }
    
    /**
     * Constructor
     * @param properties the properties to use for registration
     * @throws ClassNotFoundException if the driver class does not exist
     * @throws SQLException if the connection cannot be created
     */
    public JDBCUtil(final Properties properties) throws ClassNotFoundException, SQLException
    {
        ConfigUtil  configUtil = new ConfigUtil(properties);
        
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
     * Gets the JDBC Connection URL based on the database type
     * @param type the database type
     * @param host the instance host
     * @param port the instance port
     * @param database the instance database
     * @return
     */
    public static String getConnectionUrl(final Type type, final String host, final String port, final String database)
    {
        if (type.equals(Type.MYSQL))
        {
            return ("jdbc:" + Type.MYSQL.getName() + "://" + host + (port != null ? ":" + port : "") + (StringUtils.isNotBlank(database) ? "/" + database : ""));
        }
        else if (type.equals(Type.H2))
        {
            return ("jdbc:" + Type.H2.getName() + ":./" + host + ";SCHEMA=" + database.toUpperCase());
        }
        
        return null;      
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
     * Gets the row results from a result set
     * @param resultSet the query result set
     * @return the array of row data from the result set
     */
    public List<List<?>> getRows(final ResultSet resultSet)
    {
        try
        {
            if (!resultSet.first())
            {
                return null;
            }
            
            List<List<?>>   rows = new ArrayList<List<?>>();
            
            do
            {
                rows.add(getRow(resultSet));
            }
            while (resultSet.next());
            
            return rows;
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage(), e);
        }
        
        return null;
    }
    
    /**
     * Gets the next row from a result set
     * @param resultSet the result set containing the row
     * @return the next row of data
     */
    public List<?> getNextRow(final ResultSet resultSet)
    {
        try
        {
            if (resultSet.isClosed() || resultSet.isAfterLast())
            {
                return null;
            }
            
            resultSet.next();
            
            return getRow(resultSet);
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage(), e);
        }
        
        return null;
    }
    
    /**
     * Gets the current row from the result set
     * @param resultSet the result set of data
     * @return the current row of data
     */
    public List<?> getRow(final ResultSet resultSet)
    {
        try
        {
            if (resultSet.isClosed() || resultSet.isAfterLast())
            {
                return null;
            }
            
            List<Object>    row = new ArrayList<Object>();
            
            for (int index = 1; index <= resultSet.getMetaData().getColumnCount(); index++)
            {
                row.add(resultSet.getObject(index));
            }
            
            return row;
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage(), e);
        }
        
        return null;
    }
    
    /**
     * Gets the column metadata from the result set
     * @param resultSet the result set of data and metadata
     * @return the list of column metadata
     */
    public List<Column> getColumns(final ResultSet resultSet)
    {
        try
        {
            List<Column>        columns = new ArrayList<Column>();
            ResultSetMetaData   resultSetMetaData = resultSet.getMetaData();
            
            for (int index = 1; index <= resultSetMetaData.getColumnCount(); index++)
            {
                columns.add(new Column(resultSetMetaData.getColumnName(index),
                                       resultSetMetaData.getColumnLabel(index),
                                       Column.Type.valueOf(resultSetMetaData.getColumnTypeName(index)),
                                       resultSetMetaData.getColumnDisplaySize(index)));
            }
            
            return columns;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return null;
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
        
        if (StringUtils.isBlank(databaseName))
        {
            return false;
        }
        
        while (resultSet.next())
        {
            databaseNames.add(resultSet.getString(1).toUpperCase());
        }
        
        return databaseNames.contains(databaseName.toUpperCase());
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
        if (StringUtils.isBlank(tableName))
        {
            return false;
        }
        
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
            tableNames.add(resultSet.getString(1).toUpperCase());
            
        }
        
        return tableNames.contains(tableName.toUpperCase());
    }
        
    /**
     * checks for the existence of a column by name using the current connection
      * @param tableName the name of the table containing the column
     * @param columnName the name of the column to check for
     * @return true if the column exists, false otherwise
     * @throws SQLException if unable to execute the SQL query
     */
    public Boolean columnExists(final String tableName, final String columnName) throws SQLException
    {
        return columnExists(null, tableName, columnName);
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
        if (StringUtils.isBlank(tableName) || StringUtils.isBlank(columnName))
        {
            return false;
        }
        
        try
        {
            if (StringUtils.isNotBlank(databaseName))
            {
                execute("use `" + databaseName + "`");
            }
            
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

    public void createSchema(final Table table) throws SQLException
    {
        createSchema(table.getName(), table.getColumns());
    }

    public void createSchema(final String tableName, final List<Column> columns) throws SQLException
    {
        execute(getCreateTable(tableName) + " " + getTableInfo(columns) + ";");
    }

    public void verifySchema(final Table table) throws SQLException
    {
        if (StringUtils.isNotBlank(table.getDatabaseName()))
        {
            if (!databaseExists(table.getDatabaseName()))
            {
                execute("create schema " + table.getDatabaseName());
            }
            
            execute("use `" + table.getDatabaseName() + "`");
        }
        
        verifySchema(table.getName(), table.getColumns());
    }

    public void verifySchema(final String tableName, final List<Column> columns) throws SQLException
    {
        if (!tableExists(tableName))
        {
            execute(getCreateTable(tableName) + " " + getTableInfo(columns) + ";");
        }
        else
        {
            List<Column>    addColumns = new ArrayList<Column>();
            
            for (Column column : columns)
            {
                if (!columnExists(tableName, getSafeName(column.getName())))
                {
                    addColumns.add(column);
                }
            }
            
            if (!addColumns.isEmpty())
            {
                execute(getAlterTable(tableName) + " " + getAlterTableInfo(addColumns) + ";");
            }
        }
    }
    
    public Integer insert(final Table table, final Object record) throws SQLException
    {
        return  insert(table.getName(), table.getColumns(), getData(table.getColumns(), record));
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
    
    private String getAlterTable(final String tableName)
    {
        return "alter table `" + getSafeName(tableName) + "`"; 
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
            
            tableInfo += getColumnInfo(column);
        }
        
        return tableInfo + ")";
    }
    
    private String getAlterTableInfo(final List<Column> columns)
    {
        String  tableInfo = " ";
        
        for (Column column : columns)
        {
            if (tableInfo.length() > 1)
            {
                tableInfo += ", ";
            }
            
            tableInfo += getAddColumn(column);
        }
        
        return tableInfo;
    }

    private String getAddColumn(final Column column)
    {
        return "add column " + getColumnInfo(column);
    }
    
    private String getColumnInfo(final Column column)
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
                
            case LONGBLOB:
                return "longblob";
                
            case FLOAT:
                return "float";
                
            case BIT:
                return "bit(1)";
                
            case VARCHAR:
            case STRING:
            case UNKNOWN:
                return "varchar(255)";
                
            default:
                break;
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
    
    @SuppressWarnings("unchecked")
    private List<Object> getData(final List<Column> columns, final Object record)
    {
        List<Object>    data = new ArrayList<Object>();
        
        if (record instanceof Collection)
        {
            data.addAll((Collection<Object>)record);
            
            return data;
        }
        
        for (Column column : columns)
        {
            try
            {
                Field   field = record.getClass().getDeclaredField(column.getName());
                
                if (field != null)
                {
                    field.setAccessible(true);
                    data.add(field.get(record));
                }
                else
                {
                    data.add(null);                    
                }
            }
            catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
            {
                data.add(null);
                
                logger.error(e.getMessage(), e);
            }
        }
        
        return data;
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
            for (Type type : Type.values())
            {
                try
                {
                    register(type.getDriverClass());
                    
                    return;
                }
                catch (ClassNotFoundException e)
                {
                    logger.debug("'" + type.getDriverClass() +"' does not exist. continuing");
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
