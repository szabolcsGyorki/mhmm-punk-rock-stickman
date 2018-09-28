package com.codecool.mhmm.stickman.DAO;

import com.codecool.mhmm.stickman.GameObjects.Characters.Player;
import com.codecool.mhmm.stickman.GameObjects.GameObject;

import java.util.List;

public interface PlayerDAO extends BaseDAO {

    /**
     * @param name: name of the player
     * @return: a player object
     */
    Player getPlayerByName(String name);

    /**
     * load list of every game objects that was modified by the player (ex.: enemies
     * that are damaged/dead, loot that was picked up, etc...)
     * @param player: the player
     * @return: a list that contains all modified objects
     */
    List<GameObject> getModifiedByPlayer(Player player);

    /**
     * Find the player by name and load list of every game objects that was modified by the player (ex.: enemies
     * that are damaged/dead, loot that was picked up, etc...)
     * @param playerName: the name of the player
     * @return: a list that contains all modified objects
     */
    List<GameObject> getModifiedByPlayer(String playerName);

    /**
     * Find the player by id and load list of every game objects that was modified by the player (ex.: enemies
     * that are damaged/dead, loot that was picked up, etc...)
     * @param playerID: the id of the player
     * @return: a list that contains all modified objects
     */
    List<GameObject> getModifiedByPlayer(long playerID);

    /**
     *
     * @param player:
     */
    void updateModifiedByPlayer(Player player);

    /**
     *
     * @param player:
     */
    void updatePlayerItems(Player player);
}
