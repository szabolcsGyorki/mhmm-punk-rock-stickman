package com.codecool.mhmm.stickman;

import com.codecool.mhmm.stickman.GameObjects.Characters.Player;
import com.codecool.mhmm.stickman.GameObjects.GameObject;
import com.codecool.mhmm.stickman.Map.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.codecool.mhmm.stickman.GameObjects.GameObjectType.*;
import static com.codecool.mhmm.stickman.GameObjects.GameObjectType.DRAGON;
import static com.codecool.mhmm.stickman.GameObjects.GameObjectType.ORC;

public class Game {
    private static Game ourInstance = new Game();

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction transaction = em.getTransaction();

    //GUEST TRIAL STUFF
    private Player Zsolt;
    private Level levelOne;

    public void initForDemo(){
        levelOne = new Level(10,10 ,WALL, FLOOR);
        Zsolt = new Player(1,1);
        levelOne.placeWall(1,2);
        levelOne.placeWall(2,2);
        levelOne.placeWall(3,2);
        levelOne.placeWall(4,2);
        levelOne.placeWall(5,2);
        levelOne.placeWall(7,2);
        levelOne.placeWall(9,2);

        levelOne.placeWall(1,5);
        levelOne.placeWall(2,1);
        levelOne.placeWall(3,1);
        levelOne.placeWall(4,1);
        levelOne.placeWall(5,1);
        levelOne.placeWall(6,1);

        levelOne.placeEnemy(1,1,SLIME,1);
        levelOne.placeEnemy(1,2,SKELETON,1);
        levelOne.placeEnemy(1,3,ORC,1);
        levelOne.placeEnemy(1,4,DRAGON,1);
        levelOne.placePlayer(Zsolt);
        transaction.begin();
        em.persist(levelOne);
        for (GameObject object : levelOne.getMap()) {
            em.persist(object);
        }
        transaction.commit();
    }

    public static Game getInstance() {
        return ourInstance;
    }

    Player getPlayer(HttpSession session) {
        if (session.getAttribute("Player") == null) {
            session.setAttribute("Player", Zsolt);
            return Zsolt;
        }
        return (Player)session.getAttribute("Player");
    }

    public Level getLevel(HttpSession session) {
        if (session.getAttribute("Level") == null) {
            session.setAttribute("Level", levelOne);
            return levelOne;
        }
        return (Level)session.getAttribute("Level");
    }

    void setPlayer(HttpSession session, Player player) {
        session.setAttribute("Player", player);
    }

    void setLevel(HttpSession session, Level level) {
        session.setAttribute("Level", level);
    }

    void move(GameObject movingObject, Level level, String actionRequired) {
        if (actionRequired.equals("down")
                && movingObject.getY() < level.getHEIGHT() -1) {
            level.move(movingObject.getX(), movingObject.getY()+1, (Player) movingObject);
        }
        if (actionRequired.equals("up")
                && movingObject.getY() > 0) {
            level.move(movingObject.getX(), movingObject.getY()-1, (Player) movingObject);
        }
        if (actionRequired.equals("right")
                && movingObject.getX() < level.getWIDTH() -1) {
            level.move(movingObject.getX()+1, movingObject.getY(), (Player) movingObject);
        }
        if (actionRequired.equals("left")
                && movingObject.getX() > 0) {
            level.move(movingObject.getX()-1, movingObject.getY(), (Player) movingObject);
        }
    }
}
