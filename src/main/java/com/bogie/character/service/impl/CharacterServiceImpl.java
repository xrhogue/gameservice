/**
 * CharacterServiceImpl.java
 */
package com.bogie.character.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bogie.character.dao.CharacterDao;
import com.bogie.character.model.Character;
import com.bogie.character.service.CharacterService;

/**
 * CharacterServiceImpl 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
@Transactional
public class CharacterServiceImpl implements CharacterService
{
    @Autowired
    private CharacterDao    characterDao;

    /**
     * @see com.bogie.character.service.CharacterService#getCharacter(java.lang.Long)
     */
    @Override
    public Character getCharacter(Long characterId)
    {
        return characterDao.getCharacter(characterId);
    }

    /**
     * @see com.bogie.character.service.CharacterService#saveCharacter(com.bogie.character.model.Character)
     */
    @Override
    public Character saveCharacter(Character character)
    {
        return null;
    }

    /**
     * @see com.bogie.character.service.CharacterService#deleteCharacter(java.lang.Long)
     */
    @Override
    public void deleteCharacter(Long characterId)
    {
    }

    /**
     * @see com.bogie.character.service.CharacterService#deleteCharacter(com.bogie.character.model.Character)
     */
    @Override
    public void deleteCharacter(Character character)
    {
    }

    /**
     * @see com.bogie.character.service.CharacterService#findCharacters()
     */
    @Override
    public List<Character> findCharacters()
    {
        return null;
    }
}
