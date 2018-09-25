package com.codecool.mhmm.stickman.GameObjects;

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
