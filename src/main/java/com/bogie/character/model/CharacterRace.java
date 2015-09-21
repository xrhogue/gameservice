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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.bogie.common.model.Complexion;
import com.bogie.common.model.EyeColor;
import com.bogie.common.model.HairColor;
import com.bogie.common.model.SkinColor;
import com.bogie.race.model.Race;

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
    @Cascade(CascadeType.PERSIST)
    private Race    race;
    
    @Column
    private Integer age;
    
    @Column
    private Integer height;
    
    @Column
    private Integer weight;
    
    @ManyToOne
    @Cascade(CascadeType.PERSIST)
    private Complexion  complexion;
    
    @ManyToOne
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name="eye_color_id")
    private EyeColor    eyeColor;
    
    @ManyToOne
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name="hair_color_id")
    private HairColor   hairColor;
    
    @ManyToOne
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name="skin_color_id")
    private SkinColor   skinColor;
    
    public CharacterRace()
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
     * @return the age
     */
    public Integer getAge()
    {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age)
    {
        this.age = age;
    }

    /**
     * @return the height
     */
    public Integer getHeight()
    {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Integer height)
    {
        this.height = height;
    }

    /**
     * @return the weight
     */
    public Integer getWeight()
    {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }

    /**
     * @return the complexion
     */
    public Complexion getComplexion()
    {
        return complexion;
    }

    /**
     * @param complexion the complexion to set
     */
    public void setComplexion(Complexion complexion)
    {
        this.complexion = complexion;
    }

    /**
     * @return the eyeColor
     */
    public EyeColor getEyeColor()
    {
        return eyeColor;
    }

    /**
     * @param eyeColor the eyeColor to set
     */
    public void setEyeColor(EyeColor eyeColor)
    {
        this.eyeColor = eyeColor;
    }

    /**
     * @return the hairColor
     */
    public HairColor getHairColor()
    {
        return hairColor;
    }

    /**
     * @param hairColor the hairColor to set
     */
    public void setHairColor(HairColor hairColor)
    {
        this.hairColor = hairColor;
    }

    /**
     * @return the skinColor
     */
    public SkinColor getSkinColor()
    {
        return skinColor;
    }

    /**
     * @param skinColor the skinColor to set
     */
    public void setSkinColor(SkinColor skinColor)
    {
        this.skinColor = skinColor;
    }
}
