package com.codecool.mhmm.stickman.controller;

import com.codecool.mhmm.stickman.Game;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.map.Level;
import com.codecool.mhmm.stickman.services.ItemHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/potion")
public class PotionController extends BaseController {

    @Override
    String doAction(HttpServletRequest req, Game game, Player player, Level level) {
        ItemHandler itemHandler = game.getItemHandler();
        String potion = req.getHeader("potion");
        itemHandler.drinkPotion(player, potion);

        return potion + " drunk, player drank too.";
    }
}
