package com.codecool.mhmm.stickman.game_objects.characters;

import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Random;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Character extends GameObject {

    protected int level;
    int hitPoint;
    protected int damage;
    protected int dodgeChance;
    protected int armor;
    protected int hitChance;
    protected GameObjectType type;

    public Character(int X, int Y, int hitPoint, int damage) {
        super(X, Y);
        this.hitPoint = hitPoint;
        this.damage = damage;
        this.dodgeChance = 0;
        this.armor = 0;
        this.hitChance = 100;
    }

    protected Character() {
    }

    public void takeDamage(int damageAmount){
        Random dodge = new Random();
        if (dodge.nextInt(100)>this.dodgeChance)
            this.hitPoint -= damageAmount;
    }

    public GameObjectType getType(){
        return type;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public int getHitChance() {
        return this.hitChance;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }
}
