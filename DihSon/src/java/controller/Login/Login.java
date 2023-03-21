/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Login;

import controller.Cart.CookieController;
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

public class Login extends HttpServlet {

    int code;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("account");
        if (user == null || user.getEmail() == null) {
            Check check = new Check();
            code = check.createCheckCode();
            request.setAttribute("code", code);
            request.getRequestDispatcher("View/signin.jsp").forward(request, response);
        } else {
            response.sendRedirect("loadnewitem");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        HttpSession ses = request.getSession();
        Check checkLogin = new Check();
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String checkCode = request.getParameter("checkCode");
        int check = 0;
        try {
            check = Integer.parseInt(checkCode);
        } catch (Exception e) {
        }
        User user = Dao.getUserByPassAndEmail(email, checkLogin.MD5Encryption(pass));
        if (user != null && !user.isIsDeleted()) {
            if (check == code) {
                ses.setAttribute("account", user);
                ses.setAttribute("isLogined", true);
                int numberProductInCart = CookieController.getNumberInCart(request, response);
                request.setAttribute("numberProductInCart", numberProductInCart);
                request.getRequestDispatcher("View/home.jsp").forward(request, response);
            } else {
                request.setAttribute("email", email);
                request.setAttribute("mess", "Wrong checkcode!");
                code = checkLogin.createCheckCode();
                request.setAttribute("code", code);
                request.getRequestDispatcher("View/signin.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("email", email);
            code = checkLogin.createCheckCode();
            request.setAttribute("code", code);
            request.setAttribute("mess", "Wrong email or password!");
            request.getRequestDispatcher("View/signin.jsp").forward(request, response);
        }

    }

}
