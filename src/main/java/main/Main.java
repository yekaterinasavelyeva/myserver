package main;

import database.DBService;
import database.DBServiceImpl;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

/**
 * Created by Yekaterina Savelyeva
 * on 19.10.2018
 */

public class Main {

    public static void main (String [] args) throws Exception {


        DBService service = new DBServiceImpl();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new SignUpServlet(service)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(service)), "/signin");


        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server  = new Server(8080);
        server.setHandler(handlers);


        server.start();
        System.out.println("Server started");
        server.join();
    }
}
