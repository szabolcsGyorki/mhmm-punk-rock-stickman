package com.codecool.mhmm.stickman;

import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.items.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;
import com.codecool.mhmm.stickman.map.Level;
import com.codecool.mhmm.stickman.services.JSONHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/send"})
public class AjaxCall extends HttpServlet {
    private boolean demoload = true;

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction transaction = em.getTransaction();
    private JSONHandler jsonHandler = new JSONHandler();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Game game = Game.getInstance();
        if (demoload) {
            game.initForDemo();
            demoload = false;
        }

        HttpSession session = req.getSession(true);
        Player player = game.getPlayer(session);

        Level level = game.getLevel(session);

        String actionRequired;
        if (req.getHeader("map") != null) {
            actionRequired = req.getHeader("map");
            game.move(player,level,actionRequired);
        } else if (req.getHeader("equipWeapon") != null) {
            actionRequired = req.getHeader("equipWeapon");
            game.equipWeapon(player, actionRequired);
        } else if(req.getHeader("equipArmor") != null) {
            actionRequired = req.getHeader("equipArmor");
            game.equipArmor(player, actionRequired);
        }

        game.setPlayer(session, player);
        game.setLevel(session, level);

        String jsonArray = jsonHandler.gameStateToJson(player, level);
        resp.getWriter().write(jsonArray);
    }
}
