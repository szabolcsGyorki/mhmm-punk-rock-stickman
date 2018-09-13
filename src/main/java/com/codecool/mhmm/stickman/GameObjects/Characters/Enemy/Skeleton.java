package com.codecool.mhmm.stickman.GameObjects.Characters.Enemy;

import com.codecool.mhmm.stickman.GameObjects.GameObjectType;
import javax.persistence.Entity;

@Entity
public class Skeleton extends Enemy {
    public Skeleton(int X, int Y, int level){
        super(X,Y,6 * level,Math.round(level * (3/2)),level);
        this.dodgeChance = 25;
        this.type = GameObjectType.SKELETON;
    }

    public Skeleton() {
    }
}
