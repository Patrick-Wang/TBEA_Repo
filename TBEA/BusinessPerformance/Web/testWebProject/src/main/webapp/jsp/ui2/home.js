///<reference path="tabs/tab.ts"/>
///<reference path="tree/tree.ts"/>
///<reference path="pages/util.ts"/>
///<reference path="navigator.ts"/>
var home;
(function (home) {
    $("#logoutBtn").on("click", function () {
        var loc = "" + window.location.href;
        var index = loc.lastIndexOf("/");
        loc = loc.substring(0, index);
        index = loc.lastIndexOf("/");
        var logoutAjax = new Util.Ajax(loc.substring(0, index) + "/v2/exitSystem.do");
        logoutAjax.get().then(function onSuccess() {
        }, function onFailed() {
            alert("网络错误");
        });
    });
    window['invalidate'] = function (redirect) {
        if (redirect) {
            var index = redirect.lastIndexOf("/");
            window.location.href = redirect.substring(0, index) + "/v2" + redirect.substring(index);
        }
        else {
            $("#logoutBtn").click();
        }
    };
    function getFrameWindow(name) {
        for (var i = 0; i < window.frames.length; ++i) {
            if (window.frames[i].name == name) {
                return window.frames[i];
            }
        }
    }
    function triggerFrameResize(name) {
        var frame = getFrameWindow(name);
        if (frame && frame.triggerResize) {
            frame.triggerResize();
        }
    }
    function updateFrameSize() {
        var heightUp = $(".content-up>div").css("height");
        $(".content-up").css("height", heightUp);
        var bodyHeight = parseInt($("body").css("height").replace("px", ""));
        var contentDownHeight = bodyHeight - parseInt(heightUp.replace("px", ""));
        $(".content-down").css("height", contentDownHeight + "px");
        var tabHeight = parseInt($("#tabNew .tab").css("height").replace("px", ""));
        $("#tabContent").css("height", (contentDownHeight - tabHeight) + "px");
        $("#tabContent-up").css("height", (contentDownHeight - tabHeight) + "px");
    }
    $(document).ready(function () {
        updateFrameSize();
        $(document.body).css("visibility", "visible").show();
        $(".container-fluid").css("visibility", "visible").show();
        topTab.triggerClickHome();
        $(window).resize(function () {
            updateFrameSize();
        });
    });
    $(".nav-left").mCustomScrollbar({
        theme: "minimal-dark",
        scrollInertia: 0
    });
    var topTab = new tab.Tab("tabNew");
    topTab.addMore({
        id: "refresh",
        name: "刷新"
    }).addMore({
        id: "closeCurrent",
        name: "关闭当前页"
    }).addMore({
        id: "closeAll",
        name: "关闭全部页"
    }).addMore({
        id: "closeOther",
        name: "关闭其它页"
    }).setHomeClickListener(function () {
        var homeFrame = $("#tabContent #home");
        if (homeFrame.length == 0) {
            //let src = '/BusinessManagement/ydzb/v2/hzb_zbhz.do?breads=[{"id":"1498180329800","value":"abc"},{"id":"1498180329801","value":"def"},{"id":"1498180329802","value":"hij"}]';
            var src = "/BusinessManagement/jsp/ui2/background.html";
            $("#tabContent").append('<iframe frameborder="0" name="home" id="home" src=' + src + ' style="width:100%;height:100%;"></iframe>');
            homeFrame = $("#tabContent #home");
            $("#tabContent #home").active(function () {
                $("body").click();
            });
            //homeFrame.css("height", "0").css("width", "0");
            $("iframe").addClass("gone");
        }
        //homeFrame.css("height", "100%").css("width", "100%");
        homeFrame.removeClass("gone");
        triggerFrameResize(homeFrame[0].name);
        topTab.disableMore("closeCurrent");
    }).setMoreClickListener(function (data) {
        var activeTab = topTab.getActiveTab();
        if (data.id == "refresh") {
            if (activeTab != undefined) {
                var tabFrame = $("#tabContent #" + activeTab.id);
                $("#tabContent #" + activeTab.id)[0].src = tabFrame[0].src;
            }
            else {
                var homeFrame = $("#tabContent #home");
                $("#tabContent #home")[0].src = homeFrame[0].src;
            }
        }
        else if (data.id == "closeCurrent") {
            topTab.triggerClickClose(activeTab.id);
        }
        else if (data.id == "closeAll") {
            var tabs = topTab.getTabs();
            var tabCopy = [];
            for (var i = 0; i < tabs.length; ++i) {
                tabCopy.push(tabs[i]);
            }
            for (var i = 0; i < tabCopy.length; ++i) {
                topTab.triggerClickClose(tabCopy[i].id);
            }
        }
        else if (data.id == "closeOther") {
            var tabs = topTab.getTabs();
            var tabCopy = [];
            for (var i = 0; i < tabs.length; ++i) {
                if (topTab.getActiveTab() != tabs[i]) {
                    tabCopy.push(tabs[i]);
                }
            }
            for (var i = 0; i < tabCopy.length; ++i) {
                topTab.triggerClickClose(tabCopy[i].id);
            }
        }
    }).setTabClickListener(function (data) {
        topTab.enableMore("closeCurrent");
        // $("iframe").css("height", "0px").css("width", "0px");
        $("iframe").addClass("gone");
        //$("#tabContent #" + data.id).css("width", "100%").css("height", "100%");
        $("#tabContent #" + data.id).removeClass("gone");
        triggerFrameResize($("#tabContent #" + data.id)[0].name);
    }).setCloseTabClickListener(function (data) {
        if (data == undefined) {
            $("#tabContent #home").remove();
        }
        else {
            $("#tabContent #" + data.id).remove();
        }
    });
    $(window).resize(function () {
        topTab.rearrange();
    });
    var leftTree = new tree.Tree("tree");
    var treeNodes = leftTree.render(builder.build('root'));
    $(treeNodes).each(function (i, e) {
        e.accept({
            visit: function (node) {
                $("#search-sel").append('<option value="' + node.getData().id + '">' + node.getData().value + '</option>');
                return false;
            }
        });
    });
    $("#search-sel").comboSelect();
    function extractNodeAndClick(e, id) {
        var stop = e.accept({
            visit: function (node) {
                if (node.getData().id == id) {
                    var currentNode = node;
                    while (node.getParent()) {
                        node = node.getParent();
                        if (!node.getData().extracted) {
                            leftTree.triggerClicked(node);
                        }
                    }
                    leftTree.triggerClicked(currentNode);
                    return true;
                }
                return false;
            }
        });
        return stop;
    }
    $("#search-btn").on("click", function () {
        if ("none" == $("#search-sel").val()) {
            return;
        }
        $(treeNodes).each(function (i, e) {
            return !extractNodeAndClick(e, $("#search-sel").val());
        });
    });
    window['onClickBreadcrumb'] = function (breadcrumbNode) {
        $(treeNodes).each(function (i, e) {
            return !extractNodeAndClick(e, breadcrumbNode.id);
        });
    };
    leftTree.setOnClickListener(function (node) {
        if (node.data.url != undefined) {
            var tab_1 = topTab.findTab(node.data.id + "tab");
            if (tab_1 == undefined) {
                topTab.addTab({
                    name: node.data.value,
                    id: node.data.id + "tab"
                });
                //$("iframe").css("display", "none");
                var tabFrame = $("#tabContent #" + node.data.id + "tab");
                if (tabFrame.length == 0) {
                    var breadcrumbNode = [];
                    var nt = node;
                    while (nt) {
                        breadcrumbNode.splice(0, 0, {
                            id: nt.getData().id,
                            value: nt.getData().value
                        });
                        nt = nt.getParent();
                    }
                    var url = "breads=" + JSON.stringify(breadcrumbNode);
                    if (node.data.url.indexOf("?") > 0) {
                        url = node.data.url + "&" + url;
                    }
                    else {
                        url = node.data.url + "?" + url;
                    }
                    $("#tabContent").append('<iframe frameborder="0" src=\'' + url + '\' ' +
                        'name="' + node.data.id + "tab" + '" ' +
                        'id="' + node.data.id + "tab" + '" ' +
                        'style="width:100%;height:100%">' +
                        '</iframe>');
                    tabFrame = $("#tabContent #" + node.data.id + "tab");
                    tabFrame.active(function () {
                        $("body").click();
                    });
                }
            }
            topTab.triggerClickTab(node.data.id + "tab");
        }
        if (node.depth() == 1) {
            var parent_1 = node.getParent();
            var oldFn = leftTree.setOnClickListener(undefined);
            for (var i = 0; i < parent_1.childCount(); ++i) {
                if (parent_1.childAt(i).data.extracted && parent_1.childAt(i) != node) {
                    leftTree.triggerClicked(parent_1.childAt(i));
                }
            }
            leftTree.setOnClickListener(oldFn);
        }
    });
    var stopClick = false;
    $(".nav-other").on("click", function () {
        if (stopClick) {
            return;
        }
        stopClick = true;
        if (!$(".nav-left").hasClass("nav-hide")) {
            $(".nav-left").animate({
                marginLeft: -$(".nav-left").width() + "px"
            }, 'fast', function () {
                $(".nav-left").addClass("nav-hide");
            });
            topTab.fixedHeight();
            $(".content-right").animate({
                width: "100%"
            }, {
                duration: 'fast',
                done: function () {
                    stopClick = false;
                    topTab.unFixedHeight();
                    topTab.rearrange();
                },
                step: function () {
                }
            });
            $(".nav-other").addClass("nav-other-clicked");
            $(".nav-other").removeClass("nav-other");
        }
        else {
            $(".nav-left").removeClass("nav-hide");
            $(".nav-left").css("margin-left", -$(".nav-left").width() + "px");
            $(".nav-left").animate({
                marginLeft: "0px"
            }, 'fast', function () {
                $(".nav-left").removeCss("margin-left");
            });
            topTab.fixedHeight();
            $(".content-right").animate({
                width: 500 / 6 + "%"
            }, {
                duration: 'fast',
                done: function () {
                    $(".content-right").removeCss("width");
                    stopClick = false;
                    topTab.unFixedHeight();
                    topTab.rearrange();
                },
                step: function () {
                }
            });
            $(".nav-other-clicked").addClass("nav-other");
            $(".nav-other-clicked").removeClass("nav-other-clicked");
        }
    });
    var resetPswHtml = React.renderToStaticMarkup(React.createElement("div", {"id": "resetPassword"}, React.createElement("div", {"className": "row"}, React.createElement("div", {"className": "col-md-12"}, React.createElement("form", {"role": "form"}, React.createElement("div", {"className": "form-group"}, React.createElement("span", {"className": "input-icon icon-right"}, React.createElement("input", {"type": "password", "className": "form-control", "id": "oldPsw", "placeholder": "原始密码"}), React.createElement("i", {"className": "fa fa-lock circular"}))), React.createElement("div", {"className": "form-group"}, React.createElement("span", {"className": "input-icon icon-right"}, React.createElement("input", {"type": "password", "className": "form-control", "id": "newPsw", "placeholder": "新密码"}), React.createElement("i", {"className": "fa fa-lock circular"}))), React.createElement("div", {"className": "form-group"}, React.createElement("span", {"className": "input-icon icon-right"}, React.createElement("input", {"type": "password", "className": "form-control", "id": "confPsw", "placeholder": "确认密码"}), React.createElement("i", {"className": "fa fa-lock circular"}))), React.createElement("div", {"className": "alert alert-danger fade in", "id": "warning", "style": { display: 'none' }}))))));
    $("#btnResetPassword").on("click", function () {
        var dialog = bootbox.dialog({
            message: resetPswHtml,
            title: "修改密码",
            className: "modal-darkorange",
            buttons: {
                success: {
                    label: "确定",
                    className: "btn-blue",
                    callback: function () {
                        var oldPsw = $("#oldPsw").val();
                        var newPsw = $("#newPsw").val();
                        var confPsw = $("#confPsw").val();
                        if (!oldPsw || !newPsw || !confPsw) {
                            $("#warning").text("密码不能为空").show();
                        }
                        var ajax = new Util.Ajax("/BusinessManagement/Account/v2/resetPassword.do", false);
                        ajax.post({
                            j_username: userName,
                            j_password: oldPsw,
                            loadNewPassword: newPsw,
                            reloadNewPassword: confPsw
                        }).then(function (ret) {
                            if (ret.result) {
                                dialog.modal("hide");
                            }
                            else {
                                $("#warning").text(ret.message).show();
                            }
                        });
                        return false;
                    }
                },
                "取消": {
                    className: "btn-default",
                    callback: function () {
                    }
                }
            }
        });
        dialog.modal("show");
    });
})(home || (home = {}));
