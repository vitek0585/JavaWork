package org.itstep.core;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Вадим on 16.11.2015.
 */
public class Controller {
    protected HttpServletRequest req;
    protected HttpServletResponse resp;
    protected String _controllerName;
    protected String _viewName;
    protected String _layoutName;

    public Controller() {
    }

    public Controller(HttpServletRequest req, HttpServletResponse resp) {
        this.resp = resp;
        this.req = req;
        _layoutName = "_Layout";
    }


    public HttpServletRequest getReq() {
        return req;
    }

    public void setReq(HttpServletRequest req) {
        this.req = req;
    }

    public HttpServletResponse getResp() {
        return resp;
    }

    public void setResp(HttpServletResponse resp) {
        this.resp = resp;
    }


    public void View(String viewName, String layoutName) throws ServletException, IOException {

        _viewName = viewName;
        _layoutName = layoutName;
        View();
    }
    public void View(String viewName) throws ServletException, IOException {

        _viewName = viewName;
        View();
    }
    public void View() throws ServletException, IOException {

        String view = "/WEB-INF/view/" + _controllerName + "/" + _viewName+".jsp";
        req.setAttribute("view", view);
        String layout = "/WEB-INF/Layout/" + _layoutName+".jsp";

        RequestDispatcher dispatcher = req.getRequestDispatcher(layout);
        dispatcher.forward(req, resp);
    }

}