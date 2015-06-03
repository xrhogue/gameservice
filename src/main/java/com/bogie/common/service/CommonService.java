/**
 * CommonService.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.common.service;

import java.util.List;

import com.bogie.common.model.Complexion;
import com.bogie.common.model.EyeColor;
import com.bogie.common.model.Gender;
import com.bogie.common.model.HairColor;
import com.bogie.common.model.SkinColor;
import com.bogie.common.model.Stat;

/**
 * CommonService 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public interface CommonService
{
    Stat        getStat(Long id);
    Stat        saveStat(Stat stat);
    void        deleteStat(Long id);
    void        deleteStat(Stat stat);
    List<Stat>  findStats();

    Gender          getGender(Long id);
    Gender          saveGender(Gender gender);
    void            deleteGender(Long id);
    void            deleteGender(Gender gender);
    List<Gender>    findGenders();

    Complexion          getComplexion(Long id);
    Complexion          saveComplexion(Complexion complexion);
    void                deleteComplexion(Long id);
    void                deleteComplexion(Complexion complexion);
    List<Complexion>    findComplexions();

    EyeColor        getEyeColor(Long id);
    EyeColor        saveEyeColor(EyeColor eyeColor);
    void            deleteEyeColor(Long id);
    void            deleteEyeColor(EyeColor eyeColor);
    List<EyeColor>  findEyeColors();

    HairColor       getHairColor(Long id);
    HairColor       saveHairColor(HairColor hairColor);
    void            deleteHairColor(Long id);
    void            deleteHairColor(HairColor hairColor);
    List<HairColor> findHairColors();

    SkinColor       getSkinColor(Long id);
    SkinColor       saveSkinColor(SkinColor skinColor);
    void            deleteSkinColor(Long id);
    void            deleteSkinColor(SkinColor skinColor);
    List<SkinColor> findSkinColors();
}
