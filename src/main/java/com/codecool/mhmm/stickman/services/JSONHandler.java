package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.items.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;
import com.codecool.mhmm.stickman.map.Level;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class JSONHandler {

    private static JSONHandler instance;

    private JSONHandler() {
    }

    public static JSONHandler getInstance() {
        if (instance == null) {
            instance = new JSONHandler();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public String gameStateToJson(Player player, Level level, String response) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(levelToJson(level.getMap()));

        JSONArray charArray = new JSONArray();
        charArray.add(characterToJson(player));
        jsonArray.add(charArray);

        JSONObject responseJson = new JSONObject();
        responseJson.put("response", response);
        jsonArray.add(responseJson);

        return jsonArray.toJSONString();
    }

    /**
     * Converts a list of game_objects to a JSONObject that's AJAX response ready
     *
     * @return JSONArray with JSONObjects
     */
    @SuppressWarnings("unchecked")
    private JSONArray levelToJson(List<GameObject> gameObjects) {
        JSONArray gameObjectsJSONArray = new JSONArray();

        for (GameObject gameObject : gameObjects) {
            JSONObject obj = new JSONObject();
            obj.put("name", gameObject.getType().toString());
            obj.put("x", gameObject.getX());
            obj.put("y", gameObject.getY());
            gameObjectsJSONArray.add(obj);
        }
        return gameObjectsJSONArray;
    }

    @SuppressWarnings("unchecked")
    private JSONObject characterToJson(Player player) {
        JSONObject character = new JSONObject();
        JSONArray characterInventory = new JSONArray();

        //filling inventory
        for (Item item : player.getItems()) {
            JSONObject jsonItem = new JSONObject();

            jsonItem.put("name", item.getName());
            if (item instanceof Weapon) {
                jsonItem.put("maxDamage", ((Weapon) item).getMaxDamage());
                jsonItem.put("minDamage", ((Weapon) item).getMinDamage());
                jsonItem.put("type", "weapon");
            } else {
                jsonItem.put("healthIncrease", ((Armor) item).getHealthIncrease());
                jsonItem.put("type", "armor");
            }
            characterInventory.add(jsonItem);
        }

        //filling character details
        character.put("Health", player.getHitPoint());
        character.put("Strength", player.getStrength());
        character.put("Agility", player.getAgility());
        character.put("Intellect", player.getIntelligence());
        character.put("Damage", player.getDisplayDamage());
        character.put("inventory", characterInventory);
        return character;
    }

}
