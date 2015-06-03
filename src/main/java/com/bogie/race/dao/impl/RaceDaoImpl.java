/**
 * RaceDaoImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.dao.impl;

import java.util.List;

import com.bogie.common.dao.impl.GenericDaoImpl;
import com.bogie.race.dao.RaceDao;
import com.bogie.race.model.Race;

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
     * @see com.bogie.race.dao.RaceDao#saveRace(com.bogie.race.model.Race)
     */
    public Race saveRace(Race race)
    {
        return saveOrUpdate(race);
    }

    /**
     * @see com.bogie.race.dao.RaceDao#deleteRace(java.lang.Long)
     */
    @Override
    public void deleteRace(Long id)
    {
        delete(getRace(id));
    }

    /**
     * @see com.bogie.race.dao.RaceDao#deleteRace(com.bogie.race.model.Race)
     */
    public void deleteRace(Race race)
    {
        delete(race);
    }

    /**
     * @see com.bogie.race.dao.RaceDao#findRaces()
     */
    public List<Race> findRaces()
    {
        return find("from Race");
    }

    /**
     * @see com.bogie.race.dao.RaceDao#findRaces(java.lang.Long)
     */
    public List<Race> findRaces(Long parentId)
    {
        if (parentId == null)
        {
            return find("from Race as race where race.parent is null");
        }
        
        return find("from Race as race where race.parent=:id", new Race(parentId));
    }

    /**
     * @see com.bogie.race.dao.RaceDao#findRaces(com.bogie.race.model.Race)
     */
    public List<Race> findRaces(Race parent)
    {
        if (parent == null)
        {
            return find("from Race as race where race.parent is null");
        }
        
        Race    race = new Race();
        
        race.setParent(parent);
        
        return find("from Race as race where race.parent=:parent", race);
    }
}
