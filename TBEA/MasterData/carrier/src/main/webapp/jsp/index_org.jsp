<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<h3>
	市场数据汇总<a name="Market"> </a>
</h3>
<div class="section">
	<div class="thumb4">
		<ul class="slides">
			<li data-thumb="asset/img/example/bar1.png"><a
				href="${pageContext.request.contextPath}/Market/mkt_view.do" target="_blank"><img
					src="${pageContext.request.contextPath}/images/charts/bar1.png"> <span><strong>市场数据汇总</strong></span></a>
			</li>
			<li data-thumb="asset/img/example/cache.png"><a
				href="${pageContext.request.contextPath}/Market/mkt_import_data.do" target="_blank"><img
					src="${pageContext.request.contextPath}/images/charts/bar10.png"> <span><strong>市场数据导入</strong></span></a>
			</li>
		</ul>
	</div>
</div>