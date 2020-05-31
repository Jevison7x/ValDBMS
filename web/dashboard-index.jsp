<%--
    Document   : dashboard-index
    Created on : May 30, 2020, 8:48:11 PM
    Author     : Jevison7x
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:catch var="exception">
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <!-- Title Page-->
        <title>Dashboard</title>
        <jsp:include page="../WEB-INF/fragments/head-tags.jsp"/>
    </head>
    <body class="animsition">
        <div id="progress-div">
            <div id="progress-bar"></div>
            <div id="progress-percent"></div>
        </div>
        <div class="page-wrapper">
            <jsp:include page="../WEB-INF/fragments/header-mobile.jsp"/>
            <jsp:include page="../WEB-INF/fragments/menu-side-bar.jsp"/>
            <!-- PAGE CONTAINER-->
            <div class="page-container">
                <jsp:include page="../WEB-INF/fragments/header-desktop.jsp"/>
                <div id="page-content">
                    
                </div>
                <!-- END PAGE CONTAINER-->
            </div>
        </div>
        <jsp:include page="../WEB-INF/fragments/scripts.jsp"/>
    </body>
</html>
</c:catch>
<c:if test="${exception ne null}">
    <c:out value="${exception}"/>
</c:if>
<!-- end document-->

