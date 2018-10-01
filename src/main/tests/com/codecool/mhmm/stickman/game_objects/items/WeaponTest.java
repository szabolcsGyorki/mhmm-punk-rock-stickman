package com.codecool.mhmm.stickman.game_objects.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    private Weapon weapon;

    @BeforeEach
    void createWeapon() {
        weapon = new Weapon("Mythical Sword", 20, 40, 50);
    }

    @Test
    void testCreateWeapon() {
        Weapon weapon1 = new Weapon();
        assertNotNull(weapon1);
    }

    @Test
    void testCreateWeaponWithParams() {
        Weapon weapon1 = new Weapon("weapon", 3, 1, 4);
        assertNotNull(weapon1);
    }

    @Test
    void testWeaponNameIsNotNull() {
        assertNotNull(weapon.getName());
    }

    @Test
    void testWeaponMinDamageIsCorrect() {
        assertEquals(40, weapon.getMinDamage());
    }

    @Test
    void testWeaponMaxDamageIsCorrect() {
        assertEquals(50, weapon.getMaxDamage());
    }
}