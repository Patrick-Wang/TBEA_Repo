<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<p style="margin: 10px 0 0px 0"></p>



<div>
	<h3>
		新能源产业经营报表<a name="xnycyjybb"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
				<c:if test="${XnygbLookup}">
					<li><a href="../xnychFrame/show.do" target="_blank"><img
							src="../images/charts/bar10.png"> <span><strong>新能源存货</strong></span></a>
					</li>
				</c:if>
				<c:if test="${gcyzbLookup}">
					<li><a href="../report/gcyzb.do" target="_blank"><img
							src="../images/charts/scatter1.png"> <span><strong>工程一张表</strong></span></a>
					</li>
				</c:if>
				<c:if test="${gcyzbImport}">
					<li><a href="../report/gcyzbImportJsp.do" target="_blank"><img
							src="../images/charts/scatter5.png"> <span><strong>工程一张表数据导入</strong></span></a>
					</li>
				</c:if>
				<c:if test="${xnyJyfxLookupAuth}">
					<li><a href="../report/yszkhkzb.do" target="_blank"><img
							src="../images/charts/bar1.png"> <span><strong>新能源应收账款回款周报</strong></span></a>
					</li>
					<li><a href="../report/xnyzb.do" target="_blank"><img
							src="../images/charts/bar3.png"> <span><strong>新能源生产、发货和价格周报表</strong></span></a>
					</li>
					<li><a href="../report/xnyqyzb.do" target="_blank"><img
							src="../images/charts/bar2.png"> <span><strong>新能源签约周报</strong></span></a>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</div>