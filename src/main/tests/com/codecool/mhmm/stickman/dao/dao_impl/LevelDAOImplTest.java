package com.codecool.mhmm.stickman.dao.dao_impl;

import com.codecool.mhmm.stickman.game_objects.characters.enemy.Dragon;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Orc;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import com.codecool.mhmm.stickman.map.Level;
import org.junit.jupiter.api.*;

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
    private static EntityTransaction transaction = em.getTransaction();

    private Level level1;
    private long level1Id;
    private Level level2;
    private long level2Id;

    @BeforeEach
    void init() {
        em.clear();

        level1 = new Level(4, 5, GameObjectType.WALL, GameObjectType.FLOOR);
        level2 = new Level(3, 4, GameObjectType.WALL, GameObjectType.FLOOR);

        level1.addContent(new Player(5, 10, "Tirion"));
        level1.addContent(new Orc(2,3, 10, 1));

        transaction.begin();
        em.persist(level1);
        for (GameObject object : level1.getMap()) {
            em.persist(object);
        }
        em.persist(level2);
        for (GameObject object : level2.getMap()) {
            em.persist(object);
        }
        transaction.commit();

        level1Id = level1.getId();
        level2Id = level2.getId();
    }

    @Test
    void testInstanceCreated() {
        LevelDAOImpl dao = new LevelDAOImpl(em);
        assertNotNull(dao);
    }

    @Test
    void testGetLevel() {
        Level level = levelDAO.findById(level1Id);
        assertNotNull(level);
    }

    @Test
    void testGetLevelWidth() {
        Level level = levelDAO.findById(level1Id);
        assertEquals(level1.getWIDTH(), level.getWIDTH());
    }

    @Test
    void testGetLevelHeight() {
        Level level = levelDAO.findById(level2Id);
        assertEquals(level2.getHEIGHT(), level.getHEIGHT());
    }

    @Test
    void testGetLevelFloor() {
        Level level = levelDAO.findById(level1Id);
        assertEquals(GameObjectType.FLOOR, level.getFloorImage());
    }

    @Test
    void testGetLevelContent() {
        assertNotNull(levelDAO.getLevelObjects(level1Id));
    }

    @Test
    void testGetLevelContentIsCorrect() {
        List<GameObject> content = levelDAO.getLevelObjects(level1.getId());
        Player player = (Player) content.stream()
                .filter(c -> c instanceof Player)
                .findFirst().orElse(null);
        Enemy enemy = (Enemy) content.stream()
                .filter(c -> c instanceof Enemy)
                .findFirst().orElse(null);
        assert player != null;
        assert enemy != null;
        assertTrue(player.getName().equals("Tirion") && enemy.getType() == GameObjectType.ORC);
    }

    @Test
    void testCreateNewLevel() {
        Level newLevel = new Level(2, 4, GameObjectType.WALL, GameObjectType.FLOOR);
        levelDAO.saveNew(newLevel);
        Level savedLevel = levelDAO.findById(newLevel.getId());
        transaction.begin();
        em.remove(newLevel);
        transaction.commit();
        assertNotNull(savedLevel);
    }

    @Test
    void testCreateNewLevelIsCorrect() {
        Level newLevel = new Level(2, 4, GameObjectType.WALL, GameObjectType.FLOOR);
        newLevel.addContent(new Dragon());
        levelDAO.saveNew(newLevel);
        Level savedLevel = levelDAO.findById(newLevel.getId());
        transaction.begin();
        em.remove(newLevel);
        transaction.commit();
        assertEquals(newLevel, savedLevel);
    }

    @Test
    void testGetAll() {
        List<Level> levelList = new ArrayList<>();
        levelList.add(level1);
        levelList.add(level2);

        List<Level> savedLevels = levelDAO.getAll();
        assertEquals(levelList, savedLevels);
    }

    @AfterEach
    void removeEntries() {
        transaction.begin();
        em.remove(level1);
        em.remove(level2);
        transaction.commit();
    }

    @AfterAll
    static void close() {
        em.close();
        emf.close();
    }
}