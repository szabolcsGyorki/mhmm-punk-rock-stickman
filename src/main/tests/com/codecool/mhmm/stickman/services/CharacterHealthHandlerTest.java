package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Dragon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterHealthHandlerTest {

    private CharacterHealthHandler healthHandler;

    @BeforeEach
    void init() {
        healthHandler = new CharacterHealthHandler();
    }

    @Test
    void sanityCheck() {
        CharacterHealthHandler healthHandler = new CharacterHealthHandler();
        assertNotNull(healthHandler);
    }

    @Test
    void testDamagePlayer() {
        Player player = new Player(1,1, "john");
        player.setHitPoint(30);
        healthHandler.dealDamage(player, 10);
        assertEquals(20, player.getHitPoint());
    }
}