<%--
    Document   : header-desktop
    Created on : May 30, 2020, 10:54:13 PM
    Author     : Jevison7x
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="com.valdbms.users.User"/>
                <!-- HEADER DESKTOP-->
                <header class="header-desktop">
                    <div class="section__content section__content--p30">
                        <div class="container-fluid">
                            <div class="header-wrap">
                                <form class="form-header" action="search" method="POST">
                                    <input class="au-input au-input--xl" type="text" name="search" placeholder="Search for datas &amp; reports..." />
                                    <button class="au-btn--submit" type="submit">
                                        <i class="zmdi zmdi-search"></i>
                                    </button>
                                </form>
                                <div class="header-button">
                                    <div class="noti-wrap">
                                    </div>
                                    <div class="account-wrap">
                                        <div class="account-item clearfix js-item-menu">
                                            <div class="image">
                                                <img src="${dashboardURL}/images/icon/avatar-01.jpg" alt="Profile Pic" />
                                            </div>
                                            <div class="content">
                                                <a class="js-acc-btn" href="#">${user.userName}</a>
                                            </div>
                                            <div class="account-dropdown js-dropdown">
                                                <div class="info clearfix">
                                                    <div class="image">
                                                        <a href="#">
                                                            <img src="${dashboardURL}/images/icon/avatar-01.jpg" alt="John Doe" />
                                                        </a>
                                                    </div>
                                                    <div class="content">
                                                        <h5 class="name">
                                                            <a href="#">${user.userName}</a>
                                                        </h5>
                                                            <span class="email">${user.email}</span>
                                                    </div>
                                                </div>
                                                <div class="account-dropdown__footer">
                                                    <a href="../logout">
                                                        <i class="zmdi zmdi-power"></i> Logout
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </header>
                <!-- HEADER DESKTOP-->

