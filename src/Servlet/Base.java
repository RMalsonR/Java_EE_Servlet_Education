package Servlet;

import Configs.Config;
import Entity.User;
import Repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "base", urlPatterns = "/base")
public class Base extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = UserRepository.getRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = (String) request.getSession().getAttribute(Config.LOGGED_IN_USER);
        if (user != null){
            User userEntity = userRepository.getUserInfoById((int)request.getSession().getAttribute(Config.USER_ID));
            request.setAttribute("name", request.getSession().getAttribute(Config.USER_NAME));
            request.setAttribute("avatar", User.getAvatar());
            getServletContext().getRequestDispatcher("/base.jsp").forward(request, response);
        } else response.sendRedirect(Config.URL_PATH + "/login");
    }
}
