<%--
    Document   : index
    Created on : May 30, 2020, 12:14:43 PM
    Author     : Jevison7x
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Business Login Form Responsive Widget Template :: w3layouts</title>
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
                            <h1>Manage Your Business Account</h1>
                            <p>Donec dictum nisl nec mi lacinia, sed maximus tellus eleifend. Proin molestie cursus sapien ac eleifend.</p>
                            <img src="landing/images/image.jpg" alt="" />
                        </div>
                    </div>
                    <div class="w3_info">
                        <h2>Login to your Account</h2>
                <c:choose>
                    <c:when test="${param.login ne null and param.login eq 0}">
                        <p class="error-msg">Invalid username or password!</p>
                    </c:when>
                    <c:when test="${param.login ne null and param.login eq 1}">
                        <p class="success-msg">Your account has been created successfully!</p>
                    </c:when>
                    <c:when test="${param.login ne null and param.login eq 2}">
                        <p class="logout-msg">You have logged out successfully.</p>
                    </c:when>
                    <c:when test="${param.login ne null and param.login eq 3}">
                        <p class="error-msg">Your session has expired.</p>
                    </c:when>
                    <c:otherwise>
                        <p>Enter your details to login.</p>
                    </c:otherwise>
                </c:choose>
                        <form action="login" method="post">
                            <label>Email Address</label>
                            <div class="input-group">
                                <span class="fa fa-envelope" aria-hidden="true"></span>
                                <input type="text" name="username" placeholder="Enter Your Username or Email" required="">
                            </div>
                            <label>Password</label>
                            <div class="input-group">
                                <span class="fa fa-lock" aria-hidden="true"></span>
                                <input type="password" name="password" placeholder="Enter Your Password" required="">
                            </div>
                            <div class="login-check">
                                <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i> </i> Remember me</label>
                            </div>
                            <button class="btn btn-danger btn-block" type="submit">Login</button >
                        </form>
                        <p class="account1">Don't have an account? <a href="create-account">Register here</a></p>
                    </div>
                </div>
                <!-- //main content -->
            </div>
            <!-- footer -->
            <div class="footer">
                <p>&copy; 2019 Business login form. All Rights Reserved | Design by <a href="https://w3layouts.com/" target="blank">W3layouts</a></p>
            </div>
            <!-- footer -->
        </div>
    </body>
</html>
