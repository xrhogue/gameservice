package com.bogie.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bogie.TestAppConfig;
import com.bogie.skill.model.Skill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class SkillControllerTest extends BaseControllerTest
{
    @Autowired
    private SkillController skillController;
    
    @Test
    public void testGetSkill()
    {
        skillController.updateSkill(getSkill());
        
        Skill   skill = skillController.getSkill(1L);
        
        assertNotNull(skill);
    }

    @Test
    public void testGetSkills()
    {
        skillController.updateSkill(getSkill());

        List<Skill> skills = skillController.getSkills(null);
        
        assertNotNull(skills);
        assertTrue(skills.size() > 0);
    }
}
