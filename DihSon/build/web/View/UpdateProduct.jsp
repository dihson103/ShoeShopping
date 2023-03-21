<%-- 
    Document   : UpdateProduct.jsp
    Created on : 23-10-2022, 11:38:47
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>DihSon Shop - Admin Page</title>
        <link rel="apple-touch-icon" href="assets/img/apple-icon.png">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <link href="assets/css/form.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form action="admineditproduct" method="post" class="formm">
            <fieldset>
                <c:set var="p" value="${requestScope.product}"></c:set>
                <legend>Edit Product:</legend>
                <label for="name">Name:</label><br>
                <input type="text" class="form_input " id="fname" name="name" value="${p.tittle}"><br>
                <label for="image">Link image:</label><br>
                <input type="text" class="form_input " id="lname" name="image" value="${p.image}"><br>
                <label for="price">Price:</label><br>
                <input type="text" class="form_input " id="lname" name="price" value="${p.price}"><br>
                <label for="quanity">Quanity:</label><br>
                <input type="text" class="form_input " id="lname" name="quanity" value="${p.number}"><br>
                <label for="decription">Description:</label><br>
                <textarea name="description" rows="10" cols="95">${p.description}</textarea><br>
                <input type="hidden" name="id" value="${p.id}">
                <input type="submit" class="form_input input_submit" value="Submit">
            </fieldset>
        </form>
    </body>
</html>
