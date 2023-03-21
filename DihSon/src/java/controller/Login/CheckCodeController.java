/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Login;

import dal.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.User;
import ultils.Check;

public class CheckCodeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

        //request.getRequestDispatcher("View/home.jsp").forward(request, response);
        String id = request.getParameter("id");
        HttpSession ses = request.getSession();
        if (ses.getAttribute("listAccountSignUp") == null) {
            request.getRequestDispatcher("View/home.jsp").forward(request, response);
        } else {
            Check check = new Check();
            ArrayList<User> listUserSignUp = (ArrayList<User>) ses.getAttribute("listAccountSignUp");
            for (User user : listUserSignUp) {
                if (check.MD5Encryption(user.getEmail()).equals(id)) {
                    Dao.addNewUserToDB(user);
                    request.setAttribute("mess", "Your account is active. Login to shopping.");
                    request.getRequestDispatcher("login").forward(request, response);
                }
            }
            request.getRequestDispatcher("View/home.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Check check = new Check();
        String codeVerify = request.getParameter("codeVerify");
        String codeUserEnter = check.MD5Encryption(request.getParameter("verifyCode"));
        String pass = request.getParameter("pass");
        String rePass = request.getParameter("repass");
        String email = request.getParameter("email");

        try {
            if (!codeUserEnter.equals(codeVerify)) {
                throw new Exception("Wrong checkCode!");
            }
            if(pass.contains(" ")){
                throw new Exception("Password can not contain space!");
            }
            if (!pass.equals(rePass)) {
                throw new Exception("Wrong password!");
            }
            Dao.changePassByEmail(email, check.MD5Encryption(pass));
            response.sendRedirect("changesuccess");
        } catch (Exception e) {
            request.setAttribute("mess", e.getMessage());
            request.setAttribute("codeVerify", codeVerify);
            request.getRequestDispatcher("View/checkCodeVerify.jsp").forward(request, response);
        }

        //request.getRequestDispatcher("View/checkCodeVerify.jsp").forward(request, response);
    }
}
