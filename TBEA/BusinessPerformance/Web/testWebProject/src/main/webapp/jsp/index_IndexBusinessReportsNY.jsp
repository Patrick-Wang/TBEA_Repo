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
		能源产业经营报表<a name="nycyjybb"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
				<li><a href="../nycbfx/show.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>成本分析</strong></span></a>
				</li>
				<li><a href="../nyzbscxl/show.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>能源-周边市场销量</strong></span></a>
				</li>
				<li><a href="../nyzbscjg/show.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>能源-周边市场价格</strong></span></a>
				</li>
			</ul>
		</div>
	</div>
</div>