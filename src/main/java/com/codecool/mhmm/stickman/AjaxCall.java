package com.codecool.mhmm.stickman;

import com.codecool.mhmm.stickman.GameObjects.Characters.Player;
import com.codecool.mhmm.stickman.GameObjects.GameObject;
import com.codecool.mhmm.stickman.GameObjects.Items.Armor;
import com.codecool.mhmm.stickman.GameObjects.Items.Item;
import com.codecool.mhmm.stickman.GameObjects.Items.Weapon;
import com.codecool.mhmm.stickman.Map.Level;
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
import static com.codecool.mhmm.stickman.GameObjects.GameObjectType.*;

@WebServlet(urlPatterns = {"/send"})
public class AjaxCall extends HttpServlet {
    private boolean demoload = true;

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction transaction = em.getTransaction();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String actionRequired = req.getHeader("action");
        Game game = Game.getInstance();
        if (demoload) {
            game.initForDemo();
            demoload = false;
        }

        HttpSession session = req.getSession(true);
        Player player = game.getPlayer(session);
        Level level = game.getLevel(session);
        game.move(player,level,actionRequired);

        game.setPlayer(session, player);
        game.setLevel(session, level);

        if (actionRequired.equals("char")) {
            resp.getWriter().write(characterToJson(player).toJSONString());
        } else {
            resp.getWriter().write(levelToJson(level.getMap()).toJSONString());
        }
    }

    /**
     * Converts a list of GameObjects to a JSONObject that's AJAX response ready
     * @return JSONArray with JSONObjects
     */
    @SuppressWarnings("unchecked")
    private JSONArray levelToJson (List<GameObject> gameObjects) {
        JSONArray gameObjectsJSONArray = new JSONArray();

        for (GameObject gameObject: gameObjects) {
            JSONObject obj = new JSONObject();
            obj.put("name", gameObject.getType().toString());
            obj.put("x", gameObject.getX());
            obj.put("y", gameObject.getY());
            gameObjectsJSONArray.add(obj);
        }
        return gameObjectsJSONArray;
    }

    @SuppressWarnings("unchecked")
    private JSONObject characterToJson (Player player) {
        JSONObject character = new JSONObject();
        JSONArray characterInventory = new JSONArray();

        //filling inventory
        for (Item item: player.getItems()) {
            JSONObject jsonItem = new JSONObject();

            jsonItem.put("name", item.getName());
            if (item instanceof Weapon) {
                jsonItem.put("maxDamage", ((Weapon) item).getMaxDamage());
                jsonItem.put("minDamage", ((Weapon) item).getMinDamage());
            } else {
                jsonItem.put("healthIncrease", ((Armor) item).getHealthIncrease());
            }
            characterInventory.add(jsonItem);
        }

        //filling character details
        character.put("hp", player.getHitPoint());
        character.put("str", player.getStrength());
        character.put("agi", player.getAgility());
        character.put("int", player.getIntelligence());
        character.put("inventory", characterInventory);
        return character;
    }
}

