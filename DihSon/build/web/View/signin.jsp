<%-- 
    Document   : signin
    Created on : 15-10-2022, 13:55:40
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
        <title>DihSon Shop - Login Page</title>
        <link href="assets/css/login.css" rel="stylesheet" type="text/css"/>

        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
    </head>
    <body>
        <div class="wrapper fadeInDown">
            <div id="formContent">
                <!-- Tabs Titles -->
                <h2 class="active"><a href="login"> Sign In</a></h2>
                <h2 class="inactive underlineHover"><a href="signup"> Sign Up </a></h2>

                <!-- Login Form -->
                <form method="post" action="login">
                    <input type="text" id="login" class="fadeIn second" name="email" placeholder="Email" required value="${email!=null?email:""}">
                    <input type="text" id="password" class="fadeIn third" name="pass" placeholder="Password" required value="">
                    <input type="text" id="password" class="fadeIn third" name="checkCode" placeholder="Enter code: ${code}" required value="">
                    <br>
                    <div style="color: red">${mess}</div>
                    <br>
                    <input type="submit" class="fadeIn fourth" value="Log In">
                </form>

                <!-- Remind Passowrd -->
                <div id="formFooter">
                    <a class="underlineHover" href="forgetpass">Forgot Password?</a>&nbsp &nbsp
                    <a class="underlineHover" href="loadnewitem">Back to home</a>

                </div>

            </div>
        </div>
    </body>
</html>
