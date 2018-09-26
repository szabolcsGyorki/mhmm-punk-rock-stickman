package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.DAO.EnemyDao;
import com.codecool.mhmm.stickman.GameObjects.Characters.Enemy.Enemy;
import com.codecool.mhmm.stickman.GameObjects.GameObjectType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class EnemyDaoImpl extends BaseDaoImpl implements EnemyDao {

    EnemyDaoImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Enemy> getAllEnemy() {
        TypedQuery<Enemy> query = em.createNamedQuery("Enemy.getAll", Enemy.class);
        return query.getResultList();
    }

    @Override
    public Enemy getEnemy(long id) {
        CriteriaQuery<Enemy> q = cb.createQuery(Enemy.class);
        Root<Enemy> enemy = q.from(Enemy.class);
        ParameterExpression p = cb.parameter(long.class);
        q.select(enemy).where(cb.equal(enemy.get("id"), p));
        TypedQuery<Enemy> query = em.createQuery(q);
        query.setParameter(p, id);
        return query.getSingleResult();
    }

    @Override
    public <T> void updateEnemy(Enemy enemy, String field, T value) {
        transaction.begin();
        CriteriaUpdate<Enemy> update = cb.createCriteriaUpdate(Enemy.class);
        Root<Enemy> enemyRoot = update.from(Enemy.class);
        update.set(field, value)
                .where(cb.equal(enemyRoot.get("id"), enemy.getId()));
        Query query = em.createQuery(update);
        query.executeUpdate();
        em.refresh(enemy);
        transaction.commit();
    }

    @Override
    public <T> void updateEnemy(GameObjectType enemyType, String field, T value) {
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
    public void saveNewEnemy(Enemy enemy) {
        transaction.begin();
        em.persist(enemy);
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
