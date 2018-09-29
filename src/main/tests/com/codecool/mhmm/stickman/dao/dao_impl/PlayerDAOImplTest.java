package com.codecool.mhmm.stickman.dao.dao_impl;

import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDAOImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private static EntityManager em = emf.createEntityManager();
    private static PlayerDAOImpl playerDAO = new PlayerDAOImpl(em);

    private static Player player1;
    private static Weapon weapon1;

    @BeforeAll
    static void init() {
        player1 = new Player(1,1, "George");
        weapon1 = new Weapon("Unstoppable Force", 2500, 95, 125);
        player1.addItemToInventory(weapon1);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(player1);
        em.persist(weapon1);
        transaction.commit();
    }

    @BeforeEach
    void clear() {
        em.clear();
    }

    @Test
    void testInstanceCreated() {
        PlayerDAOImpl dao = new PlayerDAOImpl(em);
        assertNotNull(dao);
    }

    @Test
    void testGetPlayer() {
        Player player = playerDAO.findById(1L);
        assertNotNull(player);
    }

    @Test
    void testGetPlayerName() {
        Player player = playerDAO.findById(1L);
        assertEquals("George", player.getName());
    }

    @Test
    void testGetPlayerByName() {
        Player player = playerDAO.getPlayerByName("George");
        assertNotNull(player);
    }

    @Test
    void testGetPlayerByNameIsCorrect() {
        Player player = playerDAO.getPlayerByName("George");
        assertEquals("George", player.getName());
    }

    @Test
    void testSaveNewPlayer() {
        Player expectedPlayer = new Player(2,3,"Aramis");
        playerDAO.saveNew(expectedPlayer);
        Player player = playerDAO.findById(expectedPlayer.getId());
        em.remove(expectedPlayer);
        assertEquals(expectedPlayer, player);
    }

    @Test
    void testPlayerUpdate() {
        player1.place(2, 1);
        playerDAO.update(player1, "X", player1.getX());
        Player updatedPlayer = playerDAO.findById(player1.getId());
        assertEquals(2, updatedPlayer.getX());
    }
}