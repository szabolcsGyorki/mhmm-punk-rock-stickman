package com.codecool.mhmm.stickman.GameObjects.Characters.Enemy;

import com.codecool.mhmm.stickman.GameObjects.GameObjectType;

public class Dragon extends Enemy {
    private int fireBreathTimer;
    public Dragon(int X, int Y, int level) {
        super(X, Y, 100 + level*20, 15 + level*5, level);
        armor = 20 + level*3;
        dodgeChance = 15;
        hitChance = 90;
        fireBreathTimer = 2;
        this.type = GameObjectType.DRAGON;
    }

    @Override
    public int attack() {
        if (fireBreathTimer < 1) {
            fireBreathTimer = 3;
            return level*5 + super.attack();
        }
        fireBreathTimer -= 1;
        return super.attack();
    }
}

