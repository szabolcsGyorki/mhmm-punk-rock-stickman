package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.dao.dao_impl.ItemsDAOImpl;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.items.equipable.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.game_objects.items.Loot;
import com.codecool.mhmm.stickman.game_objects.items.equipable.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class ItemHandlerTest {

    private ItemsDAO itemsDAO = Mockito.mock(ItemsDAOImpl.class);
    private HealthHandler healthHandler = Mockito.mock(HealthHandler.class);
    private Random random = Mockito.mock(Random.class);

    private ItemHandler itemHandler;

    @BeforeEach
    void init() {
        itemHandler = new ItemHandler(itemsDAO, healthHandler, random);
    }

    @Test
    void sanityCheck() {
        ItemHandler itemHandler = new ItemHandler(itemsDAO, healthHandler, random);
        assertNotNull(itemHandler);
    }

    @Test
    void testAssignToPlayer() {
        Player player = new Player(1, 1, "Jo");
        Weapon weapon = new Weapon("Sword", 20, 10, 20);

        itemHandler.assignToPlayer(player, weapon);
        assertEquals(weapon, player.getItems().get(0));
    }

    @Test
    void testAssignMultipleToPlayer() {
        Player player = new Player(1, 1, "Jo");
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
    void testEquipWeapon() {
        Player player = new Player(1, 1, "Jo");
        Weapon weapon = new Weapon("Sword", 20, 10, 20);

        when(itemsDAO.getItemByName(weapon.getName())).thenReturn(weapon);

        itemHandler.equipWeapon(player, weapon.getName());
        assertEquals(weapon, player.getWeapon());
    }

    @Test
    void testEquipArmor() {
        Player player = new Player(1, 1, "Jo");
        Armor armor = new Armor("armor", 20, 20);

        when(itemsDAO.getItemByName(armor.getName())).thenReturn(armor);
        when(healthHandler.armorChangeKillsPlayer(player, armor)).thenReturn(false);

        itemHandler.equipArmor(player, armor.getName());
        assertEquals(armor, player.getFullBody());
    }

    @Test
    void testSetLootGold() {
        int expected = 5;
        Loot loot = new Loot();

        when(random.nextInt(anyInt())).thenReturn(expected - 1);

        itemHandler.setLootGold(loot);
        assertEquals(expected, loot.getGold());
    }

    @Test
    void testFillUpLoot() {
        List<Item> expected = new ArrayList<>();
        Loot loot = new Loot();
        Armor armor = new Armor();
        Weapon weapon = new Weapon();
        expected.add(armor);
        expected.add(weapon);

        when(itemsDAO.getRandomItem()).thenReturn(armor, weapon);
        when(random.nextInt(anyInt())).thenReturn(1);

        itemHandler.fillUpLoot(loot);
        assertEquals(expected, loot.getItems());
    }

    @Test
    void testPlayerPicksUpLoot() {
        List<Item> expected = new ArrayList<>();
        Player player = new Player();
        Loot loot = new Loot();
        Armor armor = new Armor();
        Weapon weapon = new Weapon();
        expected.add(armor);
        expected.add(weapon);

        when(itemsDAO.getRandomItem()).thenReturn(armor, weapon);
        when(random.nextInt(anyInt())).thenReturn(1);

        itemHandler.fillUpLoot(loot);
        itemHandler.pickUpLoot(player, loot);
        assertEquals(expected, player.getItems());

    }
}
