<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/css.css" type="text/css" media="all" />
<link rel="stylesheet" href="../css/login_list.css" type="text/css"
	media="all" />
<script src="../js/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="../js/main.js" type="text/javascript"></script>
<script src="../js/util.js" type="text/javascript"></script>
<script src="../js/index.js" type="text/javascript"></script>

<style>
.highlight:hover {
	color: red;
	text-decoration: underline;
}
</style>


<title>特变电工-首页</title>
</head>

<body>
	<div class="theme-popover3">
		<div class="theme-poptit">
			<a href="javascript:void(0)" title="关闭" class="close">×</a>
			<h3>绑定用户</h3>
		</div>
		<div class="theme-popbod dform dform3"></div>
	</div>

	<script type="text/javascript">
		// 	$(document).ready(function(){
		// 	 	$.getJSON("/fe/sso/queryallsystem.gsp",{tt:new Date()},function(data){
		// 			//赋值
		// 			var html = "";
		// 			for(var i=0;i<data.length;i++)
		// 			{
		// 				var bound = "";
		// 				 var type = data[i].systemType ;
		// 				 if(type!="CAS")
		// 				 {
		// 					 if(data[i].ssologinsysCode!=28 &&data[i].ssologinsysCode!=29)
		// 					 {
		// 						 bound="<span class=\"login_list_bd\" onclick=\"showBondPage('"+data[i].ssologinsysCode+"','${usrName}');\">绑定用户</span>";
		// 					 }

		// 				 }
		// 				 html +=" <li class=\"login_list_item login_list_item_"+(i+1)+"\"><a target=\"_blank\"  href=\"/fe/sso/gotoPage.gsp?ssologinsysCode="+data[i].ssologinsysCode+"\"><span>"+data[i].systemCode+"</span><img src=\"/fe/static/images/login_icons_1.png\"  /></a>"+bound+"</li>";
		// 			}
		//             $("#login_list_sys").append(html);
		// 		});
		// 		}); 

		// 	function loginJxpt(usrName, psw){
		// 		$.post("http://192.168.7.12:8080/login.do?validate=login&ABS_SchemeName=jxkh&userId=" +usrName+ "&pass=000000" + psw);
		// 		xmlhttp.open("POST","http://192.168.7.12:8080/login.do?validate=login&ABS_SchemeName=jxkh&userId=anfengling&pass=000000&image.x=35&image.y=13",false);
		// 		xmlhttp.send(null);document.body.innerHTML=xmlhttp.responseText;
		// 	}
	</script>

	<div >
		<div class="theme-popover-mask"></div>
		<div class="login_list_main">
			<div class="login_list_top">
				<div class="left welcome">${usrName}，欢迎您的登录！</div>
				<div class="right welcome">
					<a href="logout.do">退出</a>
				</div>
			</div>
			<div class="login_list_bottom">
				<img src="../images/logo_png.png" alt="" style="width: 240px">

				<img src="../images/title.png" alt="" style="margin-left: 75px">
				<!-- <span style="font-family: ;color: #170A69; font-size: 350%">
				<b>应用系统集成平台</b>
			</span> -->
			</div>

			<div class="login_list">
				<ul id="login_list_sys">

					<li class="login_list_item login_list_item_1"><a
						href="../account/get_login_url.do?sysId=1" target="_blank"> <span
							class="highlight">绩效管理平台</span><img
							src="../images/login_icons_1.png"></a><span
						class="login_list_bd highlight"
						onclick="showBondPage(1,'${usrName}');">绑定用户</span></li>
						
					<li class="login_list_item login_list_item_1"><a
						href="../account/get_login_url.do?sysId=8" target="_blank"> <span
							class="highlight">综合管理平台</span><img
							src="../images/login_icons_1.png"></a><span
						class="login_list_bd highlight"
						onclick="showBondPage(8,'${usrName}');">绑定用户</span></li>

					<li class="login_list_item login_list_item_2"><a
						href="../account/get_login_url.do?sysId=3" target="_blank"> <span
							class="highlight">OA系统</span><img
							src="../images/login_icons_1.png"></a><br /> <span
						class="login_list_bd highlight"
						onclick="showBondPage(3,'${usrName}');">绑定用户</span></li>

					<li class="login_list_item login_list_item_3"><a
						href="../account/get_login_url.do?sysId=4" target="_blank"> <span
							class="highlight">智慧银行</span><img
							src="../images/login_icons_1.png"></a> <span
						class="login_list_bd highlight"
						onclick="showBondPage(4,'${usrName}');">绑定用户</span></li>
					<li class="login_list_item login_list_item_4"><a
						href="../account/get_login_url.do?sysId=5" target="_blank"> <span
							class="highlight">经营管控系统</span><img
							src="../images/login_icons_1.png"></a> <span
						class="login_list_bd highlight"
						onclick="showBondPage(5,'${usrName}');">绑定用户</span></li>

					<li class="login_list_item login_list_item_5"><a
						target="_blank" href="../account/get_login_url.do?sysId=6"><span
							class="highlight">档案管理系统</span><img
							src="../images/login_icons_1.png"></a> <span
						class="login_list_bd highlight"
						onclick="showBondPage(6,'${usrName}');">绑定用户</span></li>

				</ul>
			</div>

			<div class="login_list">
				<ul id="login_list_sys">
					<li class="login_list_item login_list_item_6"><a
						target="_blank" href="http://192.168.7.20/"><span
							class="highlight">财务系统</span><img
							src="../images/login_icons_1.png"></a></li>
					
					<li class="login_list_item login_list_item_7"><a
						href="http://192.168.7.76/" target="_blank"> <span
							class="highlight">人力资源系统</span><img
							src="../images/login_icons_1.png"></a></li>

					<li class="login_list_item login_list_item_8"><a
						target="_blank" href="ftp://ftp.tbea.com/"><span
							class="highlight">常用工具下载</span><img
							src="../images/login_icons_1.png"></a></li>

					<li class="login_list_item login_list_item_9"><a
						target="_blank" href="http://172.28.8.98/Office/Login"><span
							class="highlight">招标管理系统</span><img
							src="../images/login_icons_1.png"></a></li>
					<li class="login_list_item login_list_item_10"><a
						target="_blank" href="http://mail.tbea.com/owa/auth/logon600089.aspx?url=http://mail.tbea.com/owa/&reason=0"><span
							class="highlight">邮件系统</span><img
							src="../images/login_icons_1.png"></a></li>
					<li class="login_list_item login_list_item_11"><a
						target="_blank" href="http://172.28.8.71:7083"><span
							class="highlight">教学教务系统</span><img
							src="../images/login_icons_1.png"></a></li>
					
				</ul>

			</div>
			<div class="login_list">
				<ul id="login_list_sys">
				<li class="login_list_item login_list_item_12"><a
						target="_blank" href="https://vd.tbea.com"><span
							class="highlight">桌面虚拟化</span><img
							src="../images/login_icons_1.png"></a></li>
					<li class="login_list_item login_list_item_13"><a
						target="_blank" href="http://192.168.7.11/read_paper/node_853.htm"><span
							class="highlight">特变电子报</span><img
							src="../images/login_icons_1.png"></a></li>

					<li class="login_list_item login_list_item_14"><a
						target="_blank" href="http://www.tbea.com。cn/"><span
							class="highlight">公司外网</span><img
							src="../images/login_icons_1.png"></a></li>

					<li class="login_list_item login_list_item_15"><a
						target="_blank" href="ftp://peixun.tbea.com/"><span
							class="highlight">特变大讲堂</span><img
							src="../images/login_icons_1.png"></a></li>
					
				</ul>

			</div>

			<div style="margin-top: 30px"></div>
			<hr style="width: 100%;" />
			<div style="margin-top: 30px">
				<table>
					<tr>
						<td><img src="${pageContext.request.contextPath}/images/point.png"></td>
						<td style="margin-left: 10px">
						<span style="margin-left: 10px"><b>首次使用系统，点击绑定按钮，输入需要绑定系统相应的用户名和密码；下次点击此系统图标，可以直接进入</b></span>
						</td>
					</tr>
					<tr>
						<td><img
							src="${pageContext.request.contextPath}/images/point.png"></td>
						<td><span style="margin-left: 10px"><b>使用中有任何问题和建议，请联系信息资源管理中心0994-6508898</b></span></td>
					</tr>
				</table>
			</div>
		</div>
	</div>


</body>
</html>