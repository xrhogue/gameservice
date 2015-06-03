package com.bogie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bogie.race.model.Race;
import com.bogie.race.model.RaceComplexion;
import com.bogie.race.model.RaceEyeColor;
import com.bogie.race.model.RaceHairColor;
import com.bogie.race.service.RaceService;

@RestController
public class RaceController
{
    @Autowired
    private RaceService    raceService;

    public RaceController()
    {
    }
    
    @RequestMapping("/race")
    public Race getRace(@RequestParam(value="id", required=true) Long id)
    {
        return raceService.getRace(id);
    }
    
    @RequestMapping("/race/update")
    public void updateRace(@RequestBody Race race)
    {
        raceService.saveRace(race);
    }
    
    @RequestMapping("/race/delete")
    public void deleteRace(@RequestParam(value="id", required=true) Long id)
    {
        raceService.deleteRace(id);
    }
    
    @RequestMapping("/race/list")
    public List<Race> getRaces(@RequestParam(value="filter", required=false, defaultValue="") String filter)
    {
        return raceService.findRaces();
    }
    
    @RequestMapping("/race/{id}/complexion/list")
    public List<RaceComplexion> getRaceComplexions(@PathVariable("id") Long id)
    {
        return raceService.getRace(id).getComplexions();
    }
    
    @RequestMapping("/race/{id}/eyecolor/list")
    public List<RaceEyeColor> getRaceEyeColors(@PathVariable("id") Long id)
    {
        return raceService.getRace(id).getEyeColors();
    }
    
    @RequestMapping("/race/{id}/haircolor/list")
    public List<RaceHairColor> getRaceHairColors(@PathVariable("id") Long id)
    {
        return raceService.getRace(id).getHairColors();
    }
}
