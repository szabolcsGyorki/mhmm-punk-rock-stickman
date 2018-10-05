package com.codecool.mhmm.stickman.controller;

import com.codecool.mhmm.stickman.Game;
import com.codecool.mhmm.stickman.game_objects.characters.Player;
import com.codecool.mhmm.stickman.map.Level;
import com.codecool.mhmm.stickman.services.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet
public abstract class BaseController extends HttpServlet {

    private JSONHandler jsonHandler = JSONHandler.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);

        Game game = (Game) session.getAttribute("game");
        Player player = game.getPlayer(session);
        Level level = game.getLevel(session);

        String response = doAction(req, game, player, level);

        game.setPlayer(session, player);
        game.setLevel(session, level);

        String jsonArray = jsonHandler.gameStateToJson(player, level, response);
        resp.getWriter().write(jsonArray);
    }

    abstract String doAction(HttpServletRequest req, Game game, Player player, Level level);
}
