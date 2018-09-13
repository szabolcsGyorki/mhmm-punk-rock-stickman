package com.codecool.mhmm.stickman.GameObjects.Items;

import com.codecool.mhmm.stickman.GameObjects.Characters.Character;
import com.codecool.mhmm.stickman.GameObjects.GameObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Item extends GameObject {

    private int value;
    private int id;
    private static int nextId;
    private String name;
    private static final Logger logger = LoggerFactory.getLogger(Item.class);

    Item(String name, int value) {
        super(0,0);
        this.value = value;
        this.name = name;
        this.id = nextId++;
    }

    void assignToCharacter(Character character) {
        character.addItemToInventory(this);
        logger.info("'{}' added to inventory", this.name);
    }

    public int getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}