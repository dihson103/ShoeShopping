<%-- 
    Document   : adminOrder
    Created on : 20-10-2022, 15:51:55
    Author     : admin
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>DihSon Shop - Admin Page</title>
        <link href="assets/css/admin.css" rel="stylesheet" type="text/css"/>
        <link rel="apple-touch-icon" href="assets/img/apple-icon.png">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

    </head>

    <body>
        <jsp:include page="adminMenu.jsp"></jsp:include>
            <div class="content">
                <div class="left"></div>

                <div class="right">
                    <input type="text" name="" id="" placeholder="Find">
                    <table>
                        <tr>
                            <th>Products</th>
                            <th>Order date</th>
                            <th>Total amount</th>
                            <th>Status</th>
                            <th>Allow oder</th>
                            <th></th>
                        </tr>
                        <tr class="table_text">
                            <c:forEach items="${listOrder}" var="o">
                                <th>
                                    <c:forEach items="${o.listIdItems}" var="i">
                                        <div class="product">
                                        <div class="product_img">
                                            <img src="${i.product.image}" alt="">
                                        </div>
                                        <div class="product_tittle">
                                            ${i.product.tittle}
                                        </div>
                                    </div>
                                    <div class="price" style="color: red; font-size: 15px;">Price: ${i.priceWhenSell} VND</div>
                                    </c:forEach>
                                </th>
                                <th style="width: 10%;">${o.orderDate}</th>
                                <th>444444 VND</th>
                                <th>${o.status}</th>
                                <th><a href="#" style="width: 15%;">Accept</a></th>
                                <th><a href="#" style="width: 15%;">Remove</a></th>
                            </c:forEach>
                        </tr>
                </table>
            </div>
        </div>


    </body>

</html>
