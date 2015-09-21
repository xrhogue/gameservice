/**
 * RaceSkinColor.java
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

import com.bogie.common.model.SkinColor;

/**
 * RaceSkinColor 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="race_skin_color")
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
    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name="skin_color_id")
    @Access(AccessType.PROPERTY)
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
