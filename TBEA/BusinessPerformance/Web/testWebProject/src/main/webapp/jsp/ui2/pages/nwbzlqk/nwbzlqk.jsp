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

<meta name="description" content="modals and wells" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script>
	Date.now = function(){
		return new Date();
	}
</script>
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
	src="${pageContext.request.contextPath}/jsp/ui2/assets/js/toastr/toastr.js"></script>
	<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/messageBox.js"
	type="text/javascript"></script>
<%@include file="../framework/basic/basicShow.jsp"%>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/nwbzlqk/nwbzlqkdef.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/nwbzlqk/nwbzlqk.js"></script>

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
							<div class="workinput pull-left">
								<input id="grid-date" type="text" readonly="readonly"><i
									class="fa fa-calendar"></i>
							</div>
							<div id="sels" class="pull-left">
								<div id="comp-sel" class="pull-left"></div>
								<div id="item-sel" class="pull-left"></div>
								<select id="season-sel" class="pull-left">
									<option value="0" selected="selected">月度</option>
									<option value="1">季度</option>
								</select>
							</div>
							<div id="grid-update" class="btn btn-default"
								onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_UPDATE)">
								查找 <i class="fa fa-search"></i>
							</div>
							<div id="grid-export" class="btn btn-default" style="display:none"
								onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_EXPORTEXCEL, 'exportExcel')">
								导出 <i class="fa fa-file-excel-o"></i>
							</div>
							<div class=" comment-area" style="display: inline-block;">
							<c:if test="${pageType == 2}">
								<%-- 2 录入页面--%>
								<div id="saveComment" class="btn btn-default"
									onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(nwbzlqk.Event.ZLFE_SAVE_COMMENT)">
									提交</div>
							</c:if>
							<c:if test="${pageType == 1}">
								<%-- 1 为审核页面 --%>
								<div id="approveComment"
									class="btn btn-default" style="display: none"
									onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(nwbzlqk.Event.ZLFE_APPROVE_COMMENT)">
									同意上报</div>
								<div id="approveComment1" 
									class="btn btn-default" style="display: none"
									onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(nwbzlqk.Event.ZLFE_APPROVE_COMMENT1)">
									审核</div>
								<div id="approveComment2"
									class="btn btn-default" style="display: none"
									onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(nwbzlqk.Event.ZLFE_APPROVE_COMMENT2)">
									同意上报</div>
								<div id="approveComment3"
									class="btn btn-default" style="display: none"
									onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(nwbzlqk.Event.ZLFE_APPROVE_COMMENT3)">
									同意上报</div>
							</c:if>
							</div>
							
						</div>
						<form id="exportExcel" style="display: none" method="post"></form>
					</div>
				</div>
				<!-- /Page Header -->
				<!-- Page Body -->
				<div class="page-body">
					<div class="row">
						<div class="col-lg-12 col-sm-12 col-xs-12">
							<%@include file="byqnwbzlztqk/byqnwbzlztqk.jsp"%>
							<%@include file="xlnwbzlztqk/xlnwbzlztqk.jsp"%>
							<%@include file="pdnwbzlztqk/pdnwbzlztqk.jsp"%>
							<%@include file="byqsjzlqk/byqsjzlqk.jsp"%>
							<%@include file="xlgyzlqk/xlgyzlqk.jsp"%>
							<%@include file="pdsjzlqk/pdsjzlqk.jsp"%>
							<%@include file="byqyclzlwt/byqyclzlwt.jsp"%>
							<%@include file="xlyclzlwt/xlyclzlwt.jsp"%>
							<%@include file="byqsczzzlwt/byqsczzzlwt.jsp"%>
							<%@include file="xlsczzzlwt/xlsczzzlwt.jsp"%>
							<%@include file="pdsczzzlwt/pdsczzzlwt.jsp"%>
							<%@include file="byqsczzzlwtxxxx/byqsczzzlwtxxxx.jsp"%>
							<%@include file="xlsczzzlwtxxxx/xlsczzzlwtxxxx.jsp"%>
							<%@include file="byqysazzlwt/byqysazzlwt.jsp"%>
							<%@include file="xlysazzlwt/xlysazzlwt.jsp"%>
							<%@include file="pdysazzlwt/pdysazzlwt.jsp"%>
							<%@include file="byqnbzlwtfl/byqnbzlwtfl.jsp"%>
							<%@include file="xlnbzlwtfl/xlnbzlwtfl.jsp"%>
							<%@include file="pdnbzlwtfl/pdnbzlwtfl.jsp"%>
							<%@include file="byqwbzlwtfl/byqwbzlwtfl.jsp"%>
							<%@include file="xlwbzlwtfl/xlwbzlwtfl.jsp"%>
							<%@include file="pdwbzlwtfl/pdwbzlwtfl.jsp"%>
							<%@include file="byqnbzlwttjqk/byqnbzlwttjqk.jsp"%>
							<%@include file="xlnbzlwttjqk/xlnbzlwttjqk.jsp"%>
							<%@include file="pdnbzlwttjqk/pdnbzlwttjqk.jsp"%>
							<%@include file="byqwbzlwttjqk/byqwbzlwttjqk.jsp"%>
							<%@include file="xlwbzlwttjqk/xlwbzlwttjqk.jsp"%>
							<%@include file="pdwbzlwttjqk/pdwbzlwttjqk.jsp"%>
							<%@include file="xknwbzlztqk/xknwbzlztqk.jsp"%>
							<%@include file="xksjzlqk/xksjzlqk.jsp"%>
							<%@include file="xkyclzlwt/xkyclzlwt.jsp"%>
							<%@include file="xksczzzlwt/xksczzzlwt.jsp"%>
							<%@include file="xksczzzlwtxxxx/xksczzzlwtxxxx.jsp"%>
							<%@include file="xkysazzlwt/xkysazzlwt.jsp"%>
							<%@include file="xknbzlwtfl/xknbzlwtfl.jsp"%>
							<%@include file="xkwbzlwtfl/xkwbzlwtfl.jsp"%>
							<%@include file="xknbzlwttjqk/xknbzlwttjqk.jsp"%>
							<%@include file="xkwbzlwttjqk/xkwbzlwttjqk.jsp"%>
						</div>
						</div>
						<div class="row">
						<div class="col-lg-12 col-sm-12 col-xs-12">
						<div class="well comment-area" style="display:none;">
					
							<div style="font-size: 18px; font-weight: bold">问题分析</div>
							<textarea id="commentText" rows="5" style="width: 100%;"></textarea>
						</div>
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
		window.pageType = ${pageType};
		$(document).ready(function() {
			framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_INIT_EVENT,{
				type: "item-sel",
				comp:"comp-sel",
				comps : JSON.parse('${nodeData}'),
				dt: "grid-date",
				contentType: "season-sel",
				date: Util.parseDate('${year}', '${month}'),
			});
		});
	</script>

</body>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/www2/js/echarts-plain-2-0-0.js"></script>
<!--  /Body -->
</html>
