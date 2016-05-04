<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<!-- message box -->
<link href="${pageContext.request.contextPath}/jsp/message-box/css/style.css" rel="stylesheet" type="text/css">

<!-- jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.min.js"></script>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/multi-select/assets/prettify.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/multi-select/jquery.multiselect.js"></script>


<!-- jqgrid -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/jqgrid/themes/ui.jqgrid.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/jqgrid/themes/ui.multiselect.css">
<script src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.tablednd.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.contextmenu.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.layout.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/jqgrid/js/jquery.jqGrid.js" type="text/javascript"></script>

<!-- jqgrid assist -->
<script src="${pageContext.request.contextPath}/jsp/jqgrid/vector.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/jqgrid/jqassist.js" type="text/javascript"></script>


<script src="${pageContext.request.contextPath}/jsp/json2.js" type="text/javascript"></script>

<!-- message box -->
<script src="${pageContext.request.contextPath}/jsp/message-box/js/Sweefty.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/message-box/js/moaModal.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/messageBox.js" type="text/javascript"></script>

<%@include file="../framework/basic/basicEntry.jsp"%>
<script src="${pageContext.request.contextPath}/jsp/template/templatedef.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/template/templateEntry.js" type="text/javascript"></script>
<title>完工产品情况</title>

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
	padding: 5px,10px;
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
</style>
</head> 
<body>
	<div class="header">
		<h1 id="headertitle">完工产品情况</h1>
	</div>

	<Table id="frameTable" align="center" style="width:1200px">
		<tr>
			<td>
				<div id="dt" style="float: left"></div>
				<div id="compid" style="float: left"></div>
				<div id="type" style="float: left"></div>
				<input type="button" value="更新" style="float: left; width: 80px; margin-left: 10px;"
				onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_UPDATE)" />
			</td>
		</tr> 
		<tr>
			<td id="plugin">
			</td>
		</tr> 
		<tr>
			<td>
				<input id="gbsv" type="button" value="保存" style="float: right; width: 80px; margin-left: 10px;"
					   onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_SAVE)" />
				<input id="gbsm" type="button" value="提交" style="float: right; width: 80px; margin-left: 10px;"
					   onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_SUBMIT)" />
			</td>
		</tr>
	</Table>
	<script type="text/javascript">
	    $(document).ready(function () {
			framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_INIT_EVENT,{
				type: "type",
				dt: "dt",
				comp:"compid",
				comps : JSON.parse('${nodeData}'),
				date: {
					month: "${month}".length == 0 ? undefined : parseInt("${month}"),
					year: ${year}
				}
			});
        $(document.body).css("visibility", "visible");
    });
</script>

	<script src="${pageContext.request.contextPath}/jsp/style_button.js"></script>
	<script src="${pageContext.request.contextPath}/jsp/www2/js/echarts-plain-2-0-0.js"></script>
	<%@include file="../components/loading.jsp"%>
</body> 


</html>