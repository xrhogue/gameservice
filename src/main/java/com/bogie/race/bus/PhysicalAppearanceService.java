/**
 * PhysicalAppearanceService.java
 * 
 * Copyright 2007 SirsiDynix. All rights reserved.
 */
package com.bogie.race.bus;

import java.util.List;

import com.bogie.common.lib.model.Complexion;
import com.bogie.common.lib.model.EyeColor;
import com.bogie.common.lib.model.Gender;
import com.bogie.common.lib.model.HairColor;
import com.bogie.common.lib.model.SkinColor;

/**
 * PhysicalAppearanceService 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public interface PhysicalAppearanceService
{
    Gender getGender(Long genderId);
    Gender saveGender(Gender gender);
    void deleteGender(Long genderId);
    void deleteGender(Gender gender);
    List<Gender> findGenders();
    
    SkinColor getSkinColor(Long skinColorId);
    SkinColor saveSkinColor(SkinColor skinColor);
    void deleteSkinColor(Long skinColorId);
    void deleteSkinColor(SkinColor skinColor);
    List<SkinColor> findSkinColors();

    HairColor getHairColor(Long hairColorId);
    HairColor saveHairColor(HairColor hairColor);
    void deleteHairColor(Long hairColorId);
    void deleteHairColor(HairColor hairColor);
    List<HairColor> findHairColors();

    EyeColor getEyeColor(Long eyeColorId);
    EyeColor saveEyeColor(EyeColor eyeColor);
    void deleteEyeColor(Long eyeColorId);
    void deleteEyeColor(EyeColor eyeColor);
    List<EyeColor> findEyeColors();

    Complexion getComplexion(Long complexionId);
    Complexion saveComplexion(Complexion complexion);
    void deleteComplexion(Long complexionId);
    void deleteComplexion(Complexion complexion);
    List<Complexion> findComplexions();
}
