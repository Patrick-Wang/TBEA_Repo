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

<c:if test="${notSbqgb}">
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
				<li><a href="../report/acyhzzb.do" target="_blank"><img
						src="../images/charts/pie2.png"> <span><strong>按照产业汇总指标</strong></span></a>
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



<div>
	<h3>
		经营指标预测情况<a name="zbPrediction"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
				<c:if test="${CorpAuth}">
				<li><a href="../ydzb/hzb_zbhz_prediction.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>整体指标预测完成情况</strong></span></a>
				</li>
				</c:if> 
				<li><a href="../ydzb/hzb_companys_prediction.do" target="_blank"><img
						src="../images/charts/scatter2.png"> <span><strong>经营单位及项目公司指标预测完成情况</strong></span></a>
				</li>
				<!--/li-->
				<c:if test="${CorpAuth}">
				<li><a href="../ydzb/financial_zbhz_prediction.do" target="_blank"><img
						src="../images/charts/line2.png"> <span><strong>各产业五大经营指标预测完成情况</strong></span></a>
				</li>
				<li><a href="../ydzb/gdw_zbhz_prediction.do" target="_blank"><img
						src="../images/charts/pie1.png"> <span><strong>各单位五大经营指标预测完成情况</strong></span></a>
				</li>
				</c:if>
				<c:if test="${JYEntryLookup || zhAuth}">
				<li><a href="../dashboard/status.do" target="_blank"><img
						src="../images/charts/scatter1.png"> <span><strong>20号/28号/实际指标预测值填报情况</strong></span></a>
				</li>
				</c:if>

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

<div>
	<h3>
		财务指标情况<a name="NChz"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
				<c:if test="${CorpAuth}">
				<li><a href="../NCzb/AllCompanysNC_overview.do" target="_blank"><img
						src="../images/charts/scatter2.png"> <span><strong>公司整体财务指标完成情况</strong></span></a>
				</li>
				</c:if> 
				<li><a href="../NCzb/CompanysNC.do" target="_blank"><img
						src="../images/charts/scatter4.png"> <span><strong>经营单位财务指标完成情况</strong></span></a>
				</li>
			</ul>
		</div>
	</div>
</div>

<c:if test="${JYAnalysisLookup}">
<div>
	<h3>
		经营指标分析<a name="dashboard"> </a>
	</h3>
	<div class="section">
		<div class="thumb3">
			<ul class="slides">
<%-- 				<c:if test="${JYAnalysisSummary}">
				<li><a href="../fxcpylspdqddmlqk/openview.do" target="_blank"><img
						src="../images/charts/bar1.png"> <span><strong>经营指标分析</strong></span></a>
				</li>
				</c:if> --%>
				<c:if test="${YSZKDialyLookup}">
				<%-- <c:if test="${false}"> --%>
				<li><a href="../yszkrb/yszk.do" target="_blank"><img
						src="../images/charts/force.png"> <span><strong>应收账款日报</strong></span></a>
				</li>
				</c:if>
				<%-- <c:if test="${false}"> --%>
			    <c:if test="${XJLDialyLookup}">
				<li><a href="../ydzb/xjlrb.do" target="_blank"><img
						src="../images/charts/force1.png"> <span><strong>现金流日报</strong></span></a>
				</li>
				</c:if>
				<c:if test="${CorpAuth}">
				<li><a href="../ydzbRanking/companys_ranking.do" target="_blank"><img
						src="../images/charts/scatter2.png"> <span><strong>各单位经营指标排名情况</strong></span></a>
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
				<c:if test="${xtnyrbLookupAuth}">
				<li><a href="../report/xtnyrb.do" target="_blank"><img
						src="../images/charts/scatter2.png"> <span><strong>新特能源日报</strong></span></a>
				</li>
				</c:if>
				<c:if test="${zhJyfxLookupAuth}">
				<li><a href="../report/zhgsljzbhz.do" target="_blank"><img
						src="../images/charts/scatter3.png"> <span><strong>众和分子公司累计汇总</strong></span></a>
				</li>
				<li><a href="../report/zhgsydzbhz.do" target="_blank"><img
						src="../images/charts/scatter4.png"> <span><strong>众和分子公司月度汇总</strong></span></a>
				</li>
				<li><a href="../report/zhgsfcylrze.do" target="_blank"><img
						src="../images/charts/bar5.png"> <span><strong>众和分产业利润总额汇报</strong></span></a>
				</li>
				<li><a href="../report/zhgsfcyxssr.do" target="_blank"><img
						src="../images/charts/scatter6.png"> <span><strong>众和分产业销售收入汇报</strong></span></a>
				</li>
				</c:if>
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

<!-- <div>
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
				/li
			</ul>
		</div>
	</div>
</div> -->
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
<%--
				<li data-thumb="asset/img/example/cache.png"><a
					href="../rhkqk/rhkqk.do" target="_blank"><img
						src="../images/charts/scatter5.png"> <span><strong>当日回款明细</strong></span></a>
				</li>
--%>
			</ul>
		</div>
	</div>
</div>
<%-- 
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
--%>
</c:if> 