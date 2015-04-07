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
	href="${pageContext.request.contextPath}/css/resetPassword.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/resetPassword.js"></script>
<script src="../jsp/util.js" type="text/javascript"></script>
<!-- message box -->
<link href="../jsp/message-box/css/style.css" rel="stylesheet" type="text/css">
<!-- message box -->
<script src="../jsp/message-box/js/Sweefty.js" type="text/javascript"></script>
<script src="../jsp/message-box/js/moaModal.js" type="text/javascript"></script>
<script src="../jsp/messageBox.js" type="text/javascript"></script>
 <style type="text/css">
button, input, select, textarea { font-family: inherit; /* 1 */ font-size: 100%; /* 2 */ margin: 5px; /* 3 */ }
</style>

</head>
<body onload="afterLoadPage()">
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
		<div align="center" style="height: 260px;">
			<span><h2>修改密码</h2></span>
			<form id="resetPasswordFrom" method="post"
				action="${pageContext.request.contextPath}/Account/resetPassword.do">
				<span id="checkPassword" style="color: red; display: none;">
					请输入正确信息，密码只可以是字母或者数字</span>
				<span id="sameAsOldPassword" style="color: red; display: none;"> 
					请不要与原密码相同 </span>
				<span id="newPasswordNotSame" style="color: red; display: none;"> 
					请确认两次输入新密码一致 </span>
				<table style="text-align: left;">
					<tr>
						<td class="Properties_label"><label>用户名:</label></td>
						<td width="10"></td>
						<td> <input type="text" name="j_username" id="j_username" 
								autocomplete="off" style="display: block; width:265px;padding: 4px">
						</td>
					</tr>
					<tr>
						<td class="Properties_label"><label>原密码:</label></td>
						<td width="10"></td>
						<td><input type="password"
								id="j_password" name="j_password"  type="text" style="display: block;width:265px;padding: 4px">
						</td>
					</tr>
					<tr>
						<td class="Properties_label"><label>新密码:</label></td>
						<td width="10"></td>
						<td><input type="password"
								name="loadNewPassword" id="loadNewPassword" type="text"
								style="display: block;width:265px;padding: 4px">
						</td>
					</tr>
					<tr>
						<td class="Properties_label"><label>确认新密码:</label></td>
						<td width="10"></td>
						<td><input type="password"
								id="reloadNewPassword" name="reloadNewPassword" type="text" style="display: block;width:265px;padding: 4px">
						</td>
					</tr>
					<tr>
						<td colspan="3"><span class="btnPassword" id="btnPasswordOK">提交</span>
							<span class="btnPassword" id="btnPasswordCancel">清空</span></td>
					</tr>
				</table>
			</form>
		</div>


		<div
			style="text-align: center; margin-top: 140px; font-size: 13px; font-weight: 400;">
            <input type="hidden" id="result" value="${result}"/>
            <input type="hidden" id="message" value="${message}"/>
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