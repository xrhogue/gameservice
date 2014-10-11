/**
 * RaceSkinColor.java
 */
package com.bogie.race.lib.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bogie.common.lib.model.SkinColor;

/**
 * RaceSkinColor 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="RACE_SKIN_COLOR")
public class RaceSkinColor extends RacePhysicalAttribute<SkinColor> implements Serializable
{
	private static final long serialVersionUID = -8944585705945168171L;

	/**
     * Default constructor
     */
    public RaceSkinColor()
    {
    }
    
    /**
     * Default constructor
     * @param skinColor the race skinColor
     */
    public RaceSkinColor(SkinColor skinColor)
    {
        setPhysicalAttribute(skinColor);
    }

    /**
     * @return the skinColor
     */
    public SkinColor getSkinColor()
    {
        return getPhysicalAttribute();
    }

    /**
     * @param skinColor the skinColor to set
     */
    public void setSkinColor(SkinColor skinColor)
    {
        setPhysicalAttribute(skinColor);
    }
}    
