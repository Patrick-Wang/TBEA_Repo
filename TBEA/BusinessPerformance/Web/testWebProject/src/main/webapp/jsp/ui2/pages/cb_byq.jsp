<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
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
<script src="../jsp/json2.js" type="text/javascript"></script>
<script src="../jsp/util.js" type="text/javascript"></script>
<script src="../jsp/cb_byq.js" type="text/javascript"></script>

<script type="text/javascript">

		var instance = cb_byq.View.newInstance();
          (function () {
              $(document).ready(function () {
              	instance.init(
              			"table1", 
              			"table2", 
              			"table3",
              			JSON.parse('${tbmx}'),
              			JSON.parse('${jttb}'),
              			JSON.parse('${gstb}'),
              			${month});
            })})();
    </script>
<meta charset="UTF-8">

<title>变压器投标成本明细</title>

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
</style>
</head>
<body style:"width=1400px">
	<div class="header">
		<h1>变压器投标成本明细</h1>
	</div>
	<Table align="center">
		<tr>
			<td>
				<Table>
					<tr>
						<td><%@include file="company_selection.jsp"%></td>
						<td><input type="button" value="更新"
							style="width: 80px; margin-left: 10px;"
							onclick="instance.updateUI()"></input>
					</tr>
				</Table>
			</td>
		</tr>
		<tr>
			<td><div id="table1" style="margin-bottom: 15px"></div></td>
		</tr>
		<tr>
			<td><div id="table2" style="margin-bottom: 15px"></div></td>
		</tr>
		<tr>
			<td><div id="table3" style="margin-bottom: 15px"></div></td>
		</tr>
	</Table>
	<%@include file="loading.jsp"%>
</body>

</html>