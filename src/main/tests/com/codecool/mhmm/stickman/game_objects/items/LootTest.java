package com.codecool.mhmm.stickman.game_objects.items;

import com.codecool.mhmm.stickman.dao.dao_impl.ItemsDAOImpl;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LootTest {

    private static ItemsDAOImpl itemsDAOMock = Mockito.mock(ItemsDAOImpl.class);
    private static List<Item> items;
    private static Weapon weapon;
    private static Armor armor;
    private Loot newLootParam;
    private Loot newLoot;

    @BeforeAll
    static void init() {
        items = new ArrayList<>();
        items.add(weapon = new Weapon());
        items.add(armor = new Armor());
        when(itemsDAOMock.getAll()).thenReturn(items);
        when(itemsDAOMock.findById(1L)).thenReturn(weapon);
        when(itemsDAOMock.findById(2L)).thenReturn(armor);
    }

    @BeforeEach
    void createLoot() {
        newLootParam = new Loot(2,3,itemsDAOMock);
        newLoot = new Loot();
    }

    @Test
    void testCreateLoot() {
        newLoot = new Loot();
        assertNotNull(newLoot);
    }

    @Test
    void testCreateLootWithParams() {
        assertNotNull(newLootParam);
    }

    @Test
    void testLootHasItems() {
        assertNotNull(newLootParam.getItems());
    }

    @Test
    void testPickUp() {
        Player player = new Player(2,2, "John");
        List<Item> lootItems = newLoot.getItems();
        newLoot.pickup(player);
        assertEquals(lootItems, player.getItems());
    }

    @RepeatedTest(20)
    void testGetGoldIfSet() {
        int gold = newLootParam.getGold();
        assertTrue(gold > 0 && gold < 11);
    }

    @Test
    void testGoldWithDefaultInstance() {
        int gold = newLoot.getGold();
        assertEquals(0, gold);
    }

    @Test
    void testAdd() {
        newLoot.add(armor);
        assertNotNull(newLoot.getItems());
    }

    @Test
    void testAddIsCorrect() {
        newLoot.add(armor);
        assertEquals(armor, newLoot.getItems().get(0));
    }

    @Test
    void testSetItemsDAO() {
        newLoot.setItemsDao(Mockito.mock(ItemsDAOImpl.class));
        assertNotEquals(itemsDAOMock, newLoot.getItemsList());
    }
}