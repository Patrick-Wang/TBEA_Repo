<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<p style="margin: 10px 0 0px 0"></p>


<div>
	<h3>
		质量管报<a name="zlgb"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
				<li><a href="../cpzlqk/show.do" target="_blank"><img
						src="../images/charts/bar5.png"> <span><strong>产品质量情况</strong></span></a>
				</li>
				<c:if test="${QualityApprove}">
				<li><a href="../cpzlqk/approve.do" target="_blank"><img
						src="../images/charts/bar8.png"> <span><strong>质量管报审核</strong></span></a>
				</li>
				</c:if>
			</ul>
		</div>
	</div>
</div>