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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Cart;
import model.ItemInCart;
import model.User;
import ultils.Check;

/**
 *
 * @author admin
 */
public class CheckOut extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<ItemInCart> listChoose = (ArrayList<ItemInCart>) session.getAttribute("listChoose");

        if (session.getAttribute("listChoose") == null || listChoose.isEmpty()) {
            request.setAttribute("mess", "Choose item please!");
            request.getRequestDispatcher("cart").forward(request, response);
        } else {
            request.getRequestDispatcher("View/InputorderInformation.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        ArrayList<ItemInCart> listChoose = (ArrayList<ItemInCart>) session.getAttribute("listChoose");

        User user = null;
        if (session.getAttribute("account") != null) {
            user = (User) session.getAttribute("account");
        }
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Check check = new Check();
        try {
            if (user == null) {
                if (!check.checkEmail(email)) {
                    throw new Exception("Wrong format of email!");
                }
                if (!check.checkName(name)) {
                    throw new Exception("Wrong format of name!");
                }
                if (!check.checkPhone(phone)) {
                    throw new Exception("Wrong format of phone!");
                }
                Dao.addOrderToDbIfHasNoUserID(name, email, phone, address);
            } else {
                if (!user.getEmail().equals(email) || !user.getFullName().equals(name)
                        || !user.getPhone().equals(phone)) {
                    if (!check.checkEmail(email)) {
                        throw new Exception("Wrong format of email!");
                    }
                    if (!check.checkName(name)) {
                        throw new Exception("Wrong format of name!");
                    }
                    if (!check.checkPhone(phone)) {
                        throw new Exception("Wrong format of phone!");
                    }
                }
                Dao.addOrderToDbIfHasUserID(user, name, email, phone, address);
            }
            Dao.addOrderToOrderDetail(listChoose, email);

            Cart cart = CookieController.getCartInCookie(request, response);
            ArrayList<ItemInCart> list = cart.getListItems();
            for (ItemInCart item : list) {
                for (ItemInCart i : listChoose) {
                    if (item.getProduct().getId() == i.getProduct().getId()) {
                        list.remove(item);
                    }
                }
            }
            CookieController.updateCartToCookie(request, response, list);
//                Cookie c = new Cookie("cart", "");
//                c.setMaxAge(0);
//                response.addCookie(c);
            response.sendRedirect("cart");
        } catch (Exception e) {
            String mess = e.getMessage();
            request.setAttribute("mess", mess);
            request.getRequestDispatcher("View/InputorderInformation.jsp").forward(request, response);
        }
    }

}
