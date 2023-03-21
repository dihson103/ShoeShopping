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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import model.User;
import ultils.Check;
import ultils.Send;


public class SignUp extends HttpServlet {

    
    int code;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("account");
        if (user == null || user.getFullName() == null) {
            Check check = new Check();
            code = check.createCheckCode();
            request.setAttribute("code", code);
            request.getRequestDispatcher("View/signup.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("View/home.jsp").forward(request, response);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException {
        //processRequest(request, response);
        Check check = new Check();
        String mess = null;
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String pass = check.MD5Encryption(request.getParameter("pass"));
        String rePass = check.MD5Encryption(request.getParameter("repass"));
        String checkCode = request.getParameter("checkCode");
        int code2 = 0;
        try {
            code2 = Integer.parseInt(checkCode);
        } catch (Exception e) {
        }
        try {
            if (Dao.getUserByEmail(email) != null) {
                throw new Exception("Account is already exist!");
            }
            if (!check.checkEmail(email)) {
                throw new Exception("Wrong format of email!");
            }
            if (!check.checkName(name)) {
                throw new Exception("Full name do not have special characters or number!");
            }
            if (request.getParameter("pass").contains(" ")) {
                throw new Exception("Password must not have space!");
            }
            if (!pass.equals(rePass)) {
                throw new Exception("Password must be same with re-password!");
            }
            if (code != code2) {
                throw new Exception("Wrong of check code!");
            }
            Send send = new Send();
            String body = "Registered successfully. Please verify your account using this link: "
                    + "http://localhost:8080/DihSon/checkcode?id=" +  check.MD5Encryption(email);

            User user = new User(name, email, pass);
            HttpSession ses = request.getSession();
            ArrayList<User> listUserSignUp;
            if (ses.getAttribute("listAccountSignUp") == null) {
                listUserSignUp = new ArrayList<>();
            } else {
                listUserSignUp = (ArrayList<User>) ses.getAttribute("listAccountSignUp");
                for (User u : listUserSignUp) {
                    if (u.getEmail().equals(email)) {
                        throw new Exception("This is an account pending verification!");
                    }
                }
            }
            send.sendMail(email, "CheckCode", body);

            listUserSignUp.add(user);
            ses.setAttribute("listAccountSignUp", listUserSignUp);
            throw new Exception("Please go to mail to check verify");
        } catch (Exception e) {
            Check checkLogin = new Check();
            code = checkLogin.createCheckCode();
            request.setAttribute("code", code);
            mess = e.getMessage();
            request.setAttribute("mess", mess);
            request.getRequestDispatcher("View/signup.jsp").forward(request, response);
        }

    }

  

}
