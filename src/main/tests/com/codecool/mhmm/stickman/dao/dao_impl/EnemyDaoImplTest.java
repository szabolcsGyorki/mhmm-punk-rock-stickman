package com.codecool.mhmm.stickman.dao.dao_impl;


import com.codecool.mhmm.stickman.game_objects.characters.enemy.*;
import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codecool.mhmm.stickman.game_objects.GameObjectType.DRAGON;
import static org.junit.jupiter.api.Assertions.*;

class EnemyDaoImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private static EntityManager em = emf.createEntityManager();
    private static EnemyDAOImpl edi = new EnemyDAOImpl(em);
    private static EntityTransaction transaction = em.getTransaction();

    private Enemy enemy1;
    private long enemy1Id;
    private Enemy enemy2;
    private long enemy2Id;
    private Enemy enemy3;
    private long enemy3Id;

    @BeforeEach
    void init() {
        em.clear();

        enemy1 = new Slime(1,2, 5, 1, new Random());
        enemy2 = new Orc(1,2, 10, 1);
        enemy3 = new Dragon(1,2, 20, 1);

        transaction.begin();
        em.persist(enemy1);
        em.persist(enemy2);
        em.persist(enemy3);
        transaction.commit();

        enemy1Id = enemy1.getId();
        enemy2Id = enemy2.getId();
        enemy3Id = enemy3.getId();
    }

    @Test
    void testInstanceCreated() {
        EnemyDAOImpl dao = new EnemyDAOImpl(em);
        assertNotNull(dao);
    }

    @Test
    void testGetEnemyById() {
        Enemy enemy = edi.findById(enemy3Id);
        assertNotNull(enemy);
    }

    @Test
    void testGetEnemyByIdOne() {
        Enemy enemy = edi.findById(enemy1Id);
        assertEquals(enemy1, enemy);
    }

    @Test
    void testGetALlEnemy() {
        List<Enemy> expected = new ArrayList<>();
        expected.add(enemy1);
        expected.add(enemy2);
        expected.add(enemy3);

        List<Enemy> enemies = edi.getAll();
        assertTrue(expected.containsAll(enemies) && enemies.containsAll(expected));
    }

    @Test
    void testUpdateEnemy() {
        int expected = 40;
        edi.update(enemy3, "hitPoint", expected);
        Enemy updatedEnemy = edi.findById(enemy3.getId());
        assertEquals(expected, updatedEnemy.getHitPoint());
    }

    @Test
    void testUpdateAllEnemies() {
        edi.updateEnemiesByType(GameObjectType.ORC, "hitChance", 95);
        Enemy enemy = edi.findById(enemy2.getId());
        assertEquals(95, enemy.getHitChance());
    }

    @Test
    void testSaveNewEnemy() {
        Enemy expectedEnemy = new Orc(5,6,10, 2);
        edi.saveNew(expectedEnemy);
        Enemy enemy = edi.findById(expectedEnemy.getId());

        transaction.begin();
        em.remove(expectedEnemy);
        transaction.commit();

        assertEquals(expectedEnemy, enemy);
    }

    @Test
    void testGetEnemiesByType() {
        List<Enemy> enemies = edi.getEnemiesByType(DRAGON);
        assertEquals(DRAGON, enemies.get(0).getType());
    }

    @AfterEach
    void remove() {
        transaction.begin();
        em.remove(enemy1);
        em.remove(enemy2);
        em.remove(enemy3);
        transaction.commit();
    }

    @AfterAll
    static void close() {
        em.close();
        emf.close();
    }
}