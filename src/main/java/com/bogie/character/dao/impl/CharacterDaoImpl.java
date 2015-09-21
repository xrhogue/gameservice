/**
 * CharacterDaoImpl.java
 */
package com.bogie.character.dao.impl;

import java.util.List;

import com.bogie.common.dao.impl.GenericDaoImpl;
import com.bogie.character.dao.CharacterDao;
import com.bogie.character.model.Character;

public class CharacterDaoImpl extends GenericDaoImpl<Long, Character> implements CharacterDao
{
    /**
     * @see com.bogie.character.dao.CharacterDao#getCharacter(java.lang.Long)
     */
    public Character getCharacter(Long characterId)
    {
        return get(Character.class, characterId);
    }

    /**
     * @see com.bogie.character.dao.CharacterDao#deleteCharacter(com.bogie.character.lib.vo.Character)
     */
    public void deleteCharacter(Character character)
    {
        deleteCharacter(character.getId());
    }

    /**
     * @see com.bogie.character.dao.CharacterDao#deleteCharacter(java.lang.Long)
     */
    @Override
    public void deleteCharacter(Long characterId)
    {
        delete(getCharacter(characterId));
    }

    /**
     * @see com.bogie.character.dao.CharacterDao#saveCharacter(com.bogie.character.lib.vo.Character)
     */
    public Character saveCharacter(Character character)
    {
        return saveOrUpdate(character);
    }

    /**
     * @see com.bogie.character.dao.CharacterDao#findCharacters(com.bogie.character.lib.vo.Character)
     */
    public List<Character> findCharacters()
    {        
        return find("from Character");
    }
}
