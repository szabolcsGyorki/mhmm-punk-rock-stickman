package com.codecool.mhmm.stickman.DAO;

import com.codecool.mhmm.stickman.GameObjects.Characters.Player;
import com.codecool.mhmm.stickman.GameObjects.GameObject;
import com.codecool.mhmm.stickman.GameObjects.Items.Item;

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
     */
    Player getPlayer(int id);

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
    void updatePlayer(Player player);

    /**
     * check if player name exist (character name)
     */
    void saveNewplayer(Player player);

    /**
     * load player items
     */
    <List> Item getPlayerItems(Player player);

    /**
     * load player items
     */
    <List> Item getPlayerItems(int playerID);

    /**
     * load player items
     */
    <List> Item getPlayerItems(String PlayerName);

    /**
     * load list of modified elements
     */
    <List> GameObject getModifiedByPlayer(Player player);

    /**
     * load list of modified elements
     */
    <List> GameObject getModifiedByPlayer(String playerName);

    /**
     * load list of modified elements
     */
    <List> GameObject getModifiedByPlayer(int playerID);

    void updateModifiedByPlayer(Player player);

    void updatePlayerItems(Player player);
}
