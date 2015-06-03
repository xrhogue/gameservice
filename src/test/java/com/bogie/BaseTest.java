/**
 * BaseTest.java
 */
package com.bogie;

import java.sql.SQLException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;

import com.bogie.common.model.Complexion;
import com.bogie.common.model.EyeColor;
import com.bogie.common.model.Gender;
import com.bogie.common.model.HairColor;
import com.bogie.common.model.SkinColor;
import com.bogie.common.model.Stat;
import com.bogie.common.service.HibernateService;
import com.bogie.race.model.Race;
import com.bogie.race.model.RaceComplexion;
import com.bogie.race.model.RaceEyeColor;
import com.bogie.race.model.RaceHairColor;
import com.bogie.race.model.RaceSkinColor;
import com.bogie.skill.model.Skill;
import com.bogie.util.JDBCUtil;

/**
 * BaseTest
 *
 * @author Richard Hogue
 */
public abstract class BaseTest
{
    public static final String RACE_NAME_VALUE = "raceNameValue";
    public static final String      SKILL_SHORT_NAME_VALUE = "skillShortNameValue";
    public static final String      SKILL_NAME_VALUE = "skillNameValue";
    public static final String      SKIN_COLOR_NAME_VALUE = "skinColorNameValue";
    public static final String      HAIR_COLOR_NAME_VALUE = "hairColorNameValue";
    public static final String      EYE_COLOR_NAME_VALUE = "eyeColorNameValue";
    public static final String      GENDER_NAME_VALUE = "genderNameValue";
    public static final String      COMPLEXION_NAME_VALUE = "complexionNameValue";
    public static final Integer     STAT_MULTIPLIER_VALUE = 1;
    public static final String      STAT_SHORT_FORM_VALUE = "XXX";
    public static final String      STAT_LONG_FORM_VALUE = "XXXXXX";
    public static final Character   STAT_CODE_VALUE = 'X';

    @Value("${spring.datasource.driverClassName}")
    protected String    driver;

    @Value("${spring.datasource.url}")
    protected String    url;

    @Value("${spring.datasource.username}")
    protected String    userName;

    @Value("${spring.datasource.password}")
    protected String    password;

    protected void resetCommonDatabase() throws ClassNotFoundException, SQLException
    {
        JDBCUtil    jdbcUtil = new JDBCUtil(driver, url, userName, password);
        
        if (jdbcUtil.databaseExists("gametest"))
        {
            jdbcUtil.execute("drop schema gametest");
        }
        
        HibernateService.createSchema("src/test/resources/common.cfg.xml", "gametest");
    }

    protected void resetRaceDatabase() throws ClassNotFoundException, SQLException
    {
        JDBCUtil    jdbcUtil = new JDBCUtil(driver, url, userName, password);
        
        if (jdbcUtil.databaseExists("gametest"))
        {
            jdbcUtil.execute("drop schema gametest");
        }
        
        HibernateService.createSchema("src/test/resources/race.cfg.xml", "gametest");
    }

    protected void resetSkillDatabase() throws ClassNotFoundException, SQLException
    {
        JDBCUtil    jdbcUtil = new JDBCUtil(driver, url, userName, password);
        
        if (jdbcUtil.databaseExists("gametest"))
        {
            jdbcUtil.execute("drop schema gametest");
        }
        
        HibernateService.createSchema("src/test/resources/skill.cfg.xml", "gametest");
    }

    protected Stat getStat()
    {
        Stat    stat = new Stat();
        
        stat.setCode(STAT_CODE_VALUE);
        stat.setLongForm(STAT_LONG_FORM_VALUE);
        stat.setShortForm(STAT_SHORT_FORM_VALUE);
        stat.setMultiplier(STAT_MULTIPLIER_VALUE);

        return stat;
    }

    protected Gender getGender()
    {
        Gender  gender = new Gender();
        
        gender.setName(GENDER_NAME_VALUE);

        return gender;
    }

    protected Complexion getComplexion()
    {
        Complexion  complexion = new Complexion();
        
        complexion.setName(COMPLEXION_NAME_VALUE);

        return complexion;
    }

    protected EyeColor getEyeColor()
    {
        EyeColor  eyeColor = new EyeColor();
        
        eyeColor.setName(EYE_COLOR_NAME_VALUE);

        return eyeColor;
    }

    protected HairColor getHairColor()
    {
        HairColor   hairColor = new HairColor();
        
        hairColor.setName(HAIR_COLOR_NAME_VALUE);

        return hairColor;
    }

    protected SkinColor getSkinColor()
    {
        SkinColor   skinColor = new SkinColor();
        
        skinColor.setName(SKIN_COLOR_NAME_VALUE);

        return skinColor;
    }

    protected Skill getSkill()
    {
        Skill   skill = new Skill();
        
        skill.setName(SKILL_NAME_VALUE);
        skill.setShortName(SKILL_SHORT_NAME_VALUE);
        skill.setPrimaryStat(getStat());
        skill.setSecondaryStats(Arrays.asList(getStat()));
        skill.setBaseCost(3);
        skill.setLevelCost(3);

        return skill;
    }
    
    protected Race getRace()
    {
        RaceComplexion  raceComplexion = new RaceComplexion(getComplexion());
        RaceEyeColor    raceEyeColor = new RaceEyeColor(getEyeColor());
        RaceHairColor   raceHairColor = new RaceHairColor(getHairColor());
        RaceSkinColor   raceSkinColor = new RaceSkinColor(getSkinColor());        
        Race            race = new Race();
        
        race.setName(RACE_NAME_VALUE);
        race.addComplexion(raceComplexion);
        race.addEyeColor(raceEyeColor);
        race.addHairColor(raceHairColor);
        race.addSkinColor(raceSkinColor);
        
        return race;
    }
}
