package com.codecool.mhmm.stickman.DAO;

import com.codecool.mhmm.stickman.GameObjects.Characters.Enemy.Enemy;
import com.codecool.mhmm.stickman.GameObjects.GameObjectType;

import java.util.List;

public interface EnemyDao {

    List<Enemy> getAllEnemy();

    /**
     * get every field of enemy
     */
    Enemy getEnemy(long id);

    /**
     * Update single enemy
     * @param enemy: enemy object to be updated
     * @param field: name of the field that is updated
     * @param value: the new value (generic type)
     */
    <T> void updateEnemy(Enemy enemy, String field, T value);

    /**
     * update every enemy in this type
     * !!! Handle different lvl !!!
     */
    <T> void updateEnemy(GameObjectType enemyType, String field, T value);

    void saveNewEnemy(Enemy enemy);

    List<Enemy> getEnemiesByType(GameObjectType gameObjectType);
}
