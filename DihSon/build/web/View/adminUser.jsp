<%-- 
    Document   : adminUser
    Created on : 20-10-2022, 15:50:21
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
                        <th>Email</th>
                        <th>Full name</th>
                        <th>Phone</th>
                        <th>Role</th>
                        <th>Set Role</th>
                        <th></th>
                    </tr>
                <c:forEach items="${listUser}" var="u">
                    <tr class="table_text">
                        <th>${u.email}</th>
                        <th>${u.fullName}</th>
                        <th>${u.phone}</th>
                        <th>${u.roleId==1?"Admin":"User"}</th>
                        <th><a href="adminuser?idUpdate=${u.id}&&roleId=${u.roleId}">${u.roleId==1?"To User":"To Admin"}</a></th>
                        <th><a href="adminuser?idremove=${u.id}">Remove</a></th>
                    </tr>
                </c:forEach>
                    
                </table>
            </div>
        </div>


    </body>

</html>
