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
     * update specific enemy with this id
     */
    void updateEnemy(Enemy enemy, long id);

    /**
     * update every enemy in this type
     * !!! Handle different lvl !!!
     */
    void updateEnemy(Enemy enemy, GameObjectType enemyType);

    void saveNewEnemy(Enemy enemy);
}
