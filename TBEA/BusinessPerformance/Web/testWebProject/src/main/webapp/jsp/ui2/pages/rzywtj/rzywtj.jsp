﻿<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
<meta charset="utf-8" />
<title></title>


<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<%@include file="../../ie8-t.jsp"%>

<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jquery-1.12.3.js"></script>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/jsp/ui2/assets/img/favicon.png"
	type="image/x-icon">

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
	src="${pageContext.request.contextPath}/jsp/ui2/assets/js/toastr/toastr.js"></script>
	<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/messageBox.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jqueryex.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/jsp/ui2/ui2.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/scroll/css/jquery.mCustomScrollbar.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/scroll/js/jquery.mCustomScrollbar.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/route/route.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/basic/basicdef.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/basic/basic.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/basic/basicShow.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/templates/singleDateReport/show.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/rzywtj/rzywtj.js"></script>
<%@include file="../../ie8-b.jsp"%>
</head>
<!-- /Head -->
<!-- Body -->
<body>
	<!-- Main Container -->
	<div class="main-container container-fluid">
		<!-- Page Container -->
		<div class="page-container">

			<!-- Page Content -->
			<div class="page-content">
				<!-- Page Breadcrumb -->
				<div class="page-breadcrumbs">
					<ul class="breadcrumb">
					</ul>
				</div>
				<!-- /Page Breadcrumb -->

				<!-- Page Header -->
				<div class="page-header position-relative">
					<div class="header-title">
					<div id="headerHost" class="pull-left">
						<div class="workinput pull-left hidden">
							<input id="grid-date" type="text" readonly="readonly"><i
								class="fa fa-calendar"></i>
						</div>
						<div id="sels" class="pull-left">
							<div id="item-sel1" class="pull-left"></div>
							<div id="item-sel2" class="pull-left"></div>
						</div>
						<div id="grid-update" class="btn btn-default"
							onclick="framework.router.to(framework.templates.singleDateReport.FRAME_ID).send(framework.templates.singleDateReport.FE_UPDATE)">
							查找 <i class="fa fa-search"></i>
						</div>
						<%--<div id="grid-solve" class="btn btn-default hidden"--%>
							<%--onclick="framework.router.to(framework.templates.singleDateReport.FRAME_ID).send(rzywtj.FE_SOLVED)">--%>
							<%--处理 <i class="fa fa-check"></i>--%>
						<%--</div>--%>
						<div id="grid-export" class="btn btn-default"
							onclick="framework.router.to(framework.templates.singleDateReport.FRAME_ID).send(framework.templates.singleDateReport.FE_EXPORTEXCEL, 'exportExcel')">
							导出 <i class="fa fa-file-excel-o"></i>
						</div>
						</div>
						<form id="exportExcel" style="display: none" method="post"></form>
					</div>
				</div>
				<!-- /Page Header -->
				<!-- Page Body -->
				<div class="page-body">
					<div class="col-lg-12 col-sm-12 col-xs-12">
							<div class="well">
								<div id="table"></div>
								<div id="tip" class="hidden">暂时没有数据</div>
							</div>
					</div>
				</div>
				<!-- /Page Body -->
			</div>
			<!-- /Page Content -->
		</div>
		<!-- /Page Container -->
		<!-- Main Container -->
	</div>
	<!--Basic Scripts-->

	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/slimscroll/jquery.slimscroll.min.js"></script>

	<!--Beyond Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/beyond.min.js"></script>

	<!--Page Related Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/bootbox/bootbox.js"></script>
	<%@include file="../loading.jsp"%>
	<script>
		Util.Breadcrumb.render(JSON.parse('${param.breads}'));
		if (Util.isIframe()) {
			Util.Breadcrumb.setOnClickListener(function(breadNode) {
				window.parent['onClickBreadcrumb']
				&& window.parent['onClickBreadcrumb'](breadNode);
			});
		}
		$(document).ready(function () {
			rzywtj.create();

	        var dateEnd;
	        var date;
	        if ('${date}' == ""){
	        	date = Util.parseDate('${year}', '${month}', '${day}');
	        }else{
	            date = Util.toDate('${date}');
	            if ('${dateEnd}' != ''){
				    dateEnd = Util.toDate('${dateEnd}');
	            }
	        }

	        var nd = '${nodeData}';
	        var nd2 = '${nodeData2}';
	        var ind = nd == '' ? undefined : JSON.parse(nd);
	        var ind2 = nd2 == '' ? undefined : JSON.parse(nd2);
	        
	        framework.router.to(framework.templates.singleDateReport.FRAME_ID).send(framework.basic.FrameEvent.FE_INIT_EVENT,{
	            dtId:"grid-date",
	            date: date,
	            dateEnd:dateEnd,
	            host:"table",
	            title:"${title}",
	            asSeason:"${asSeason}" == "true",
	            asSeasonAcc:"${asSeasonAcc}" == "true",
	            jdName:'${jdName}' == "" ? undefined : JSON.parse('${jdName}'),
	            updateUrl:"${updateUrl}.do",
	            exportUrl:"${exportUrl}.do",
				submitUrl:"${submitUrl}.do",
	            itemId:"item-sel1",
	            itemNodes: ind,
	            itemId2:"item-sel2",
	            itemNodes2: ind2
	        });
		});
	</script>

</body>
<!--  /Body -->
</html>
