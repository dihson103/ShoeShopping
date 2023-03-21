/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Cart;

import dal.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Cart;
import model.ItemInCart;

/**
 *
 * @author admin
 */
public class ChangeNumberItemInCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int add = Integer.parseInt(request.getParameter("add"));

        Cart cart = CookieController.getCartInCookie(request, response);
        int numberProducRemain = Dao.getProductById(id).getNumber();
        int number = cart.getItemById(id).getNumber();
        if (add == 1) {
            number += add;
            if (number > numberProducRemain) {
                number = numberProducRemain;
            }
        } else {
            number += add;
            if (number == 0) {
                number = 1;
            }
        }
        cart.getItemById(id).setNumber(number);
        
        ArrayList<ItemInCart> list = cart.getListItems();
        CookieController.updateCartToCookie(request, response, list);
        
        response.sendRedirect("cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
