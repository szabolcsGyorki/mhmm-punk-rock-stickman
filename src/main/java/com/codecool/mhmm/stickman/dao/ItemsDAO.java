package com.codecool.mhmm.stickman.dao;

import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import com.codecool.mhmm.stickman.game_objects.items.Item;

import java.util.List;

public interface ItemsDAO extends BaseDAO {

    /**
     * @param name: name of the item
     * @return: an item object
     */
    Item getItemByName(String name);

    /**
     * @return: a random item from the DB
     */
    Item getRandomItem();

    /**
     * @param type: type of the weapon to return
     * @return: all items that belongs to the given type
     */
    List<Item> getAllItemsByType(GameObjectType type);
}
