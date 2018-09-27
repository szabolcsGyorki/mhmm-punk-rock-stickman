package com.codecool.mhmm.stickman.GameObjects.Items;

import com.codecool.mhmm.stickman.DAO.DAOImpl.ItemsDAOImpl;
import com.codecool.mhmm.stickman.GameObjects.Characters.Player;
import com.codecool.mhmm.stickman.GameObjects.GameObject;
import com.codecool.mhmm.stickman.GameObjects.GameObjectType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Loot extends Item {

    @ManyToMany
    @JoinTable(name = "loot_content")
    private List<Item> lootContent = new ArrayList<>();

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

    public void setItemsDao(ItemsDAOImpl itemsList) {
        this.itemsList = itemsList;
    }

    public void Pickup(Player player){
        for(Item item : lootContent)
            player.addItemToInventory(item);
    }

    public Loot() {
    }

    public void add() {
        int numberOfLoots = random.nextInt(3) + 1;
        fillUpLoot(numberOfLoots);
    }

    public void add(ArrayList<Item> items){
        lootContent = items;
    }

    private void fillUpLoot(int numberOfLoots) {
        List<Item> items = itemsList.getAllItems();
        for (int i = 0; i < numberOfLoots; i++) {
            long itemNumber = (long) random.nextInt(items.size()) + 1;
            Item item = itemsList.getItem(itemNumber);
            if (item != null) {
                lootContent.add(item);
            }
        }
    }

    public List<Item> getItems() {
        return new ArrayList<>(lootContent);
    }

    public int getGold() {
        return gold;
    }
}
