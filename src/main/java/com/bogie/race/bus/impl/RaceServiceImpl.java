/**
 * RaceServiceImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.bus.impl;

import java.util.List;

import com.bogie.race.bus.RaceService;
import com.bogie.race.dao.PhysicalAppearanceDao;
import com.bogie.race.dao.RaceDao;
import com.bogie.race.lib.model.Race;
import com.bogie.race.lib.model.RaceComplexion;
import com.bogie.race.lib.model.RaceEyeColor;
import com.bogie.race.lib.model.RaceHairColor;
import com.bogie.race.lib.model.RaceSkinColor;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * RaceServiceImpl 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@org.springframework.transaction.annotation.Transactional
public class RaceServiceImpl implements RaceService
{
    @Autowired 
    private RaceDao                                 raceDao;
    
    @Autowired 
    private PhysicalAppearanceDao<RaceSkinColor>    raceSkinColorDao;
    
    @Autowired 
    private PhysicalAppearanceDao<RaceHairColor>    raceHairColorDao;
    
    @Autowired 
    private PhysicalAppearanceDao<RaceEyeColor>     raceEyeColorDao;

    @Autowired 
    private PhysicalAppearanceDao<RaceComplexion>   raceComplexionDao;

    /**
     * @see com.bogie.race.bus.RaceService#getRace(java.lang.Long)
     */
    public Race getRace(final Long raceId)
    {
        return raceDao.getRace(raceId);
    }
    /**
     * @see com.bogie.race.bus.RaceService#deleteRace(java.lang.Long)
     */
    public void deleteRace(final Long raceId)
    {
        raceDao.deleteRace(getRace(raceId));
    }
    
    /**
     * @see com.bogie.race.bus.RaceService#deleteRace(com.bogie.race.lib.vo.Race)
     */
    public void deleteRace(final Race race)
    {
        raceDao.deleteRace(race);
    }
    
    /**
     * @see com.bogie.race.bus.RaceService#saveRace(com.bogie.race.lib.vo.Race)
     */
    public void saveRace(final Race race)
    {
        raceDao.saveRace(race);
    }
    
    /**
     * @see com.bogie.race.bus.RaceService#findRaces(com.bogie.race.lib.vo.Race)
     */
    public List<Race> findRaces(final Race parent)
    {
        return raceDao.findRaces(parent);
    }

    /**
     * @see com.bogie.race.bus.RaceService#findRaces()
     */
    public List<Race> findRaces()
    {
        return raceDao.findRaces();
    }
}
