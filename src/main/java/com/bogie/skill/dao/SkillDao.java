/**
 * SkillDao.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.skill.dao;

import java.util.List;

import com.bogie.common.dao.GenericDao;
import com.bogie.skill.lib.model.Skill;

/**
 * SkillDao 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public interface SkillDao extends GenericDao<Long, Skill>
{
    Skill getSkill(Long skillId);
    Skill saveSkill(Skill skill);
    void deleteSkill(Skill skill);
    void deleteSkill(Long skillId);
    
    List<Skill> findSkills(Long parentId);
    List<Skill> findSkills(Skill parent);
    List<Skill> findAllSkills();
}
