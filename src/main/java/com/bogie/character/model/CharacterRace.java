/**
 * CharacterRace.java
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

import com.bogie.common.model.Complexion;
import com.bogie.common.model.EyeColor;
import com.bogie.common.model.HairColor;
import com.bogie.race.model.Race;
import com.bogie.race.model.RaceComplexion;
import com.bogie.race.model.RaceEyeColor;
import com.bogie.race.model.RaceHairColor;

/**
 * CharacterRace
 *
 * @author Richard Hogue
 */
@Entity
@Table(name="character_race")
public class CharacterRace implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Race    race;
    
    @Column
    private Integer age;
    
    @Column
    private Integer height;
    
    @Column
    private Integer weight;
    
    @ManyToOne
    private Complexion  complexion;
    
    @ManyToOne
    private EyeColor    eyeColor;
    
    @ManyToOne
    private HairColor   hairColor;
    
    public CharacterRace()
    {        
    }
}
