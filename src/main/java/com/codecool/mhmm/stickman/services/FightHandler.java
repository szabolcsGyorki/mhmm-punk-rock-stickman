package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.characters.Character;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;

import java.util.Random;

public class FightHandler {

    private Random random;

    public FightHandler(Random random) {
        this.random = random;
    }

    public int getPlayerDamage(Player player) {
        int baseDamage = player.getStrength();
        Weapon weapon = player.getWeapon();
        if (weapon != null) {
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
        return random.nextInt(100) < character.getHitChance();
    }

    public boolean characterDodges(Character character) {
        return random.nextInt(100) < character.getDodgeChance();
    }
}
