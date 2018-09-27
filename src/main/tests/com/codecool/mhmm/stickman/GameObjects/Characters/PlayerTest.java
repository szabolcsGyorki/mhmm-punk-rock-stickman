package com.codecool.mhmm.stickman.GameObjects.Characters;

import com.codecool.mhmm.stickman.GameObjects.Items.Armor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player playerWithParams;
    private Player player;

    @BeforeEach
    void init() {
        player = new Player();
        playerWithParams = new Player(2,2,"John");
    }

    @Test
    void testPlayerIsCreated() {
        Player player = new Player();
        assertNotNull(player);
    }

    @Test
    void testPlayerIsCreatedWithParams() {
        Player player = new Player(2,2, "John");
        assertNotNull(player);
    }

    @Test
    void testPlayerName() {
        assertEquals("John", playerWithParams.getName());
    }

    @Test
    void setArmor() {
        playerWithParams.setFullBody(new Armor());
        assertNotNull(playerWithParams.getFullBody());
    }
}