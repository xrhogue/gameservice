package com.bogie.common.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SkinColor 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="skin_color")
public class SkinColor extends PhysicalAppearance<SkinColor> implements PhysicalAttribute, Serializable
{
	private static final long serialVersionUID = -461836289872060256L;
	
    /**
     * Default constructor
     */
    public SkinColor()
    {
    }

    /**
     * Default constructor
     * @param name
     */
    public SkinColor(String name)
    {
        super(name);
    }
}
