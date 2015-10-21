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


	$(document).ready(function(){
		$.getJSON("/fe/sso/queryallsystem.gsp",{tt:new Date()},function(data){
			//赋值
			var html = "";
			for(var i=0;i<data.length;i++)
			{
				var bound = "";
				 var type = data[i].systemType ;
				 if(type!="CAS")
				 {
					 if(data[i].ssologinsysCode!=28 &&data[i].ssologinsysCode!=29)
					 {
						 bound="<span class=\"login_list_bd\" onclick=\"showBondPage('"+data[i].ssologinsysCode+"','${usrName}');\">绑定用户</span>";
					 }
					 
				 }
				 html +=" <li class=\"login_list_item login_list_item_"+(i+1)+"\"><a target=\"_blank\"  href=\"/fe/sso/gotoPage.gsp?ssologinsysCode="+data[i].ssologinsysCode+"\"><span>"+data[i].systemCode+"</span><img src=\"/fe/static/images/login_icons_1.png\"  /></a>"+bound+"</li>";
			}
            $("#login_list_sys").append(html);
		});
		});



	
	function loginJxpt(usrName, psw){
		$.post("http://192.168.7.12:8080/login.do?validate=login&ABS_SchemeName=jxkh&userId=" +usrName+ "&pass=000000" + psw);
		xmlhttp.open("POST","http://192.168.7.12:8080/login.do?validate=login&ABS_SchemeName=jxkh&userId=anfengling&pass=000000&image.x=35&image.y=13",false);
		xmlhttp.send(null);document.body.innerHTML=xmlhttp.responseText;
	}
	
		</script>


	<div class="theme-popover-mask"></div>
	<div class="login_list_main">
		<div class="login_list_top">
			<div class="left">${usrName}，欢迎您的登录！</div>
			<div class="right">
				<a href="logout.do">退出</a> <a href="index.do">返回首页</a>
			</div>
		</div>
		<div class="login_list_bottom">
			<a href=""><img src="../images/logo_png.png" alt=""></a>
		</div>

		<div class="login_list">
			<ul id="login_list_sys">

				<li class="login_list_item login_list_item_1"><div onclick="login(1)">
					<span>绩效管理平台</span><img
						src="../images/login_icons_1.png"></div><span
					class="login_list_bd"
					onclick="showBondPage(&#39;84&#39;,&#39;${usrName}&#39;);">绑定用户</span></li>
<!-- 				<li class="login_list_item login_list_item_2"><div onclick="login( 2)"> -->
<!-- 					<span>用户管理系统</span><img -->
<!-- 						src="../images/login_icons_1.png"></div></li> -->
				<li class="login_list_item login_list_item_3"><div onclick="login(3)">
					<span>OA系统</span><img
						src="../images/login_icons_1.png"></div><span
					class="login_list_bd"
					onclick="showBondPage(&#39;65&#39;,&#39;${usrName}&#39;);">绑定用户</span></li>
				<li class="login_list_item login_list_item_4"><div onclick="login(4)">
					<span>智慧银行</span><img
						src="../images/login_icons_1.png"></div></li>
				<li class="login_list_item login_list_item_5"><div onclick="login(5)"><span>经营管控系统</span><img
						src="../images/login_icons_1.png"></a></li>
				<li class="login_list_item login_list_item_6"><a
					target="_blank"
					href="http://192.168.7.75:9081/login.jsp?key=13d8240a-62f7-40a6-9140-84ddbda58655"><span>人力资源系统</span><img
						src="../images/login_icons_1.png"></a></li>
				<li class="login_list_item login_list_item_7"><a
					target="_blank"
					href="http://192.168.7.24:9083/login.jsp?key=e273def1-4222-4f45-b934-902617c36206"><span>财务系统</span><img
						src="../images/login_icons_1.png"></a></li>
			</ul>
		</div>
	</div>


</body>
</html>