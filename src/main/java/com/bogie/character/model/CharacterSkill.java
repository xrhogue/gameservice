/**
 * CharacterSkill.java
 */
package com.bogie.character.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.bogie.skill.model.Skill;

/**
 * CharacterSkill
 *
 * @author Richard Hogue
 */
@Entity
@Table(name="character_skill")
public class CharacterSkill implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @Cascade(CascadeType.PERSIST)
    private Skill   skill;
    
    @Column(name="base_level")
    private Integer baseLevel;
    
    public CharacterSkill()
    {     
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
     * @return the skill
     */
    public Skill getSkill()
    {
        return skill;
    }

    /**
     * @param skill the skill to set
     */
    public void setSkill(Skill skill)
    {
        this.skill = skill;
    }

    /**
     * @return the baseLevel
     */
    public Integer getBaseLevel()
    {
        return baseLevel;
    }

    /**
     * @param baseLevel the baseLevel to set
     */
    public void setBaseLevel(Integer baseLevel)
    {
        this.baseLevel = baseLevel;
    }
}
