package com.codecool.mhmm.stickman.game_objects.characters.enemy;

import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import javax.persistence.Entity;

@Entity
public class Orc extends Enemy {

    public Orc(int X, int Y, int hitPoints, int level) {
        super(X, Y, hitPoints, 5 + 2*(level-1), level);
        this.armor = 2 + Math.round((1/2) * (level-1));
        this.type = GameObjectType.ORC;
    }

    public Orc() {
    }
}