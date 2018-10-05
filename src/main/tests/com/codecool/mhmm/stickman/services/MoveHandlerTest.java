package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Slime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MoveHandlerTest {

    private Player player;
    private MoveHandler moveHandler;

    @BeforeEach
    void init() {
        moveHandler = MoveHandler.getInstance();
        player = new Player(2,2, "John");
    }

    @Test
    void sanityCheck() {
        MoveHandler moveHandler = MoveHandler.getInstance();
        assertNotNull(moveHandler);
    }

    @Test
    void moveLeft() {
        moveHandler.moveLeft(player);
        assertEquals(1, player.getX());
    }

    @Test
    void moveRight() {
        moveHandler.moveRight(player);
        assertEquals(3, player.getX());
    }

    @Test
    void moveUp() {
        moveHandler.moveUp(player);
        assertEquals(1, player.getY());
    }

    @Test
    void moveDown() {
        moveHandler.moveDown(player);
        assertEquals(3, player.getY());
    }

    @Test
    void testGetDestination() {
        Enemy expected = new Slime(3,3,10, 1, new Random());
        List<GameObject> objects = new ArrayList<>();
        objects.add(expected);
        assertEquals(expected, moveHandler.getDestination(3, 3, objects));
    }

    @Test
    void testGetEmptyDestination() {
        List<GameObject> objects = new ArrayList<>();
        objects.add(new Slime(4, 4, 10, 1, new Random()));
        assertNull(moveHandler.getDestination(3, 3, objects));
    }
}