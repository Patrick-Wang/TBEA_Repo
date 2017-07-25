<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="${pageContext.request.contextPath}/jsp/ui2/navigator.js"></script>

<c:if test="${admin}">
	<script>
		builder.register('glkzt', function() {
			return createNode("管理控制台", undefined, "fa fa-tachometer", "fa fa-tachometer")
					.append(createNode("在线用户", 'dashboard/user_status.do'));
		});
		</script>
</c:if>
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
						return [ createNode('市场签约统计情况', 'report/v2/scqytjqk.do'),
								createNode('重点产品签约情况查看', 'report/v2/scqy.do'),
								createNode('行业签约情况查看', 'report/v2/scjb.do') ];
					});
				</script>
			</c:if>
			<c:if test="${scbsjEntry}">
				<script>
					builder.register('marketEntry', function() {
						return root = [
								createNode('重点产品签约情况录入', 'report/v2/scqyEntry.do'),
								createNode('行业签约情况录入', 'report/v2/scjbEntry.do') ];
					});
				</script>
			</c:if>
		</c:if>
	</c:when>

	<c:when test="${QualityAuth}">
		<script>
			builder.register('quality', function() {
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
						createNode("产品一次送试录入", 'cpzlqk/v2/entry.do'), 
						createNode("原材料合格率录入", 'report/v2/yclhglqktjEntry.do')
					];
				});
	 		</script>
			<c:if test="${I_EQualityImport}">
				<script>
					builder.register('qualityImport', function() {
						return [
							createNode("内部质量问题导入", 'report/v2/nbzlqkImportJsp.do'), 
							createNode("外部质量问题导入", 'report/v2/wbzlqkImportJsp.do')
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
								createNode("产品一次送试及原材料合格率汇总及分析", 'cpzlqk/v2/show.do'), 
								createNode("内外部质量问题汇总及分析", 'nwbzlqk/v2/show.do')
							];
					});
	 			</script>
			</c:if>
			<c:if test="${QualityApprove}">
				<script>
					builder.register('qualityApprove', function() {
						return [
								createNode("产品一次送试及原材料合格率审核及上报", 'cpzlqk/v2/approve.do'), 
								createNode("内外部质量问题审核及上报", 'nwbzlqk/v2/approve.do')
							];
					});
	 			</script>
			</c:if>
		</c:if>
	</c:when>

	<c:otherwise>
		<!--------------------------------------------------------Company index begin ------------------------------------>
		<!--------------------------------------------------------CorpAuth refer_account table ------------------------------------>
		<c:if
			test="${CorpAuth || _75 || _76 || zhzlLookup || JYEntryLookup || zhAuth || zhJyfxLookupAuth}">
			<script>
				builder.register('bbxx', function() {
					return createNode('经营指标完成情况', undefined, "fa fa-plus-square-o", "fa fa-minus-square-o")
						.append(builder.build('gczt'))
						.append(builder.build('dwzbqk'))
						.append(builder.build('gcyzb'))
						.append(builder.build('ztzbycwcqk'))
						.append(builder.build('dwzbycqk'))
						.append(builder.build('wdjyzbyc'))
						.append(builder.build('sjztbqk'))
						.append(builder.build('Zhzlzb'))
						.append(builder.build('Zhfzgszlzb'));
				});

	 		</script>


			<c:if test="${_75}">
				<script>
	 			builder.register('dwzbqk', function () {
						return [
						    createNode('单位指标完成情况', 'ydzb/v2/hzb_companys.do')				   
						];
					});
	 			</script>
			</c:if>

			<c:if test="${_76}">
				<script>
	 			builder.register('dwzbycqk', function () {
						return [
							createNode('单位指标预测完成情况', 'ydzb/v2/hzb_companys_prediction.do')		   
						];
					});
	 			</script>
			</c:if>

			<c:if test="${CorpAuth}">
				<script>
					builder.register('gczt', function () {
						return [
						    createNode('公司整体指标完成情况', 'ydzb/v2/hzb_zbhz.do')				   
						];
					});
					
					builder.register('gcyzb', function() {
						return [
						    createNode('产业重点指标完成情况', 'ydzb/v2/gcy_zbhz.do'),
						    createNode('单位重点指标完成情况', 'ydzb/v2/gdw_zbhz.do'),
						    createNode('单位数据子库', 'report/v2/zk.do'),
						    createNode('产业指标汇总', 'report/v2/acyhzzb.do')
						];
					});
		 		</script>
			</c:if>
		</c:if>

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
					return createNode('指标填报情况汇总', 'dashboard/v2/status.do');
				});
			</script>
		</c:if>

		<!--------------------------------------------------------ZH Company begin --------------------->
		<c:if test="${zhzlLookup}">
			<script>
				builder.register('Zhzlzb', function () {
					return [
					   createNode('众和质量指标', "report/v2/zhzl.do")
					];
				});
			</script>
		</c:if>

		<c:if test="${zhJyfxLookupAuth}">
			<script>
				builder.register('Zhfzgszlzb', function () {
					return [
					   createNode('众和分子公司累计汇总', "report/v2/zhgsljzbhz.do"),
					   createNode('众和分子公司月度汇总', "report/v2/zhgsljzbhz.do"),
					   createNode('众和分产业关键指标汇总', "report/v2/zhgsfcyUnion.do")
					];
				});
			</script>
		</c:if>
		<!--------------------------------------------------------ZH Company end ------------------------->
		<!--------------------------------------------------------Company index end ------------------------------------>



		<!--------------------------------------------------------Management analysis begin ---------------------------->

		<c:if test="${JYAnalysisLookup}">
			<script>
				builder.register('bbxx', function() {
					return createNode('经营指标分析', undefined)
						.append(builder.build('yszkrbLookup'))
						.append(builder.build('xjlrbLookup'))
						.append(builder.build('jyzbpm'));
				});
			</script>
			<c:if test="${YSZKDialyLookup}">
				<script>
					builder.register('yszkrbLookup', function() {
						return createNode('应收账款日报', 'yszkrb/v2/yszk.do');
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
						return createNode('各单位经营指标排名情况', 'ydzbRanking/v2/companys_ranking.do');
					});
				</script>
			</c:if>
		</c:if>
		<!--------------------------------------------------------Management analysis end --------------------------->

		<!--------------------------------------------------------Finance analysis start ---------------------------->

		<c:if test="${isJydw || scgsdbqx || FinanceLookup || _73 || _74}">
			<script>
				builder.register('bbxx', function() {
					return createNode('财务数据分析', undefined, "fa fa-plus-square-o", "fa fa-minus-square-o")
						.append(builder.build('gsztcwwcqk'))
						.append(builder.build('gdwcwzbxx'))
						.append(builder.build('cwfxLookup'))
						.append(builder.build('externalFinData'))
						.append(builder.build('tax'))
						.append(builder.build('taximport'));
				})
			</script>

			<c:if test="${CorpAuth}">
				<script>
					builder.register('gsztcwwcqk', function() {
						return createNode('公司财务指标完成情况', 'NCzb/v2/AllCompanysNC_overview.do');
					});
				</script>
			</c:if>

			<c:if test="${isJydw || scgsdbqx}">
				<script>
				builder.register('gdwcwzbxx', function() {
					return [createNode('单位财务指标完成情况', 'NCzb/v2/CompanysNC.do'),
					        createNode('财务对标需求', 'report/v2/dbxq.do'),
					        createNode('经济增加值', 'report/v2/jjzjz.do')
					        ];
				});
				</script>
			</c:if>
			<c:if test="${FinanceLookup}">
				<script>
					builder.register('cwfxLookup', function () {
						return [
						    createNode('财务-应交税费', 'cwyjsf/v2/show.do'),
						    createNode('财务-产品大类毛利表', 'cwcpdlml/v2/show.do'),
						    createNode('财务-经营性现金流', 'cwgbjyxxjl/v2/show.do'),
						    createNode('财务-三项费用明细', 'report/v2/sxfy.do')
						];
					});
				</script>
			</c:if>
		</c:if>

		<c:if test="${scgsdbqx}">
			<script>
				builder.register('externalFinData', function() {
					return [createNode('外部单位数据总体分析', 'report/v2/ztdbfx.do'),
					         createNode('外部单位数据分类分析', 'report/v2/fldbfx.do')
					        ];
				});
			</script>
		</c:if>

		<c:if test="${_73}">
			<script>
	    	 builder.register('taximport', function () {
					return [
					    createNode('税务-数据维护', 'report/v2/cwsfImportWrapperJsp.do')				   
					];
				});
	    	 </script>

		</c:if>


		<c:if test="${_74}">
			<script>
				builder.register('tax', function() {
					return [createNode('税务-预算统计', 'report/v2/cwsfysjhWrapper.do'),
					         createNode('税务-税金税负统计', 'report/v2/cwsfsjsfWrapper.do')
					        ];
				});
			</script>
		</c:if>

		<!--------------------------------------------------------Finance analysis end ---------------------------->


		<!--------------------------------------------------------cost start ---------------------------->
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
						   createNode('投标订单汇总', "report/v2/bidCollectionWrapper.do"),
						   createNode('中标产品分型号汇总', "report/v2/WinBidModelCollection.do"),
						   createNode('分阶段单位订单成本汇总', "report/v2/gdwddfxhCollection.do"),
						   createNode('订单成本汇总表(按型号)', "report/v2/gdwddCollection.do"),
						   createNode('公司订单分阶段汇总分析', "report/v2/ddfjdCollection.do"),
						   createNode('完工订单分图号对比分析', "report/v2/codeCollection.do"),
						   createNode('完工订单分型号对比分析', "report/v2/modelCollection.do"),
						   createNode('订单信息明细', "report/v2/ddDetailJsp.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${sddbImport}">
				<script>
					builder.register('SddbImport', function () {
						return createNode('订单信息导入', "report/v2/sddbImportWrapperJsp.do");
					});
				</script>
			</c:if>
		</c:if>


		<!--------------------------------------------------------cost end ---------------------------->


		<!--------------------------------------------------------market start ---------------------------->
		<c:if test="${scbsjLookup || scbsjEntry || _71 || _72}">
			<script>
				builder.register('bbxx', function() {
					return createNode('市场分析')
						.append(builder.build('ScqyLookup'))
						.append(builder.build('ScqyEntry'))
						.append(builder.build('GnscEntry'))
						.append(builder.build('GnscLookup'));
					
				});
			</script>
			<c:if test="${scbsjLookup}">
				<script>
					builder.register('ScqyLookup', function () {
						return [
						   createNode('市场签约统计情况', "report/v2/scqytjqk.do"),
						   createNode('重点产品签约情况查看', "report/v2/scqy.do"),
						   createNode('行业签约情况查看', "report/v2/scjb.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${scbsjEntry}">
				<script>
					builder.register('ScqyEntry',function () {
						return [
						   createNode('重点产品签约情况录入', "report/v2/scqyEntry.do"),
						   createNode('行业签约情况录入', "report/v2/scjbEntry.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${_72}">
				<script>
					builder.register('GnscEntry',function () {
						return [
						   createNode('国内市场项目签约导入', "report/v2/gnscxmqywrapperImportJsp.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${_71}">
				<script>
					builder.register('GnscLookup',function () {
						return [
						   createNode('合同明细', "report/v2/gnscxmqyHtmx.do"),
						   createNode('投标明细', "report/v2/gnscxmqyTbmx.do"),
						   createNode('项目明细', "report/v2/gnscxmqyXmmx.do")
						];
					});
				</script>
			</c:if>

		</c:if>
		<!--------------------------------------------------------market end ---------------------------->

		<!--------------------------------------------------------price database begin -------------------------------------->
		<c:if test="${PriceLibAuth}">
			<script>
				builder.register('bbxx', function() {
					return createNode('价格库数据汇总')
						.append(createNode('价格库数据展示', "jcycljg/v2/show.do"))
						.append(createNode('价格库数据录入', "jcycljg/v2/import/show.do"));
				});
			</script>
		</c:if>

		<!--------------------------------------------------------price database end -------------------------------------->

		<!--------------------------------------------------------kind of gb begin -------------------------------------->
		<c:if test="${ComGbLookup}">
			<script>
				builder.register('bbxx', function() {
					return createNode('经营管报管控')
						.append(builder.build('Yszkgb'))
						.append(builder.build('Chgb'))
						.append(builder.build('sbdgbModule'))
						.append(builder.build('nygbModule'))
						.append(builder.build('xnygbModule'));
				});
			</script>
			<c:if test="${YszkgbLookup}">
				<script>
					builder.register('Yszkgb', function () {
						return [
						   createNode('应收账款管报汇总', "yszkgb/v2/show.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${ChgbLookup}">
				<script>
					builder.register('Chgb', function () {
						return [
						   createNode('存货管报汇总', "chgb/v2/show.do")
						];
					});
				</script>
			</c:if>
		</c:if>
		<c:if test="${SbdgbLookup}">
			<script>
				builder.register('sbdgbModule', function() {
					return [createNode('大宗物资管报', "dzwzgb/v2/show.do"),
							createNode('未履约订单情况', "wlydd/v2/show.do"),
							createNode('输变电产值/产量完成情况', "sbdczclwcqk/v2/show.do"),
							createNode('输变电市场签约情况', "sbdscqyqk/v2/show.do"),
							createNode('完工产品情况', "wgcpqk/v2/show.do")
							];
					});
			</script>
		</c:if>
		<c:if test="${NygbLookup}">
			<script>
				builder.register('nygbModule', function() {
					return [createNode('成本分析', "cbfx/v2/show.do"),
					        createNode('能源-周边市场情况', "nyzbscqk/v2/show.do")
					        ];
				});
			</script>
		</c:if>
		<c:if test="${XnygbLookup}">
			<script>
					builder.register('xnygbModule', function () {
						return [
						   createNode('新能源存货', "xnychFrame/v2/show.do")
						];
					});
				</script>
		</c:if>

		<!--------------------------------------------------------kind of gb end -------------------------------------->

		<!--------------------------------------------------------XNY Management start -------------------------------->
		<c:if test="${gcyzbLookup || gcyzbImport || xnyJyfxLookupAuth}">
			<script>
				builder.register('bbxx', function() {
					return createNode('新能源产业经营管控')
						.append(builder.build('Gcyzb'))
						.append(builder.build('GcyzbImport'))
						.append(builder.build('XnyJyfx'))
						.append(builder.build('xnyrb'));
				});
			</script>

			<c:if test="${gcyzbLookup}">
				<script>
					builder.register('Gcyzb', function () {
						return [
						   createNode('工程一张表', "report/v2/gcyzb.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${gcyzbImport}">
				<script>
					builder.register('GcyzbImport', function () {
						return [
						   createNode('工程一张表数据导入', "report/v2/gcyzbImportJsp.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${xnyJyfxLookupAuth}">
				<script>
					builder.register('XnyJyfx', function () {
						return [
						   createNode('应收账款回款周报', "report/v2/yszkhkzb.do"),
						   createNode('生产、发货和价格周报', "report/v2/xnyzb.do"),
						   createNode('签约周报', "report/v2/xnyqyzb.do")
						];
					});
				</script>
			</c:if>

			<c:if test="${xtnyrbLookupAuth}">
				<script>
					builder.register('xnyrb', function() {
						return createNode('新特能源日报', 'report/v2/xtnyrb.do');
					});
				</script>
			</c:if>
		</c:if>
		<!--------------------------------------------------------XNY Management end -------------------------------->

		<c:if test="${_69 || _70}">
			<script>
				builder.register('bbxx', function() {
					return createNode('存货管控', undefined, "fa fa-plus-square-o", "fa fa-minus-square-o")
						.append(builder.build('chgkShow'))
						.append(builder.build('chgkEntry'));
				});
		 	</script>
			<c:if test="${_69}">
				<script>
					builder.register('chgkShow', function() {
						return [
							createNode('存货入库年份统计', "report/v2/kcchrunftj.do"),
							createNode('积压物资汇总', "report/v2/kcjywz.do")];
					});
	
		 		</script>
			</c:if>
			<c:if test="${_70}">
				<script>
					builder.register('chgkEntry', function() {
						return createNode('存货导入', "report/v2/kcwrapperImportJsp.do");
					});
	
		 		</script>
			</c:if>
		</c:if>

		<c:if test="${_66 || _68}">
			<script>
				builder.register('bbxx', function() {
					return createNode('人力资源信息', undefined, "fa fa-plus-square-o", "fa fa-minus-square-o")
						.append(builder.build('rlzyxtbb'))
						.append(builder.build('rlzyxxdr'));
				});
		 	</script>
			<c:if test="${_66}">
				<script>
					builder.register('rlzyxtbb', function() {
						return createNode('人力资源系统报表', "report/v2/rlzyxibb.do");
					});
	
		 		</script>

			</c:if>
			<c:if test="${_68}">
				<script>
					builder.register('rlzyxxdr', function() {
						return createNode('人力资源信息导入', "report/v2/rlzyxtbbImportJsp.do");
					});
		 		</script>
			</c:if>
		</c:if>
		<c:if test="${_67}">
			<script>
				builder.register('bbxx', function() {
					return createNode('新产品信息', undefined, "fa fa-plus-square-o", "fa fa-minus-square-o")
						.append(createNode('新产品信息', "report/v2/xcpjjxy.do"));
				});
		 	</script>
		</c:if>
		<!--------------------------------------------------------Index entry start -------------------------------->

		<c:if test="${entryPlan}">
			<script>
				builder.register('entryxx', function() {
					return createNode('计划指标录入')
						.append(createNode('全年计划指标录入', "entry/v2/zb.do?entryType=0"))
						.append(createNode('季度-月度末计划值录入', "entry/v2/zb.do?entryType=1"));
				});
			</script>
		</c:if>

		<c:if test="${entryPredict}">
			<script>
				builder.register('entryxx', function() {
					return createNode('预计/实际指标录入')
						.append(createNode('20号预计指标录入', "entry/v2/zb.do?entryType=2"))
						.append(createNode('28号预计指标录入', "entry/v2/zb.do?entryType=3"))
						.append(createNode('实际指标录入', "entry/v2/zb.do?entryType=4"));
				});
			</script>
		</c:if>
		<!--------------------------------------------------------Index entry end -------------------------------->
		<c:if test="${isJydw || xnyJyfxEntryAuth || xtnyrbEntryAuth}">
			<script>
				builder.register('entryxx', function() {
					return createNode('经营分析录入')
						.append(createNode('应收账款日报录入', "dailyReport/v2/yszk.do"))
						.append(builder.build('ZXnyJyfxEntry'))
						.append(builder.build('ZXtnyrbEntry'));
				});
			</script>
			<c:if test="${xnyJyfxEntryAuth}">
				<script>
					builder.register('ZXnyJyfxEntry', function () {
						return [
						   createNode('新能源签约周报录入', "report/v2/xnyqyzbEntryJsp.do"),
						   createNode('新能源生产、发货和价格周报录入', "report/v2/xnyzbEntryJsp.do"),
						   createNode('新能源应收账款回款周报导入', "report/v2/yszkhkzbImportJsp.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${xtnyrbEntryAuth}">
				<script>
					builder.register('ZXtnyrbEntry', function () {
						return [
						   createNode('新特能源日报录入', "report/v2/xtnyrbEntry.do")
						];
					});
				</script>
			</c:if>

		</c:if>

		<c:if test="${zhzlEntry}">
			<script>
				builder.register('bbxx', function() {
					return createNode('质量信息指标录入')
						.append(createNode('质量信息指标录入', "report/v2/zhzlEntry.do"));
				});
			</script>
		</c:if>
		<!--------------------------------------------------------Index approve start -------------------------------->

		<c:if test="${approvePlan}">
			<script>
				builder.register('Approve', function() {
					return createNode('计划指标审核')
						.append(createNode('全年计划指标审核', "approve/v2/zb.do?approveType=0"))
						.append(createNode('季度-月度末计划值审核', "approve/v2/zb.do?approveType=1"));
				});
			</script>
		</c:if>

		<c:if test="${approvePredict}">
			<script>
				builder.register('Approve', function() {
					return createNode('预计/实际指标审核')
						.append(createNode('20预计指标审核', "approve/v2/zb.do?approveType=2"))
						.append(createNode('28号预计指标审核', "approve/v2/zb.do?approveType=3"))
						.append(createNode('实际指标审核', "approve/v2/zb.do?approveType=4"));
				});
			</script>
		</c:if>

		<!--------------------------------------------------------Index approve end -------------------------------->
		<%--      <ul id="navlist3" style="padding: 10px 0; display: none">
	        <li style="background-color: transparent; diplay: none"><i
	            class="ec-icon ec-icon-force"></i> <a href="#finincial"
	            style="color: rgb(62, 152, 197);">财务指标汇总</a></li>
	    </ul> --%>

		<c:if test="${ComGbEntry}">
			<script>
				builder.register('entryxx', function() {
					return createNode('通用经营管报录入')
						.append(builder.build('YszkgbEntry'))
						.append(builder.build('ChgbEntry'));
				});
			</script>
			<c:if test="${YszkgbEntry}">
				<script>
					builder.register('YszkgbEntry', function () {
						return [
						   createNode('应收账款管报录入', "yszkgb/v2/entry.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${ChgbEntry}">
				<script>
					builder.register('ChgbEntry', function() {
						return [
						   createNode('存货管报录入', "chgb/v2/entry.do")
						];
					});
				</script>
			</c:if>
		</c:if>
		<c:if test="${SbdgbEntry}">
			<script>
				builder.register('entryxx', function() {
					return createNode('输变电产业经营管报录入')
						.append(createNode('未履约订单情况录入', "wlydd/v2/entry.do"))
						.append(createNode('输变电产值/产量完成情况录入', "sbdczclwcqk/v2/entry.do"))
						.append(createNode('输变电市场签约情况录入', "sbdscqyqk/v2/entry.do"))
						.append(createNode('材料使用情况录入', "wgcpqk/v2/entry.do"));
				});
			</script>
		</c:if>
		<c:if test="${NygbEntry}">
			<script>
	        	builder.register('entryxx', function() {
					return createNode('能源产业经营报表录入')
						.append(createNode('成本分析录入', 'cbfx/v2/entry.do'))
						.append(builder.build('nyzbscqk'));
				});
			</script>
		</c:if>

		<c:if test="${NYzbscqkEntry}">
			<script>
	        	builder.register('nyzbscqk', function() {
					return createNode('能源-周边市场情况录入', "nyzbscqk/v2/entry.do");
				});
			</script>
		</c:if>

		<c:if test="${XnygbEntry}">
			<script>
	        	builder.register('bbxx', function() {
					return createNode('新能源产业经营报表录入')
						.append(createNode('新能源存货录入', 'xnychFrame/v2/entry.do'));
				});
			</script>
		</c:if>


		<c:if test="${FinanceEntry}">
			<script>
	         	builder.register('entryxx', function() {
					return createNode('财务报表录入')
						.append(createNode('财务-经营性现金流录入', 'cwgbjyxxjl/v2/entry.do'));
				});
			</script>
		</c:if>

		<c:if test="${QualityLookup || QualityApprove}">
			<script>
				builder.register('quality', function() {
					return createNode('质量数据管控')
						.append(builder.build('ZlLookup'))
						.append(builder.build('ZlApprove'));
				});
			</script>
			<c:if test="${!QualityApprove}">
				<script>
					builder.register('ZlLookup', function () {
						return [
						   createNode('产品一次送试及原材料合格率汇总及分析', "cpzlqk/v2/show.do"),
						   createNode('内外部质量问题汇总及分析', "nwbzlqk/v2/show.do")
						];
					});
				</script>
			</c:if>
			<c:if test="${QualityApprove}">
				<script>
					builder.register('ZlApprove', function () {
						return [
						   createNode('产品一次送试及原材料合格率审核及上报', "cpzlqk/v2/approve.do"),
						   createNode('内外部质量问题审核及上报', "nwbzlqk/v2/approve.do")
						];
					});
				</script>
			</c:if>
		</c:if>
	</c:otherwise>
</c:choose>
<c:if test="${entryPlan}">
	<script>
					builder.register('entryxxRoot', function () {
						var entryxx = createNode("数据录入");
						entryxx.data.icon = "fa fa-pencil-square-o";
						entryxx.data.iconOpen = undefined;	
						entryxx.append(builder.build("entryxx"));
						return entryxx;
					});
				</script>
</c:if>

<c:if test="${approvePlan}">
	<script>
					builder.register('ApprovexxRoot', function () {
						var Approve = createNode("数据审核");
						Approve.data.icon = "fa fa-check-square-o";
						Approve.data.iconOpen = undefined;	
						Approve.append(builder.build("Approve"));
						return Approve;
					});
				</script>
</c:if>

<c:if test="${QualityAuth}">
	<script>
					builder.register('qulityxxRoot', function () {
						var quality = createNode("质量数据管控");
						quality.data.icon = "fa fa-cogs";
						quality.data.iconOpen = undefined;
						quality.data.extracted = true;
						quality.append(builder.build("quality"));
						return quality;
					});
				</script>
</c:if>

<c:if test="${_77}">
	<script>
					builder.register('jyjsc', function () {
						var jyjsc = createNode("经营驾驶舱");
						jyjsc.data.icon = "fa fa-bar-chart";
						jyjsc.data.iconOpen = undefined;
						jyjsc.append(createNode("经营驾驶舱", "dashboard/dashboard.do"));
						return jyjsc;
					});
				</script>
</c:if>

<script>
	function createNode(value, url, icon, iconOpen){
	    if (url != undefined){
	        url = '${pageContext.request.contextPath}/' + url;
	        icon = icon ? icon : "fa fa-dot-circle-o";
	    	iconOpen = iconOpen ? iconOpen : "fa fa-dot-circle-o";	        
	    }else{
	    	icon = icon ? icon : "fa fa-plus-square-o";
	    	iconOpen = iconOpen ? iconOpen : "fa fa-minus-square-o";
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

		var root = createNode();
	/* 	var ztfx = createNode("主题分析");
		ztfx.data.icon = "fa fa-bar-chart";
		ztfx.data.iconOpen = undefined; */
		
		root.append(builder.build("glkzt"));
		
		var bbxx = [];	

		var subNodes = builder.build('bbxx');
		if (subNodes.length > 0){
			bbxx = createNode("报表信息");
			bbxx.data.extracted = true;
			bbxx.data.icon = "fa fa-table";
			bbxx.data.iconOpen = undefined;
			bbxx.append(subNodes);	
		}
		
		root.append(builder.build("jyjsc"))
			.append(bbxx)
			.append(builder.build("entryxxRoot"))
			.append(builder.build("ApprovexxRoot"))
			.append(builder.build("qulityxxRoot"))
		return root.subNodes;
	});
</script>
