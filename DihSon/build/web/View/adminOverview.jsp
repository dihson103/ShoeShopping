<%-- 
    Document   : admin
    Created on : 19-10-2022, 14:51:12
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
            <div class="left">

            </div>

            <div class="right">
                <div class="right_content">
                    <div class="right_main">
                        <h2>Number Product</h2>
                        <h2 class="number">${numberProduct}</h2>
                    </div>
                    <div class="right_main">
                        <h2>Number Account</h2>
                        <h2 class="number">${numberAccount}</h2>
                    </div>
                </div>
                <div class="right_content">
                    <div class="right_main">
                        <h2>Number sell in day</h2>
                        <h2 class="number">${numberProductSellInDay}</h2>
                    </div>
                    <div class="right_main">
                        <h2>Totalamount sell in day</h2>
                        <h2 class="number">${amountSellInDay} VND</h2>
                    </div>
                </div>
                <div class="right_content">
                    <div class="right_main">
                        <h2>Number sell in month</h2>
                        <h2 class="number">${numberProductSellInMonth}</h2>
                    </div>
                    <div class="right_main">
                        <h2>Total amount sell in month</h2>
                        <h2 class="number">${amountSellInMonth} VND</h2>
                    </div>
                </div>
                <div class="right_content">
                    <div class="right_main">
                        <h2>Number sell in year</h2>
                        <h2 class="number">${numberProductSellInYear}</h2>
                    </div>
                    <div class="right_main">
                        <h2>Total amount sell in year</h2>
                        <h2 class="number">${amountSellInYear} VND</h2>
                    </div>
                </div>

            </div>
        </div>

    </body>

</html>