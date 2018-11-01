package servlets;

import database.DBException;
import database.DBService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yekaterina Savelyeva
 * on 24.10.2018
 */

public class SignUpServlet extends HttpServlet {

    private final DBService service;

    public SignUpServlet(DBService service) {
        this.service = service;
    }

    //get page
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

    }

    //sign up
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

       try {

           if (service.getUser(login) == null) {
               response.setContentType("text/html;charset=utf-8");
               long userId = service.addUser(login, pass);
               response.getWriter().println("Registration Successful");
               response.setStatus(HttpServletResponse.SC_OK);
           }
       } catch (DBException dbe){dbe.printStackTrace();}





    }
}
