package com.codecool.mhmm.stickman.GameObjects;

public class Wall extends GameObject {

    public Wall(int X, int Y, GameObjectType type){
        super(X,Y);
        this.type = type;
    }
}
