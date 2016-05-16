<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<p style="margin: 10px 0 0px 0"></p>


<div>
	<h3>
		通用经营管报<a name="commonjygb"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
				<c:if test="${YszkgbLookup}">
				<li><a href="../yszkgb/show.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>应收账款管报汇总</strong></span></a>
				</li>
				</c:if>
				<c:if test="${ChgbLookup}">
				<li><a href="../chgb/show.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>存货管报汇总</strong></span></a>
				</li>
				</c:if>
			</ul>
		</div>
	</div>
</div>