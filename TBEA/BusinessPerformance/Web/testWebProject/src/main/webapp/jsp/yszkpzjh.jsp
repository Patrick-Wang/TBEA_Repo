<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="ECharts">
<meta name="author" content="">
<title>${year}年${month}月应收账款盘子规划</title>


<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jqgrid/themes/ui.jqgrid.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jqgrid/themes/ui.multiselect.css">
<script src="../jsp/jqgrid/js/jquery.js" type="text/javascript"></script>

<script src="../jsp/jqgrid/js/jquery.layout.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/i18n/grid.locale-en.js"
	type="text/javascript"></script>


<script src="../jsp/jqgrid/js/jquery.jqGrid.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.tablednd.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/js/jquery.contextmenu.js"
	type="text/javascript"></script>
<script src="../jsp/jqgrid/vector.js" type="text/javascript"></script>
<script src="../jsp/jqgrid/jqassist.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" media="screen"
	href="../jsp/jqgrid/themes/redmond/jquery-ui-custom.css">
<script src="../jsp/jqgrid/js/jquery-ui-custom.min.js"
	type="text/javascript"></script>
<script src="../jsp/jqgrid/js/ui.multiselect.js" type="text/javascript"></script>
<script src="../jsp/util.js" type="text/javascript"></script>
<script src="../jsp/yszkpzjh.js" type="text/javascript"></script>
<script type="text/javascript">
	var instance = yszkpzjh.View.newInstance();
	 (function () {
         $(document).ready(function () {
         	instance.init("chart", "list", ${month}, ${year});
         });
     })();
    </script>

<style>
body {
	background-color: rgb(247, 247, 247);
}

.panel-content-border {
	height: 450px;
	width: 45%;
	border: 2px solid #e3e3e3;
	margin: 0;
	padding: 0;
	align: left;
	valign: left;
	text-align: left;
	float: left;
}

.panel-content {
	height: 100%;
	width: 100%;
	margin: 0;
	padding: 0;
}

.right {
	float: left;
	padding-top: 20px;
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
	color: #fff
}

.header {
	width: 100%;
	height: 60px;
}

.header h1 {
	text-align: center;
}

.companyname h1 {
	margin-top: 0px;
	margin-bottom: 0px;
	width: 30px;
	font-size: 30px;
	word-wrap: break-word;
	letter-spacing: 20px;
	color: #5cb85c;
	float: left;
}

.lxian {
	margin-left: 30px;
	margin-right: 15px;
	width: 1px;
	height: 175px;
	background: #5cb85c;
	float: left;
}

.hrclass {
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

.ui-corner-all, .ui-corner-bottom, .ui-corner-right, .ui-corner-br {
	border-bottom-right-radius: 0px;
}

.ui-corner-all, .ui-corner-bottom, .ui-corner-left, .ui-corner-bl {
	border-bottom-left-radius: 0px;
}

.ui-corner-all, .ui-corner-top, .ui-corner-right, .ui-corner-tr {
	border-top-right-radius: 0;
}

.ui-corner-all, .ui-corner-top, .ui-corner-left, .ui-corner-tl {
	border-top-left-radius: 0px;
}
</style>

</head>

<body style="width: 1400px">
	<div class="header" id="title">
		<h1>${year}年${month}月应收账款盘子规划</h1>
	</div>
	<Table>
		<tr>
			<td><%@include file="date_selection.jsp"%>
			</td>
			<td><%@include file="company_selection.jsp"%>
			</td>
			<td><input type="button" value="更新"
				style="width: 80px; margin-left: 10px;"
				onclick="instance.updateUI()"></input></td>
		</tr>
	</Table>

	<Table id="dataarea">
		<c:forEach begin="0" end="${fn:length(topComp[0]) - 1}" var="i">
			<tr id="${topComp[1][i]}block">
				<td>
					<div style="display: block; height: 10px"></div>
				</td>
			</tr>
			<tr id="${topComp[1][i]}" style="padding-top: 10px">
				<td valign="middle" align="center">
					<div class="companyname">
						<h1>${topComp[0][i]}</h1>

					</div>
				</td>
				<td valign="middle">
					<div class="lxian"></div>
				</td>
				<td valign="middle">
					<div id="list${topComp[1][i]}1" }" style="float: left;"></div>
					<div style="float: left;">
						<div style="float: left;">
							<div id="list${topComp[1][i]}3"></div>
						</div>
						<div style="float: left;">

							<div>
								<div id="list${topComp[1][i]}2"></div>
							</div>
							<div>
								<div id="list${topComp[1][i]}4"></div>
							</div>
						</div>
					</div>
				<td>
			</tr>

			<c:forEach begin="0" end="${fn:length(subComp[i][0]) - 1}" var="j">
				<tr id="${subComp[i][1][j]}block">
					<td>
						<div style="height: 10px"></div>
					</td>
				</tr>
				<tr id="${subComp[i][1][j]}" style="padding-top: 10px">
					<td valign="middle" align="center">
						<div class="companyname">
							<h1>${subComp[i][0][j]}</h1>
						</div>
					</td>
					<td valign="middle">
						<div class="lxian"></div>
					</td>
					<td valign="middle">
						<div id="list${subComp[i][1][j]}1" style="float: left;"></div>
						<div style="float: left;">
							<div style="float: left;">
								<div id="list${subComp[i][1][j]}3"></div>
							</div>
							<div style="float: left;">

								<div>
									<div id="list${subComp[i][1][j]}2"></div>
								</div>
								<div>
									<div id="list${subComp[i][1][j]}4"></div>
								</div>
							</div>
						</div>
					<td>
				</tr>
			</c:forEach>
		</c:forEach>

	</Table>
	<%@include file="loading.jsp"%>
</body>
<script src="../jsp/www2/js/echarts-plain-2-0-0.js"></script>
</html>