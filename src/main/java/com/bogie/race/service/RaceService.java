/**
 * RaceService.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.service;

import java.util.List;

import com.bogie.race.model.Race;

/**
 * RaceService 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public interface RaceService
{
    Race getRace(Long raceId);
    void saveRace(Race race);
    void deleteRace(Long raceId);
    void deleteRace(Race race);
    List<Race> findRaces(Race parent);
    List<Race> findAllRaces();
}
