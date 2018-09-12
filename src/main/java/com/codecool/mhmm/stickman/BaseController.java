package com.codecool.mhmm.stickman;

import com.codecool.mhmm.stickman.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class BaseController extends HttpServlet {

    private void addPlusContext(WebContext context, HttpServletRequest req) throws IndexOutOfBoundsException {
    }

    private String getHTML() {
        return "index";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        try {
            addPlusContext(context, req);
            engine.process( getHTML() + ".html", context, resp.getWriter());
        } catch(IndexOutOfBoundsException e) {
            engine.process("404.html", context, resp.getWriter());
        }
    }
}
