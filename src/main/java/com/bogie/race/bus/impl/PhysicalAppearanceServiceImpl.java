/**
 * PhysicalAppearanceServiceImpl.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.bus.impl;

import java.util.List;

import com.bogie.common.lib.model.Complexion;
import com.bogie.common.lib.model.EyeColor;
import com.bogie.common.lib.model.Gender;
import com.bogie.common.lib.model.HairColor;
import com.bogie.common.lib.model.SkinColor;
import com.bogie.race.bus.PhysicalAppearanceService;
import com.bogie.race.dao.PhysicalAppearanceDao;

/**
 * PhysicalAppearanceServiceImpl 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@org.springframework.transaction.annotation.Transactional
public class PhysicalAppearanceServiceImpl implements PhysicalAppearanceService
{
    private PhysicalAppearanceDao<Gender>       genderDao;
    private PhysicalAppearanceDao<SkinColor>    skinColorDao;
    private PhysicalAppearanceDao<HairColor>    hairColorDao;
    private PhysicalAppearanceDao<EyeColor>     eyeColorDao;
    private PhysicalAppearanceDao<Complexion>   complexionDao;
    
    public void setGenderDao(PhysicalAppearanceDao<Gender> x) { genderDao = x; }
    public void setSkinColorDao(PhysicalAppearanceDao<SkinColor> x) { skinColorDao = x; }
    public void setHairColorDao(PhysicalAppearanceDao<HairColor> x) { hairColorDao = x; }
    public void setEyeColorDao(PhysicalAppearanceDao<EyeColor> x) { eyeColorDao = x; }
    public void setComplexionDao(PhysicalAppearanceDao<Complexion> x) { complexionDao = x; }
    
    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#getComplexion(java.lang.Long)
     */
    public Complexion getComplexion(Long complexionId)
    {
        return complexionDao.getPhysicalAppearance(Complexion.class, complexionId);
    }
    
    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#getEyeColor(java.lang.Long)
     */
    public EyeColor getEyeColor(Long eyeColorId)
    {
        return eyeColorDao.getPhysicalAppearance(EyeColor.class, eyeColorId);
    }
    
    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#getGender(java.lang.Long)
     */
    public Gender getGender(Long genderId)
    {
        return genderDao.getPhysicalAppearance(Gender.class, genderId);
    }
    
    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#getHairColor(java.lang.Long)
     */
    public HairColor getHairColor(Long hairColorId)
    {
        return hairColorDao.getPhysicalAppearance(HairColor.class, hairColorId);
    }
    
    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#getSkinColor(java.lang.Long)
     */
    public SkinColor getSkinColor(Long skinColorId)
    {
        return skinColorDao.getPhysicalAppearance(SkinColor.class, skinColorId);
    }
    
    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#deleteGender(java.lang.Long)
     */
    public void deleteGender(Long genderId)
    {
        deleteGender(getGender(genderId));
    }

    /**
     * @see com.bogie.race.bus.RaceService#deleteGender(com.bogie.common.lib.vo.Gender)
     */
    public void deleteGender(Gender gender)
    {
        genderDao.deletePhysicalAppearance(gender);
    }
    
    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#deleteSkinColor(java.lang.Long)
     */
    public void deleteSkinColor(Long skinColorId)
    {
        deleteSkinColor(getSkinColor(skinColorId));
    }

    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#deleteSkinColor(com.bogie.common.lib.vo.SkinColor)
     */
    public void deleteSkinColor(SkinColor skinColor)
    {
        skinColorDao.deletePhysicalAppearance(skinColor);
    }
    
    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#deleteComplexion(java.lang.Long)
     */
    public void deleteComplexion(Long complexionId)
    {
        deleteComplexion(getComplexion(complexionId));
    }

    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#deleteComplexion(com.bogie.common.lib.vo.Complexion)
     */
    public void deleteComplexion(Complexion complexion)
    {
        complexionDao.deletePhysicalAppearance(complexion);
    }
    
    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#deleteEyeColor(java.lang.Long)
     */
    public void deleteEyeColor(Long eyeColorId)
    {
        deleteEyeColor(getEyeColor(eyeColorId));
    }

    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#deleteEyeColor(com.bogie.common.lib.vo.EyeColor)
     */
    public void deleteEyeColor(EyeColor eyeColor)
    {
        eyeColorDao.deletePhysicalAppearance(eyeColor);
    }

    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#deleteHairColor(java.lang.Long)
     */
    public void deleteHairColor(Long hairColorId)
    {
        deleteHairColor(getHairColor(hairColorId));
    }

    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#deleteHairColor(com.bogie.common.lib.vo.HairColor)
     */
    public void deleteHairColor(HairColor hairColor)
    {
        hairColorDao.deletePhysicalAppearance(hairColor);
    }

    /**
     * @see com.bogie.race.bus.RaceService#findGenders()
     */
    public List<Gender> findGenders()
    {
        return genderDao.findPhysicalAppearances(Gender.class.getSimpleName());
    }

    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#findSkinColors()
     */
    public List<SkinColor> findSkinColors()
    {
        return skinColorDao.findPhysicalAppearances(SkinColor.class.getSimpleName());
    }

    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#findComplexions()
     */
    public List<Complexion> findComplexions()
    {
        return complexionDao.findPhysicalAppearances(Complexion.class.getSimpleName());
    }
    
    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#findEyeColors()
     */
    public List<EyeColor> findEyeColors()
    {
        return eyeColorDao.findPhysicalAppearances(EyeColor.class.getSimpleName());
    }
    
    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#findHairColors()
     */
    public List<HairColor> findHairColors()
    {
        return hairColorDao.findPhysicalAppearances(HairColor.class.getSimpleName());
    }
    
    /**
     * @see com.bogie.race.bus.RaceService#saveGender(com.bogie.common.lib.vo.Gender)
     */
    public Gender saveGender(Gender gender)
    {
        return genderDao.savePhysicalAppearance(gender);
    }

    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#saveSkinColor(com.bogie.common.lib.vo.SkinColor)
     */
    public SkinColor saveSkinColor(SkinColor skinColor)
    {
        return skinColorDao.savePhysicalAppearance(skinColor);
    }

    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#saveComplexion(com.bogie.common.lib.vo.Complexion)
     */
    public Complexion saveComplexion(Complexion complexion)
    {
        return complexionDao.savePhysicalAppearance(complexion);
    }
    
    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#saveEyeColor(com.bogie.common.lib.vo.EyeColor)
     */
    public EyeColor saveEyeColor(EyeColor eyeColor)
    {
        return eyeColorDao.savePhysicalAppearance(eyeColor);
    }
    
    /**
     * @see com.bogie.race.bus.PhysicalAppearanceService#saveHairColor(com.bogie.common.lib.vo.HairColor)
     */
    public HairColor saveHairColor(HairColor hairColor)
    {
        return hairColorDao.savePhysicalAppearance(hairColor);
    }
}
