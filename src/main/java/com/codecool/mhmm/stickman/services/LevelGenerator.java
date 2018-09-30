package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.dao.EnemyDAO;
import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import com.codecool.mhmm.stickman.game_objects.Wall;
import com.codecool.mhmm.stickman.game_objects.items.Loot;
import com.codecool.mhmm.stickman.map.Level;

import static com.codecool.mhmm.stickman.game_objects.GameObjectType.*;

public class LevelGenerator {

    public Level levelOne(ItemsDAO itemsDAO, EnemyDAO enemyDAO){
        Level levelOne = generateBase(itemsDAO);
        GameObjectType wallImage = levelOne.getWallImage();

        levelOne.addContent(new Wall(1, 2, wallImage));
        levelOne.addContent(new Wall(2, 2, wallImage));
        levelOne.addContent(new Wall(3, 2, wallImage));
        levelOne.addContent(new Wall(4, 2, wallImage));
        levelOne.addContent(new Wall(5, 2, wallImage));
        levelOne.addContent(new Wall(7, 2, wallImage));
        levelOne.addContent(new Wall(9, 2, wallImage));
        levelOne.addContent(new Wall(1, 5, wallImage));
        levelOne.addContent(new Wall(2, 5, wallImage));
        levelOne.addContent(new Wall(3, 5, wallImage));
        levelOne.addContent(new Wall(4, 5, wallImage));
        levelOne.addContent(new Wall(5, 5, wallImage));
        levelOne.addContent(new Wall(6, 5, wallImage));
        levelOne.addContent(new Wall(7, 4, wallImage));
        levelOne.addContent(new Wall(7, 3, wallImage));
        levelOne.addContent(new Wall(7, 5, wallImage));
        levelOne.addContent(new Wall(7, 6, wallImage));

        levelOne.addContent((GameObject) enemyDAO.findById(26L));
        levelOne.addContent((GameObject) enemyDAO.findById(27L));
        levelOne.addContent((GameObject) enemyDAO.findById(28L));
        levelOne.addContent((GameObject) enemyDAO.findById(29L));

        levelOne.addContent((Loot) itemsDAO.findById(30));
        levelOne.addContent((Loot) itemsDAO.findById(31));

        return levelOne;
    }

    private Level generateBase(ItemsDAO itemsDAO){
        Level level = new Level(10,10 ,WALL, FLOOR);

        for(int i = 0; i< level.getWIDTH(); i++){
            level.addContent(new Wall(i,0, level.getWallImage()));
            level.addContent(new Wall(i,level.getHEIGHT()-1, level.getWallImage()));
        }
        for(int i = 1; i< level.getHEIGHT()-1; i++){
            level.addContent(new Wall(0, i, level.getWallImage()));
            level.addContent(new Wall(level.getWIDTH()-1, i, level.getWallImage()));
        }

        return level;
    }

}
