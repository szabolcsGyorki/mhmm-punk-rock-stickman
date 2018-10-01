package com.codecool.mhmm.stickman.game_objects.characters.enemy;

import com.codecool.mhmm.stickman.game_objects.characters.Character;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name="Enemy.getAll", query="SELECT e FROM Enemy e")
public abstract class Enemy extends Character {

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    int damage;

    Enemy(int X, int Y, int hitPoints, int damage, int level){
        super(X,Y,hitPoints);
        this.level = level;
        this.damage = damage;
    }

    protected Enemy() {
    }
}