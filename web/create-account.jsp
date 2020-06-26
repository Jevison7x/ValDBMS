<%--
    Document   : create-account
    Created on : May 30, 2020, 2:22:38 PM
    Author     : Ugimson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Val DBMS</title>
        <!-- Meta-Tags -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
        <meta name="keywords" content="Business Login Form a Responsive Web Template, Bootstrap Web Templates, Flat Web Templates, Android Compatible Web Template, Smartphone Compatible Web Template, Free Webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
        <script>
            addEventListener("load", function(){
                setTimeout(hideURLbar, 0);
            }, false);

            function hideURLbar(){
                window.scrollTo(0, 1);
            }
        </script>
        <!-- //Meta-Tags -->

        <!-- css files -->
        <link href="landing/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
        <link href="landing/css/style.css" rel="stylesheet" type="text/css" media="all"/>
        <link href="landing/css/jevison7x.css" rel="stylesheet" type="text/css" media="all"/>
        <!-- //css files -->

        <!-- google fonts -->
        <link href="//fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
        <!-- //google fonts -->

    </head>
    <body>
        <div class="signupform">
            <div class="container">
                <!-- main content -->
                <div class="agile_info">
                    <div class="w3l_form">
                        <div class="left_grid_info">
                            <h1>Val DBMS</h1>
                            <p>Database Management System for L.G.A. Records.</p>
                            <img src="landing/images/image.jpg" alt="" />
                        </div>
                    </div>
                    <div class="w3_info">
                        <h2>Create a new account</h2>
                <c:choose>
                    <c:when test="${errorMessage ne null}">
                        <p class="error-msg">${errorMessage}</p>
                    </c:when>
                    <c:otherwise>
                        <p>Enter your details to create an account.</p>
                    </c:otherwise>
                </c:choose>
                        <form action="create-account-servlet" method="post">
                            <label>Username</label>
                            <div class="input-group">
                                <span class="fa fa-mobile" aria-hidden="true"></span>
                                <input type="text" placeholder="Enter Your Username" name="userName" required="">
                            </div>
                            <label>Email Address</label>
                            <div class="input-group">
                                <span class="fa fa-envelope" aria-hidden="true"></span>
                                <input type="email" placeholder="Enter Your Email" name="email" required="">
                            </div>
                            <label>Password</label>
                            <div class="input-group">
                                <span class="fa fa-lock" aria-hidden="true"></span>
                                <input type="password" placeholder="Enter Password" name="password" required="">
                            </div>
                            <label>Confirm Password</label>
                            <div class="input-group">
                                <span class="fa fa-lock" aria-hidden="true"></span>
                                <input type="password" placeholder="Please Confirm Password" name="coNpassword" required="">
                            </div>
                            <label>Admin Pin</label>
                            <div class="input-group">
                                <span class="fa fa-lock" aria-hidden="true"></span>
                                <input type="password" placeholder="Enter admin pin" name="pin" required="">
                            </div>
                            <button class="btn btn-danger btn-block" type="submit">Register</button>
                            <p class="account1">Already have an account? <a href="login-page">login here</a></p>
                        </form>
                    </div>
                </div>
                <!-- //main content -->
            </div>
            <!-- footer -->
            <div class="footer">
                <p>&copy; 2020 ValDBMS</p>
            </div>
            <!-- footer -->
        </div>
    </body>
</html>
