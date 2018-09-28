package com.codecool.mhmm.stickman.game_objects.items;

import com.codecool.mhmm.stickman.game_objects.characters.*;
import com.codecool.mhmm.stickman.game_objects.GameObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name="Item.getAll", query="SELECT i FROM Item i")
public abstract class Item extends GameObject {

    private int value;
    private String name;

    @Transient
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