package com.codecool.mhmm.stickman.GameObjects.Items;

import com.codecool.mhmm.stickman.GameObjects.GameObjectType;

import javax.persistence.Entity;

@Entity
public class Armor extends Item {

    private int healthIncrease;

    public Armor(String name, int value, int healthIncrease) {
        super(name, value);
        this.healthIncrease = healthIncrease;
        this.type = GameObjectType.ARMOR;
    }

    protected Armor() {
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }
}
