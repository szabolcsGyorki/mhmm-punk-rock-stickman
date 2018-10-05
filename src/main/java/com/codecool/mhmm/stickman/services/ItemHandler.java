package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.items.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.game_objects.items.Loot;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;

import java.util.Random;
import java.util.stream.Stream;

public class ItemHandler {

    private ItemsDAO itemsDAO;
    private HealthHandler healthHandler;
    private Random random;

    public ItemHandler(ItemsDAO itemsDAO, HealthHandler healthHandler, Random random) {
        this.itemsDAO = itemsDAO;
        this.healthHandler = healthHandler;
        this.random = random;
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

    public void setLootGold(Loot loot) {
        loot.setGold(random.nextInt(10) + 1);
    }

    public void fillUpLoot(Loot loot) {
        Stream.generate(itemsDAO::getRandomItem)
                .limit(random.nextInt(3) + 1)
                .forEach(loot::add);
    }

    public void pickUpLoot(Player player, Loot loot) {
        loot.getItems().forEach(player::addItemToInventory);
    }
}

