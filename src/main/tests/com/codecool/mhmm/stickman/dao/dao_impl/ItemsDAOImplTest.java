package com.codecool.mhmm.stickman.dao.dao_impl;

import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import com.codecool.mhmm.stickman.game_objects.items.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemsDAOImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private static EntityManager em = emf.createEntityManager();
    private static ItemsDAOImpl itemsDAO = new ItemsDAOImpl(em);

    private static Item armor1;
    private static Item armor2;

    @BeforeAll
    static void init() {
        armor1 = new Armor("Sacred Armor", 800, 40);
        armor2 = new Armor("Mythic Armor", 1000, 60);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(armor1);
        em.persist(armor2);
        transaction.commit();
    }

    @BeforeEach
    void clear() {
        em.clear();
    }

    @Test
    void testInstanceCreated() {
        ItemsDAOImpl dao = new ItemsDAOImpl(em);
        assertNotNull(dao);
    }

    @Test
    void testGetItemById() {
        Item item = itemsDAO.findById(1);
        assertNotNull(item);
    }

    @Test
    void testGetItemByIdTwo() {
        Item item = itemsDAO.findById(2);
        assertNotNull(item);
    }

    @Test
    void testGetItemByIdReturnsCorrectItem() {
        Item item = itemsDAO.findById(1);
        assertEquals("Sacred Armor", item.getName());
    }

    @Test
    void testGetItemByNameOne() {
        Item item = itemsDAO.getItemByName("Sacred Armor");
        assertNotNull(item);
    }

    @Test
    void testGetItemByNameTwo() {
        Item item = itemsDAO.getItemByName("Mythic Armor");
        assertNotNull(item);
    }

    @Test
    void testGetItemByNameReturnsCorrectItem() {
        Item item = itemsDAO.getItemByName("Sacred Armor");
        assertEquals("Sacred Armor", item.getName());
    }

    @Test
    void testSaveNewItem() {
        Item item = new Weapon("Perdition Blade", 650, 46, 34);
        itemsDAO.saveNew(item);
        Item savedItem = itemsDAO.findById(item.getId());
        assertEquals(item, savedItem);
        em.remove(item);
    }

    @Test
    void testGetItemsByType() {
        List<Item> items = itemsDAO.getAllItemsByType(GameObjectType.ARMOR);
        assertNotNull(items);
    }

    @Test
    void testGetItemsByTypeReturnsCorrectItems() {
        List<Item> expectedItems = new ArrayList<>();
        expectedItems.add(itemsDAO.findById(1L));
        expectedItems.add(itemsDAO.findById(2L));

        List<Item> items = itemsDAO.getAllItemsByType(GameObjectType.ARMOR);
        assertTrue(expectedItems.containsAll(items));
    }

    @Test
    void testGetAllItems() {
        List<Item> items = itemsDAO.getAll();
        assertNotNull(items);
    }

    @Test
    void testGetAllItemsReturnsCorrectly() {
        List<Item> expectedItems = new ArrayList<>();
        expectedItems.add(itemsDAO.findById(1L));
        expectedItems.add(itemsDAO.findById(2L));

        List<Item> items = itemsDAO.getAll();
        assertTrue(expectedItems.containsAll(items));
    }

    @RepeatedTest(20)
    void testGetRandomItem() {
        Item item = itemsDAO.getRandomItem();
        assertNotNull(item);
    }

    @AfterAll
    static void close() {
        em.close();
        emf.close();
    }
}