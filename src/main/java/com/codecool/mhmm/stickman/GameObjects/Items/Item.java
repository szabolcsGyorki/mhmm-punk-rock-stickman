package com.codecool.mhmm.stickman.GameObjects.Items;

import com.codecool.mhmm.stickman.GameObjects.Characters.*;
import com.codecool.mhmm.stickman.GameObjects.GameObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item extends GameObject {

    private int value;
    private String name;
    private static final Logger logger = LoggerFactory.getLogger(Item.class);

    Item(String name, int value) {
        super(0,0);
        this.value = value;
        this.name = name;
    }

    protected Item() {
    }

    void assignToCharacter(Player player) {
        player.addItemToInventory(this);
        logger.info("'{}' added to inventory", this.name);
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}