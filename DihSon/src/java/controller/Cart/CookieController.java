/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Cart;

import dal.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Cart;
import model.ItemInCart;
import model.Product;

/**
 *
 * @author admin
 */
public class CookieController {

    public static void updateCartToCookie(HttpServletRequest request, HttpServletResponse response, ArrayList<ItemInCart> items)
            throws ServletException, IOException {
        String txt ="";
        if(!items.isEmpty()){
            txt = items.get(0).getProduct().getId() + ":" + items.get(0).getNumber();
            for (int i = 1; i < items.size(); i++) {
                txt += "+" + items.get(i).getProduct().getId() + ":" + items.get(i).getNumber();
            }
        }
        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(2*24*60*60);
        response.addCookie(c);
    }
    
    public static Cart getCartInCookie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //ArrayList<Product> listNewProduct = Dao.getListNewProduct();
        Cookie[] arr = request.getCookies();
        String stringCart = "";
        if (arr != null) {
            for (Cookie cookie : arr) {
                if (cookie.getName().equals("cart")) {
                    stringCart += cookie.getValue();
                }
            }
        }
        return new Cart(stringCart);
    }

    public static void addItemToCookieCart(HttpServletRequest request, HttpServletResponse response, String number, String id)
            throws ServletException, IOException {
        Cookie[] arr = request.getCookies();
        String stringCart = "";
        if (arr != null) {
            for (Cookie cookie : arr) {
                if (cookie.getName().equals("cart")) {
                    stringCart += cookie.getValue();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        if (stringCart.isEmpty()) {
            stringCart = id + ":" + number;
        } else {
            stringCart += "+" + id + ":" + number;
        }
        Cookie c = new Cookie("cart", stringCart);
        c.setMaxAge(2*24*60*60);
        response.addCookie(c);
    }

    public static int getNumberInCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return getCartInCookie(request, response).getNumberItemInCart();
    }
   
}
