<%--
    Document   : header-mobile
    Created on : May 30, 2020, 10:50:27 PM
    Author     : Jevison7x
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
            <!-- HEADER MOBILE-->
            <header class="header-mobile d-block d-lg-none">
                <div class="header-mobile__bar">
                    <div class="container-fluid">
                        <div class="header-mobile-inner">
                            <a class="logo" href="home">
                                <img src="${dashboardURL}/images/icon/logo.png" alt="Val DBMS" />
                                <h3>Val DBMS</h3>
                            </a>
                            <button class="hamburger hamburger--slider" type="button">
                                <span class="hamburger-box">
                                    <span class="hamburger-inner"></span>
                                </span>
                            </button>
                        </div>
                    </div>
                </div>
                <nav class="navbar-mobile">
                    <div class="container-fluid">
                        <ul class="navbar-mobile__list list-unstyled">
                            <li>
                                <a href="home" class="ajax-link">
                                    <i class="fas fa-tachometer-alt"></i> Dashboard
                                </a>
                            </li>
                            <li>
                                <a href="members" class="ajax-link">
                                    <i class="fas fa-users"></i> Members
                                </a>
                            </li>
                            <li>
                                <a href="new-member" class="ajax-link">
                                    <i class="fas fa-user-plus"></i> Add New Member
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- END HEADER MOBILE-->

