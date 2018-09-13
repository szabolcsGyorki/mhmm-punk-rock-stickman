package com.codecool.mhmm.stickman.GameObjects.Characters;

import com.codecool.mhmm.stickman.GameObjects.GameObject;
import com.codecool.mhmm.stickman.GameObjects.GameObjectType;
import com.codecool.mhmm.stickman.GameObjects.Items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Character extends GameObject {
    protected int level;
    int hitPoint;
    protected int damage;
    protected int dodgeChanse;
    protected int armor;
    protected int hitChanse;



    private List<Item> items = new ArrayList<>();
    protected GameObjectType type;

    public Character(int X, int Y, int hitPoint, int damage) {
        super(X, Y);
        this.hitPoint = hitPoint;
        this.damage = damage;
        this.dodgeChanse = 0;
        this.armor = 0;
        this.hitChanse = 100;
    }

    public void takeDamage(int damageAmount){
        Random dodge = new Random();
        if (dodge.nextInt(100)>this.dodgeChanse)
            this.hitPoint -= damageAmount;
    }

    public GameObjectType getType(){
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void addItemToInventory(Item item) {
        items.add(item);
    }

    public Item getItemById(int id) {
        return items.stream()
                .filter(item -> item.getId() == id)
                .findFirst().orElse(null);
    }

    void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public List<Item> getItems() {
        return items;
    }

}
