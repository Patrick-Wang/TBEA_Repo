﻿<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- message box -->
<link
	href="${pageContext.request.contextPath}/jsp/message-box/css/style.css"
	rel="stylesheet" type="text/css">

<!-- jquery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.min.js"></script>
<!-- jquery ui blue -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/jqgrid/themes/redmond/jquery-ui-custom.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.js"></script>
<!-- 多选菜单 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/multi-select/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/multi-select/assets/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/multi-select/assets/prettify.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/multi-select/assets/prettify.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/multi-select/jquery.multiselect.js"></script>


<!-- jqgrid -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/jqgrid/themes/ui.jqgrid.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/jqgrid/themes/ui.multiselect.css">
<script
	src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.tablednd.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.contextmenu.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.layout.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.jqGrid.js"
	type="text/javascript"></script>

<!-- jqgrid assist -->
<script src="${pageContext.request.contextPath}/jsp/jqgrid/vector.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/jqgrid/jqassist.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/json2.js"
	type="text/javascript"></script>


<!-- message box -->
<script
	src="${pageContext.request.contextPath}/jsp/message-box/js/Sweefty.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/message-box/js/moaModal.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/messageBox.js"
	type="text/javascript"></script>
<%@include file="../framework/basic/basicShow.jsp"%>
<script
	src="${pageContext.request.contextPath}/jsp/components/dateSeasonSelector.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/components/SeasonAccSelector.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/components/dateSelectorProxy.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/cpzlqk/cpzlqkdef.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/cpzlqk/cpzlqk.js"
	type="text/javascript"></script>
<title>产品质量情况</title>

<style type="text/css">
body {
	background-color: rgb(247, 247, 247);
	visibility: hidden;
}

.panel-content-border {
	height: 350px;
	width: 1000px;
	border: 2px solid #e3e3e3;
	margin: 0;
	padding: 0;
	align: center;
	valign: center;
	text-align: center;
}

.panel-content {
	height: 100%;
	width: 100%;
	margin: 0;
	padding: 0;
}

.right {
	width: 45%;
	height: 180px;
	float: left;
	padding-top: 20px;
	margin-left: 225px;
}

.contract {
	text-align: center;
}

.contract h1 {
	display: none;
	color: #003B8F;
}

.btn_loading, .btn_detail {
	width: 100px;
	height: 30px;
	padding: 5px, 10px;
	font-size: 12px;
	line-height: 1.5;
	boder-radius: 3px;
	background-color: #5cb85c;
	boder-color: #4cae4c;
	color: #fff;
}

.header {
	width: 100%;
	height: 60px;
}

.header h1 {
	text-align: center;
}

.companyname h1 {
	width: 30px;
	font-size: 30px;
	word-wrap: break-word;
	letter-spacing: 20px;
	color: #5cb85c;
	float: left;
}

.lxian {
	margin-left: 30px;
	width: 1px;
	height: 175px;
	background: #5cb85c;
	float: left;
}

.hrclass hr {
	width: 1100px;
	height: 1px;
	margin-top: 10px;
	margin-left: 90px;
	border: 0;
	background-color: #5cb85c;
}

th.ui-th-column div {
	/* jqGrid columns name wrap  */
	white-space: normal !important;
	height: auto !important;
	padding: 0px;
}

th.ui-th-ltr {
	/* jqGrid columns name wrap  */
	font-size: 14px;
}

.ui-multiselect {
	padding: 2px 0 2px 4px;
	text-align: left;
	font-size: 12px;
}

#exportButton {
	height: 23px;
	width: 100px;
	padding: .1em 1em;
	margin-top: 2px;
}
</style>
</head>
<body>
	<div class="header">
		<h1 id="headertitle">产品质量情况</h1>
	</div>

	<Table id="frameTable" align="center" style="width: 1200px">
		<tr>
			<td>
				<div id="zlAndyclhgl" style="float: left"></div>
				<div id="dt" style="float: left"></div>
				<div id="compid" style="float: left"></div>
				<div id="type" style="float: left"></div> <input type="button"
				value="更新" style="float: left; width: 80px; margin-left: 10px;"
				onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_UPDATE)" />

				<div id="radio" style="float: right">
					<input type="radio" id="rdyd" name="radio" checked="checked">
					<label for="rdyd">月度</label> <input type="radio" id="rdjd"
						name="radio"> <label for="rdjd">季度</label>
				</div>
			</td>
		</tr>
		<tr>
			<td id="plugin"><%@include file="byqacptjjg/byqacptjjg.jsp"%>
				<%@include file="pdacptjjg/pdacptjjg.jsp"%>
				<%@include file="xlacptjjg/xlacptjjg.jsp"%>
				<%@include file="byqadwtjjg/byqadwtjjg.jsp"%>
				<%@include file="pdadwtjjg/pdadwtjjg.jsp"%>
				<%@include file="xladydjtjjg/xladydjtjjg.jsp"%>
				<%@include file="byqcpycssbhgwtmx/byqcpycssbhgwtmx.jsp"%>
				<%@include file="pdcpycssbhgwtmx/pdcpycssbhgwtmx.jsp"%>
				<%@include file="byqcpycssbhgxxfb/byqcpycssbhgxxfb.jsp"%>
				<%@include file="pdcpycssbhgxxfb/pdcpycssbhgxxfb.jsp"%>
				<%@include file="xlbhgcpmx/xlbhgcpmx.jsp"%>
			</td>
		</tr>
		<tr>
			<td>
				<div id="comment" style="display: none">
					<div style="font-size: 18px; font-weight: bold">问题分析</div>
					<textarea id="commentText" cols="20" rows="5"
						style="width: 100%; resize: none; margin-bottom: 5px"></textarea>
					<c:if test="${pageType == 2}">
						<%-- 2 录入页面--%>
						<input type="button" id="saveComment" value="提交"
							style="float: right; width: 90px"
							onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(cpzlqk.Event.ZLFE_SAVE_COMMENT)">
					</c:if>
					<c:if test="${pageType == 1}">
						<%-- 1 为审核页面 --%>
						<input type="button" id="approveComment" value="同意上报"
							style="float: right; width: 90px"
							onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(cpzlqk.Event.ZLFE_APPROVE_COMMENT)">
						<input type="button" id="approveComment1" value="审核"
							style="float: right; width: 90px"
							onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(cpzlqk.Event.ZLFE_APPROVE_COMMENT1)">
						<input type="button" id="approveComment2" value="同意上报"
							style="float: right; width: 90px"
							onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(cpzlqk.Event.ZLFE_APPROVE_COMMENT2)">
						<input type="button" id="approveComment3" value="同意上报"
							style="float: right; width: 90px"
							onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(cpzlqk.Event.ZLFE_APPROVE_COMMENT3)">
					</c:if>
				</div>
				<form id="export" method="post">
					<input id="exportButton" type="button" value="导出"
						style="display: none"
						onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_EXPORTEXCEL, 'export')">
				</form>
				<!-- <form id="exportReport" method="post">
					<input id="exportReportButton" type="button" value="质量报告"
						onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(cpzlqk.EVENT.ZLFE_ZLREPORT, 'exportReport')">
				</form> -->
			</td>
		</tr>
	</Table>
	<script type="text/javascript">

		var ts = '${tableStatus}';
		window.tableStatus = ts == ''?undefined : JSON.parse(ts);
		window.pageType = ${pageType};
		$("#radio").buttonset();
		//$("#zlAndyclhgl").buttonset();

    $(document).ready(function () {

		framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_INIT_EVENT,{
			type: "type",
			comp:"compid",
			comps : JSON.parse('${nodeData}'),
			dt: "dt",
			contentType: "radio",
			date: Util.parseDate('${year}', '${month}'),
			tableStatus:tableStatus
			//isSingleDate: '${isSingleDate}' == 'true',
		});
        $("#exportButton")
			.css("height", "23px")
			.css("padding", ".1em 1em")
			.css("margin-top", "2px");
		$("#saveComment")
				.css("height", "23px")
				.css("padding", ".1em 1em")
				.css("margin-top", "2px");
        $(document.body).css("visibility", "visible");
    });
</script>

	<script src="${pageContext.request.contextPath}/jsp/style_button.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/www2/js/echarts-plain-2-0-0.js"></script>
	<%@include file="../components/loading.jsp"%>
</body>


</html>