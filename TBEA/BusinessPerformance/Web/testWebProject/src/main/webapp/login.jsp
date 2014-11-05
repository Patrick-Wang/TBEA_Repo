<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%-- <%@page import="com.sony.controller.servlet.mail.SendLostPasswordFormBean"%> --%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<title>特变电工经营管控信息平台</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Indexstyle.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.min.js"></script>


</head>
<body>
    <div style="text-align: center;">
	<div class="header">
		<div>
			<h1><strong>特变电工经营管控信息平台</strong></h1>
		</div>
	</div>
	
	<div style="height:80px;"></div>
    <div align="center" >
	
	<form id="loginForm" action="" method="post" >
	  <fieldset>
		  <label for="j_username">Username:</label>
		  <input type="text" name="j_username" id="j_username" onkeydown="doSubmit(event)" autocomplete="on" />
		  <label for="j_password">Password:</label>
		  <input type="password" name="j_password" id="j_password" onkeydown="doSubmit(event)"/>
		  <input type="hidden" name="transmissionStr" id="transmissionStr"/>
		  <div style="position: relative;height: 52px;margin-top:10px;" onclick="javascript:window.location.href='index.htm'">
		  <span  class="small-btn" >Login</span>
	       </div>
	  
	  </fieldset>
	</form>
	
	</div>
		<div style="text-align:center;margin-top:210px;font-size: 13px;font-weight: 400;">
		  <div>&copy;2014 信息资源管理中心版权所有 </div>
	    </div>
	</div>

</body>
</html>