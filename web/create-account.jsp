<%--
    Document   : create-account
    Created on : May 30, 2020, 2:22:38 PM
    Author     : Ugimson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <h2>Create a new account</h2>
                        <p>Enter your details to create an account.</p>
                        <form action="#" method="post">
                            <label>Username</label>
                            <div class="input-group">
                                <span class="fa fa-mobile" aria-hidden="true"></span>
                                <input type="email" placeholder="Enter Your Username" name="userName" required="">
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
                                <input type="password" placeholder="Please Confirm Password" name="conpassword" required="">
                            </div>
                            <label>Admin Pin</label>
                            <div class="input-group">
                                <span class="fa fa-lock" aria-hidden="true"></span>
                                <input type="password" placeholder="Please Confirm Password" name="pin" required="">
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
                <p>&copy; 2019 Business login form. All Rights Reserved | Design by <a href="https://w3layouts.com/" target="blank">W3layouts</a></p>
            </div>
            <!-- footer -->
        </div>
    </body>
</html>
