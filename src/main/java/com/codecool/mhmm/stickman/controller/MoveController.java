package com.codecool.mhmm.stickman.controller;

import com.codecool.mhmm.stickman.Game;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.map.Level;
import com.codecool.mhmm.stickman.services.MoveHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet(urlPatterns = {"/move"})
public class MoveController extends BaseController {

    @Override
    void doAction(HttpServletRequest req, Game game, Player player, Level level) {
        String actionRequired;
        MoveHandler moveHandler = game.getMoveHandler();

        if (req.getHeader("map") != null) {
            actionRequired = req.getHeader("map");
            if (actionRequired.equals("down") && player.getY() < level.getHEIGHT() - 1) {
                moveHandler.moveDown(player);
            }
            if (actionRequired.equals("up") && player.getY() > 0) {
                moveHandler.moveUp(player);
            }
            if (actionRequired.equals("right") && player.getX() < level.getWIDTH() - 1) {
                moveHandler.moveRight(player);
            }
            if (actionRequired.equals("left") && player.getX() > 0) {
                moveHandler.moveLeft(player);
            }
        }
    }
}
