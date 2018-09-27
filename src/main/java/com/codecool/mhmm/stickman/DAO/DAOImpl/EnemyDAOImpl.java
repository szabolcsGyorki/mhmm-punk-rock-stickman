package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.DAO.EnemyDAO;
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

public class EnemyDAOImpl extends BaseDaoImpl implements EnemyDAO {

    public EnemyDAOImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Enemy getEnemy(long id) {
        return em.find(Enemy.class, id);
    }

    @Override
    public List<Enemy> getAllEnemy() {
        TypedQuery<Enemy> query = em.createNamedQuery("Enemy.getAll", Enemy.class);
        return query.getResultList();
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