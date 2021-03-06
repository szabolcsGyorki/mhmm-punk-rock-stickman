package com.codecool.mhmm.stickman.game_objects.items.potion;

import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import com.codecool.mhmm.stickman.game_objects.items.Item;

import javax.persistence.Entity;

@Entity
public class HealthPotion extends Item {

    private int ammount;

    public HealthPotion(String name, int value, int ammount) {
        super(name, value);
        this.ammount = ammount;
        this.type = GameObjectType.HEALTHPOTION;
    }

    public HealthPotion() {
    }

    public int getAmmount() {
        return ammount;
    }
}
