/**
 * GenderDao.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.dao;

import java.util.List;

import com.bogie.common.dao.GenericDao;
import com.bogie.common.model.PhysicalAttribute;

/**
 * PhysicalAppearanceDao 
 * 
 * @author Richard Hogue
 * @version 1.0
 * @param <T> The PhysicalAttribute subclass
 */
public interface PhysicalAppearanceDao<T extends PhysicalAttribute> extends GenericDao<Long, T>
{
    T getPhysicalAppearance(Class<T> type, Long id);
    T savePhysicalAppearance(T physicalAppearance);
    void deletePhysicalAppearance(Long physicalAppearanceId);
    void deletePhysicalAppearance(T physicalAppearance);
    List<T> findPhysicalAppearances(String className);
}
