<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<h3>
	人员主数据<a name="person"> </a>
</h3>
<div class="section">
	<div class="thumb4">
		<ul class="slides">
			<li data-thumb="${pageContext.request.contextPath}/images/person/person.png"><a
				href="${pageContext.request.contextPath}/psn/search.do" target="_blank"><img
					src="${pageContext.request.contextPath}/images/person/person.png"> <span><strong>人员信息查询</strong></span></a>
			</li>
			<li data-thumb="${pageContext.request.contextPath}/images/person/person.png"><a
				href="${pageContext.request.contextPath}/psn/search.do" target="_blank"><img
					src="${pageContext.request.contextPath}/images/person/person.png"> <span><strong>人员员工卡号查询</strong></span></a>
			</li>
			<li data-thumb="${pageContext.request.contextPath}/images/person/person.png"><a
				href="${pageContext.request.contextPath}/psn/search.do" target="_blank"><img
					src="${pageContext.request.contextPath}/images/person/person.png"> <span><strong>人员单点登录账号查询</strong></span></a>
			</li>
		</ul>
	</div>
</div>