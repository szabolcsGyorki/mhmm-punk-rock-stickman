package com.codecool.mhmm.stickman.dao;

import com.codecool.mhmm.stickman.game_objects.GameObject;

import java.util.List;

public interface LevelDAO extends BaseDAO {

    /**
     * Get every objects that belongs to the given level
     *
     * @param id: id of the level which objects needs to be returned
     * @return: a list with all the items of the given level
     */
    List<GameObject> getLevelObjects(long id);
}
