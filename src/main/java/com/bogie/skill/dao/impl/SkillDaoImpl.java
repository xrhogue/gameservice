/**
 * SkillDaoImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.skill.dao.impl;

import java.util.List;

import com.bogie.common.dao.impl.GenericDaoImpl;
import com.bogie.skill.dao.SkillDao;
import com.bogie.skill.model.Skill;

/**
 * SkillDaoImpl 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class SkillDaoImpl extends GenericDaoImpl<Long, Skill> implements SkillDao
{
    /**
     * @see com.bogie.skill.dao.SkillDao#getSkill(java.lang.Long)
     */
    public Skill getSkill(Long skillId)
    {
        return get(Skill.class, skillId);
    }

    /**
     * @see com.bogie.skill.dao.SkillDao#saveSkill(com.bogie.skill.model.Skill)
     */
    public Skill saveSkill(Skill skill)
    {
        return saveOrUpdate(skill);
    }
    
    /**
     * @see com.bogie.skill.dao.SkillDao#deleteSkill(com.bogie.skill.model.Skill)
     */
    public void deleteSkill(Skill skill)
    {
        delete(skill);
    }

    /**
     * @see com.bogie.skill.dao.SkillDao#deleteSkill(java.lang.Long)
     */
    public void deleteSkill(Long skillId)
    {
        delete(getSkill(skillId));
    }

    /**
     * @see com.bogie.skill.dao.SkillDao#findSkills()
     */
    public List<Skill> findSkills()
    {
        return find("from Skill");
    }

    /**
     * @see com.bogie.skill.dao.SkillDao#findSkills(com.bogie.skill.model.Skill)
     */
    public List<Skill> findSkills(Skill parent)
    {
        if (parent == null)
        {
            return find("from Skill as skill where skill.parent is null");
        }
        
        Skill   skill = new Skill();
        
        skill.setParent(parent);
        
        return find("from Skill as skill where skill.parent=:parent", skill);
    }

    /**
     * @see com.bogie.skill.dao.SkillDao#findSkills(java.lang.Long)
     */
    public List<Skill> findSkills(Long parentId)
    {
        if (parentId == null)
        {
            return find("from Skill as skill where skill.parent is null");
        }

        return find("from Skill as skill where skill.parent.id=:id", new Skill(parentId));
    }
}
