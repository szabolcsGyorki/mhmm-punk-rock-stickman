package com.codecool.mhmm.stickman.GameObjects.Items;

import com.codecool.mhmm.stickman.DAO.DAOImpl.ItemsDAOImpl;
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
        when(itemsDAOMock.getAllItems()).thenReturn(items);
        when(itemsDAOMock.getItem(1L)).thenReturn(weapon);
        when(itemsDAOMock.getItem(2L)).thenReturn(armor);
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

    }

}