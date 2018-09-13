package com.codecool.mhmm.stickman.GameObjects.Characters.Enemy;

import com.codecool.mhmm.stickman.GameObjects.GameObjectType;
import javax.persistence.Entity;

@Entity
public class Orc extends Enemy {

    public Orc(int X, int Y, int level) {
        super(X, Y, 20 + 8*(level-1), 5 + 2*(level-1), level);
        this.armor = 2 + Math.round((1/2) * (level-1));
        this.type = GameObjectType.ORC;
    }

    public Orc() {
    }
}