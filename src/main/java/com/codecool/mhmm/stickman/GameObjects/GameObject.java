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

    @Column(name = "coordinate_x")
    protected int X;

    @Column(name = "coordinate_y")
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

    public void setId(long id) {
        this.id = id;
    }
}
