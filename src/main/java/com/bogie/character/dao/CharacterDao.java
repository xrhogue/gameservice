/**
 * CharacterDao.java
 */
package com.bogie.character.dao;

import java.util.List;

import com.bogie.common.dao.GenericDao;
import com.bogie.character.model.Character;

/**
 * CharacterDao 
 * 
 * @author Richard Hogue
 * @version 1.0
 */
public interface CharacterDao extends GenericDao<Long, Character>
{
    public Character getCharacter(Long characterId);
    public void saveCharacter(Character character);
    public void deleteCharacter(Character character);
    
    /**
     * Retrieves the list of characters
     * @return the list of child characters
     */
    public List<Character>  findCharacters();
}
