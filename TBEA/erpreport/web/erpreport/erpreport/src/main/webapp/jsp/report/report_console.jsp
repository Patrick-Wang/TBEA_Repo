<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 

<!-- jquery -->
<script  src="${pageContext.request.contextPath}/jsp/plugins/jquery/jquery-1.12.3.js"></script>


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/report/ztree/css/zTreeStyle/zTreeStyle.css" />

<script 
	src="${pageContext.request.contextPath}/jsp/report/ztree/jquery.ztree.all.min.js"></script>


<script src="${pageContext.request.contextPath}/jsp/report/report_console.js" ></script>


	<script src="${pageContext.request.contextPath}/jsp/report/zTreeEx.js" ></script>


<script>
	$(document).ready(function(){
        report.console.onInit(JSON.parse('${componentTree}'.replace(/\\/g,"\\\\")));
	});
</script>
<style>

	.header {
		width: 100%;
		height: 60px;
	}

	.header h1 {
		text-align: center;
	}
	div, ul , p, table, th, td {
		list-style:none;
		margin:0; padding:0;
		color:#333; font-size:13px;
		font-family:"Segoe UI";
	}
	div#rMenu {
		background-color:#555555;
		text-align: left;
		padding:2px;
		width:100px;
		position:absolute;
		display:none;
	}
	div#rMenu ul {
		margin:1px 0;
		padding:0 5px;
		cursor: pointer;
		list-style: none outside none;
		background-color:#DFDFDF;
		display:none;
	}
	div#rMenu ul li {
		margin:0;
		padding:2px 0;
	}

</style>
 
<title></title>

</head>
<body>
	<div class="header">
		<h1>报表组件控制台</h1>
	</div>
	<div style="float:left;border:2px">
		<ul id="navigator" class="ztree"></ul>
	</div>
	<div style="float:left;border:2px;margin-left:5px">
	</div>
</body>

</html>