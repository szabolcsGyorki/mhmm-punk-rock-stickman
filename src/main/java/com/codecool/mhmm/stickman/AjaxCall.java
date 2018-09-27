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
        Game game = Game.getInstance();
        if (demoload) {
            game.initForDemo();
            demoload = false;
        }


        HttpSession session = req.getSession(true);
        Player player = game.getPlayer(session);
        player.addItemToInventory(new Weapon("Foskard", 0, 50, 25));
        player.addItemToInventory(new Armor("Fosarmor", 0, 50));
        Level level = game.getLevel(session);

        String actionRequired = null;
        if (req.getHeader("map") != null) {
            actionRequired = req.getHeader("map");
            game.move(player,level,actionRequired);
        } else if (req.getHeader("equip") != null) {
            actionRequired = req.getHeader("equip");
            // jatekos.equip(actionRequired) JÁNOS HELP
        }

        game.setPlayer(session, player);
        game.setLevel(session, level);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(levelToJson(level.getMap()));

        JSONArray charArray = new JSONArray();
        charArray.add(characterToJson(player));
        jsonArray.add(charArray);

        resp.getWriter().write(jsonArray.toJSONString());
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
        character.put("Health", player.getHitPoint());
        character.put("Strength", player.getStrength());
        character.put("Agility", player.getAgility());
        character.put("Intellect", player.getIntelligence());
        character.put("Damage", player.getDamage());
        character.put("inventory", characterInventory);
        return character;
    }
}
