package com.codecool.mhmm.stickman.GameObjects.Characters;

import com.codecool.mhmm.stickman.GameObjects.GameObjectType;
import com.codecool.mhmm.stickman.GameObjects.Items.Armor;
import com.codecool.mhmm.stickman.GameObjects.Items.Weapon;

import java.util.Random;

public class Player extends Character {
    private int strength;
    private int agility;
    private int intelligence;
    private Armor fullBody;
    private Weapon weapon;
    public static int X;
    public static int Y;

    public Player(int X, int Y) {
        super(X, Y, 30, 0);
        this.type = GameObjectType.MAIN_CHARACTER;
        strength = 3;
        agility = 3;
        intelligence = 3;
        Player.X = X;
        Player.Y = Y;
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

}

