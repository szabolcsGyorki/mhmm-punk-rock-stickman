package com.codecool.mhmm.stickman.game_objects.characters.enemy;

import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import javax.persistence.Entity;

@Entity
public class Skeleton extends Enemy {
    public Skeleton(int X, int Y, int hitPoints, int level){
        super(X, Y, hitPoints, Math.round(level * (3/2)), level);
        this.dodgeChance = 25;
        this.type = GameObjectType.SKELETON;
    }

    public Skeleton() {
    }
}
