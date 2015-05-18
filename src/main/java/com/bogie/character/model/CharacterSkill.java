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
    private Skill   skill;
    
    @Column
    private Integer baseLevel;
}
