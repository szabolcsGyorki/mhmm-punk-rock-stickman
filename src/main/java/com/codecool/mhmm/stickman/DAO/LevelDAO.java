package com.codecool.mhmm.stickman.DAO;

import com.codecool.mhmm.stickman.GameObjects.GameObject;
import com.codecool.mhmm.stickman.Map.Level;

import java.util.List;

public interface LevelDAO {

    /**
     * get Height, Width
     * get Wall, Floor type
     * get Object List
     * @param id: id of the level
     */
    Level getLevel(long id);

    /**
     * get ONLY objects in level
     * @param id: id of the level
     */
    List<GameObject> getLevelObjects(long id);

    /**
     * check name duplication!
     */
    void createNewLevel(Level level);
}
