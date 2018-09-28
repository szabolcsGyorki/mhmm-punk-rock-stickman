package com.codecool.mhmm.stickman.DAO;

import com.codecool.mhmm.stickman.GameObjects.Characters.Enemy.Enemy;
import com.codecool.mhmm.stickman.GameObjects.GameObjectType;

import java.util.List;

public interface EnemyDAO extends BaseDAO {

    /**
     * Update every enemies which belongs to the given type
     * @param enemyType: type of the enemy
     * @param field: the name of the field to update (from the POJO not DB)
     * @param value: the new value of the given field
     */
    <T> void updateEnemiesByType(GameObjectType enemyType, String field, T value);

    /**
     * @param gameObjectType: type of the enemies to return
     * @return: a list with all enemies that belongs to the given type
     */
    List<Enemy> getEnemiesByType(GameObjectType gameObjectType);
}
