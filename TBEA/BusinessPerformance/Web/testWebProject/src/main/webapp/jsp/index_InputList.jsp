<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${entryPlan}">
	<h3>
		计划指标录入<a name="inputPlan"> </a>
	</h3>
	<div class="section">
		<div class="thumb4">
			<ul class="slides">
				<li data-thumb="asset/img/example/bar1.png"><a
					href="../entry/zb.do?entryType=0" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>全年计划指标录入</strong></span></a>
				</li>
				<li data-thumb="asset/img/example/cache.png"><a
					href="../entry/zb.do?entryType=1" target="_blank"><img
						src="../images/charts/bar10.png"> <span><strong>季度-月度末计划值录入</strong></span></a>
				</li>
			</ul>
		</div>
	</div>
</c:if>
<c:if test="${entryPredict}">
	<h3>
		预计/实际指标录入<a name="inputPrediction"> </a>
	</h3>
	<div class="section">
		<div class="thumb4">
			<ul class="slides">
				<li data-thumb="asset/img/example/bar1.png"><a
					href="../entry/zb.do?entryType=2" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>20号预计指标录入</strong></span></a>
				</li>
				<li data-thumb="asset/img/example/cache.png"><a
					href="../entry/zb.do?entryType=3" target="_blank"><img
						src="../images/charts/bar10.png"> <span><strong>28号预计指标录入</strong></span></a>
				</li>
				<li data-thumb="asset/img/example/cache.png"><a
					href="../entry/zb.do?entryType=4" target="_blank"><img
						src="../images/charts/bar10.png"> <span><strong>实际指标录入</strong></span></a>
				</li>
			</ul>
		</div>
	</div>
</c:if>

<%-- <c:if test="${isJydw}">
	<h3>
		日报录入<a name="inputDaily"> </a>
	</h3>
	<div class="section">
		<div class="thumb4">
			<ul class="slides">
				<li data-thumb="asset/img/example/bar1.png"><a
					href="../dailyReport/yszk.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>应收账款录入</strong></span></a>
				</li>
			</ul>
		</div>
	</div>
</c:if> --%>

<c:if test="${isJydw}">
	<h3>
		经营分析录入<a name="inputDaily"> </a>
	</h3>
	<div class="section">
		<div class="thumb4">
			<ul class="slides">
				<li data-thumb="asset/img/example/bar1.png"><a
					href="../zzy_lrsj/zb.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>经营分析录入</strong></span></a>
				</li>
				<li data-thumb="asset/img/example/bar1.png"><a
					href="../dailyReport/yszk.do" target="_blank"><img
						src="../images/charts/force.png"> <span><strong>应收账款日报录入</strong></span></a>
				</li>
			</ul>
		</div>
	</div>
</c:if>





