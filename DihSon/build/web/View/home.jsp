<%-- 
    Document   : home
    Created on : 09-10-2022, 21:39:05
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>DihSon Shop - Home Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="apple-touch-icon" href="assets/img/apple-icon.png">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/templatemo.css">
        <link rel="stylesheet" href="assets/css/custom.css">
        <link rel="stylesheet" href="assets/css/menu.css">

        <!-- Load fonts style after rendering the layout styles -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
        <link rel="stylesheet" href="assets/css/fontawesome.min.css">
        <!--
            
        TemplateMo 559 Zay Shop
        
        https://templatemo.com/tm-559-zay-shop
        
        -->
    </head>

    <body>
        <jsp:include page="menu.jsp"></jsp:include>



            <!-- Start Banner Hero -->
            <div class="banner">
                <img class="mySlides" src="https://drake.vn/image/cache/catalog/banner/banner-l%E1%BB%9Bn/web-Palladium-Pallabrousse-Legion-1500x560.jpg" style="width:100%">
                <img class="mySlides" src="https://drake.vn/image/cache/catalog/banner/banner-l%E1%BB%9Bn/web-vans-classic-1500x560.jpg" style="width:100%">
                <img class="mySlides" src="https://drake.vn/image/cache/catalog/banner/banner-l%E1%BB%9Bn/web-converse-classic1-1500x560.jpg" style="width:100%">
            </div>

            <script>
                var myIndex = 0;
                carousel();

                function carousel() {
                    var i;
                    var x = document.getElementsByClassName("mySlides");
                    for (i = 0; i < x.length; i++) {
                        x[i].style.display = "none";
                    }
                    myIndex++;
                    if (myIndex > x.length) {
                        myIndex = 1
                    }
                    x[myIndex - 1].style.display = "block";
                    setTimeout(carousel, 2000); // Change image every 2 seconds
                }
            </script>
            <!-- End Banner Hero -->


            <!-- Start Categories of The Month -->
            <section class="container py-5">
                <div class="row text-center pt-3">
                    <div class="col-lg-6 m-auto">
                        <h1 class="h1">Categories of The Month</h1>
                        <p>
                            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                            deserunt mollit anim id est laborum.
                        </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 col-md-4 p-5 mt-3">
                        <a href="load?categoryid=1"><img src="./assets/image/logo.jpg" class="rounded-circle img-fluid border"></a>
                        <h5 class="text-center mt-3 mb-3">Converse Shoes</h5>
                        <p class="text-center"><a href="load?categoryid=1" class="btn btn-success">Go Shop</a></p>
                    </div>
                    <div class="col-12 col-md-4 p-5 mt-3">
                        <a href="load?categoryid=2"><img src="./assets/image/HD-wallpaper-vans-logo-brands.jpg" class="rounded-circle img-fluid border"></a>
                        <h2 class="h5 text-center mt-3 mb-3">Vans Shoes</h2>
                        <p class="text-center"><a href="load?categoryid=2" class="btn btn-success">Go Shop</a></p>
                    </div>
                    <div class="col-12 col-md-4 p-5 mt-3">
                        <a href="load?categoryid=3"><img src="./assets/image/palladium.png" class="rounded-circle img-fluid border"></a>
                        <h2 class="h5 text-center mt-3 mb-3">Palladium Shoes</h2>
                        <p class="text-center"><a href="load?categoryid=3" class="btn btn-success">Go Shop</a></p>
                    </div>
                </div>
            </section>
            <!-- End Categories of The Month -->


            <!-- Start Featured Product -->
            <section class="bg-light">
                <div class="container py-5">
                    <div class="row text-center py-3">
                        <div class="col-lg-6 m-auto">
                            <h1 class="h1">Featured Product</h1>
                            <p>
                                Reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                                Excepteur sint occaecat cupidatat non proident.
                            </p>
                        </div>
                    </div>
                    <div class="row">
                    <c:forEach items="${listNew}" var="ls">
                        <div class="col-12 col-md-4 mb-4">
                            <div class="card h-100">
                                <a href="itemcontroller?id=${ls.id}">
                                    <img src="${ls.image}" class="card-img-top" alt="...">
                                </a>
                                <div class="card-body">
                                    <ul class="list-unstyled d-flex justify-content-between">
                                        <li>
                                            <i class="text-warning fa fa-star"></i>
                                            <i class="text-warning fa fa-star"></i>
                                            <i class="text-warning fa fa-star"></i>
                                            <i class="text-warning fa fa-star"></i>
                                            <i class="text-warning fa fa-star"></i>
                                        </li>
                                        <li class="text-muted text-right">${ls.price} VND</li>
                                    </ul>
                                    <a href="itemcontroller?id=${ls.id}&&number=1" class="h2 text-decoration-none text-dark">${ls.tittle}</a>
                                    <p class="card-text">
                                        ${ls.description}
                                    </p>
                                    <p class="text-muted">Reviews (74)</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <!-- End Featured Product -->


        <jsp:include page="footer.jsp"></jsp:include>

        <!-- Start Script -->
        <script src="assets/js/jquery-1.11.0.min.js"></script>
        <script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/templatemo.js"></script>
        <script src="assets/js/custom.js"></script>
        <!-- End Script -->
    </body>

</html>