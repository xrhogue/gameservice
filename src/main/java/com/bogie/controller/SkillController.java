package com.bogie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bogie.skill.bus.SkillService;
import com.bogie.skill.lib.model.Skill;

@RestController
@RequestMapping("/skills")
public class SkillController
{
    @Autowired
    private SkillService    skillService;

    public SkillController()
    {
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Skill getSkill(@PathVariable(value="id") Long id)
    {
        return skillService.getSkill(id);
    }
    
    @RequestMapping(value="", method=RequestMethod.POST)
    public void createSkill(@RequestBody Skill skill)
    {
        skillService.saveSkill(skill);
    }
    
    @RequestMapping(value="", method=RequestMethod.PUT)
    public void updateSkill(@RequestBody Skill skill)
    {
        skillService.saveSkill(skill);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public void deleteSkill(@RequestParam(value="id", required=true) Long id)
    {
        skillService.deleteSkill(id);
    }
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public List<Skill> getSkills(@RequestParam(value="filter", required=false, defaultValue="") String filter)
    {
        return skillService.findSkills();
    }
}
