/**
 * Race.java
 */
package com.bogie.race.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Race 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="race")
public class Race implements Serializable
{
	private static final long serialVersionUID = -3214017671150454948L;

	@Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;
    
    @Version
    @Column
    private Long version;
    
    @ManyToOne
    private Race    parent;
    
    @Column(nullable=false)
    private String  name;
    
    @Column
    private boolean selectable;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="parent")
    private Set<Race>  children = new HashSet<Race>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="race", fetch=FetchType.EAGER)
    private Set<RaceSkinColor>  skinColors = new HashSet<RaceSkinColor>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="race", fetch=FetchType.EAGER)
    private Set<RaceHairColor>  hairColors = new HashSet<RaceHairColor>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="race", fetch=FetchType.EAGER)
    private Set<RaceEyeColor>   eyeColors = new HashSet<RaceEyeColor>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="race", fetch=FetchType.EAGER)
    private Set<RaceComplexion> complexions = new HashSet<RaceComplexion>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="race", fetch=FetchType.EAGER)
    private Set<RaceStat> raceStats = new HashSet<RaceStat>();
    
    /**
     * Default constructor
     */
    public Race()
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
     * @return Returns the parent.
     */
    public Race getParent()
    {
        return parent;
    }

    /**
     * @param parent The parent to set.
     */
    public void setParent(Race parent)
    {
        this.parent = parent;
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

    /**
     * @return Returns the selectable.
     */
    public boolean isSelectable()
    {
        return selectable;
    }

    /**
     * @param selectable The selectable to set.
     */
    public void setSelectable(boolean selectable)
    {
        this.selectable = selectable;
    }

    /**
     * @return the children
     */
    public Set<Race> getChildren()
    {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(Set<Race> children)
    {
        this.children = children;
    }

    /**
     * @param child the child to add
     */
    public void addChild(Race child)
    {
        child.setParent(this);
        children.add(child);
    }

    /**
     * @return the skinColors
     */
    public Set<RaceSkinColor> getSkinColors()
    {
        return skinColors;
    }

    /**
     * @param skinColors the skinColors to set
     */
    public void setSkinColors(Set<RaceSkinColor> skinColors)
    {
        this.skinColors = skinColors;
    }

    /**
     * @param skinColor the skinColor to add
     */
    public void addSkinColor(RaceSkinColor skinColor)
    {
        skinColor.setRace(this);
        skinColors.add(skinColor);
    }

    /**
     * @return the hairColors
     */
    public Set<RaceHairColor> getHairColors()
    {
        return hairColors;
    }

    /**
     * @param hairColors the hairColors to set
     */
    public void setHairColors(Set<RaceHairColor> hairColors)
    {
        this.hairColors = hairColors;
    }

    /**
     * @param hairColor the hairColor to add
     */
    public void addHairColor(RaceHairColor hairColor)
    {
        hairColor.setRace(this);
        hairColors.add(hairColor);
    }

    /**
     * @return the eyeColors
     */
    public Set<RaceEyeColor> getEyeColors()
    {
        return eyeColors;
    }

    /**
     * @param eyeColors the eyeColors to set
     */
    public void setEyeColors(Set<RaceEyeColor> eyeColors)
    {
        this.eyeColors = eyeColors;
    }

    /**
     * @param eyeColor the eyeColor to add
     */
    public void addEyeColor(RaceEyeColor eyeColor)
    {
        eyeColor.setRace(this);
        eyeColors.add(eyeColor);
    }
    
    /**
     * @return the complexions
     */
    public Set<RaceComplexion> getComplexions()
    {
        return complexions;
    }

    /**
     * @param complexions the complexions to set
     */
    public void setComplexions(Set<RaceComplexion> complexions)
    {
        this.complexions = complexions;
    }

    /**
     * @param complexion the complexion to add
     */
    public void addComplexion(RaceComplexion complexion)
    {
        complexion.setRace(this);
        complexions.add(complexion);
    }

    /**
     * @return the raceStats
     */
    public Set<RaceStat> getRaceStats()
    {
        return raceStats;
    }

    /**
     * @param raceStats the raceStats to set
     */
    public void setRaceStats(Set<RaceStat> raceStats)
    {
        this.raceStats = raceStats;
    }
}
