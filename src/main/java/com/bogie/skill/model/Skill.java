/**
 * Skill.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.skill.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.bogie.common.model.Stat;
import com.bogie.race.model.Race;

/**
 * Skill 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Entity
@Table(name="skill")
public class Skill implements Serializable
{
	private static final long serialVersionUID = -2177231468677011854L;

	@Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;
    
    @Version
    @Column
    private Long version;
    
    @Column(nullable=false)
    private String  name;
    
    @Column
    private String  shortName;
    
    @ManyToOne
    private Skill   parent;
    
    @ManyToOne
    @JoinTable(name="race_skills")
    private Race    race;
    
    @Column(nullable=false)
    private Boolean selectable = true;
    
    @Column(nullable=false)
    private Integer baseCost = 3;
    
    @Column(nullable=false)
    private Integer levelCost = 3;
    
    @ManyToOne(optional=false)
    private Stat    primaryStat;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="parent", fetch=FetchType.EAGER)
    private Set<Skill>  children = new HashSet<Skill>();
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Stat>   secondaryStats = new HashSet<Stat>();
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Skill> prerequisites = new HashSet<Skill>();
    
    /**
     * Default constructor
     */
    public Skill()
    {
    }

    /**
     * Default constructor
     * @param skill
     */
    public Skill(Skill skill)
    {
        update(skill);
    }
    
    /**
     * Updates the skill with the DTO info
     * @param skill the update info
     * @return itself
     */
    public Skill update(Skill skill)
    {
        name = skill.getName();
        shortName = skill.getShortName();
        selectable = skill.isSelectable();
        baseCost = skill.getBaseCost();
        levelCost = skill.getLevelCost();
        
        return this;
    }

    /**
     * @return the baseCost
     */
    public Integer getBaseCost()
    {
        return baseCost;
    }

    /**
     * @param baseCost the baseCost to set
     */
    public void setBaseCost(Integer baseCost)
    {
        this.baseCost = baseCost;
    }

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    /**
     * @return the version
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     * @return the levelCost
     */
    public Integer getLevelCost()
    {
        return levelCost;
    }

    /**
     * @param levelCost the levelCost to set
     */
    public void setLevelCost(Integer levelCost)
    {
        this.levelCost = levelCost;
    }

    /**
     * @return the selectable
     */
    public Boolean isSelectable()
    {
        return selectable;
    }

    /**
     * @param selectable the selectable to set
     */
    public void setSelectable(Boolean selectable)
    {
        this.selectable = selectable;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the parent
     */
    public Skill getParent()
    {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Skill parent)
    {
        this.parent = parent;
    }

    /**
     * @return the race
     */
    public Race getRace()
    {
        return race;
    }

    /**
     * @param race the race to set
     */
    public void setRace(Race race)
    {
        this.race = race;
    }

    /**
     * @return the primaryStat
     */
    public Stat getPrimaryStat()
    {
        return primaryStat;
    }

    /**
     * @param primaryStat the primaryStat to set
     */
    public void setPrimaryStat(Stat primaryStat)
    {
        this.primaryStat = primaryStat;
    }

    /**
     * @return the secondaryStats
     */
    public Set<Stat> getSecondaryStats()
    {
        return secondaryStats;
    }

    /**
     * @param secondaryStats the secondaryStats to set
     */
    public void setSecondaryStats(Set<Stat> secondaryStats)
    {
        this.secondaryStats = secondaryStats;
    }

    /**
     * @return the shortName
     */
    public String getShortName()
    {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    /**
     * @return the children
     */
    public Set<Skill> getChildren()
    {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(Set<Skill> children)
    {
        this.children = children;
    }

    /**
     * @param child the child to add
     */
    public void addChild(Skill child)
    {
        child.setParent(this);
        children.add(child);
    }

    /**
     * @return the prerequisites
     */
    public Set<Skill> getPrerequisites()
    {
        return prerequisites;
    }

    /**
     * @param prerequisites the prerequisites to set
     */
    public void setPrerequisites(Set<Skill> prerequisites)
    {
        this.prerequisites = prerequisites;
    }
    
    /**
     * @param prerequisite the prerequisite to add
     */
    public void addPrerequisite(Skill prerequisite)
    {
        prerequisites.add(prerequisite);
    }
}
