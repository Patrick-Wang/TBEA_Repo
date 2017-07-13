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
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/beyond.css"
	rel="stylesheet" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/login.css" />
	<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/font-awesome.min.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/util.js"></script>

<script type="text/javascript">

	Util.Ajax.parentInvalidate(window.location.href);

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
	
	$(document).ready(function(){
		var queueLength = $(".middlebg").length;
		var queue = $(".middlebg");
		var header = 0;
		setInterval(function(){
			queue.eq(header).fadeOut(2500, function(){
				var oldHeader = header;			
				header = (header + 1) % queueLength;			
				for(var i = 0; i < queueLength; ++i){
					queue.eq((i + header) % queueLength).css("z-index", "" + (queueLength - i));
				}
				queue.eq(oldHeader).fadeIn(10);
				
			});
		}, 5000);
	});
	
</script>
<!--[if IE 8]> 


<style>
.login-area .fa{
	padding: 9px 7px 7px 7px; 
}

.login-area .fa-lock{
	padding: 9px 10px 7px 8px;
}
</style>
<![endif]-->
</head>
<body>
	<div style="text-align: center;">
		<img class="top-title" src="${pageContext.request.contextPath}/jsp/ui2/img/login_title.png">
		<img class="headerbg" src="${pageContext.request.contextPath}/jsp/ui2/img/login_header.png">
		<img class="middlebg z3" src="${pageContext.request.contextPath}/jsp/ui2/img/bottom_bg3.png">
		<img class="middlebg z2" src="${pageContext.request.contextPath}/jsp/ui2/img/bottom_bg1.jpg">
		<img class="middlebg z1" src="${pageContext.request.contextPath}/jsp/ui2/img/bottom_bg2.jpg">
		<img class="bottombg" src="${pageContext.request.contextPath}/jsp/ui2/img/login_header.png">
		<div align="center">

			<form id="loginForm" class="login-area" action="validate.do"
				method="post">
				<div class="welcome">欢迎登录</div>
				<div class="diveder"></div>
				<fieldset>
					<label for="j_username">用户名：</label> 
					<div><div class="fa fa-user" ></div>
					<input type="text" placeholder="用户名"
						name="j_username" id="j_username" onkeydown="doSubmit(event)"
						autocomplete="on" /></div>
					<label for="j_password">密码：</label> 
					<div><div class="fa fa-lock"></div><input type="password"  placeholder="用户名" name="j_password" id="j_password" 
						onkeydown="doSubmit(event)" />
					<input type="hidden" name="transmissionStr" id="transmissionStr" /></div>
					<c:choose>
						<c:when test="${(!empty error) && error}">
							<div class="errors">用户名或密码错误，请重新输入。</div>
						</c:when>
						<c:otherwise>
							<div class="errors" style="display: none">用户名或密码错误，请重新输入。</div>
						</c:otherwise>
					</c:choose>
					<div class="login-btn btn btn-primary" onclick="formSubmit();" >登录</div>
				</fieldset>
				
			</form>

		</div>


	</div>

</body>
</html>