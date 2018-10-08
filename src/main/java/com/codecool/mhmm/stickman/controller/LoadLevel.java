package com.codecool.mhmm.stickman.controller;

import com.codecool.mhmm.stickman.Game;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.map.Level;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet(urlPatterns = {"/send"})

public class LoadLevel extends BaseController {
    @Override
    String doAction(HttpServletRequest req, Game game, Player player, Level level) {
        return "Loading complete";
    }
}
