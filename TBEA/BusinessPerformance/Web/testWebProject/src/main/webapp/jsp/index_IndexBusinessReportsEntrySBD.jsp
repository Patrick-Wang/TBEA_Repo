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
		经营管报录入<a name="sbdcyjygbEntry"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
			<c:if test="${YszkgbEntry}">
				<li><a href="../yszkgb/entry.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>应收账款管报录入</strong></span></a>
				</li>
				</c:if>
				<c:if test="${ChgbEntry}">
				<li><a href="../chgb/entry.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>存货管报录入</strong></span></a>
				</li>
				</c:if>
				<%--
				<li><a href="../dzwzgb/entry.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>大宗物资管报录入</strong></span></a>
				</li>
				--%>
				<c:if test="${WlyddEntry}">
				<li><a href="../wlydd/entry.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>未履约订单情况录入</strong></span></a>
				</li>
				</c:if>
				<%--
				<li><a href="../sbdczclwcqk/entry.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>输变电产值/产量完成情况录入</strong></span></a>
				</li>
				<li><a href="../sbdscqyqk/entry.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>输变电市场签约情况录入</strong></span></a>
				</li>
				<li><a href="../wgcpqk/entry.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>完工产品情况录入</strong></span></a>
				</li>
				<li><a href="../cpzlqk/entry.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>产品质量情况录入</strong></span></a>
				</li>
				--%>
			</ul>
		</div>
	</div>
</div>