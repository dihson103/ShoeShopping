<%-- 
    Document   : AddNewProduct
    Created on : 24-10-2022, 07:22:43
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
        <link href="assets/css/form.css" rel="stylesheet" type="text/css"/>
        <link rel="apple-touch-icon" href="assets/img/apple-icon.png">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
    </head>
    <body>
        <form action="adminaddnewproduct" method="post" class="formm">
            <fieldset>
                <legend>Add new Product:</legend>
                <label for="name">Name (*):</label><br>
                <input type="text" class="form_input " id="fname" name="name" value="" required><br>
                <label for="image">Link image (*):</label><br>
                <input type="text" class="form_input " id="lname" name="image" value="" required><br>
                <label for="price">Price (*):</label><br>
                <input type="text" class="form_input " id="lname" name="price" value="" required><br>
                <label for="quanity">Quanity (*):</label><br>
                <input type="text" class="form_input " id="lname" name="quanity" value="" required><br>
                <label for="catefory">Category:</label><br>
                <select class="form_input " name="cate">
                    <option value="1">CONVERSE</option>
                    <option value="2">VANS</option>
                    <option value="3">PALLADIUM</option>
                </select><br>
                <label for="decription">Description:</label><br>
                <textarea name="descrip" rows="10" cols="95"></textarea><br>
                <input type="submit" class="form_input input_submit" value="Submit">
                &nbsp&nbsp&nbsp&nbsp<a href="admin">Back to admin</a>
            </fieldset>
        </form>
    </body>
</html>
