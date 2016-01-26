/**
 * Table.java
 */
package com.bogie.common.model;

import java.util.List;

/**
 * Table
 *
 * @author Richard Hogue
 */
public class Table
{
    private String          databaseName;
    private String          name;
    private List<Column>    columns;
    
    public Table()
    {        
    }

    public Table(final String name, final List<Column> columns)
    {
        this(null, name, columns);
    }

    public Table(final String databaseName, final String name, final List<Column> columns)
    {
        this.databaseName = databaseName;
        this.name = name;
        this.columns = columns;
    }
    
    /**
     * @return the databaseName
     */
    public String getDatabaseName()
    {
        return databaseName;
    }

    /**
     * @param databaseName the databaseName to set
     */
    public void setDatabaseName(String databaseName)
    {
        this.databaseName = databaseName;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the columns
     */
    public List<Column> getColumns()
    {
        return columns;
    }    
}
