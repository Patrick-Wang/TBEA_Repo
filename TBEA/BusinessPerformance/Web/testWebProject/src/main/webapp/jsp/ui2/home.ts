///<reference path="tabs/tab.ts"/>
///<reference path="tree/tree.ts"/>
///<reference path="../util.ts"/>
///<reference path="navigator.ts"/>
declare var $;
module home{

    export function logout(){
        let logoutAjax:any = new Util.Ajax("../exitSystem.do");
        logoutAjax.get().then(function onSuccess() {

        }, function onFailed() {
            alert("网络错误");
        });
    }

    import ITreeNode = tree.ITreeNode;
    function updateFrameSize(){
        let heightUp = $(".content-up>div").css("height");
        $(".content-up").css("height", heightUp);

        let bodyHeight = parseInt($("body").css("height").replace("px", ""));

        let contentDownHeight = bodyHeight - parseInt(heightUp.replace("px", ""));
        $(".content-down").css("height", contentDownHeight + "px");


        let tabHeight = parseInt($("#tabNew .tab").css("height").replace("px", ""));
        $("#tabContent").css("height", (contentDownHeight - tabHeight) + "px");
        $("#tabContent-up").css("height", (contentDownHeight - tabHeight) + "px");
    }

    var topTab = new tab.Tab("tabNew");

    $(document).ready(()=>{
        updateFrameSize();
        $(".container-fluid").css("visibility", "visible");
        //myTab.triggerClickHome();
        $(window).resize(()=>{
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
        let homeFrame = $("#tabContent #home");
        if (homeFrame.length == 0){
            $("#tabContent").append('<iframe src="/BusinessManagement/Login/login.do" id="home" style="width:100%;height:100%" ></iframe>');
            homeFrame = $("#tabContent #home");
            $("#tabContent #home").active(()=>{
                $("body").click();
            });
            $("iframe").css("height", "0px").css("width", "0px");
        }
        homeFrame.css("height", "100%").css("width", "100%");
        topTab.disableMore("closeCurrent");
    });

    topTab.setMoreClickListener(function (data) {

        let activeTab:tab.TabInfo = topTab.getActiveTab();
        if (data.id == "refresh"){
           if (activeTab != undefined){
               let tabFrame = $("#tabContent #" + activeTab.id);
               $("#tabContent #" + activeTab.id)[0].src = tabFrame[0].src;
           }else{
               let homeFrame = $("#tabContent #home");
               $("#tabContent #home")[0].src = homeFrame[0].src;
           }
        } else if (data.id == "closeCurrent"){
            topTab.triggerClickClose(activeTab.id);
        } else if (data.id == "closeAll"){//close all
            let tabs = topTab.getTabs();
            let tabCopy = [];
            for(let i = 0; i < tabs.length; ++i){
                tabCopy.push(tabs[i]);
            }
            for(let i = 0; i < tabCopy.length; ++i){
                topTab.triggerClickClose(tabCopy[i].id);
            }

        } else if (data.id == "closeOther"){//close other
            let tabs = topTab.getTabs();
            let tabCopy = [];
            for(let i = 0; i < tabs.length; ++i){
                if (topTab.getActiveTab() != tabs[i]){
                    tabCopy.push(tabs[i]);
                }
            }
            for(let i = 0; i < tabCopy.length; ++i){
                topTab.triggerClickClose(tabCopy[i].id);
            }
        }
    });

    topTab.setTabClickListener(function (data:any) {
        topTab.enableMore("closeCurrent");
        $("iframe").css("height", "0px").css("width", "0px");
        $("#tabContent #" + data.id).css("width", "100%").css("height", "100%");
    });

    topTab.setCloseTabClickListener(function (data) {
        if (data == undefined){
            $("#tabContent #home").remove();
        }else{
            $("#tabContent #" + data.id).remove();
        }
    });

    topTab.triggerClickHome();

    var leftTree = new tree.Tree("tree");

    leftTree.render(builder.build('root'));

    leftTree.setOnClickListener((node:any) =>{
        if (node.data.url != undefined){
            let tab = topTab.findTab(node.data.id + "tab");
            if (tab == undefined){
                topTab.addTab({
                    name: node.data.value,
                    id: node.data.id + "tab"
                });

                //$("iframe").css("display", "none");
                let tabFrame = $("#tabContent #" + node.data.id + "tab");
                if (tabFrame.length == 0){
                    $("#tabContent").append(
                        '<iframe src="' + node.data.url + '" ' +
                        'id="' + node.data.id + "tab" + '" ' +
                        'style="width:100%;height:100%">' +
                        '</iframe>');
                    tabFrame = $("#tabContent #" + node.data.id + "tab");
                    tabFrame.active(()=>{
                        $("body").click();
                    });
                }
            }
            topTab.triggerClickTab(node.data.id + "tab");
        }

        if (node.depth() == 1){
            let parent = node.getParent();
            let oldFn = leftTree.setOnClickListener(undefined);
            for (let i = 0; i < parent.childCount(); ++i){
                if (parent.childAt(i).data.extracted && parent.childAt(i) != node){
                    leftTree.triggerClicked(parent.childAt(i));
                }
            }
            leftTree.setOnClickListener(oldFn);
        }
    });

}