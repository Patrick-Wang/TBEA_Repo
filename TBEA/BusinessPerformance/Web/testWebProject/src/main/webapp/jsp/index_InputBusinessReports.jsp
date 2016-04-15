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
		经营管报数据录入<a name="jygbxx"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
				<li><a href="../yszkgb/entry.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>应收账款数据录入</strong></span></a>
				</li>
				<li><a href="../chgb/entry.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>存货数据录入</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>盈利分析数据录入</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>输变电-订单储备及排产数据录入</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>成本数据录入</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>财务数据录入</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>市场分析数据录入</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>输变电产值/产量数据录入</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>能源-周边市场销量/市场价格数据录入</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>新能源存货数据录入</strong></span></a>
				</li>
			</ul>
		</div>
	</div>
</div>