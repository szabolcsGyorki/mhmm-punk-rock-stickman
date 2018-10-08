package com.codecool.mhmm.stickman.game_objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    @Test
    void sanityCheck() {
        Floor floor = new Floor(1, 1, GameObjectType.FLOOR);
        assertNotNull(floor);
    }

}