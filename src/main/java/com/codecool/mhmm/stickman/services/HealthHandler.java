package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.characters.Character;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.items.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Item;

class HealthHandler {

    void dealDamage(Character character, int damage) {
        int currentHealth = character.getHitPoint();
        int newHealth = currentHealth - damage;
        character.setHitPoint(newHealth);
    }

    int calculateDragonHealth(int level) {
        return 100 + level*20;
    }

    int calculateOrcHealth(int level) {
        return 20 + 8*(level-1);
    }

    int calculateSlimeHealth(int level) {
        return 12 * level/2;
    }

    int calculateSkeletonHealth(int level) {
        return 6*level;
    }

    boolean characterIsDead(Character character) {
        return character.getHitPoint() <= 0;
    }

    private int getIncreaseHealth(Character character, Armor item) {
        int increaseHealth = item.getHealthIncrease();
        if (character instanceof Player) {
            Armor armor = ((Player) character).getFullBody();
            increaseHealth -= (armor != null) ? armor.getHealthIncrease() : 0;
        }
        return increaseHealth;
    }

    void increaseHealth(Character character, Item item) {
        if (item instanceof Armor) {
            int increaseHealth = getIncreaseHealth(character, (Armor) item);
            character.setHitPoint(character.getHitPoint() + increaseHealth);
        }
    }

    boolean characterWillDie(Character character, Item item) {
        int increaseHealth = 0;
        if (item instanceof Armor) {
            increaseHealth = getIncreaseHealth(character, (Armor) item);
        }
        return character.getHitPoint() + increaseHealth <= 0;
    }

    boolean damageKillsCharacter(Character character, int damage) {
        return (character.getHitPoint() - damage) < 1;
    }
}
