<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<p style="margin: 10px 0 0px 0"></p>


<c:if test="${admin}">
<div>
	<h3>
		Dashboard<a name="dashboard"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
				<li><a href="../dashboard/user_status.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>在线用户状态</strong></span></a>
				</li>
			</ul>
		</div>
	</div>
</div>
</c:if>

<div>
	<h3>
		新能源产业经营报表录入<a name="xnycyjybbEntry"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
				<li><a href="../xnych/show.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>新能源存货录入</strong></span></a>
				</li>
				
			</ul>
		</div>
	</div>
</div>