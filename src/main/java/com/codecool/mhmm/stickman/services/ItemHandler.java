package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;

class ItemHandler {

    void assignToPlayer(Player player, Item item) {
        player.addItemToInventory(item);
    }


    public void assignToEnemy(Enemy enemy, Item item) {
        enemy.addLoot(item);
    }
}

