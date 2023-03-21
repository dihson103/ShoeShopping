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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Stack;
import model.Product;
import controller.Cart.CookieController;


public class ListItem extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
        }

        if (id > Dao.getIdOfTheLastProduct()) {
            id = 1;
        }
        Product product = new Product();
        product = Dao.getProductById(id);
        String brandName = Dao.getNameBrand(id);

        boolean isViewed = false;
        HttpSession ses = request.getSession();
        ArrayList<Product> listIdViewed = new ArrayList<>();
        if (ses.getAttribute("listViewed") == null) {
            listIdViewed.add(product);
        } else {
            listIdViewed = (ArrayList<Product>) ses.getAttribute("listViewed");
            for (Product pro : listIdViewed) {
                if (pro.getCategoryId() == product.getCategoryId() && pro.getId() == product.getId()) {
                    isViewed = true;
                    break;
                }
            }
            if (!isViewed) {
                listIdViewed.add(product);
            }
        }
        
        int numberProductInCart = CookieController.getNumberInCart(request, response);
        request.setAttribute("numberProductInCart", numberProductInCart);
        ses.setAttribute("listViewed", listIdViewed);

        //int number = Integer.parseInt(request.getParameter("number"));
//        if(request.getParameter("add")!=null){
//            int add = Integer.parseInt(request.getParameter("add"));
//            number += add;
//            if(number == 0)
//                number = 1;
//        }
        
        //request.setAttribute("number", number);
        request.setAttribute("product", product);
        request.setAttribute("id", id);
        request.setAttribute("brand", brandName);
        request.getRequestDispatcher("View/item.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

  

}
