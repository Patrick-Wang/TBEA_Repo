/// <reference path="util.ts" />
//绩效管理信息平台 
//http://172.28.8.119/HQ/myportal/__ac0x3login/__tpaction?requestSource=HQ_login&ssousername=sunfuda&ssopassword=123456
//OA
//http://192.168.7.12:8080/login.do?validate=login&ABS_SchemeName=jxkh&userId=sunfuda&pass=000000
//jingyingguankong
//http://192.168.7.22/BusinessManagement/Login/validate.do?j_username=fujianghua&j_password=fujianghua
function showBondPage(systemCode, sysTemName) {
    $("div.theme-popover3 .dform3").empty();
    var params = "params.ssoSysCode=" + systemCode;
    //	$.getJSON("/fe/sso/querySystemParams.gsp",params,function(data){
    //	});
    $('.theme-popover-mask').fadeIn(100);
    $('.theme-popover3').slideDown(200);
    var data = JSON.parse('{"infoList":[{"paramsName":"用户名","paramsKey":"bindName","sysCode":' + systemCode + ',"encrypt":0,"encryptTramsfer":0},{"paramsName":"用户密码","paramsKey":"bindPsw","sysCode":' + systemCode + ',"encrypt":0,"encryptTramsfer":0}]}');
    var paramsList = data.infoList;
    var html = "";
    $.each(paramsList, function (i, item) {
        html += "<div class=\"dform_div\"><span style=\"min-width:80px;\">"
            + paramsList[i].paramsName +
            ":</span><input style=\"width:251px;\" flg=\"wr\" onblur=\"testInput('" + paramsList[i].paramsKey +
            "')\" maxlength=\"30\" type=\"text\"  id=" +
            paramsList[i].paramsKey +
            " name=" + paramsList[i].paramsKey +
            " class=\"alert_text\" /><span style=\"color:#E61212\" id='" +
            paramsList[i].paramsKey +
            "span'></span></div>";
    });
    html += "<div class=\"dform_div\"><input type=\"button\" id=\"updateUserInfo\" value=\"绑 定\" class=\"alert_button\" onclick=\"goToBond('" +
        systemCode + "','" + sysTemName + "')\"/></div>";
    $("div.theme-popover3 .dform3").append(html);
    html = "";
}
function goToBond(systemCode, sysTemName) {
    var flag = false;
    var param = {};
    var inputs = $("input[flg='wr']");
    inputs.each(function () {
        if (testInput($(this).attr("id"))) {
            param[$(this).attr("name")] = $(this).val();
            flag = true;
        }
        else {
            flag = false;
        }
    });
    if (flag) {
        param.bindSystem = systemCode;
        var ajax = new Util.Ajax("bind.do");
        ajax.get(param).then(function (data) {
            if (data.result) {
                $("div.theme-popover3 .dform3").empty();
                var html = "<div style=\"text-align: center; width: 300px;  margin: 60px auto;\"><h3>绑定 “" + sysTemName + "” 成功!</h3></div>";
                $("div.theme-popover3 .dform3").append(html);
            }
            else {
                $("div.theme-popover3 .dform3").empty();
                var html = "<div style=\"text-align: center; width: 300px;  margin: 60px auto;\"><h3>绑定 “" + sysTemName + "” 失败!</h3></div>";
                $("div.theme-popover3 .dform3").append(html);
            }
        });
    }
}
function testInput(id) {
    var idstr = "#" + id;
    var text = $(idstr).val().replace(/[ ]/g, "");
    if (text == null || text == "") {
        $(idstr + "span").text("信息填写不允许为空");
        return false;
    }
    else {
        $(idstr + "span").text("");
    }
    return true;
}
function login(system, systemCode) {
    var ajax = new Util.Ajax("get_login_url.do");
    ajax.get({ sysId: system }).then(function (result) {
        if (result.url == "") {
            showBondPage(system, systemCode);
        }
        else {
            window.location.href = result.url;
        }
    });
}
