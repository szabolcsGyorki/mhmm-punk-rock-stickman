package com.codecool.mhmm.stickman.GameObjects.Characters.Enemy;

import com.codecool.mhmm.stickman.GameObjects.Characters.Character;
import com.codecool.mhmm.stickman.GameObjects.Items.Loot;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Enemy extends Character {

    @ManyToMany
    private List<Loot> loot;

    Enemy(int X, int Y, int hitPoints, int damage, int level){
        super(X,Y,hitPoints,damage);
        this.level = level;
    }

    protected Enemy() {
    }

    public int attack(){
        return this.damage;
    }

}