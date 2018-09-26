package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.DAO.PlayerDAO;
import com.codecool.mhmm.stickman.GameObjects.Characters.Player;
import com.codecool.mhmm.stickman.GameObjects.GameObject;
import com.codecool.mhmm.stickman.GameObjects.Items.Item;

import javax.persistence.EntityManager;

public class PlayerDAOImpl extends BaseDaoImpl implements PlayerDAO {

    PlayerDAOImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Player getPlayer(int id) {
        return null;
    }

    @Override
    public Player getPlayer(String name) {
        return null;
    }

    @Override
    public void updatePlayer(Player player) {

    }

    @Override
    public void saveNewplayer(Player player) {

    }

    @Override
    public <List> Item getPlayerItems(Player player) {
        return null;
    }

    @Override
    public <List> Item getPlayerItems(int playerID) {
        return null;
    }

    @Override
    public <List> Item getPlayerItems(String PlayerName) {
        return null;
    }

    @Override
    public <List> GameObject getModifiedByPlayer(Player player) {
        return null;
    }

    @Override
    public <List> GameObject getModifiedByPlayer(String playerName) {
        return null;
    }

    @Override
    public <List> GameObject getModifiedByPlayer(int playerID) {
        return null;
    }

    @Override
    public void updateModifiedByPlayer(Player player) {

    }

    @Override
    public void updatePlayerItems(Player player) {

    }
}
