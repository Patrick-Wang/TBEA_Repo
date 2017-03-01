///<reference path="tabs/tab.ts"/>
///<reference path="tree/tree.ts"/>
///<reference path="../util.ts"/>
///<reference path="navigator.ts"/>
var home;
(function (home) {
    function logout() {
        var logoutAjax = new Util.Ajax("../exitSystem.do");
        logoutAjax.get().then(function onSuccess() {
        }, function onFailed() {
            alert("网络错误");
        });
    }
    home.logout = logout;
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
    var topTab = new tab.Tab("tabNew");
    $(document).ready(function () {
        updateFrameSize();
        $(".container-fluid").css("visibility", "visible");
        //myTab.triggerClickHome();
        $(window).resize(function () {
            updateFrameSize();
        });
    });
    $(".nav-left").mCustomScrollbar({
        theme: "minimal-dark",
        scrollInertia: 0
    });
    topTab.addMore({
        id: "refresh",
        name: "刷新"
    });
    topTab.addMore({
        id: "closeCurrent",
        name: "关闭当前页"
    });
    topTab.addMore({
        id: "closeAll",
        name: "关闭全部页"
    });
    topTab.addMore({
        id: "closeOther",
        name: "关闭其它页"
    });
    topTab.setHomeClickListener(function () {
        var homeFrame = $("#tabContent #home");
        if (homeFrame.length == 0) {
            $("#tabContent").append('<iframe src="/BusinessManagement/Login/login.do" id="home" style="width:100%;height:100%" ></iframe>');
            homeFrame = $("#tabContent #home");
            $("#tabContent #home").active(function () {
                $("body").click();
            });
            $("iframe").css("display", "none");
        }
        homeFrame.css("display", "");
        topTab.disableMore("closeCurrent");
    });
    topTab.setMoreClickListener(function (data) {
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
    });
    topTab.setTabClickListener(function (data) {
        topTab.enableMore("closeCurrent");
        $("iframe").css("display", "none");
        $("#tabContent #" + data.id).css("display", "");
    });
    topTab.setCloseTabClickListener(function (data) {
        if (data == undefined) {
            $("#tabContent #home").remove();
        }
        else {
            $("#tabContent #" + data.id).remove();
        }
    });
    topTab.triggerClickHome();
    var leftTree = new tree.Tree("tree");
    leftTree.render(builder.build('root'));
    leftTree.setOnClickListener(function (node) {
        if (node.data.url != undefined) {
            var tab_1 = topTab.findTab(node.data.id + "tab");
            if (tab_1 == undefined) {
                topTab.addTab({
                    name: node.data.value,
                    id: node.data.id + "tab"
                });
                $("iframe").css("display", "none");
                var tabFrame = $("#tabContent #" + node.data.id + "tab");
                if (tabFrame.length == 0) {
                    $("#tabContent").append('<iframe src="' + node.data.url + '" ' +
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
})(home || (home = {}));
