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
import ultils.Check;
import ultils.Send;

public class ForgetPass extends HttpServlet {

    int code;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        Check check = new Check();
        code = check.createCheckCode();
        request.setAttribute("code", code);
        request.getRequestDispatcher("View/forgetpass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String email = request.getParameter("email");
        String checkCode = request.getParameter("checkCode");
        int code2 = 0;
        Check check = new Check();
        try {
            code2 = Integer.parseInt(checkCode);
        } catch (Exception e) {
        }
        String mess = null;
        try {
            if (Dao.getUserByEmail(email) == null) {
                throw new Exception("Account is not exist!");
            }
            if (code2 != code) {
                throw new Exception("Wrong checkcode!");
            }
            //send email

            Send send = new Send();
            int codeVerify = check.createCheckCode();
            String body = "Your verify code is: " + codeVerify;
            send.sendMail(email, "CheckCode", body);
            request.setAttribute("email", email);
            request.setAttribute("codeVerify", check.MD5Encryption(Integer.toString(codeVerify)));
            request.getRequestDispatcher("View/checkCodeVerify.jsp").forward(request, response);
        } catch (Exception e) {
            mess = e.getMessage();
            code = check.createCheckCode();
            request.setAttribute("code", code);
            request.setAttribute("mess", mess);
            request.getRequestDispatcher("View/forgetpass.jsp").forward(request, response);
        }
    }

}
