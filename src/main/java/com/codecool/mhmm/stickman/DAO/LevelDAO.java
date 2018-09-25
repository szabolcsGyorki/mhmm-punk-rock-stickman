package com.codecool.mhmm.stickman.DAO;

import com.codecool.mhmm.stickman.Map.Level;

public interface LevelDAO {

    /**
     * get Height, Width
     * get Wall, Floor type
     * get Object List
     */
    Level getLevel(int id);

    /**
     * get ONLY objects in level
     */
    Level getLevelObjects(int id);

    /**
     * check name duplication!
     */
    void createNewLevel(Level level);

    void updateLevel(Level level, int id);

}
