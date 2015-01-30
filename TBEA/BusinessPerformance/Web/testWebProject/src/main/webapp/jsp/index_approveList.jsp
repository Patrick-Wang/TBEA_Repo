<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${approvePlan}">

	<h3>
		计划指标审核<a name="approvePlan"> </a>
	</h3>
	<div class="section">
		<div class="thumb4">
			<ul class="slides">
				<li data-thumb="asset/img/example/bar1.png"><a
					href="../approve/zb.do?approveType=0" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>全年计划指标审核</strong></span></a>
				</li>
				<li data-thumb="asset/img/example/cache.png"><a
					href="../approve/zb.do?approveType=1" target="_blank"><img
						src="../images/charts/bar10.png"> <span><strong>20号计划指标审核</strong></span></a>
				</li>
				<li data-thumb="asset/img/example/cache.png"><a
					href="../approve/zb.do?approveType=2" target="_blank"><img
						src="../images/charts/bar10.png"> <span><strong>28号计划指标审核</strong></span></a>
				</li>
			</ul>
		</div>
	</div>

</c:if>
<c:if test="${approvePredict}">
	<h3>
		预计/实际指标审核<a name="approvePrediction"> </a>
	</h3>
	<div class="section">
		<div class="thumb4">
			<ul class="slides">
				<li data-thumb="asset/img/example/bar1.png"><a
					href="../approve/zb.do?approveType=3" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>20预计指标审核</strong></span></a>
				</li>
				<li data-thumb="asset/img/example/cache.png"><a
					href="../approve/zb.do?approveType=4" target="_blank"><img
						src="../images/charts/bar10.png"> <span><strong>28号预计指标审核</strong></span></a>
				</li>
				<li data-thumb="asset/img/example/cache.png"><a
					href="../approve/zb.do?approveType=5" target="_blank"><img
						src="../images/charts/bar10.png"> <span><strong>实际指标审核</strong></span></a>
				</li>
			</ul>
		</div>
	</div>
</c:if>