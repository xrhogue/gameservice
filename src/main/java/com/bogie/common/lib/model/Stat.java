/**
 * Stat.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.common.lib.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Stat 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="STAT")
public class Stat implements Serializable
{
	private static final long serialVersionUID = -3308122051355560773L;

	@Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;
    
    @Version
    @Column
    private Long version;
    
    @Column
    private char code;
    
    @Column
    private String  shortForm;
    
    @Column
    private String  longForm;
    
    @Column
    private int multiplier;
    
    /**
     * Default constructor
     */
    public Stat()
    {
    }

    /**
     * Default constructor
     * @param stat the stat to update from
     */
    public Stat(Stat stat)
    {
        update(stat);
    }
    
    /**
     * Updates the stat with new info
     * @param stat the update info
     * @return itself
     */
    public Stat update(Stat stat)
    {
        code = stat.getCode();
        shortForm = stat.getShortForm();
        longForm = stat.getLongForm();
        multiplier = stat.getMultiplier();
        
        return this;
    }
    
    /**
     * @return the version
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     * @return the code
     */
    public char getCode()
    {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(char code)
    {
        this.code = code;
    }

    /**
     * @return the id
     */
    public long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return the longForm
     */
    public String getLongForm()
    {
        return longForm;
    }

    /**
     * @param longForm the longForm to set
     */
    public void setLongForm(String longForm)
    {
        this.longForm = longForm;
    }

    /**
     * @return the shortForm
     */
    public String getShortForm()
    {
        return shortForm;
    }

    /**
     * @param shortForm the shortForm to set
     */
    public void setShortForm(String shortForm)
    {
        this.shortForm = shortForm;
    }

    /**
     * @return the multiplier
     */
    public int getMultiplier()
    {
        return multiplier;
    }

    /**
     * @param multiplier the multiplier to set
     */
    public void setMultiplier(int multiplier)
    {
        this.multiplier = multiplier;
    }
}
