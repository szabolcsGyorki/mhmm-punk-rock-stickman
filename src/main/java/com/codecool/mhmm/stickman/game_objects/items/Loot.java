package com.codecool.mhmm.stickman.game_objects.items;

import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.dao.dao_impl.ItemsDAOImpl;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.GameObjectType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Loot extends Item {

    @ManyToMany
    @JoinTable(name = "loot_content")
    private List<Item> lootList = new ArrayList<>();

    @Transient
    private ItemsDAO itemsList;

    private int gold;
    private Random random = new Random();

    public Loot(int X, int Y, ItemsDAO ItemsDAO) {
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

    public void pickup(Player player){
        for(Item item : lootList)
            player.addItemToInventory(item);
    }

    public Loot() {
    }

    public void add() {
        int numberOfLoots = random.nextInt(3) + 1;
        fillUpLoot(numberOfLoots);
    }

    public void add(Item item){
        lootList.add(item);
    }

    private void fillUpLoot(int numberOfLoots) {
        List items = itemsList.getAll();
        for (int i = 0; i < numberOfLoots; i++) {
            long itemNumber = (long) random.nextInt(items.size()) + 1;
            Item item = (Item) itemsList.findById(itemNumber);
            if (item != null) {
                lootList.add(item);
            }
        }
    }

    public List<Item> getItems() {
        return new ArrayList<>(lootList);
    }

    public int getGold() {
        return gold;
    }

    public ItemsDAO getItemsList() {
        return itemsList;
    }
}
