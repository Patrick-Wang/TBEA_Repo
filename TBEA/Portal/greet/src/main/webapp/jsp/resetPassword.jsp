<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>特变电工-修改密码</title>
<link rel="stylesheet" href="../css/css.css" type="text/css" media="all" />
<script src="../js/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="../js/main.js" type="text/javascript"></script>
<script src="../jsp/util.js" type="text/javascript"></script>
<!-- message box -->
<link href="../jsp/message-box/css/style.css" rel="stylesheet" type="text/css">
<!-- message box -->
<script src="../jsp/message-box/js/Sweefty.js" type="text/javascript"></script>
<script src="../jsp/message-box/js/moaModal.js" type="text/javascript"></script>
<script src="../jsp/messageBox.js" type="text/javascript"></script>
<script type="text/javascript"
    src="${pageContext.request.contextPath}/js/resetPassword.js"></script>
<script>
        function backToLogin() {
            window.location.href = '${pageContext.request.contextPath}';
        };
</script>
</head>
<body onload="afterLoadPage()">

	<div class="login_header">
		<div class="login_header_main">
			<img src="../images/logo_login.gif"> <span>装备中国 装备世界</span>
		</div>
	</div>
	<div class="login_bg">
		<div class="login_bg_main">
			<div class="login_box">
				<div class="login_box_main">
					<form id="resetPasswordFrom" action="${pageContext.request.contextPath}/Account/resetPassword.do" method="post">
						<span id="checkPassword" style="color: red; display: none;">
						请输入正确信息，密码只可以是字母或者数字</span>
						<span id="sameAsOldPassword" style="color: red; display: none;">
						请不要与原密码相同 </span>
						<span id="newPasswordNotSame" style="color: red; display: none;">
						请确认两次输入新密码一致 </span>
						<img src="../images/reset_title.png">
						<div class="reset_name">
							<section class="row">
								<span>用户名:</span> <input id="j_username" name="j_username"
									class="login_text" tabindex="1" accesskey="n" type="text"
									value="" size="25" autocomplete="off">
							</section>
						</div>
						<div class="reset_name">
							<section class="row">
								<span>原密码:</span> <input id="j_password" name="j_password"
									class="login_text" tabindex="2" accesskey="p" type="password"
									value="" size="25" autocomplete="off">
							</section>
						</div>
						<div class="reset_name">
							<section class="row">
								<span>新密码:</span> <input id="loadNewPassword"
									name="loadNewPassword" class="login_text" tabindex="3"
									accesskey="n" type="password" value="" size="25"
									autocomplete="off">
							</section>
						</div>
						<div class="reset_name">
							<section class="row">
								<span>确认新密码:</span> <input id="reloadNewPassword"
									name="reloadNewPassword" class="login_text" tabindex="4"
									accesskey="p" type="password" value="" size="25"
									autocomplete="off">
							</section>
						</div>

						<div style="color: #CC0000; height: 30px"></div>
						<div class="login_button">
							<section class="row btn-row">
                                <input id="btnPasswordCancel" class="login_but_1 left" name="reset" 
                                accesskey="c" value="返回" tabindex="5" type="button" onclick="backToLogin();">
								<input id="btnPasswordOK" class="login_but_2 right" name="submit" 
								accesskey="l" value="提交" tabindex="6" type="submit">
							</section>
						</div>

					</form>
				</div>
				<input type="hidden" id="result" value="${result}"/>
                <input type="hidden" id="message" value="${message}"/>
			</div>
		</div>
		<div class="login_footer">
			<div>
				<span>特变电工版权：信息资源管理中心</span><span>新IPC12001046号</span>
			</div>
		</div>
	</div>

</body>
</html>