<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="${pageContext.request.contextPath}/jsp/ui2/navigator.js"></script>

<c:choose>
	<c:when test="${MarketAuth}">
		<c:if test="${scbsjLookup || scbsjEntry}">
			<script>
				builder.register('bbxx', function() {
					return root = createNode("市场签约信息", undefined, "fa fa-plus-square-o", "fa fa-minus-square-o")
						.append(builder.build('marketShow'))
						.append(builder.build('marketEntry'));
				});
			</script>
			<c:if test="${scbsjLookup}">
				<script>
					builder.register('marketShow', function() {
						return [ createNode('市场签约统计情况', 'report/scqytjqk.do'),
								createNode('重点产品签约情况查看', 'report/scqy.do'),
								createNode('行业签约情况查看', 'report/scjb.do') ];
					});
				</script>
			</c:if>
			<c:if test="${scbsjEntry}">
				<script>
					builder.register('marketEntry', function() {
						return root = [
								createNode('重点产品签约情况录入', 'report/scqyEntry.do'),
								createNode('行业签约情况录入', 'report/scjbEntry.do') ];
					});
				</script>
			</c:if>
		</c:if>
	</c:when>

	<c:when test="${QualityAuth}">
		<script>
			builder.register('bbxx', function() {
				return root = createNode("质量信息汇总分析", undefined, "fa fa-plus-square-o", "fa fa-minus-square-o").append(
						builder.build('qualityShow')).append(
						builder.build('qualityApprove')).append(
						builder.build('qualityEntry')).append(
						builder.build('qualityImport'));
			});
 		</script>
		<c:if test="${QualityEntry}">
			<script>
				builder.register('qualityEntry', function() {
					return [
						createNode("产品一次送试录入", 'cpzlqk/entry.do'), 
						createNode("原材料合格率录入", 'report/yclhglqktjEntry.do')
					];
				});
	 		</script>
			<c:if test="${I_EQualityImport}">
				<script>
					builder.register('qualityImport', function() {
						return [
							createNode("内部质量问题导入", 'report/nbzlqkImportJsp.do'), 
							createNode("外部质量问题导入", 'report/wbzlqkImportJsp.do')
						];
					});
		 		</script>
			</c:if>
		</c:if>

		<c:if test="${QualityLookup || QualityApprove}">
 			<c:if test="${!QualityApprove}">
				<script>
					builder.register('qualityShow', function() {
						return [
								createNode("产品一次送试及原材料合格率汇总及分析", 'cpzlqk/show.do'), 
								createNode("内外部质量问题汇总及分析", 'nwbzlqk/show.do')
							];
					});
	 			</script>
			</c:if>
			<c:if test="${QualityApprove}">
				<script>
					builder.register('qualityApprove', function() {
						return [
								createNode("产品一次送试及原材料合格率审核及上报", 'cpzlqk/approve.do'), 
								createNode("内外部质量问题审核及上报", 'nwbzlqk/approve.do')
							];
					});
	 			</script>
			</c:if>
		</c:if>
	</c:when>

	<c:otherwise>

		<c:if test="${admin}">
			<script>
				builder.register('bbxx', function() {
					return createNode("Dashboard", 'dashboard/user_status.do');
				});
	 		</script>
		</c:if>

		<c:if test="${notSbqgb}">
			<script>
				builder.register('bbxx', function() {
					return createNode('经营指标完成情况', undefined, "fa fa-plus-square-o", "fa fa-minus-square-o")
						.append(builder.build('gczt'))
						.append(createNode('单位指标完成情况', 'ydzb/v2/hzb_companys.do'))
						.append(builder.build('gcyzb'));
				});
	 		</script>

			<c:if test="${CorpAuth}">
				<script>
					builder.register('gczt', function () {
						return [
						    createNode('公司整体指标完成情况', 'ydzb/v2/hzb_zbhz.do')
						];
					});
		 		</script>
			</c:if>
			<c:if test="${CorpAuth}">
				<script>
					builder.register('gcyzb', function() {
						return [
						    createNode('产业重点指标完成情况', 'ydzb/v2/gcy_zbhz.do'),
						    createNode('单位重点指标完成情况', 'ydzb/v2/gdw_zbhz.do'),
						    createNode('产业指标汇总', 'report/acyhzzb.do')
						];
					});
		 		</script>
			</c:if>
		</c:if>
		<script>
			builder.register('bbxx', function() {
				return createNode('经营指标预测情况', undefined, "fa fa-plus-square-o", "fa fa-minus-square-o")
					.append(builder.build('ztzbycwcqk'))
					.append(createNode('单位指标预测完成情况', 'ydzb/v2/hzb_companys_prediction.do'))
					.append(builder.build('wdjyzbyc'))
					.append(builder.build('sjztbqk'));
			});
		</script>
					
		<c:if test="${CorpAuth}">
			<script>
				builder.register('ztzbycwcqk', function() {
					return createNode('整体指标预测完成情况', 'ydzb/v2/hzb_zbhz_prediction.do');
				});
			</script>
		</c:if>
	
		<c:if test="${CorpAuth}">
			<script>
				builder.register('wdjyzbyc', function() {
					return [
						createNode('产业重点指标预测完成情况', 'ydzb/v2/financial_zbhz_prediction.do'),
						createNode('单位重点指标预测完成情况', 'ydzb/v2/gdw_zbhz_prediction.do')
					];
				});
			</script>
		</c:if>
		<c:if test="${JYEntryLookup || zhAuth}">
			<script>
				builder.register('sjztbqk', function() {
					return createNode('指标填报情况汇总', 'dashboard/status.do');
				});
			</script>
		</c:if>
	
		<c:if test="${isJydw || scgsdbqx || FinanceLookup}">
			<script>
				builder.register('bbxx', function() {
					return createNode('财务分析', undefined, "fa fa-plus-square-o", "fa fa-minus-square-o")
						.append(builder.build('gsztcwwcqk'))
						.append(createNode('单位财务指标完成情况', 'NCzb/CompanysNC.do'))
						.append(createNode('财务对标需求', 'report/dbxq.do'))
						.append(createNode('经济增加值', 'report/jjzjz.do'))
						.append(builder.build('cwfxLookup'));
				})
			</script>
	
			<c:if test="${CorpAuth}">
				<script>
					builder.register('gsztcwwcqk', function() {
						return createNode('公司财务指标完成情况', 'NCzb/AllCompanysNC_overview.do');
					});
				</script>
			</c:if>
	
			<c:if test="${FinanceLookup}">
				<script>
					builder.register('cwfxLookup', function () {
						return [
						    createNode('财务-应交税费', 'cwyjsf/show.do'),
						    createNode('财务-产品大类毛利表', 'cwcpdlml/show.do'),
						    createNode('财务-经营性现金流', 'cwgbjyxxjl/show.do')
						];
					});
				</script>
			</c:if>
		</c:if>
		<c:if test="${JYAnalysisLookup}">
			<script>
				builder.register('bbxx', function() {
					return createNode('经营指标分析', undefined)
						.append(builder.build('yszkrbLookup'))
						.append(builder.build('xjlrbLookup'))
						.append(builder.build('jyzbpm'))
						.append(builder.build('xnyrb'));
				});
			</script>
			<c:if test="${YSZKDialyLookup}">
				<script>
					builder.register('yszkrbLookup', function() {
						return createNode('应收账款日报', 'yszkrb/yszk.do');
					});
				</script>
			</c:if>
			<c:if test="${XJLDialyLookup}">
				<script>
					builder.register('xjlrbLookup', function() {
						return createNode('现金流日报', 'ydzb/v2/xjlrb.do');
					});
				</script>
			</c:if>
			<c:if test="${CorpAuth}">
				<script>
					builder.register('jyzbpm', function() {
						return createNode('各单位经营指标排名情况', 'ydzbRanking/companys_ranking.do');
					});
				</script>
			</c:if>
			<c:if test="${xtnyrbLookupAuth}">
				<script>
					builder.register('xnyrb', function() {
						return createNode('新特能源日报', 'report/xtnyrb.do');
					});
				</script>
			</c:if>
		</c:if>
		<c:if test="${scgsdbqx}">
			<script>
				builder.register('bbxx', function() {
					return createNode('上市公司对标数据')
						.append(createNode('对标数据总体分析', 'report/ztdbfx.do'))
						.append(createNode('对标数据分类分析', 'report/fldbfx.do'));
				});
			</script>
		</c:if>
		<c:if test="${sddbLookup || sddbImport}">
			<script>
				builder.register('bbxx', function() {
					return createNode('订单全过程管控')
						.append(builder.build('SddbLookup'))
						.append(builder.build('SddbImport'));
				});
			</script>
			<c:if test="${sddbLookup}">
				<script>
					builder.register('SddbLookup', function () {
						return [
						   createNode('订单全过程管控', "report/bidCollectionWrapper.do"),
						   createNode('中标产品分型号汇总', "report/WinBidModelCollection.do"),
						   createNode('分阶段单位订单成本汇总', "report/gdwddfxhCollection.do"),
						   createNode('订单成本汇总表(按型号)', "report/gdwddCollection.do"),
						   createNode('公司订单分阶段汇总分析', "report/ddfjdCollection.do"),
						   createNode('完工订单分图号对比分析', "report/codeCollection.do"),
						   createNode('完工订单分型号对比分析', "report/modelCollection.do"),
						   createNode('订单信息明细', "report/ddDetailJsp.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${sddbImport}">
				<script>
					builder.register('SddbImport', function () {
						return createNode('订单信息导入', "report/sddbImportWrapperJsp.do");
					});
				</script>
			</c:if>
		</c:if>
		<c:if test="${scbsjLookup || scbsjEntry}">
			<script>
				builder.register('bbxx', function() {
					return createNode('市场签约信息')
						.append(builder.build('ScqyLookup'))
						.append(builder.build('ScqyEntry'));
				});
			</script>
			<c:if test="${scbsjLookup}">
				<script>
					builder.register('ScqyLookup', function () {
						return [
						   createNode('市场签约统计情况', "report/scqytjqk.do"),
						   createNode('重点产品签约情况查看', "report/scqy.do"),
						   createNode('行业签约情况查看', "report/scjb.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${scbsjEntry}">
				<script>
					builder.register('ScqyEntry',function () {
						return [
						   createNode('重点产品签约情况录入', "report/scqyEntry.do"),
						   createNode('行业签约情况录入', "report/scjbEntry.do")
						];
					});
				</script>
			</c:if>
		</c:if>
		<c:if test="${zhzlLookup || zhJyfxLookupAuth}">
			<script>
				builder.register('bbxx', function() {
					return createNode('众和指标')
						.append(builder.build('Zhzlzb'))
						.append(builder.build('Zhfzgszlzb'));
				});
			</script>
			<c:if test="${zhzlLookup}">
				<script>
					builder.register('Zhzlzb', function () {
						return [
						   createNode('众和质量指标', "report/zhzl.do")
						];
					});
				</script>
			</c:if>
		
			<c:if test="${zhJyfxLookupAuth}">
				<script>
					builder.register('Zhfzgszlzb', function () {
						return [
						   createNode('众和分子公司累计汇总', "report/zhgsljzbhz.do"),
						   createNode('众和分子公司月度汇总', "report/zhgsljzbhz.do"),
						   createNode('众和分产业关键指标汇总', "report/zhgsfcyUnion.do")
						];
					});
				</script>
			</c:if>
		</c:if>
		<c:if test="${PriceLibAuth}">
			<script>
				builder.register('bbxx', function() {
					return createNode('价格库数据汇总')
						.append(createNode('价格库数据展示', "jcycljg/show.do"))
						.append(createNode('价格库数据录入', "jcycljg/import/show.do"));
				});
			</script>
		</c:if>
		<c:if test="${ComGbLookup}">
			<script>
				builder.register('bbxx', function() {
					return createNode('通用经营管报')
						.append(builder.build('Yszkgb'))
						.append(builder.build('Chgb'));
				});
			</script>
			<c:if test="${YszkgbLookup}">
				<script>
					builder.register('Yszkgb', function () {
						return [
						   createNode('应收账款管报汇总', "yszkgb/show.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${ChgbLookup}">
				<script>
					builder.register('Chgb', function () {
						return [
						   createNode('存货管报汇总', "chgb/show.do")
						];
					});
				</script>
			</c:if>
		</c:if>
		<c:if test="${SbdgbLookup}">
			<script>
				builder.register('bbxx', function() {
					return createNode('输变电产业经营管报')
						.append(createNode('大宗物资管报', "dzwzgb/show.do"))
						.append(createNode('存货管报汇总', "wlydd/show.do"))
						.append(createNode('输变电产值/产量完成情况', "sbdczclwcqk/show.do"))
						.append(createNode('输变电市场签约情况', "sbdscqyqk/show.do"))
						.append(createNode('完工产品情况', "wgcpqk/show.do"));
				});
			</script>
	    </c:if>
		<c:if test="${NygbLookup}">
	       <script>
				builder.register('bbxx', function() {
					return createNode('能源产业经营报表')
						.append(createNode('成本分析', "cbfx/show.do"))
						.append(createNode('能源-周边市场情况', "nyzbscqk/show.do"));
				});
			</script>
		</c:if>
		<c:if test="${XnygbLookup || gcyzbLookup || gcyzbImport || xnyJyfxLookupAuth}">
			<script>
				builder.register('bbxx', function() {
					return createNode('新能源产业经营报表')
						.append(builder.build('Xnygb'))
						.append(builder.build('Gcyzb'))
						.append(builder.build('GcyzbImport'))
						.append(builder.build('XnyJyfx'));
				});
			</script>
			<c:if test="${XnygbLookup}">
				<script>
					builder.register('Xnygb', function () {
						return [
						   createNode('新能源存货', "xnychFrame/show.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${gcyzbLookup}">
				<script>
					builder.register('Gcyzb', function () {
						return [
						   createNode('工程一张表', "report/gcyzb.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${gcyzbImport}">
				<script>
					builder.register('GcyzbImport', function () {
						return [
						   createNode('工程一张表数据导入', "report/gcyzbImportJsp.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${xnyJyfxLookupAuth}">
				<script>
					builder.register('XnyJyfx', function () {
						return [
						   createNode('应收账款回款周报', "report/yszkhkzb.do"),
						   createNode('生产、发货和价格周报', "report/xnyzb.do"),
						   createNode('签约周报', "report/xnyqyzb.do")
						];
					});
				</script>
			</c:if>
		 </c:if>
	        
	 
		<c:if test="${entryPlan}">
			<script>
				builder.register('bbxx', function() {
					return createNode('计划指标录入')
						.append(createNode('全年计划指标录入', "entry/v2/zb.do?entryType=0"))
						.append(createNode('季度-月度末计划值录入', "entry/v2/zb.do?entryType=1"));
				});
			</script>
		</c:if>
	
		<c:if test="${entryPredict}">
			<script>
				builder.register('bbxx', function() {
					return createNode('预计/实际指标录入')
						.append(createNode('20号预计指标录入', "entry/v2/zb.do?entryType=2"))
						.append(createNode('28号预计指标录入', "entry/v2/zb.do?entryType=3"))
						.append(createNode('实际指标录入', "entry/v2/zb.do?entryType=4"));
				});
			</script>
		</c:if>
	
		<c:if test="${isJydw}">
			<script>
				builder.register('bbxx', function() {
					return createNode('经营分析录入')
						.append(createNode('应收账款日报录入', "dailyReport/yszk.do"))
						.append(builder.build('ZXnyJyfxEntry'))
						.append(builder.build('ZXtnyrbEntry'));
				});
			</script>
			<c:if test="${xnyJyfxEntryAuth}">
				<script>
					builder.register('ZXnyJyfxEntry', function () {
						return [
						   createNode('新能源生产、发货和价格周报录入', "report/xnyzbEntryJsp.do"),
						   createNode('新能源应收账款回款周报导入', "report/yszkhkzbImportJsp.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${xtnyrbEntryAuth}">
				<script>
					builder.register('ZXtnyrbEntry', function () {
						return [
						   createNode('新特能源日报录入', "report/xtnyrbEntry.do")
						];
					});
				</script>
			</c:if>
		</c:if>
		
		<c:if test="${zhzlEntry}">
			<script>
				builder.register('bbxx', function() {
					return createNode('质量信息指标录入')
						.append(createNode('质量信息指标录入', "report/zhzlEntry.do"));
				});
			</script>
		</c:if>
	
	
		<c:if test="${approvePlan}">
			<script>
				builder.register('bbxx', function() {
					return createNode('计划指标审核')
						.append(createNode('全年计划指标审核', "approve/zb.do?approveType=0"))
						.append(createNode('季度-月度末计划值审核', "approve/zb.do?approveType=1"));
				});
			</script>
		</c:if>
	
		<c:if test="${approvePredict}">
			<script>
				builder.register('bbxx', function() {
					return createNode('预计/实际指标审核')
						.append(createNode('20预计指标审核', "approve/zb.do?approveType=2"))
						.append(createNode('28号预计指标审核', "approve/zb.do?approveType=3"))
						.append(createNode('实际指标审核', "approve/zb.do?approveType=4"));
				});
			</script>
		</c:if>
	
	
	<%--      <ul id="navlist3" style="padding: 10px 0; display: none">
	        <li style="background-color: transparent; diplay: none"><i
	            class="ec-icon ec-icon-force"></i> <a href="#finincial"
	            style="color: rgb(62, 152, 197);">财务指标汇总</a></li>
	    </ul> --%>
	
		<c:if test="${ComGbEntry}">
			<script>
				builder.register('bbxx', function() {
					return createNode('通用经营管报录入')
						.append(builder.build('YszkgbEntry'))
						.append(builder.build('ChgbEntry'));
				});
			</script>
			<c:if test="${YszkgbEntry}">
				<script>
					builder.register('YszkgbEntry', function () {
						return [
						   createNode('应收账款管报录入', "yszkgb/entry.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${ChgbEntry}">
				<script>
					builder.register('ChgbEntry', function() {
						return [
						   createNode('存货管报录入', "chgb/entry.do")
						];
					});
				</script>
			</c:if>
		</c:if>
		<c:if test="${SbdgbEntry}">	
			<script>
				builder.register('bbxx', function() {
					return createNode('输变电产业经营管报录入')
						.append(createNode('未履约订单情况录入', "wlydd/entry.do"))
						.append(createNode('输变电产值/产量完成情况录入', "sbdczclwcqk/entry.do"))
						.append(createNode('输变电市场签约情况录入', "sbdscqyqk/entry.do"))
						.append(createNode('完工产品情况录入', "wgcpqk/entry.do"));
				});
			</script>
		</c:if>
	    <c:if test="${NygbEntry}">
	        <script>
	        	builder.register('bbxx', function() {
					return createNode('能源产业经营报表录入')
						.append(createNode('成本分析录入', 'cbfx/entry.do'))
						.append(createNode('能源-周边市场情况录入', "nyzbscqk/entry.do"));
				});
			</script>
		</c:if>
	
		<c:if test="${XnygbEntry}">
	        <script>
	        	builder.register('bbxx', function() {
					return createNode('新能源产业经营报表录入')
						.append(createNode('新能源存货录入', 'xnychFrame/entry.do'));
				});
			</script>
		</c:if>
	    <c:if test="${FinanceEntry}">
	         <script>
	         	builder.register('bbxx', function() {
					return createNode('财务报表录入')
						.append(createNode('财务-经营性现金流录入', 'cwgbjyxxjl/entry.do'));
				});
			</script>
	    </c:if>
		<c:if test="${QualityLookup || QualityApprove}">
			<script>
				builder.register('bbxx', function() {
					return createNode('质量问题')
						.append(builder.build('ZlLookup'))
						.append(builder.build('ZlApprove'));
				});
			</script>
			<c:if test="${!QualityApprove}">
				<script>
					builder.register('ZlLookup', function () {
						return [
						   createNode('产品一次送试及原材料合格率汇总及分析', "cpzlqk/show.do"),
						   createNode('内外部质量问题汇总及分析', "nwbzlqk/show.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${QualityApprove}">
				<script>
					builder.register('ZlApprove', function () {
						return [
						   createNode('产品一次送试及原材料合格率审核及上报', "cpzlqk/approve.do"),
						   createNode('内外部质量问题审核及上报', "nwbzlqk/approve.do")
						];
					});
				</script>
			</c:if>
		</c:if>
	</c:otherwise>
</c:choose>


<script>
	function createNode(value, url, icon, iconOpen){
	    if (url != undefined){
	        url = '${pageContext.request.contextPath}/' + url;
	    }else{
	    	icon = "fa fa-plus-square-o";
	    	iconOpen = "fa fa-minus-square-o";
	    }
	    var node = {
	        data : {
	            id : navi.uid(),
	            value:value,
	            url : url,
	            icon : icon,
	            iconOpen : iconOpen
	        },
	        subNodes:[]
	    };
	    node.append  = function(sub){
	        if (sub != undefined){
	            if (sub instanceof Array){
	                node.subNodes.pushAll(sub);
	            }else{
	                node.subNodes.push(sub);
	            }
	        }
	        return node;
	    }
	    return node;
	}

	builder.register('root', function(){
		var ztfx = createNode("主题分析");
		ztfx.data.icon = "fa fa-bar-chart";
		ztfx.data.iconOpen = undefined;
		var bbxx = createNode("报表信息");
		bbxx.data.extracted = true;
		bbxx.data.icon = "fa fa-pencil-square-o";
		bbxx.data.iconOpen = undefined;
		bbxx.append(builder.build('bbxx'));
		return [ztfx, bbxx];
	});
</script>
