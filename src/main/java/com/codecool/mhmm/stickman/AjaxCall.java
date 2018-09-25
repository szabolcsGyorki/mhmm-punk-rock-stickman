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
import java.io.IOException;
import java.util.List;
import static com.codecool.mhmm.stickman.GameObjects.GameObjectType.*;

@WebServlet(urlPatterns = {"/send"})
public class AjaxCall extends HttpServlet {

    private Player Zsolt;
    private Level levelOne;
    private Boolean demoLoad = false;

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("stickman");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction transaction = em.getTransaction();

    private void initForDemo(){
        levelOne = new Level(10,10 ,WALL, FLOOR);
        Zsolt = new Player(1,5);
        levelOne.placeWall(2,2);
        levelOne.placeEnemy(1,1,SLIME,1);
        levelOne.placeEnemy(1,2,SKELETON,1);
        levelOne.placeEnemy(1,3,ORC,1);
        levelOne.placeEnemy(1,4,DRAGON,1);
        levelOne.placePlayer(Zsolt);
        demoLoad = true;
        transaction.begin();
        em.persist(levelOne);
        for (GameObject object : levelOne.getMap()) {
            em.persist(object);
        }
        transaction.commit();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!demoLoad) { initForDemo(); }    // For demo only
        String actionRequired = req.getHeader("action");
        if (actionRequired.equals("down")
                && Zsolt.getY() < levelOne.getHEIGHT() -1) {
            levelOne.move(Zsolt.getX(), Zsolt.getY()+1, Zsolt);
            }
        if (actionRequired.equals("up")
                && Zsolt.getY() > 0) {
            levelOne.move(Zsolt.getX(), Zsolt.getY()-1, Zsolt);
            }
        if (actionRequired.equals("right")
                && Zsolt.getX() < levelOne.getWIDTH() -1) {
            levelOne.move(Zsolt.getX()+1, Zsolt.getY(), Zsolt);
            }
        if (actionRequired.equals("left")
                && Zsolt.getX() > 0) {
            levelOne.move(Zsolt.getX()-1, Zsolt.getY(), Zsolt);
            }

        if (actionRequired.equals("char")) {
            resp.getWriter().write(characterToJson(Zsolt).toJSONString());
        } else {
            resp.getWriter().write(levelToJson(levelOne.getMap()).toJSONString());
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
                jsonItem.put("type", "weapon");
                jsonItem.put("maxDamage", ((Weapon) item).getMaxDamage());
                jsonItem.put("minDamage", ((Weapon) item).getMinDamage());
            } else {
                jsonItem.put("type", "armor");
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

