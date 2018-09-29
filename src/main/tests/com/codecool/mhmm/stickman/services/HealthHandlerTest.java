package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Orc;
import com.codecool.mhmm.stickman.game_objects.items.Armor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthHandlerTest {

    private HealthHandler healthHandler;

    @BeforeEach
    void init() {
        healthHandler = new HealthHandler();
    }

    @Test
    void sanityCheck() {
        HealthHandler healthHandler = new HealthHandler();
        assertNotNull(healthHandler);
    }

    @Test
    void testDamagePlayer() {
        Player player = new Player(1,1, "john");
        player.setHitPoint(30);
        healthHandler.dealDamage(player, 10);
        assertEquals(20, player.getHitPoint());
    }

    @Test
    void testDamagEnemy() {
        Enemy enemy = new Orc(1,1,2);
        enemy.setHitPoint(30);
        healthHandler.dealDamage(enemy, 15);
        assertEquals(15, enemy.getHitPoint());
    }

    @Test
    void testCalculateDragonHealth() {
        int expectedHP = 120;
        int calculatedHP = healthHandler.calculateDragonHealth(1);
        assertEquals(expectedHP, calculatedHP);
    }

    @Test
    void testCalculateDragonHealthHigherLevel() {
        int expectedHP = 160;
        int calculatedHP = healthHandler.calculateDragonHealth(3);
        assertEquals(expectedHP, calculatedHP);
    }

    @Test
    void testCalculateOrcHealth() {
        int expectedHP = 20;
        int calculatedHP = healthHandler.calculateOrcHealth(1);
        assertEquals(expectedHP, calculatedHP);
    }

    @Test
    void testCalculateOrcHealthHigherLevel() {
        int expectedHP = 36;
        int calculatedHP = healthHandler.calculateOrcHealth(3);
        assertEquals(expectedHP, calculatedHP);
    }

    @Test
    void testCalculateSlimeHealth() {
        int expectedHP = 6;
        int calculatedHP = healthHandler.calculateSlimeHealth(1);
        assertEquals(expectedHP, calculatedHP);
    }

    @Test
    void testCalculateSlimeHealthHigherLevel() {
        int expectedHP = 18;
        int calculatedHP = healthHandler.calculateSlimeHealth(3);
        assertEquals(expectedHP, calculatedHP);
    }

    @Test
    void testCalculateSkeletonHealth() {
        int expectedHP = 6;
        int calculatedHP = healthHandler.calculateSkeletonHealth(1);
        assertEquals(expectedHP, calculatedHP);
    }

    @Test
    void testCalculateSkeletonHealthHigherLevel() {
        int expectedHP = 18;
        int calculatedHP = healthHandler.calculateSkeletonHealth(3);
        assertEquals(expectedHP, calculatedHP);
    }

    @Test
    void testPlayerIsDead() {
        Player player = new Player(1,1,"john");
        player.setHitPoint(0);
        assertTrue(healthHandler.characterIsDead(player));
    }

    @Test
    void testPlayerIsNotDead() {
        Player player = new Player(1,1,"john");
        player.setHitPoint(10);
        assertFalse(healthHandler.characterIsDead(player));
    }

    @Test
    void testEnemyIsDead() {
        Enemy enemy = new Orc(1,1,1);
        enemy.setHitPoint(0);
        assertTrue(healthHandler.characterIsDead(enemy));
    }

    @Test
    void testEnemyIsNotDead() {
        Enemy enemy = new Orc(1,1,1);
        enemy.setHitPoint(20);
        assertFalse(healthHandler.characterIsDead(enemy));
    }

    @Test
    void testArmorIncreasePlayerHealth() {
        Player player = new Player(1,1,"john");
        player.setHitPoint(20);
        Armor armor = new Armor("armor", 20, 20);
        healthHandler.increasePlayerHealthWithArmor(player, armor);
        player.setFullBody(armor);
        assertEquals(40, player.getHitPoint());
    }

    @Test
    void testArmorDecreasePlayerHealthIfNewValueIsLower() {
        Player player = new Player(1,1,"john");
        player.setHitPoint(20);
        Armor armor1 = new Armor("armor", 20, 20);
        healthHandler.increasePlayerHealthWithArmor(player, armor1);
        player.setFullBody(armor1);
        healthHandler.increasePlayerHealthWithArmor(player, new Armor("armor", 20, 10));
        assertEquals(30, player.getHitPoint());
    }

    @Test
    void testArmorChangeKillsPlayer() {
        Player player = new Player(1, 1, "kong");
        player.setHitPoint(20);
        player.setFullBody(new Armor("armor", 20, 50));
        assertTrue(healthHandler.armorChangeKillsPlayer(player, new Armor("armor", 20, 10)));
    }

    @Test
    void testArmorChangeKillsPlayerFalse() {
        Player player = new Player(1, 1, "kong");
        player.setHitPoint(20);
        player.setFullBody(new Armor("armor", 20, 10));
        assertFalse(healthHandler.armorChangeKillsPlayer(player, new Armor("armor", 20, 10)));
    }

    @Test
    void testDamageKillsCharacter() {
        Player player = new Player(1, 1, "kong");
        player.setHitPoint(20);
        assertTrue(healthHandler.damageKillsCharacter(player, 21));
    }

    @Test
    void testDamageKillsCharacterFalse() {
        Player player = new Player(1, 1, "kong");
        player.setHitPoint(20);
        assertFalse(healthHandler.damageKillsCharacter(player, 11));
    }

    @Test
    void testDamageKillsEnemy() {
        Enemy enemy = new Orc(1,1,1);
        enemy.setHitPoint(10);
        assertTrue(healthHandler.damageKillsCharacter(enemy, 21));
    }

    @Test
    void testDamageKillsEnemyFalse() {
        Enemy enemy = new Orc(1,1,1);
        enemy.setHitPoint(30);
        assertFalse(healthHandler.damageKillsCharacter(enemy, 21));
    }
}