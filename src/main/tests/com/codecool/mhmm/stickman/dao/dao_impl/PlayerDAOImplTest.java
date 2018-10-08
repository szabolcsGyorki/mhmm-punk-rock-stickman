package com.codecool.mhmm.stickman.dao.dao_impl;

import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.items.equipable.Weapon;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDAOImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private static EntityManager em = emf.createEntityManager();
    private static PlayerDAOImpl playerDAO = new PlayerDAOImpl(em);
    private static EntityTransaction transaction = em.getTransaction();

    private Player player;
    private long playerId;
    private Weapon weapon;

    @BeforeEach
    void init() {
        em.clear();

        player = new Player(1,1, "George");
        weapon = new Weapon("Unstoppable Force", 2500, 95, 125);
        player.addItemToInventory(weapon);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(player);
        em.persist(weapon);
        transaction.commit();

        playerId = player.getId();
    }

    @Test
    void testInstanceCreated() {
        PlayerDAOImpl dao = new PlayerDAOImpl(em);
        assertNotNull(dao);
    }

    @Test
    void testGetPlayer() {
        Player player = playerDAO.findById(playerId);
        assertNotNull(player);
    }

    @Test
    void testGetPlayerName() {
        Player player = playerDAO.findById(playerId);
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
        transaction.begin();
        em.remove(expectedPlayer);
        transaction.commit();
        assertEquals(expectedPlayer, player);
    }

    @Test
    void testPlayerUpdate() {
        int expected = 2;
        playerDAO.update(player, "X", expected);
        Player updatedPlayer = playerDAO.findById(player.getId());
        assertEquals(expected, updatedPlayer.getX());
    }

    @Test
    void testGetAll() {
        Player playerTwo = new Player();
        List<Player> expected = new ArrayList<>();
        expected.add(player);
        expected.add(playerTwo);

        playerDAO.saveNew(playerTwo);
        List<Player> savedPlayers = playerDAO.getAll();
        transaction.begin();
        em.remove(playerTwo);
        transaction.commit();

        assertEquals(expected, savedPlayers);
    }

    @AfterEach
    void removeEntries() {
        transaction.begin();
        em.remove(player);
        em.remove(weapon);
        transaction.commit();
    }

    @AfterAll
    static void close() {
        em.close();
        emf.close();
    }
}