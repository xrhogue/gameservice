package com.bogie.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bogie.skill.model.Skill;

public class SkillControllerTest extends BaseControllerTest
{
    @Autowired
    private SkillController skillController;
    
    @Test
    public void testGetSkill()
    {
        Skill   skill = skillController.getSkill(3L);
        
        assertNotNull(skill);
    }

    @Test
    public void testGetSkills()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testGetRaces()
    {
        fail("Not yet implemented");
    }
}
