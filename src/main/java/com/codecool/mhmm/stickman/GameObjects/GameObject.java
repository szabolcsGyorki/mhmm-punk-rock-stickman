package com.codecool.mhmm.stickman.GameObjects;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GameObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Enumerated(EnumType.STRING)
    protected GameObjectType type;

    protected int X;
    protected int Y;

    public GameObject(int X, int Y){
        this.X = X;
        this.Y = Y;
    }

    protected GameObject() {
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

    public long getId() {
        return id;
    }
}
