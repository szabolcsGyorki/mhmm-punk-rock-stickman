package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Orc;
import com.codecool.mhmm.stickman.game_objects.items.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemHandlerTest {

    private ItemHandler itemHandler;

    @BeforeEach
    void init() {
        itemHandler = new ItemHandler();
    }

    @Test
    void sanityCheck() {
        ItemHandler itemHandler = new ItemHandler();
        assertNotNull(itemHandler);
    }

    @Test
    void testAssignToPlayer() {
        Player player = new Player(1,1, "Jo");
        Weapon weapon = new Weapon("Sword", 20, 10, 20);
        itemHandler.assignToPlayer(player, weapon);
        assertEquals(weapon, player.getItems().get(0));
    }

    @Test
    void testAssignMultipleToPlayer() {
        Player player = new Player(1,1, "Jo");
        List<Item> inventory = new ArrayList<>();
        Weapon weapon = new Weapon("Sword", 20, 10, 20);
        Armor armor = new Armor("armor", 20, 20);
        inventory.add(weapon);
        inventory.add(armor);

        itemHandler.assignToPlayer(player, weapon);
        itemHandler.assignToPlayer(player, armor);
        assertEquals(inventory, player.getItems());
    }

    @Test
    void testAssignToEnemy() {
        Enemy enemy = new Orc(1, 1, 1);
        List<Item> loot = new ArrayList<>();
        Weapon weapon = new Weapon("Sword", 20, 10, 20);
        loot.add(weapon);
        itemHandler.assignToEnemy(enemy, weapon);
        assertEquals(loot, enemy.getLoot());
    }

    @Test
    void testAssignMultipleToEnemy() {
        Enemy enemy = new Orc(1, 1, 1);
        List<Item> loot = new ArrayList<>();
        Weapon weapon = new Weapon("Sword", 20, 10, 20);
        Armor armor = new Armor("armor", 10, 20);
        loot.add(weapon);
        loot.add(armor);

        itemHandler.assignToEnemy(enemy, weapon);
        itemHandler.assignToEnemy(enemy, armor);
        assertEquals(loot, enemy.getLoot());
    }
}
