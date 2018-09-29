package com.codecool.mhmm.stickman.game_objects.characters.enemy;

import com.codecool.mhmm.stickman.game_objects.characters.Character;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.game_objects.items.Loot;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name="Enemy.getAll", query="SELECT e FROM Enemy e")
public abstract class Enemy extends Character {

    @ManyToOne
    private Loot loot;

    Enemy(int X, int Y, int hitPoints, int damage, int level){
        super(X,Y,hitPoints,damage);
        this.level = level;
    }

    protected Enemy() {
    }

    public int attack(){
        return this.damage;
    }

    public void addLoot(Item item) {
        loot.add(item);
    }
}