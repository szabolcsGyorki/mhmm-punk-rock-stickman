package com.codecool.mhmm.stickman.game_objects.items;

import com.codecool.mhmm.stickman.dao.dao_impl.ItemsDAOImpl;
import com.codecool.mhmm.stickman.game_objects.items.equipable.Armor;
import com.codecool.mhmm.stickman.game_objects.items.equipable.Weapon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
        newLootParam = new Loot(2,3);
        newLoot = new Loot();
    }

    @Test
    void testCreateLoot() {
        assertNotNull(newLoot);
    }

    @Test
    void testCreateLootWithParams() {
        assertNotNull(newLootParam);
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
    void testGetGold() {
        assertEquals(0, newLootParam.getGold());
    }

    @Test
    void testSetGold() {
        newLootParam.setGold(5);
        assertEquals(5, newLootParam.getGold());
    }
}