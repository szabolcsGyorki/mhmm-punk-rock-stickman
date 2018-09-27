package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.GameObjects.Characters.Enemy.Enemy;
import com.codecool.mhmm.stickman.GameObjects.Characters.Enemy.Orc;
import com.codecool.mhmm.stickman.GameObjects.Characters.Player;
import com.codecool.mhmm.stickman.GameObjects.GameObject;
import com.codecool.mhmm.stickman.GameObjects.GameObjectType;
import com.codecool.mhmm.stickman.Map.Level;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LevelDAOImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private static EntityManager em = emf.createEntityManager();
    private static LevelDAOImpl levelDAO = new LevelDAOImpl(em);

    private static Level level1;
    private static Level level2;
    private static Player player;
    private static Enemy enemy;

    @BeforeAll
    static void init() {
        level1 = new Level(4, 5, GameObjectType.WALL, GameObjectType.FLOOR);
        level2 = new Level(3, 4, GameObjectType.WALL, GameObjectType.FLOOR);

        level1.placePlayer(player = new Player(5, 10, "Tirion"));
        level1.addContent(enemy = new Orc(2,3, 1));

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(level1);
        for (GameObject object : level1.getMap()) {
            em.persist(object);
        }
        transaction.commit();
    }

    @Test
    void testInstanceCreated() {
        LevelDAOImpl dao = new LevelDAOImpl(em);
        assertNotNull(dao);
    }

    @Test
    void testGetLevel() {
        Level level = levelDAO.getLevel(1L);
        assertNotNull(level);
    }

    @Test
    void testGetLevelWidth() {
        Level level = levelDAO.getLevel(1L);
        assertEquals(4, level.getWIDTH());
    }

    @Test
    void testGetLevelHeight() {
        Level level = levelDAO.getLevel(1L);
        assertEquals(5, level.getHEIGHT());
    }

    @Test
    void testGetLevelFloor() {
        Level level = levelDAO.getLevel(1L);
        assertEquals(GameObjectType.FLOOR, level.getFloorImage());
    }

    @Test
    void testGetLevelContent() {
        assertNotNull(levelDAO.getLevelObjects(1L));
    }

    @Test
    void testGetLevelContentIsCorrect() {
        List<GameObject> expectedContent = new ArrayList<>();
        expectedContent.add(enemy);
        expectedContent.add(player);
        assertTrue(levelDAO.getLevelObjects(level1.getId()).containsAll(expectedContent));
    }

    @Test
    void testCreateNewLevel() {
        Level newLevel = new Level(2, 4, GameObjectType.WALL, GameObjectType.FLOOR);
        levelDAO.createNewLevel(newLevel);
        Level savedLevel = levelDAO.getLevel(newLevel.getId());
        em.remove(newLevel);
        assertNotNull(savedLevel);
    }

    @Test
    void testCreateNewLevelIsCorrect() {
        Level newLevel = new Level(2, 4, GameObjectType.WALL, GameObjectType.FLOOR);
        levelDAO.createNewLevel(newLevel);
        Level savedLevel = levelDAO.getLevel(newLevel.getId());
        em.remove(newLevel);
        assertEquals(newLevel, savedLevel);
    }
}