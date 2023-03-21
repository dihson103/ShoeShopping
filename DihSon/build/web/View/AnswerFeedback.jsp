<%-- 
    Document   : AnswerFeedback
    Created on : 23-10-2022, 18:23:11
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
        <link href="assets/css/answer.css" rel="stylesheet" type="text/css"/>
        <link rel="apple-touch-icon" href="assets/img/apple-icon.png">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
    </head>
    <body>
        <div class="feedback">
            <div class="message">
                <div class="mess">
                    <div class="mess_left">Email:</div>&ensp;
                    <div class="mess_right">${feedback.email}</div>
                </div>
                <div class="mess">
                    <div class="mess_left">Full name:</div>&ensp;
                    <div class="mess_right">${feedback.name}</div>
                </div>
                <div class="mess">
                    <div class="mess_left">Date:</div>&ensp;
                    <div class="mess_right">${feedback.date}</div>
                </div>
                <div class="mess">
                    <div class="mess_left">Subject:</div>&ensp;
                    <div class="mess_right">${feedback.subject}</div>
                </div>
                <div class="mess">
                    <div class="mess_left">Message:</div>&ensp;
                    <div class="mess_right">${feedback.mess}</div>
                </div>
            </div>
            <div class="answer">

            </div>
        </div>
        <form action="adminanswerfeedback" method="post" class="formm">
            <fieldset>
                <legend>Answer feedback:</legend>
                <label for="name">To:</label><br>
                <input type="text" class="form_inputter " id="fname" name="email" value="${feedback.email}"><br>
                <label for="image">Subject:</label><br>
                <input type="text" class="form_inputter " id="lname" name="subject" value="Answer feedback"><br>
                <label for="decription">Message:</label><br>
                <textarea name="message" rows="10" cols="50"></textarea><br>
                <input type="submit" class="form_inputter input_submitter" value="Submit">
            </fieldset>
        </form>
    </body>
</html>
