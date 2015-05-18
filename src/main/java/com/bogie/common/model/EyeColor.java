package com.bogie.common.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * EyeColor 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="eye_color")
public class EyeColor extends PhysicalAppearance<EyeColor> implements PhysicalAttribute, Serializable
{
	private static final long serialVersionUID = -166537206050585725L;

	/**
     * Default constructor
     */
    public EyeColor()
    {
    }

    /**
     * Default constructor
     * @param name
     */
    public EyeColor(String name)
    {
        super(name);
    }
}
