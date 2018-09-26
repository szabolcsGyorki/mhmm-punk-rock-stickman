package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.GameObjects.Items.Armor;
import com.codecool.mhmm.stickman.GameObjects.Items.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class ItemsDAOImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private static EntityManager em = emf.createEntityManager();
    private static ItemsDAOImpl itemsDAO = new ItemsDAOImpl(em);

    private static Item armor1;

    @BeforeAll
    static void init() {
        armor1 = new Armor("Sacred Armor", 800, 40);
    }

    @Test
    void testGetItemById() {
        Item item = itemsDAO.getItem(1);
        assertNotNull(item);
    }

    @Test
    void testGetItemByIdReturnsCorrectItem() {
        Item item = itemsDAO.getItem(1);
        assertEquals(armor1, item);
    }


}