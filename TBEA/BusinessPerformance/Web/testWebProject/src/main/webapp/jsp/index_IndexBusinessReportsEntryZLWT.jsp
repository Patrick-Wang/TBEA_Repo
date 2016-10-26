<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<p style="margin: 10px 0 0px 0"></p>


<div>
	<h3>
		质量问题录入<a name="zlwtEntry"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
<!-- 				<li><a href="../cwyjsf/entry.do" target="_blank"><img -->
<!-- 						src="../images/charts/bar9.png"> <span><strong>财务-应交税费录入</strong></span></a> -->
<!-- 				</li> -->
<!-- 				<li><a href="../cwcpdlmlb/entry.do" target="_blank"><img -->
<!-- 						src="../images/charts/bar2.png"> <span><strong>财务-产品大类毛利表录入</strong></span></a> -->
<!-- 				</li> -->
				<li><a href="../cpzlqk/entry.do" target="_blank"><img
						src="../images/charts/bar7.png"> <span><strong>产品一次送试录入</strong></span></a>
				</li>
				<li><a href="../report/yclhglqktjEntry.do" target="_blank"><img
						src="../images/charts/bar3.png"> <span><strong>原材料合格率录入</strong></span></a>
				</li>
				<c:if test="${I_EQualityImport}">
					<li><a href="../report/nbzlqkImportJsp.do" target="_blank"><img
						src="../images/charts/bar4.png"> <span><strong>内部质量问题导入</strong></span></a>
					</li>
					<li><a href="../report/wbzlqkImportJsp.do" target="_blank"><img
						src="../images/charts/bar4.png"> <span><strong>外部质量问题导入</strong></span></a>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</div>