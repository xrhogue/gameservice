package com.bogie.common.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bogie.TestAppConfig;
import com.bogie.common.dao.GenericDao;
import com.bogie.common.model.Stat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestAppConfig.class)
public class GenericDaoImplTest
{
    @Autowired
    GenericDao<Long, Stat>   statDao;

    @Test
    public void testGet()
    {
        Stat    stat = statDao.saveOrUpdate(new Stat());
        
        assertNotNull(stat);
        
        stat = statDao.get(Stat.class, stat.getId());
        
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
        
        stat = statDao.saveOrUpdate(stat);
        
        Stat    savedStat = statDao.get(Stat.class, stat.getId());
        
        assertEquals(stat.getCode(), savedStat.getCode());
        assertEquals(stat.getShortForm(), savedStat.getShortForm());
        assertEquals(stat.getLongForm(), savedStat.getLongForm());
        assertEquals(stat.getMultiplier(), savedStat.getMultiplier());
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
