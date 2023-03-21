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
import java.util.ArrayList;
import model.User;

/**
 *
 * @author admin
 */
public class AdminUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idRemove = request.getParameter("idremove");
        if (idRemove != null && !idRemove.equals("1")) {
            Dao.deleteUserById(idRemove);
        } else {
            String idUpdate = request.getParameter("idUpdate");
            if (idUpdate != null && !idUpdate.equals("1")) {
                String roleId = request.getParameter("roleId");
                Dao.updateRoleUser(idUpdate, roleId);
            }
        }

        ArrayList<User> listUser = Dao.getListUser();
        request.setAttribute("listUser", listUser);
        request.setAttribute("isAdminUser", true);
        request.getRequestDispatcher("View/adminUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
