<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<p style="margin: 10px 0 0px 0"></p>


<div>
	<h3>
		输变电产业经营管报<a name="sbdcyjygb"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
				<li><a href="../dzwzgb/show.do" target="_blank"><img
						src="../images/charts/bar9.png"> <span><strong>大宗物资管报</strong></span></a>
				</li>
				<c:if test="${SbdgbLookup}">
				<li><a href="../wlydd/show.do" target="_blank"><img
						src="../images/charts/bar4.png"> <span><strong>未履约订单情况</strong></span></a>
				</li>
				</c:if>
				
				<li><a href="../sbdczclwcqk/show.do" target="_blank"><img
						src="../images/charts/bar5.png"> <span><strong>输变电产值/产量完成情况</strong></span></a>
				</li>
				<li><a href="../sbdscqyqk/show.do" target="_blank"><img
						src="../images/charts/bar2.png"> <span><strong>输变电市场签约情况</strong></span></a>
				</li>
				<li><a href="../wgcpqk/show.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>完工产品情况</strong></span></a>
				</li>
				<%--
				<li><a href="../cpzlqk/show.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>产品质量情况</strong></span></a>
				</li>
				--%>
			</ul>
		</div>
	</div>
</div>