package com.bogie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bogie.race.bus.RaceService;
import com.bogie.race.lib.model.Race;
import com.bogie.skill.bus.SkillService;
import com.bogie.skill.lib.model.Skill;

@RestController
public class GameController
{
    @Autowired
    private SkillService    skillService;
 
    @Autowired
    private RaceService     raceService;

    public GameController()
    {
    }
//    @Autowired
//    public GameController(SkillService skillService)
//    {
//        this.skillService = skillService;
//    }
    
    @RequestMapping("/skill")
    public Skill getSkill(@RequestParam(value="id", required=false, defaultValue="3") Long id)
    {
        return skillService.getSkill(id);
    }
    
    @RequestMapping("/skill/list")
    public List<Skill> getSkills(@RequestParam(value="filter", required=false, defaultValue="") String filter)
    {
        return skillService.findAllSkills();
    }
   
    @RequestMapping("/race/list")
    public List<Race> getRaces(@RequestParam(value="filter", required=false, defaultValue="") String filter)
    {
        return raceService.findAllRaces();
    }
}
