package com.codecool.mhmm.stickman.dao.dao_impl;

import com.codecool.mhmm.stickman.dao.EnemyDAO;
import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.map.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class EnemyDAOImpl extends BaseDaoImpl implements EnemyDAO {

    public EnemyDAOImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Enemy findById(long id) {
        return em.find(Enemy.class, id);
    }

    @Override
    public List<Enemy> getAll() {
        TypedQuery<Enemy> query = em.createNamedQuery("Enemy.getAll", Enemy.class);
        return query.getResultList();
    }

    @Override
    public void update(Object enemy, String field, Object value) {
        transaction.begin();
        CriteriaUpdate<Enemy> update = cb.createCriteriaUpdate(Enemy.class);
        Root<Enemy> enemyRoot = update.from(Enemy.class);
        update.set(field, value)
                .where(cb.equal(enemyRoot.get("id"), ((Enemy)enemy).getId()));
        Query query = em.createQuery(update);
        query.executeUpdate();
        em.refresh(enemy);
        transaction.commit();

    }

    @Override
    public void saveNew(Object enemy) {
        transaction.begin();
        em.persist(enemy);
        transaction.commit();
    }

    @Override
    public <T> void updateEnemiesByType(GameObjectType enemyType, String field, T value) {
        transaction.begin();
        CriteriaUpdate<Enemy> update = cb.createCriteriaUpdate(Enemy.class);
        Root<Enemy> enemyRoot = update.from(Enemy.class);
        update.set(field, value)
                .where(cb.equal(enemyRoot.get("type"), enemyType));
        Query query = em.createQuery(update);
        query.executeUpdate();
        getEnemiesByType(enemyType).forEach(em::refresh);
        transaction.commit();
    }

    @Override
    public List<Enemy> getEnemiesByType(GameObjectType gameObjectType) {
        CriteriaQuery<Enemy> q = cb.createQuery(Enemy.class);
        Root<Enemy> enemy = q.from(Enemy.class);
        ParameterExpression p = cb.parameter(long.class);
        q.select(enemy).where(cb.equal(enemy.get("type"), p));
        TypedQuery<Enemy> query = em.createQuery(q);
        query.setParameter(p, gameObjectType);
        return query.getResultList();
    }
}
