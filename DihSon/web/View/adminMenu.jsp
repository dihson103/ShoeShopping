<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="menu">
    <h2 class="admin_menu">Admin menu</h2>
    <div class="left_all">
        <div class="left_content" ${isOverView?"style=\"background-color: aquamarine\"":""}><a href="admin" class="textt"> Overview</a></div>
        <div class="left_content" ${isAdminProduct?"style=\"background-color: aquamarine\"":""}><a href="adminproduct" class="textt">Product management</a></div>
        <div class="left_content" ><a href="adminaddnewproduct" class="textt">Add new product</a></div>
        <div class="left_content" ${isAdminUser?"style=\"background-color: aquamarine\"":""}><a href="adminuser" class="textt">User account management</a></div>
        <div class="left_content" ${isAdminOrder?"style=\"background-color: aquamarine\"":""}><a href="adminorder" class="textt">Order management</a></div>
        <div class="left_content" ${isAdminFeedback?"style=\"background-color: aquamarine\"":""}><a href="adminfeedback" class="textt">Feedback management</a></div>
        <div class="left_content"><a href="logout" class="textt">Log out</a></div>
        <div class="left_content"><a href="loadnewitem" class="textt">Back To Home</a></div>
    </div>
</div>