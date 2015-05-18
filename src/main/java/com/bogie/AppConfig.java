package com.bogie;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.bogie.character.dao.CharacterDao;
import com.bogie.character.dao.impl.CharacterDaoImpl;
import com.bogie.character.service.CharacterService;
import com.bogie.character.service.impl.CharacterServiceImpl;
import com.bogie.common.dao.GenericDao;
import com.bogie.common.dao.impl.GenericDaoImpl;
import com.bogie.common.model.Complexion;
import com.bogie.common.model.EyeColor;
import com.bogie.common.model.HairColor;
import com.bogie.common.model.SkinColor;
import com.bogie.common.model.Stat;
import com.bogie.common.service.CommonService;
import com.bogie.common.service.impl.CommonServiceImpl;
import com.bogie.controller.GameController;
import com.bogie.controller.SkillController;
import com.bogie.controller.StatController;
import com.bogie.race.dao.PhysicalAppearanceDao;
import com.bogie.race.dao.RaceDao;
import com.bogie.race.dao.impl.PhysicalAppearanceDaoImpl;
import com.bogie.race.dao.impl.RaceDaoImpl;
import com.bogie.race.service.RaceService;
import com.bogie.race.service.impl.RaceServiceImpl;
import com.bogie.skill.dao.SkillDao;
import com.bogie.skill.dao.impl.SkillDaoImpl;
import com.bogie.skill.service.SkillService;
import com.bogie.skill.service.impl.SkillServiceImpl;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
public class AppConfig extends WebMvcAutoConfigurationAdapter
{       
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
    }

    @Bean
    public GameController gameController()
    {
        return new GameController();
    }
    
    @Bean
    public SkillController skillController()
    {
        return new SkillController();
    }
    
    @Bean
    public StatController statController()
    {
        return new StatController();
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
    public RaceService raceService()
    {
        return new RaceServiceImpl();
    }
    
    @Bean
    public CommonService commonService()
    {
        return new CommonServiceImpl();
    }

    @Bean
    public SkillDao skillDao()
    {
        return new SkillDaoImpl();
    }

    @Bean
    public RaceDao raceDao()
    {
        return new RaceDaoImpl();
    }

    @Bean
    public CharacterDao characterDao()
    {
        return new CharacterDaoImpl();
    }

    @Bean
    public GenericDao<Long, Stat> statDao()
    {
        return new GenericDaoImpl<Long, Stat>();
    }

    @Bean
    public PhysicalAppearanceDao<Complexion> complexionDao()
    {
        return new PhysicalAppearanceDaoImpl<Complexion>();
    }

    @Bean
    public PhysicalAppearanceDao<SkinColor> skinColorDao()
    {
        return new PhysicalAppearanceDaoImpl<SkinColor>();
    }

    @Bean
    public PhysicalAppearanceDao<HairColor> hairColorDao()
    {
        return new PhysicalAppearanceDaoImpl<HairColor>();
    }

    @Bean
    public PhysicalAppearanceDao<EyeColor> eyeColorDao()
    {
        return new PhysicalAppearanceDaoImpl<EyeColor>();
    }
}