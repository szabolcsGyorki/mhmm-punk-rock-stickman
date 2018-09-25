package com.codecool.mhmm.stickman.GameObjects.Characters.Enemy;

import com.codecool.mhmm.stickman.GameObjects.GameObjectType;

import java.util.Random;

public class Slime extends com.codecool.mhmm.stickman.GameObjects.Characters.Enemy.Enemy {
    private int slimeSplashChanse = 10;

    public Slime(int X, int Y, int level) {
        super(X,Y,12 * level/2,level,level);
        this.hitChance = 80;
        this.type = GameObjectType.SLIME;
    }

    @Override
    public int attack() {
        Random attackType = new Random();
        int attackRoll = attackType.nextInt();
        if (attackRoll < slimeSplashChanse) {
            return Math.round(level * (10 / 16));
        } else {
            return super.attack();
        }
    }
}
