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

public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int numberProduct = Dao.getNumberALLProduct();
        int numberAccount = Dao.getNumberAccount();
        int numberProductSellInDay = Dao.getNumberProductSellInDay();
        double amountSellInDay = Dao.getAmountSellInDay();
        int numberProductSellInMonth = Dao.getNumberProductSellInMonth();
        double amountSellInMonth = Dao.getAmountSellInMonth();
        int numberProductSellInYear = Dao.getNumberProductSellInYear();
        double amountSellInYear = Dao.getAmountSellInYear();

        request.setAttribute("numberProduct", numberProduct);
        request.setAttribute("numberAccount", numberAccount);
        request.setAttribute("numberProductSellInDay", numberProductSellInDay);
        request.setAttribute("amountSellInDay", amountSellInDay);
        request.setAttribute("numberProductSellInMonth", numberProductSellInMonth);
        request.setAttribute("amountSellInMonth", amountSellInMonth);
        request.setAttribute("numberProductSellInYear", numberProductSellInYear);
        request.setAttribute("amountSellInYear", amountSellInYear);
        request.setAttribute("isOverView", true);
        request.getRequestDispatcher("View/adminOverview.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
