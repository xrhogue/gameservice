package com.bogie;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.bogie.common.bus.CommonService;
import com.bogie.common.bus.impl.CommonServiceImpl;
import com.bogie.common.dao.GenericDao;
import com.bogie.common.dao.impl.GenericDaoImpl;
import com.bogie.common.lib.model.Complexion;
import com.bogie.common.lib.model.EyeColor;
import com.bogie.common.lib.model.HairColor;
import com.bogie.common.lib.model.SkinColor;
import com.bogie.common.lib.model.Stat;
import com.bogie.controller.GameController;
import com.bogie.controller.SkillController;
import com.bogie.controller.StatController;
import com.bogie.race.bus.RaceService;
import com.bogie.race.bus.impl.RaceServiceImpl;
import com.bogie.race.dao.PhysicalAppearanceDao;
import com.bogie.race.dao.RaceDao;
import com.bogie.race.dao.impl.PhysicalAppearanceDaoImpl;
import com.bogie.race.dao.impl.RaceDaoImpl;
import com.bogie.skill.bus.SkillService;
import com.bogie.skill.bus.impl.SkillServiceImpl;
import com.bogie.skill.dao.SkillDao;
import com.bogie.skill.dao.impl.SkillDaoImpl;
import com.bogie.skill.lib.model.Skill;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
public class AppConfig extends WebMvcAutoConfigurationAdapter
{       
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
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

    @Bean
    public HibernateTemplate hibernateTemplate()
    {
        return new HibernateTemplate(sessionFactory());
    }
    
    @Bean
    public SessionFactory sessionFactory()
    {
        return new LocalSessionFactoryBuilder(dataSource()).addAnnotatedClasses(Skill.class).scanPackages("com.bogie.common.lib.model", "com.bogie.race.lib.model").buildSessionFactory();
    }

    @Bean
    public DataSource dataSource()
    {
        BasicDataSource dataSource = new BasicDataSource();
        
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/game");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        
        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager()
    {
        return new HibernateTransactionManager(sessionFactory());
    }
}