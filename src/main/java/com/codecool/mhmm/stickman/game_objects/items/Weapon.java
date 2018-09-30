package com.codecool.mhmm.stickman.game_objects.items;

import com.codecool.mhmm.stickman.game_objects.GameObjectType;

import javax.persistence.Entity;
import java.util.Random;

@Entity
public class Weapon extends Item {

    private int maxDamage;
    private int minDamage;

    public Weapon(String name, int value, int minDamage, int maxDamage) {
        super(name, value);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.type = GameObjectType.WEAPON;
    }

    public Weapon() {
    }

    public int dealDamage() {
        Random random = new Random();
        return random.nextInt(this.maxDamage - this.minDamage) + this.minDamage;
    }

    public int getMinDamage() {
        return this.minDamage;
    }

    public int getMaxDamage() {
        return this.maxDamage;
    }
}
