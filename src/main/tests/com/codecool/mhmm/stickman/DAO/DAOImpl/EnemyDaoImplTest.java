package com.codecool.mhmm.stickman.DAO.DAOImpl;


import com.codecool.mhmm.stickman.GameObjects.Characters.Enemy.*;
import com.codecool.mhmm.stickman.GameObjects.GameObjectType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.mhmm.stickman.GameObjects.GameObjectType.DRAGON;
import static org.junit.jupiter.api.Assertions.*;

class EnemyDaoImplTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private static EntityManager em = emf.createEntityManager();
    private static EnemyDAOImpl edi = new EnemyDAOImpl(em);
    private static Enemy enemy1;
    private static Enemy enemy2;
    private static Enemy enemy3;

    @BeforeAll
    static void init() {
        enemy1 = new Slime(1,2,1);
        enemy2 = new Orc(1,2,1);
        enemy3 = new Dragon(1,2,1);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(enemy1);
        em.persist(enemy2);
        em.persist(enemy3);
        transaction.commit();
    }

    @BeforeEach
    void clear() {
        em.clear();
    }

    @Test
    void testInstanceCreated() {
        EnemyDAOImpl dao = new EnemyDAOImpl(em);
        assertNotNull(dao);
    }

    @Test
    void testGetEnemyById() {
        Enemy enemy = edi.getEnemy(3);
        assertNotNull(enemy);
    }

    @Test
    void testGetEnemyByIdOne() {
        Enemy enemy = edi.getEnemy(1);
        assertEquals(1, enemy.getId());
    }

    @Test
    void testGetEnemyByIdThree() {
        Enemy enemy = edi.getEnemy(3);
        assertEquals(DRAGON, enemy.getType());
    }

    @Test
    void testGetALlEnemy() {
        List<Enemy> enemies = edi.getAllEnemy();
        assertNotNull(enemies);
    }

    @Test
    void testGetAllEnemyReturnsCorrectly() {
        List<Enemy> expected = new ArrayList<>();
        expected.add(edi.getEnemy(1L));
        expected.add(edi.getEnemy(2L));
        expected.add(edi.getEnemy(3L));

        List<Enemy> enemies = edi.getAllEnemy();
        assertTrue(enemies.containsAll(expected));
    }

    @Test
    void testUpdateEnemy() {
        edi.updateEnemy(enemy3, "hitPoint", 20);
        Enemy updatedEnemy = edi.getEnemy(enemy3.getId());
        assertEquals(20, updatedEnemy.getHitPoint());
    }

    @Test
    void testUpdateAllEnemies() {
        edi.updateEnemy(GameObjectType.ORC, "hitChance", 95);
        Enemy enemy = edi.getEnemy(enemy2.getId());
        assertEquals(95, enemy.getHitChance());
    }

    @Test
    void testSaveNewEnemy() {
        Enemy expectedEnemy = new Orc(5,6, 2);
        edi.saveNewEnemy(expectedEnemy);
        Enemy enemy = edi.getEnemy(expectedEnemy.getId());
        assertEquals(expectedEnemy, enemy);
    }

    @Test
    void testGetEnemiesByType() {
        List<Enemy> enemies = edi.getEnemiesByType(DRAGON);
        assertEquals(DRAGON, enemies.get(0).getType());
    }

    @AfterAll
    static void close() {
        em.close();
        emf.close();
    }
}