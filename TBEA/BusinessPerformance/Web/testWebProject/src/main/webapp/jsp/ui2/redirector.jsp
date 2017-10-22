<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html style="width: 100%; height: 100%">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="Shortcut Icon"
	href="${pageContext.request.contextPath}/jsp/ui2/images/logo.ico"
	type="image/x-icon">
<title>跳转中</title>
<style>
body {
	width: 100%;
	height: 100%;
	background: skyblue;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

table {
	width: 100%;
	height: 80%;
}

.main {
	width: 100%;
	height: auto;
	display: table-cell;
	vertical-align: middle;
}

.loading {
	width: 150px;
	height: 15px;
	margin: 0 auto;
}

.loading span {
	display: inline-block;
	width: 13px;
	height: 13px;
	margin-right: 5px;
	border-radius: 50%;
	background: white;
	-webkit-animation: load 1.04s ease infinite;
}

.loading span:last-child {
	margin-right: 0px;
}

@-webkit-keyframes load { 
	0%{
		opacity: 1;
	}
	
	100%{
		opacity:0.2;            
	}
}

.loading span:nth-child(1) {
	-webkit-animation-delay: 0.13s;
}

.loading span:nth-child(2) {
	-webkit-animation-delay: 0.26s;
}

.loading span:nth-child(3) {
	-webkit-animation-delay: 0.39s;
}

.loading span:nth-child(4) {
	-webkit-animation-delay: 0.52s;
}

.loading span:nth-child(5) {
	-webkit-animation-delay: 0.65s;
}
</style>
</head>
<body>
	<form
		method="post" style="display: none">
		<%
		Enumeration pNames = request.getParameterNames();
		while(pNames.hasMoreElements()){
		    String name= (String)pNames.nextElement();
		    String value = request.getParameter(name);
		    out.print("<input type='text' name='" + name + "' value='" + value + "'>");
		}
	%>
	</form>
	<table cellpadding="0" cellspacing="0">
		<tr align="center">
			<td>
				<div class="main">
					<div class="loading">
						<span></span> <span></span> <span></span> <span></span> <span></span>
					</div>
				</div>
			</td>
		</tr>
	</table>

</body>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jquery-1.12.3.js"></script>
<script>

	$("form")[0].action = encodeURI("${pageContext.request.contextPath}/${param.url}");
		$("form")[0].submit();
	</script>
</html>