package com.codecool.mhmm.stickman.DAO;

import com.codecool.mhmm.stickman.GameObjects.Characters.Enemy.Enemy;
import com.codecool.mhmm.stickman.GameObjects.GameObjectType;
import com.codecool.mhmm.stickman.Map.Level;

public interface EnemyDao {

    /**
     * get every field of enemy
     */
    Enemy getEnemy(int id);

    /**
     * update specific enemy with this id
     */
    void updateEnemy(Enemy enemy, int id);

    /**
     * update every enemy in this type
     * !!! Handle different lvl !!!
     */
    void updateEnemy(Enemy enemy, GameObjectType enemyType);

    void saveNewEnemy(Enemy enemy);
}
