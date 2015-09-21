/**
 * RaceDaoImplTest.java
 */
package com.bogie.race.dao.impl;

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
import com.bogie.race.dao.RaceDao;
import com.bogie.race.model.Race;

/**
 * RaceDaoImplTest
 *
 * @author Richard Hogue
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestAppConfig.class)
public class RaceDaoImplTest extends BaseTest
{  
    @Autowired
    private RaceDao raceDao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        resetRaceDatabase();
    }

    /**
     * Test method for {@link com.bogie.race.dao.impl.RaceDaoImpl#getRace(java.lang.Long)}.
     */
    @Test
    public void testGetRace()
    {
        Race   race = raceDao.getRace(raceDao.saveRace(getRace()).getId());
        
        assertNotNull(race);
    }

    /**
     * Test method for {@link com.bogie.race.dao.impl.RaceDaoImpl#saveRace(com.bogie.race.model.Race)}.
     */
    @Test
    public void testSaveRace()
    {
        Race   race = raceDao.saveRace(getRace());
        
        assertNotNull(race);
        assertNotNull(race.getId());
    }

    /**
     * Test method for {@link com.bogie.race.dao.impl.RaceDaoImpl#deleteRace(java.lang.Long)}.
     */
    @Test
    public void testDeleteRaceLong()
    {
        Race   race = raceDao.saveRace(getRace());
        
        raceDao.deleteRace(race.getId());
        
        assertNull(raceDao.getRace(race.getId()));
    }

    /**
     * Test method for {@link com.bogie.race.dao.impl.RaceDaoImpl#deleteRace(com.bogie.race.model.Race)}.
     */
    @Test
    public void testDeleteRaceRace()
    {
        Race   race = raceDao.saveRace(getRace());
        
        raceDao.deleteRace(race);
        
        assertNull(raceDao.getRace(race.getId()));
    }

    /**
     * Test method for {@link com.bogie.race.dao.impl.RaceDaoImpl#findRaces()}.
     */
    @Test
    public void testFindRaces()
    {
        List<Race> races = raceDao.findRaces();
        
        assertNotNull(races);
        assertEquals(0, races.size());
        
        raceDao.saveRace(getRace());
        raceDao.saveRace(getRace());
        raceDao.saveRace(getRace());
        
        races = raceDao.findRaces();
        
        assertNotNull(races);
        assertEquals(3, races.size());
    }

    /**
     * Test method for {@link com.bogie.race.dao.impl.RaceDaoImpl#findRaces(java.lang.Long)}.
     */
    @Test
    public void testFindRacesLong()
    {
        Race   parent = getRace();
        Race   race = getRace();
        
        race.setParent(parent);
        
        race = raceDao.saveRace(race);
        
        List<Race> races = raceDao.findRaces(race.getParent().getId());
        
        assertNotNull(races);
        assertEquals(1, races.size());
        assertEquals(race.getId(), races.get(0).getId());
    }

    /**
     * Test method for {@link com.bogie.race.dao.impl.RaceDaoImpl#findRaces(com.bogie.race.model.Race)}.
     */
    @Test
    public void testFindRacesRace()
    {
        Race   parent = getRace();
        Race   race = getRace();
        
        race.setParent(parent);
        
        race = raceDao.saveRace(race);
        
        List<Race> races = raceDao.findRaces(race.getParent());
        
        assertNotNull(races);
        assertEquals(1, races.size());
        assertEquals(race.getId(), races.get(0).getId());

        race = getRace();
        
        race.setParent(parent);
        
        race = raceDao.saveRace(race);
        
        races = raceDao.findRaces(race.getParent());
        
        assertNotNull(races);
        assertEquals(2, races.size());
        assertEquals(race.getId(), races.get(1).getId());
    }
}
