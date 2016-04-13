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
		经营管报数据汇总<a name="jygbxx"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
				<li><a href="../yszkgb/show.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>应收账款管报</strong></span></a>
				</li>
				<li><a href="../chgb/show.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>存货管报</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>盈利分析管报</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>输变电-订单储备及排产情况</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>成本管报</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>财务管报</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>市场分析管报</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>输变电产值/产量管报</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>能源-周边市场销量/市场价格</strong></span></a>
				</li>
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>新能源存货</strong></span></a>
				</li>
			</ul>
		</div>
	</div>
</div>