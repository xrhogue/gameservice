/**
 * PhysicalAppearanceDaoImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.dao.impl;

import java.util.List;

import com.bogie.common.dao.impl.GenericDaoImpl;
import com.bogie.common.model.PhysicalAttribute;
import com.bogie.race.dao.PhysicalAppearanceDao;

public class PhysicalAppearanceDaoImpl<T extends PhysicalAttribute> extends GenericDaoImpl<Long, T> implements PhysicalAppearanceDao<T>
{
    
    /**
     * @see com.bogie.race.dao.PhysicalAppearanceDao#deletePhysicalAppearance(java.lang.Long)
     */
    public void deletePhysicalAppearance(Long physicalAppearanceId)
    {
    }

    /**
     * @see com.bogie.race.dao.PhysicalAppearanceDao#deletePhysicalAppearance(java.lang.Object)
     */
    public void deletePhysicalAppearance(T physicalAppearance)
    {
        delete(physicalAppearance);
    }

    /**
     * @see com.bogie.race.dao.PhysicalAppearanceDao#getPhysicalAppearance(java.lang.Long)
     */
    public T getPhysicalAppearance(Class<T> type, Long id)
    {
        return get(type, id);
    }

    /**
     * @see com.bogie.race.dao.PhysicalAppearanceDao#findPhysicalAppearances(java.lang.String)
     */
    public List<T> findPhysicalAppearances(String className)
    {
        return find("from " + className);
    }

    /**
     * @see com.bogie.race.dao.PhysicalAppearanceDao#savePhysicalAppearance(java.lang.Object)
     */
    public T savePhysicalAppearance(T physicalAppearance)
    {
        saveOrUpdate(physicalAppearance);
        
        return physicalAppearance;
    }
}
