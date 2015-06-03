/**
 * CommonServiceImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bogie.common.dao.GenericDao;
import com.bogie.common.model.Complexion;
import com.bogie.common.model.EyeColor;
import com.bogie.common.model.Gender;
import com.bogie.common.model.HairColor;
import com.bogie.common.model.SkinColor;
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
    
    @Autowired
    private GenericDao<Long, Gender> genderDao;
    
    @Autowired
    private GenericDao<Long, Complexion>    complexionDao;
    
    @Autowired
    private GenericDao<Long, EyeColor>  eyeColorDao;
    
    @Autowired
    private GenericDao<Long, HairColor> hairColorDao;
    
    @Autowired
    private GenericDao<Long, SkinColor> skinColorDao;

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
     * @see com.bogie.common.service.CommonService#saveStat(com.bogie.common.lib.vo.Stat)
     */
    public Stat saveStat(Stat stat)
    {
        return statDao.saveOrUpdate(stat);
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
     * @see com.bogie.common.service.CommonService#getGender(java.lang.Long)
     */
    public Gender getGender(Long id)
    {
        return genderDao.get(Gender.class, id);
    }

    /**
     * @see com.bogie.common.service.CommonService#saveGender(com.bogie.common.lib.vo.Gender)
     */
    public Gender saveGender(Gender gender)
    {
        return genderDao.saveOrUpdate(gender);
    }

    /**
     * @see com.bogie.common.service.CommonService#deleteGender(java.lang.Long)
     */
    public void deleteGender(Long id)
    {
        genderDao.delete(getGender(id));
    }

    /**
     * @see com.bogie.common.service.CommonService#deleteGender(com.bogie.common.lib.vo.Gender)
     */
    public void deleteGender(Gender gender)
    {
        genderDao.delete(gender);
    }

    /**
     * @see com.bogie.common.service.CommonService#findGenders()
     */
    public List<Gender> findGenders()
    {
        return genderDao.find("from Gender");
    }

    /**
     * @see com.bogie.common.service.CommonService#getComplexion(java.lang.Long)
     */
    @Override
    public Complexion getComplexion(Long id)
    {
        return complexionDao.get(Complexion.class, id);
    }

    /**
     * @see com.bogie.common.service.CommonService#saveComplexion(com.bogie.common.model.Complexion)
     */
    @Override
    public Complexion saveComplexion(Complexion complexion)
    {
        return complexionDao.saveOrUpdate(complexion);
    }

    /**
     * @see com.bogie.common.service.CommonService#deleteComplexion(java.lang.Long)
     */
    @Override
    public void deleteComplexion(Long id)
    {
        complexionDao.delete(getComplexion(id));     
    }

    /**
     * @see com.bogie.common.service.CommonService#deleteComplexion(com.bogie.common.model.Complexion)
     */
    @Override
    public void deleteComplexion(Complexion complexion)
    {
        complexionDao.delete(complexion);
    }

    /**
     * @see com.bogie.common.service.CommonService#findComplexions()
     */
    @Override
    public List<Complexion> findComplexions()
    {
        return complexionDao.find("from Complexion");
    }    

    /**
     * @see com.bogie.common.service.CommonService#getEyeColor(java.lang.Long)
     */
    @Override
    public EyeColor getEyeColor(Long id)
    {
        return eyeColorDao.get(EyeColor.class, id);
    }

    /**
     * @see com.bogie.common.service.CommonService#saveEyeColor(com.bogie.common.model.EyeColor)
     */
    @Override
    public EyeColor saveEyeColor(EyeColor eyeColor)
    {
        return eyeColorDao.saveOrUpdate(eyeColor);
    }

    /**
     * @see com.bogie.common.service.CommonService#deleteEyeColor(java.lang.Long)
     */
    @Override
    public void deleteEyeColor(Long id)
    {
        eyeColorDao.delete(getEyeColor(id));        
    }

    /**
     * @see com.bogie.common.service.CommonService#deleteEyeColor(com.bogie.common.model.EyeColor)
     */
    @Override
    public void deleteEyeColor(EyeColor eyeColor)
    {
        eyeColorDao.delete(eyeColor);
    }

    /**
     * @see com.bogie.common.service.CommonService#findEyeColors()
     */
    @Override
    public List<EyeColor> findEyeColors()
    {
        return eyeColorDao.find("from EyeColor");
    }    

    /**
     * @see com.bogie.common.service.CommonService#getHairColor(java.lang.Long)
     */
    @Override
    public HairColor getHairColor(Long id)
    {
        return hairColorDao.get(HairColor.class, id);
    }

    /**
     * @see com.bogie.common.service.CommonService#saveHairColor(com.bogie.common.model.HairColor)
     */
    @Override
    public HairColor saveHairColor(HairColor hairColor)
    {
        return hairColorDao.saveOrUpdate(hairColor);
    }

    /**
     * @see com.bogie.common.service.CommonService#deleteHairColor(java.lang.Long)
     */
    @Override
    public void deleteHairColor(Long id)
    {
        hairColorDao.delete(getHairColor(id));        
    }

    /**
     * @see com.bogie.common.service.CommonService#deleteHairColor(com.bogie.common.model.HairColor)
     */
    @Override
    public void deleteHairColor(HairColor hairColor)
    {
        hairColorDao.delete(hairColor);
    }

    /**
     * @see com.bogie.common.service.CommonService#findHairColors()
     */
    @Override
    public List<HairColor> findHairColors()
    {
        return hairColorDao.find("from HairColor");
    }    

    /**
     * @see com.bogie.common.service.CommonService#getSkinColor(java.lang.Long)
     */
    @Override
    public SkinColor getSkinColor(Long id)
    {
        return skinColorDao.get(SkinColor.class, id);
    }

    /**
     * @see com.bogie.common.service.CommonService#saveSkinColor(com.bogie.common.model.SkinColor)
     */
    @Override
    public SkinColor saveSkinColor(SkinColor skinColor)
    {
        return skinColorDao.saveOrUpdate(skinColor);
    }

    /**
     * @see com.bogie.common.service.CommonService#deleteSkinColor(java.lang.Long)
     */
    @Override
    public void deleteSkinColor(Long id)
    {
        skinColorDao.delete(getSkinColor(id));        
    }

    /**
     * @see com.bogie.common.service.CommonService#deleteSkinColor(com.bogie.common.model.SkinColor)
     */
    @Override
    public void deleteSkinColor(SkinColor skinColor)
    {
        skinColorDao.delete(skinColor);
    }

    /**
     * @see com.bogie.common.service.CommonService#findSkinColors()
     */
    @Override
    public List<SkinColor> findSkinColors()
    {
        return skinColorDao.find("from SkinColor");
    }    
}
