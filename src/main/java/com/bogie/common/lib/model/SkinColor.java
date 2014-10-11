package com.bogie.common.lib.model;

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
@Table(name=SkinColor.TABLE_NAME)
public class SkinColor extends PhysicalAppearance<SkinColor> implements PhysicalAttribute, Serializable
{
	private static final long serialVersionUID = -461836289872060256L;

	public static final String TABLE_NAME = "SKIN_COLOR";
	
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
