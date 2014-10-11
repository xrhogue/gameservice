/**
 * RaceEyeColor.java
 */
package com.bogie.race.lib.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bogie.common.lib.model.EyeColor;

/**
 * RaceEyeColor 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="RACE_EYE_COLOR")
public class RaceEyeColor extends RacePhysicalAttribute<EyeColor> implements Serializable
{
	private static final long serialVersionUID = 4963843055972135904L;

	/**
     * Default constructor
     */
    public RaceEyeColor()
    {
    }
    
    /**
     * Default constructor
     * @param eyeColor the race eyeColor
     */
    public RaceEyeColor(EyeColor eyeColor)
    {
        setPhysicalAttribute(eyeColor);
    }
    
    /**
     * @return the eyeColor
     */
    public EyeColor getEyeColor()
    {
        return getPhysicalAttribute();
    }

    /**
     * @param eyeColor the eyeColor to set
     */
    public void setEyeColor(EyeColor eyeColor)
    {
        setPhysicalAttribute(eyeColor);
    }
}
