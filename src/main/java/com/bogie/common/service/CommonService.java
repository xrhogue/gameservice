/**
 * CommonService.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.common.service;

import java.util.List;

import com.bogie.common.model.Stat;

/**
 * CommonService 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public interface CommonService
{
    Stat getStat(Long id);
    Stat saveStat(Stat stat);
    void deleteStat(Long id);
    void deleteStat(Stat stat);
    List<Stat> findStats();
}
