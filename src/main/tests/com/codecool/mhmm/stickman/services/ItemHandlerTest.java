package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemHandlerTest {

    private ItemHandler itemHandler;

    @BeforeEach
    void init() {
        itemHandler = new ItemHandler();
    }

    @Test
    void sanityCheck() {
        ItemHandler itemHandler = new ItemHandler();
        assertNotNull(itemHandler);
    }

    @Test
    void testAssignToCharacter() {
        Player player = new Player(1,1, "Jo");
        Weapon weapon = new Weapon("Sword", 20, 10, 20);
        itemHandler.assignToCharacter(player, weapon);
        assertEquals(weapon, player.getItems().get(0));
    }
}
