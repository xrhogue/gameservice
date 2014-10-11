/**
 * CommonServiceImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.common.bus.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bogie.common.bus.CommonService;
import com.bogie.common.dao.GenericDao;
import com.bogie.common.lib.model.Stat;

/**
 * CommonServiceImpl 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public class CommonServiceImpl implements CommonService
{
    @Autowired
    private GenericDao<Long, Stat>  statDao;
    
    /**
     * Default constructor
     */
    public CommonServiceImpl()
    {
    }

    /**
     * @see com.bogie.common.bus.CommonService#getStat(java.lang.Long)
     */
    public Stat getStat(Long id)
    {
        return statDao.get(Stat.class, id);
    }

    /**
     * @see com.bogie.common.bus.CommonService#deleteStat(java.lang.Long)
     */
    public void deleteStat(Long id)
    {
        statDao.delete(getStat(id));
    }

    /**
     * @see com.bogie.common.bus.CommonService#deleteStat(com.bogie.common.lib.vo.Stat)
     */
    public void deleteStat(Stat stat)
    {
        statDao.delete(stat);
    }

    /**
     * @see com.bogie.common.bus.CommonService#findStats()
     */
    public List<Stat> findStats()
    {
        return statDao.find("from Stat");
    }

    /**
     * @see com.bogie.common.bus.CommonService#saveStat(com.bogie.common.lib.vo.Stat)
     */
    public Stat saveStat(Stat stat)
    {
        statDao.saveOrUpdate(stat);
        
        return stat;
    }
}
