/**
 * RaceServiceImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bogie.race.dao.PhysicalAppearanceDao;
import com.bogie.race.dao.RaceDao;
import com.bogie.race.model.Race;
import com.bogie.race.model.RaceComplexion;
import com.bogie.race.model.RaceEyeColor;
import com.bogie.race.model.RaceHairColor;
import com.bogie.race.model.RaceSkinColor;
import com.bogie.race.service.RaceService;

/**
 * RaceServiceImpl 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Transactional
public class RaceServiceImpl implements RaceService
{
    @Autowired
    private RaceDao                                 raceDao;
    private PhysicalAppearanceDao<RaceComplexion>   raceComplexionDao;
    private PhysicalAppearanceDao<RaceSkinColor>    raceSkinColorDao;
    private PhysicalAppearanceDao<RaceHairColor>    raceHairColorDao;
    private PhysicalAppearanceDao<RaceEyeColor>     raceEyeColorDao;

    /**
     * @see com.bogie.race.service.RaceService#getRace(java.lang.Long)
     */
    public Race getRace(Long raceId)
    {
        return raceDao.getRace(raceId);
    }
    /**
     * @see com.bogie.race.service.RaceService#deleteRace(java.lang.Long)
     */
    public void deleteRace(Long raceId)
    {
        raceDao.deleteRace(getRace(raceId));
    }
    
    /**
     * @see com.bogie.race.service.RaceService#deleteRace(com.bogie.race.model.Race)
     */
    public void deleteRace(Race race)
    {
        raceDao.deleteRace(race);
    }
    
    /**
     * @see com.bogie.race.service.RaceService#saveRace(com.bogie.race.model.Race)
     */
    public void saveRace(Race race)
    {
        raceDao.saveRace(race);
    }

    /**
     * @see com.bogie.race.service.RaceService#findRaces()
     */
    public List<Race> findRaces()
    {
        return raceDao.findRaces();
    }
    
    /**
     * @see com.bogie.race.service.RaceService#findRaces(java.lang.Long)
     */
    public List<Race> findRaces(Long parentId)
    {
        return raceDao.findRaces(parentId);
    }
    
    /**
     * @see com.bogie.race.service.RaceService#findRaces(com.bogie.race.model.Race)
     */
    public List<Race> findRaces(Race parent)
    {
        return raceDao.findRaces(parent);
    }
}
