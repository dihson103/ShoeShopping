/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Product;
import controller.Cart.CookieController;


public class Shop extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

        //ArrayList<Product> listProduct = Dao.getListProduct();
        ArrayList<Product> listProduct = new ArrayList<>();
        int categoryId = 0;
        int numberPaging = 1;
        int size = 6;
        int index = 1;
        String search = null;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (Exception e) {
        }
        try {
            categoryId = Integer.parseInt(request.getParameter("categoryid"));
            listProduct = Dao.getListProductByCategoryId(categoryId, index, size);
            numberPaging = Dao.getNumberAllProductByCategoryId(categoryId);
        } catch (Exception e) {
        }

        if (categoryId == 0) {
            try {
                search = request.getParameter("search");
                listProduct = Dao.getListProductBySearch(search, index, size);
                numberPaging = Dao.getNumberAllProductBySearch(search);
            } catch (Exception e) {
            }
            if (search == null) {
                listProduct = Dao.getListProductByIndex(index, size);
                numberPaging = Dao.getNumberALLProduct();
            }
        }
        int endPaging = numberPaging / size;
        if (numberPaging % size != 0) {
            endPaging++;
        }
        
        int numberProductInCart = CookieController.getNumberInCart(request, response);
        request.setAttribute("numberProductInCart", numberProductInCart);

        request.setAttribute("categoryid", categoryId);
        request.setAttribute("endPaging", endPaging);
        request.setAttribute("list", listProduct);
        request.setAttribute("index", index);
        request.setAttribute("search", search);
        request.getRequestDispatcher("View/shop.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

 
}
