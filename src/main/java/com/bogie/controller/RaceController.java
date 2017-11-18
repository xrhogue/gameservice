package com.bogie.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bogie.race.bus.RaceService;
import com.bogie.race.lib.model.Race;
import com.bogie.race.lib.model.RaceComplexion;

@RestController
@RequestMapping("/races")
public class RaceController
{
    @Autowired
    private RaceService    raceService;

    public RaceController()
    {
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Race getRace(@PathVariable("id") Long id)
    {
        return raceService.getRace(id);
    }
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public void createRace(@RequestBody Race race)
    {
        raceService.saveRace(race);
    }
    
    @RequestMapping(value="/", method=RequestMethod.PUT)
    public void updateRace(@RequestBody Race race)
    {
        raceService.saveRace(race);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public void deleteRace(@PathVariable("id") Long id)
    {
        raceService.deleteRace(id);
    }
    
    @RequestMapping("/")
    public List<Race> getRaces(@RequestParam(value="filter", required=false, defaultValue="") String filter)
    {
        return raceService.findAllRaces();
    }
    
    @RequestMapping("/{id}/complexion/list")
    public Set<RaceComplexion> getRaceComplexions(@PathVariable("id") Long id)
    {
        return raceService.getRace(id).getComplexions();
    }
}
