/**
 * RaceStat.java
 */
package com.bogie.race.lib.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bogie.common.lib.model.Gender;
import com.bogie.common.lib.model.Stat;

/**
 * RaceStat 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="RACE_STAT")
public class RaceStat implements Serializable
{
	private static final long serialVersionUID = -6199015334739815094L;

	@Id
    @GeneratedValue
    @Column(nullable=false)
    private int id;
    
    @ManyToOne(optional=false)
    private Gender  gender;
    
    @ManyToOne(optional=false)
    private Race    race;
    
    @ManyToOne
    private Stat    stat;
    
    @Column(nullable=false)
    private int minimum;
    
    @Column(nullable=false)
    private int maximum;
    
    /**
     * Default constructor
     */
    public RaceStat()
    {
    }
    
    /**
     * @return Returns the id.
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(int id)
    {
        this.id = id;
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

    /**
     * @return the stat
     */
    public Stat getStat()
    {
        return stat;
    }

    /**
     * @param stat the stat to set
     */
    public void setStat(Stat stat)
    {
        this.stat = stat;
    }

    /**
     * @return the maximum
     */
    public int getMaximum()
    {
        return maximum;
    }

    /**
     * @param maximum the maximum to set
     */
    public void setMaximum(int maximum)
    {
        this.maximum = maximum;
    }

    /**
     * @return the minimum
     */
    public int getMinimum()
    {
        return minimum;
    }

    /**
     * @param minimum the minimum to set
     */
    public void setMinimum(int minimum)
    {
        this.minimum = minimum;
    }
    
}
