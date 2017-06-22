<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="UTF-8">

<!-- jquery -->
<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jquery-1.12.3.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jqueryex.js"
	type="text/javascript"></script>


<!-- bootstrap -->
<link
	href="${pageContext.request.contextPath}/jsp/ui2/bootstrap-3.3.0-dist/dist/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/jsp/ui2/bootstrap-3.3.0-dist/dist/css/bootstrap-theme.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/bootstrap-3.3.0-dist/dist/js/bootstrap.js"></script>
	

<!-- font awesome -->
<link href="${pageContext.request.contextPath}/jsp/ui2/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">


<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/skins.min.js"></script>

<!--Beyond styles-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/beyond/beyond.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/beyond/animate.min.css" />
	<script
	src="${pageContext.request.contextPath}/jsp/ui2/beyond/beyond.js"></script>

<%@include file="loading.jsp"%>

<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/slimscroll/jquery.slimscroll.min.js"></script>

    <!--Page Related Scripts-->
    <script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/bootbox/bootbox.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/example.js"
	type="text/javascript"></script>



<title></title>

</head>

<body>
	<div class="row">
		<div class="col-md-12">
			<div class="well attached top">
				<div class="row">
					<div class="col-md-2">
						<input id="date1" type="text" class="workinput wicon"
							placeholder="请选择日期" readonly>
					</div>
					<div class="col-md-2">
						<select onchange="instance.onTypeSelected(this.value)">
							<option value="0" selected="selected">全部</option>
							<option value="1">收入签约分结构</option>
						</select>
					</div>
					<div class="col-md-2">
						<button id="update" type="button"
							onclick="instance.updateUI()" class="btn btn-primary">查找</button>
					</div>
				</div>
			</div>
			<div class="well attached">
				<div id="table"></div>
			</div>
			<div class="well attached bottom">
				<div class="row">
					<div class="col-md-2">
						<form id="exportJydw" style="float: left" method="post">
							<input class="exportButton" type="button" value="导出"
								style="width: 100px;"
								onclick="instance.exportExcelJydw($('h1').text())"
								class="ui-button ui-widget ui-state-default ui-corner-all"
								role="button" aria-disabled="false"></input>
						</form>
					</div>
					<div class="col-md-2">
						<form style="float: left; margin-left: 5px;" id="exportxmgs"
							method="post" style="dispaly:none">
							<input class="exportButton" type="button" value="导出项目公司"
								style="width: 120px;"
								onclick="instance.exportExcelXmgs($('h1').text())"
								class="ui-button ui-widget ui-state-default ui-corner-all"
								role="button" aria-disabled="false"></input>
						</form>
					</div>

				</div>

			</div>
		</div>
	</div>

</body>

</html>