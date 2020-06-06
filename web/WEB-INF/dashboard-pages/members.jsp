<%--
    Document   : members
    Created on : May 31, 2020, 4:23:45 AM
    Author     : Jevison7x
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="member" class="com.valdbms.members.Member"/>
            <div id="members-page" class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="table-responsive table--no-card m-b-30">
                                    <table id="members-table" class="table table-borderless table-striped table-earning display" style="width:100%">
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
                                        <c:forEach var="member" items="${members}">
                                            <tr>
                                                <td class="text-right">${member.sn}</td>
                                                <td>${member.title}</td>
                                                <td>${member.firstName}</td>
                                                <td>${member.lastName}</td>
                                                <td>${member.role}</td>
                                                <td>${member.mobileNo}</td>
                                                <td>${member.state}</td>
                                                <td>${member.lga}</td>
                                                <td class="text-right">${member.ward}</td>
                                                <td>${member.bank}</td>
                                                <td class="text-right">${member.accountNo}</td>
                                                <td>${member.accountName}</td>
                                                <td>${member.email}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <jsp:include page="../WEB-INF/fragments/footer.jsp"/>
                    </div>
                </div>
            </div>