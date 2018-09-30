package com.codecool.mhmm.stickman.controller;

import com.codecool.mhmm.stickman.Game;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.map.Level;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet(urlPatterns = {"/equip"})
public class EquipController extends BaseController {

    @Override
    void doAction(HttpServletRequest req, Game game, Player player, Level level){
        String actionRequired;
        if (req.getHeader("equipWeapon") != null) {
            actionRequired = req.getHeader("equipWeapon");
            game.equipWeapon(player, actionRequired);
        } else if(req.getHeader("equipArmor") != null) {
            actionRequired = req.getHeader("equipArmor");
            game.equipArmor(player, actionRequired);
        }
    }
}
