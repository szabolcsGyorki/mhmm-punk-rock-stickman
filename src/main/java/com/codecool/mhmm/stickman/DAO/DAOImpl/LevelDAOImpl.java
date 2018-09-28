package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.DAO.LevelDAO;
import com.codecool.mhmm.stickman.GameObjects.GameObject;
import com.codecool.mhmm.stickman.Map.Level;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class LevelDAOImpl extends BaseDaoImpl implements LevelDAO {

    public LevelDAOImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Level findById(long id) {
        return em.find(Level.class, id);
    }

    @Override
    public List<Level> getAll() {
        TypedQuery<Level> query = em.createNamedQuery("Level.getAll", Level.class);
        return query.getResultList();
    }

    @Override
    public void update(Object entity, String field, Object value) {

    }

    @Override
    public void saveNew(Object level) {
        transaction.begin();
        em.persist(level);
        for (GameObject object : ((Level)level).getMap()) {
            em.persist(object);
        }
        transaction.commit();
    }

    @Override
    public List<GameObject> getLevelObjects(long id) {
        Level level = em.find(Level.class, id);
        return level.getMap();
    }
}
