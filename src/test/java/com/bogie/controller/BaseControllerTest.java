/**
 * 
 */
package com.bogie.controller;

import org.springframework.test.context.ContextConfiguration;

import com.bogie.BaseTest;
import com.bogie.TestAppConfig;
import com.bogie.common.model.Stat;

/**
 * @author Richard Hogue
 * @version 1.0
 */
@ContextConfiguration(classes = {TestAppConfig.class})
public class BaseControllerTest extends BaseTest
{
    public static final Character   PROPERTY_CODE_VALUE = 'X';
    public static final String      PROPERTY_SHORT_FORM_VALUE = "XXX";
    public static final String      PROPERTY_LONG_FORM_VALUE = "Xxxxxx";
    public static final Integer     PROPERTY_MULTIPLIER_VALUE = 3;
    
    protected Stat getTestStat()
    {
        Stat    stat = new Stat();
        
        stat.setCode(PROPERTY_CODE_VALUE);
        stat.setShortForm(PROPERTY_SHORT_FORM_VALUE);
        stat.setLongForm(PROPERTY_LONG_FORM_VALUE);
        stat.setMultiplier(PROPERTY_MULTIPLIER_VALUE);
        
        return stat;
    }
}
