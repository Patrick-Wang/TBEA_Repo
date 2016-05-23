<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <%@page import="com.sony.controller.servlet.mail.SendLostPasswordFormBean"%> --%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<title>特变电工经营管控信息平台</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Indexstyle.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.min.js"></script>


<script type="text/javascript">
	function formSubmit() {
		var usrName = $('#j_username').val();
		var psw = $('#j_password').val();
		if (null == usrName || "" == usrName || "" == psw || null == psw) {
			$('.errors').show();
		} else {
			$('.errors').hide();
			document.forms[0].submit();
		}

		//window.location.href='index.htm';
		//document.forms[0].submit();
	};
	function doSubmit(evt) {
		var evt = evt ? evt : (window.event ? window.event : null);
		if (evt.keyCode == 13) {
			formSubmit();
		}
	}
</script>
</head>
<body>
	<div style="text-align: center;">
		<div class="header">
			<div>
				<table>
					<tr>
						<td style="padding-top: 0px"><img
							src="${pageContext.request.contextPath}/images/login_logo.png"
							width="100%" border="0" height="130px"></td>
					</tr>
				</table>
			</div>
		</div>
		<div style="height: 80px;"></div>
		<div class="title" style="text-align: center;">
			<h1 style="color: #170A69; font-size: 300%">
				<b>特变电工经营管控信息平台</b>
			</h1>
		</div>

		<div style="height: 50px;"></div>
		<div align="center">

			<form id="loginForm" action="/BusinessManagement/Login/validate.do"
				method="post">
				<fieldset>
					<label for="j_username">用户名：</label> <input type="text"
						name="j_username" id="j_username" onkeydown="doSubmit(event)"
						autocomplete="on" style="width:265px;padding: 4px;"/> <label for="j_password">密码：</label> <input
						type="password" name="j_password" id="j_password" style="width:265px;padding: 4px;"
						onkeydown="doSubmit(event)" /> <input type="hidden"
						name="transmissionStr" id="transmissionStr" />
					<c:choose>
						<c:when test="${(!empty error) && error}">
							<div class="errors">用户名或密码错误，请重新输入。</div>
						</c:when>
						<c:otherwise>
							<div class="errors" style="display: none">用户名或密码错误，请重新输入。</div>
						</c:otherwise>
					</c:choose>
					<c:if test="${(!empty error) && error}">
					</c:if>

					<div style="position: relative; height: 52px; margin-top: 10px;">
						<a id="a_id" href="${pageContext.request.contextPath}/jsp/resetPassword.jsp">修改密码</a>
						<span class="small-btn" onclick="formSubmit();">登录</span>
					</div>
				</fieldset>
			</form>

		</div>


		<div
			style="text-align: center; margin-top: 140px; font-size: 13px; font-weight: 400;">

			<div align="center">
				<table>
					<tr>
						<td><img
							src="${pageContext.request.contextPath}/jsp/point.png"></td>
						<td>
							<div style="font-size: 14px; font-weight: 400;">
								提示：为了获得更好的浏览效果，建议您使用IE8.0及以上版本或chrome浏览器登陆本站点</div>
						</td>
					</tr>
				</table>
			</div>
			<br /> <br /> <br />
			<div>&copy;2015 信息资源管理中心版权所有</div>
		</div>
	</div>

</body>
</html>