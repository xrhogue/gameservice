/**
 * Character.java
 */
package com.bogie.character.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Character 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="`character`")
public class Character implements Serializable
{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Version
    @Column
    private Long version;
    
    @Column(nullable=false)
    private String  name;

    @Column
    private CharacterRace   race;
    
    /**
     * Default constructor
     */
    public Character()
    {
    }

    /**
     * @return Returns the id.
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @return the version
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     * @param id The id to set.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
