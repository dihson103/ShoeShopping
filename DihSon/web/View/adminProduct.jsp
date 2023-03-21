<%-- 
    Document   : adminProduct
    Created on : 20-10-2022, 15:00:52
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <select>
                        <option>ALL</option>
                        <option>Vans</option>
                        <option>Converse</option>
                        <option>Paladium</option>
                    </select>
                    <table>
                        <tr>
                            <th style="width: 50%;">Product</th>
                            <th style="width: 10%;">Quanity </th>
                            <th style="width: 20%;">Price</th>
                            <th style="width: 6%;"></th>
                            <th style="width: 7%;"></th>
                            <th style="width: 7%;"></th>
                        </tr>
                    <c:forEach items="${listProduct}" var="p">
                        <tr>
                            <th>
                                <div class="product">
                                    <div class="product_img">
                                        <img src="${p.image}" alt="">
                                    </div>
                                    <div class="product_tittle">
                                        ${p.tittle}
                                    </div>
                                </div>
                            </th>
                            <th>${p.number}</th>
                            <th>${p.price} VND</th>
                            <th><a href="itemcontroller?id=${p.id}&&number=1">View</a></th>
                            <th><a href="admineditproduct?id=${p.id}">Edit</a></th>
                            <th><a href="adminproduct?idRemove=${p.id}">Remove</a></th>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>


    </body>

</html>
