/**
 * SkillService.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.skill.bus;

import java.util.List;

import com.bogie.skill.lib.model.Skill;

/**
 * SkillService 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public interface SkillService
{
    Skill getSkill(Long skillId);
    Skill saveSkill(Skill skill);
    void deleteSkill(Skill skill);
    void deleteSkill(Long skillId);
    
    List<Skill> findSkills(Long parentId);
    List<Skill> findSkills(Skill parent);
    List<Skill> findSkills();
}
