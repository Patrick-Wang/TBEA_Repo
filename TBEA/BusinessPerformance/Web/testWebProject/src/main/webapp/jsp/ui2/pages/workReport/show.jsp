<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
<meta charset="utf-8" />
<title></title>


<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<%@include file="../../ie8-t.jsp"%>

<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jquery-1.12.3.js"></script>

<!--Basic Styles-->
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/bootstrap.min.css"
	rel="stylesheet" />

<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/font-awesome.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/weather-icons.min.css"
	rel="stylesheet" />


<!--Beyond styles-->
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/beyond.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/demo.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/typicons.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/animate.min.css"
	rel="stylesheet" />
<link id="skin-link" href="" rel="stylesheet" type="text/css" />

<!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
<script
	src="${pageContext.request.contextPath}/jsp/ui2/assets/js/skins.min.js"></script>


<!-- jquery ui -->
<!-- jquery ui gray -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/jquery-ui-1.11.1.custom/jquery-ui.js"></script>
<!-- jquery ui blue -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/redmond/jquery-ui-custom.css">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery-ui-custom.min.js"
	type="text/javascript"></script>

<!-- 多选菜单 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/assets/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/assets/prettify.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/assets/prettify.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jsp/ui2/pages/multi-select/jquery.multiselect.js"></script>
<link
	href="${pageContext.request.contextPath}/jsp/ui2/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- jedate -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/skin/jedate.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/skin/deepgreen.css">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jedate/jquery.jedate.js"
	type="text/javascript"></script>
<!-- jqgrid -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/ui.jqgrid.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/themes/ui.multiselect.css">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.tablednd.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.contextmenu.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/i18n/grid.locale-cn.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.layout.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/js/jquery.jqGrid.js"
	type="text/javascript"></script>

	<link
	href="${pageContext.request.contextPath}/jsp/ui2/pages/components/select2/css/select2.css"
	rel="stylesheet">
	<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/components/select2/js/select2.js"
	type="text/javascript"></script>
	<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/components/select2/js/i18n/zh-CN.js"
	type="text/javascript"></script>
<!-- jqgrid assist -->
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/jqassist.js"
	type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/jsp/ui2/pages/json2.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/pages/util.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/jqgrid/vector.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/dateSelector.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/unitedSelector.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/companySelector.js"
	type="text/javascript"></script>

<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jqueryex.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/jsp/ui2/ui2.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/scroll/css/jquery.mCustomScrollbar.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/jsp/ui2/scroll/js/jquery.mCustomScrollbar.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/route/route.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/basic/basicdef.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/basic/basic.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/basic/basicShow.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/framework/templates/singleDateReport/show.js"></script>
    <script src="${pageContext.request.contextPath}/jsp/ui2/pages/workReport/show.js?ver=1.0"></script>
<%@include file="../../ie8-b.jsp"%>
    <script
            src="${pageContext.request.contextPath}/jsp/ui2/pages/www2/js/echarts-plain-2-0-0.js"></script>
    <style>
		.Png-2 {width: 450px; height: 400px; visibility:hidden; }
		.Png-1 {width: 900px; height: 400px; visibility:hidden;}
        .well{
            position: absolute;
            float: left;
            left: -999999px;
            top: -99999px;
        }
	</style>
</head>
<!-- /Head -->
<!-- Body -->
<body>
	<!-- Main Container -->
	<div class="main-container container-fluid">
		<!-- Page Container -->
		<div class="page-container">

			<!-- Page Content -->
			<div class="page-content">
				<!-- Page Breadcrumb -->
				<div class="page-breadcrumbs">
					<ul class="breadcrumb">
					</ul>
				</div>
				<!-- /Page Breadcrumb -->

				<!-- Page Header -->
				<div class="page-header position-relative">
					<div class="header-title">
						<div id="headerHost" class="pull-left">
							<div class="workinput pull-left ${nodate != 'true' ? '' : 'hidden'}">
								<input id="grid-date" type="text" readonly="readonly"><i
									class="fa fa-calendar"></i>
							</div>
							<div id="sels" class="pull-left">
								<div id="item-sel1" class="pull-left"></div>
								<div id="item-sel2" class="pull-left"></div>
							</div>
							<div id="grid-update" class="btn btn-default" style="width: 145px;"
								onclick="framework.router.to(framework.templates.singleDateReport.FRAME_ID).send(framework.templates.singleDateReport.FE_UPDATE)">
								生成经营分析报告 <i class="fa fa-file-word-o"></i>
							</div>

							<form id="myform" name="myform" method="post" onsubmit="return sumbitTest()" action="allpng.do">
								<input id="lrzbPng" name="lrzbPng" type="hidden" value="" />
								<input id="srzbPng" name="srzbPng" type="hidden" value="" />
								<input id="bhqs_lrPng" name="bhqs_lrPng" type="hidden" value="" />
								<input id="bhqs_srPng" name="bhqs_srPng" type="hidden" value="" />
								<input id="bhqs_xslrPng" name="bhqs_xslrPng" type="hidden" value="" />
								<input id="xjl_lrPng" name="xjl_lrPng" type="hidden" value="" />
								<input id="xjl_lcPng" name="xjl_lcPng" type="hidden" value="" />
								<input id="yszk_zlPng" name="yszk_zlPng" type="hidden" value="" />
								<input id="yqk_ztPng" name="yqk_ztPng" type="hidden" value="" />
								<input id="yqk_zxPng" name="yqk_zxPng" type="hidden" value="" />
								<input id="yqk_ysPng" name="yqk_ysPng" type="hidden" value="" />
								<input id="yszk_zmyePng" name="yszk_zmyePng" type="hidden" value="" />
								<input id="yszk_sxzbPng" name="yszk_sxzbPng" type="hidden" value="" />
								<input id="ch_zlbhPng" name="ch_zlbhPng" type="hidden" value="" />
								<input id="ch_xzPng" name="ch_xzPng" type="hidden" value="" />
								<input id="zjhl_ywPng" name="zjhl_ywPng" type="hidden" value="" />
								<input id="zjhl_hkfsPng" name="zjhl_hkfsPng" type="hidden" value="" />
								<input id="djqy_bhqsPng" name="djqy_bhqsPng" type="hidden" value="" />
								<input id="qyqk_scqsPng" name="qyqk_scqsPng" type="hidden" value="" />
								<input id="cyqy_kVPng" name="cyqy_kVPng" type="hidden" value="" />
								<input id="cyqy_cpPng" name="cyqy_cpPng" type="hidden" value="" />
								<input id="czzb_wcqkPng" name="czzb_wcqkPng" type="hidden" value="" />
								<input id="fyzk_qsbdPng" name="fyzk_qsbdPng" type="hidden" value="" />
							</form>

						</div>
						<form id="exportForm" style="display: none" method="post"></form>
					</div>
				</div>
				<!-- /Page Header -->
				<!-- Page Body -->
				<div class="page-body">
					<div class="col-lg-12 col-sm-12 col-xs-12">
						<div class="well">
							<div id="table"></div>
							<div id="warning" class="hidden">暂时没有数据</div>
							<%--共有--%>
							<div id="lrzb" class="Png-2" ></div><%--利润指标--%>
							<div id="srzb" class="Png-2"></div><%--收入指标--%>
							<div id="bhqs_lr" class="Png-1"></div><%--利润变化趋势--%>
							<div id="bhqs_sr" class="Png-1"></div><%--收入变化趋势--%>
							<div id="bhqs_xslr" class="Png-1" ></div><%--销售利润率变化趋势--%>
							<div id="xjl_lr" class="Png-1"></div><%--现金流流入--%>
							<div id="xjl_lc" class="Png-1"></div><%--现金流流出--%>
							<div id="ch_xz" class="Png-1"></div><%--存货性质情况--%>
							<div id="zjhl_yw" class="Png-1"></div><%--资金回笼--%>
							<div id="zjhl_hkfs" class="Png-1"></div><%--回款方式--%>
							<div id="djqy_bhqs" class="Png-1"></div><%--单机签约方式--%>
							<div id="fyzk_qsbd" class="Png-1"></div><%--三项费用管控--%>
							<%--共有--%>
							<%--byq独有--%>
							<div id="yszk_zl" class="Png-1"></div><%--应收账款账龄--%>
							<div id="yqk_zt" class="Png-1"></div><%--应收账款款项性质情况整体--%>
							<div id="yqk_zx" class="Png-1"></div><%--逾期款专项--%>
							<div id="yqk_ys" class="Png-1"></div><%--逾期应收账产生因素--%>
							<div id="yszk_zmye" class="Png-1"></div><%--账面余额的变化趋势--%>
							<div id="yszk_sxzb" class="Png-1"></div><%--三项指标变化趋势--%>
							<div id="ch_zlbh" class="Png-1"></div><%--存货账龄变化情况--%>
							<div id="qyqk_scqs" class="Png-1"></div><%--市场签约情况--%>
							<div id="cyqy_kV" class="Png-1" ></div><%--产品签约情况及趋势--%>
							<div id="cyqy_cp" class="Png-1" ></div><%--对非晶合金、卷铁芯产品签约进行展示--%>
							<div id="czzb_wcqk" class="Png-1" ></div></div><%--产值情况--%>
							<%--byq独有--%>
							<%--xl独有--%>
							<div id="qz_tlyl" class="Png-1" ></div><%--铜铝用量--%>
							<%--xl独有--%>
						</div>
					</div>
				</div>
				<!-- /Page Body -->
			</div>
			<!-- /Page Content -->
		</div>
		<!-- /Page Container -->
		<!-- Main Container -->
	</div>
	<!--Basic Scripts-->

	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/slimscroll/jquery.slimscroll.min.js"></script>

	<!--Beyond Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/beyond.min.js"></script>
	<!--Page Related Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/bootbox/bootbox.js"></script>

	<%@include file="../loading.jsp"%>
	<script>
		Util.Breadcrumb.render(JSON.parse('${param.breads}'));
		if (Util.isIframe()) {
			Util.Breadcrumb.setOnClickListener(function(breadNode) {
				window.parent['onClickBreadcrumb']
				&& window.parent['onClickBreadcrumb'](breadNode);
			});
		}
		Util.Loading.DEFAULT_TEXT = "正在生成经营分析报告，内容较多，请耐心等候...";
		$(document).ready(function () {
	        framework.templates.dateReport.create();

	        var dateEnd;
	        var date;
	        if ('${date}' == ""){
	        	date = Util.parseDate('${year}', '${month}', '${day}');
	        }else{
	            date = Util.toDate('${date}');
	            if ('${dateEnd}' != ''){
				    dateEnd = Util.toDate('${dateEnd}');
	            }
	        }

	        var nd = '${nodeData}';
	        var nd2 = '${nodeData2}';
	        var ind = nd == '' ? undefined : JSON.parse(nd);
	        var ind2 = nd2 == '' ? undefined : JSON.parse(nd2);

	        framework.router.to(framework.templates.singleDateReport.FRAME_ID).send(framework.basic.FrameEvent.FE_INIT_EVENT,{
	            dtId:"grid-date",
	            date: date,
	            dateEnd:dateEnd,
	            host:"table",
	            title:"${title}",
	            asSeason:"${asSeason}" == "true",
	            asSeasonAcc:"${asSeasonAcc}" == "true",
	            jdName:'${jdName}' == "" ? undefined : JSON.parse('${jdName}'),
	            updateUrl:"${updateUrl}.do",
	            exportUrl:"${exportUrl}.do",
                dataUrl:"${dataUrl}.do",
	            itemId:"item-sel1",
	            itemNodes: ind,
				searchlist: '${searchlist}',
	            itemId2:"item-sel2",
	            itemNodes2: ind2
	        });



            // var dataArr1 = $("#grid-date").val().split("年");
            // var timeDate = dataArr1[0] + "-" + dataArr1[1].split("月")[0] + "-01";
            // var selectObj = $("#item-sel1").find("select");
            // var LRZB = new Array();
            // var ZbSj ;
            // var bhqs_lr = new Array() ;
            // var bhqs_sr = new Array() ;
            // var bhqs_xslr = new Array() ;
            // var bhqs_lr_12 ; var bhqs_sr_12 ; var bhqs_xslr_12 ;
            // var xjl_lr_1 ; var xjl_lr_2 ; var xjl_lr_3;
            // var xjl_lc_1 ; var xjl_lc_2 ; var xjl_lc_3;
            // var yszk_zl_1 = new Array() ; var yszk_zl_2 = new Array() ; var yszk_zl_3 = new Array() ; var yszk_zl_4 = new Array() ; var yszk_zl_5 = new Array() ; var yszk_zl_6 = new Array() ;
            // var yqk_zx_1 = new Array() ; var yqk_zx_2 = new Array() ; var yqk_zx_3 = new Array() ; var yqk_zx_4 = new Array() ; var yqk_zx_5 = new Array() ;
            // var yqk_zt_1 = new Array(); var yqk_zt_2 = new Array();var yqk_zt_3 = new Array();
            // var yqk_ys_sum =[ 0 , 0, 0 ,0 ,0 ,0 ,0] ; var yqk_ys_sum2 = new Array() ; var yqk_ys_1 = new Array() ; var yqk_ys_2 = new Array() ; var yqk_ys_3 = new Array() ; var yqk_ys_4 = new Array() ;
            // var yszk_zmye_1 = new Array() ; var yszk_zmye_2 = new Array() ; var yszk_sxzb_1 = new Array() ; var yszk_sxzb_2 = new Array() ; var yszk_sxzb_3 = new Array() ;
            // var ch_zlbh_1 = new Array(); var ch_zlbh_2 = new Array(); var ch_zlbh_3 = new Array(); var ch_zlbh_4 = new Array(); var ch_zlbh_5 = new Array(); var ch_zlbh_6 = new Array();
            // var ch_xz_1 = new Array(); var ch_xz_2 = new Array(); var ch_xz_3 = new Array();
            // var zjhl_yw_1 ; var zjhl_yw_2 ;
            // var zjhl_hkfs_1 ; var zjhl_hkfs_2; var zjhl_hkfs_3 ;
            // var djqy_bhqs_1 ;
            // var qyqk_scqs_1 =[ 0 , 0, 0 ,0 ,0 ,0 ,0 , 0 , 0, 0 ,0 , 0 , 0] ; var qyqk_scqs_2 = [ 0 , 0, 0 ,0 ,0 ,0 ,0 , 0 , 0, 0 ,0 , 0 , 0];var qyqk_scqs_3 = [ 0 , 0, 0 ,0 ,0 ,0 ,0 , 0 , 0, 0 ,0 , 0 , 0];var qyqk_scqs_4 = [ 0 , 0, 0 ,0 ,0 ,0 ,0 , 0 , 0, 0 ,0 , 0 , 0];
            // var cyqy_kV_1 ; var cyqy_kV_2 ; var cyqy_kV_3 ; var cyqy_cp_1 ; var cyqy_cp_2 ;
            // var czzb_wcqk_1 ; var czzb_wcqk_2 ; var czzb_wcqk_3 ; var czzb_wcqk_4 ; var czzb_wcqk_5 ; var czzb_wcqk_6 ; var czzb_wcqk_7 ; var czzb_wcqk_8 ;
            // var fyzk_qsbd_1 ; var fyzk_qsbd_2 ; var fyzk_qsbd_3 ;var qz_tlyl_1 ; var qz_tlyl_2 ;
            // //利润收入指标占比月份数据
            // jQuery.ajax({
            //     url: "operShuju.do",
            //     type: "get",
            //     data: {
            //         item: selectObj.val(),
            //         date: timeDate
            //     },
            //     dataType: "json",
            //     async: false,
            //     success: function(Douw){
            //         LRZB.push(Douw.lrsr[1][11]);
            //         LRZB.push(Douw.lrsr[2][11]);
            //         LRZB.push(Douw.lrsr[3][11]);
            //         LRZB.push(Douw.lrsr[4][11]);
            //         LRZB.push(Douw.lrsr[5][11]);
            //         LRZB.push(Douw.lrsr[7][11]);
            //         LRZB.push(Douw.lrsr[8][11]);
            //         LRZB.push(Douw.lrsr[9][11]);
            //         LRZB.push(Douw.lrsr[10][11]);
            //         ZbSj = Douw.zbsj[1] ;
            //         for(var lrw = 0 ; lrw < Douw.bhqs_lr.length ; lrw++)
				// 	{
            //             for(var lr = 0 ; lr < Douw.bhqs_lr[lrw].length ; lr++)
            //             {
            //                 if(Douw.bhqs_lr[lrw][lr] == "--")
            //                 {
            //                     Douw.bhqs_lr[lrw][lr] =  "0.0" ;
            //                 }
            //             }
				// 	}
            //         bhqs_lr.push(Douw.bhqs_lr[0]);
            //         bhqs_sr.push(Douw.bhqs_lr[6]);
            //         bhqs_xslr.push(Douw.bhqs_lr[11]);
				// 	for(var xs = 0 ; xs < bhqs_xslr[0].length ; xs++)
				// 	{
				// 	    var xslr = bhqs_xslr[0][xs] ;
				// 	    var w = RegExp(/%/) ;
				// 	    if(w.exec(xslr) )
				// 		{
            //                 bhqs_xslr[0][xs] = xslr.replace(w,"");
				// 		}
				// 	}
            //         bhqs_lr_12 = bhqs_lr[0] ;
            //         bhqs_sr_12 = bhqs_sr[0] ;
            //         bhqs_xslr_12 = bhqs_xslr[0] ;
            //         for(var xlrlc = 0 ; xlrlc < Douw.xjl_lrlc.length ; xlrlc++)
            //         {
            //             for(var xlr = 0 ; xlr < Douw.xjl_lrlc[xlrlc].length ; xlr++)
            //             {
            //                 if(Douw.xjl_lrlc[xlrlc][xlr] == "--")
            //                 {
            //                     Douw.xjl_lrlc[xlrlc][xlr] = "0.0" ;
            //                 }
            //
            //             }
            //         }
            //         xjl_lr_1 = Douw.xjl_lrlc[0] ; xjl_lc_1 = Douw.xjl_lrlc[11] ;
            //         xjl_lr_2 = Douw.xjl_lrlc[1] ; xjl_lc_2 = Douw.xjl_lrlc[12] ;
            //         xjl_lr_3 = Douw.xjl_lrlc[2] ; xjl_lc_3 = Douw.xjl_lrlc[13] ;
            //         for(var yzl = 0 ; yzl < Douw.yszk_zl.length ; yzl++)
            //         {
            //             for(var zl = 0 ; zl < Douw.yszk_zl[yzl].length ; zl++)
            //             {
            //                 if(Douw.yszk_zl[yzl][zl] == "--")
            //                 {
            //                     Douw.yszk_zl[yzl][zl] =  "0.0" ;
            //                 }
            //             }
            //             yszk_zl_1.push(Douw.yszk_zl[yzl][0]);
            //             yszk_zl_2.push(Douw.yszk_zl[yzl][1]);
            //             yszk_zl_3.push(Douw.yszk_zl[yzl][2]);
            //             yszk_zl_4.push(Douw.yszk_zl[yzl][3]);
            //             yszk_zl_5.push(Douw.yszk_zl[yzl][4]);
            //             yszk_zl_6.push(Douw.yszk_zl[yzl][5]);
            //         }
            //         for(var yqwd = 0 ; yqwd < Douw.yqkzx_yqwd.length ; yqwd++)
            //         {
            //             for(var qwd = 0 ; qwd < Douw.yqkzx_yqwd[yqwd].length ; qwd++)
            //             {
            //                 if(Douw.yqkzx_yqwd[yqwd][qwd] == "--")
            //                 {
            //                     Douw.yqkzx_yqwd[yqwd][qwd] =  "0.0" ;
            //                 }
            //             }
            //             yqk_zx_1.push(Douw.yqkzx_yqwd[yqwd][0]);
            //             yqk_zx_2.push(Douw.yqkzx_yqwd[yqwd][1]);
            //             yqk_zx_3.push(Douw.yqkzx_yqwd[yqwd][2]);
            //             yqk_zx_4.push(Douw.yqkzx_yqwd[yqwd][3]);
            //             yqk_zx_5.push(Douw.yqkzx_yqwd[yqwd][4]);
            //             yqk_zt_1.push(Douw.yqkzx_yqwd[yqwd][5]);
            //             yqk_zt_2.push(Douw.yqkzx_yqwd[yqwd][6]);
            //             yqk_zt_3.push(Douw.yqkzx_yqwd[yqwd][7]);
            //         }
            //         for(var yqwd = 0 ; yqwd < Douw.yqk_ys.length ; yqwd++)
            //         {
            //             for(var qwd = 0 ; qwd < Douw.yqk_ys[yqwd].length ; qwd++)
            //             {
            //                 if(Douw.yqk_ys[yqwd][qwd] == "--")
            //                 {
            //                     Douw.yqk_ys[yqwd][qwd] =  "0.0" ;
            //                 }
            //             }
            //             yqk_ys_sum2[0] = yqk_ys_sum[0] = yqk_ys_sum[0] + parseFloat(Douw.yqk_ys[yqwd][0]) ;
            //             yqk_ys_sum2[1] = yqk_ys_sum[1] = yqk_ys_sum[1] + parseFloat(Douw.yqk_ys[yqwd][1]) ;
            //             yqk_ys_sum2[2] = yqk_ys_sum[2] = yqk_ys_sum[2] + parseFloat(Douw.yqk_ys[yqwd][2]) ;
            //             yqk_ys_sum2[3] = yqk_ys_sum[3] = yqk_ys_sum[3] + parseFloat(Douw.yqk_ys[yqwd][3]) ;
            //             yqk_ys_sum2[4] = yqk_ys_sum[4] = yqk_ys_sum[4] + parseFloat(Douw.yqk_ys[yqwd][4]) ;
            //             yqk_ys_sum2[5] = yqk_ys_sum[5] = yqk_ys_sum[5] + parseFloat(Douw.yqk_ys[yqwd][5]) ;
            //             yqk_ys_sum2[6] = yqk_ys_sum[6] = yqk_ys_sum[6] + parseFloat(Douw.yqk_ys[yqwd][6]) ;
            //         }
            //         for(var su = 0 ; su < yqk_ys_sum.length-1 ; su++ )
				// 	{
				// 	    for(var su2 = 0 ; su2 < yqk_ys_sum.length-1-su ; su2++ )
				// 		{
				// 		    if(yqk_ys_sum[su2]<yqk_ys_sum[su2+1])
				// 			{
            //                     var temp = yqk_ys_sum[su2];
            //                     yqk_ys_sum[su2] = yqk_ys_sum[su2+1];
            //                     yqk_ys_sum[su2+1] = temp;
				// 			}
				// 		}
				// 	}
				// 	for(var sum2 = 0 ; sum2 < yqk_ys_sum2.length ; sum2++ )
				// 	{
				// 	    if(yqk_ys_sum[0]==yqk_ys_sum2[sum2])
				// 		{
            //                 for(var yqwd = 0 ; yqwd < Douw.yqk_ys.length ; yqwd++)
            //                 {
            //                     yqk_ys_1.push(Douw.yqk_ys[yqwd][sum2]);
            //                 }
				// 		}
            //             else if(yqk_ys_sum[1]==yqk_ys_sum2[sum2])
            //             {
            //                 for(var yqwd = 0 ; yqwd < Douw.yqk_ys.length ; yqwd++)
            //                 {
            //                     yqk_ys_2.push(Douw.yqk_ys[yqwd][sum2]);
            //                 }
            //             }
            //             else if(yqk_ys_sum[2]==yqk_ys_sum2[sum2])
            //             {
            //                 for(var yqwd = 0 ; yqwd < Douw.yqk_ys.length ; yqwd++)
            //                 {
            //                     yqk_ys_3.push(Douw.yqk_ys[yqwd][sum2]);
            //                 }
            //             }
            //             else if(yqk_ys_sum[3]==yqk_ys_sum2[sum2])
            //             {
            //                 for(var yqwd = 0 ; yqwd < Douw.yqk_ys.length ; yqwd++)
            //                 {
            //                     yqk_ys_4.push(Douw.yqk_ys[yqwd][sum2]);
            //                 }
            //             }
				// 	}
            //         for(var ysj = 0 ; ysj < Douw.yszk_sj.length ; ysj++)
            //         {
            //             for(var sj = 0 ; sj < Douw.yszk_sj[ysj].length ; sj++)
            //             {
            //                 if(Douw.yszk_sj[ysj][sj] == "--")
            //                 {
            //                     Douw.yszk_sj[ysj][sj] = "0.0" ;
            //                 }
            //             }
            //             yszk_zmye_1.push(Douw.yszk_sj[ysj][0]);
            //             yszk_zmye_2.push(Douw.yszk_sj[ysj][7]);
            //             yszk_sxzb_1.push(Douw.yszk_sj[ysj][2]);
            //             yszk_sxzb_2.push(Douw.yszk_sj[ysj][3]);
            //             yszk_sxzb_3.push(Douw.yszk_sj[ysj][4]);
            //         }
            //         for(var yzlbh = 0 ; yzlbh < Douw.ch_zlbh.length ; yzlbh++)
            //         {
            //             for(var zlbh = 0 ; zlbh < Douw.ch_zlbh[yzlbh].length ; zlbh++)
            //             {
            //                 if(Douw.ch_zlbh[yzlbh][zlbh] == "--")
            //                 {
            //                     Douw.ch_zlbh[yzlbh][zlbh] = "0.0" ;
            //                 }
            //             }
            //             ch_zlbh_1.push(Douw.ch_zlbh[yzlbh][0]);
            //             ch_zlbh_2.push(Douw.ch_zlbh[yzlbh][1]);
            //             ch_zlbh_3.push(Douw.ch_zlbh[yzlbh][2]);
            //             ch_zlbh_4.push(Douw.ch_zlbh[yzlbh][3]);
            //             ch_zlbh_5.push(Douw.ch_zlbh[yzlbh][4]);
            //             ch_zlbh_6.push(Douw.ch_zlbh[yzlbh][5]);
            //
            //         }
            //         for(var cxz = 0 ; cxz < Douw.ch_xz.length ; cxz++)
            //         {
            //             for(var xz = 0 ; xz < Douw.ch_xz[cxz].length ; xz++)
            //             {
            //                 if(Douw.ch_xz[cxz][xz] == "--")
            //                 {
            //                     Douw.ch_xz[cxz][xz] = "0.0" ;
            //                 }
            //             }
            //             ch_xz_1.push(Douw.ch_xz[cxz][0]);
            //             ch_xz_2.push(Douw.ch_xz[cxz][1]);
            //             ch_xz_3.push(Douw.ch_xz[cxz][2]);
            //         }
            //         for(var zyw = 0 ; zyw < Douw.zjhl_yw.length ; zyw++)
            //         {
            //             for(var yw = 0 ; yw < Douw.zjhl_yw[zyw].length ; yw++)
            //             {
            //                 if(Douw.zjhl_yw[zyw][yw] == "--")
            //                 {
            //                     Douw.zjhl_yw[zyw][yw] = "0.0" ;
            //                 }
            //             }
            //         }
            //         zjhl_yw_1 = Douw.zjhl_yw[0] ;
            //         zjhl_yw_2 = Douw.zjhl_yw[5] ;
            //         for(var zhkfs = 0 ; zhkfs < Douw.zjhl_hkfs.length ; zhkfs++)
            //         {
            //             for(var hkfs = 0 ; hkfs < Douw.zjhl_hkfs[zhkfs].length ; hkfs++)
            //             {
            //                 if(Douw.zjhl_hkfs[zhkfs][hkfs] == "--")
            //                 {
            //                     Douw.zjhl_hkfs[zhkfs][hkfs] = "0.0" ;
            //                 }
            //             }
            //         }
            //         zjhl_hkfs_1 = Douw.zjhl_hkfs[1] ;
            //         zjhl_hkfs_2 = Douw.zjhl_hkfs[2] ;
            //         zjhl_hkfs_3 = Douw.zjhl_hkfs[3] ;
            //         for(var dbhqs = 0 ; dbhqs < Douw.djqy_bhqs.length ; dbhqs++)
            //         {
            //             for(var bhqs = 0 ; bhqs < Douw.djqy_bhqs[dbhqs].length ; bhqs++)
            //             {
            //                 if(Douw.djqy_bhqs[dbhqs][bhqs] == "--")
            //                 {
            //                     Douw.djqy_bhqs[dbhqs][bhqs] = "0.0" ;
            //                 }
            //             }
            //         }
            //         djqy_bhqs_1 = Douw.djqy_bhqs[0];
            //         for(var qscqs = 0 ; qscqs < Douw.qyqk_scqs.length ; qscqs++)
            //         {
            //             for(var scqs = 0 ; scqs < Douw.qyqk_scqs[qscqs].length ; scqs++)
            //             {
            //                 if(Douw.qyqk_scqs[qscqs][scqs] == "--")
            //                 {
            //                     Douw.qyqk_scqs[qscqs][scqs] = "0.0" ;
            //                 }
            //             }
            //         }
            //         for(var scqs = 0 ; scqs < Douw.qyqk_scqs[0].length ; scqs++ )
				// 	{
            //             qyqk_scqs_1[scqs] =  qyqk_scqs_1[scqs] + parseFloat(Douw.qyqk_scqs[0][scqs]) + parseFloat(Douw.qyqk_scqs[1][scqs]) + parseFloat(Douw.qyqk_scqs[2][scqs]) + parseFloat(Douw.qyqk_scqs[3][scqs]);
            //             qyqk_scqs_2[scqs] =  qyqk_scqs_2[scqs] + parseFloat(Douw.qyqk_scqs[4][scqs]) + parseFloat(Douw.qyqk_scqs[5][scqs]) + parseFloat(Douw.qyqk_scqs[6][scqs]) ;
            //             qyqk_scqs_3[scqs] =  qyqk_scqs_3[scqs] + parseFloat(Douw.qyqk_scqs[7][scqs]) + parseFloat(Douw.qyqk_scqs[8][scqs]) + parseFloat(Douw.qyqk_scqs[9][scqs]) + parseFloat(Douw.qyqk_scqs[10][scqs]) + parseFloat(Douw.qyqk_scqs[11][scqs]) + parseFloat(Douw.qyqk_scqs[12][scqs]);
            //             qyqk_scqs_4[scqs] =  qyqk_scqs_4[scqs] + parseFloat(Douw.qyqk_scqs[13][scqs]) + parseFloat(Douw.qyqk_scqs[14][scqs]);
				// 	}
            //         for(var ckv = 0 ; ckv < Douw.cyqy_kV.length ; ckv++)
            //         {
            //             for( var kv = 0 ; kv < Douw.cyqy_kV[ckv].length ; kv++)
            //             {
            //                 if(Douw.cyqy_kV[ckv][kv] == "--")
            //                 {
            //                     Douw.cyqy_kV[ckv][kv] = "0.0" ;
            //                 }
            //             }
            //         }
            //         cyqy_kV_1 = Douw.cyqy_kV[3] ;
            //         cyqy_kV_2 = Douw.cyqy_kV[4] ;
            //         cyqy_kV_3 = Douw.cyqy_kV[5] ;
            //         cyqy_cp_1 = Douw.cyqy_kV[25] ;
            //         cyqy_cp_2 = Douw.cyqy_kV[26] ;
            //         for(var cwcqk = 0 ; cwcqk < Douw.czzb_wcqk.length ; cwcqk++)
				//     {
				//        for( var wcqk = 0 ; wcqk < Douw.czzb_wcqk[cwcqk].length ; wcqk++)
				// 	   {
            //                if(Douw.czzb_wcqk[cwcqk][wcqk] == "--")
            //                {
            //                    Douw.czzb_wcqk[cwcqk][wcqk] = "0.0" ;
            //                }
				// 	   }
				//     }
            //         czzb_wcqk_1 = Douw.czzb_wcqk[0] ;
            //         czzb_wcqk_2 = Douw.czzb_wcqk[8] ;
            //         czzb_wcqk_3 = Douw.czzb_wcqk[14] ;
            //         czzb_wcqk_4 = Douw.czzb_wcqk[19] ;
            //         czzb_wcqk_5 = Douw.czzb_wcqk[23] ;
            //         czzb_wcqk_6 = Douw.czzb_wcqk[28] ;
            //         czzb_wcqk_7 = Douw.czzb_wcqk[33] ;
            //         czzb_wcqk_8 = Douw.czzb_wcqk[39] ;
            //         for(var fqsbd = 0 ; fqsbd < Douw.fyzk_qsbd.length ; fqsbd++)
            //         {
            //             for(var qsbd = 0 ; qsbd < Douw.fyzk_qsbd[fqsbd].length ; qsbd++)
            //             {
            //                 if(Douw.fyzk_qsbd[fqsbd][qsbd] == "--")
            //                 {
            //                     Douw.fyzk_qsbd[fqsbd][qsbd] = "0.0" ;
            //                 }
            //             }
            //         }
            //         fyzk_qsbd_1 = Douw.fyzk_qsbd[0] ;
            //         fyzk_qsbd_2 = Douw.fyzk_qsbd[1] ;
            //         fyzk_qsbd_3 = Douw.fyzk_qsbd[2] ;
            //         for(var qtlyl = 0 ; qtlyl < Douw.qz_tlyl.length ; qtlyl++)
            //         {
            //             for(var tlyl = 0 ; tlyl < Douw.qz_tlyl[qtlyl].length ; tlyl++)
            //             {
            //                 if(Douw.qz_tlyl[qtlyl][tlyl] == "--")
            //                 {
            //                     Douw.qz_tlyl[qtlyl][tlyl] = "0.0" ;
            //                 }
            //             }
            //         }
            //         qz_tlyl_1 = Douw.qz_tlyl[0] ;
            //         qz_tlyl_2 = Douw.qz_tlyl[1] ;
            //
            //     },
            //     error: function (XMLHttpRequest, textStatus, errorThrown) {
            //         console.log(XMLHttpRequest.status);
            //         console.log(XMLHttpRequest.readyState);
            //         console.log(textStatus);
            //     }
            // });
            //
            //
            // //利润指标占比饼状图
            // var lrzb = echarts.init(document.getElementById("lrzb")) ;
            // var lrzbOption = {
            //     title : {
            //         text: '利润指标完成情况',
            //         x:'center'
            //     },
            //     legend: {
            //         orient: 'vertical',
            //         left: 'left',
            //     },
            //     series : [
            //         {
            //             type: 'pie',
            //             radius : '55%',
            //             center: ['50%', '60%'],
            //             data:[
            //                 {value:LRZB[0], name:'制造业'},
            //                 {value:LRZB[1], name:'集成服务业务'},
            //                 {value:LRZB[2], name:'物流贸易业务'},
            //                 {value:LRZB[3], name:'项目资金'},
            //                 {value:LRZB[4], name:'其他'}
            //             ],
            //             itemStyle: {
            //                 emphasis: {
            //                     shadowBlur: 10,
            //                     shadowOffsetX: 0,
            //                     shadowColor: 'rgba(0, 0, 0, 0.5)'
            //                 },
            //                 normal: {
            //                     label:{
            //                         show: true,
            //                         position:'outer',
            //                         formatter: "{b}{c} "
            //                     }
            //                 }
            //
            //             }
            //         }
            //     ]
            // }
            // lrzb.setOption(lrzbOption);
            // //收入指标占比饼状图
            // var srzb = echarts.init(document.getElementById("srzb"));
            // var srzbOption = {
            //     title : {
            //         text: '收入指标完成情况',
            //         x:'center'
            //     },
            //     tooltip : {
            //         trigger: 'item',
            //         formatter: "{a} <br/>{b} : {c} ({d}%)"
            //     },
            //     legend: {
            //         orient: 'vertical',
            //         left: 'left',
            //     },
            //     series : [
            //         {
            //             type: 'pie',
            //             radius : '55%',
            //             center: ['50%', '60%'],
            //             data:[
            //                 {value:LRZB[5], name:'制造业'},
            //                 {value:LRZB[6], name:'集成服务业务'},
            //                 {value:LRZB[7], name:'物流贸易业务'},
            //                 {value:LRZB[8], name:'其他'}
            //             ],
            //             itemStyle: {
            //                 emphasis: {
            //                     shadowBlur: 10,
            //                     shadowOffsetX: 0,
            //                     shadowColor: 'rgba(0, 0, 0, 0.5)'
            //                 },
            //                 normal: {
            //                     label:{
            //                         show: true,
            //                         position:'outer',
            //                         formatter: "{b}{c} "
            //                     }
            //                 }
            //             }
            //         }
            //     ]
            // }
            // srzb.setOption(srzbOption);
            // //利润变化趋势
            // var bhqs_lr = echarts.init(document.getElementById("bhqs_lr"));
            // var bhqs_lroption = {
            //     title: {
            //         text: '利润变化趋势' ,
            //         x:'center'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value'
            //     },
            //     series: [{
            //         data: bhqs_lr_12 ,
            //         type: 'line',
            //         label: {
            //             normal: {
            //                 show: true,
            //                 position: 'top'
            //             }
            //         },
            //         smooth: true
            //     }]
            // };
            // bhqs_lr.setOption(bhqs_lroption);
            // //收入变化趋势
            // var bhqs_sr = echarts.init(document.getElementById("bhqs_sr"));
            // var bhqs_sroption = {
            //     title: {
            //         text: '收入变化趋势' ,
            //         x:'center'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value'
            //     },
            //     series: [{
            //         data: bhqs_sr_12 ,
            //         type: 'line',
            //         label: {
            //             normal: {
            //                 show: true,
            //                 position: 'top'
            //             }
            //         },
            //         smooth: true
            //     }]
            // };
            // bhqs_sr.setOption(bhqs_sroption);
            // //销售利润率变化趋势
            // var bhqs_xslr = echarts.init(document.getElementById("bhqs_xslr"));
            // var bhqs_xslroption = {
            //     title: {
            //         text: '销售利润率变化趋势' ,
            //         x:'center'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}%'
            //         }
            //     },
            //     series: [{
            //         data: bhqs_xslr_12 ,
            //         type: 'line',
            //         label: {
            //             normal: {
            //                 show: true,
            //                 position: 'top',
            //                 formatter: "{c}%"
            //             }
            //         },
            //         smooth: true
            //     }]
            // };
            // bhqs_xslr.setOption(bhqs_xslroption);
            // //现金流流入
            // var xjl_lr = echarts.init(document.getElementById("xjl_lr"));
            // var xjl_lroption = {
            //     title: {
            //         text: '现金流流入情况' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:['销售商品、提供劳务收到的现金','收到的税费返还','收到的其他与经营活动有关的现金' ],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '销售商品、提供劳务收到的现金' ,
				// 		data: xjl_lr_1 ,
				// 		type: 'line',
            //             /*label: {
            //                 normal: {
            //                     show: true,
            //                     position: 'top',
            //                 }
            //             }*/
            //     	},
            //         {
            //             name: '收到的税费返还' ,
            //             data: xjl_lr_2 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '收到的其他与经营活动有关的现金' ,
            //             data: xjl_lr_3 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // xjl_lr.setOption(xjl_lroption);
            // //现金流流出
            // var xjl_lc = echarts.init(document.getElementById("xjl_lc"));
            // var xjl_lcoption = {
            //     title: {
            //         text: '现金流流出情况' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:['购买商品、接受劳务所支付的现金','支付给职工以及为职工支付的现金','支付的各项税费' ],
				// 	top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '购买商品、接受劳务所支付的现金' ,
            //             data: xjl_lc_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '支付给职工以及为职工支付的现金' ,
            //             data: xjl_lc_2 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '支付的各项税费' ,
            //             data: xjl_lc_3 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // xjl_lc.setOption(xjl_lcoption);
            // //应收账款账龄
            // var yszk_zl = echarts.init(document.getElementById("yszk_zl"));
            // var yszk_zloption = {
            //     title: {
            //         text: '应收账款账龄变化情况 ' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '5年以上' , '4-5年' , '3-4年' , '2-3年' , '1-2年' , '1年以内' ],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '5年以上' ,
            //             data: yszk_zl_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '4-5年' ,
            //             data: yszk_zl_2 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '3-4年' ,
            //             data: yszk_zl_3 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '2-3年' ,
            //             data: yszk_zl_4 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '1-2年' ,
            //             data: yszk_zl_5 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '1年以内' ,
            //             data: yszk_zl_6 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // yszk_zl.setOption(yszk_zloption);
            // //应收账款款项性质情况整体
            // var yqk_zt = echarts.init(document.getElementById("yqk_zt"));
            // var yqk_ztoption = {
            //     title: {
            //         text: '应收账款变化情况 ' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '逾期款' , '未到期款' , '未到期质保金'],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '逾期款' ,
            //             data: yqk_zt_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '未到期款' ,
            //             data: yqk_zt_2 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '未到期质保金' ,
            //             data: yqk_zt_3 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // yqk_zt.setOption(yqk_ztoption);
            // //逾期款专项
            // var yqk_zx = echarts.init(document.getElementById("yqk_zx"));
            // var yqk_zxoption = {
            //     title: {
            //         text: '逾期款专项变化情况' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '逾期0-1个月' , '逾期1-3月' , '逾期3-6月' , '逾期6-12月' , '逾期1年以上'],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '逾期0-1个月' ,
            //             data: yqk_zx_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '逾期1-3月' ,
            //             data: yqk_zx_2 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '逾期3-6月' ,
            //             data: yqk_zx_3 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '逾期6-12月' ,
            //             data: yqk_zx_4 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '逾期1年以上' ,
            //             data: yqk_zx_5 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // yqk_zx.setOption(yqk_zxoption);
            // //逾期应收账产生因素
            // var yqk_ys = echarts.init(document.getElementById("yqk_ys"));
            // var yqk_ysoption = {
            //     title: {
            //         text: '占比前四项因素的款项的变化情况 ' ,
            //         x:'center'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             data: yqk_ys_1 ,
            //             type: 'line',
            //         },
            //         {
            //             data: yqk_ys_2 ,
            //             type: 'line',
            //         },
            //         {
            //             data: yqk_ys_3 ,
            //             type: 'line',
            //         },
            //         {
            //             data: yqk_ys_4 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // yqk_ys.setOption(yqk_ysoption);
            // //账面余额的变化趋势
            // var yszk_zmye = echarts.init(document.getElementById("yszk_zmye"));
            // var yszk_zmyeoption = {
            //     title: {
            //         text: '账面余额的变化趋势' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '应收账款账面余额' , '预警台账账面余额'],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '应收账款账面余额' ,
            //             data: yszk_zmye_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '预警台账账面余额' ,
            //             data: yszk_zmye_2 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // yszk_zmye.setOption(yszk_zmyeoption);
            // //三项指标变化趋势
            // var yszk_sxzb = echarts.init(document.getElementById("yszk_sxzb"));
            // var yszk_sxzboption = {
            //     title: {
            //         text: '指标变化趋势' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '货发票未开金额' , '票开货未发金额' , '预收款冲减应收' ],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '货发票未开金额' ,
            //             data: yszk_sxzb_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '票开货未发金额' ,
            //             data: yszk_sxzb_2 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '预收款冲减应收' ,
            //             data: yszk_sxzb_3 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // yszk_sxzb.setOption(yszk_sxzboption);
            // //存货账龄变化情况
            // var ch_zlbh = echarts.init(document.getElementById("ch_zlbh"));
            // var ch_zlbhoption = {
            //     title: {
            //         text: '账龄存货变化情况 ' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '5年以上' , '4-5年' , '3-4年' , '2-3年' , '1-2年' , '1年以内' ],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '5年以上' ,
            //             data: ch_zlbh_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '4-5年' ,
            //             data: ch_zlbh_2 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '3-4年' ,
            //             data: ch_zlbh_3 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '2-3年' ,
            //             data: ch_zlbh_4 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '1-2年' ,
            //             data: ch_zlbh_5 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '1年以内' ,
            //             data: ch_zlbh_6 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // ch_zlbh.setOption(ch_zlbhoption);
            // //存货性质情况
            // var ch_xz = echarts.init(document.getElementById("ch_xz"));
            // var ch_xzoption = {
            //     title: {
            //         text: '存货变化情况' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '原材料' , '半成品' , '实际库存商品' ],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '原材料' ,
            //             data: ch_xz_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '半成品' ,
            //             data: ch_xz_2 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '实际库存商品' ,
            //             data: ch_xz_3 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // ch_xz.setOption(ch_xzoption);
            // //资金回笼
            // var zjhl_yw = echarts.init(document.getElementById("zjhl_yw"));
            // var zjhl_ywoption = {
            //     title: {
            //         text: '资金回笼情况' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '销售收入' , '资金回笼' ],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '销售收入' ,
            //             data: zjhl_yw_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '资金回笼' ,
            //             data: zjhl_yw_2 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // zjhl_yw.setOption(zjhl_ywoption);
            // //回款方式
            // var zjhl_hkfs = echarts.init(document.getElementById("zjhl_hkfs"));
            // var zjhl_hkfsoption = {
            //     title: {
            //         text: '回款方式的变化趋势' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '现金' , '票据' , '其他' ],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '现金' ,
            //             data: zjhl_hkfs_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '票据' ,
            //             data: zjhl_hkfs_2 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '其他' ,
            //             data: zjhl_hkfs_3 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // zjhl_hkfs.setOption(zjhl_hkfsoption);
            // //单机签约方式
            // var djqy_bhqs = echarts.init(document.getElementById("djqy_bhqs"));
            // var djqy_bhqsoption = {
            //     title: {
            //         text: '单机签约的变化趋势' ,
            //         x:'center'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             data: djqy_bhqs_1 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // djqy_bhqs.setOption(djqy_bhqsoption);
            // //市场签约情况
            // var qyqk_scqs = echarts.init(document.getElementById("qyqk_scqs"));
            // var qyqk_scqsoption = {
            //     title: {
            //         text: '市场签约情况' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '电力市场' , '新能源市场' , '重点领域市场' , '其他市场'  ],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '电力市场' ,
            //             data: qyqk_scqs_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '新能源市场' ,
            //             data: qyqk_scqs_2 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '重点领域市场' ,
            //             data: qyqk_scqs_3 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '其他市场' ,
            //             data: qyqk_scqs_4 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // qyqk_scqs.setOption(qyqk_scqsoption);
            // //产品签约情况及趋势   110-500kV产品签约进行展示
            // var cyqy_kV = echarts.init(document.getElementById("cyqy_kV"));
            // var cyqy_kVoption = {
            //     title: {
            //         text: '110-500kV产品签约的变化趋势' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '220KV' , '330KV' , '500KV'   ],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '220KV' ,
            //             data: cyqy_kV_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '330KV' ,
            //             data: cyqy_kV_2 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '500KV' ,
            //             data: cyqy_kV_3 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // cyqy_kV.setOption(cyqy_kVoption);
            // //对非晶合金、卷铁芯产品签约进行展示
            // var cyqy_cp = echarts.init(document.getElementById("cyqy_cp"));
            // var cyqy_cpoption = {
            //     title: {
            //         text: '非晶合金、卷铁芯产品签约的变化趋势' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '非晶合金' , '卷铁芯'  ],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '非晶合金' ,
            //             data: cyqy_cp_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '卷铁芯' ,
            //             data: cyqy_cp_2 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // cyqy_cp.setOption(cyqy_cpoption);
            // //产值情况
            // var czzb_wcqk = echarts.init(document.getElementById("czzb_wcqk"));
            // var czzb_wcqkoption = {
            //     title: {
            //         text: '产品产出变化趋势' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '交流变压器' , '直流变压器' , '电抗器' , '干式变压器' , '配电变压器' , '箱式变电站' , '特种变压器' , '产业链延伸类'],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '交流变压器' ,
            //             data: czzb_wcqk_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '直流变压器' ,
            //             data: czzb_wcqk_2 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '电抗器' ,
            //             data: czzb_wcqk_3 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '干式变压器' ,
            //             data: czzb_wcqk_4 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '配电变压器' ,
            //             data: czzb_wcqk_5 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '箱式变电站' ,
            //             data: czzb_wcqk_6 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '特种变压器' ,
            //             data: czzb_wcqk_7 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '产业链延伸类' ,
            //             data: czzb_wcqk_8 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // czzb_wcqk.setOption(czzb_wcqkoption);
            // //三项费用管控
            // var fyzk_qsbd = echarts.init(document.getElementById("fyzk_qsbd"));
            // var fyzk_qsbdoption = {
            //     title: {
            //         text: '三项费用变化趋势' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '销售费用' , '管理费用' , '财务费用' ],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '销售费用' ,
            //             data: fyzk_qsbd_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '管理费用' ,
            //             data: fyzk_qsbd_2 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '财务费用' ,
            //             data: fyzk_qsbd_3 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // fyzk_qsbd.setOption(fyzk_qsbdoption);
            // //铜铝用量
            // var qz_tlyl = echarts.init(document.getElementById("qz_tlyl"));
            // var qz_tlyloption = {
            //     title: {
            //         text: '铜铝用量情况' ,
            //         x:'center'
            //     },
            //     legend: {
            //         data:[ '铜' , '铝'  ],
            //         top: '8%'
            //     },
            //     xAxis: {
            //         type: 'category',
            //         data: ZbSj
            //     },
            //     yAxis: {
            //         type: 'value' ,
            //         axisLabel: {
            //             formatter: '{value}'
            //         }
            //     },
            //     series: [
            //         {
            //             name: '铜' ,
            //             data: qz_tlyl_1 ,
            //             type: 'line',
            //         },
            //         {
            //             name: '铝' ,
            //             data: qz_tlyl_2 ,
            //             type: 'line',
            //         }
            //     ]
            // };
            // qz_tlyl.setOption(qz_tlyloption);
            // setTimeout(function(){
            //
            // }, 1000);
        });

        function toDataUrl(canvases){
            var cloneElem = $(canvases)[0].clone();
            var context = cloneElem[0].getContext("2d");

            for (var i = 0; i < cs.length; ++i){
                context.drawImage(cs[i], 0, 0);
            }
            var dwpclPng = cloneElem[0].toDataURL();
            return dwpclPng;
        }

	</script>
	<%--<script>
        $("#grid-update").click(function(){
            var lrzbPng = $("canvas")[0].toDataURL("lrzb");$("#lrzbPng").val(lrzbPng);
            var srzbPng = $("canvas")[0].toDataURL("srzb");$("#srzbPng").val(srzbPng);

            console.log(srzbPng);
            $("#myform").submit();
        });
        function sumbitTest(){
            return true;
        }
	</script>--%>
</body>
<!--  /Body -->
</html>
