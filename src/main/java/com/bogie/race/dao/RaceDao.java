/**
 * RaceDao.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.dao;

import java.util.List;

import com.bogie.common.dao.GenericDao;
import com.bogie.race.lib.model.Race;

/**
 * RaceDao 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public interface RaceDao extends GenericDao<Long, Race>
{
    Race getRace(Long raceId);
    void saveRace(Race race);
    void deleteRace(Race race);
    
    /**
     * Retrieves the list of races based on the parent race
     * @param parent the parent race
     * @return the list of child races
     */
    List<Race>  findRaces(Race parent);
    List<Race>  findRaces();
}
