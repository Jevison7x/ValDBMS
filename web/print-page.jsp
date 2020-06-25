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
        <title>Val DBMS Print Page</title>
    </head>
    <body>
        <table>
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
    </body>
</html>
