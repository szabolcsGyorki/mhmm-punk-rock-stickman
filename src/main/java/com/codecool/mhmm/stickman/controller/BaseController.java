package com.codecool.mhmm.stickman.controller;

import com.codecool.mhmm.stickman.Game;
import com.codecool.mhmm.stickman.dao.EnemyDAO;
import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.dao.LevelDAO;
import com.codecool.mhmm.stickman.dao.PlayerDAO;
import com.codecool.mhmm.stickman.dao.dao_impl.EnemyDAOImpl;
import com.codecool.mhmm.stickman.dao.dao_impl.ItemsDAOImpl;
import com.codecool.mhmm.stickman.dao.dao_impl.LevelDAOImpl;
import com.codecool.mhmm.stickman.dao.dao_impl.PlayerDAOImpl;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.map.Level;
import com.codecool.mhmm.stickman.services.HealthHandler;
import com.codecool.mhmm.stickman.services.JSONHandler;
import com.codecool.mhmm.stickman.services.LevelGenerator;
import com.codecool.mhmm.stickman.services.MoveHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet
public abstract class BaseController extends HttpServlet {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private EntityManager em = emf.createEntityManager();
    private ItemsDAO itemsDAO = new ItemsDAOImpl(em);
    private EnemyDAO enemyDao = new EnemyDAOImpl(em);
    private LevelDAO levelDao = new LevelDAOImpl(em);
    private PlayerDAO playerDAO = new PlayerDAOImpl(em);
    private HealthHandler healthHandler = new HealthHandler();
    private LevelGenerator levelGenerator = new LevelGenerator();
    private MoveHandler moveHandler = MoveHandler.getInstance();
    private JSONHandler jsonHandler = new JSONHandler();
    private boolean demoload = true;
    private Game game = new Game(itemsDAO, enemyDao, levelDao, playerDAO, healthHandler, levelGenerator, moveHandler);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (demoload) {
            game.initForDemo();
            demoload = false;
        }


        HttpSession session = req.getSession(true);
        Player player = game.getPlayer(session);

        Level level = game.getLevel(session);

        doAction(req, game, player, level);

        game.setPlayer(session, player);
        game.setLevel(session, level);

        String jsonArray = jsonHandler.gameStateToJson(player, level);
        resp.getWriter().write(jsonArray);
    }

    abstract void doAction(HttpServletRequest req, Game game, Player player, Level level);
}