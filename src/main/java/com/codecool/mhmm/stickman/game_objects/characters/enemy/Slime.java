package com.codecool.mhmm.stickman.game_objects.characters.enemy;

import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import javax.persistence.Entity;
import java.util.Random;

@Entity
public class Slime extends Enemy {

    private int slimeSplashChanse = 10;

    public Slime(int X, int Y, int hitPoints, int level) {
        super(X, Y ,hitPoints, level, level);
        this.hitChance = 80;
        this.type = GameObjectType.SLIME;
    }

    public Slime() {
    }

    @Override
    public int getDamage() {
        Random attackType = new Random();
        int attackRoll = attackType.nextInt();
        if (attackRoll < slimeSplashChanse) {
            return Math.round(level * (10 / 16));
        } else {
            return super.getDamage();
        }
    }
}
