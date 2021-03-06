///<reference path="tabs/tab.ts"/>
///<reference path="tree/tree.ts"/>
///<reference path="pages/util.ts"/>
///<reference path="navigator.ts"/>
declare var $;
namespace home {

    import TreeNode = tree.TreeNode;
    import ITreeNode = tree.ITreeNode;

    $("#logoutBtn").on("click", ()=>{
        var loc = "" + window.location.href;
        var index = loc.lastIndexOf("/");
        loc = loc.substring(0, index);
        index = loc.lastIndexOf("/");
        let logoutAjax:any = new Util.Ajax(loc.substring(0, index) + "/v2/exitSystem.do");
        logoutAjax.get().then(function onSuccess() {

        }, function onFailed() {
            alert("网络错误");
        });
    });

    window['invalidate'] = (redirect:string)=> {
        if (redirect) {
            var index = redirect.lastIndexOf("/");
            window.location.href = redirect.substring(0, index) + "/v2" + redirect.substring(index);
        } else {
            $("#logoutBtn").click();
        }
    }

    function getFrameWindow(name){
        for (let i = 0; i < window.frames.length; ++i){
            if (window.frames[i].name == name){
                return window.frames[i];
            }
        }
    }

    function triggerFrameResize(name){
        let frame = getFrameWindow(name);
        if (frame && frame.triggerResize){
            frame.triggerResize();
        }
    }

    function updateFrameSize() {
        let heightUp = $(".content-up>div").css("height");
        $(".content-up").css("height", heightUp);

        let bodyHeight = parseInt($("body").css("height").replace("px", ""));

        let contentDownHeight = bodyHeight - parseInt(heightUp.replace("px", ""));
        $(".content-down").css("height", contentDownHeight + "px");


        let tabHeight = parseInt($("#tabNew .tab").css("height").replace("px", ""));
        $("#tabContent").css("height", (contentDownHeight - tabHeight) + "px");
        $("#tabContent-up").css("height", (contentDownHeight - tabHeight) + "px");
    }


    $(document).ready(()=> {
        updateFrameSize();
        $(document.body).css("visibility", "visible").show();
        $(".container-fluid").css("visibility", "visible").show();
        topTab.triggerClickHome();
        $(window).resize(()=> {
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
    }).setHomeClickListener(()=> {
        let homeFrame = $("#tabContent #home");
        if (homeFrame.length == 0) {
            //let src = '/BusinessManagement/ydzb/v2/hzb_zbhz.do?breads=[{"id":"1498180329800","value":"abc"},{"id":"1498180329801","value":"def"},{"id":"1498180329802","value":"hij"}]';
            let src = "/BusinessManagement/jsp/ui2/background.jsp";
            if (_77 || _87){
                src += "?map=true";
            }

            $("#tabContent").append('<iframe frameborder="0" name="home" id="home" src=' + src +  ' style="width:100%;height:100%;"></iframe>');
            homeFrame = $("#tabContent #home");
            $("#tabContent #home").active(()=> {
                $("body").click();
            });
            //homeFrame.css("height", "0").css("width", "0");
            $("iframe").addClass("gone");
        }
        //homeFrame.css("height", "100%").css("width", "100%");
        homeFrame.removeClass("gone");
        triggerFrameResize(homeFrame[0].name);

        topTab.disableMore("closeCurrent");
    }).setMoreClickListener((data)=> {
        let activeTab:tab.TabInfo = topTab.getActiveTab();
        if (data.id == "refresh") {
            if (activeTab != undefined) {
                let tabFrame = $("#tabContent #" + activeTab.id);
                $("#tabContent #" + activeTab.id)[0].src = tabFrame[0].src;
            } else {
                let homeFrame = $("#tabContent #home");
                $("#tabContent #home")[0].src = homeFrame[0].src;
            }
        } else if (data.id == "closeCurrent") {
            topTab.triggerClickClose(activeTab.id);
        } else if (data.id == "closeAll") {//close all
            let tabs = topTab.getTabs();
            let tabCopy = [];
            for (let i = 0; i < tabs.length; ++i) {
                tabCopy.push(tabs[i]);
            }
            for (let i = 0; i < tabCopy.length; ++i) {
                topTab.triggerClickClose(tabCopy[i].id);
            }

        } else if (data.id == "closeOther") {//close other
            let tabs = topTab.getTabs();
            let tabCopy = [];
            for (let i = 0; i < tabs.length; ++i) {
                if (topTab.getActiveTab() != tabs[i]) {
                    tabCopy.push(tabs[i]);
                }
            }
            for (let i = 0; i < tabCopy.length; ++i) {
                topTab.triggerClickClose(tabCopy[i].id);
            }
        }
    }).setTabClickListener((data:any)=> {
        topTab.enableMore("closeCurrent");
       // $("iframe").css("height", "0px").css("width", "0px");
        $("iframe").addClass("gone");
        //$("#tabContent #" + data.id).css("width", "100%").css("height", "100%");
        $("#tabContent #" + data.id).removeClass("gone");
        triggerFrameResize($("#tabContent #" + data.id)[0].name);

    }).setCloseTabClickListener((data)=> {
        if (data == undefined) {
            $("#tabContent #home").remove();
        } else {
            $("#tabContent #" + data.id).remove();
        }
    });

    $(window).resize(()=>{
        topTab.rearrange();
    });


    var leftTree = new tree.Tree("tree");
    let treeNodes:TreeNode[] = leftTree.render(builder.build('root'));

    $(treeNodes).each((i, e:TreeNode)=> {
        e.accept({
            visit: (node:TreeNode) => {
                $("#search-sel").append('<option value="' + node.getData().id + '">' + node.getData().value + '</option>');
                return false;
            }
        });
    });
    $("#search-sel").comboSelect();

    function extractNodeAndClickUrl(e:TreeNode, url:string):boolean {
        let stop = e.accept({
            visit: (node:TreeNode) => {
                if (url.indexOf(node.getData().url) >= 0) {
                    let currentNode = node;
                    let currentData = node.getData();
                    while (node.getParent()) {
                        node = node.getParent();
                        if (!node.getData().extracted) {
                            leftTree.triggerClicked(node);
                        }
                    }
                    let oldUrl = currentData.url;
                    currentData.url = url;
                    leftTree.triggerClicked(currentNode);
                    currentData.url = oldUrl;
                    return true;
                }
                return false;
            }
        });
        return stop;
    }


    function extractNodeAndClick(e:TreeNode, id:number):boolean {
        let stop = e.accept({
            visit: (node:TreeNode) => {
                if (node.getData().id == id) {
                    let currentNode = node;
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

    $("#search-btn").on("click", ()=> {
        if ("none" == $("#search-sel").val()) {
            return;
        }

        $(treeNodes).each((i, e:TreeNode)=> {
            return !extractNodeAndClick(e, $("#search-sel").val());
        });
    });

    window['onClickBreadcrumb'] = (breadcrumbNode:Util.BreadcrumbNode)=> {
        $(treeNodes).each((i, e:TreeNode)=> {
            return !extractNodeAndClick(e, breadcrumbNode.id);
        });
    }

    window['triggerClickUrl'] = (url:string)=> {
        $(treeNodes).each((i, e:TreeNode)=> {
            return !extractNodeAndClickUrl(e, url);
        });
    }

    window['triggerClickClose'] = (url:string, onFinish:any)=> {
        let tab;
        $(treeNodes).each((i, e:TreeNode)=> {
            let stop = e.accept({
                visit: (node:TreeNode) => {
                    if (url.indexOf(node.getData().url) >= 0) {
                        let tabId = node.getData().id + "tab";
                        tab = topTab.findTab(tabId);
                        if (tab){
                            topTab.triggerClickClose(tabId, onFinish);
                        }
                        return true;
                    }
                    return false;
                }
            });
            return !stop;
        });

        if (!tab) {
            if (onFinish){
                onFinish();
            }
        }
    }


    leftTree.setOnClickListener((node:any) => {
        if (node.data.url != undefined) {
            let tab = topTab.findTab(node.data.id + "tab");
            if (tab == undefined) {
                topTab.addTab({
                    name: node.data.value,
                    id: node.data.id + "tab"
                });

                //$("iframe").css("display", "none");
                let tabFrame = $("#tabContent #" + node.data.id + "tab");
                if (tabFrame.length == 0) {
                    let breadcrumbNode:Util.BreadcrumbNode[] = [];
                    let nt = node;
                    while (nt) {
                        breadcrumbNode.splice(0, 0, {
                            id: nt.getData().id,
                            value: nt.getData().value,
                            url : nt.getData().url
                        });
                        nt = nt.getParent();
                    }
                    let url = null;
                    if (node.data.url.indexOf("http://") == 0 || node.data.url.indexOf("https://") == 0 ){
                        url = encodeURI(node.data.url);
                    }else{
                        url = "breads=" + JSON.stringify(breadcrumbNode);
                        if (node.data.url.indexOf("?") > 0) {
                            url = encodeURI(node.data.url + "&" + url);
                        } else {
                            url = encodeURI(node.data.url + "?" + url);
                        }
                    }


                    $("#tabContent").append(
                        '<iframe frameborder="0" src=\'' + url + '\' ' +
                        'name="' + node.data.id + "tab" + '" ' +
                        'id="' + node.data.id + "tab" + '" ' +
                        'style="width:100%;height:100%">' +
                        '</iframe>');
                    tabFrame = $("#tabContent #" + node.data.id + "tab");
                    tabFrame.active(()=> {
                        $("body").click();
                    });
                }
            }
            topTab.triggerClickTab(node.data.id + "tab");
        }

        if (node.depth() == 1) {
            let parent = node.getParent();
            let oldFn = leftTree.setOnClickListener(undefined);
            for (let i = 0; i < parent.childCount(); ++i) {
                if (parent.childAt(i).data.extracted && parent.childAt(i) != node) {
                    leftTree.triggerClicked(parent.childAt(i));
                }
            }
            leftTree.setOnClickListener(oldFn);
        }
    });

    let stopClick = false;
    $(".nav-other").on("click", ()=> {

        if (stopClick) {
            return;
        }

        stopClick = true;
        if (!$(".nav-left").hasClass("nav-hide")) {

            $(".nav-left").animate({
                marginLeft: -$(".nav-left").width() + "px"
            }, 'fast', ()=> {
                $(".nav-left").addClass("nav-hide");
            });

            topTab.fixedHeight();
            $(".content-right").animate({
                width: "100%"
            }, {
                duration: 'fast',
                done : ()=> {
                    stopClick = false;
                    topTab.unFixedHeight();
                    topTab.rearrange();
                },
                step : ()=>{

                }
            });

            $(".nav-other").addClass("nav-other-clicked");
            $(".nav-other").removeClass("nav-other");
        } else {
            $(".nav-left").removeClass("nav-hide");
            $(".nav-left").css("margin-left", -$(".nav-left").width() + "px")
            $(".nav-left").animate({
                marginLeft: "0px"
            }, 'fast', ()=> {
                $(".nav-left").removeCss("margin-left");
            });
            topTab.fixedHeight();
            $(".content-right").animate({
                width: 500 / 6 + "%"
            }, {
                duration: 'fast',
                done : ()=> {
                    $(".content-right").removeCss("width");
                    stopClick = false;
                    topTab.unFixedHeight();
                    topTab.rearrange();
                },
                step : ()=>{

                }
            });
            $(".nav-other-clicked").addClass("nav-other");
            $(".nav-other-clicked").removeClass("nav-other-clicked");
        }
    });

    declare var React:any;

    let resetPswHtml:string = React.renderToStaticMarkup(
        <div id="resetPassword">
            <div className="row">
                <div className="col-md-12">
                    <form role="form">
                        <div className="form-group">
                            <span className="input-icon icon-right">
                                <input type="password"
                                       className="form-control" id="oldPsw" placeholder="原始密码"/>
                                <i className="fa fa-lock circular"></i>
                            </span>
                        </div>
                        <div className="form-group">
                            <span className="input-icon icon-right">
                                <input type="password"
                                       className="form-control" id="newPsw" placeholder="新密码"/>
                                <i className="fa fa-lock circular"></i>
                            </span>
                        </div>
                        <div className="form-group">
                            <span className="input-icon icon-right">
                                <input type="password" className="form-control" id="confPsw"
                                       placeholder="确认密码"/>
                                <i className="fa fa-lock circular"></i>
                            </span>
                        </div>
                        <div className="alert alert-danger fade in" id="warning" style={{display:'none'}}>
                        </div>
                    </form>
                </div>
            </div>
        </div>);

    $("#btnResetPassword").on("click", ()=>{
        let dialog = bootbox.dialog({
            message: resetPswHtml,
            title: "修改密码",
            className: "modal-darkorange",
            buttons: {
                success: {
                    label: "确定",
                    className: "btn-blue",
                    callback: () => {
                        let oldPsw = $("#oldPsw").val();
                        let newPsw = $("#newPsw").val();
                        let confPsw = $("#confPsw").val();
                        if (!oldPsw || !newPsw || !confPsw){
                            $("#warning").text("密码不能为空").show();
                        }

                        let ajax = new Util.Ajax("/BusinessManagement/Account/v2/resetPassword.do", false);
                        ajax.post({
                            j_username : userName,
                            j_password : oldPsw,
                            loadNewPassword : newPsw,
                            reloadNewPassword : confPsw
                        }).then((ret:any)=>{
                            if (ret.result){
                                dialog.modal("hide");
                            }else{
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
        if (Util.isMSIE()){
            $('input[placeholder]').placeholder();
        }
    });

}