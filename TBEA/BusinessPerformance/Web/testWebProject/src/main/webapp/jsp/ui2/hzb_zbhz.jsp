<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<!-- jquery -->
<script type="text/javascript" src="../jsp/jqgrid/js/jquery.js"></script>

<!-- jquery ui -->
<!-- jquery ui gray -->
<link rel="stylesheet" type="text/css"
	href="../jsp/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.css" />


<script type="text/javascript"
	src="../jsp/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.js"></script>
<!-- jquery ui blue -->
<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jqgrid/themes/redmond/jquery-ui-custom.css">
<script src="../jsp/My97DatePicker/WdatePicker.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="../jsp/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.js"></script>
<!-- 多选菜单 -->
<link rel="stylesheet" type="text/css"
	href="../jsp/multi-select/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css"
	href="../jsp/multi-select/assets/style.css" />
<link rel="stylesheet" type="text/css"
	href="../jsp/multi-select/assets/prettify.css" />
<script type="text/javascript"
	src="../jsp/multi-select/assets/prettify.js"></script>
<script type="text/javascript"
	src="../jsp/multi-select/jquery.multiselect.js"></script>
	<script type="text/javascript"
	src="../jsp/ui2/jquery/jqueryex.js"></script>

<!-- jqgrid -->
<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jqgrid/themes/ui.jqgrid.css">
		<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jedate/skin/jedate.css">
	<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jedate/skin/deepgreen.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jqgrid/themes/ui.multiselect.css">
<script src="../jsp/jqgrid/js/jquery.tablednd.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.contextmenu.js"
	type="text/javascript"></script>
<script src="../jsp/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.layout.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.jqGrid.js" type="text/javascript"></script>
	<script src="../jsp/jedate/jquery.jedate.js" type="text/javascript"></script>
<!-- jqgrid assist -->
<script src="../jsp/jqgrid/jqassist.js" type="text/javascript"></script>

<script src="../jsp/json2.js" type="text/javascript"></script>
<script src="../jsp/util.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/vector.js" type="text/javascript"></script>
<script src="../jsp/dateSelector.js" type="text/javascript"></script>
<script src="../jsp/unitedSelector.js" type="text/javascript"></script>
<script src="../jsp/companySelector.js" type="text/javascript"></script>
<script src="../jsp/messageBox.js" type="text/javascript"></script>
<script src="../jsp/hzb_zbhz.js" type="text/javascript"></script>

<script type="text/javascript">
		Util.loadCssFile("${pageContext.request.contextPath}/jsp/ui2/ui2.css");
    	var instance = hzb_zbhz.View.newInstance();
        (function () {
            $(document).ready(function () {
            	instance.init("table", "date", ${month}, ${year});
            });
        })();
    </script>
<meta charset="UTF-8">

<title>${year}年${month}月公司整体指标完成情况</title>

<style type="text/css">
body {
	background-color: rgb(247, 247, 247);
	margin:0px;
	font-family: Microsoft YaHei UI;
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
	text-align: left;
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

.exportButton {
	height: 23px;
	width: 140px;
	padding: .1em 1em;
	margin-top: -1px;
}

.ui-widget {
	font-family: Microsoft YaHei UI;
	font-size: 1.1em;
}

.ui-widget input, .ui-widget select, .ui-widget textarea, .ui-widget button
	{
	font-family: Microsoft YaHei UI;
	font-size: 1em;
}
/* Corner radius */
.ui-corner-all, .ui-corner-top, .ui-corner-left, .ui-corner-tl {
	-moz-border-radius-topleft: 0px;
	-webkit-border-top-left-radius: 0px;
	-khtml-border-top-left-radius: 0px;
	border-top-left-radius: 0px;
}

.ui-corner-all, .ui-corner-top, .ui-corner-right, .ui-corner-tr {
	-moz-border-radius-topright: 0px;
	-webkit-border-top-right-radius: 0px;
	-khtml-border-top-right-radius: 0px;
	border-top-right-radius: 0px;
}

.ui-corner-all, .ui-corner-bottom, .ui-corner-left, .ui-corner-bl {
	-moz-border-radius-bottomleft: 0px;
	-webkit-border-bottom-left-radius: 0px;
	-khtml-border-bottom-left-radius: 0px;
	border-bottom-left-radius: 0px;
}

.ui-corner-all, .ui-corner-bottom, .ui-corner-right, .ui-corner-br {
	-moz-border-radius-bottomright: 0px;
	-webkit-border-bottom-right-radius: 0px;
	-khtml-border-bottom-right-radius: 0px;
	border-bottom-right-radius: 0px;
}

.ui-jqgrid tr.jqgrow td {
	font-weight: normal;
	overflow: hidden;
	white-space: pre;
	height: 28px;
	padding: 0 2px 0 2px;
	border-bottom-width: 1px;
	border-bottom-color: inherit;
	border-bottom-style: solid;
}




/*  .ui-state-highlight, .ui-widget-content .ui-state-highlight, .ui-widget-header .ui-state-highlight  {border: none; background: none; color: none; } */
/* .ui-state-highlight a, .ui-widget-content .ui-state-highlight a,.ui-widget-header .ui-state-highlight a { color: none; } */

/*  .ui-state-hover, .ui-widget-content .ui-state-hover, .ui-widget-header .ui-state-hover, .ui-state-focus, .ui-widget-content .ui-state-focus, .ui-widget-header .ui-state-focus { border: 1px; background: none; font-weight: normal; color: none; } */
/*  .ui-state-hover a, .ui-state-hover a:hover { color: none; text-decoration: none; } */
.ui-jqgrid tr.jqgrow td:last-child {
	border-right-width: 0px;
}

.ui-th-ltr, .ui-jqgrid .ui-jqgrid-htable th.ui-th-ltr:last-child {
	border-right-width: 0px;
}

.ui-jqgrid tr.jqgrow:last-child td {
	border-bottom-width: 0px;
} 

.ui-state-default, .ui-widget-content .ui-state-default,
	.ui-widget-header .ui-state-default {
	border: 1px solid #c5dbec;
	font-weight: normal;
	background: rgb(79, 173, 194);
	color: white;
}

.ui-state-hover, .ui-widget-content .ui-state-hover, .ui-widget-header .ui-state-hover,
	.ui-state-focus, .ui-widget-content .ui-state-focus, .ui-widget-header .ui-state-focus
	{
	border: 1px solid #a6c9e2;
	background: none;
	font-weight: normal;
	color: black;
}

.ui-jqgrid-htable .ui-state-hover {
    border: 1px solid #c5dbec;
    font-weight: normal;
    color: white;
}

.wicon{
	background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABUAAAAQCAYAAAD52jQlAAAAR0lEQVR42mPYeOnNf2pjhlFD/1MToBhKDRdiNRTdRnL4ZBmauuwWBqaKociAoKHEYGyGUhymJLt0wMKU5Ninejqleo6iNgYATw27GzJD1pEAAAAASUVORK5CYII=");
	background-repeat:no-repeat;
	background-size: 26px 20px;
    background-position: right center;
}

.workinput {
    width: 140px;
    height: 20px;
    line-height: 20px;
    border: 1px #A5D2EC solid;
    padding: 0px 0px 0px 5px;
    background-color: #fff;
    float: left;
    font-size: 14px;
}


</style>

</head>
<body>
	<div class=" header">
		<h1>${year}年${month}月公司整体指标完成情况</h1>
	</div>

	<Table>
		<tr>
			<td>
				<Table>
					<tr>
						<td><input id="date1" type="text" class="workinput wicon" placeholder="请选择日期"  readonly></td>
					</tr>
					<tr>
						<td><div id="date"></div></td>
						<td><select onchange="instance.onTypeSelected(this.value)"
							style="width: 125px;">
								<option value="0" selected="selected">全部</option>
								<option value="1">收入签约分结构</option>
						</select></td>
						<td>
						<i class="fa fa-refresh" aria-hidden="true" id="update" onclick="instance.updateUI()"></i>
						
						<!-- <input id="update" type="button" 
							style="width: 80px; margin-left: 10px;"
							onclick="instance.updateUI()"
							class="ui-button ui-widget ui-state-default ui-corner-all"
							role="button" aria-disabled="false"></input> --></td>
					</tr>
				</Table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="table"></div>
			</td>
		</tr>
		<tr>
			<td>
				<div style="height: 6px"></div>
			</td>
		</tr>
		<tr>
			<td>
				<Table>
					<tr>
						<td>
							<form id="exportJydw" method="post">
								<input class="exportButton" type="button" value="导出"
									style="width: 100px;"
									onclick="instance.exportExcelJydw($('h1').text())"
									class="ui-button ui-widget ui-state-default ui-corner-all"
									role="button" aria-disabled="false"></input>
							</form>
						</td>
						<td>
							<form id="exportxmgs" method="post" style="dispaly:none">
								<input class="exportButton" type="button" value="导出项目公司"
									style="width: 120px;"
									onclick="instance.exportExcelXmgs($('h1').text())"
									class="ui-button ui-widget ui-state-default ui-corner-all"
									role="button" aria-disabled="false"></input>
							</form>
						</td>
					</tr>
				</Table>
			</td>


		</tr>

	</Table>
	<%@include file="loading.jsp"%>
	<script src="../jsp/www2/js/echarts-plain-2-0-0.js"></script>
	<script src="../jsp/style_button.js"></script>
	<script src="../jsp/style_select.js"></script>
</body>

</html>