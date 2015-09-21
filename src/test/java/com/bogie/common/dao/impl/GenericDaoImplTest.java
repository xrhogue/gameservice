package com.bogie.common.dao.impl;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestAppConfig.class)
public class GenericDaoImplTest extends BaseTest
{
    @Autowired
    GenericDao<Long, Stat>   statDao;
    
    @Before
    public void setUp() throws Exception
    {
        resetCommonDatabase();
    }
    
    @Test
    public void testGetStat()
    {
        Stat    stat = statDao.saveOrUpdate(new Stat());
        
        assertNotNull(stat);
        
        stat = statDao.get(Stat.class, stat.getId());
        
        assertNotNull(stat);
    }

    @Test
    public void testSaveOrUpdateStat()
    {
        Stat    stat = statDao.saveOrUpdate(getStat());
        Stat    savedStat = statDao.get(Stat.class, stat.getId());
        
        assertEquals(stat.getCode(), savedStat.getCode());
        assertEquals(stat.getShortForm(), savedStat.getShortForm());
        assertEquals(stat.getLongForm(), savedStat.getLongForm());
        assertEquals(stat.getMultiplier(), savedStat.getMultiplier());
    }

    @Test
    public void testDeleteStat()
    {
        Stat    stat = statDao.saveOrUpdate(new Stat());
        
        assertNotNull(stat);
        
        statDao.delete(stat);
        
        assertNull(statDao.get(Stat.class, stat.getId()));
    }

    @Test
    public void testFindStat()
    {
        statDao.saveOrUpdate(getStat());        
        List<Stat>  stats = statDao.find("from Stat");
        
        assertNotNull(stats);
        assertEquals(1, stats.size());
        assertEquals(STAT_CODE_VALUE, stats.get(0).getCode().charValue());
    }

    @Test
    public void testFindStatFilter()
    {
        Stat        stat = statDao.saveOrUpdate(getStat());        
        List<Stat>  stats = statDao.find("from Stat where code = :code", stat);
        
        assertNotNull(stats);
        assertEquals(1, stats.size());
        assertEquals(STAT_CODE_VALUE, stats.get(0).getCode().charValue());
    }
}
