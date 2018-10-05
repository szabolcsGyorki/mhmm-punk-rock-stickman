package com.codecool.mhmm.stickman.controller;

import com.codecool.mhmm.stickman.Game;
import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.stickman.game_objects.items.Loot;
import com.codecool.mhmm.stickman.map.Level;
import com.codecool.mhmm.stickman.services.FightHandler;
import com.codecool.mhmm.stickman.services.HealthHandler;
import com.codecool.mhmm.stickman.services.ItemHandler;
import com.codecool.mhmm.stickman.services.MoveHandler;

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
        String actionRequired;
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

        if (fightHandler.characterHits(player)) {
            int playerDamage = fightHandler.getPlayerDamage(player);
            if (!fightHandler.characterDodges(enemy)) {
                healthHandler.dealDamage(enemy, playerDamage);
            }
        }

        assert enemy != null;
        if (enemy.getHitPoint() > 0) {
            if (fightHandler.characterHits(enemy)) {
                int enemyDamage = enemy.getDamage();
                if (!fightHandler.characterDodges(player)) {
                    healthHandler.dealDamage(player, enemyDamage);
                }
            }
        } else {
            map.remove(enemy);
            Loot loot = new Loot(enemy.getX(), enemy.getY());
            itemHandler.setLootGold(loot);
            itemHandler.fillUpLoot(loot);
            level.addContent(loot);
        }
        return "fight";
    }
}
