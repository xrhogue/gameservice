/**
 * Race.java
 */
package com.bogie.race.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy=GenerationType.AUTO)
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
    private List<Race>  children = new ArrayList<Race>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="race", fetch=FetchType.EAGER)
    private List<RaceSkinColor>  skinColors = new ArrayList<RaceSkinColor>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="race", fetch=FetchType.EAGER)
    private List<RaceHairColor>  hairColors = new ArrayList<RaceHairColor>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="race", fetch=FetchType.EAGER)
    private List<RaceEyeColor>   eyeColors = new ArrayList<RaceEyeColor>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="race", fetch=FetchType.EAGER)
    private List<RaceComplexion> complexions = new ArrayList<RaceComplexion>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="race", fetch=FetchType.EAGER)
    private List<RaceStat> raceStats = new ArrayList<RaceStat>();
    
    /**
     * Default constructor
     */
    public Race()
    {
    }
    
    /**
     * Default constructor
     */
    public Race(final Long id)
    {
        this.id = id;
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
    public List<Race> getChildren()
    {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<Race> children)
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
    public List<RaceSkinColor> getSkinColors()
    {
        return skinColors;
    }

    /**
     * @param skinColors the skinColors to set
     */
    public void setSkinColors(List<RaceSkinColor> skinColors)
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
    public List<RaceHairColor> getHairColors()
    {
        return hairColors;
    }

    /**
     * @param hairColors the hairColors to set
     */
    public void setHairColors(List<RaceHairColor> hairColors)
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
    public List<RaceEyeColor> getEyeColors()
    {
        return eyeColors;
    }

    /**
     * @param eyeColors the eyeColors to set
     */
    public void setEyeColors(List<RaceEyeColor> eyeColors)
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
    public List<RaceComplexion> getComplexions()
    {
        return complexions;
    }

    /**
     * @param complexions the complexions to set
     */
    public void setComplexions(List<RaceComplexion> complexions)
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
    public List<RaceStat> getRaceStats()
    {
        return raceStats;
    }

    /**
     * @param raceStats the raceStats to set
     */
    public void setRaceStats(List<RaceStat> raceStats)
    {
        this.raceStats = raceStats;
    }
}
