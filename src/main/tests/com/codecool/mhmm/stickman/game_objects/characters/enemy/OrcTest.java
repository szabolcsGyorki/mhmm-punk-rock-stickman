package com.codecool.mhmm.stickman.game_objects.characters.enemy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrcTest {

    @Test
    void sanityCheck() {
        Orc orc = new Orc(1, 1, 10, 1);
        assertNotNull(orc);
    }

    @Test
    void testBaseDamage() {
        Orc orc = new Orc(1, 2, 10, 1);
        assertEquals(5, orc.getDamage());
    }

    @Test
    void testSetDamage() {
        Orc orc = new Orc(1, 2, 10, 1);
        orc.setDamage(20);
        assertEquals(20, orc.getDamage());
    }

}