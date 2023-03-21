

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
                <h2 class="active"> Change password </a</h2>

                <!-- Login Form -->
                <form method="post" action="changepassword">
                    <input type="text" id="login" class="fadeIn second" name="oldpass" placeholder="Old Password" required>
                    <input type="text" id="password" class="fadeIn third" name="pass" placeholder="New Password" required>
                    <input type="text" id="password" class="fadeIn third" name="repass" placeholder="ReNew-password" required>
                    <input type="text" id="password" class="fadeIn third" name="checkCode" placeholder="Enter code: ${code}" required>
                    <br><div style="color: red">${mess}</div><br>
                    <input type="submit" class="fadeIn fourth" value="Submit">
                </form>

                <div id="formFooter">
                    <a class="underlineHover" href="loadnewitem">Back to home</a>

                </div>

            </div>
        </div>
    </body>
</html>