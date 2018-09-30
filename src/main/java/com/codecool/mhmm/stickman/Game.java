package com.codecool.mhmm.stickman;

import com.codecool.mhmm.stickman.config.InitDB;
import com.codecool.mhmm.stickman.dao.EnemyDAO;
import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.dao.LevelDAO;
import com.codecool.mhmm.stickman.dao.PlayerDAO;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.items.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;
import com.codecool.mhmm.stickman.map.Level;
import com.codecool.mhmm.stickman.services.*;

import javax.servlet.http.HttpSession;

public class Game {

    private ItemsDAO itemsDAO;
    private EnemyDAO enemyDao;
    private LevelDAO levelDao;
    private PlayerDAO playerDAO;
    private HealthHandler healthHandler;
    private LevelGenerator levelGenerator;
    private MoveHandler moveHandler;
    private ItemHandler itemHandler;
    private boolean initialized = false;
    private boolean demoLoaded = false;

    //GUEST TRIAL STUFF
    private Player player;
    private Level levelOne;

    public Game(ItemsDAO itemsDAO, EnemyDAO enemyDao, LevelDAO levelDao, PlayerDAO playerDAO,
                HealthHandler healthHandler, LevelGenerator levelGenerator, MoveHandler moveHandler,
                ItemHandler itemHandler) {
        this.itemsDAO = itemsDAO;
        this.enemyDao = enemyDao;
        this.levelDao = levelDao;
        this.playerDAO = playerDAO;
        this.healthHandler = healthHandler;
        this.levelGenerator = levelGenerator;
        this.moveHandler = moveHandler;
        this.itemHandler = itemHandler;
    }

    public void initForDemo(){
        InitDB init = new InitDB(itemsDAO, levelDao, enemyDao, levelGenerator, healthHandler);
        init.init();
        playerDAO.saveNew(new Player(1, 1, "Zsolt"));
        demoLoaded = true;
    }

    public void initGame(String name) {
        player = playerDAO.getPlayerByName(name);
        levelOne = (Level) levelDao.getAll().get(0);
        levelOne.addContent(player);
        initialized = true;
    }

    public Player getPlayer(HttpSession session) {
        if (session.getAttribute("Player") == null) {
            session.setAttribute("Player", player);
            return player;
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

    public boolean isInitialized() {
        return initialized;
    }

    public boolean isDemoLoaded() {
        return demoLoaded;
    }

    public void setPlayer(HttpSession session, Player player) {
        session.setAttribute("Player", player);
    }

    public void setLevel(HttpSession session, Level level) {
        session.setAttribute("Level", level);
    }

    public void move(GameObject movingObject, Level level, String actionRequired) {
        if (actionRequired.equals("down") && movingObject.getY() < level.getHEIGHT() -1) {
            moveHandler.move(movingObject.getX(), movingObject.getY()+1, (Player) movingObject, level, itemsDAO);
        }
        if (actionRequired.equals("up") && movingObject.getY() > 0) {
            moveHandler.move(movingObject.getX(), movingObject.getY()-1, (Player) movingObject, level, itemsDAO);
        }
        if (actionRequired.equals("right") && movingObject.getX() < level.getWIDTH() -1) {
            moveHandler.move(movingObject.getX()+1, movingObject.getY(), (Player) movingObject, level, itemsDAO);
        }
        if (actionRequired.equals("left") && movingObject.getX() > 0) {
            moveHandler.move(movingObject.getX()-1, movingObject.getY(), (Player) movingObject, level, itemsDAO);
        }
    }

    public void equipWeapon(Player player, String itemName){
        Item item = itemsDAO.getItemByName(itemName);
        if (item instanceof Weapon)
            player.setWeapon((Weapon) item);
    }

    public void equipArmor(Player player, String itemName){
        Item item = itemsDAO.getItemByName(itemName);
        if (item instanceof Armor) {
            if (!healthHandler.armorChangeKillsPlayer(player, (Armor) item)) {
                healthHandler.increasePlayerHealthWithArmor(player, (Armor) item);
                player.setFullBody((Armor) item);
            }
        }
    }
}
