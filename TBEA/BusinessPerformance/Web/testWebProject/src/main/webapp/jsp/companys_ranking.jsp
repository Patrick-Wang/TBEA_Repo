<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<link rel="stylesheet" type="text/css" href="../jsp/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.css" />
	<script type="text/javascript" src="../jsp/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.js"></script>
	<!-- jquery ui blue -->
	<link rel="stylesheet" type="text/css" media="screen" href="../jsp/jqgrid/themes/redmond/jquery-ui-custom.css">
	<script src="../jsp/jqgrid/js/jquery-ui-custom.min.js" type="text/javascript"></script>
	
	<!-- 多选菜单 -->
	<link rel="stylesheet" type="text/css" href="../jsp/multi-select/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" href="../jsp/multi-select/assets/style.css" />
	<link rel="stylesheet" type="text/css" href="../jsp/multi-select/assets/prettify.css" />
	<script type="text/javascript" src="../jsp/multi-select/assets/prettify.js"></script>
	<script type="text/javascript" src="../jsp/multi-select/jquery.multiselect.js"></script>
	
	
	<!-- jqgrid -->
	<link rel="stylesheet" type="text/css" media="screen" href="../jsp/jqgrid/themes/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" media="screen" href="../jsp/jqgrid/themes/ui.multiselect.css">
	<script src="../jsp/jqgrid/js/jquery.tablednd.js" type="text/javascript"></script>
	<script src="../jsp/jqgrid/js/jquery.contextmenu.js" type="text/javascript"></script>
	<script src="../jsp/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script src="../jsp/jqgrid/js/jquery.layout.js" type="text/javascript"></script>
	<script src="../jsp/jqgrid/js/jquery.jqGrid.js" type="text/javascript"></script>
	
	<!-- jqgrid assist -->
	<script src="../jsp/jqgrid/jqassist.js" type="text/javascript"></script>

	<script src="../jsp/json2.js" type="text/javascript"></script>
	<script src="../jsp/util.js" type="text/javascript"></script>
	<script src="../jsp/jqgrid/vector.js" type="text/javascript"></script>
	<script src="../jsp/unitedSelector.js" type="text/javascript"></script>
	<script src="../jsp/dateSelector.js" type="text/javascript"></script>
    <script src="../jsp/companys_ranking.js" type="text/javascript"></script>

    <script type="text/javascript">
    	var instance = companys_ranking.View.newInstance();
    	(function () {
            $(document).ready(function () {
            	instance.init("table", "date", ${year}, ${month});
            });
        })();
    </script>


<meta charset="UTF-8">

    <title>${year}年${month}月经营单位指标排名情况</title>

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

#update {
	height: 23px;
	padding: .1em 1em;
	margin-top: -1px;
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
	margin-top: -1px;
}
</style>
</head>
<body>
    <div class=" header">
        <h1>${year}年${month}月经营单位指标排名情况</h1>
    </div>

	<Table align="center">
		<tr>
			<td>
				<Table>
					<tr>
						<td><div id="date"></div></td>
						<td><div style = "width:20px"></div>	</td>
						<td>
							<div id="t1">
							<input type="radio" name="rank" value="JY" id="JYcompanys" checked="checked"><label for="JYcompanys">经营单位排名</label>
							<input type="radio" name="rank" value="PRO" id="Procompanys" ><label for ="Procompanys">项目公司排名</label>
							</div>						
						</td>
						<td><div style = "width:10px"/></td>
						
						
						<td><%@include file="ranking_selection.jsp"%></div>
						</td> 
						<td><input id="update" type="button" value="更新" style="width : 80px; margin-left:10px;"
							onclick="instance.updateUI()"
							class="ui-button ui-widget ui-state-default ui-corner-all" 
						role="button" aria-disabled="false"></input>
				</Table>
			</td>
		</tr>
		<tr><td><div style="height:10px"></div></td></tr>
		<tr>
			<td>
				<div id="table"></div>
			<td>
		</tr>
		<tr>
			<td>
				<div style="height:6px"></div>
			<td>
		</tr>
		<tr>
			<td>
				<form id="export" method="post" style="display:none">
					<input id="exportButton" type="button" value="导出"
						onclick="instance.exportExcel()"
						class="ui-button ui-widget ui-state-default ui-corner-all" 
						role="button" aria-disabled="false"></input>
				</form>
			</td>
		</tr>
	</Table>
	<%@include file="loading.jsp"%>

</body>
<script src="../jsp/www2/js/echarts-plain-2-0-0.js"></script>
<script>
	$( "#t1" ).buttonset();
</script>

<script type="text/javascript">
	$(function(){
		showCont();
		$("input[name=rank]").click(function(){
		showCont();
		});
	});
	function showCont(){
		switch($("input[name=rank]:checked").attr("id")){
		case "JYcompanys":
			$("#ranktype").empty();
			var option = $("<option>").text("利润计划完成率排名").val(1);
			var option2 = $("<option>").text("利润指标年度累计完成同比增长排名").val(2);
			var option3 = $("<option>").text("人均利润实际完成排名").val(3);
			var option4 = $("<option>").text("人均收入实际完成排名").val(4);
			var option5 = $("<option>").text("应收账款占收入比排名").val(5);
			var option6 = $("<option>").text("应收账款加保理占收入排名").val(6);
			var option7 = $("<option>").text("存货占收入比排名").val(7);
			var option8 = $("<option>").text("应收加存货占收入比排名").val(8);
			var option9 = $("<option>").text("经营性净现金流实际完成排名").val(9);
			//var option10 = $("<option>").text("各单位净资产收益率完成排名").val(10);
			$("#ranktype").append(option).append(option2).append(option3).append(option4)
			.append(option5).append(option6).append(option7).append(option8).append(option9);
			$("#ranktype").multiselect({
	         	multiple: false,
	    	});

		 break;
		 case "Procompanys":
			$("#ranktype").empty();
			var option = $("<option>").text("利润指标年度累计完成同比增长排名").val(11);
			//var option2 = $("<option>").text("项目公司净资产收益率排名").val(12);
			var option3 = $("<option>").text("人均收入完成排名").val(13);
			var option4 = $("<option>").text("人均利润完成排名").val(14);
			$("#ranktype").append(option).append(option3).append(option4);
			$("#ranktype").multiselect({
	         	multiple: false,
	    	});
		 	
		 break;
		 default:
		 break;
		}
	}
</script>
<script src="../jsp/style_select.js"></script>

</html>