package com.codecool.mhmm.stickman;

import com.codecool.mhmm.stickman.config.InitDB;
import com.codecool.mhmm.stickman.dao.EnemyDAO;
import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.dao.LevelDAO;
import com.codecool.mhmm.stickman.dao.PlayerDAO;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
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
    private FightHandler fightHandler;
    private boolean initialized = false;
    private boolean demoLoaded = false;

    //GUEST TRIAL STUFF
    private Player player;
    private Level levelOne;

    public Game(ItemsDAO itemsDAO, EnemyDAO enemyDao, LevelDAO levelDao, PlayerDAO playerDAO,
                HealthHandler healthHandler, LevelGenerator levelGenerator, MoveHandler moveHandler,
                ItemHandler itemHandler, FightHandler fightHandler) {
        this.itemsDAO = itemsDAO;
        this.enemyDao = enemyDao;
        this.levelDao = levelDao;
        this.playerDAO = playerDAO;
        this.healthHandler = healthHandler;
        this.levelGenerator = levelGenerator;
        this.moveHandler = moveHandler;
        this.itemHandler = itemHandler;
        this.fightHandler = fightHandler;
    }

    public void initForDemo() {
        InitDB init = new InitDB(itemsDAO, levelDao, enemyDao, levelGenerator, healthHandler, itemHandler);
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

    public ItemsDAO getItemsDAO() {
        return itemsDAO;
    }

    public EnemyDAO getEnemyDao() {
        return enemyDao;
    }

    public LevelDAO getLevelDao() {
        return levelDao;
    }

    public PlayerDAO getPlayerDAO() {
        return playerDAO;
    }

    public HealthHandler getHealthHandler() {
        return healthHandler;
    }

    public LevelGenerator getLevelGenerator() {
        return levelGenerator;
    }

    public MoveHandler getMoveHandler() {
        return moveHandler;
    }

    public ItemHandler getItemHandler() {
        return itemHandler;
    }

    public FightHandler getFightHandler() {
        return fightHandler;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public boolean isDemoLoaded() {
        return demoLoaded;
    }

    public Player getPlayer() {
        return player;
    }

    public Level getLevelOne() {
        return levelOne;
    }

    public Player getPlayer(HttpSession session) {
        return (Player) session.getAttribute("Player");
    }

    public void setPlayer(HttpSession session, Player player) {
        session.setAttribute("Player", player);
    }

    public Level getLevel(HttpSession session) {
        return (Level) session.getAttribute("Level");
    }

    public void setLevel(HttpSession session, Level level) {
        session.setAttribute("Level", level);
    }
}
