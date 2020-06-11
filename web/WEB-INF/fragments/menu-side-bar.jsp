<%--
    Document   : menu-side-bar
    Created on : May 30, 2020, 10:51:01 PM
    Author     : Jevison7x
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

            <!-- MENU SIDEBAR-->
            <aside class="menu-sidebar d-none d-lg-block">
                <div class="logo">
                    <a href="#">
                        <img src="${dashboardURL}/images/icon/logo.png" alt="Cool Admin" />
                        <h3>Val DBMS</h3>
                    </a>
                </div>
                <div class="menu-sidebar__content js-scrollbar1">
                    <nav class="navbar-sidebar">
                        <ul class="list-unstyled navbar__list">
                            <li class="active has-sub">
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
                            <li>
                                <a href="pin-numbers" class="ajax-link">
                                    <i class="fas fa-lock"></i> Pin Numbers 
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </aside>
            <!-- END MENU SIDEBAR-->

