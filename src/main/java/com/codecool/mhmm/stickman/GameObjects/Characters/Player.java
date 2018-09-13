package com.codecool.mhmm.stickman.GameObjects.Characters;

import com.codecool.mhmm.stickman.GameObjects.GameObjectType;
import com.codecool.mhmm.stickman.GameObjects.Items.Armor;
import com.codecool.mhmm.stickman.GameObjects.Items.Item;
import com.codecool.mhmm.stickman.GameObjects.Items.Weapon;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Player extends Character {

    private String name;
    private int strength;
    private int agility;
    private int intelligence;

    @ManyToOne
    private Armor fullBody;

    @ManyToOne
    private Weapon weapon;

    @ManyToMany
    @JoinTable(name = "player_inventory")
    private List<Item> items = new ArrayList<>();


    public Player(int X, int Y) {
        super(X, Y, 30, 0);
        this.type = GameObjectType.MAIN_CHARACTER;
        strength = 3;
        agility = 3;
        intelligence = 3;
        name = "Roger the don";
    }

    public Player() {
    }

    public void setFullBody(Armor fullBody) {
        if (this.fullBody != null)
            this.hitPoint -= this.fullBody.getHealthIncrease();
        this.hitPoint += fullBody.getHealthIncrease();
        this.fullBody = fullBody;
        this.setHitPoint(this.hitPoint + fullBody.getHealthIncrease());
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int attack(){
        Random hit = new Random();
        if (hit.nextInt() < this.hitChanse) {
            if (this.weapon == null)
                return strength;
            return this.weapon.dealDamage() + strength;
        }
        return 0;
    }

    public void changeStrength (int changeAmount){
        this.strength += changeAmount;
    }

    public boolean strengthTest(int testValue){
        return testValue<strength;
    }

    public void changeAgility (int changeAmount){
        this.agility += changeAmount;
    }

    public boolean agilityTest(int testValue){
        return testValue<agility;
    }

    public void changeIntelligence (int changeAmount){
        this.intelligence += changeAmount;
    }

    public boolean intelligenceTest(int testValue){
        return testValue<intelligence;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getAgility() {
        return agility;
    }

    public int getStrength() {
        return strength;
    }

    public Armor getFullBody() {
        return fullBody;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void addItemToInventory(Item item) {
        items.add(item);
    }

    public Item getItemById(int id) {
        return items.stream()
                .filter(item -> item.getId() == id)
                .findFirst().orElse(null);
    }

    public List<Item> getItems() {
        return items;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

