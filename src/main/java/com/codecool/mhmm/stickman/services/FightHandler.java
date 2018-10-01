package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.characters.Character;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;

import java.util.Random;

public class FightHandler {

    private static FightHandler instance;

    public static FightHandler getInstance() {
        if (instance == null) {
            instance = new FightHandler();
        }
        return instance;
    }

    private FightHandler() {
    }

    public int getPlayerDamage(Player player) {
        int baseDamage = player.getStrength();
        Weapon weapon = player.getWeapon();
        if (weapon != null) {
            Random random = new Random();
            int minDmg = weapon.getMinDamage();
            int maxDmg = weapon.getMaxDamage();
            if (minDmg == maxDmg) {
                baseDamage += minDmg;
            } else {
                baseDamage += random.nextInt(maxDmg - minDmg) + minDmg;
            }
        }
        return baseDamage;
    }

    public boolean characterHits(Character character) {
        Random hit = new Random();
        return hit.nextInt(100) < character.getHitChance();
    }

    public boolean characterDodges(Character character) {
        Random hit = new Random();
        return hit.nextInt(100) < character.getDodgeChance();
    }
}
