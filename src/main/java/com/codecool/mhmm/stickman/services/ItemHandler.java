package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.characters.Character;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;

class ItemHandler {
    void assignToCharacter(Character character, Item item) {
        if (character instanceof Player) {
            ((Player)character).addItemToInventory(item);
        }
        if (character instanceof Enemy) {
            ((Enemy)character).addLoot(item);
        }
    }
}
