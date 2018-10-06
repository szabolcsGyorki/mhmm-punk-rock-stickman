package com.codecool.mhmm.stickman.controller;

import com.codecool.mhmm.stickman.Game;
import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.game_objects.characters.enemy.Enemy;
import com.codecool.mhmm.stickman.game_objects.items.Loot;
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

        String response = "";
        assert enemy != null;
        GameObjectType enemyType = enemy.getType();

        if (fightHandler.characterHits(player)) {
            int playerDamage = fightHandler.getPlayerDamage(player);
            if (!fightHandler.characterDodges(enemy)) {
                healthHandler.dealDamage(enemy, playerDamage);
                response += "Your attack hits " + enemyType + " for " + playerDamage + " damage. ";
                Sound.playAttack(GameObjectType.MAIN_CHARACTER);
            } else {
                Sound.playMiss();
                response += enemyType + " dodges your attack. ";
            }
        } else {
            response += "Your attack misses. ";
        }

        if (enemy.getHitPoint() > 0) {
            if (fightHandler.characterHits(enemy)) {
                int enemyDamage = enemy.getDamage();
                if (!fightHandler.characterDodges(player)) {
                    healthHandler.dealDamage(player, enemyDamage);
                    response += enemyType + "'s attack hits your for " + enemyDamage + " damage.";
                    if (enemyType.equals(GameObjectType.SKELETON)) {
                        Sound.playAttack(GameObjectType.SKELETON);
                    }
                } else {
                    Sound.playMiss();
                    response += "You dodge " + enemyType + "'s attack.";
                }
            } else {
                response += enemyType + "'s attack misses.";
            }
        } else {
            Sound.playDie(enemyType);
            map.remove(enemy);
            Loot loot = new Loot(enemy.getX(), enemy.getY());
            itemHandler.setLootGold(loot);
            itemHandler.fillUpLoot(loot);
            level.addContent(loot);
            response += enemyType + " dies.";
        }

        if (player.getHitPoint() <= 0) {
            Sound.playDie(GameObjectType.MAIN_CHARACTER);
            Sound.playGameOver();
        }
        return response;
    }
}
