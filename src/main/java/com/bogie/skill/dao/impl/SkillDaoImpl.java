/**
 * SkillDaoImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.skill.dao.impl;

import java.util.List;

import com.bogie.common.dao.impl.GenericDaoImpl;
import com.bogie.skill.dao.SkillDao;
import com.bogie.skill.lib.model.Skill;

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
     * @see com.bogie.skill.dao.SkillDao#deleteSkill(com.bogie.skill.lib.vo.Skill)
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
     * @see com.bogie.skill.dao.SkillDao#findSkills(com.bogie.skill.lib.vo.Skill)
     */
    public List<Skill> findSkills(Skill parent)
    {
        if (parent == null)
        {
            return find("from Skill as skill where skill.parent is null");
        }
        
        return find("from Skill as skill where skill.parent=?", parent);
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
        
        return find("from Skill as skill where skill.parent.id=?", parentId);
    }

    /**
     * @see com.bogie.skill.dao.SkillDao#findAllSkills()
     */
    public List<Skill> findAllSkills()
    {
        return find("from Skill");
    }

    /**
     * @see com.bogie.skill.dao.SkillDao#saveSkill(com.bogie.skill.lib.vo.Skill)
     */
    public Skill saveSkill(Skill skill)
    {
        saveOrUpdate(skill);
        
        return skill;
    }
}
