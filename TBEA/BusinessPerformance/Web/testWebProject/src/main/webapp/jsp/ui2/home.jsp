<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="Shortcut Icon"
	href="${pageContext.request.contextPath}/jsp/ui2/images/logo.ico"
	type="image/x-icon">
<%@include file="ie8-t.jsp"%>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jquery-1.12.3.js"></script>


<!--Basic Styles-->
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/bootstrap.min.css"
	rel="stylesheet" />

<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/font-awesome.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/weather-icons.min.css"
	rel="stylesheet" />

<!--Beyond styles-->
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/beyond.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/demo.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/typicons.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/animate.min.css"
	rel="stylesheet" />
<link id="skin-link" href="" rel="stylesheet" type="text/css" />

<!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
<script
	src="${pageContext.request.contextPath}/jsp/ui2/assets/js/skins.min.js"></script>


<!-- jquery ui -->
<!-- jquery ui gray -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.js"></script>
<!-- jquery ui blue -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/redmond/jquery-ui-custom.css">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery-ui-custom.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/components/select2/css/select2.min.css">
	<script src="${pageContext.request.contextPath}/jsp/ui2/pages/components/select2/js/select2.min.js" type="text/javascript"></script>
	<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/components/select2/js/i18n/zh-CN.js"></script>
	
<!-- 多选菜单 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/assets/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/assets/prettify.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/assets/prettify.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/jquery.multiselect.js"></script>
<link
	href="${pageContext.request.contextPath}/jsp/ui2/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- jedate -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/skin/jedate.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/skin/deepgreen.css">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/jquery.jedate.js"
	type="text/javascript"></script>
<!-- jqgrid -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/ui.jqgrid.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/ui.multiselect.css">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.tablednd.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.contextmenu.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.layout.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.jqGrid.js"
	type="text/javascript"></script>

<!-- jqgrid assist -->
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/jqassist.js"
	type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/jsp/ui2/pages/json2.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/pages/util.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/vector.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/dateSelector.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/unitedSelector.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/companySelector.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/messageBox.js"
	type="text/javascript"></script>

<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jqueryex.js"
	type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/jsp/ui2/tabs/tab.css"
	rel="stylesheet">
<script src="${pageContext.request.contextPath}/jsp/ui2/tabs/tab.js"></script>
<link href="${pageContext.request.contextPath}/jsp/ui2/tree/tree.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/tree/treeNode.js"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/tree/tree.js"></script>

<link
	href="${pageContext.request.contextPath}/jsp/ui2/scroll/css/jquery.mCustomScrollbar.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/scroll/js/jquery.mCustomScrollbar.js"></script>
<link
	href="${pageContext.request.contextPath}/jsp/ui2/combobox/css/combo.select.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/combobox/js/jquery.combo.select.js"></script>
<link href="${pageContext.request.contextPath}/jsp/ui2/home.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/react/0.13.2/react.js"></script>
	
<!--[if IE 8]> 
<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jquery.placeholder.min.js"></script>
	
<![endif]-->
	
	
<%@include file="ie8-b.jsp"%>
<title>经营管控平台</title>
</head>
<body>
	<div class="container-fluid">
		<div class="content-up row-fluid clearfix">
			<div class="col-md-12 col-xs-12 column">
				<nav class="navbar navbar-default">
					<div>
						<a class="navbar-title pull-left"><img
							src="${pageContext.request.contextPath}/jsp/ui2/img/logo_word.png"></a>
						<div class="nav-other pull-left"></div>

						<div class="btn-group pull-right person">
							<a class="btn btn-default dropdown-toggle" data-toggle="dropdown"
								aria-expanded="false"> <i class="fa fa-user"></i>
								${userName}
							</a>
							<ul class="dropdown-menu">
								<li><a href="#" id="btnReturnOld" style="display:none">旧版切换</a></li>
								<li><a href="#" id="btnResetPassword">修改密码</a></li>
								<li><a href="#" id="logoutBtn">退出登录</a></li>
							</ul>
						</div>

						<div class="search-area pull-right">
							<div class="pull-left">
								<select id="search-sel">
									<option value="none" selected="selected">报表名称查询</option>
								</select>
							</div>
							<div id="search-btn" class="search-btn pull-left"></div>
						</div>
					</div>

				</nav>
			</div>
		</div>
		<div class="content-down row-fluid clearfix">
			<div class="nav-left col-md-2 col-xs-2 column">
				<div id="tree"></div>
			</div>
			<div class="content-right col-md-10 col-xs-10 column">

				<div class="row-fluid clearfix">
					<div class="col-md-12 col-xs-12 column">
						<div id="tabNew"></div>
					</div>
				</div>
				<div class="row-fluid clearfix">
					<div class="col-md-12 col-xs-12 column">
						<div id="tabContent"></div>
						<!--<div id="tabContent-up" ></div>-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="navigator.jsp"%>

	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/slimscroll/jquery.slimscroll.min.js"></script>

	<!--Beyond Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/beyond.min.js"></script>

	<!--Page Related Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/bootbox/bootbox.js"></script>
	<script>
		window["userName"] = '${userName}';
		$("#btnReturnOld").click(function(){
			window.location.href = "${pageContext.request.contextPath}/Login/index.do?from=new"
		});
		var _77 = '${_77}'.length == 0 ? false : true;
	</script>
	<script src="${pageContext.request.contextPath}/jsp/ui2/home.js"></script>
</body>
</html>