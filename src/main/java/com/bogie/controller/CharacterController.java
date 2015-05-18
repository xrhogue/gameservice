package com.bogie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bogie.character.model.Character;
import com.bogie.character.service.CharacterService;

@RestController
public class CharacterController
{
    @Autowired
    private CharacterService    characterService;

    public CharacterController()
    {
    }
    
    @RequestMapping("/character")
    @ResponseBody 
    public Character getCharacter(@RequestParam(value="id", required=true) Long id)
    {
        return characterService.getCharacter(id);
    }
    
    @RequestMapping("/character/update")
    @ResponseBody
    public Character updateCharacter(@RequestBody(required=true) Character character)
    {
       return characterService.saveCharacter(character);
    }
    
    @RequestMapping("/character/delete")
    public void deleteCharacter(@RequestParam(value="id", required=true) Long id)
    {
        characterService.deleteCharacter(id);
    }
    
    @RequestMapping(value="/character/list", method=RequestMethod.GET)
    @ResponseBody 
    public List<Character> getCharacters()
    {
        return characterService.findCharacters();
    }
}
