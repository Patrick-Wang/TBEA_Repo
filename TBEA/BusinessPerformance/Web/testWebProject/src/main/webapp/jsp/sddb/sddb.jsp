﻿<%@ page language="java" contentType="text/html; charset=utf-8"
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

	<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/components/select2/css/select2.min.css">
	<script src="${pageContext.request.contextPath}/jsp/components/select2/js/select2.min.js" type="text/javascript"></script>
<!-- jqgrid --> 
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/jqgrid/themes/ui.jqgrid.css">
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
<%@include file="../framework/basic/basicShow.jsp"%>
	<script src="${pageContext.request.contextPath}/jsp/message-box/js/Sweefty.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/jsp/message-box/js/moaModal.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/jsp/messageBox.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/jsp/util.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/jsp/unitedSelector.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/jsp/dateSelector.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/jsp/components/dateSeasonSelector.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/jsp/components/dateSelectorProxy.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/jsp/companySelector.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/jsp/framework/route/route.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/sddb/sddbdef.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/sddb/sddb.js" type="text/javascript"></script>
<title>${title}</title>

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
	.select2-selection__rendered{
	font-size: 12px;
	}

	.select2-container--default .select2-selection--single .select2-selection__rendered{
	height: 23px;
	line-height:23px;
	}

	.select2-container--default .select2-selection--single .select2-selection__arrow{
	height:23px;
	margin-top:-2px;
	}
	.select2-container .select2-selection--single{
	height:23px;
	margin-top:-1px;
	}
	.select2-container{
	margin-left:8px;
	}

	/*         .select2-selection__rendered{
	border: 1px solid #c5dbec;
	background: #dfeffc url(images/ui-bg_glass_85_dfeffc_1x400.png) 50% 50% repeat-x;
	font-weight: bold;
	color: #2e6e9e;
	border: solid 1px;
	} */




	.select2-container--open .select2-dropdown--below{
	margin-top:7px;
	}

	</style>
</head>
<body >
	<div class="header">
		<h1 id="headertitle">新能源周报</h1>
	</div>

	<Table id="frameTable" align="center" style="width:1200px">
		<tr>
			<td>
				<div id="dstartLabel" style="float: left;margin-right:5px;padding-top:3px">开始日期: </div>
				<div id="dstart" style="float: left;width: 100px;margin-right:10px"></div>
				<div id="dEndLabel" style="float: left;margin-right:5px;padding-top:3px">截至日期: </div>
				<div id="dEnd" style="float: left;width: 100px;margin-right:10px"></div>
				<div id="type" style="float: left"></div>
				<div id="item0" style="float: left"></div>
				<div id="itemLabel" style="float: left;margin-right:5px;padding-top:3px"></div>
				<div id="item" style="float: left"></div>
				<div id="compid" style="float: left"></div>

				<input id="doUpdate" type="button" value="更新" style="float: left; width: 80px; margin-left: 10px;"
				onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_UPDATE)" />
			</td>
		</tr>
		<tr>
			<td id="plugin">
				<%@include file="sddb/sddb.jsp"%>
			</td>
		</tr>
		<tr>
			<td>
				<form id="export" method="post">
					<input id="exportButton" type="button" value="导出"  style="float: left; width: 80px;"
						   onclick="framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_EXPORTEXCEL, 'export')">
				</form> 
			</td>
		</tr>

		<tr id="ctTitle">
		<td>
		<div id="chartName"  align="center" style="display: none;margin-top:23px;margin-left:10px;font-size:24px;font-weight:bold;"></div>
		</td>
		</tr>
		<tr id="ctarea">
		<td>
		<div id="chart" align="center" style="display: none;margin-top:10px"></div>
		</td>
		</tr>
	</Table>
	<script type="text/javascript">


    $(document).ready(function () {


		var itemLabel = '${itemLabel}';
		if (itemLabel == ''){
			$("#itemLabel").css("display", "none");
		}else{
			$("#itemLabel")[0].innerHTML = itemLabel;
		}

		var date = undefined;
		var dateStart = undefined;
		var dateEnd = undefined;
		if ('${date}' != ''){
			var dt = new Date(Date.parse('${date}'.replace(/-/g, '/')));
				date = {month: dt.getMonth() + 1,
				year: dt.getFullYear(),
				day: dt.getDate()
			}
		}

		if ('${dateStart}' != ''){
			var dt = new Date(Date.parse('${dateStart}'.replace(/-/g, '/')));
			dateStart = {month: dt.getMonth() + 1,
						year: dt.getFullYear(),
						day: dt.getDate()
				}
		}

		if ('${dateEnd}' != ''){
			var dt = new Date(Date.parse('${dateEnd}'.replace(/-/g, '/')));
			dateEnd = {month: dt.getMonth() + 1,
				year: dt.getFullYear(),
				day: dt.getDate()
			}
		}

		var items = '${itemNodes}'.replace(/\n/g, "");
		var items0 = '${itemNodes0}'.replace('/\n/g', "");
		var comps = '${nodeData}';



		if (items == '' && comps == '' && '${date}' == ''){
			$("#doUpdate").css("display", "none");
		}

		framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_INIT_EVENT,{
			type: "type",
			comp:"compid",
			comps : comps == '' ? comps : JSON.parse(comps),
			date: date,
			dateStart : dateStart,
			dateEnd:dateEnd,
			itemId0:"item0",
			itemNodes0:items0 == '' ? items0 : JSON.parse(items0),
			itemId:"item",
			itemNodes:items == '' ? items : JSON.parse(items)
		});
        $("#exportButton")
			.css("height", "23px")
			.css("padding", ".1em 1em")
			.css("margin-top", "2px");
        $(document.body).css("visibility", "visible");
    });
</script>

	<script src="${pageContext.request.contextPath}/jsp/style_button.js"></script>
	<script src="${pageContext.request.contextPath}/jsp/www2/js/echarts-plain-2-0-0.js"></script>
	<%@include file="../components/loading.jsp"%>
</body>


</html>