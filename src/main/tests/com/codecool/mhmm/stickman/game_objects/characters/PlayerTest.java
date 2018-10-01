package com.codecool.mhmm.stickman.game_objects.characters;

import com.codecool.mhmm.stickman.game_objects.items.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;
import com.codecool.mhmm.stickman.services.FightHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PlayerTest {

    private Player playerWithParams;
    private Player player;

    @BeforeEach
    void init() {
        player = new Player();
        playerWithParams = new Player(2, 2, "John");
    }

    @Test
    void testPlayerIsCreated() {
        Player player = new Player();
        assertNotNull(player);
    }

    @Test
    void testPlayerIsCreatedWithParams() {
        Player player = new Player(2, 2, "John");
        assertNotNull(player);
    }

    @Test
    void testPlayerName() {
        assertEquals("John", playerWithParams.getName());
    }

    @Test
    void testSetName() {
        player.setName("Mike");
        assertEquals("Mike", player.getName());
    }

    @Test
    void testSetArmor() {
        Armor armor = new Armor();
        playerWithParams.setFullBody(armor);
        assertEquals(armor, playerWithParams.getFullBody());
    }

    @Test
    void testChangeArmor() {
        playerWithParams.setFullBody(new Armor("armor1", 20, 30));
        Armor armor = new Armor("armor1", 20, 10);
        playerWithParams.setFullBody(armor);
        assertEquals(armor, playerWithParams.getFullBody());
    }

    @Test
    void testSetWeapon() {
        playerWithParams.setWeapon(new Weapon());
        assertNotNull(playerWithParams.getWeapon());
    }

    @Test
    void testChangeStrenght() {
        player.changeStrength(10);
        assertEquals(10, player.getStrength());
    }

    @Test
    void testChangeAgility() {
        player.changeAgility(10);
        assertEquals(10, player.getAgility());
    }

    @Test
    void testChangeIntelligence() {
        player.changeIntelligence(10);
        assertEquals(10, player.getIntelligence());
    }

    @Test
    void testStrengthTestSuccess() {
        player.changeStrength(15);
        assertTrue(player.strengthTest(10));
    }

    @Test
    void testStrengthTestFail() {
        player.changeStrength(5);
        assertFalse(player.strengthTest(15));
    }

    @Test
    void testAgilityTestSuccess() {
        player.changeAgility(15);
        assertTrue(player.agilityTest(10));
    }

    @Test
    void testAgilityTestFail() {
        player.changeAgility(5);
        assertFalse(player.agilityTest(15));
    }

    @Test
    void testIntelligenceTestSuccess() {
        player.changeIntelligence(15);
        assertTrue(player.intelligenceTest(10));
    }

    @Test
    void testIntelligenceTestFail() {
        player.changeIntelligence(5);
        assertFalse(player.intelligenceTest(15));
    }

    @Test
    void testGetDodgeChance() {
        int dodgeChance = playerWithParams.getDodgeChance();
        assertEquals(0, dodgeChance);
    }

    @Test
    void setHitchance() {
        playerWithParams.setHitChance(50);
        assertEquals(50, playerWithParams.getHitChance());
    }

    @Test
    void testPlacePlayer() {
        int x = 2;
        int y = 2;
        player.place(x, y);
        assertTrue(x == player.getX() && y == player.getY());
    }

    @Test
    void testAddItemToInventory() {
        Weapon weapon = new Weapon();
        player.addItemToInventory(weapon);
        assertNotNull(player.getItems());
    }

    @Test
    void testAddItemToInventoryIsCorrect() {
        Weapon weapon = new Weapon("sword", 10, 2, 10);
        player.addItemToInventory(weapon);
        assertEquals("sword", player.getItems().get(0).getName());
    }

    @Test
    void testGetItemById() {
        Weapon weapon = new Weapon();
        weapon.setId(1);
        player.addItemToInventory(weapon);
        assertNotNull(player.getItemById(1));
    }

    @Test
    void testGetItemByIdIsCorrect() {
        Weapon weapon = new Weapon("sword", 10, 2, 10);
        weapon.setId(1);
        player.addItemToInventory(weapon);
        assertEquals("sword", player.getItemById(1).getName());
    }

    @Test
    void testDisplayDamageWithoutWeapon() {
        assertEquals("3", playerWithParams.getDisplayDamage());
    }

    @Test
    void testDisplayDamageWithWeapon() {
        playerWithParams.setWeapon(new Weapon("sword", 10, 2, 10));
        assertEquals("5 - 13", playerWithParams.getDisplayDamage());
    }

    @Test
    void testDisplayDamageWithoutWeaponAndDefaultInstance() {
        assertEquals("0", player.getDisplayDamage());
    }

    @Test
    void testDisplayDamageWithWeaponAndDefaultInstance() {
        player.setWeapon(new Weapon("sword", 10, 2, 10));
        assertEquals("2 - 10", player.getDisplayDamage());
    }

    @Test
    void testGetX() {
        assertEquals(2, playerWithParams.getX());
    }

    @Test
    void testGetY() {
        assertEquals(2, playerWithParams.getY());
    }
}