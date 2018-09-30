package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.items.Item;

public class ItemHandler {

    private static ItemHandler instance;

    public static ItemHandler getInstance() {
        if (instance == null) {
            instance = new ItemHandler();
        }
        return instance;
    }

    private ItemHandler() {
    }

    void assignToPlayer(Player player, Item item) {
        player.addItemToInventory(item);
    }
}

