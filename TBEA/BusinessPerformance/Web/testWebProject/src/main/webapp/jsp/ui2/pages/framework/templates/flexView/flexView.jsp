<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta http-equiv="Content-Type" content="text/javascript;charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <%@include file="../../../../ie8-t.jsp"%>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/jsp/build/images/icon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/ui2/plugins/layui/css/layui.css" media="all">
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/jquery/jquery-1.12.3.js"></script>

    <!--Basic Styles-->
    <link
            href="${pageContext.request.contextPath}/jsp/ui2/assets/css/bootstrap.min.css"
            rel="stylesheet" />

    <link
            href="${pageContext.request.contextPath}/jsp/ui2/assets/css/font-awesome.min.css"
            rel="stylesheet" />
    <link
            href="${pageContext.request.contextPath}/jsp/ui2/assets/css/weather-icons.min.css"
            rel="stylesheet" />


    <!--Beyond styles-->
    <link
            href="${pageContext.request.contextPath}/jsp/ui2/assets/css/beyond.css"
            rel="stylesheet" />
    <link
            href="${pageContext.request.contextPath}/jsp/ui2/assets/css/demo.min.css"
            rel="stylesheet" />
    <link
            href="${pageContext.request.contextPath}/jsp/ui2/assets/css/typicons.min.css"
            rel="stylesheet" />
    <link
            href="${pageContext.request.contextPath}/jsp/ui2/assets/css/animate.min.css"
            rel="stylesheet" />
    <link id="skin-link" href="" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/ui2/pages/framework/templates/flexView/flexView.css" media="all">

    <!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/assets/js/skins.min.js"></script>


    <!-- jquery ui -->
    <!-- jquery ui gray -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.css" />
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
          href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/jquery.multiselect.css" />
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/assets/style.css" />
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/assets/prettify.css" />
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

    <link
            href="${pageContext.request.contextPath}/jsp/ui2/pages/components/select2/css/select2.css"
            rel="stylesheet">
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/components/select2/js/select2.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/components/select2/js/i18n/zh-CN.js"
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
          rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/templates/flexView/ie8.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/templates/flexView/layui-ie8.js"></script>
    <%@include file="../../../../ie8-b.jsp"%>
</head>
<body>
<div class="page-breadcrumbs nav-area">
    <ul class="breadcrumb">
    </ul>
</div>
<script>
    var breadcrumb = '${param.breads}';
    if (breadcrumb){
        Util.Breadcrumb.render(JSON.parse('${param.breads}'));
        if (Util.isIframe()) {
            Util.Breadcrumb.setOnClickListener(function(breadNode) {
                window.parent['onClickBreadcrumb']
                && window.parent['onClickBreadcrumb'](breadNode);
            });
        }
    }
</script>

<div class="search-area">
    <div class="option-area hidden">


        <div class="cux-btn-group-fun">
            <button class="btn btn-default btn-custom-sz btn-search opt-btn" onclick="search.searchPanel.onClickSearch()">
                <i class="fa fa-search"> </i>查找</button>
            <button class="btn btn-default btn-custom-sz btn-reset opt-btn" onclick="search.searchPanel.onClickReset()">
                <i class="fa fa-refresh"> </i>重置</button>
            <button class="btn btn-default btn-custom-sz btn-export opt-btn" onclick="search.searchPanel.onClickExport()">
                <i class="fa fa-file-excel-o"> </i>导出</button>
        </div>
    </div>

</div>
<div>
    <div class="row">
        <div class="col-lg-12 col-sm-12 col-xs-12">
            <div class="well">
                <div>
                    <div id="table" class="jq-table"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<form id="exportForm" method="post" style="display:none">

</form>
</body>
<script src="${pageContext.request.contextPath}/jsp/ui2/plugins/layui/layui.all.js"></script>
<script>

    var printUrl = '${printUrl}';
    var context = {
        pager: '${pager}' == 'true',
        options: JSON.parse('${options}'),
        updateUrl: '${updateUrl}.do',
        exportUrl: '${exportUrl}.do',
        printUrl: printUrl ? printUrl + ".do" : "",
        baseUrl: '${pageContext.request.contextPath}/',
        isFavorite : ${isFavorite ? "true" : "false"}
    }
</script>
<%@include file="../../../loading.jsp"%>
<script src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/templates/flexView/flexView.js"></script>

</html>