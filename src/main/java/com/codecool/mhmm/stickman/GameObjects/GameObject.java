package com.codecool.mhmm.stickman.GameObjects;

public abstract class GameObject {
    protected GameObjectType type;
    protected int X;
    protected int Y;

    public GameObject(int X, int Y){
        this.X = X;
        this.Y = Y;
    }
    public int getX() {
        return X;
    }

    public GameObjectType getType() {
        return type;
    }

    public int getY() {
        return Y;
    }

    public void place(int X, int Y){
        this.X = X;
        this.Y = Y;
    }
}
