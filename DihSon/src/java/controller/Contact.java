/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import controller.Cart.CookieController;
import dal.Dao;
import model.Feedback;
import ultils.Check;

public class Contact extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //processRequest(request, response);
        int numberProductInCart = CookieController.getNumberInCart(request, response);
        request.setAttribute("numberProductInCart", numberProductInCart);
        request.getRequestDispatcher("View/contact.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        Check check = new Check();
        Feedback f = new Feedback(email, name, subject, message, check.getDateNow());
        Dao.addFeedbackToDB(f);
        doGet(request, response);
    }

}
