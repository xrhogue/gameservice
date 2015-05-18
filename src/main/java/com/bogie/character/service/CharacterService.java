/**
 * CharacterService.java
 */
package com.bogie.character.service;

import java.util.List;

import com.bogie.character.model.Character;

/**
 * CharacterService 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public interface CharacterService
{
    public Character        getCharacter(Long characterId);
    public Character        saveCharacter(Character character);
    public void             deleteCharacter(Long characterId);
    public void             deleteCharacter(Character character);
    public List<Character>  findCharacters();
}
