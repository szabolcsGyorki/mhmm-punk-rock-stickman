package com.codecool.mhmm.stickman.game_objects;

import javax.persistence.Entity;

@Entity
public class Floor extends GameObject {

    public Floor(int X, int Y, GameObjectType type){
        super(X,Y);
        this.type = type;
    }

    public Floor() {
    }
}
