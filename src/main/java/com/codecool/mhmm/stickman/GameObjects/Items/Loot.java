package com.codecool.mhmm.stickman.GameObjects.Items;

import com.codecool.mhmm.stickman.DAO.ItemsDAO;
import com.codecool.mhmm.stickman.GameObjects.GameObject;
import com.codecool.mhmm.stickman.GameObjects.GameObjectType;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Loot extends Item {

    private List<Item> loot = new ArrayList<>();
    private ItemsDao itemsList;
    private int gold;
    private Random random = new Random();

    public Loot();
    public Loot(int X, int Y, ItemsDAOImpl ItemsDAO) {
        super("Loot",0);
        this.itemsList = ItemsDAO;
        this.X = X;
        this.Y = Y;
        this.type = GameObjectType.LOOT;
        this.gold = random.nextInt(10) + 1;
        add();
    }

    public void add() {
        int numberOfLoots = random.nextInt(3) + 1;
        fillUpLoot(numberOfLoots);
    }

    public void add(List<Item> items){
        loot = items;
    }

    private void fillUpLoot(int numberOfLoots) {
        for (int i = 0; i < numberOfLoots; i++) {
            Random random = new Random();
            GameObject item = itemsList.getItem(random.nextInt(itemsList.getAllItems().size));
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
