package com.codecool.mhmm.stickman.DAO;

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

    void saveNewItem(Item item);

    /**
     * update specific item with this id
     */
    void updateItem(Item item, long id);

    /**
     * update every item with this name
     */
    void updateItem(Item item, String name);

    List<Item> getAllItems();
}
