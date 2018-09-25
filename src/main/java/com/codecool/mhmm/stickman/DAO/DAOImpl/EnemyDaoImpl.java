package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.DAO.EnemyDao;
import com.codecool.mhmm.stickman.GameObjects.Characters.Enemy.Enemy;
import com.codecool.mhmm.stickman.GameObjects.GameObjectType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class EnemyDaoImpl extends BaseDaoImpl implements EnemyDao {

    public EnemyDaoImpl(EntityManagerFactory emf, EntityManager em) {
        super(emf, em);
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
    public void updateEnemy(Enemy enemy, long id) {

    }

    @Override
    public void updateEnemy(Enemy enemy, GameObjectType enemyType) {

    }

    @Override
    public void saveNewEnemy(Enemy enemy) {

    }
}
