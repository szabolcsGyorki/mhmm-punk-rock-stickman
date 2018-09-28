package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.characters.Character;
import com.codecool.mhmm.stickman.game_objects.characters.Player;

class CharacterHealthHandler {

    void dealDamage(Character character, int damage) {
        int currentHealth = character.getHitPoint();
        int newHealth = currentHealth - damage;
        character.setHitPoint(newHealth);
    }
}
