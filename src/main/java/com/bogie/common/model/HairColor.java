package com.bogie.common.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * HairColor 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="hair_color")
public class HairColor extends PhysicalAppearance<HairColor> implements PhysicalAttribute, Serializable
{
	private static final long serialVersionUID = -7754051533487068528L;

	/**
     * Default constructor
     */
    public HairColor()
    {
    }

    /**
     * Default constructor
     * @param name
     */
    public HairColor(String name)
    {
        super(name);
    }
}
