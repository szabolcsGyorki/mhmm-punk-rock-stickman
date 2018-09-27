package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.DAO.LevelDAO;
import com.codecool.mhmm.stickman.GameObjects.GameObject;
import com.codecool.mhmm.stickman.Map.Level;

import javax.persistence.EntityManager;
import java.util.List;

public class LevelDAOImpl extends BaseDaoImpl implements LevelDAO {

    LevelDAOImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Level getLevel(long id) {
        return em.find(Level.class, id);
    }

    @Override
    public List<GameObject> getLevelObjects(long id) {
        Level level = em.find(Level.class, id);
        return level.getMap();
    }

    @Override
    public void createNewLevel(Level level) {
        transaction.begin();
        em.persist(level);
        for (GameObject object : level.getMap()) {
            em.persist(object);
        }
        transaction.commit();
    }

}
