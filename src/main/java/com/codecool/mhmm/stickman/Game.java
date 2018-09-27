package com.codecool.mhmm.stickman;

import com.codecool.mhmm.stickman.DAO.DAOImpl.EnemyDaoImpl;
import com.codecool.mhmm.stickman.DAO.DAOImpl.ItemsDAOImpl;
import com.codecool.mhmm.stickman.GameObjects.Characters.Player;
import com.codecool.mhmm.stickman.GameObjects.GameObject;
import com.codecool.mhmm.stickman.GameObjects.Items.Armor;
import com.codecool.mhmm.stickman.GameObjects.Items.Item;
import com.codecool.mhmm.stickman.GameObjects.Items.Weapon;
import com.codecool.mhmm.stickman.Map.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static com.codecool.mhmm.stickman.GameObjects.GameObjectType.*;
import static com.codecool.mhmm.stickman.GameObjects.GameObjectType.DRAGON;
import static com.codecool.mhmm.stickman.GameObjects.GameObjectType.ORC;

public class Game {
    private static Game ourInstance = new Game();

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction transaction = em.getTransaction();

    private ItemsDAOImpl itemsDAO = new ItemsDAOImpl(em);
    private EnemyDaoImpl enemyDao = new EnemyDaoImpl(em);
    // private LevelDaoImpl levelDao = new LevelDaoImpl(em);

    //GUEST TRIAL STUFF
    private Player Zsolt;
    private Level levelOne;

    public void initForDemo(){
        levelOne = new Level(10,10 ,WALL, FLOOR, itemsDAO);
        Zsolt = new Player(1,1, "Zsolt");
        levelOne.placePlayer(Zsolt);

        Init.init(em);

        levelOne.placeWall(1,2);
        levelOne.placeWall(2,2);
        levelOne.placeWall(3,2);
        levelOne.placeWall(4,2);
        levelOne.placeWall(5,2);
        levelOne.placeWall(7,2);
        levelOne.placeWall(9,2);

        levelOne.placeWall(1,5);
        levelOne.placeWall(2,5);
        levelOne.placeWall(3,5);
        levelOne.placeWall(4,5);
        levelOne.placeWall(5,5);
        levelOne.placeWall(6,5);
        levelOne.placeWall(7,4);
        levelOne.placeWall(7,3);
        levelOne.placeWall(7,5);
        levelOne.placeWall(7,6);

        levelOne.placeEnemy(6,2,SLIME,1);
        levelOne.placeEnemy(8,2,SKELETON,1);
        levelOne.placeEnemy(2,4,ORC,1);
        levelOne.placeEnemy(6,7,DRAGON,1);

        levelOne.placeLoot(4, 1, itemsDAO.getItem("Colossus Blade"));
        levelOne.placeLoot(1, 4, itemsDAO.getItem("Shadow Plate"));

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

    void equip(Player player, String itemName){
        Item item = itemsDAO.getItem(itemName);
        if (item instanceof Armor)
            player.setFullBody((Armor) item);
        if (item instanceof Weapon)
            player.setWeapon((Weapon) item);
    }
}
