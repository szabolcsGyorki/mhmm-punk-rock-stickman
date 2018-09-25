package com.codecool.mhmm.stickman.GameObjects;

import javax.persistence.Entity;

@Entity
public class Wall extends GameObject {

    public Wall(int X, int Y, GameObjectType type){
        super(X,Y);
        this.type = type;
    }

    public Wall() {
    }
}
