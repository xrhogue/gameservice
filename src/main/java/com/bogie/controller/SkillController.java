package com.bogie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bogie.skill.bus.SkillService;
import com.bogie.skill.lib.model.Skill;

@RestController
public class SkillController
{
    @Autowired
    private SkillService    skillService;

    public SkillController()
    {
    }
    
    @RequestMapping("/skill")
    public Skill getSkill(@RequestParam(value="id", required=true) Long id)
    {
        return skillService.getSkill(id);
    }
    
    @RequestMapping("/skill/update")
    public void updateSkill(@RequestBody Skill skill)
    {
        skillService.saveSkill(skill);
    }
    
    @RequestMapping("/skill/delete")
    public void deleteSkill(@RequestParam(value="id", required=true) Long id)
    {
        skillService.deleteSkill(id);
    }
    
    @RequestMapping("/skill/list")
    public List<Skill> getSkills(@RequestParam(value="filter", required=false, defaultValue="") String filter)
    {
        return skillService.findAllSkills();
    }
}
