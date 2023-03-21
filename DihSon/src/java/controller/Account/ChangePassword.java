/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Account;

import dal.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import ultils.Check;

/**
 *
 * @author admin
 */
public class ChangePassword extends HttpServlet {

    int code;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        if(ses.getAttribute("account") == null){
            response.sendRedirect("loadnewitem");
        }
        User user = (User) ses.getAttribute("account");
        if (user.getEmail() != null) {
            Check check = new Check();
            code = check.createCheckCode();
            request.setAttribute("code", code);
            request.getRequestDispatcher("View/ChangePassword.jsp").forward(request, response);
        } else {
            response.sendRedirect("loadnewitem");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldPass = request.getParameter("oldpass");
        String newpass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        int checkCode = Integer.parseInt(request.getParameter("checkCode"));
        
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("account");
        Check check = new Check();
        User u = Dao.getUserByPassAndEmail(user.getEmail(), check.MD5Encryption(oldPass));
        try {
            if(u == null){
                throw new Exception("Wrong password");
            }
            if(newpass.contains(" ")){
                throw new Exception("Password can not contain space!");
            }
            if(!newpass.equals(repass)){
                throw new Exception("New password and renew password must be the same!");
            }
            if(checkCode != code){
                throw new Exception("Wrong checkCode!");
            }
            Dao.changePassByEmail(user.getEmail(), check.MD5Encryption(newpass));
            response.sendRedirect("changesuccess");
        } catch (Exception e) {
            String mess = e.getMessage();
            code = check.createCheckCode();
            request.setAttribute("code", code);
            request.setAttribute("mess", mess);
            request.getRequestDispatcher("View/ChangePassword.jsp").forward(request, response);
        }

    }

}
