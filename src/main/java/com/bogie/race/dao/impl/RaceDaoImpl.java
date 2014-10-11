/**
 * RaceDaoImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.dao.impl;

import java.util.List;

import com.bogie.common.dao.impl.GenericDaoImpl;
import com.bogie.race.dao.RaceDao;
import com.bogie.race.lib.model.Race;

public class RaceDaoImpl extends GenericDaoImpl<Long, Race> implements RaceDao
{
    /**
     * @see com.bogie.race.dao.RaceDao#getRace(java.lang.Long)
     */
    public Race getRace(Long raceId)
    {
        return get(Race.class, raceId);
    }

    /**
     * @see com.bogie.race.dao.RaceDao#deleteRace(com.bogie.race.lib.vo.Race)
     */
    public void deleteRace(Race race)
    {
        delete(race);
    }

    /**
     * @see com.bogie.race.dao.RaceDao#saveRace(com.bogie.race.lib.vo.Race)
     */
    public void saveRace(Race race)
    {
        saveOrUpdate(race);
    }

    /**
     * @see com.bogie.race.dao.RaceDao#findRaces(com.bogie.race.lib.vo.Race)
     */
    public List<Race> findRaces(Race parent)
    {
        if (parent == null)
        {
            return find("from Race as race where race.parent is null");
        }
        
        return find("from Race as race where race.parent=?", parent);
    }

    /**
     * @see com.bogie.race.dao.RaceDao#findAllRaces()
     */
    public List<Race> findAllRaces()
    {
        return find("from Race");
    }
}
