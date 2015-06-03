/**
 * CommonServiceImplTest.java
 */
package com.bogie.common.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bogie.BaseTest;
import com.bogie.TestAppConfig;
import com.bogie.common.model.Complexion;
import com.bogie.common.model.EyeColor;
import com.bogie.common.model.Gender;
import com.bogie.common.model.HairColor;
import com.bogie.common.model.SkinColor;
import com.bogie.common.model.Stat;
import com.bogie.common.service.CommonService;

/**
 * CommonServiceImplTest
 *
 * @author Richard Hogue
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestAppConfig.class)
public class CommonServiceImplTest extends BaseTest
{
    @Autowired
    private CommonService   commonService;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        resetCommonDatabase();
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#CommonServiceImpl()}.
     */
    @Test
    public void testCommonServiceImpl()
    {
        assertNotNull(commonService);
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#getStat(java.lang.Long)}.
     */
    @Test
    public void testGetStat()
    {
        Stat    stat = commonService.getStat(commonService.saveStat(getStat()).getId());
        
        assertNotNull(stat);
        assertEquals(STAT_CODE_VALUE, stat.getCode());
        assertEquals(STAT_LONG_FORM_VALUE, stat.getLongForm());
        assertEquals(STAT_SHORT_FORM_VALUE, stat.getShortForm());
        assertEquals(STAT_MULTIPLIER_VALUE.intValue(), stat.getMultiplier().intValue());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#saveStat(com.bogie.common.model.Stat)}.
     */
    @Test
    public void testSaveStat()
    {
        Stat    stat = commonService.saveStat(getStat());
        
        assertNotNull(stat);
        assertNotNull(stat.getId());
        assertEquals(STAT_CODE_VALUE, stat.getCode());
        assertEquals(STAT_LONG_FORM_VALUE, stat.getLongForm());
        assertEquals(STAT_SHORT_FORM_VALUE, stat.getShortForm());
        assertEquals(STAT_MULTIPLIER_VALUE.intValue(), stat.getMultiplier().intValue());        
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#deleteStat(java.lang.Long)}.
     */
    @Test
    public void testDeleteStatLong()
    {
        Stat    stat = commonService.getStat(commonService.saveStat(getStat()).getId());
        
        commonService.deleteStat(stat.getId());
        
        assertNull(commonService.getStat(stat.getId()));
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#deleteStat(com.bogie.common.model.Stat)}.
     */
    @Test
    public void testDeleteStatStat()
    {
        Stat    stat = commonService.getStat(commonService.saveStat(getStat()).getId());
        
        commonService.deleteStat(stat);
        
        assertNull(commonService.getStat(stat.getId()));
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#findStats()}.
     */
    @Test
    public void testFindStats()
    {     
        List<Stat>  stats = commonService.findStats();
        
        assertNotNull(stats);
        assertEquals(0, stats.size());

        commonService.saveStat(getStat());
        commonService.saveStat(getStat());
        commonService.saveStat(getStat());
        
        stats = commonService.findStats();
        
        assertNotNull(stats);
        assertEquals(3, stats.size());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#getGender(java.lang.Long)}.
     */
    @Test
    public void testGetGender()
    {
        Gender  gender = commonService.getGender(commonService.saveGender(getGender()).getId());
        
        assertNotNull(gender);
        assertEquals(GENDER_NAME_VALUE, gender.getName());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#saveGender(com.bogie.common.model.Gender)}.
     */
    @Test
    public void testSaveGender()
    {
        Gender  gender = commonService.saveGender(getGender());
        
        assertNotNull(gender);
        assertNotNull(gender.getId());
        assertEquals(GENDER_NAME_VALUE, gender.getName());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#deleteGender(java.lang.Long)}.
     */
    @Test
    public void testDeleteGenderLong()
    {
        Gender    stat = commonService.getGender(commonService.saveGender(getGender()).getId());
        
        commonService.deleteGender(stat.getId());
        
        assertNull(commonService.getGender(stat.getId()));
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#deleteGender(com.bogie.common.model.Gender)}.
     */
    @Test
    public void testDeleteGenderGender()
    {
        Gender    stat = commonService.getGender(commonService.saveGender(getGender()).getId());
        
        commonService.deleteGender(stat);
        
        assertNull(commonService.getGender(stat.getId()));
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#findGenders()}.
     */
    @Test
    public void testFindGenders()
    {     
        List<Gender>  stats = commonService.findGenders();
        
        assertNotNull(stats);
        assertEquals(0, stats.size());

        commonService.saveGender(getGender());
        commonService.saveGender(getGender());
        commonService.saveGender(getGender());
        
        stats = commonService.findGenders();
        
        assertNotNull(stats);
        assertEquals(3, stats.size());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#getComplexion(java.lang.Long)}.
     */
    @Test
    public void testGetComplexion()
    {
        Complexion  complexion = commonService.getComplexion(commonService.saveComplexion(getComplexion()).getId());
        
        assertNotNull(complexion);
        assertEquals(COMPLEXION_NAME_VALUE, complexion.getName());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#saveComplexion(com.bogie.common.model.Complexion)}.
     */
    @Test
    public void testSaveComplexion()
    {
        Complexion  complexion = commonService.saveComplexion(getComplexion());
        
        assertNotNull(complexion);
        assertNotNull(complexion.getId());
        assertEquals(COMPLEXION_NAME_VALUE, complexion.getName());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#deleteComplexion(java.lang.Long)}.
     */
    @Test
    public void testDeleteComplexionLong()
    {
        Complexion  complexion = commonService.getComplexion(commonService.saveComplexion(getComplexion()).getId());
        
        commonService.deleteComplexion(complexion.getId());
        
        assertNull(commonService.getComplexion(complexion.getId()));
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#deleteComplexion(com.bogie.common.model.Complexion)}.
     */
    @Test
    public void testDeleteComplexionComplexion()
    {
        Complexion  complexion = commonService.getComplexion(commonService.saveComplexion(getComplexion()).getId());
        
        commonService.deleteComplexion(complexion);
        
        assertNull(commonService.getComplexion(complexion.getId()));
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#findComplexions()}.
     */
    @Test
    public void testFindComplexions()
    {
        List<Complexion>  complexions = commonService.findComplexions();
        
        assertNotNull(complexions);
        assertEquals(0, complexions.size());

        commonService.saveComplexion(getComplexion());
        commonService.saveComplexion(getComplexion());
        commonService.saveComplexion(getComplexion());
        
        complexions = commonService.findComplexions();
        
        assertNotNull(complexions);
        assertEquals(3, complexions.size());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#getEyeColor(java.lang.Long)}.
     */
    @Test
    public void testGetEyeColor()
    {
        EyeColor  eyeColor = commonService.getEyeColor(commonService.saveEyeColor(getEyeColor()).getId());
        
        assertNotNull(eyeColor);
        assertEquals(EYE_COLOR_NAME_VALUE, eyeColor.getName());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#saveEyeColor(com.bogie.common.model.EyeColor)}.
     */
    @Test
    public void testSaveEyeColor()
    {
        EyeColor  eyeColor = commonService.saveEyeColor(getEyeColor());
        
        assertNotNull(eyeColor);
        assertNotNull(eyeColor.getId());
        assertEquals(EYE_COLOR_NAME_VALUE, eyeColor.getName());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#deleteEyeColor(java.lang.Long)}.
     */
    @Test
    public void testDeleteEyeColorLong()
    {
        EyeColor  eyeColor = commonService.getEyeColor(commonService.saveEyeColor(getEyeColor()).getId());
        
        commonService.deleteEyeColor(eyeColor.getId());
        
        assertNull(commonService.getEyeColor(eyeColor.getId()));
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#deleteEyeColor(com.bogie.common.model.EyeColor)}.
     */
    @Test
    public void testDeleteEyeColorEyeColor()
    {
        EyeColor  eyeColor = commonService.getEyeColor(commonService.saveEyeColor(getEyeColor()).getId());
        
        commonService.deleteEyeColor(eyeColor);
        
        assertNull(commonService.getEyeColor(eyeColor.getId()));
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#findEyeColors()}.
     */
    @Test
    public void testFindEyeColors()
    {
        List<EyeColor>  eyeColors = commonService.findEyeColors();
        
        assertNotNull(eyeColors);
        assertEquals(0, eyeColors.size());

        commonService.saveEyeColor(getEyeColor());
        commonService.saveEyeColor(getEyeColor());
        commonService.saveEyeColor(getEyeColor());
        
        eyeColors = commonService.findEyeColors();
        
        assertNotNull(eyeColors);
        assertEquals(3, eyeColors.size());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#getHairColor(java.lang.Long)}.
     */
    @Test
    public void testGetHairColor()
    {
        HairColor  hairColor = commonService.getHairColor(commonService.saveHairColor(getHairColor()).getId());
        
        assertNotNull(hairColor);
        assertEquals(HAIR_COLOR_NAME_VALUE, hairColor.getName());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#saveHairColor(com.bogie.common.model.HairColor)}.
     */
    @Test
    public void testSaveHairColor()
    {
        HairColor  hairColor = commonService.saveHairColor(getHairColor());
        
        assertNotNull(hairColor);
        assertNotNull(hairColor.getId());
        assertEquals(HAIR_COLOR_NAME_VALUE, hairColor.getName());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#deleteHairColor(java.lang.Long)}.
     */
    @Test
    public void testDeleteHairColorLong()
    {
        HairColor  hairColor = commonService.getHairColor(commonService.saveHairColor(getHairColor()).getId());
        
        commonService.deleteHairColor(hairColor.getId());
        
        assertNull(commonService.getHairColor(hairColor.getId()));
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#deleteHairColor(com.bogie.common.model.HairColor)}.
     */
    @Test
    public void testDeleteHairColorHairColor()
    {
        HairColor  hairColor = commonService.getHairColor(commonService.saveHairColor(getHairColor()).getId());
        
        commonService.deleteHairColor(hairColor);
        
        assertNull(commonService.getHairColor(hairColor.getId()));
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#findHairColors()}.
     */
    @Test
    public void testFindHairColors()
    {
        List<HairColor>  hairColors = commonService.findHairColors();
        
        assertNotNull(hairColors);
        assertEquals(0, hairColors.size());

        commonService.saveHairColor(getHairColor());
        commonService.saveHairColor(getHairColor());
        commonService.saveHairColor(getHairColor());
        
        hairColors = commonService.findHairColors();
        
        assertNotNull(hairColors);
        assertEquals(3, hairColors.size());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#getSkinColor(java.lang.Long)}.
     */
    @Test
    public void testGetSkinColor()
    {
        SkinColor  hairColor = commonService.getSkinColor(commonService.saveSkinColor(getSkinColor()).getId());
        
        assertNotNull(hairColor);
        assertEquals(HAIR_COLOR_NAME_VALUE, hairColor.getName());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#saveSkinColor(com.bogie.common.model.SkinColor)}.
     */
    @Test
    public void testSaveSkinColor()
    {
        SkinColor  hairColor = commonService.saveSkinColor(getSkinColor());
        
        assertNotNull(hairColor);
        assertNotNull(hairColor.getId());
        assertEquals(HAIR_COLOR_NAME_VALUE, hairColor.getName());
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#deleteSkinColor(java.lang.Long)}.
     */
    @Test
    public void testDeleteSkinColorLong()
    {
        SkinColor  hairColor = commonService.getSkinColor(commonService.saveSkinColor(getSkinColor()).getId());
        
        commonService.deleteSkinColor(hairColor.getId());
        
        assertNull(commonService.getSkinColor(hairColor.getId()));
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#deleteSkinColor(com.bogie.common.model.SkinColor)}.
     */
    @Test
    public void testDeleteSkinColorSkinColor()
    {
        SkinColor  hairColor = commonService.getSkinColor(commonService.saveSkinColor(getSkinColor()).getId());
        
        commonService.deleteSkinColor(hairColor);
        
        assertNull(commonService.getSkinColor(hairColor.getId()));
    }

    /**
     * Test method for {@link com.bogie.common.service.impl.CommonServiceImpl#findSkinColors()}.
     */
    @Test
    public void testFindSkinColors()
    {
        List<SkinColor>  hairColors = commonService.findSkinColors();
        
        assertNotNull(hairColors);
        assertEquals(0, hairColors.size());

        commonService.saveSkinColor(getSkinColor());
        commonService.saveSkinColor(getSkinColor());
        commonService.saveSkinColor(getSkinColor());
        
        hairColors = commonService.findSkinColors();
        
        assertNotNull(hairColors);
        assertEquals(3, hairColors.size());
    }
}
