package com.bogie.skill.dao.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bogie.AppConfig;
import com.bogie.common.dao.GenericDao;
import com.bogie.common.lib.model.Stat;
import com.bogie.skill.dao.SkillDao;
import com.bogie.skill.lib.model.Skill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class SkillDaoImplTest
{
    @Autowired
    private GenericDao<Long, Stat>    statDao;
    
    @Autowired
    private SkillDao    skillDao;
   
    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void testGetSkill()
    {
        Skill   skill = skillDao.getSkill(2L);
        
        assertNotNull(skill);
    }

    @Test
    public void testDeleteSkillSkill()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testDeleteSkillLong()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testFindSkillsSkill()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testFindSkillsLong()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testFindAllSkills()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testSaveSkill()
    {
        Stat    stat = statDao.get(Stat.class, 1L);
        Skill   skill = new Skill();
        
        skill.setName("skill");
        skill.setPrimaryStat(stat);
        
        skill = skillDao.saveSkill(skill);
        
        assertNotNull(skill);
    }
}
