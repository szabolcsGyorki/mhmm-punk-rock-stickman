package com.codecool.mhmm.stickman.controller;

import com.codecool.mhmm.stickman.Game;
import com.codecool.mhmm.stickman.config.TemplateEngineUtil;
import com.codecool.mhmm.stickman.dao.EnemyDAO;
import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.dao.LevelDAO;
import com.codecool.mhmm.stickman.dao.PlayerDAO;
import com.codecool.mhmm.stickman.dao.dao_impl.EnemyDAOImpl;
import com.codecool.mhmm.stickman.dao.dao_impl.ItemsDAOImpl;
import com.codecool.mhmm.stickman.dao.dao_impl.LevelDAOImpl;
import com.codecool.mhmm.stickman.dao.dao_impl.PlayerDAOImpl;
import com.codecool.mhmm.stickman.services.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class StartGame extends HttpServlet {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private EntityManager em = emf.createEntityManager();
    private ItemsDAO itemsDAO = new ItemsDAOImpl(em);
    private EnemyDAO enemyDao = new EnemyDAOImpl(em);
    private LevelDAO levelDao = new LevelDAOImpl(em);
    private PlayerDAO playerDAO = new PlayerDAOImpl(em);
    private LevelGenerator levelGenerator = LevelGenerator.getInstance();
    private HealthHandler healthHandler = HealthHandler.getInstance();
    private MoveHandler moveHandler = MoveHandler.getInstance();
    private ItemHandler itemHandler = ItemHandler.getInstance();

    private void addPlusContext(WebContext context, HttpServletRequest req) throws IndexOutOfBoundsException {
    }

    private String getHTML() {
        return "index";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession(true);
        String name = (String) session.getAttribute("playerName");

        if (session.getAttribute("game") == null) {
            Game game = new Game(itemsDAO, enemyDao, levelDao, playerDAO,
                    healthHandler, levelGenerator, moveHandler, itemHandler);
            if (!game.isDemoLoaded()) {
                game.initForDemo();
                name = "Zsolt";
            }
            if (!game.isInitialized()) {
                game.initGame(name);
            }
            session.setAttribute("game", game);
        }


        try {
            addPlusContext(context, req);
            engine.process( getHTML() + ".html", context, resp.getWriter());
        } catch(IndexOutOfBoundsException e) {
            engine.process("404.html", context, resp.getWriter());
        }
    }
}