<%--
    Document   : new-member
    Created on : May 31, 2020, 9:39:31 PM
    Author     : Jevison7x
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
                    <!-- MAIN CONTENT-->
                    <div id="new-member" class="main-content">
                        <style type="text/css" scoped="">
                            .hidden {
                                display: none;
                            }

                            .error-in-form {
                                color: #990000;
                            }
                        </style>
                        <div class="section__content section__content--p30">
                            <div class="container-fluid">
                                <form id="new-member-form">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="card">
                                                <div class="card-header">
                                                    <strong><i class="fas fa-user-plus"></i> Create New Member</strong> Form
                                                </div>
                                                <div class="card-body card-block">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="has-success form-group">
                                                                <label for="title" class="form-control-label">Title: </label>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">
                                                                        <i class="fa fa-tag"></i>
                                                                    </div>
                                                                    <select name="title" id="title" class="form-control" required>
                                                                        <option value="">Please select</option>
                                                                        <option value="Mr.">Mr.</option>
                                                                        <option value="Mrs.">Mrs.</option>
                                                                        <option value="Miss">Miss</option>
                                                                        <option value="Dr.">Dr.</option>
                                                                        <option value="Dr (Mrs).">Dr (Mrs).</option>
                                                                        <option value="Prof.">Prof.</option>
                                                                        <option value="Chief">Chief</option>
                                                                        <option value="Engr.">Engr.</option>
                                                                        <option value="Elder">Elder</option>
                                                                        <option value="Barr.">Barr.</option>
                                                                        <option value="Arct.">Arct.</option>
                                                                        <option value="Hon.">Hon.</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="has-success form-group">
                                                                <label for="first-name" class="form-control-label">First Name: </label>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">
                                                                        <i class="fa fa-user"></i>
                                                                    </div>
                                                                    <input type="text" name="firstName" id="first-name" class="form-control-success form-control" required>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="has-success form-group">
                                                                <label for="middle-name" class="form-control-label">Middle Name: </label>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">
                                                                        <i class="fa fa-user"></i>
                                                                    </div>
                                                                    <input type="text" name="middleName" id="middle-name" class="form-control-success form-control">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="has-success form-group">
                                                                <label for="last-name" class="form-control-label">Last Name: </label>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">
                                                                        <i class="fa fa-user"></i>
                                                                    </div>
                                                                    <input type="text" name="lastName" id="last-name" class="form-control-success form-control" required>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="has-success form-group">
                                                                <label for="phone-number" class="form-control-label">Phone Number: </label>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">
                                                                        <i class="fa fa-mobile-phone"></i>
                                                                    </div>
                                                                    <input type="text" name="mobileNo" id="phone-number" class="form-control-success form-control" required>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="has-success form-group">
                                                                <label for="email" class="form-control-label">Email: </label>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">
                                                                        <i class="fa fa-envelope"></i>
                                                                    </div>
                                                                    <input type="email" name="email" id="email" class="form-control-success form-control">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <hr/>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="has-success form-group">
                                                                <label for="state" class="form-control-label">State: </label>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">
                                                                        <i class="fa fa-location-arrow"></i>
                                                                    </div>
                                                                    <select name="state" id="state" class="form-control" required>
                                                                        <option value="">Please select</option>
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
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="has-success form-group">
                                                                <label for="lga" class="form-control-label">L.G.A.: </label>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon" id="lga-addon">
                                                                        <i class="fa fa-weight"></i>
                                                                    </div>
                                                                    <input type="text" name="lga" id="lga" class="form-control-success form-control hidden" placeholder="Add New L.G.A." required/>
                                                                    <select name="lga-select" id="lga-select" class="form-control">
                                                                        <option value="">Please select</option>
                                                                    </select>
                                                                    <div class="input-group-addon hidden">
                                                                        <button id="select-lga" type="button"><i class="fa fa-arrow-down"></i> Select L.G.A.</button>
                                                                    </div>
                                                                    <div class="input-group-addon">
                                                                        <button id="add-lga" type="button"><i class="fa fa-plus"></i> Add New L.G.A.</button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="has-success form-group">
                                                                <label for="ward" class="form-control-label">Ward: </label>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon" id="ward-addon">
                                                                        <i class="fa fa-navicon"></i>
                                                                    </div>
                                                                    <input type="text" name="ward" id="ward" class="form-control-success form-control hidden" placeholder="Add New Ward" required/>
                                                                    <select name="ward-select" id="ward-select" class="form-control">
                                                                        <option value="">Please select</option>
                                                                    </select>
                                                                    <div class="input-group-addon hidden">
                                                                        <button id="select-ward" type="button"><i class="fa fa-arrow-down"></i> Select Ward</button>
                                                                    </div>
                                                                    <div class="input-group-addon">
                                                                        <button id="add-ward" type="button"><i class="fa fa-plus"></i> Add New Ward</button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="has-success form-group">
                                                                <label for="role" class="form-control-label">Role: </label>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">
                                                                        <i class="fa fa-navicon"></i>
                                                                    </div>
                                                                    <input type="text" name="role" id="role" class="form-control-success form-control hidden" placeholder="Add New Role" required/>
                                                                    <select name="role-select" id="role-select" class="form-control">
                                                                        <option value="">Please select</option>
                                                                    </select>
                                                                    <div class="input-group-addon hidden">
                                                                        <button id="select-role" type="button"><i class="fa fa-arrow-down"></i> Select Role</button>
                                                                    </div>
                                                                    <div class="input-group-addon">
                                                                        <button id="add-role" type="button"><i class="fa fa-plus"></i> Add New Role</button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <hr/>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="has-success form-group">
                                                                <label for="bank" class="form-control-label">Bank Name: </label>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">
                                                                        <i class="fa fa-building"></i>
                                                                    </div>
                                                                    <input type="text" name="bank" id="bank" class="form-control-success form-control hidden" placeholder="Enter a Bank Name" required/>
                                                                    <select name="bank-select" id="bank-select" class="form-control">
                                                                        <option value="">Please select</option>
                                                                    </select>
                                                                    <div class="input-group-addon hidden">
                                                                        <button id="select-bank" type="button"><i class="fa fa-arrow-down"></i> Select Bank</button>
                                                                    </div>
                                                                    <div class="input-group-addon">
                                                                        <button id="add-bank" type="button"><i class="fa fa-plus"></i> Add New Bank</button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="has-success form-group">
                                                                <label for="account-no" class="form-control-label">Account No.: </label>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">
                                                                        <i class="fa fa-calculator"></i>
                                                                    </div>
                                                                    <input type="text" name="accountNo" id="account-no" class="form-control-success form-control" required/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="has-success form-group">
                                                                <label for="account-name" class="form-control-label">Account Name: </label>
                                                                <div class="input-group">
                                                                    <div class="input-group-addon">
                                                                        <i class="fa fa-vcard"></i>
                                                                    </div>
                                                                    <input type="text" name="accountName" id="account-name" class="form-control-success form-control" required/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="card-footer">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <button id="submit-button" type="submit" class="btn btn-lg btn-primary btn-block">
                                                                <i class="fa fa-save"></i> Submit
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <jsp:include page="../WEB-INF/fragments/footer.jsp"/>
                            </div>
                        </div>
                    </div>
