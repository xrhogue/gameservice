/**
 * Character.java
 */
package com.bogie.character.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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

    @OneToMany
    @Cascade(CascadeType.ALL)
    @JoinTable(name="character_stats", joinColumns=@JoinColumn(name="character_id"), inverseJoinColumns=@JoinColumn(name="character_stat_id"))
    private List<CharacterStat>	stats;
    
    @OneToOne
    @Cascade(CascadeType.ALL)
    private CharacterRace   race;

    @OneToMany
    @Cascade(CascadeType.ALL)
    @JoinTable(name="character_skills", joinColumns=@JoinColumn(name="character_id"), inverseJoinColumns=@JoinColumn(name="character_skill_id"))
    private List<CharacterSkill>    skills;
    
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

    /**
     * @return the stats
     */
    public List<CharacterStat> getStats()
    {
        return stats;
    }

    /**
     * @param stats the stats to set
     */
    public void setStats(List<CharacterStat> stats)
    {
        this.stats = stats;
    }

    /**
     * @return the race
     */
    public CharacterRace getRace()
    {
        return race;
    }

    /**
     * @param race the race to set
     */
    public void setRace(CharacterRace race)
    {
        this.race = race;
    }

    /**
     * @return the skills
     */
    public List<CharacterSkill> getSkills()
    {
        return skills;
    }

    /**
     * @param skills the skills to set
     */
    public void setSkills(List<CharacterSkill> skills)
    {
        this.skills = skills;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Long version)
    {
        this.version = version;
    }
}
