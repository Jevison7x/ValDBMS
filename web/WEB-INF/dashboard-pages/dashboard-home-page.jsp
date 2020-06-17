<%--
    Document   : dashboard-home-page
    Created on : May 30, 2020, 10:55:34 PM
    Author     : Jevison7x
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
                <!-- MAIN CONTENT-->
                <div id="dashboard-home" class="main-content">
                    <div class="section__content section__content--p30">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="overview-wrap">
                                        <h2 class="title-1">overview</h2>
                                        <button class="au-btn au-btn-icon au-btn--blue">
                                            <i class="zmdi zmdi-plus"></i> add new member
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="row m-t-25">
                                <div class="col-sm-6 col-lg-3">
                                    <div class="overview-item overview-item--c1">
                                        <div class="overview__inner">
                                            <div class="overview-box clearfix">
                                                <div class="icon">
                                                    <i class="zmdi zmdi-accounts-list"></i>
                                                </div>
                                                <div class="text">
                                                    <h2>${totalMembers}</h2>
                                                    <span>Registered Members</span>
                                                </div>
                                            </div>
                                            <div class="overview-chart">
                                                <canvas id="widgetChart1"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-lg-3">
                                    <div class="overview-item overview-item--c2">
                                        <div class="overview__inner">
                                            <div class="overview-box clearfix">
                                                <div class="icon">
                                                    <i class="zmdi zmdi-markunread-mailbox"></i>
                                                </div>
                                                <div class="text">
                                                    <h2>${totalLgas}</h2>
                                                    <span>Local Government Areas</span>
                                                </div>
                                            </div>
                                            <div class="overview-chart">
                                                <canvas id="widgetChart2"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-lg-3">
                                    <div class="overview-item overview-item--c3">
                                        <div class="overview__inner">
                                            <div class="overview-box clearfix">
                                                <div class="icon">
                                                    <i class="zmdi zmdi-calendar-note"></i>
                                                </div>
                                                <div class="text">
                                                    <h2>${totalWards}</h2>
                                                    <span>Wards</span>
                                                </div>
                                            </div>
                                            <div class="overview-chart">
                                                <canvas id="widgetChart3"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-lg-3">
                                    <div class="overview-item overview-item--c4">
                                        <div class="overview__inner">
                                            <div class="overview-box clearfix">
                                                <div class="icon">
                                                    <i class="zmdi zmdi-account-o"></i>
                                                </div>
                                                <div class="text">
                                                    <h2>${totalUsers}</h2>
                                                    <span>Users</span>
                                                </div>
                                            </div>
                                            <div class="overview-chart">
                                                <canvas id="widgetChart4"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-9">
                                    <h2 class="title-1 m-b-25">List of Ward Chairpersons</h2>
                                    <div class="table-responsive table--no-card m-b-40">
                                        <table class="table table-borderless table-striped table-earning">
                                            <thead>
                                                <tr>
                                                    <th>S/N</th>
                                                    <th>Role</th>
                                                    <th>First Name</th>
                                                    <th>Surname</th>
                                                    <th class="text-right">Ward</th>
                                                    <th>LGA</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="member" items="${chairmanList}">
                                                <jsp:useBean id="member" class="com.valdbms.members.Member"/>
                                                <tr>
                                                    <td>${member.sn}</td>
                                                    <td>${member.role}</td>
                                                    <td>${member.firstName}</td>
                                                    <td>${member.lastName}</td>
                                                    <td class="text-right">${member.ward}</td>
                                                    <td class="text-right">${member.lga}</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="col-lg-3">
                                    <h2 class="title-1 m-b-25">Local Government Areas</h2>
                                    <div class="au-card au-card--bg-blue au-card-top-countries m-b-40">
                                        <div class="au-card-inner">
                                            <div class="table-responsive">
                                                <table class="table table-top-countries">
                                                    <tbody>
                                                        <tr>
                                                            <td>Onitsha North</td>
                                                            <td class="text-right">1,000</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Awka</td>
                                                            <td class="text-right">540</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Onitsha South</td>
                                                            <td class="text-right">980</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Urumba North</td>
                                                            <td class="text-right">45</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Urumba South</td>
                                                            <td class="text-right">55</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Idemili</td>
                                                            <td class="text-right">78</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Aguata</td>
                                                            <td class="text-right">25</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Ayamelum</td>
                                                            <td class="text-right">45</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Njikoka</td>
                                                            <td class="text-right">45</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Oyi</td>
                                                            <td class="text-right">45</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <jsp:include page="../WEB-INF/fragments/footer.jsp"/>
                        </div>
                    </div>
                </div>
                <!-- END MAIN CONTENT-->
