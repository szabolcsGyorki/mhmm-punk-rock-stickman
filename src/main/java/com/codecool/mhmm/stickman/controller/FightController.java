package com.codecool.mhmm.stickman.controller;

import com.codecool.mhmm.stickman.Game;
import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.stickman.map.Level;
import com.codecool.mhmm.stickman.services.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet(urlPatterns = {"/fight"})
public class FightController extends BaseController {

    @Override
    String doAction(HttpServletRequest req, Game game, Player player, Level level) {
        MoveHandler moveHandler = game.getMoveHandler();
        HealthHandler healthHandler = game.getHealthHandler();
        FightHandler fightHandler = game.getFightHandler();
        ItemHandler itemHandler = game.getItemHandler();

        List<GameObject> map = level.getMap();
        Enemy enemy = getEnemy(req, player, moveHandler, map);

        String response = "";
        assert enemy != null;

        response += fightHandler.playerHitsEnemy(player, enemy, healthHandler);

        if (enemy.getHitPoint() > 0) {
            response += fightHandler.enemyHitsPlayer(player, enemy, healthHandler);
        } else {
            response += fightHandler.enemyDies(level, itemHandler, map, enemy);
        }

        if (player.getHitPoint() <= 0) {
            Sound.playDie(GameObjectType.MAIN_CHARACTER);
            Sound.playGameOver();
        }
        return response;
    }

    private Enemy getEnemy(HttpServletRequest req, Player player, MoveHandler moveHandler, List<GameObject> map) {
        Enemy enemy = null;
        String actionRequired;
        if (req.getHeader("fight") != null) {
            actionRequired = req.getHeader("fight");
            if (actionRequired.equals("down")) {
                enemy = (Enemy) moveHandler.getDestination(player.getX(), player.getY() + 1, map);
            } else if (actionRequired.equals("up")) {
                enemy = (Enemy) moveHandler.getDestination(player.getX(), player.getY() - 1, map);
            } else if (actionRequired.equals("right")) {
                enemy = (Enemy) moveHandler.getDestination(player.getX() + 1, player.getY(), map);
            } else {
                enemy = (Enemy) moveHandler.getDestination(player.getX() - 1, player.getY(), map);
            }
        }
        return enemy;
    }
}
