<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

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
<script src="../jsp/jqgrid/js/jquery-ui-custom.min.js"
	type="text/javascript"></script>

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


<!-- jqgrid -->
<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jqgrid/themes/ui.jqgrid.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jqgrid/themes/ui.multiselect.css">
<script src="../jsp/jqgrid/js/jquery.tablednd.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.contextmenu.js"
	type="text/javascript"></script>
<script src="../jsp/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.layout.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.jqGrid.js" type="text/javascript"></script>

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

.exportButton {
	height: 23px;
	width:140px;
	padding: .1em 1em;
	margin-top: -1px;
}

</style>

</head>
<body>
	<div class=" header">
		<h1>${year}年${month}月公司整体指标完成情况</h1>
	</div>

	<Table align="center">
		<tr>
			<td>
				<Table>
					<tr>
						<td><div id="date"></div>
						</td>
						<td><select onchange="instance.onTypeSelected(this.value)"
							style="width: 125px;">
								<option value="0" selected="selected">全部</option>
								<option value="1">收入签约分结构</option>
						</select></td>
						<td><input id="update" type="button" value="更新"
							style="width: 80px; margin-left: 10px;"
							onclick="instance.updateUI()"
							class="ui-button ui-widget ui-state-default ui-corner-all"
							role="button" aria-disabled="false"></input>
						</td>
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
				<div style="height:6px"></div>
			</td>
		</tr>
		<tr>	
		<td>
			<Table>
					<tr>
			<td>
				<form id="export" method="post">
					<input class="exportButton" type="button" value="导出"
						style="width: 100px;"
						onclick="instance.exportExcel($('h1').text())"
						class="ui-button ui-widget ui-state-default ui-corner-all"
						role="button" aria-disabled="false"></input>
				</form>
			</td>
			<td>
				<form id="exportxmgs" method="post">
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
			
		</tr>
	</Table>
	<%@include file="loading.jsp"%>
	<script src="../jsp/www2/js/echarts-plain-2-0-0.js"></script>
	<script src="../jsp/style_button.js"></script>
	<script src="../jsp/style_select.js"></script>
</body>

</html>