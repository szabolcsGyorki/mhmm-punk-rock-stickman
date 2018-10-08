package com.codecool.mhmm.stickman.game_objects.characters.enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SlimeTest {

    private Slime slime;
    private static Random attackType = Mockito.mock(Random.class);


    @BeforeEach
    void init() {
        slime = new Slime(1, 1, 10, 5, attackType);
    }

    @Test
    void sanityCheck() {
        Slime slime = new Slime(1, 1, 10, 1, attackType);
        assertNotNull(slime);
    }

    @Test
    void testBaseDamage() {
        when(attackType.nextInt(anyInt())).thenReturn(51);
        int damage = slime.getDamage();
        assertEquals(5, damage);
    }

    @Test
    void testSplashDamage() {
        when(attackType.nextInt(100)).thenReturn(5);
        int damage = slime.getDamage();
        assertEquals(10, damage);
    }
}