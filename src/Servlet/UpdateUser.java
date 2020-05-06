package Servlet;

import Repository.UserRepository;
import Configs.Config;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@WebServlet("/update")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UpdateUser extends HttpServlet {
    private UserRepository userRepository;
    private static final String UPLOAD_DIR = "uploads";

    @Override
    public void init() throws ServletException {
        userRepository = UserRepository.getRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String applicationPath = request.getServletContext().getRealPath("");

        String uploadFilePath = applicationPath + UPLOAD_DIR;

        File uploadFolder = new File(uploadFilePath);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        PrintWriter writer = response.getWriter();
        String name = (String) request.getSession().getAttribute(Config.USER_NAME);
        int userId = (int) request.getSession().getAttribute(Config.USER_ID);
        Map<String, Object> context = new HashMap<>();
        context.put("name", name);

        for (Part part : request.getParts()) {
            if (part != null && part.getSize() > 0) {
                String fileName = part.getSubmittedFileName();
                part.write(uploadFilePath + File.separator + fileName);
                String sPath =Config.URL_PATH + File.separator + UPLOAD_DIR + File.separator + fileName;
                System.out.println(sPath);
                context.put("avatar", sPath);
            }
        }
        userRepository.updateStudentById(userId, context);
        response.sendRedirect(Config.URL_PATH + "/base");
    }
}
