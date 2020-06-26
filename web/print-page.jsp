<%--
    Document   : print-page
    Created on : Jun 25, 2020, 12:59:19 AM
    Author     : Ugimson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="filteredMember" class="com.valdbms.members.Member"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS only -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>Val DBMS Print Page</title>
    </head>
    <body>
        <h1>List of Selected Members</h1>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="table-responsive">
                        <table class="table table-bordered" style="width:100%">
                            <thead>
                                <tr>
                                    <th>SN</th>
                                    <th>TITLE</th>
                                    <th>FIRST NAME</th>
                                    <th>SURNAME</th>
                                    <th>ROLE</th>
                                    <th>MOBILE NO</th>
                                    <th>STATE</th>
                                    <th>LGA</th>
                                    <th>WARD</th>
                                    <th>BANK</th>
                                    <th>ACCOUNT NO</th>
                                    <th>ACCOUNT NAME</th>
                                    <th>EMAIL</th> 
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="filteredMember" items="${filteredMembers}">
                                <tr>
                                    <td>${filteredMember.sn}</td>
                                    <td>${filteredMember.title}</td>
                                    <td>${filteredMember.firstName}</td>
                                    <td>${filteredMember.lastName}</td>
                                    <td>${filteredMember.role}</td>
                                    <td>${filteredMember.mobileNo}</td>
                                    <td>${filteredMember.state}</td>
                                    <td>${filteredMember.lga}</td>
                                    <td>${filteredMember.ward}</td>
                                    <td>${filteredMember.bank}</td>
                                    <td>${filteredMember.accountNo}</td>
                                    <td>${filteredMember.accountName}</td>
                                    <td>${filteredMember.email}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</html>
