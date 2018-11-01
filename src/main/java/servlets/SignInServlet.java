package servlets;

import domain.UserProfile;
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

public class SignInServlet extends HttpServlet {

    private final DBService service;

    public SignInServlet(DBService service) {
        this.service = service;
    }

    //get public user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        try {
            String sessionId = request.getSession().getId();
            long id = service.getUserIdBySessionId(sessionId);
            UserProfile profile = service.getUser(id);
            if (profile == null) {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Authorized: " + profile.getLogin());
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (DBException dex){
            dex.printStackTrace();
        }
    }

    //sign in
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
           UserProfile profile = service.getUser(login);
           if (profile == null || !profile.getPass().equals(pass)) {
               response.setContentType("text/html;charset=utf-8");
               response.getWriter().println("Unauthorized");
               response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
               return;
           }

           service.addSession(profile.getId(), request.getSession().getId());

           response.setContentType("text/html;charset=utf-8");
           response.getWriter().println("Authorized: " + login);
           response.setStatus(HttpServletResponse.SC_OK);
       } catch (DBException dex){dex.printStackTrace();}
    }
}
