package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.dao.EnemyDAO;
import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.dao.dao_impl.EnemyDAOImpl;
import com.codecool.mhmm.stickman.dao.dao_impl.ItemsDAOImpl;
import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Slime;
import com.codecool.mhmm.stickman.game_objects.items.Loot;
import com.codecool.mhmm.stickman.map.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class LevelGeneratorTest {

    private LevelGenerator levelGenerator;
    private ItemsDAO itemsDAO = Mockito.mock(ItemsDAOImpl.class);
    private EnemyDAO enemyDAO = Mockito.mock(EnemyDAOImpl.class);

    private Enemy slime;
    private Loot loot;

    @BeforeEach
    void init() {
        levelGenerator = LevelGenerator.getInstance();
        slime = new Slime();
        loot = new Loot();

        when(enemyDAO.findById(anyLong())).thenReturn(slime);
        when(itemsDAO.findById(anyLong())).thenReturn(loot);

    }

    @Test
    void sanityCheck() {
        LevelGenerator levelGenerator = LevelGenerator.getInstance();
        assertNotNull(levelGenerator);
    }

    @Test
    void testLevelOne() {
        Level levelOne = levelGenerator.levelOne(itemsDAO, enemyDAO);
        assertNotNull(levelOne);
    }
}