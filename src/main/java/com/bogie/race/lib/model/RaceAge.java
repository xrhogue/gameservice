/**
 * RaceAge.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.lib.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RaceAge 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="RACE_AGE")
public class RaceAge implements Serializable
{
	private static final long serialVersionUID = -199487998148424187L;

	@Id
    @GeneratedValue
    @Column(nullable=false)
    private int id;
    
    @Column(name="young_age", nullable=false)
    private int youngAge;
    
    @Column(name="mature_age", nullable=false)
    private int matureAge;
    
    @Column(name="middle_age", nullable=false)
    private int middleAge;
    
    @Column(name="old_age", nullable=false)
    private int oldAge;
    
    @Column(name="venerable_age", nullable=false)
    private int venerableAge;

    @Column(name="max_age", nullable=false)
    private int maxAge;
    
    /**
     * Default constructor
     */
    public RaceAge()
    {
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the matureAge
     */
    public int getMatureAge()
    {
        return matureAge;
    }

    /**
     * @param matureAge the matureAge to set
     */
    public void setMatureAge(int matureAge)
    {
        this.matureAge = matureAge;
    }

    /**
     * @return the maxAge
     */
    public int getMaxAge()
    {
        return maxAge;
    }

    /**
     * @param maxAge the maxAge to set
     */
    public void setMaxAge(int maxAge)
    {
        this.maxAge = maxAge;
    }

    /**
     * @return the middleAge
     */
    public int getMiddleAge()
    {
        return middleAge;
    }

    /**
     * @param middleAge the middleAge to set
     */
    public void setMiddleAge(int middleAge)
    {
        this.middleAge = middleAge;
    }

    /**
     * @return the oldAge
     */
    public int getOldAge()
    {
        return oldAge;
    }

    /**
     * @param oldAge the oldAge to set
     */
    public void setOldAge(int oldAge)
    {
        this.oldAge = oldAge;
    }

    /**
     * @return the venerableAge
     */
    public int getVenerableAge()
    {
        return venerableAge;
    }

    /**
     * @param venerableAge the venerableAge to set
     */
    public void setVenerableAge(int venerableAge)
    {
        this.venerableAge = venerableAge;
    }

    /**
     * @return the youngAge
     */
    public int getYoungAge()
    {
        return youngAge;
    }

    /**
     * @param youngAge the youngAge to set
     */
    public void setYoungAge(int youngAge)
    {
        this.youngAge = youngAge;
    }
}
