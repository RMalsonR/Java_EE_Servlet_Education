package Servlet;

import Configs.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "base", urlPatterns = "/base")
public class Base extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = (String) request.getSession().getAttribute(Config.LOGGED_IN_USER);

        if (user != null){
            request.setAttribute("name", request.getSession().getAttribute(Config.USER_NAME));
        } else response.sendRedirect(Config.URL_PATH + "/login");
        getServletContext().getRequestDispatcher("/base.jsp").forward(request, response);
    }
}
