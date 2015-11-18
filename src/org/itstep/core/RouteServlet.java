package org.itstep.core;

import org.itstep.controller.TestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Вадим on 16.11.2015.
 */
public class RouteServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String []segments = req.getRequestURI().split("/");
        String controllerName;
        String actionName;
        if(segments.length>0){
            controllerName = req.getRequestURI().split("/")[1];

        }  else
        {
            controllerName = "Test";
        }
        if(segments.length>2){
            actionName = req.getRequestURI().split("/")[2];
        }else{
            actionName = "Test";
        }

        System.out.print(controllerName);

        String controllerClassName = "org.itstep.controller." + controllerName.toUpperCase().charAt(0) +
                controllerName.toLowerCase().substring(1)+"Controller";

        try {
            Class controllerClass = Class.forName(controllerClassName);
            Constructor constructor = controllerClass.getConstructor(HttpServletRequest.class, HttpServletResponse.class,String.class);

            Object controller = constructor.newInstance(req, resp,actionName.toUpperCase().charAt(0) +
                    actionName.toLowerCase().substring(1));

            Method action = controller.getClass().getMethod(actionName.toUpperCase().charAt(0) +
                    actionName.toLowerCase().substring(1), null);

            action.invoke(controller);

        } catch (NoSuchMethodException e) {
            out.print(actionName);
            out.print("404 Not Found 1");
        } catch (InvocationTargetException e) {
            out.print("404 Not Found 2");
        } catch (IllegalAccessException e) {
            out.print("404 Not Found 3");
        } catch (ClassNotFoundException e) {
            out.print("404 Not Found 4");
        } catch (InstantiationException e) {
            out.print("404 Not Found 5");
        }
    }

}