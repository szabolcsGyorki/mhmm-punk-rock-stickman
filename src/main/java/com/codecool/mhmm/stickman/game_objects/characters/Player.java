package com.codecool.mhmm.stickman.game_objects.characters;

import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import com.codecool.mhmm.stickman.game_objects.items.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@NamedQuery(name="Player.getAll", query="SELECT player FROM Player player")
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

    public Player(int X, int Y, String name) {
        super(X, Y, 30, 0);
        this.type = GameObjectType.MAIN_CHARACTER;
        strength = 3;
        agility = 3;
        intelligence = 3;
        this.name = name;
    }

    public Player() {
    }

    public void setFullBody(Armor fullBody) {
        this.fullBody = fullBody;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public int getDamage(){
        Random hit = new Random();
        if (hit.nextInt(100) < this.hitChance) {
            if (this.weapon == null)
                return strength;
            return this.weapon.dealDamage() + strength;
        }
        return 0;
    }

    void changeStrength(int changeAmount){
        this.strength += changeAmount;
    }

    boolean strengthTest(int testValue){
        return testValue<strength;
    }

    void changeAgility(int changeAmount){
        this.agility += changeAmount;
    }

    boolean agilityTest(int testValue){
        return testValue<agility;
    }

    void changeIntelligence(int changeAmount){
        this.intelligence += changeAmount;
    }

    boolean intelligenceTest(int testValue){
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

    public Item getItemById(long id) {
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

    public String getDisplayDamage() {
        if (this.weapon == null)
            return String.valueOf(strength);
        return String.valueOf(this.getWeapon().getMinDamage() + this.strength)
                + " - "
                + String.valueOf(this.getWeapon().getMaxDamage() + this.strength);
    }
}

