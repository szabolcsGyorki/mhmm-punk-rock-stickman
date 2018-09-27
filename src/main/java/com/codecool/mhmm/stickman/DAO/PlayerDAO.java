package com.codecool.mhmm.stickman.DAO;

import com.codecool.mhmm.stickman.GameObjects.Characters.Player;
import com.codecool.mhmm.stickman.GameObjects.GameObject;

import java.util.List;

public interface PlayerDAO {

    /**
     * IN ORDER!
     * get player stats(name, str, agi, int, dodge-hit chance, X-Y coordinates)
     * set damage, armor to 0
     * get player inventory
     * get player Armor
     * get player Weapon
     * equip player Armor
     * equip player Weapon
     * @param id: the id of the player
     */
    Player getPlayer(long id);

    /**
     * IN ORDER!
     * get player stats(name, str, agi, int, dodge-hit chance, X-Y coordinates)
     * set damage, armor to 0
     * get player inventory
     * get player Armor
     * get player Weapon
     * equip player Armor
     * equip player Weapon
     */
    Player getPlayer(String name);

    /**
     * IN ORDER!
     * UNEQUIP player Armor
     * UNEQUIP player Weapon
     * save player stats(name, str, agi, int, dodge-hit chance, X-Y coordinates, inventory)
     * REEQUIP player Armor
     * REEQUIP player Weapon
     */
    <T> void updatePlayer(Player player, String field, T value);

    /**
     * check if player name exist (character name)
     */
    void saveNewPlayer(Player player);

    /**
     * load list of modified elements
     */
    List<GameObject> getModifiedByPlayer(Player player);

    /**
     * load list of modified elements
     */
    List<GameObject> getModifiedByPlayer(String playerName);

    /**
     * load list of modified elements
     * @param playerID: id of the player
     */
    List<GameObject> getModifiedByPlayer(long playerID);

    void updateModifiedByPlayer(Player player);

    void updatePlayerItems(Player player);
}
