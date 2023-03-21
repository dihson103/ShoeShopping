<%-- 
    Document   : InputorderInformation
    Created on : 24-10-2022, 10:57:07
    Author     : admin
--%>

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
        <form action="checkout" method="post" class="formm">
        <fieldset>
            <legend>User order information:</legend>
            <div style="color: red">${requestScope.mess}</div>
            <label for="name">Name (*):</label><br>
            <input type="text" class="form_input " id="fname" name="name" value="${sessionScope.account.fullName}"><br>
            <label for="image">Email (*):</label><br>
            <input type="text" class="form_input " id="lname" name="email" value="${sessionScope.account.email}"><br>
            <label for="price">Phone (*):</label><br>
            <input type="text" class="form_input " id="lname" name="phone" value="${sessionScope.account.phone}"><br>
            <label for="quanity">Address (*):</label><br>
            <input type="text" class="form_input " id="lname" name="address" value="${sessionScope.account.address}"><br>
            <input type="submit" class="form_input input_submit" value="Submit">
        </fieldset>
    </form>
        
    </body>
</html>
