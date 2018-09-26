package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.DAO.ItemsDAO;
import com.codecool.mhmm.stickman.GameObjects.Characters.Enemy.Enemy;
import com.codecool.mhmm.stickman.GameObjects.Items.Armor;
import com.codecool.mhmm.stickman.GameObjects.Items.Item;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class ItemsDAOImpl extends BaseDaoImpl implements ItemsDAO {

    ItemsDAOImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Item getItem(long itemID) {
        return em.find(Item.class, itemID);
    }

    @Override
    public Item getItem(String name) {
        CriteriaQuery<Item> q = cb.createQuery(Item.class);
        Root<Item> itemRoot = q.from(Item.class);
        ParameterExpression p = cb.parameter(String.class);
        q.select(itemRoot).where(cb.equal(itemRoot.get("name"), p));
        TypedQuery<Item> query = em.createQuery(q);
        query.setParameter(p, name);
        return query.getSingleResult();
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
