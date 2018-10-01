package com.codecool.mhmm.stickman.game_objects.characters.enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DragonTest {

    private Dragon dragon;

    @BeforeEach
    void init() {
        dragon = new Dragon(1, 2, 20, 1);
    }

    @Test
    void sanityCheck() {
        Dragon dragon = new Dragon(1, 2, 10, 1);
        assertNotNull(dragon);
    }

    @Test
    void testBaseDamage() {
        dragon.setDamage(20);
        assertEquals(20, dragon.getDamage());
    }

    @Test
    void testFireBreath() {
        dragon.getDamage();
        dragon.getDamage();
        int breath = dragon.getDamage();
        assertEquals(25, breath);
    }

}