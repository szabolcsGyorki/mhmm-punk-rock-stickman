package com.codecool.mhmm.stickman.GameObjects.Items;

public class Armor extends Item {

    private int healthIncrease;

    public Armor(String name, int value, int healthIncrease) {
        super(name, value);
        this.healthIncrease = healthIncrease;
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }
}
