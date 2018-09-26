package com.codecool.mhmm.stickman.DAO;

import com.codecool.mhmm.stickman.Map.Level;

public interface LevelDAO {

    /**
     * get Height, Width
     * get Wall, Floor type
     * get Object List
     * @param id
     */
    Level getLevel(long id);

    /**
     * get ONLY objects in level
     * @param id
     */
    Level getLevelObjects(long id);

    /**
     * check name duplication!
     */
    void createNewLevel(Level level);

    void updateLevel(Level level, long id);

}
