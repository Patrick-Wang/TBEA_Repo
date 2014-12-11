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


<script type="text/javascript">
function formSubmit(){
	var usrName = $('#j_username').val();
	var psw = $('#j_password').val();
	if ("huanghanjie" == usrName && "hhj" == psw) {
        $('.errors').hide();
		window.location.href='index.htm';
	} else if ("admin" == usrName && "1234" == psw) { 
        $('.errors').hide();
        window.location.href='index.htm';
    } else if ("qgb" == usrName && "1234" == psw) { 
        $('.errors').hide();
        window.location.href='index2.htm';
    } else {
	    $('.errors').show();
	}
	//window.location.href='index.htm';
    //document.forms[0].submit();
};
function doSubmit(evt){
      var evt=evt?evt:(window.event?window.event:null);
      if (evt.keyCode==13){
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
                    <td style = "padding-top: 3px">
                        <img src="images/login_logo.png" width = "300px" border="0" height="60px">
                    </td>
                </tr>
            </table>
		</div>
	</div>
	<div style="height:30px;"></div>
    <div class = "title" style ="text-align: center;">
        <img src="images/title.png" width = "640px" border="0" height="62px">
    </div>
    
	<div style="height:60px;"></div>
    <div align="center" >
	
	<form id="loginForm" action="" method="post" >
	  <fieldset>
		  <label for="j_username">用户名：</label>
		  <input type="text" name="j_username" id="j_username" onkeydown="doSubmit(event)" autocomplete="on" />
		  <label for="j_password">密码：</label>
		  <input type="password" name="j_password" id="j_password" onkeydown="doSubmit(event)"/>
		  <input type="hidden" name="transmissionStr" id="transmissionStr"/>
          <div class="errors" style="display: none" >用户名或密码错误，请重新输入。 </div>
		  <div style="position: relative;height: 52px;margin-top:10px;" onclick="formSubmit();">
		  <span  class="small-btn" >Login</span>
	      </div>
	  </fieldset>
	</form>
	
	</div>
		<div style="text-align:center;margin-top:140px;font-size: 13px;font-weight: 400;">
		  <div>&copy;2014 信息资源管理中心版权所有 </div>
	    </div>
	</div>

</body>
</html>