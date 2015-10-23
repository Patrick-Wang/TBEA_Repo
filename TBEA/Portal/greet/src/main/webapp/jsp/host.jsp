<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>特变电工-首页</title>
</head>

<body>
 	<script type="text/javascript"> 
 	if ('${url}' == ""){
 		alert("请绑定用户！");
 		window.close();
 	}else{
 		window.location.href = '${url}';
 	}
	</script> 
</body>
</html>