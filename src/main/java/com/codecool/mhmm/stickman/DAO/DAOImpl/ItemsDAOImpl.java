package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.DAO.ItemsDAO;
import com.codecool.mhmm.stickman.GameObjects.GameObjectType;
import com.codecool.mhmm.stickman.GameObjects.Items.Item;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Random;

public class ItemsDAOImpl extends BaseDaoImpl implements ItemsDAO {

    public ItemsDAOImpl(EntityManager em) {
        super(em);
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
        Random random = new Random();
        List<Item> items = getAll();
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
