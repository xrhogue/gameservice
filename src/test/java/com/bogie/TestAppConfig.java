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
import com.bogie.common.model.Stat;

/**
 * @author rhogue
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
}
