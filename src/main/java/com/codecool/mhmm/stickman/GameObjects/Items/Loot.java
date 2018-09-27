package com.codecool.mhmm.stickman.GameObjects.Items;

import com.codecool.mhmm.stickman.DAO.DAOImpl.ItemsDAOImpl;
import com.codecool.mhmm.stickman.DAO.ItemsDAO;
import com.codecool.mhmm.stickman.GameObjects.GameObject;
import com.codecool.mhmm.stickman.GameObjects.GameObjectType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Loot extends Item {

    @ManyToMany
    private List<Item> loot = new ArrayList<>();

    @Transient
    private ItemsDAOImpl itemsList;

    private int gold;
    private Random random = new Random();

    public Loot(int X, int Y, ItemsDAOImpl ItemsDAO) {
        super("Loot",0);
        this.itemsList = ItemsDAO;
        this.X = X;
        this.Y = Y;
        this.type = GameObjectType.LOOT;
        this.gold = random.nextInt(10) + 1;
        add();
    }

    public void setItemsList(ItemsDAOImpl itemsList) {
        this.itemsList = itemsList;
    }

    public Loot() {
    }

    public void add() {
        int numberOfLoots = random.nextInt(3) + 1;
        fillUpLoot(numberOfLoots);
    }

    public void add(ArrayList<Item> items){
        loot = items;
    }

    private void fillUpLoot(int numberOfLoots) {
        for (int i = 0; i < numberOfLoots; i++) {
            Random random = new Random();
            int itemNumber = random.nextInt(itemsList.getAllItems().size());
            GameObject item = itemsList.getItem(itemNumber);
            if (item instanceof Item) {
                loot.add((Item) item);
            }
        }
    }

    public List<Item> getItems() {
        return new ArrayList<>(loot);
    }

    public int getGold() {
        return gold;
    }
}
