<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<p style="margin: 10px 0 0px 0"></p>
<c:if test="${!sbqgb}">
<div>
	<h3>
		经营指标完成情况<a name="zbhz"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
				<c:if test="${CorpAuth}">
				<li><a href="../ydzb/hzb_zbhz.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>公司整体指标完成情况</strong></span></a>
				</li>
				</c:if>				
				<li><a href="../ydzb/hzb_companys.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>经营单位及项目公司指标完成情况</strong></span></a>
				</li>
				<!--/li-->
				<c:if test="${CorpAuth}">
				<li><a href="../ydzb/gcy_zbhz.do" target="_blank"><img
						src="../images/charts/line2.png"> <span><strong>各产业五大经营指标完成情况</strong></span></a>
				</li>
				<!--/li-->
				<li><a href="../ydzb/gdw_zbhz.do" target="_blank"><img
						src="../images/charts/pie1.png"> <span><strong>各单位五大经营指标完成情况</strong></span></a>
				</li>
<!-- 				/li
				<li><a href="../ydzb/gdw_zbhz.do?zb=6" target="_blank"><img
						src="../images/charts/scatter1.png"> <span><strong>销售收入指标完成情况</strong></span></a>
				</li>
				/li
				<li><a href="../ydzb/gdw_zbhz.do?zb=29" target="_blank"><img
						src="../images/charts/scatter2.png"> <span><strong>净现金流指标完成情况</strong></span></a>
				</li>
				/li
				<li><a href="../ydzb/gdw_zbhz.do?zb=32" target="_blank"><img
						src="../images/charts/scatter4.png"> <span><strong>应收账款指标完成情况</strong></span></a>
						</li>
				/li
				<li><a href="../ydzb/gdw_zbhz.do?zb=35" target="_blank"><img
						src="../images/charts/scatter5.png"> <span><strong>存货指标完成情况</strong></span></a>
						</li>
				/li -->
				</c:if>
			</ul>
		</div>
	</div>
</div>
</c:if>


<c:if test="${CorpAuth}">
<div>
	<h3>
		经营指标预测情况<a name="zbPrediction"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">

				<li><a href="../ydzb/hzb_zbhz_prediction.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>整体指标预测完成情况</strong></span></a>
				</li>
				<!--/li-->
				<li><a href="../ydzb/financial_zbhz_prediction.do" target="_blank"><img
						src="../images/charts/line2.png"> <span><strong>各产业五大经营指标预测完成情况</strong></span></a>
				</li>
				<li><a href="../ydzb/gdw_zbhz_prediction.do" target="_blank"><img
						src="../images/charts/pie1.png"> <span><strong>各单位五大经营指标预测完成情况</strong></span></a>
				</li>
				<li><a href="../entry/status.do" target="_blank"><img
						src="../images/charts/scatter1.png"> <span><strong>20号/28号/实际指标预测值填报情况</strong></span></a>
				</li>
<!-- 	
				/li
				<li><a href="../ydzb/gdw_zbhz_prediction.do?zb=29" target="_blank"><img
						src="../images/charts/scatter2.png"> <span><strong>经营性净现金流预测完成情况</strong></span></a>
				</li>
				/li
				<li><a href="../ydzb/gdw_zbhz_prediction.do?zb=32" target="_blank"><img
						src="../images/charts/scatter4.png"> <span><strong>应收账款预测完成情况</strong></span></a></li>
				/li
				<li><a href="../ydzb/gdw_zbhz_prediction.do?zb=35" target="_blank"><img
						src="../images/charts/scatter5.png"> <span><strong>存货预测完成情况</strong></span></a></li> -->
			</ul>
		</div>
	</div>
</div>
</c:if> 
<c:if test="${SbdAuth}">
<div>
	<h3>
		应收账款<a name="yszk"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">

				<li data-thumb="asset/img/example/line1.png"><a
					href="../yszkpzjh/yszkpzjh.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>应收账款盘子规划</strong></span></a>
				</li>
				<!--/li-->

				<li data-thumb="asset/img/example/cache.png"><a
					href="../yszkjgqk/yszkjgqk.do" target="_blank"><img
						src="../images/charts/line2.png"> <span><strong>应收账款结构情况</strong></span></a>
				</li>
				<!--/li-->

				<li data-thumb="asset/img/example/cache.png"><a
					href="../yqkbhqs/yqkbhqs.do" target="_blank"><img
						src="../images/charts/pie1.png"> <span><strong>逾期款趋势变化</strong></span></a>
				</li>
				<!--/li-->

				<li data-thumb="asset/img/example/line1.png"><a
					href="../yqysysfx/yqysysfx.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>逾期应收因素分析</strong></span></a>
				</li>

				<li><a href="../CQK/cqk.do" target="_blank"><img
						src="../images/charts/pie1.png"> <span><strong>陈欠款分析</strong></span></a>
				</li>
				<!--/li-->

				<li data-thumb="asset/img/example/line1.png"><a
					href="../ztyszkfx/ztyszkfx.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>整体应收账款分析表</strong></span></a>
				</li>
				<!--/li-->
			</ul>
		</div>
	</div>
</div>

<div>
	<h3>
		保理状态<a name="bl"> </a>
	</h3>
	<div class="section">
		<div class="thumb4">
			<ul class="slides">
				<li data-thumb="asset/img/example/k1.png"><a
					href="../blhtdqqkhz/blhtdqqkhz.do" target="_blank"><img
						src="../images/charts/k1.png"> <span><strong>保理合同到期情况汇总</strong></span></a>
				</li>
				<!--/li-->
			</ul>
		</div>
	</div>
</div>
<div>
	<h3>
		合同付款<a name="ht"> </a>
	</h3>
	<div class="section">
		<div class="thumb4">
			<ul class="slides">
				<li data-thumb="asset/img/example/cache.png"><a
					href="../byqfkfstj/byqfkfstj.do" target="_blank"><img
						src="../images/charts/pie1.png"> <span><strong>变压器合同付款方式明细</strong></span></a>
				</li>
				<li data-thumb="asset/img/example/cache.png"><a
					href="../xlfkfstj/xlfkfstj.do" target="_blank"><img
						src="../images/charts/pie2.png"> <span><strong>线缆合同付款方式明细</strong></span></a>
				</li>
			</ul>
		</div>
	</div>

</div>
<div>
	<h3>
		回款<a name="hk"> </a>
	</h3>
	<div class="section">
		<div class="thumb4">
			<ul class="slides">
				<li data-thumb="asset/img/example/cache.png"><a
					href="../hkjhjg/hkjhjg.do" target="_blank"><img
						src="../images/charts/scatter2.png"> <span><strong>本月回款计划结构明细</strong></span></a>
				</li>
				<li data-thumb="asset/img/example/cache.png"><a
					href="../syhkjhzxqk/syhkjhzxqk.do" target="_blank"><img
						src="../images/charts/scatter5.png"> <span><strong>上月回款计划执行情况</strong></span></a>
				</li>
				<li data-thumb="asset/img/example/cache.png"><a
					href="../rhkqk/rhkqk.do" target="_blank"><img
						src="../images/charts/scatter5.png"> <span><strong>当日回款明细</strong></span></a>
				</li>
			</ul>
		</div>
	</div>
</div>
<div>
	<h3>
		成本管控<a name="cb"> </a>
	</h3>
	<div class="section">
		<div class="thumb4">
			<ul class="slides">
				<li data-thumb="asset/img/example/bar1.png"><a
					href="../byqcb/tb.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>变压器投标明细</strong></span></a>
				</li>
				<li data-thumb="asset/img/example/cache.png"><a
					href="../byqcb/zx.do" target="_blank"><img
						src="../images/charts/bar10.png"> <span><strong>变压器执行订单明细</strong></span></a>
				</li>
				<li data-thumb="asset/img/example/cache.png"><a
					href="../byqcb/wg.do" target="_blank"><img
						src="../images/charts/bar10.png"> <span><strong>变压器完工订单明细</strong></span></a>
				</li>
				<li data-thumb="asset/img/example/cache.png"><a
					href="../xlcb/tb.do" target="_blank"><img
						src="../images/charts/bar2.png"> <span><strong>线缆投标明细</strong></span></a>
				</li>

				<li data-thumb="asset/img/example/bar1.png"><a
					href="../xlcb/wg.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>线缆完工订单明细</strong></span></a>
				</li>
			</ul>
		</div>
	</div>
</div>
<div>
	<h3>
		投标保证金<a name="tbbzj"> </a>
	</h3>
	<div class="section">
		<div class="thumb4">
			<ul class="slides">
				<li data-thumb="asset/img/example/bar1.png"><a
					href="../tbbzjqk/tbbzjqk.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>投标保证金情况</strong><br>投标保证金情况</span></a>
				</li>
			</ul>
		</div>
	</div>
</div>
</c:if> 