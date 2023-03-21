<%-- 
    Document   : checkCodeVerify
    Created on : 18-10-2022, 09:18:12
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>DihSon Shop - Verify Page</title>
        <link href="assets/css/login.css" rel="stylesheet" type="text/css"/>

        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
    </head>
    <body>
        <div class="wrapper fadeInDown">
            <div id="formContent">
                <!-- Tabs Titles -->
                <h2 class="active"><a href="signup"> Verify </a></h2>

                <!-- Login Form -->
                <form method="post" action="checkcode">
                    <input type="text" id="login" class="fadeIn second" name="verifyCode" placeholder="Verify code" required>
                    <input type="text" id="login" class="fadeIn second" name="pass" placeholder="New password" required>
                    <input type="text" id="login" class="fadeIn second" name="repass" placeholder="Re-NewPassword" required>
                    <input type="hidden" id="login" class="fadeIn second" name="codeVerify" value="${codeVerify}">
                    <input type="hidden" id="login" class="fadeIn second" name="email" value="${email}">
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
