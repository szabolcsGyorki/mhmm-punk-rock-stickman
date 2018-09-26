package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.DAO.ItemsDAO;
import com.codecool.mhmm.stickman.GameObjects.Items.Item;

import javax.persistence.EntityManager;

public class ItemsDAOImpl extends BaseDaoImpl implements ItemsDAO {

    ItemsDAOImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Item getItem(int itemID) {
        return null;
    }

    @Override
    public Item getItem(String name) {
        return null;
    }

    @Override
    public void saveNewItem(Item item) {

    }

    @Override
    public void updateNewItem(Item item, int id) {

    }

    @Override
    public void updateNewItem(Item item, String name) {

    }
}
