package com.codecool.mhmm.stickman.dao.dao_impl;

import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import com.codecool.mhmm.stickman.game_objects.items.Item;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemsDAOImpl extends BaseDaoImpl implements ItemsDAO {

    private Random random;

    public ItemsDAOImpl(EntityManager em, Random random) {
        super(em);
        this.random = random;
    }

    @Override
    public Item findById(long id) {
        return em.find(Item.class, id);
    }

    @Override
    public List<Item> getAll() {
        TypedQuery<Item> query = em.createNamedQuery("Item.getAll", Item.class);
        return query.getResultList();
    }

    @Override
    public void update(Object entity, String field, Object value) {

    }

    @Override
    public void saveNew(Object item) {
        transaction.begin();
        em.persist(item);
        transaction.commit();
    }

    @Override
    public Item getItemByName(String name) {
        CriteriaQuery<Item> q = cb.createQuery(Item.class);
        Root<Item> itemRoot = q.from(Item.class);
        ParameterExpression p = cb.parameter(String.class);
        q.select(itemRoot).where(cb.equal(itemRoot.get("name"), p));
        TypedQuery<Item> query = em.createQuery(q);
        query.setParameter(p, name);
        return query.getSingleResult();
    }

    @Override
    public Item getRandomItem() {
        List<Item> items = new ArrayList<>();
        List<Item> armors = getAllItemsByType(GameObjectType.ARMOR);
        List<Item> weapons = getAllItemsByType(GameObjectType.WEAPON);
        items.addAll(armors);
        items.addAll(weapons);

        int id = random.nextInt(items.size());
        return items.get(id);
    }

    @Override
    public List<Item> getAllItemsByType(GameObjectType type) {
        CriteriaQuery<Item> criteriaQuery = cb.createQuery(Item.class);
        Root<Item> itemRoot = criteriaQuery.from(Item.class);
        ParameterExpression p = cb.parameter(GameObjectType.class);
        criteriaQuery.select(itemRoot).where(cb.equal(itemRoot.get("type"), p));
        TypedQuery<Item> query = em.createQuery(criteriaQuery);
        query.setParameter(p, type);
        return query.getResultList();
    }
}
