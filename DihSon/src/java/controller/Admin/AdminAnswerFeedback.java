/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Admin;

import dal.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import model.Feedback;
import ultils.Send;

/**
 *
 * @author admin
 */
public class AdminAnswerFeedback extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        Feedback f = Dao.getFeedbackById(id);
        request.setAttribute("feedback", f);               
        request.getRequestDispatcher("View/AnswerFeedback.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        Send send = new Send();
        try {
            send.sendMail(email, subject, message);
        } catch (MessagingException ex) {
            Logger.getLogger(AdminAnswerFeedback.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("adminfeedback");
    }


}
