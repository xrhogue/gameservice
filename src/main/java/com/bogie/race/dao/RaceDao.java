/**
 * RaceDao.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.dao;

import java.util.List;

import com.bogie.common.dao.GenericDao;
import com.bogie.race.model.Race;

/**
 * RaceDao 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public interface RaceDao extends GenericDao<Long, Race>
{
    Race getRace(Long raceId);
    Race saveRace(Race race);
    void deleteRace(Long id);
    void deleteRace(Race race);
    List<Race>  findRaces();
    List<Race>  findRaces(Race parent);
    List<Race>  findRaces(Long parentid);
}
