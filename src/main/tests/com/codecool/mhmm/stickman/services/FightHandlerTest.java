package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class FightHandlerTest {

    private Random random = Mockito.mock(Random.class);
    private FightHandler fightHandler;
    private Player player;

    @BeforeEach
    void init() {
        fightHandler = new FightHandler(random);
        player = new Player();
    }

    @Test
    void sanityCheck() {
        FightHandler fightHandler = new FightHandler(random);
        assertNotNull(fightHandler);
    }

    @Test
    void testGetPlayerBaseDamage() {
        int expected = 3;
        player.changeStrength(expected);
        assertEquals(expected, fightHandler.getPlayerDamage(player));
    }

    @Test
    void testGetPlayerDamageWithWeapon() {
        int expected = 15;
        player.changeStrength(5);
        player.setWeapon(new Weapon("sword", 10, 10, 12));

        when(random.nextInt(anyInt())).thenReturn(0);

        assertEquals(expected, fightHandler.getPlayerDamage(player));
    }

    @Test
    void testGetPlayerDamageWithEqualMinMaxDmgWeapon() {
        int expected = 20;
        player.changeStrength(5);
        player.setWeapon(new Weapon("sword", 10, 15, 15));

        assertEquals(expected, fightHandler.getPlayerDamage(player));
    }

    @Test
    void testCharacterHits() {
        player.setHitChance(50);

        when(random.nextInt(anyInt())).thenReturn(10);

        assertTrue(fightHandler.characterHits(player));
    }

    @Test
    void testCharacterMisses() {
        player.setHitChance(50);

        when(random.nextInt(anyInt())).thenReturn(80);

        assertFalse(fightHandler.characterHits(player));
    }

    @Test
    void testCharacterDodges() {
        player.setDodgeChance(50);

        when(random.nextInt(anyInt())).thenReturn(10);

        assertTrue(fightHandler.characterDodges(player));
    }

    @Test
    void testCharacterNotDodges() {
        player.setDodgeChance(50);

        when(random.nextInt(anyInt())).thenReturn(80);

        assertFalse(fightHandler.characterDodges(player));
    }
}