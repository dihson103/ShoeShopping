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
import model.Product;

/**
 *
 * @author admin
 */
public class EditProduct extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        int idd = Integer.parseInt(id);
        if(idd > Dao.getIdOfTheLastProduct()){
            idd = 1;
        }
        Product p = Dao.getProductById(idd);
        request.setAttribute("product", p);
        request.getRequestDispatcher("View/UpdateProduct.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        int idd = Integer.parseInt(id);
        String tittle = request.getParameter("name");
        String image = request.getParameter("image");
        double price = Double.parseDouble(request.getParameter("price"));
        int quanity = Integer.parseInt(request.getParameter("quanity"));
        String description = request.getParameter("description");
        Product p = new Product();
        p.setId(idd);
        p.setImage(image);
        p.setDescription(description);
        p.setNumber(quanity);
        p.setPrice(price);
        p.setTittle(tittle);
        Dao.updateProduct(p);
        response.sendRedirect("adminproduct");
    }


}
