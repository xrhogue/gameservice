/**
 * RaceEyeColor.java
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

import com.bogie.common.model.EyeColor;

/**
 * RaceEyeColor 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="race_eye_color")
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
    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name="eye_color_id")
    @Access(AccessType.PROPERTY)
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
