package com.codecool.mhmm.stickman.game_objects.characters.enemy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkeletonTest {

    @Test
    void sanityCheck() {
        Skeleton skeleton = new Skeleton(1, 2, 10, 1);
        assertNotNull(skeleton);
    }

    @Test
    void testBaseDamage() {
        Skeleton skeleton = new Skeleton(1, 1, 20, 2);
        assertEquals(2, skeleton.getDamage());
    }

    @Test
    void testSetDamage() {
        Skeleton skeleton = new Skeleton(1, 1, 20, 2);
        skeleton.setDamage(20);
        assertEquals(20, skeleton.getDamage());
    }
}