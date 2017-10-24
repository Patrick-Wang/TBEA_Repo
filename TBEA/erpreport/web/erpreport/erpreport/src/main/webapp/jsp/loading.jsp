<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jsp/plugins/mloading/jquery.mloading.css">
<script
	src="${pageContext.request.contextPath}/jsp/plugins/mloading/jquery.mloading.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/plugins/mloading/loading.js"></script>
<script type="text/javascript">
	Util.Loading.init();
	$(document).ajaxStart(function() {
		Util.Loading.start();
	}).ajaxStop(function() {
		Util.Loading.stop();
	});
</script>
