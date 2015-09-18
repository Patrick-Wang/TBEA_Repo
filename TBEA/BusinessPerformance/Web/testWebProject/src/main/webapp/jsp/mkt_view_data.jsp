<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<!-- message box -->
<link href="../jsp/message-box/css/style.css" rel="stylesheet" type="text/css">

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
<!-- message box -->
<script src="../jsp/message-box/js/Sweefty.js" type="text/javascript"></script>
<script src="../jsp/message-box/js/moaModal.js" type="text/javascript"></script>
<script src="../jsp/messageBox.js" type="text/javascript"></script>
<script src="../jsp/mkt_view_data.js" type="text/javascript"></script>

<script type="text/javascript">
	var instance = mkt_view_data.View.newInstance();
	(function() {
		$(document).ready(
				function() {
					//instance.init("table1", ${companyName});
					instance.init("table1", "${companyName}");
				});
	})();
</script>
<meta charset="UTF-8">

<title>市场部数据信息汇总</title>

<style type="text/css">
body {
	background-color: rgb(247, 247, 247);
}

.panel-content-border {
	height: 330px;
	width: 620px;
	border: 2px solid #e3e3e3;
	margin: 0;
	padding: 0;
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

#update{
	height: 23px;
	padding: .1em 1em;
	margin-top: -1px;
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
		<h1>${companyName}市场部数据信息汇总</h1>
	</div>


	<table align="center">
		<tr>
			<td><Table>
					<tr>
<!-- 						<td> -->
							
<!-- 							<select id="comp_category" onchange="instance.onCompanySelected()" style="width: 135px"> -->
<!-- 								<option value="全部公司"selected="selected">全部单位</option> -->
<!-- 								<option value="沈变" >沈变公司</option> -->
<!-- 								<option value="衡变">衡变公司</option> -->
<!-- 								<option value="新变">新变厂</option> -->
<!-- 								<option value="天变">天变公司</option> -->
<!-- 								<option value="鲁缆">鲁缆公司</option> -->
<!-- 								<option value="新缆">新缆厂</option> -->
<!-- 								<option value="德缆">德缆公司</option> -->
<!-- 							</select> -->
<!-- 						</td> -->
<!-- 						<td width="10px"></td> -->
						<td>
							<select id="rpttype" style="width: 115px" onchange="instance.onDoc_TypeSelected()">								
								<option value="2">
									项目信息
								</option>
								<option value="3">
									投标台账
								</option>
								<option value="4">
									签约台账
								</option>
							</select>
						</td>
						<td><input id="update" type="button" value="更新"
							style="width: 80px; margin-left: 10px;"
							onclick="instance.updateUI()"></input>
					</tr>
				</Table></td>
		</tr>
		<tr>
			<td><div id="table1" style="margin-bottom: 15px"></div></td>
		</tr>
		
		<tr>
			<td>
				<Table id="assist" style="display:none">
					<tr>
 					  <c:if test="${(companyName != '输变电产业集团')}"> 
						<td>
							<input class="exportButton" type="button" value="提交"
								style="width: 100px;"
								onclick="instance.saveData()"
								class="ui-button ui-widget ui-state-default ui-corner-all"
								role="button" aria-disabled="false" ></input>
						</td>
				</c:if>
						<td>
							<form id="exportxmgs" method="post" style="overflow:visible">
								<input class="exportButton" type="button" value="导出数据"
									style="width: 120px;"
									onclick="instance.exportExcel()"
									class="ui-button ui-widget ui-state-default ui-corner-all"
									role="button" aria-disabled="false"></input>
							</form>
						</td>
					</tr>
				</Table>
			</td>


		</tr>

	</table>
	
	<%@include file="loading.jsp"%>
</body>
<script src="../jsp/www2/js/echarts-plain-2-0-0.js"></script>
<script src="../jsp/style_button.js"></script>
<script src="../jsp/style_select.js"></script>
</html>