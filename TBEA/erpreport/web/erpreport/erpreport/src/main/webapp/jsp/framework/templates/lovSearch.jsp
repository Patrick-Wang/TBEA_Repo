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
    <title>特变电工报表管理平台</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/jsp/build/images/icon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jsp\plugins\font-awesome\css\font-awesome.css">
    <link
            href="${pageContext.request.contextPath}/jsp/plugins/bootstrap-3.3.0-dist/dist/css/bootstrap.min.css"
            rel="stylesheet" />
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/jsp/plugins/bootstrap-select-1.12.4/dist/css/bootstrap-select.min.css">
    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/jsp/plugins/jqgrid/themes/redmond/jquery-ui-custom.css">
    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/jsp/plugins/jqgrid/themes/ui.jqgrid.css">
    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/jsp/plugins/jqgrid/themes/ui.multiselect.css">
    <link rel="stylesheet"
            href="${pageContext.request.contextPath}/jsp/plugins/beyond/beyond.css"/>


    <script src="${pageContext.request.contextPath}/jsp/es5-shim.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/es5-sham.js"></script>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/framework/templates/lovSearch.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/ui2.css" media="all">
    <script src="${pageContext.request.contextPath}/jsp/plugins/jquery/jquery-1.12.3.js"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/plugins/jqgrid/js/jquery.tablednd.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/plugins/jqgrid/js/jquery.contextmenu.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/plugins/jqgrid/js/i18n/grid.locale-cn.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/plugins/jqgrid/js/jquery.layout.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/plugins/jqgrid/js/jquery.jqGrid.js"
            type="text/javascript"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/plugins/bootstrap-3.3.0-dist/dist/js/bootstrap.min.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script
            src="${pageContext.request.contextPath}/jsp/plugins/bootstrap-select-1.12.4/dist/js/bootstrap-select.min.js"></script>
    <script
            src="${pageContext.request.contextPath}/jsp/plugins/bootstrap-select-1.12.4/dist/js/i18n/defaults-zh_CN.js"></script>

    <script src="${pageContext.request.contextPath}/jsp/json2.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/ie8.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/layui-ie8.js"></script>
    <!-- jqgrid assist -->
    <script
            src="${pageContext.request.contextPath}/jsp/plugins/jqgrid/jqassist.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/framework/templates/util.js"></script>
</head>
<body>
<div class="nav-area">
    <span class="layui-breadcrumb">
    </span>
</div>
<script>
    var breadcrumb = '${param.breadcrumb}';
    if (breadcrumb){
        breadcrumb = JSON.parse(breadcrumb);
        for (var i = 0; i < breadcrumb.length; ++i){
            $(".layui-breadcrumb").append('<a><cite>' + breadcrumb[i] + '</cite></a>')
        }
    }
</script>
<div class="search-area">
    <div class="option-area hidden">
        <button class="btn btn-primary btn-custom-sz btn-search opt-btn" onclick="search.searchPanel.onClickSearch()">
            <i class="fa fa-search"> </i>查找</button>
        <button class="btn btn-primary btn-custom-sz btn-reset opt-btn" onclick="search.searchPanel.onClickReset()">
            <i class="fa fa-refresh"> </i>重置</button>

        <div class="cux-btn-group-fun">
            <button class="btn btn-primary btn-custom-sz btn-export opt-btn" onclick="search.searchPanel.onClickExport()">
                <i class="fa fa-file-excel-o"> </i>导出</button>
            <button class="btn btn-primary btn-custom-sz btn-collect opt-btn" onclick="search.searchPanel.onClickCollect()">
                <i class="fa fa-star"> </i>收藏</button>
            <c:if test="${! empty printUrl}">
                <button class="btn btn-primary btn-custom-sz btn-print opt-btn" onclick="search.searchPanel.onClickPrint()">
                    <i class="fa fa-print"> </i>打印</button>
            </c:if>
        </div>
    </div>

</div>
<div>
    <div class="row">
        <div class="col-lg-12 col-sm-12 col-xs-12">
            <div class="well">
                <div id="table" class="jq-table"></div>
            </div>
        </div>
    </div>
</div>
<form id="exportForm" method="post" style="display:none">

</form>
</body>
<script src="${pageContext.request.contextPath}/jsp/plugins/layui/layui.all.js"></script>
<script>
    var printUrl = '${printUrl}';
    var context = {
        pager: '${pager}' == 'true',
        options: JSON.parse('${options}'),
        updateUrl: '${updateUrl}.do',
        exportUrl: '${exportUrl}.do',
        printUrl: printUrl ? printUrl + ".do" : "",
        baseUrl: '${pageContext.request.contextPath}/',
        item : ${param.item},
        isFavorite : ${isFavorite ? "true" : "false"}
    }
</script>
<%@include file="../../loading.jsp"%>
<script src="${pageContext.request.contextPath}/jsp/framework/templates/lovSearch.js"></script>

</html>