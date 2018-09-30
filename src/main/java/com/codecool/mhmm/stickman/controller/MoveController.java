package com.codecool.mhmm.stickman.controller;

import com.codecool.mhmm.stickman.Game;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.items.Armor;
import com.codecool.mhmm.stickman.game_objects.items.Item;
import com.codecool.mhmm.stickman.game_objects.items.Weapon;
import com.codecool.mhmm.stickman.map.Level;
import com.codecool.mhmm.stickman.services.JSONHandler;
import com.codecool.mhmm.stickman.services.MoveHandler;
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

@WebServlet(urlPatterns = {"/move"})
public class MoveController extends BaseController {

    @Override
    void doAction(HttpServletRequest req, Game game, Player player, Level level) {
        String actionRequired;
        MoveHandler moveHandler = game.getMoveHandler();

        if (req.getHeader("map") != null) {
            actionRequired = req.getHeader("map");
            if (actionRequired.equals("down") && player.getY() < level.getHEIGHT() -1) {
                moveHandler.moveDown(player);
            }
            if (actionRequired.equals("up") && player.getY() > 0) {
                moveHandler.moveUp(player);
            }
            if (actionRequired.equals("right") && player.getX() < level.getWIDTH() -1) {
                moveHandler.moveRight(player);
            }
            if (actionRequired.equals("left") && player.getX() > 0) {
                moveHandler.moveLeft(player);
            }
        }
    }
}
