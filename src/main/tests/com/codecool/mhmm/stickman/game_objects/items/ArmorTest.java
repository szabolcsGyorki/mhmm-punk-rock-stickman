package com.codecool.mhmm.stickman.game_objects.items;

import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.items.equipable.Armor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {

    private Armor armor;

    @BeforeEach
    void createArmor() {
        armor = new Armor("Sacred Armor", 75, 50);
    }

    @Test
    void testCreateArmorWithParams() {
        Armor armor1 = new Armor("test", 23, 10);
        assertNotNull(armor1);
    }

    @Test
    void testCreatArmor() {
        Armor armor1 = new Armor();
        assertNotNull(armor1);
    }

    @Test
    void testArmorNameIsNotNull() {
        assertNotNull(armor.getName());
    }

    @Test
    void testArmorValueIsCorrect() {
        assertEquals(armor.getValue(), 75);
    }

    @Test
    void testArmorHealthIncreaseIsCorrect() {
        assertEquals(armor.getHealthIncrease(), 50);
    }

    @Test
    void testArmorAssignToCharacter() {
        Player player = new Player(0,0, "john");
        armor.assignToCharacter(player);
        assertNotNull(player.getItemById(armor.getId()));
    }
}