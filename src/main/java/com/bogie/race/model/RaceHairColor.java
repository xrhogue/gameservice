/**
 * RaceHairColor.java
 */
package com.bogie.race.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bogie.common.model.HairColor;

/**
 * RaceHairColor 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="race_hair_color")
public class RaceHairColor extends RacePhysicalAttribute<HairColor> implements Serializable
{
	private static final long serialVersionUID = -4347517881667756053L;

	/**
     * Default constructor
     */
    public RaceHairColor()
    {
    }
    
    /**
     * Default constructor
     * @param hairColor the race hairColor
     */
    public RaceHairColor(HairColor hairColor)
    {
        setPhysicalAttribute(hairColor);
    }
    
    /**
     * @return the hairColor
     */
    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name="hair_color_id")
    @Access(AccessType.PROPERTY)
    public HairColor getHairColor()
    {
        return getPhysicalAttribute();
    }

    /**
     * @param hairColor the hairColor to set
     */
    public void setHairColor(HairColor hairColor)
    {
        setPhysicalAttribute(hairColor);
    }
}
