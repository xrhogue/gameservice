/**
 * RaceComplexion.java
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

import com.bogie.common.model.Complexion;

/**
 * RaceComplexion 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="race_complexion")
public class RaceComplexion extends RacePhysicalAttribute<Complexion> implements Serializable
{
	private static final long serialVersionUID = 4869670351005278538L;

	/**
     * Default constructor
     */
    public RaceComplexion()
    {
    }
    
    /**
     * Default constructor
     * @param complexion the race complexion
     */
    public RaceComplexion(Complexion complexion)
    {
        setPhysicalAttribute(complexion);
    }
    
    /**
     * @return the complexion
     */
    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name="complexion_id")
    @Access(AccessType.PROPERTY)
    public Complexion getComplexion()
    {
        return getPhysicalAttribute();
    }

    /**
     * @param complexion the complexion to set
     */
    public void setComplexion(Complexion complexion)
    {
        setPhysicalAttribute(complexion);
    }
}
