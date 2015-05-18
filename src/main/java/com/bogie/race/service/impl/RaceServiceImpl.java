/**
 * RaceServiceImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.service.impl;

import java.util.List;

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
@org.springframework.transaction.annotation.Transactional
public class RaceServiceImpl implements RaceService
{
    private RaceDao                                 raceDao;
    private PhysicalAppearanceDao<RaceSkinColor>    raceSkinColorDao;
    private PhysicalAppearanceDao<RaceHairColor>    raceHairColorDao;
    private PhysicalAppearanceDao<RaceEyeColor>     raceEyeColorDao;
    private PhysicalAppearanceDao<RaceComplexion>   raceComplexionDao;
    
    public void setRaceDao(RaceDao x) { raceDao = x; }
    public void setRaceSkinColorDao(PhysicalAppearanceDao<RaceSkinColor> x) { raceSkinColorDao = x; }
    public void setRaceHairColorDao(PhysicalAppearanceDao<RaceHairColor> x) { raceHairColorDao = x; }
    public void setRaceEyeColorDao(PhysicalAppearanceDao<RaceEyeColor> x) { raceEyeColorDao = x; }
    public void setRaceComplexionDao(PhysicalAppearanceDao<RaceComplexion> x) { raceComplexionDao = x; }

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
     * @see com.bogie.race.service.RaceService#deleteRace(com.bogie.race.lib.vo.Race)
     */
    public void deleteRace(Race race)
    {
        raceDao.deleteRace(race);
    }
    
    /**
     * @see com.bogie.race.service.RaceService#saveRace(com.bogie.race.lib.vo.Race)
     */
    public void saveRace(Race race)
    {
        raceDao.saveRace(race);
    }
    
    /**
     * @see com.bogie.race.service.RaceService#findRaces(com.bogie.race.lib.vo.Race)
     */
    public List<Race> findRaces(Race parent)
    {
        return raceDao.findRaces(parent);
    }

    /**
     * @see com.bogie.race.service.RaceService#findAllRaces()
     */
    public List<Race> findAllRaces()
    {
        return raceDao.findAllRaces();
    }
}
