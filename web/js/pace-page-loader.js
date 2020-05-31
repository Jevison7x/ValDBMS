/*
 * Copyright (c) 2020, Xyneex Technologies. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * You are not meant to edit or modify this source code unless you are
 * authorized to do so.
 *
 * Please contact Xyneex Technologies, #1 Orok Orok Street, Calabar, Nigeria.
 * or visit www.xyneex.com if you need additional information or have any
 * questions.
 */
$.fn.extend({
    initPacePage: function(pace){
        var pathname = window.location.pathname;
        var url = pathname.split('/')[2];
        ajaxPageLoad(url, pace.defaultTitle, pace);
        $('body').on('click', 'a.ajax-link', function(e){
            e.preventDefault();
            var pageTitle = $(this).attr('title');
            var urlPath = $(this).attr('href');
            if(typeof pageTitle === 'undefined' || pageTitle === null)
                pageTitle = pace.defaultTitle;
            ajaxPageLoad(urlPath, pageTitle, pace);
            $('#' + pace.sidebarNav + ' li').removeClass('active');
            $(this).parent('li').addClass('active');
        });

        window.onpopstate = function(e){
            if(e.state){
                document.getElementById(pace.pageContent).innerHTML = e.state.html;
                document.title = e.state.pageTitle;
            }
        };

        // Select the node that will be observed for mutations
        const pageContentDiv = document.getElementById(pace.pageContent);
        // Options for the observer (which mutations to observe)
        const config = {attributes: true, childList: true, subtree: false};

        // Callback function to execute when mutations are observed
        const callback = function(mutationsList, observer){
            // Use traditional 'for loops' for IE 11
            for(let mutation of mutationsList){
                if(mutation.type === 'childList'){
                    var pageId = $("#" + pace.pageContent + " div:first-child").attr("id");
                    console.log('Page ID: ' + pageId);
                    for(let pacePage of pace.pages){
                        if(pacePage.pageId === pageId){
                            pacePage.pageInit();
                        }
                    }
                }else if(mutation.type === 'attributes'){
                    console.log('The ' + mutation.attributeName + ' attribute was modified.');
                }
            }
        };

        // Create an observer instance linked to the callback function
        const observer = new MutationObserver(callback);

        // Start observing the target node for configured mutations
        observer.observe(pageContentDiv, config);
    }
});

function ajaxPageLoad(urlPath, pageTitle, pace){
    $.ajax({
        url: urlPath,
        beforeSend: function(xhr){
            pace.beforeSend();
        },
        success: function(response){
            processAjaxData(response, urlPath, pageTitle, pace);
        },
        error: function(jqXHR){
            if(jqXHR.status === 401)
                location.href = pace.login;
            if(jqXHR.status === 404)
                loadErrorPage(pace.e404, urlPath, pace);
            if(jqXHR.status === 403)
                loadErrorPage2(pace.e403, urlPath, pace);
        },
        complete: function(jqXHR, textStatus){
            pace.complete();
        }
    });
}

function loadErrorPage(errorPageURL, pageURL, pace){
    $.ajax({
        url: errorPageURL,
        success: function(response){
            processAjaxData(response, pageURL, pace.e404Title, pace);
        }
    });
}

function loadErrorPage2(errorPageURL, pageURL, pace){
    $.ajax({
        url: errorPageURL,
        success: function(response){
            processAjaxData(response, pageURL, pace.e403Title, pace);
        }
    });
}

function processAjaxData(response, urlPath, pageTitle, pace){
    document.getElementById(pace.pageContent).innerHTML = response;
    document.title = pageTitle;
    window.history.pushState({"html": response, "pageTitle": pageTitle}, "", urlPath);
}