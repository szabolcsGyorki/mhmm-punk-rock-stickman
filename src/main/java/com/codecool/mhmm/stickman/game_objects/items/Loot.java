package com.codecool.mhmm.stickman.game_objects.items;

import com.codecool.mhmm.stickman.game_objects.GameObjectType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Loot extends Item {

    @ManyToMany
    @JoinTable(name = "loot_content")
    private List<Item> lootList = new ArrayList<>();

    private int gold;
    private Random random = new Random();

    public Loot(int X, int Y) {
        super("Loot", 0);
        this.X = X;
        this.Y = Y;
        this.type = GameObjectType.LOOT;
        this.gold = 0;
    }

    public Loot() {
    }

    public void add(Item item) {
        lootList.add(item);
    }

    public List<Item> getItems() {
        return new ArrayList<>(lootList);
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}