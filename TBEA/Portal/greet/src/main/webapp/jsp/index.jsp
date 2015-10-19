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


	function showBondPage(systemCode,sysTemName){
		$("div.theme-popover3 .dform3").empty();
		var params = "params.ssoSysCode="+systemCode;
	//	$.getJSON("/fe/sso/querySystemParams.gsp",params,function(data){

	//	});
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover3').slideDown(200);
		
		data = JSON.parse('{"infoList":[{"paramsName":"用户名","paramsKey":"ssousername","ssosysCode":65,"encrypt":0,"encryptTramsfer":0},{"paramsName":"用户密码","paramsKey":"ssopassword","ssosysCode":65,"encrypt":0,"encryptTramsfer":0}]}');
		var paramsList = data.infoList;
		var html = "";
		$.each(paramsList,function(i,item){
			html += "<div class=\"dform_div\"><span style=\"min-width:80px;\">"
			+ paramsList[i].paramsName+
			 ":</span><input style=\"width:352px;\" flg=\"wr\" onblur=\"testInput('"+paramsList[i].paramsKey+
			"')\" maxlength=\"30\" type=\"text\"  id="+
			paramsList[i].paramsKey+
			" name="+paramsList[i].paramsKey+
			" class=\"alert_text\" /><lable style=\"color:#E61212\" id='"+
			paramsList[i].paramsKey+
			"lable'></lable></div>";
		});
		html +="<div class=\"dform_div\"><input type=\"button\" id=\"updateUserInfo\" value=\"绑 定\" class=\"alert_button\" onclick=\"goToBond('"+
				systemCode+"','"+sysTemName+"')\"/></div>";

		$("div.theme-popover3 .dform3").append(html);
		html = "";
	}


	function goToBond(systemCode,sysTemName){
		var flag = false;
		var str = "";
		var inputs= $("input[flg='wr']");
		inputs.each(function(){
			if(testInput($(this).attr("id"))){
				str +="{\"key\":\""+$(this).attr("name")+"\",\"value\":\""+$(this).val()+"\"},";
				flag = true;
			}else{
				flag = false;
			}
		});
		if(flag){
			str = str.substring(0,str.length-1).replace(/[ ]/g,"");
			str = "["+str+"]";

			var params={"params.paramsValuesStr":str,"params.systemCode":systemCode,"params.userCode":"${usrName}"};
			
			//$.getJSON("/fe/sso/insertSystemParamsValues.gsp",params,function(data){
				if(true){
					$("div.theme-popover3 .dform3").empty();
					var html = "<div style=\"text-align: center; width: 300px;  margin: 60px auto;\"><h3>绑定 “"+sysTemName+"” 成功!</h3></div>";
					$("div.theme-popover3 .dform3").append(html);
				}else{
					$("div.theme-popover3 .dform3").empty();
					var html = "<div style=\"text-align: center; width: 300px;  margin: 60px auto;\"><h3>绑定 “"+sysTemName+"” 失败!</h3></div>";
					$("div.theme-popover3 .dform3").append(html);
				}
			//});
		}
	}

	function testInput(id){
		
		var idstr = "#"+id;
		var text = $(idstr).val().replace(/[ ]/g,"");
		if(text == null || text == ""){
			$(idstr+"lable").text("请按照提示填入信息");
			return false;
		}else{
			$(idstr+"lable").text("");
		}
		return true;
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

				<li class="login_list_item login_list_item_1"><a
					target="_blank"
					href="http://192.168.7.12:8080/welcome.do"><span>绩效管理平台</span><img
						src="../images/login_icons_1.png"></a><span
					class="login_list_bd"
					onclick="showBondPage(&#39;84&#39;,&#39;${usrName}&#39;);">绑定用户</span></li>
				<li class="login_list_item login_list_item_2"><a
					target="_blank"
					href="http://172.28.8.74/apps/page/admin/index.vm"><span>用户管理系统</span><img
						src="../images/login_icons_1.png"></a></li>
				<li class="login_list_item login_list_item_3"><a
					target="_blank"
					href="http://172.28.8.119/HQ/myportal/__ac0x3login/__tpaction?requestSource=HQ_login"><span>OA系统</span><img
						src="../images/login_icons_1.png"></a><span
					class="login_list_bd"
					onclick="showBondPage(&#39;65&#39;,&#39;${usrName}&#39;);">绑定用户</span></li>
				<li class="login_list_item login_list_item_4"><a
					target="_blank"
					href="http://km.tbea.com:8080/login.jsp"><span>智慧银行</span><img
						src="../images/login_icons_1.png"></a></li>
				<li class="login_list_item login_list_item_5"><a
					target="_blank"
					href="http://192.168.7.22/BusinessManagement"><span>经营管控系统</span><img
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