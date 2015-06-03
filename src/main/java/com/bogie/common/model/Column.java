/**
 * Column.java
 */
package com.bogie.common.model;

import java.sql.Types;

import com.google.gson.Gson;

/**
 * Column
 *
 * @author Richard Hogue
 */
public class Column
{
    public enum Type
    {
        STRING(Types.VARCHAR),
        LONGTEXT(Types.CLOB),
        INTEGER(Types.INTEGER),
        DOUBLE(Types.DOUBLE),
        DATETIME(Types.TIMESTAMP),
        DATE(Types.DATE),
        UNKNOWN(Types.VARCHAR);
        
        private Integer sqlType;
        
        private Type(final Integer sqlType)
        {
            this.sqlType = sqlType;
        }
        
        public Integer getSQLType()
        {
            return sqlType;
        }
    };
    
    private String  name;
    private Type    type = Type.UNKNOWN;
    private Integer length = -1;
    
    public Column(final String name)
    {
        this.name = name;
    }
    
    public Column(final String name, final Type type)
    {
        this.name = name;
        this.type = type;
    }
    
    public Column(final String name, final Type type, final Integer length)
    {
        this.name = name;
        this.type = type;
        this.length = length;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the type
     */
    public Type getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type)
    {
        this.type = type;
    }

    /**
     * @return the length
     */
    public Integer getLength()
    {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(Integer length)
    {
        this.length = length;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }
}
