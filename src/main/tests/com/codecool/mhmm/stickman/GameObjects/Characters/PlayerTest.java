package com.codecool.mhmm.stickman.GameObjects.Characters;

import com.codecool.mhmm.stickman.GameObjects.Items.Armor;
import com.codecool.mhmm.stickman.GameObjects.Items.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
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
    void testSetName() {
        player.setName("Mike");
        assertEquals("Mike", player.getName());
    }

    @Test
    void testSetArmor() {
        playerWithParams.setFullBody(new Armor());
        assertNotNull(playerWithParams.getFullBody());
    }

    @Test
    void testArmorChangesHealth(){
        playerWithParams.setFullBody(new Armor("armor1", 20, 10));
        assertEquals(40, playerWithParams.getHitPoint());
    }

    @Test
    void testChangeArmor() {
        playerWithParams.setFullBody(new Armor("armor1", 20, 30));
        playerWithParams.setFullBody(new Armor("armor1", 20, 10));
        assertEquals(40, playerWithParams.getHitPoint());
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

    @RepeatedTest(20)
    void testAttackWithoutWeapon() {
        player.changeStrength(10);
        int damage = player.attack();
        assertTrue(damage == 0 || damage == 10);
    }

    @RepeatedTest(20)
    void testAttackWithWeapon() {
        player.changeStrength(10);
        player.setWeapon(new Weapon("Sword", 20, 10, 8));
        int damage = player.attack();
        assertTrue(damage == 0 || damage > 17 && damage < 21);
    }

    @Test
    void testStrengthTestSuccess() {
        player.changeStrength(15);
        assertTrue(player.strengthTest(10));
    }

    @Test
    void testStrengthTestFail() {
        player.changeStrength(5);
        assertFalse(player.strengthTest(10));
    }

    @Test
    void testAgilityTestSuccess() {
        player.changeAgility(15);
        assertTrue(player.agilityTest(10));
    }

    @Test
    void testAgilityTestFail() {
        player.changeAgility(5);
        assertFalse(player.agilityTest(10));
    }

    @Test
    void testIntelligenceTestSuccess() {
        player.changeIntelligence(15);
        assertTrue(player.intelligenceTest(10));
    }

    @Test
    void testIntelligenceTestFail() {
        player.changeIntelligence(5);
        assertFalse(player.intelligenceTest(10));
    }

    @Test
    void testAddItemToInventory() {
        Weapon weapon = new Weapon();
        player.addItemToInventory(weapon);
        assertNotNull(player.getItems());
    }

    @Test
    void testAddItemToInventoryIsCorrect() {
        Weapon weapon = new Weapon("sword", 10, 10, 2);
        player.addItemToInventory(weapon);
        assertEquals("sword", player.getItems().get(0).getName());
    }

    @Test
    void testGetItemById(){
        Weapon weapon = new Weapon();
        weapon.setId(1);
        player.addItemToInventory(weapon);
        assertNotNull(player.getItemById(1));
    }

    @Test
    void testGetItemByIdIsCorrect(){
        Weapon weapon = new Weapon("sword", 10, 10, 2);
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
        playerWithParams.setWeapon(new Weapon("sword", 10, 10, 2));
        assertEquals("5 - 13", playerWithParams.getDisplayDamage());
    }

    @Test
    void testDisplayDamageWithoutWeaponAndDefaultInstance() {
        assertEquals("0", player.getDisplayDamage());
    }

    @Test
    void testDisplayDamageWithWeaponAndDefaultInstance() {
        player.setWeapon(new Weapon("sword", 10, 10, 2));
        assertEquals("2 - 10", player.getDisplayDamage());
    }
}