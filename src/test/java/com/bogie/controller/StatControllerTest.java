/**
 * 
 */
package com.bogie.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bogie.common.model.Stat;

/**
 * @author rhogue
 *
 */
public class StatControllerTest extends BaseControllerTest
{
    @Autowired
    private StatController  statController;
    
    /**
     * Test method for {@link com.bogie.controller.StatController#StatController()}.
     */
    @Test
    public void testStatController()
    {
        assertNotNull(statController);
    }

    /**
     * Test method for {@link com.bogie.controller.StatController#getStat(java.lang.Long)}.
     */
    @Test
    public void testGetStat()
    {
        Stat    stat = statController.updateStat(getTestStat());
        
        assertNotNull(stat);
        assertNotNull(stat.getId());
        
        Stat    savedStat = statController.getStat(stat.getId());
        
        assertNotNull(savedStat);
        assertEquals(stat.getId(), savedStat.getId());
    }

    /**
     * Test method for {@link com.bogie.controller.StatController#updateStat(com.bogie.common.model.Stat)}.
     */
    @Test
    public void testUpdateStat()
    {
        Stat    newStat = getTestStat();
        Stat    savedStat = statController.updateStat(newStat);
        
        assertNotNull(savedStat);
        assertNotNull(savedStat.getId());
        
        savedStat.setLongForm(savedStat.getLongForm() + "new");
        
        Stat    updatedStat = statController.updateStat(savedStat);
        
        assertNotNull(updatedStat);
        assertEquals(savedStat.getLongForm(), updatedStat.getLongForm());
    }

    /**
     * Test method for {@link com.bogie.controller.StatController#deleteStat(java.lang.Long)}.
     */
    @Test
    public void testDeleteStat()
    {
        Stat    newStat = getTestStat();
        Stat    savedStat = statController.updateStat(newStat);
        
        assertNotNull(savedStat);
        assertNotNull(savedStat.getId());
        
        statController.deleteStat(savedStat.getId());
        
        assertNull(statController.getStat(savedStat.getId()));
    }

    /**
     * Test method for {@link com.bogie.controller.StatController#getStats()}.
     */
    @Test
    public void testGetStats()
    {
        List<Stat>  stats = statController.getStats();
        
        assertNotNull(stats);
        assertTrue(stats.size() > 0);
    }
}
