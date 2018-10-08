package com.codecool.mhmm.stickman.map;

import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import com.codecool.mhmm.stickman.game_objects.Wall;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Dragon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    private Level level;
    @BeforeEach
    void init() {
        level = new Level(10, 10, GameObjectType.WALL, GameObjectType.FLOOR);
    }

    @Test
    void sanityCheck() {
        Level level = new Level(10, 10, GameObjectType.WALL, GameObjectType.FLOOR);
        assertNotNull(level);
    }

    @Test
    void getId() {
        assertEquals(GameObjectType.FLOOR, level.getFloorImage());
    }

    @Test
    void getMap() {
        List<GameObject> expected = new ArrayList<>();
        Dragon dragon = new Dragon();
        expected.add(dragon);

        level.addContent(dragon);
        assertEquals(expected, level.getMap());
    }

    @Test
    void getHEIGHT() {
        assertEquals(10, level.getHEIGHT());
    }

    @Test
    void getWIDTH() {
        assertEquals(10, level.getWIDTH());
    }

    @Test
    void getFloorImage() {
        assertEquals(GameObjectType.FLOOR, level.getFloorImage());
    }

    @Test
    void getWallImage() {
        assertEquals(GameObjectType.WALL, level.getWallImage());
    }

    @Test
    void addContent() {
        level.addContent(new Dragon());
        assertNotNull(level.getMap());
    }
}