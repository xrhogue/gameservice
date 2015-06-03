package com.bogie.skill.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bogie.BaseTest;
import com.bogie.TestAppConfig;
import com.bogie.common.dao.GenericDao;
import com.bogie.common.model.Stat;
import com.bogie.skill.dao.SkillDao;
import com.bogie.skill.model.Skill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class SkillDaoImplTest extends BaseTest
{
    @Autowired
    private GenericDao<Long, Stat>    statDao;
    
    @Autowired
    private SkillDao    skillDao;
   
    @Before
    public void setUp() throws Exception
    {
        resetSkillDatabase();
    }

    @Test
    public void testGetSkill()
    {     
        Skill   skill = skillDao.getSkill(skillDao.saveSkill(getSkill()).getId());
        
        assertNotNull(skill);
    }

    @Test
    public void testSaveSkill()
    {
        Skill   skill = skillDao.saveSkill(getSkill());
        
        assertNotNull(skill);
        assertNotNull(skill.getId());
    }

    @Test
    public void testDeleteSkillSkill()
    {
        Skill   skill = skillDao.saveSkill(getSkill());
        
        skillDao.deleteSkill(skill);
        
        assertNull(skillDao.getSkill(skill.getId()));
    }

    @Test
    public void testDeleteSkillLong()
    {
        Skill   skill = skillDao.saveSkill(getSkill());
        
        skillDao.deleteSkill(skill.getId());
        
        assertNull(skillDao.getSkill(skill.getId()));
    }

    @Test
    public void testFindSkills()
    {
        List<Skill> skills = skillDao.findSkills();
        
        assertNotNull(skills);
        assertEquals(0, skills.size());
        
        skillDao.saveSkill(getSkill());
        skillDao.saveSkill(getSkill());
        skillDao.saveSkill(getSkill());
        
        skills = skillDao.findSkills();
        
        assertNotNull(skills);
        assertEquals(3, skills.size());
    }

    @Test
    public void testFindSkillsSkill()
    {
        Skill   parent = getSkill();
        Skill   skill = getSkill();
        
        skill.setParent(parent);
        
        skill = skillDao.saveSkill(skill);
        
        List<Skill> skills = skillDao.findSkills(skill.getParent());
        
        assertNotNull(skills);
        assertEquals(1, skills.size());
        assertEquals(skill.getId(), skills.get(0).getId());
    }

    @Test
    public void testFindSkillsLong()
    {
        Skill   parent = getSkill();
        Skill   skill = getSkill();
        
        skill.setParent(parent);
        
        skill = skillDao.saveSkill(skill);
        
        List<Skill> skills = skillDao.findSkills(skill.getParent().getId());
        
        assertNotNull(skills);
        assertEquals(1, skills.size());
        assertEquals(skill.getId(), skills.get(0).getId());
    }
}
