<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/login_list.css" type="text/css"
	media="all" />
<script src="../js/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="../js/main.js" type="text/javascript"></script>
<script src="../js/util.js" type="text/javascript"></script>
<script src="../js/index_camel.js" type="text/javascript"></script>

<style type="text/css">
.login_list li {
	width: 80px;
	height: 80px;
	margin: 7px;
	display: inline;
}

.login_list {
	text-align: center;
}

body {
	width: 100%;
	height: 100%;
}

.login_list {
	width: 100%;
	height: 100%;
}

.login_list_item_new span {
	font-size: 12px;
	font-family: "microsoft yahei";
	color: #FFFFFF;
	padding-left: 3px;
	line-height: 12px
}

.login_list_item_new span.login_list_bd {
	font-size: 9px;
	padding-top: 10px
}

.login_list_item_new img {
	position: absolute;
	bottom: 0;
	right: 0
}

.login_list_item_new:hover {
	opacity: 0.7;
	border: 0px dashed #003b8f
}

.login_list_item_new:hover span {
	cursor: pointer
}

.login_list_item_new {
	position: relative;
}
</style>

</head>
<body>
	<table align="center">
		<tr>
			<td>
				<div class="login_list" >
					<ul id="login_list_sys">
						<li class="login_list_item_new login_list_item_1"><a
							href="../account/get_login_url.do?sysId=1" target="_blank"> <span>绩效管理平台</span><img
								src="../images/login_icons_1.png"></a> <br />
						<span class="login_list_bd"
							onclick="showBondPage(1,'${usrName}');">绑定用户</span></li>

						<li class="login_list_item_new login_list_item_2"><a
							href="../account/get_login_url.do?sysId=3" target="_blank"> <span>OA系统</span><img
								src="../images/login_icons_1.png"></a><br />
						<span class="login_list_bd"
							onclick="showBondPage(3,'${usrName}');">绑定用户</span></li>

						<li class="login_list_item_new login_list_item_3"><a
							href="../account/get_login_url.do?sysId=4" target="_blank"> <span>智慧银行</span><img
								src="../images/login_icons_1.png"></a> <br /> <span
							class="login_list_bd" onclick="showBondPage(4,'${usrName}');">绑定用户</span></li>

						<li class="login_list_item_new login_list_item_4"><a
							href="../account/get_login_url.do?sysId=5" target="_blank"> <span>经营管控系统</span><img
								src="../images/login_icons_1.png"></a> <br /> <span
							class="login_list_bd" onclick="showBondPage(5,'${usrName}');">绑定用户</span></li>

						<li class="login_list_item_new login_list_item_5"
							style="margin-right: 7px"><a target="_blank"
							href="http://192.168.7.75:9081/login.jsp"><span>人力资源系统</span>

								<img src="../images/login_icons_1.png"></a></li>

						<li class="login_list_item_new login_list_item_6"><a
							target="_blank" href="http://192.168.7.24:9083/login.jsp"><span>财务系统</span><img
								src="../images/login_icons_1.png"></a></li>
					</ul>
				</div>
			<td>
		</tr>
	</table>
</body>
</html>