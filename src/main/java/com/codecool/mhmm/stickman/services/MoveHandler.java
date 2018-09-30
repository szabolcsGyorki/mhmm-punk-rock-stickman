package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.characters.Character;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.stickman.game_objects.items.Loot;
import com.codecool.mhmm.stickman.map.Level;

import java.util.List;

public class MoveHandler {
    private static MoveHandler instance;

    public static MoveHandler getInstance() {
        if (instance == null) {
            instance = new MoveHandler();
        }
        return instance;
    }

    private MoveHandler() {
    }

    public void move(int toX, int toY, Character movingCharacter, Level level, ItemsDAO itemsDAO){
        List<GameObject> map = level.getMap();
        GameObject destination = getDestination(toX, toY, map);

        if (destination == null) {
            movingCharacter.place(toX, toY);
        } else {
            switch (destination.getType()) {
                case LOOT: {
                    movingCharacter.place(toX, toY);
                    Loot loot = (Loot) destination;
                    loot.Pickup((Player)movingCharacter);
                    map.remove(destination);
                    break;
                }
                case DRAGON:
                case ORC:
                case SKELETON:
                case SLIME: {
                    if (movingCharacter instanceof Player) {
                        Player player = (Player) movingCharacter;
                        Enemy enemy = (Enemy) destination;
                        player.takeDamage(enemy.attack());
                        enemy.takeDamage(player.attack());
                        if (enemy.getHitPoint() <= 0) {
                            map.remove(destination);
                            movingCharacter.place(toX, toY);
                            Loot loot = new Loot(0,0,itemsDAO);
                            loot.Pickup(player);
                        }
                        break;
                    }
                }
            }
        }
    }

    private GameObject getDestination(int toX, int toY, List<GameObject> map) {
        GameObject destination = null;
        for (GameObject mapElement: map) {
            if (mapElement.getX() == toX && mapElement.getY() == toY) {
                destination = mapElement;
                break;
            }
        }
        return destination;
    }
}
