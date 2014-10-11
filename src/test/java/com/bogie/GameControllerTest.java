package com.bogie;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bogie.skill.bus.SkillService;
import com.bogie.skill.lib.model.Skill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class GameControllerTest
{
    @Autowired
    private SkillService    skillService;
    private GameController  gameController;
    
    @Before
    public void setUp() throws Exception
    {
        gameController = new GameController();
    }

    @Test
    public void testGetSkill()
    {
        Skill   skill = gameController.getSkill(3L);
        
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
