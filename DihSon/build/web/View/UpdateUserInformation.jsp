<%-- 
    Document   : UpdateUserInformation
    Created on : 24-10-2022, 03:34:30
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
        <form action="updateuserinfo" method="post" class="formm">
            <fieldset>
                <legend>Update account information:</legend>
                <label for="name">Full name:</label><br>
                <input type="text" class="form_input " id="fname" name="name" value="${account.fullName}"><br>
                <label for="image">Email:</label><br>
                <input type="text" class="form_input " id="lname" name="email" value="${account.email}"><br>
                <label for="quanity">Phone number:</label><br>
                <input type="text" class="form_input " id="lname" name="phone" value="${account.phone}"><br>
                <label for="quanity">Address:</label><br>
                <input type="text" class="form_input " id="lname" name="address" value="${account.address}"><br>
                <div style="color: red">${mess}</div><br>
                <input type="submit" class="form_input input_submit" value="Submit">
                &nbsp&nbsp<a href="loadnewitem">Back to home</a>
            </fieldset>
        </form>
    </body>
</html>
