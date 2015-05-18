/**
 * CommonServiceImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bogie.common.dao.GenericDao;
import com.bogie.common.model.Stat;
import com.bogie.common.service.CommonService;

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
     * @see com.bogie.common.service.CommonService#getStat(java.lang.Long)
     */
    public Stat getStat(Long id)
    {
        return statDao.get(Stat.class, id);
    }

    /**
     * @see com.bogie.common.service.CommonService#deleteStat(java.lang.Long)
     */
    public void deleteStat(Long id)
    {
        statDao.delete(getStat(id));
    }

    /**
     * @see com.bogie.common.service.CommonService#deleteStat(com.bogie.common.lib.vo.Stat)
     */
    public void deleteStat(Stat stat)
    {
        statDao.delete(stat);
    }

    /**
     * @see com.bogie.common.service.CommonService#findStats()
     */
    public List<Stat> findStats()
    {
        return statDao.find("from Stat");
    }

    /**
     * @see com.bogie.common.service.CommonService#saveStat(com.bogie.common.lib.vo.Stat)
     */
    public Stat saveStat(Stat stat)
    {
        statDao.saveOrUpdate(stat);
        
        return stat;
    }
}
