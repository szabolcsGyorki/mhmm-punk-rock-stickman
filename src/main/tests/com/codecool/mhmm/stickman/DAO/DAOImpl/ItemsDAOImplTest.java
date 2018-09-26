package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.GameObjects.Items.Armor;
import com.codecool.mhmm.stickman.GameObjects.Items.Item;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

    @Test
    void testGetItemById() {
        Item item = itemsDAO.getItem(1);
        assertNotNull(item);
    }

    @Test
    void testGetItemByIdTwo() {
        Item item = itemsDAO.getItem(2);
        assertNotNull(item);
    }

    @Test
    void testGetItemByIdReturnsCorrectItem() {
        Item item = itemsDAO.getItem(1);
        assertEquals(armor1, item);
    }

    @Test
    void testGetItemByNameOne() {
        Item item = itemsDAO.getItem("Sacred Armor");
        assertNotNull(item);
    }

    @Test
    void testGetItemByNameTwo() {
        Item item = itemsDAO.getItem("Mythic Armor");
        assertNotNull(item);
    }

    @Test
    void testGetItemByNameReturnsCorrectItem() {
        Item item = itemsDAO.getItem("Sacred Armor");
        assertEquals(armor1, item);
    }

    @AfterAll
    static void close() {
        em.close();
        emf.close();
    }
}