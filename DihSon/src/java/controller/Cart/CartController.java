/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Cart;

import dal.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Cart;
import model.ItemInCart;
import model.ListChoose;
import model.Product;

/**
 *
 * @author admin
 */
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cart cart = CookieController.getCartInCookie(request, response);
        
        
        int numberProductInCart = cart.getNumberItemInCart();
        request.setAttribute("listCart", cart.getListItems());
        request.setAttribute("total", cart.getTotalAmount());
        request.setAttribute("numberProductInCart", numberProductInCart);
        request.getRequestDispatcher("View/Cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameterValues("checkBox") != null) {
            String[] arr = request.getParameterValues("checkBox");
            Cart cart = CookieController.getCartInCookie(request, response);
            ArrayList<ItemInCart> listItems = cart.getListItems();
            ArrayList<ItemInCart> listChoose = new ArrayList<>();
            for (String str : arr) {
                for (ItemInCart item : listItems) {
                    if (item.getProduct().getId() == Integer.parseInt(str)) {
                        item.setIsChoose(true);
                        listChoose.add(item);
                    }
                }
            }
            ListChoose listC = new ListChoose(listChoose);

            HttpSession session = request.getSession();
            session.setAttribute("listChoose", listChoose);

            int numberProductInCart = cart.getNumberItemInCart();
            request.setAttribute("listCart", cart.getListItems());
            request.setAttribute("total", listC.getTotalAmount());
            request.setAttribute("numberProductInCart", numberProductInCart);
            request.getRequestDispatcher("View/Cart.jsp").forward(request, response);
        } else {
            doGet(request, response);
        }
    }

}
