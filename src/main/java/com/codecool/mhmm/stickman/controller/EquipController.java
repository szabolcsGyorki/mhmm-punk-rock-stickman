package com.codecool.mhmm.stickman.controller;

import com.codecool.mhmm.stickman.Game;
import com.codecool.mhmm.stickman.dao.ItemsDAO;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.map.Level;
import com.codecool.mhmm.stickman.services.ItemHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet(urlPatterns = {"/equip"})
public class EquipController extends BaseController {

    @Override
    void doAction(HttpServletRequest req, Game game, Player player, Level level) {
        ItemHandler itemHandler = game.getItemHandler();
        ItemsDAO itemsDAO = game.getItemsDAO();

        String itemName;

        if (req.getHeader("equipWeapon") != null) {
            itemName = req.getHeader("equipWeapon");
            itemHandler.equipWeapon(player, itemName);
        } else if (req.getHeader("equipArmor") != null) {
            itemName = req.getHeader("equipArmor");
            itemHandler.equipArmor(player, itemName);
        }
    }
}
