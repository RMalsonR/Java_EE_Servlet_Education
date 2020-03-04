package Servlet;

import Repository.UserRepository;
import Configs.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "login", urlPatterns = "/login")
public class Login extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = UserRepository.getRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("login");
        String password = request.getParameter("password");
        HashMap<String, String> dbResponse = userRepository.getUserIdNameByLogin(username, password);
        if (dbResponse != null) {
            String id = dbResponse.get("id");
            String name = dbResponse.get("name");
            request.getSession().setAttribute(Config.LOGGED_IN_USER, "true");
            request.getSession().setAttribute(Config.USER_ID, Integer.parseInt(id));
            request.getSession().setAttribute(Config.USER_NAME, name);
            response.sendRedirect(Config.URL_PATH + "/base");
        } else {
            request.setAttribute("error", true);
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", false);
        if (request.getSession().getAttribute(Config.LOGGED_IN_USER) != null) {
            System.out.println(request.getSession().getAttribute(Config.LOGGED_IN_USER));
            response.sendRedirect(Config.URL_PATH + "/base");
            return;
        }
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
