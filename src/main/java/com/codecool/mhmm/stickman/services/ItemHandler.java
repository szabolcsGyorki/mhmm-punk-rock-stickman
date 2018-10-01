package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.items.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;

public class ItemHandler {

    private ItemsDAO itemsDAO;
    private HealthHandler healthHandler;

    public ItemHandler(ItemsDAO itemsDAO, HealthHandler healthHandler) {
        this.itemsDAO = itemsDAO;
        this.healthHandler = healthHandler;
    }

    void assignToPlayer(Player player, Item item) {
        player.addItemToInventory(item);
    }

    public void equipWeapon(Player player, String itemName){
        Item item = itemsDAO.getItemByName(itemName);
        if (item instanceof Weapon)
            player.setWeapon((Weapon) item);
    }

    public void equipArmor(Player player, String itemName){
        Item item = itemsDAO.getItemByName(itemName);
        if (item instanceof Armor) {
            if (!healthHandler.armorChangeKillsPlayer(player, (Armor) item)) {
                healthHandler.increasePlayerHealthWithArmor(player, (Armor) item);
                player.setFullBody((Armor) item);
            }
        }
    }

}

