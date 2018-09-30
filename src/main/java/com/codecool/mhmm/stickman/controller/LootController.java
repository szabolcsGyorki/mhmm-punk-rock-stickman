package com.codecool.mhmm.stickman.controller;

import com.codecool.mhmm.stickman.Game;
import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.items.Loot;
import com.codecool.mhmm.stickman.map.Level;
import com.codecool.mhmm.stickman.services.MoveHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet(urlPatterns = {"/loot"})
public class LootController extends BaseController {

    @Override
    void doAction(HttpServletRequest req, Game game, Player player, Level level) {
        String actionRequired;
        MoveHandler moveHandler = game.getMoveHandler();

        if (req.getHeader("pickUpLoot") != null) {
            actionRequired = req.getHeader("pickUpLoot");
            Loot loot = null;
            List<GameObject> map = level.getMap();
            if (actionRequired.equals("down")) {
                loot = (Loot) moveHandler.getDestination(player.getX(), player.getY() + 1, map);
            } else if (actionRequired.equals("up")) {
                loot = (Loot) moveHandler.getDestination(player.getX(), player.getY() - 1, map);
            } else if (actionRequired.equals("right")) {
                loot = (Loot) moveHandler.getDestination(player.getX() + 1, player.getY(), map);
            } else {
                loot = (Loot) moveHandler.getDestination(player.getX() - 1, player.getY(), map);
            }
            loot.pickup(player);
            map.remove(loot);
        }
    }
}
