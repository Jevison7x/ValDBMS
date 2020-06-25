<%--
    Document   : members
    Created on : May 31, 2020, 4:23:45 AM
    Author     : Jevison7x
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="member" class="com.valdbms.members.Member"/>
            <div id="members-page" class="main-content">
                <style type="text/css" scoped>
                    .j-form-group {
                        padding-bottom: 10px;
                    }

                    .j-form-group select {
                        max-width: 400px;
                    }

                    .modal-footer {
                        display: block;
                    }

                    #personalized-message {
                        margin-bottom: 10px;
                    }
                </style>
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <c:if test="${param.a ne null and param.a eq 1}">
                            <h3>A new member was added successfully!</h3>
                        </c:if>
                        <form id="member-form-filter">
                            <div class="row form-group j-form-group">
                                <div class="col-md-12">
                                    <div class="form-check">
                                        <div class="checkbox">
                                            <label for="filter-by-role" class="form-check-label ">
                                                <input type="checkbox" id="filter-by-role" name="filter-by-role" value="filter-by-role" class="form-check-input">Filter by Role
                                            </label>
                                        </div>
                                    </div>
                                    <select name="role-select" id="role-select" class="form-control" disabled>
                                        <option value="">Select Role</option>
                                    <c:forEach var="role" items="${roles}">
                                        <option value="${role}">${role}</option>
                                    </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row form-group j-form-group">
                                <div class="col-md-12">
                                    <div class="form-check">
                                        <div class="checkbox">
                                            <label for="filter-by-state" class="form-check-label ">
                                                <input type="checkbox" id="filter-by-state" name="filter-by-state" value="filter-by-state" class="form-check-input">Filter by State
                                            </label>
                                        </div>
                                    </div>
                                    <select name="state-select" id="state-select" class="form-control" disabled>
                                        <option value="">Select State</option>
                                        <option value="Abia">Abia</option>
                                        <option value="Adamawa">Adamawa</option>
                                        <option value="Akwa Ibom">Akwa Ibom</option>
                                        <option value="Anambra">Anambra</option>
                                        <option value="Bauchi">Bauchi</option>
                                        <option value="Bayelsa">Bayelsa</option>
                                        <option value="Benue">Benue</option>
                                        <option value="Borno">Borno</option>
                                        <option value="Cross River">Cross River</option>
                                        <option value="Delta">Delta</option>
                                        <option value="Ebonyi">Ebonyi</option>
                                        <option value="Edo">Edo</option>
                                        <option value="Ekiti">Ekiti</option>
                                        <option value="Enugu">Enugu</option>
                                        <option value="Gombe">Gombe</option>
                                        <option value="Imo">Imo</option>
                                        <option value="Jigawa">Jigawa</option>
                                        <option value="Kaduna">Kaduna</option>
                                        <option value="Katsina">Katsina</option>
                                        <option value="Kanu">Kanu</option>
                                        <option value="Kebbi">Kebbi</option>
                                        <option value="Kogi">Kogi</option>
                                        <option value="Kwara">Kwara</option>
                                        <option value="Lagos">Lagos</option>
                                        <option value="Nasarawa">Nasarawa</option>
                                        <option value="Niger">Niger</option>
                                        <option value="Ogun">Ogun</option>
                                        <option value="Ondo">Ondo</option>
                                        <option value="Osun">Osun</option>
                                        <option value="Oyo">Oyo</option>
                                        <option value="Plateau">Plateau</option>
                                        <option value="Rivers">Rivers</option>
                                        <option value="Sokoto">Sokoto</option>
                                        <option value="Taraba">Taraba</option>
                                        <option value="Yobe">Yobe</option>
                                        <option value="Zamfara">Zamfara</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row form-group j-form-group">
                                <div class="col-md-12">
                                    <div class="form-check">
                                        <div class="checkbox">
                                            <label for="filter-by-lga" class="form-check-label">
                                                <input type="checkbox" id="filter-by-lga" name="filter-by-lga" value="filter-by-lga" class="form-check-input">Filter by L.G.A.
                                            </label>
                                        </div>
                                    </div>
                                    <select name="lga-select" id="lga-select" class="form-control" disabled>
                                        <option value="">Select L.G.A.</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row form-group j-form-group">
                                <div class="col-md-12">
                                    <div class="form-check">
                                        <div class="checkbox">
                                            <label for="filter-by-ward" class="form-check-label ">
                                                <input type="checkbox" id="filter-by-ward" name="filter-by-ward" value="filter-by-ward" class="form-check-input">Filter by Ward
                                            </label>
                                        </div>
                                    </div>
                                    <select name="ward-select" id="ward-select" class="form-control" disabled>
                                        <option value="">Select Ward</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row form-group j-form-group">
                                <div class="col-md-12">
                                    <div class="form-check">
                                        <div class="checkbox">
                                            <label for="filter-by-bank" class="form-check-label ">
                                                <input type="checkbox" id="filter-by-bank" name="filter-by-bank" value="filter-by-bank" class="form-check-input">Filter by Bank
                                            </label>
                                        </div>
                                    </div>
                                    <select name="bank-select" id="bank-select" class="form-control" disabled>
                                        <option value="">Select Bank</option>
                                    <c:forEach var="bank" items="${banksList}">
                                        <option value="${bank}">${bank}</option>
                                    </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row form-group j-form-group">
                                <div class="col-md-12">
                                    <button type="button" id="filter-btn" class="btn btn-primary btn-sm sort-btns">
                                        <i class="fa fa-filter"></i> Filter
                                    </button>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <button type="button" id="select-all-btn" class="btn btn-info btn-sm sort-btns">
                                        <i class="fa fa-check"></i> Select All
                                    </button>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <button type="button" id="edit-record" class="btn btn-warning btn-sm sort-btns">
                                        <i class="fa fa-adjust"></i> Edit Record
                                    </button>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <button type="button" id="delete-record" class="btn btn-danger btn-sm sort-btns">
                                        <i class="fa fa-times"></i> Delete Record
                                    </button>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <button type="button" id="message-launch" class="btn btn-dark btn-sm sort-btns">
                                        <i class="fa fa-envelope"></i> Message Selected
                                    </button>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <button type="button" id="send-money" class="btn btn-success btn-sm sort-btns">
                                        <i class="fa fa-briefcase"></i> Transfer Money
                                    </button>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="../print-page" id="print-selected" class="btn btn-secondary btn-sm sort-btns">
                                        <i class="fa fa-print"></i> Print Filtered
                                    </a>
                                </div>
                            </div>
                        </form>
                    </div>

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
                                    </table>
                                </div>
                            </div>
                        </div>
                        <jsp:include page="../WEB-INF/fragments/footer.jsp"/>
                    </div>
                </div>

                <!-- Modal -->
                <div id="message-modal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title"><i class="fa fa-users"></i> Message Selected Members via SMS</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <button id="personalized-message" type="button" class="btn btn-outline-primary">
                                    <i class="fa fa-user-plus"></i> Personalized Message
                                </button>
                                <textarea name="textarea-input" id="textarea-input" rows="9" placeholder="Content..." class="form-control"></textarea>
                            </div>
                            <div class="modal-footer">
                                <button id="send-message" type="button" class="btn btn-success pull-left">
                                    <i class="fa fa-envelope"></i> Send
                                </button>
                                <button type="button" class="btn btn-warning pull-right" data-dismiss="modal">
                                    <i class="fa fa-times"></i> Close
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Modal -->
                <div id="send-money-modal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title"><i class="fa fa-briefcase"></i> Transfer Money to Selected Members</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <input type="number" name="amount" id="sending-amount" placeholder="Enter the amount here."/>
                            </div>
                            <div class="modal-footer">
                                <button id="send-money-submit" type="button" class="btn btn-success pull-left">
                                    <i class="fa fa-envelope"></i> Send
                                </button>
                                <button type="button" class="btn btn-warning pull-right" data-dismiss="modal">
                                    <i class="fa fa-times"></i> Close
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>