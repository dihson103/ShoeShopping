/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
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
public class RemoveItemInCart extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cart cart = CookieController.getCartInCookie(request, response);
        ArrayList<ItemInCart> list = cart.getListItems();
        for (ItemInCart item : list) {
            if(item.getProduct().getId() == id){
                list.remove(item);
                break;
            }
        }
        CookieController.updateCartToCookie(request, response, list);
        Cart c = CookieController.getCartInCookie(request, response);
        int number = c.getNumberItemInCart();
        response.sendRedirect("cart");
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

    
}
