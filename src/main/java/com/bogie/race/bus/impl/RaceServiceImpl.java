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
     * @see com.bogie.race.bus.RaceService#getRace(java.lang.Long)
     */
    public Race getRace(Long raceId)
    {
        return raceDao.getRace(raceId);
    }
    /**
     * @see com.bogie.race.bus.RaceService#deleteRace(java.lang.Long)
     */
    public void deleteRace(Long raceId)
    {
        raceDao.deleteRace(getRace(raceId));
    }
    
    /**
     * @see com.bogie.race.bus.RaceService#deleteRace(com.bogie.race.lib.vo.Race)
     */
    public void deleteRace(Race race)
    {
        raceDao.deleteRace(race);
    }
    
    /**
     * @see com.bogie.race.bus.RaceService#saveRace(com.bogie.race.lib.vo.Race)
     */
    public void saveRace(Race race)
    {
        raceDao.saveRace(race);
    }
    
    /**
     * @see com.bogie.race.bus.RaceService#findRaces(com.bogie.race.lib.vo.Race)
     */
    public List<Race> findRaces(Race parent)
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
