package com.codecool.mhmm.stickman.dao.dao_impl;

import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import com.codecool.mhmm.stickman.game_objects.items.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class ItemsDAOImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private static EntityManager em = emf.createEntityManager();
    private Random random = Mockito.mock(Random.class);
    private ItemsDAOImpl itemsDAO = new ItemsDAOImpl(em, random);
    private EntityTransaction transaction = em.getTransaction();

    private Item armor;
    private long armorId;
    private Item weapon;
    private long weaponId;

    @BeforeEach
    void init() {
        em.clear();

        armor = new Armor("Sacred Armor", 800, 40);
        weapon = new Weapon("Sword", 20, 10, 20);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(armor);
        em.persist(weapon);
        transaction.commit();

        armorId = armor.getId();
        weaponId = weapon.getId();
    }

    @Test
    void testInstanceCreated() {
        ItemsDAOImpl dao = new ItemsDAOImpl(em, random);
        assertNotNull(dao);
    }

    @Test
    void testGetItemById() {
        Item item = itemsDAO.findById(armorId);
        assertEquals(armor, item);
    }

    @Test
    void testGetItemByIdTwo() {
        Item item = itemsDAO.findById(weaponId);
        assertEquals(weapon, item);
    }

    @Test
    void testGetItemByNameOne() {
        Item item = itemsDAO.getItemByName("Sacred Armor");
        assertEquals(armor, item);
    }

    @Test
    void testSaveNewItem() {
        Item item = new Weapon("Perdition Blade", 650, 34, 46);
        itemsDAO.saveNew(item);
        Item savedItem = itemsDAO.findById(item.getId());

        transaction.begin();
        em.remove(item);
        transaction.commit();

        assertEquals(item, savedItem);
    }

    @Test
    void testGetItemsByType() {
        List<Item> expected = new ArrayList<>();
        expected.add(armor);

        List<Item> items = itemsDAO.getAllItemsByType(GameObjectType.ARMOR);
        assertTrue(expected.containsAll(items) && items.containsAll(expected));
    }

    @Test
    void testGetAllItems() {
        List<Item> expected = new ArrayList<>();
        expected.add(armor);
        expected.add(weapon);

        List<Item> items = itemsDAO.getAll();
        assertTrue(expected.containsAll(items) && items.containsAll(expected));
    }

    @Test
    void testGetRandomItem() {
        when(random.nextInt(anyInt())).thenReturn(0);
        Item item = itemsDAO.getRandomItem();
        assertNotNull(item);
    }

    @AfterEach
    void removeEntries() {
        transaction.begin();
        em.remove(armor);
        em.remove(weapon);
        transaction.commit();
    }

    @AfterAll
    static void close() {
        em.close();
        emf.close();
    }
}