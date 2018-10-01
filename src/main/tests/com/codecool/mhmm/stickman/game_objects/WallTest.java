package com.codecool.mhmm.stickman.game_objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    @Test
    void sanityCheck() {
        Wall wall = new Wall(1, 1, GameObjectType.WALL);
        assertNotNull(wall);
    }

}