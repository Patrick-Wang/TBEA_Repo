<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<p style="margin: 10px 0 0px 0"></p>


<div>
	<h3>
		能源产业经营报表录入<a name="nycyjybbEntry"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
				<li><a href="../cbfx/entry.do" target="_blank"><img
						src="../images/charts/bar3.png"> <span><strong>成本分析录入</strong></span></a>
				</li>
				<c:if test="${NYzbscqkEntry}">
				<li><a href="../nyzbscqk/entry.do" target="_blank"><img
						src="../images/charts/bar2.png"> <span><strong>能源-周边市场情况录入</strong></span></a>
				</li>
				</c:if>
			</ul>
		</div>
	</div>
</div>