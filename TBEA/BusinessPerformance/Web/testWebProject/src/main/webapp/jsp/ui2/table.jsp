<%--
  Created by IntelliJ IDEA.
  User: zq150
  Date: 2018/2/5
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta http-equiv="Content-Type" content="text/javascript;charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>报表管理平台</title>
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/jquery/jquery-1.12.3.js"></script>
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath}/jsp/ui2/img/logo.png"
          type="image/x-icon">

    <!--Basic Styles-->
    <link
            href="${pageContext.request.contextPath}/jsp/ui2/assets/css/bootstrap.min.css"
            rel="stylesheet"/>

    <link
            href="${pageContext.request.contextPath}/jsp/ui2/assets/css/font-awesome.min.css"
            rel="stylesheet"/>
    <link
            href="${pageContext.request.contextPath}/jsp/ui2/assets/css/weather-icons.min.css"
            rel="stylesheet"/>


    <!--Beyond styles-->
    <link
            href="${pageContext.request.contextPath}/jsp/ui2/assets/css/beyond.css"
            rel="stylesheet"/>
    <link
            href="${pageContext.request.contextPath}/jsp/ui2/assets/css/demo.min.css"
            rel="stylesheet"/>
    <link
            href="${pageContext.request.contextPath}/jsp/ui2/assets/css/typicons.min.css"
            rel="stylesheet"/>
    <link
            href="${pageContext.request.contextPath}/jsp/ui2/assets/css/animate.min.css"
            rel="stylesheet"/>
    <link id="skin-link" href="" rel="stylesheet" type="text/css"/>

    <!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/assets/js/skins.min.js"></script>


    <!-- jquery ui -->
    <!-- jquery ui gray -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.js"></script>
    <!-- jquery ui blue -->
    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/redmond/jquery-ui-custom.css">
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery-ui-custom.min.js"
            type="text/javascript"></script>

    <!-- 多选菜单 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/jquery.multiselect.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/assets/style.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/assets/prettify.css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/assets/prettify.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/jquery.multiselect.js"></script>
    <link
            href="${pageContext.request.contextPath}/jsp/ui2/font-awesome/css/font-awesome.min.css"
            rel="stylesheet">
    <!-- jedate -->
    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/skin/jedate.css">
    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/skin/deepgreen.css">
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/jquery.jedate.js"
            type="text/javascript"></script>
    <!-- jqgrid -->
    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/ui.jqgrid.css">
    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/ui.multiselect.css">
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.tablednd.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.contextmenu.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/i18n/grid.locale-cn.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.layout.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.jqGrid.js"
            type="text/javascript"></script>

    <!-- jqgrid assist -->
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/jqassist.js"
            type="text/javascript"></script>

    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/json2.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/util.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/vector.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/dateSelector.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/unitedSelector.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/companySelector.js"
            type="text/javascript"></script>

    <script
            src="${pageContext.request.contextPath}/jsp/ui2/jquery/jqueryex.js"
            type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/jsp/ui2/ui2.css"
          rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/templates/driver.js"></script>
    <style>
        body:before {
            background-color: white;
        }

    </style>
</head>
<body style="background:white">
<div>
    <div class="row">
        <div class="col-lg-12 col-sm-12 col-xs-12">
            <div class="well">
                <div id="table" class="jq-table"></div>
            </div>
        </div>
    </div>
</div>
<script>
    var context = {};
    var url = window.location.href;
    var i = url.indexOf('?');
    if (i > 0) {
        url = url.substring(i + 1);
        var params = url.split('&');
        for (var j = 0; j < params.length; ++j) {
            var pair = params[j].split('=');
            context[pair[0]] = decodeURI(pair[1]);
            if (context.breads) {
                break;
            }
        }
    }
    if (context.type == 'open') {
        var fileName = context.file;
        if (fileName) {
            $(document).ready(function () {
                Driver.query2Table(fileName, "table");
            })
        }
    }
    $(document).ready(function () {
        window.onresize = function () {
            reSize();
        }
        window.onload = function () {
            reSize();
        }
        reSize = function () {
            //$(".well").height($("body").height() - 20);
        }
    })
</script>
</body>
</html>
