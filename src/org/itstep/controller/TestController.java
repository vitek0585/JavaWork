package org.itstep.controller;

import org.itstep.core.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Вадим on 16.11.2015.
 */
public class TestController extends Controller {
    public TestController(HttpServletRequest req, HttpServletResponse resp, String view) {
        super(req, resp);
        _controllerName = "Test";
        _viewName = view;
    }

    public void Test() throws IOException, ServletException {
        String message = "ASP NET MVC";
        req.setAttribute("asp", message);

        String name = req.getParameter("name");
        req.setAttribute("name", name);

        View();
    }
    public void Index() throws IOException, ServletException {
        String message = "New Message";
        req.setAttribute("say", message);

        String name = req.getParameter("name");
        req.setAttribute("name", name);

        View();
    }
}