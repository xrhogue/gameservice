/**
 * RaceHairColor.java
 */
package com.bogie.race.model;

import java.io.Serializable;

import javax.persistence.Entity;
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
