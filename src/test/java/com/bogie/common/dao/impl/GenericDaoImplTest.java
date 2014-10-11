package com.bogie.common.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bogie.AppConfig;
import com.bogie.common.dao.GenericDao;
import com.bogie.common.lib.model.Stat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class GenericDaoImplTest
{
    @Autowired
    GenericDao<Long, Stat>   statDao;

    @Test
    public void testGet()
    {
        Stat    stat = statDao.get(Stat.class, 1L);
        
        assertNotNull(stat);
    }

    @Test
    public void testSaveOrUpdate()
    {
        Stat    stat = new Stat();
        
        stat.setCode('X');
        stat.setLongForm("XXXXXX");
        stat.setShortForm("XXX");
        stat.setMultiplier(1);
        
        statDao.saveOrUpdate(stat);
    }

    @Test
    public void testDelete()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testFindString()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testFindStringObject()
    {
        fail("Not yet implemented");
    }

}
