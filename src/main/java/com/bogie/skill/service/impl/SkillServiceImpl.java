/**
 * SkillServiceImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.skill.service.impl;

import java.util.List;

import com.bogie.skill.dao.SkillDao;
import com.bogie.skill.model.Skill;
import com.bogie.skill.service.SkillService;

import org.springframework.beans.factory.annotation.Autowired;
 
/**
 * SkillServiceImpl 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class SkillServiceImpl implements SkillService
{
    @Autowired
    private SkillDao    skillDao;

    /**
     * @see com.bogie.skill.service.SkillService#getSkill(java.lang.Long)
     */
    public Skill getSkill(Long skillId)
    {
        return skillDao.getSkill(skillId);
    }
    
    /**
     * @see com.bogie.skill.service.SkillService#deleteSkill(com.bogie.skill.lib.vo.Skill)
     */
    public void deleteSkill(Skill skill)
    {
        skillDao.deleteSkill(skill);
    }

    /**
     * @see com.bogie.skill.service.SkillService#deleteSkill(java.lang.Long)
     */
    public void deleteSkill(Long skillId)
    {
        skillDao.deleteSkill(skillId);
    }

    /**
     * @see com.bogie.skill.service.SkillService#findSkills(com.bogie.skill.lib.vo.Skill)
     */
    public List<Skill> findSkills(Skill parent)
    {
        return skillDao.findSkills(parent);
    }

    /**
     * @see com.bogie.skill.service.SkillService#findSkills(java.lang.Long)
     */
    public List<Skill> findSkills(Long parentId)
    {
        return skillDao.findSkills(parentId);
    }

    /**
     * @see com.bogie.skill.service.SkillService#findAllSkills()
     */
    public List<Skill> findAllSkills()
    {
        return skillDao.findAllSkills();
    }

    /**
     * @see com.bogie.skill.service.SkillService#saveSkill(com.bogie.skill.lib.vo.Skill)
     */
    public Skill saveSkill(Skill skill)
    {
        return skillDao.saveSkill(skill);
    }
}
