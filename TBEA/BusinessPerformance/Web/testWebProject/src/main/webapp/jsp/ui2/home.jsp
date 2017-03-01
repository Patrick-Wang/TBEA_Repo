<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link
	href="${pageContext.request.contextPath}/jsp/ui2/bootstrap-3.3.0-dist/dist/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/jsp/ui2/bootstrap-3.3.0-dist/dist/css/bootstrap-theme.css"
	rel="stylesheet">

<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jquery-1.12.3.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jqueryex.js"></script>
<script src="${pageContext.request.contextPath}/jsp/util.js"></script>

<script
	src="${pageContext.request.contextPath}/jsp/ui2/bootstrap-3.3.0-dist/dist/js/bootstrap.js"></script>

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

<link href="${pageContext.request.contextPath}/jsp/ui2/home.css"
	rel="stylesheet">

<title>Title</title>
</head>
<body>
	<div class="container-fluid" style="visibility: hidden">
		<div class="content-up row-fluid clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"> </span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#"><img
							src="${pageContext.request.contextPath}/jsp/ui2/img/logo.png"></a>
					</div>
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a class="navbar-title"><span>reporting
										system</span></a></li>
						</ul>

						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">${userName}<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="#">通知消息</a></li>
									<li><a href="#">个人中心</a></li>
									<li><a href="#" onclick="home.logout()">退出登录</a></li>
								</ul></li>
						</ul>
						<form class="navbar-form navbar-right" role="search">
							<div class="form-group">
								<input type="text" class="form-control" />
							</div>
							<div type="submit" class="search-btn"></div>
						</form>
					</div>
				</nav>
			</div>
		</div>
		<div class="content-down row-fluid clearfix">
			<div class="nav-left col-md-2 column">
				<div id="tree"></div>
			</div>
			<div class="content-right col-md-10 column">

				<div class="row-fluid clearfix">
					<div class="col-md-12 column">
						<div id="tabNew"></div>
					</div>
				</div>
				<div class="row-fluid clearfix">
					<div class="col-md-12 column">
						<div id="tabContent"></div>
						<!--<div id="tabContent-up" ></div>-->
					</div>
				</div>
			</div>
		</div>
	</div>
    <%@include file="navigator.jsp"%>
	<script src="${pageContext.request.contextPath}/jsp/ui2/home.js"></script>
</body>
</html>