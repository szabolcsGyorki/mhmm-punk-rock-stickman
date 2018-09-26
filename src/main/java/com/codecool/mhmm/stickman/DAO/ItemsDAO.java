package com.codecool.mhmm.stickman.DAO;

import com.codecool.mhmm.stickman.GameObjects.GameObjectType;
import com.codecool.mhmm.stickman.GameObjects.Items.Item;

import java.util.List;

public interface ItemsDAO {

    /**
     * get value, name
     * get specific Type fields
     * @param itemID
     */
    Item getItem(long itemID);

    /**
     * get value, name
     * get specific Type fields
     */
    Item getItem(String name);
    Item getRandomItem();

    void saveNewItem(Item item);

    List<Item> getAllItems();
    List<Item> getAllItems(GameObjectType type);
}
