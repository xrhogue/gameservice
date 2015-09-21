/**
 * 
 */
package com.bogie;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bogie.character.dao.CharacterDao;
import com.bogie.character.dao.impl.CharacterDaoImpl;
import com.bogie.character.service.CharacterService;
import com.bogie.character.service.impl.CharacterServiceImpl;
import com.bogie.common.dao.GenericDao;
import com.bogie.common.dao.impl.GenericDaoImpl;
import com.bogie.common.model.Complexion;
import com.bogie.common.model.EyeColor;
import com.bogie.common.model.Gender;
import com.bogie.common.model.HairColor;
import com.bogie.common.model.SkinColor;
import com.bogie.common.model.Stat;
import com.bogie.common.service.CommonService;
import com.bogie.common.service.impl.CommonServiceImpl;
import com.bogie.controller.GameController;
import com.bogie.controller.RaceController;
import com.bogie.controller.SkillController;
import com.bogie.controller.StatController;
import com.bogie.race.dao.RaceDao;
import com.bogie.race.dao.impl.RaceDaoImpl;
import com.bogie.race.service.RaceService;
import com.bogie.race.service.impl.RaceServiceImpl;
import com.bogie.skill.dao.SkillDao;
import com.bogie.skill.dao.impl.SkillDaoImpl;
import com.bogie.skill.service.SkillService;
import com.bogie.skill.service.impl.SkillServiceImpl;

/**
 * @author Richard Hogue
 *
 */
@EnableTransactionManagement
@EnableAutoConfiguration
@PropertySource(value = {
        "classpath:testapplication.properties"
    })
public class TestAppConfig
{
    @Bean
    public GenericDao<Long, Stat> statDao()
    {
        return new GenericDaoImpl<Long, Stat>();
    }
    
    @Bean
    public GenericDao<Long, Gender> genderDao()
    {
        return new GenericDaoImpl<Long, Gender>();
    }
        
    @Bean
    public GenericDao<Long, Complexion> complexionDao()
    {
        return new GenericDaoImpl<Long, Complexion>();
    }
    
    @Bean
    public GenericDao<Long, EyeColor> eyeColorDao()
    {
        return new GenericDaoImpl<Long, EyeColor>();
    }
    
    @Bean
    public GenericDao<Long, HairColor> hairColorDao()
    {
        return new GenericDaoImpl<Long, HairColor>();
    }
    
    @Bean
    public GenericDao<Long, SkinColor> skinColorDao()
    {
        return new GenericDaoImpl<Long, SkinColor>();
    }
    
    @Bean
    public RaceDao raceDao()
    {
        return new RaceDaoImpl();
    }
    
    @Bean
    public SkillDao skillDao()
    {
        return new SkillDaoImpl();
    }
    
    @Bean
    public CharacterDao characterDao()
    {
        return new CharacterDaoImpl();
    }
    
    @Bean
    public CommonService commonService()
    {
        return new CommonServiceImpl();
    }
    
    @Bean
    public RaceService raceService()
    {
        return new RaceServiceImpl();
    }
    
    @Bean
    public SkillService skillService()
    {
        return new SkillServiceImpl();
    }
    
    @Bean
    public CharacterService characterService()
    {
        return new CharacterServiceImpl();
    }
    
    @Bean
    public StatController statController()
    {
        return new StatController();
    }
    
    @Bean
    public RaceController raceController()
    {
        return new RaceController();
    }
    
    @Bean
    public SkillController skillController()
    {
        return new SkillController();
    }
    
    @Bean
    public GameController gameController()
    {
        return new GameController();
    }
}
