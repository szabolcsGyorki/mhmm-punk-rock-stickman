package com.codecool.mhmm.stickman.controller;

import com.codecool.mhmm.stickman.Game;
import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.stickman.game_objects.items.Loot;
import com.codecool.mhmm.stickman.map.Level;
import com.codecool.mhmm.stickman.services.HealthHandler;
import com.codecool.mhmm.stickman.services.MoveHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

@WebServlet(urlPatterns = {"/fight"})
public class FightController extends BaseController {

    @Override
    void doAction(HttpServletRequest req, Game game, Player player, Level level) {
        String actionRequired;
        MoveHandler moveHandler = game.getMoveHandler();
        HealthHandler healthHandler = game.getHealthHandler();
        ItemsDAO itemsDAO = game.getItemsDAO();
        List<GameObject> map = level.getMap();
        Enemy enemy = null;

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

        int playerDamage = player.getDamage();
        Random dodge = new Random();
        if (dodge.nextInt(100) < player.getDodgeChance()) {
            playerDamage = 0;
        }
        healthHandler.dealDamage(enemy, playerDamage);

        assert enemy != null;
        int enemyDamage = enemy.getDamage();
        if (dodge.nextInt(100) < enemy.getDodgeChance()) {
            enemyDamage = 0;
        }
        healthHandler.dealDamage(player, enemyDamage);

        if (enemy.getHitPoint() <= 0) {
            map.remove(enemy);
            level.addContent(new Loot(enemy.getX(), enemy.getY(), itemsDAO));
        }
    }
}
