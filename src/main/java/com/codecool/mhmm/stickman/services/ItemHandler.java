package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.items.Item;

class ItemHandler {

    void assignToPlayer(Player player, Item item) {
        player.addItemToInventory(item);
    }
}

