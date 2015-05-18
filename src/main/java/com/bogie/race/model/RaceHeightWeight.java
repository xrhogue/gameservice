/**
 * RaceHeightWeight.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bogie.common.model.Gender;

/**
 * RaceHeightWeight 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="race_height_weight")
public class RaceHeightWeight implements Serializable
{
	private static final long serialVersionUID = -300007194857722423L;

	@Id
    @GeneratedValue
    @Column(nullable=false)
    private int id;
    
    @Column(nullable=false)
    private Race    race;
    
    @Column
    private Gender  gender;
    
    @Column(nullable=false)
    private int minHeight;
    
    @Column(nullable=false)
    private int maxHeight;
    
    @Column(nullable=false)
    private int minWeight;
    
    @Column(nullable=false)
    private int maxWeight;
    
    /**
     * Default constructor
     */
    public RaceHeightWeight()
    {
    }

    /**
     * @return the gender
     */
    public Gender getGender()
    {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender)
    {
        this.gender = gender;
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
     * @return the maxHeight
     */
    public int getMaxHeight()
    {
        return maxHeight;
    }

    /**
     * @param maxHeight the maxHeight to set
     */
    public void setMaxHeight(int maxHeight)
    {
        this.maxHeight = maxHeight;
    }

    /**
     * @return the maxWeight
     */
    public int getMaxWeight()
    {
        return maxWeight;
    }

    /**
     * @param maxWeight the maxWeight to set
     */
    public void setMaxWeight(int maxWeight)
    {
        this.maxWeight = maxWeight;
    }

    /**
     * @return the minHeight
     */
    public int getMinHeight()
    {
        return minHeight;
    }

    /**
     * @param minHeight the minHeight to set
     */
    public void setMinHeight(int minHeight)
    {
        this.minHeight = minHeight;
    }

    /**
     * @return the minWeight
     */
    public int getMinWeight()
    {
        return minWeight;
    }

    /**
     * @param minWeight the minWeight to set
     */
    public void setMinWeight(int minWeight)
    {
        this.minWeight = minWeight;
    }

    /**
     * @return the race
     */
    public Race getRace()
    {
        return race;
    }

    /**
     * @param race the race to set
     */
    public void setRace(Race race)
    {
        this.race = race;
    }
}
