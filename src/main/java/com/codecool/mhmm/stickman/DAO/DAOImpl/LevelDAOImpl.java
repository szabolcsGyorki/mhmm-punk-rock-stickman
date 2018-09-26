package com.codecool.mhmm.stickman.DAO.DAOImpl;

import com.codecool.mhmm.stickman.DAO.LevelDAO;
import com.codecool.mhmm.stickman.Map.Level;

import javax.persistence.EntityManager;

public class LevelDAOImpl extends BaseDaoImpl implements LevelDAO {

    LevelDAOImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Level getLevel(long id) {
        return null;
    }

    @Override
    public Level getLevelObjects(long id) {
        return null;
    }

    @Override
    public void createNewLevel(Level level) {

    }

    @Override
    public void updateLevel(Level level, long id) {

    }
}
