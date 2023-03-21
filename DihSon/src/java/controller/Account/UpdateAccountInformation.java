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
public class UpdateAccountInformation extends HttpServlet {
  
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
            request.setAttribute("account", user);
            request.getRequestDispatcher("View/UpdateUserInformation.jsp").forward(request, response);
        } else {
            response.sendRedirect("loadnewitem");
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("account");
        Check check = new Check();
        
        try {
            if(!email.equals(user.getEmail())){
                if(!check.checkEmail(email)){
                    throw new Exception("Wrong format of email!");
                }
                if(Dao.getUserByEmail(email) != null){
                    throw new Exception("This email is aldready has account!");
                }
            }
            if(!check.checkName(name)){
                throw new Exception("Full name do not have special characters or number!");
            }
            if(!check.checkPhone(phone)){
                throw new Exception("Phone has 10 number only!");
            }
            user.setEmail(email);
            user.setPhone(phone);
            user.setFullName(name);
            user.setAddress(address);
            Dao.updateAccountInfor(user);
            response.sendRedirect("changesuccess");
        } catch (Exception e) {
            String mess = e.getMessage();
            code = check.createCheckCode();
            request.setAttribute("account", user);
            request.setAttribute("code", code);
            request.setAttribute("mess", mess);
            request.getRequestDispatcher("View/UpdateUserInformation.jsp").forward(request, response);
        }
    }

}
