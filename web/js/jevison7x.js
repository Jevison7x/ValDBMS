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
    $('#members-table').DataTable();
}

