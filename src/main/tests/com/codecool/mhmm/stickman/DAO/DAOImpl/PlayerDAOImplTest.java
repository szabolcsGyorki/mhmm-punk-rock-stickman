package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.GameObjects.Characters.Player;
import org.junit.jupiter.api.BeforeAll;
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

    @BeforeAll
    static void init() {
        Player player1 = new Player(1,1, "George");

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(player1);
        transaction.commit();
    }

    @Test
    void testInstanceCreated() {
        PlayerDAOImpl dao = new PlayerDAOImpl(em);
        assertNotNull(dao);
    }

    @Test
    void testGetPlayer() {
        Player player = playerDAO.getPlayer(1L);
        assertNotNull(player);
    }

    @Test
    void testGetPlayerName() {
        Player player = playerDAO.getPlayer(1L);
        assertEquals("George", player.getName());
    }
}