package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.DAO.ItemsDAO;
import com.codecool.mhmm.stickman.GameObjects.Characters.Enemy.Enemy;
import com.codecool.mhmm.stickman.GameObjects.Items.Armor;
import com.codecool.mhmm.stickman.GameObjects.Items.Item;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ItemsDAOImpl extends BaseDaoImpl implements ItemsDAO {

    public ItemsDAOImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Item getItem(long itemID) {
        return new Armor("Armor", 3, 20);
    }

    @Override
    public Item getItem(String name) {
        return null;
    }

    @Override
    public void saveNewItem(Item item) {

    }

    @Override
    public void updateItem(Item item, long id) {

    }

    @Override
    public void updateItem(Item item, String name) {

    }

    @Override
    public List<Item> getAllItems() {
        TypedQuery<Item> query = em.createNamedQuery("Item.getAll", Item.class);
        return query.getResultList();
    }
}
