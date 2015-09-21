/**
 * CharacterStat.java
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

import com.bogie.common.model.Stat;

/**
 * CharacterStat
 *
 * @author Richard Hogue
 */
@Entity
@Table(name="character_stat")
public class CharacterStat implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @Cascade(CascadeType.PERSIST)
    private Stat    stat;
    
    @Column
    private Integer value;
    
    public CharacterStat()
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
     * @return the stat
     */
    public Stat getStat()
    {
        return stat;
    }

    /**
     * @param stat the stat to set
     */
    public void setStat(Stat stat)
    {
        this.stat = stat;
    }

    /**
     * @return the value
     */
    public Integer getValue()
    {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Integer value)
    {
        this.value = value;
    }
}
