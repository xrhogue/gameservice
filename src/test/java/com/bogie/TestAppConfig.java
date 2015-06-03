/**
 * 
 */
package com.bogie;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bogie.common.dao.GenericDao;
import com.bogie.common.dao.impl.GenericDaoImpl;
import com.bogie.common.model.Complexion;
import com.bogie.common.model.EyeColor;
import com.bogie.common.model.Gender;
import com.bogie.common.model.HairColor;
import com.bogie.common.model.Stat;
import com.bogie.common.service.CommonService;
import com.bogie.common.service.impl.CommonServiceImpl;
import com.bogie.race.dao.RaceDao;
import com.bogie.race.dao.impl.RaceDaoImpl;
import com.bogie.skill.dao.SkillDao;
import com.bogie.skill.dao.impl.SkillDaoImpl;

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
    public CommonService commonService()
    {
        return new CommonServiceImpl();
    }
}
