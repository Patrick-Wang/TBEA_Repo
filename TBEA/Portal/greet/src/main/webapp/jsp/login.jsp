<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>特变电工-登录</title>
<link rel="stylesheet" href="../css/css.css" type="text/css" media="all" />
<script src="../js/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="../js/main.js" type="text/javascript"></script>
<script>

	    function reloadCode(){
	  	  var img =document.getElementById("imgCode");
	  	  img.src="/cas/verifyCode.go?"+ Math.random();
	  	}
	    function goToQuery(){
			var userName = $.trim($("#userName").val());
			var idCode = $.trim($("#idCode").val());
			var safeCode = $.trim($("#safeCode").val());
			var params = encodeURI("userName="+userName+"&idCode="+idCode+"&safeCode="+safeCode);
			if(nameTest(userName) && isCardNoTest(idCode) && safeCodeTest(safeCode)){
				$.getJSON("/cas/queryUserCode.go",params,function(data){
					if(data.errorcode == '1'){
						$("#check_right_safecode").hide();
						$("#check_error_safecode").show();
						reloadCode();
						$("#safeCode").val("");
					}else{
						$("#divTheme").hide();
						$("#resultDiv h3").text(data.message+data.userCode);
						$("#resultDiv").show();
					}
				});
			}
		}
    </script>
</head>
<body>
	<div class="theme-popover6">
		<div class="theme-poptit">
			<a href="javascript:;" title="关闭" class="close">×</a>
			<h3>登录名查询</h3>
		</div>
		<div id="divTheme" class="theme-popbod dform dform2 password">
			<div class="dform_div">
				<span>用户姓名：</span> <input id="userName" name="userName"
					maxlength="30" type="text" class="alert_text"> <label
					id="check_message_name"></label> <img id="check_right_name"
					style="display: none" src="../images/check_right.gif" /> <img
					style="display: none" id="check_error_name"
					src="../images/check_error.gif" />
			</div>
			<div class="dform_div">
				<span>证件号码：</span> <input id="idCode" name="idCode" maxlength="30"
					type="text" class="alert_text"> <label
					id="check_message_idcode"></label> <img id="check_right_idcode"
					style="display: none" src="../images/check_right.gif"> <img
					style="display: none" id="check_error_idcode"
					src="../images/check_error.gif">
			</div>
			<div class="dform_div">
				<span> 验证码：</span> <img id="imgCode" class="yzm"
					src="../images/verifyCode.go" width="114" height="29"
					onclick="reloadCode()"> <input id="safeCode" maxlength="4"
					name="safeCode" type="text" class="alert_text alert_textyz">
				<img style="display: none" id="check_right_safecode"
					src="../images/check_right.gif"> <img style="display: none"
					id="check_error_safecode" src="../images/check_error.gif">
			</div>
			<div class="dform_div">
				<input type="button" value="查 询" id="sub" class="alert_button"
					onclick="goToQuery()">
			</div>
			<div class="dform_div"
				style="float: right; margin-right: 10px; color: #F11414">未查询到单点登录用户信息的请联系管理员！
			</div>
		</div>
		<div id="resultDiv"
			style="text-align: center; margin-top: 60px; display: none">
			<h3></h3>
		</div>
	</div>

	<div class="login_header">
		<div class="login_header_main">
			<a
				href="http://172.28.8.74/cas/login?service=http%3A%2F%2F172.28.8.74%3A7001%2Ffe%2Findex.jsp#"><img
				src="../images/logo_login.gif"></a> <span>装备中国 装备世界</span>
		</div>
	</div>
	<div class="login_bg">
		<div class="login_bg_main">
			<div class="login_box">
				<div class="login_box_main">
					<form id="fm1" action="login.do" method="post">

						<img src="../images/login_title.gif">
						<div class="login_name login_name1">
							<section class="row">
								<span>用户名:</span> <input id="username" name="username"
									class="login_text" tabindex="1" accesskey="n" type="text"
									value="" size="25" autocomplete="off">
							</section>

						</div>
						<div class="login_name">
							<section class="row">
								<span>密 码:</span> <input id="password" name="password"
									class="login_text" tabindex="2" accesskey="p" type="password"
									value="" size="25" autocomplete="off">
							</section>
						</div>


						<div class="login_botton_top">
							<section class="row check">
								<label for="warn"> <input id="warn" name="warn"
									value="true" tabindex="3" accesskey="w" type="checkbox">
									记住用户名
								</label>
								<!-- <div class="padding_10"><a href="javascript:;">忘记登录名？</a></div> -->
							</section>
						</div>

						<div class="login_button">
							<section class="row btn-row">
								<input type="hidden" name="lt"
									value="LT-93-DCzbjcq7AmGvifo91CDOodawTascP9-cas01.example.org">
								<input type="hidden" name="execution" value="e1s1"> <input
									type="hidden" name="_eventId" value="submit"> <input
									class="login_but_1 left" name="reset" accesskey="c" value="重置"
									tabindex="5" type="reset"> <input
									class="login_but_2 right" name="submit" accesskey="l"
									value="登录" tabindex="4" type="submit">
							</section>
						</div>

						<div style="display: none" class="login_box_bottom1">
							<span class="left">通过数字证书登陆</span> <span class="right">高级用户<a
								href="http://172.28.8.74/cas/login?service=http%3A%2F%2F172.28.8.74%3A7001%2Ffe%2Findex.jsp#">点击此处</a></span>
						</div>
						<div style="display: none" class="login_box_bottom">
							<a
								href="http://172.28.8.74/cas/login?service=http%3A%2F%2F172.28.8.74%3A7001%2Ffe%2Findex.jsp#">使用帮助</a>
							| <a
								href="http://172.28.8.74/cas/login?service=http%3A%2F%2F172.28.8.74%3A7001%2Ffe%2Findex.jsp#">软件下载</a>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="login_footer">
			<div>
				<span>特变电工版权：Webmaster@TBEA.com.cn</span><span>新IPC12001046号</span>
			</div>
		</div>
	</div>

</body>
</html>