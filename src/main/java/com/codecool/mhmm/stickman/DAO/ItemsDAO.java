package com.codecool.mhmm.stickman.DAO;

import com.codecool.mhmm.stickman.GameObjects.Items.Item;

public interface ItemsDAO {

    /**
     * get value, name
     * get specific Type fields
     */
    Item getItem(int itemID);

    /**
     * get value, name
     * get specific Type fields
     */
    Item getItem(String name);

    void saveNewItem(Item item);

    /**
     * update specific item with this id
     */
    void updateNewItem(Item item, int id);

    /**
     * update every item with this name
     */
    void updateNewItem(Item item, String name);
}
