/**
 * RacePhysicalAttribute.java
 */
package com.bogie.race.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.bogie.common.model.Gender;
import com.bogie.common.model.PhysicalAttribute;

/**
 * RacePhysicalAttribute 
 * 
 * @author Richard Hogue
 * @version 1.0
 * @param <T> the physical attribute
 */
@MappedSuperclass
public class RacePhysicalAttribute<T extends PhysicalAttribute> implements PhysicalAttribute, Serializable
{
	private static final long serialVersionUID = -8592476982820891098L;

	@Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    private Gender  gender;
    
    @ManyToOne(optional=false)
    private Race    race;
    
    @ManyToOne(optional=false, cascade=CascadeType.PERSIST)
    private T physicalAttribute;
    
    /**
     * Default constructor
     */
    public RacePhysicalAttribute()
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
     * @see com.bogie.common.lib.vo.PhysicalAttribute#getName()
     */
    public String getName()
    {
        return physicalAttribute.getName();
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
     * @return the physical attribute
     */
    public T getPhysicalAttribute()
    {
        return physicalAttribute;
    }
    
    /**
     * @param physicalAttribute the physical attribute to set
     */
    public void setPhysicalAttribute(T physicalAttribute)
    {
        this.physicalAttribute = physicalAttribute;
    }
}    
