/*
 * Copyright (c) 2018, Xyneex Technologies. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * You are not meant to edit or modify this source code unless you are
 * authorized to do so.
 *
 * Please contact Xyneex Technologies, #1 Orok Orok Street, Calabar, Nigeria.
 * or visit www.xyneex.com if you need additional information or have any
 * questions.
 */

var paceObject = {
    defaultTitle: 'Val DBMS',
    pageContent: 'page-content',
    linkSelector: 'a.ajax-link',
    sidebarNav: 'sidebarnav',
    pages: [
        {
            pageId: 'dashboard-home',
            pageInit: function(){

            }
        },
        {
            pageId: 'members-page',
            pageInit: function(){
                initMembersPage();
            }
        },
        {
            pageId: 'new-member',
            pageInit: function(){
                initFormActions();
                initNewMemberSubmitFormEvent();
            }
        },
        {
            pageId: 'pin-numbers',
            pageInit: function(){
                initPinNumbersPage();
            }
        }
    ],
    e404: '404',
    e404Title: 'Error 404 | Not Found',
    beforeSend: function(){
        loadProgress();
    },
    complete: function(){
        hideProgress();
    }
};

$(document).ready(function(){
    $(document).initPacePage(paceObject);
});

function loadProgress(){
    $('#progress-bar').removeClass('progress-end');
    $('#progress-div').fadeIn(50, function(){
        $('#progress-bar').addClass('progress-start');
    });
}

function hideProgress(){
    $('#progress-div').fadeOut(1000, function(){
        $('#progress-bar').removeClass('progress-start');
        $('#progress-bar').addClass('progress-end');
    });
}

function initMembersPage(){
    var $membersTable = $('#members-table').DataTable({
        "serverSide": true,
        ajax: {
            url: 'members?json=true',
            dataType: 'json',
            type: 'POST'
        },
        "paging": true,
        "Filter": true,
        "processing": true,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "scrollX": true,
        "columns": [
            {"data": "sn"},
            {"data": "title"},
            {"data": "firstName"},
            {"data": "lastName"},
            {"data": "role"},
            {"data": "mobileNo"},
            {"data": "state"},
            {"data": "lga"},
            {"data": "ward"},
            {"data": "bank"},
            {"data": "accountNo"},
            {"data": "accountName"},
            {"data": "email"}
        ],
        "columnDefs": [
            {
                className: "phone-number",
                "targets": [5]
            }
        ]
    });

    //Filter by role enable/disable
    $('#filter-by-role').change(function(){
        if(this.checked){
            $('#role-select').removeAttr('disabled');
        }else
            $('#role-select').attr('disabled', true);
    });
    //Filter by state enable/disable
    $('#filter-by-state').change(function(){
        if(this.checked){
            $('#state-select').removeAttr('disabled');
        }else
            $('#state-select').attr('disabled', true);
    });
    //Filter by LGA enable/disable
    $('#filter-by-lga').change(function(){
        if(this.checked){
            $('#lga-select').removeAttr('disabled');
        }else
            $('#lga-select').attr('disabled', true);
    });
    //Filter by Ward enable/disable
    $('#filter-by-ward').change(function(){
        if(this.checked){
            $('#ward-select').removeAttr('disabled');
        }else
            $('#ward-select').attr('disabled', true);
    });
    //Filter by Bank enable/disable
    $('#filter-by-bank').change(function(){
        if(this.checked){
            $('#bank-select').removeAttr('disabled');
        }else
            $('#bank-select').attr('disabled', true);
    });

    $('#state-select').change(function(){
        var state = $(this).val();
        var $lgaSelect = $('#lga-select');
        $.ajax({
            url: 'new-member',
            data: {getLGAs: true, state: state},
            dataType: 'JSON',
            beforeSend: function(xhr){
                $lgaSelect.html('');
                $lgaSelect.append('<option value="">Loading L.G.A.s ...</option>');
            },
            complete: function(jqXHR, textStatus){
            },
            success: function(data, textStatus, jqXHR){
                $lgaSelect.html('');
                $lgaSelect.append('<option value="">Please select</option>');
                for(var i = 0; i < data.length; i++){
                    $lgaSelect.append('<option value="' + data[i] + '">' + data[i] + '</option>');
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Something went wrong!',
                    footer: 'Please reload this page and try again.'
                });
            }
        });
    });

    $('#lga-select').change(function(){
        var lga = $(this).val();
        var state = $('#state-select').val();
        var $wardSelect = $('#ward-select');
        $.ajax({
            url: 'new-member',
            data: {getWards: true, state: state, lga: lga},
            dataType: 'JSON',
            beforeSend: function(xhr){
                $wardSelect.html('');
                $wardSelect.append('<option value="">Loading Wards ...</option>');
            },
            complete: function(jqXHR, textStatus){
            },
            success: function(data, textStatus, jqXHR){
                $wardSelect.html('');
                $wardSelect.append('<option value="">Please select</option>');
                for(var i = 0; i < data.length; i++){
                    $wardSelect.append('<option value="' + data[i].ward + '">' + data[i].ward + '</option>');
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Something went wrong!',
                    footer: 'Please reload this page and try again.'
                });
            }
        });
    });

    $('#filter-btn').click(function(){
        var role = '', state = '', lga = '', ward = '', bank = '';
        if($('#filter-by-role').is(':checked')){
            role = $('#role-select').val();
        }
        if($('#filter-by-state').is(':checked')){
            state = $('#state-select').val();
        }
        if($('#filter-by-lga').is(':checked')){
            lga = $('#lga-select').val();
        }
        if($('#filter-by-ward').is(':checked')){
            ward = $('#ward-select').val();
        }
        if($('#filter-by-bank').is(':checked')){
            bank = $('#bank-select').val();
        }
        $membersTable.column(4).search(role).draw();
        $membersTable.column(6).search(state).draw();
        $membersTable.column(7).search(lga).draw();
        $membersTable.column(8).search(ward).draw();
        $membersTable.column(9).search(bank).draw();
    });

    $('#members-table tbody').on('click', 'tr', function(){
        var phoneNumber = $(this).find('.phone-number').html();
        $(this).toggleClass('selected');
        var status = $(this).hasClass('selected') ? 'select' : 'unselect';
        $.ajax({
            url: 'select-member',
            data: {phoneNumber: phoneNumber, status: status, selectType: 'single'},
            dataType: 'text/html',
            method: 'GET'
        });
    });

    $('#select-all-btn').click(function(){
        $('#members-table tbody tr').toggleClass('selected');
    });

    $('#message-launch').click(function(){
        $('#message-modal').modal('show');
    });

    $('#personalized-message').click(function(){
        $('#textarea-input').val('Dear ${firstName} ${lastName} ');
    });

    $('#send-message').click(function(){
        var message = $('#textarea-input').val();
        var phoneNumbers = '';
        var count = $membersTable.rows({selected: true}).count();
        console.log('selected rows: ', count);
        /*
         var selectedRows = $('#members-table').DataTable().row('.selected');
         for(var i = 0; i < selectedRows.length; i++){
         var row = selectedRows[i];
         console.log('Row: ', row);
         }
         */
    });
}

function initFormActions(){
    $('#add-lga').click(function(){
        $('#lga-select').addClass('hidden');
        $('#lga').removeClass('hidden');
        $('#add-lga').parent().addClass('hidden');
        $('#select-lga').parent().removeClass('hidden');
    });

    $('#select-lga').click(function(){
        $('#lga-select').removeClass('hidden');
        $('#lga').addClass('hidden');
        $('#add-lga').parent().removeClass('hidden');
        $('#select-lga').parent().addClass('hidden');
    });

    $('#add-ward').click(function(){
        $('#ward-select').addClass('hidden');
        $('#ward').removeClass('hidden');
        $('#add-ward').parent().addClass('hidden');
        $('#select-ward').parent().removeClass('hidden');
    });

    $('#select-ward').click(function(){
        $('#ward-select').removeClass('hidden');
        $('#ward').addClass('hidden');
        $('#add-ward').parent().removeClass('hidden');
        $('#select-ward').parent().addClass('hidden');
    });

    $('#add-role').click(function(){
        $('#role-select').addClass('hidden');
        $('#role').removeClass('hidden');
        $('#add-role').parent().addClass('hidden');
        $('#select-role').parent().removeClass('hidden');
    });

    $('#select-role').click(function(){
        $('#role-select').removeClass('hidden');
        $('#role').addClass('hidden');
        $('#add-role').parent().removeClass('hidden');
        $('#select-role').parent().addClass('hidden');
    });

    $('#add-bank').click(function(){
        $('#bank-select').addClass('hidden');
        $('#bank').removeClass('hidden');
        $('#add-bank').parent().addClass('hidden');
        $('#select-bank').parent().removeClass('hidden');
    });

    $('#select-bank').click(function(){
        $('#bank-select').removeClass('hidden');
        $('#bank').addClass('hidden');
        $('#add-bank').parent().removeClass('hidden');
        $('#select-bank').parent().addClass('hidden');
    });

    $('#state').change(function(){
        var state = $(this).val();
        var $lgaSelect = $('#lga-select');
        $.ajax({
            url: 'new-member',
            data: {getLGAs: true, state: state},
            dataType: 'JSON',
            beforeSend: function(xhr){
                $lgaSelect.html('');
                $lgaSelect.append('<option value="">Loading L.G.A.s ...</option>');
                $lgaSelect.attr('disabled', 'true');
                $('#lga-addon i').removeClass('fa-weight').addClass('fa-refresh').addClass('fa-spin');
            },
            complete: function(jqXHR, textStatus){
                $lgaSelect.removeAttr('disabled');
                $('#lga-addon i').removeClass('fa-refresh').removeClass('fa-spin').addClass('fa-weight');
            },
            success: function(data, textStatus, jqXHR){
                $lgaSelect.html('');
                $lgaSelect.append('<option value="">Please select</option>');
                for(var i = 0; i < data.length; i++){
                    $lgaSelect.append('<option value="' + data[i] + '">' + data[i] + '</option>');
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Something went wrong!',
                    footer: 'Please reload this page and try again.'
                });
            }
        });
    });

    $('#lga-select').change(function(){
        var lga = $(this).val();
        $('#lga').val(lga);
        var state = $('#state').val();
        var $wardSelect = $('#ward-select');
        $.ajax({
            url: 'new-member',
            data: {getWards: true, state: state, lga: lga},
            dataType: 'JSON',
            beforeSend: function(xhr){
                $wardSelect.html('');
                $wardSelect.append('<option value="">Loading Wards ...</option>');
                $wardSelect.attr('disabled', 'true');
                $('#ward-addon i').removeClass('fa-navicon').addClass('fa-refresh').addClass('fa-spin');
            },
            complete: function(jqXHR, textStatus){
                $wardSelect.removeAttr('disabled');
                $('#ward-addon i').removeClass('fa-refresh').removeClass('fa-spin').addClass('fa-navicon');
            },
            success: function(data, textStatus, jqXHR){
                $wardSelect.html('');
                $wardSelect.append('<option value="">Please select</option>');
                for(var i = 0; i < data.length; i++){
                    $wardSelect.append('<option value="' + data[i].ward + '">' + data[i].ward + '</option>');
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Something went wrong!',
                    footer: 'Please reload this page and try again.'
                });
            }
        });
    });

    $('#ward-select').change(function(){
        var ward = $(this).val();
        $('#ward').val(ward);
    });

    $('#role-select').change(function(){
        var role = $(this).val();
        $('#role').val(role);
    });

    $('#bank-select').change(function(){
        var bank = $(this).val();
        $('#bank').val(bank);
    });
}

function initNewMemberSubmitFormEvent()
{
    $('#new-member-form').submit(function(event){
        event.preventDefault();
        var title = $('#title').val();
        var firstName = $('#first-name').val().trim();
        var middleName = $('#middle-name').val().trim();
        var lastName = $('#last-name').val().trim();
        var gender = $('#gender').val();
        var dateOfBirth = $('#date-of-birth').val().trim();
        var phoneNumber = $('#phone-number').val().trim();
        var email = $('#email').val().trim();
        var state = $('#state').val().trim();
        var lga = $('#lga').val().trim();
        var ward = $('#ward').val().trim();
        var role = $('#role').val().trim();
        var bank = $('#bank').val().trim();
        var accountNo = $('#account-no').val().trim();
        var accountName = $('#account-name').val().trim();

        $.ajax({
            url: 'submit-new-member',
            dataType: 'text',
            data: {
                title: title, firstName: firstName, middleName: middleName,
                lastName: lastName, gender: gender, dateOfBirth: dateOfBirth,
                phoneNumber: phoneNumber, email: email, state: state,
                lga: lga, ward: ward, role: role,
                bank: bank, accountNo: accountNo, accountName: accountName
            },
            method: 'POST',
            beforeSend: function(xhr){
                loadProgress();
            },
            complete: function(jqXHR, textStatus){
                hideProgress();
            },
            success: function(data, textStatus, jqXHR){
                if(data === 'success')
                    ajaxPageLoad('members', 'Val DBMS - Members', paceObject);
                else
                    $('.card-header').html('<strong><i class="fas fa-warning"></i> ' + data + '</strong>').addClass('error-in-form');
            },
            error: function(jqXHR, textStatus, errorThrown){
                $('.card-header').html('<strong><i class="fas fa-warning"></i> An unknown error occured, please refresh this page.</strong>').addClass('error-in-form');
            }
        });
    });
}

function initPinNumbersPage()
{
    $('#generate-pin-btn').click(function(){
        var $btn = $(this);
        $.ajax({
            url: 'generate-pin',
            dataType: 'text',
            beforeSend: function(xhr){
                $btn.attr('disabled', true);
                $btn.find('i').removeClass('fa-gears').addClass('fa-refresh').addClass('fa-spin');
            },
            complete: function(jqXHR, textStatus){
                $btn.removeAttr('disabled');
                $btn.find('i').removeClass('fa-spin').removeClass('fa-refresh').addClass('fa-gears');
            },
            success: function(data, textStatus, jqXHR){
                if(data === 'success')
                    ajaxPageLoad('pin-numbers?success=true', '', paceObject);
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert('There was an error, please reload this page.');
            }
        });
    });
}

