package com.codecool.mhmm.stickman.game_objects.items;

import com.codecool.mhmm.stickman.game_objects.GameObjectType;

import javax.persistence.Entity;

@Entity
public class Armor extends Item {

    private int healthIncrease;

    public Armor(String name, int value, int healthIncrease) {
        super(name, value);
        this.healthIncrease = healthIncrease;
        this.type = GameObjectType.ARMOR;
    }

    public Armor() {
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }
}
