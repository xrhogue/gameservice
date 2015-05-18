package com.bogie.common.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Complexion 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="complexion")
public class Complexion extends PhysicalAppearance<Complexion> implements PhysicalAttribute, Serializable
{
	private static final long serialVersionUID = -2590925197707506533L;

    /**
     * Default constructor
     */
    public Complexion()
    {
    }
    
    /**
     * Default constructor
     * @param name
     */
    public Complexion(String name)
    {
        super(name);
    }
}
