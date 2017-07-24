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
<title>Databoxes</title>

<meta name="description" content="databoxes" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/jsp/ui2/assets/img/favicon.png"
	type="image/x-icon">

<!--Basic Styles-->
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<link id="bootstrap-rtl-link" href="" rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/font-awesome.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/weather-icons.min.css"
	rel="stylesheet" />



<!--Beyond styles-->
<link id="beyond-link"
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/beyond.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/demo.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/jsp/ui2/assets/css/typicons.min.css" rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/animate.min.css"
	rel="stylesheet" />
<link id="skin-link" href="" rel="stylesheet" type="text/css" />



<!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
<script
	src="${pageContext.request.contextPath}/jsp/ui2/assets/js/skins.min.js"></script>
<style>
.legendColorBox {
    padding-left: 10px;
    vertical-align: middle;
  	padding-top: 0px;
}

</style>

</head>
<!-- /Head -->
<!-- Body -->
<body>
	<!-- Page Breadcrumb -->
	<div class="page-breadcrumbs">
		<ul class="breadcrumb">
		</ul>
	</div>
	<!-- /Page Breadcrumb -->

	<!-- Page Body -->
	<div class="page-body">
		<div class="row">
			<div class="col-lg-12 col-sm-12 col-xs-12">
				<div class="row">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
						<div
							class="databox radius-bordered databox-shadowed databox-graded">
							<div class="databox-left bg-orange">
								<div class="databox-piechart">
									<div data-toggle="easypiechart" class="easyPieChart"
										data-barcolor="#fff" data-linecap="butt" data-percent="61.1"
										data-animate="1000" data-linewidth="3" data-size="47"
										data-trackcolor="rgba(255,255,255,0.1)">
										<span id="lrzewcl" class="white font-90">0%</span>
									</div>
								</div>
							</div>
							<div class="databox-right bg-white">
								<span id="lrze"  class="databox-number orange">--</span>
								<div class="databox-text darkgray">利润总额（年度累计）</div>
								<div class="databox-stat bg-orange radius-bordered">
									<div  id="lrzetbzf"  class="stat-text">0%</div>
									<i class="stat-icon fa fa-arrow-up"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
						<div
							class="databox radius-bordered databox-shadowed databox-graded">
							<div class="databox-left" style="background-color: #03B3B2">
								<div class="databox-piechart">
									<div data-toggle="easypiechart" class="easyPieChart"
										data-barcolor="#fff" data-linecap="butt" data-percent="49.1"
										data-animate="1500" data-linewidth="3" data-size="47"
										data-trackcolor="rgba(255,255,255,0.1)">
										<span  id="xssrwcl"  class="white font-90">0%</span>
									</div>
								</div>
							</div>
							<div class="databox-right bg-white">
								<span id="xssr" class="databox-number" style="color: #03B3B2">--
									</span>
								<div class="databox-text darkgray">销售收入（年度累计）</div>
								<div class="databox-stat radius-bordered"
									style="background-color: #03B3B2">
									<div id="xssrtbzf" class="stat-text white">0%</div>
									<i class="stat-icon fa fa-arrow-up white"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
						<div
							class="databox radius-bordered databox-shadowed databox-graded">
							<div class="databox-left" style="background-color: #0072C6;">
								<div class="databox-piechart">
									<div data-toggle="easypiechart" class="easyPieChart"
										data-barcolor="#fff" data-linecap="butt" data-percent="102.9"
										data-animate="2000" data-linewidth="3" data-size="47"
										data-trackcolor="rgba(255,255,255,0.1)">
										<span id="yszkwcl" class="white font-40">0%</span>
									</div>
								</div>
							</div>
							<div class="databox-right bg-white ">
								<span id="yszk"  class="databox-number" style="color: #0072C6;">--
									</span>
								<div class="databox-text darkgray">应收账款（年度累计）</div>
								<div class="databox-stat radius-bordered"
									style="background-color: #0072C6;">
									<div id="yszktbzf" class="stat-text white">0%</div>
									<i class="stat-icon fa fa-arrow-up white"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
						<div
							class="databox radius-bordered databox-shadowed databox-graded">
							<div class="databox-left bg-lightred">
								<div class="databox-piechart">
									<div data-toggle="easypiechart" class="easyPieChart"
										data-barcolor="#fff" data-linecap="butt" data-percent="128.4"
										data-animate="2000" data-linewidth="3" data-size="47"
										data-trackcolor="rgba(255,255,255,0.1)">
										<span  id="chwcl" class="white font-40">0%</span>
									</div>
								</div>
							</div>
							<div class="databox-right bg-white">
								<span  id="ch" class="databox-number lightred">--</span>
								<div class="databox-text darkgray">存货（年度累计）</div>
								<div class="databox-stat bg-lightred radius-bordered">
									<div  id="chtbzf" class="stat-text">0%</div>
									<i class="stat-icon fa fa-arrow-up"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="row">
							<div class="col-xs-12">
								<div class="dashboard-box">
									<div class="box-tabbs">
										<div class="tabbable">
											<ul class="nav nav-tabs tabs-flat nav-justified" id="myTab11">
												<li class="active"><a data-toggle="tab"
													href="#total_profit"> 利润总额 </a></li>
												<li><a data-toggle="tab" href="#sales_income">
														销售收入 </a></li>
												<li><a data-toggle="tab" href="#accounts_receivable">
														应收账款 </a></li>
												<li><a data-toggle="tab" href="#stock"> 存货 </a></li>
											</ul>
											<div class="tab-content tabs-flat no-padding">
												<div id="total_profit"
													class="tab-pane padding-10 active animated fadeInUp">
													<div class="row">
														<div class="col-lg-12 chart-container">
															<div id="dashboard-chart-total-profit"
																class="chart chart-lg no-margin" style="width: 100%"></div>
														</div>
													</div>
												</div>
												<div id="sales_income"
													class="tab-pane  padding-10 animated fadeInUp">
													<div class="row">
														<div class="col-lg-12 chart-container">
															<div id="dashboard-chart-sales-income"
																class="chart chart-lg no-margin" style="width: 100%"></div>
														</div>
													</div>
												</div>
												<div id="accounts_receivable"
													class="tab-pane padding-10 animated fadeInUp">
													<div class="row">
														<div class="col-lg-12 chart-container">
															<div id="dashboard-chart-accounts-receivable"
																class="chart chart-lg no-margin" style="width: 100%"></div>
														</div>
													</div>
												</div>
												<div id="stock"
													class="tab-pane padding-20 animated fadeInUp"
													style="width: 100%">
													<div class="row">
														<div class="col-lg-12 chart-container">
															<div id="dashboard-chart-stock"
																class="chart chart-lg no-margin" style="width: 100%"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
						<div
							class="databox databox-xxlg databox-vertical databox-inverted">
							<div class="databox-top bg-white no-padding"
								style="height: 263px">
								<div class="databox-row row-3 bg-orange">
									<div
										class="databox-cell cell-5 no-padding padding-5 text-align-center">
										<span id="gsqy"
											class="databox-number number-xxlg  white no-margin padding-top-5">--</span>
										<span class="databox-text whitesmoke no-margin padding-top-5">公司整体市场签约（万元）</span>
									</div>
									<div
										class="databox-cell cell-2 no-padding padding-5 text-align-center">
										<span id="zzyqy"
											class="databox-number number-lg  white no-margin padding-top-5">--</span>
										<span class="databox-text whitesmoke no-margin padding-top-5">制造业签约</span>
									</div>
									<div
										class="databox-cell cell-2 no-padding padding-5 text-align-center">
										<span id="jcfwqy"
											class="databox-number number-lg white no-margin padding-top-5">--</span>
										<span class="databox-text whitesmoke no-margin padding-top-5">集成服务业签约</span>
									</div>
									<div 
										class="databox-cell cell-2 no-padding padding-5 text-align-center">
										<span id="qtqy"
											class="databox-number number-lg  white no-margin padding-top-5">--</span>
										<span class="databox-text whitesmoke no-margin padding-top-5">其他签约</span>
									</div>
								</div>
								<div class="databox-row row-9 no-padding bg-white">
									<div class="databox-sparkline">
										<!--11A9CC-->
										<span id="ydqy" data-sparkline="line" data-height="198px"
											data-width="100%" data-fillcolor="#37c2e2"
											data-linecolor="#37c2e2" data-spotcolor="#fafafa"
											data-minspotcolor="#fafafa" data-maxspotcolor="#ffce55"
											data-highlightspotcolor="#f5f5f5 "
											data-highlightlinecolor="#f5f5f5" data-linewidth="2"
											data-spotradius="0">
											0,0,0,0,0,0,0,0,0,0,0,0 </span>
									</div>
								</div>
							</div>
							<div class="databox-bottom bg-sky no-padding no-border"
								style="height: 36px">
								<div
									class="databox-cell cell-1 text-align-center no-padding padding-top-5">
									<span class="databox-text white">一月</span>
								</div>
								<div
									class="databox-cell cell-1 text-align-center no-padding padding-top-5">
									<span class="databox-text white">二月</span>
								</div>
								<div
									class="databox-cell cell-1 text-align-center no-padding padding-top-5">
									<span class="databox-text white">三月</span>
								</div>
								<div
									class="databox-cell cell-1 text-align-center no-padding padding-top-5">
									<span class="databox-text white">四月</span>
								</div>
								<div
									class="databox-cell cell-1 text-align-center no-padding padding-top-5">
									<span class="databox-text white">五月</span>
								</div>
								<div
									class="databox-cell cell-1 text-align-center no-padding padding-top-5">
									<span class="databox-text white">六月</span>
								</div>
								<div
									class="databox-cell cell-1 text-align-center no-padding padding-top-5">
									<span class="databox-text white">七月</span>
								</div>
								<div
									class="databox-cell cell-1 text-align-center no-padding padding-top-5">
									<span class="databox-text white">八月</span>
								</div>
								<div
									class="databox-cell cell-1 text-align-center no-padding padding-top-5">
									<span class="databox-text white">九月</span>
								</div>
								<div
									class="databox-cell cell-1 text-align-center no-padding padding-top-5">
									<span class="databox-text white">十月</span>
								</div>
								<div
									class="databox-cell cell-1 text-align-center no-padding padding-top-5">
									<span class="databox-text white">十一月</span>
								</div>
								<div
									class="databox-cell cell-1 text-align-center no-padding padding-top-5">
									<span class="databox-text white">十二月</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-12 col-sm-12 col-xs-12">
						<div
							class="databox databox-xxlg databox-vertical databox-shadowed bg-white radius-bordered padding-5">
							<div class="databox-top">
								<div class="databox-row row-12">
									<div class="databox-cell cell-3 text-right">
										<div id="ztscqy"
											class="databox-number orange number-xxlg no-margin padding-top-5">--</div>
									</div>
									<div
										class="databox-cell cell-1 text-left no-margin no-padding padding-top-10">
										<div class="databox-text darkgray">万元</div>
									</div>
									<div class="databox-cell cell-8 text-align-center">
										<div class="databox-row row-6 text-left margin-bottom-5">
											<span
												class="badge badge-palegreen badge-empty margin-left-10"></span>
											<span class="databox-inlinetext darkgray margin-left-10">输变电国内市场签约额</span>
											<span class="badge badge-yellow badge-empty margin-left-10"></span>
											<span class="databox-inlinetext darkgray margin-left-10">输变电国际市场签约额</span>
										</div>
										<div class="databox-row row-6">
											<div class="progress bg-yellow progress-no-radius">
												<div id="scqygnzb" class="progress-bar progress-bar-palegreen"
													role="progressbar" aria-valuenow="50" aria-valuemin="0"
													aria-valuemax="100" style="width: 50%"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="databox-bottom">
								<div class="databox-row row-12">
									<div
										class="databox-cell cell-5 text-left no-padding-left padding-bottom-5 padding-top-10">
										<div
											class="databox-row bordered-bottom bordered-ivory padding-5"
											style="height: 12.5%">
											<span
												class="databox-text sonic-silver darkgray pull-left no-margin">国内市场签约排名</span>
											<span 
												class="databox-text sonic-silver darkgray pull-right no-margin uppercase">签约额</span>
										</div>
										<div
											class="databox-row bordered-bottom bordered-ivory padding-5"
											style="height: 12.5%">
											<span class="badge badge-blue badge-empty pull-left margin-5"></span>
											<span id="gnpm_gs1"
												class="databox-text darkgray pull-left no-margin hidden-xs">沈变</span>
											<span id="gnpm_val1"
												class="databox-text darkgray pull-right no-margin uppercase">--万元</span>
										</div>
										<div
											class="databox-row bordered-bottom bordered-ivory padding-5"
											style="height: 12.5%">
											<span
												class="badge badge-orange badge-empty pull-left margin-5"></span>
											<span id="gnpm_gs2"
												class="databox-text darkgray pull-left no-margin hidden-xs">衡变</span>
											<span id="gnpm_val2"
												class="databox-text darkgray pull-right no-margin uppercase">--万元</span>
										</div>
										<div
											class="databox-row bordered-bottom bordered-ivory padding-5"
											style="height: 12.5%">
											<span class="badge badge-pink badge-empty pull-left margin-5"></span>
											<span id="gnpm_gs3"
												class="databox-text darkgray pull-left no-margin hidden-xs">新变</span>
											<span id="gnpm_val3"
												class="databox-text darkgray pull-right no-margin uppercase">--万元</span>
										</div>
										<div
											class="databox-row bordered-bottom bordered-ivory padding-5"
											style="height: 12.5%">
											<span
												class="badge badge-palegreen badge-empty pull-left margin-5"></span>
											<span id="gnpm_gs4"
												class="databox-text darkgray pull-left no-margin hidden-xs">鲁缆</span>
											<span id="gnpm_val4"
												class="databox-text darkgray pull-right no-margin uppercase">--万元</span>
										</div>
										<div
											class="databox-row bordered-bottom bordered-ivory padding-5"
											style="height: 12.5%">
											<span
												class="badge badge-blueberry badge-empty pull-left margin-5"></span>
											<span id="gnpm_gs5"
												class="databox-text darkgray pull-left no-margin hidden-xs">新缆</span>
											<span id="gnpm_val5"
												class="databox-text darkgray pull-right no-margin uppercase">--万元</span>
										</div>
										<div
											class="databox-row bordered-bottom bordered-ivory padding-5"
											style="height: 12.5%">
											<span
												class="badge badge-yellow badge-empty pull-left margin-5"></span>
											<span id="gnpm_gs6"
												class="databox-text darkgray pull-left no-margin hidden-xs">德缆</span>
											<span id="gnpm_val6"
												class="databox-text darkgray pull-right no-margin uppercase">--万元</span>
										</div>
									</div>
									<div
										class="databox-cell cell-2 text-left no-padding-left padding-bottom-5 padding-top-10">
									</div>
									<div
										class="databox-cell cell-5 text-left no-padding-left padding-bottom-5 padding-top-10">
										<div
											class="databox-row bordered-bottom bordered-ivory padding-5"
											style="height: 12.5%">
											<span
												class="databox-text sonic-silver darkgray pull-left no-margin">国际市场签约排名</span>
											<span
												class="databox-text sonic-silver darkgray pull-right no-margin uppercase">签约额</span>
										</div>
										<div
											class="databox-row bordered-bottom bordered-ivory padding-5"
											style="height: 12.5%">
											<span class="badge badge-blue badge-empty pull-left margin-5"></span>
											<span id="gjpm_gs1"
												class="databox-text darkgray pull-left no-margin hidden-xs">沈变</span>
											<span id="gjpm_val1"
												class="databox-text darkgray pull-right no-margin uppercase">--万元</span>
										</div>
										<div
											class="databox-row bordered-bottom bordered-ivory padding-5"
											style="height: 12.5%">
											<span
												class="badge badge-orange badge-empty pull-left margin-5"></span>
											<span id="gjpm_gs2"
												class="databox-text darkgray pull-left no-margin hidden-xs">衡变</span>
											<span id="gjpm_val2"
												class="databox-text darkgray pull-right no-margin uppercase">--万元</span>
										</div>
										<div
											class="databox-row bordered-bottom bordered-ivory padding-5"
											style="height: 12.5%">
											<span class="badge badge-pink badge-empty pull-left margin-5"></span>
											<span id="gjpm_gs3"
												class="databox-text darkgray pull-left no-margin hidden-xs">衡变</span>
											<span id="gjpm_val3"
												class="databox-text darkgray pull-right no-margin uppercase">--万元</span>
										</div>
										<div
											class="databox-row bordered-bottom bordered-ivory padding-5"
											style="height: 12.5%">
											<span
												class="badge badge-palegreen badge-empty pull-left margin-5"></span>
											<span id="gjpm_gs4"
												class="databox-text darkgray pull-left no-margin hidden-xs">衡变</span>
											<span id="gjpm_val4"
												class="databox-text darkgray pull-right no-margin uppercase">--万元</span>
										</div>
										<div
											class="databox-row bordered-bottom bordered-ivory padding-5"
											style="height: 12.5%">
											<span
												class="badge badge-blueberry badge-empty pull-left margin-5"></span>
											<span id="gjpm_gs5"
												class="databox-text darkgray pull-left no-margin hidden-xs">衡变</span>
											<span id="gjpm_val5"
												class="databox-text darkgray pull-right no-margin uppercase">--万元</span>
										</div>
										<div
											class="databox-row bordered-bottom bordered-ivory padding-5"
											style="height: 12.5%">
											<span
												class="badge badge-yellow badge-empty pull-left margin-5"></span>
											<span id="gjpm_gs6"
												class="databox-text darkgray pull-left no-margin hidden-xs">衡变</span>
											<span id="gjpm_val6"
												class="databox-text darkgray pull-right no-margin uppercase">--万元</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- /Page Body -->

	<!--Basic Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/jquery/jquery-1.12.3.js"></script>

	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/slimscroll/jquery.slimscroll.min.js"></script>

	<!--Beyond Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/beyond.min.js"></script>


	<!--Flot Charts Needed Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/flot/jquery.flot.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/flot/jquery.flot.resize.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/flot/jquery.flot.pie.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/flot/jquery.flot.tooltip.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/flot/jquery.flot.orderbars.js"></script>

	<!--Page Related Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/sparkline/jquery.sparkline.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/sparkline/sparkline-init.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/excanvas.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/easypiechart/jquery.easypiechart.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/easypiechart/easypiechart-init.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/morris/raphael-2.0.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/morris/morris.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/morris/morris-init.js"></script>


	<script src="${pageContext.request.contextPath}/jsp/ui2/pages/util.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/chartjs/chart.js"></script>
		<%@include file="pages/loading.jsp"%>
	<script>
	
	Util.Breadcrumb.render(JSON.parse('${param.breads}'));
	if (Util.isIframe()) {
		Util.Breadcrumb.setOnClickListener(function(breadNode) {
			window.parent['onClickBreadcrumb']
					&& window.parent['onClickBreadcrumb'](breadNode);
		});
	}

	window.data = JSON.parse('${data}');
	
	bindPart1Data();
	function bindPart1Data(){
		$("#lrzewcl").text(data.zt[0].ndjhwcl)
		.parent().attr("data-percent", data.zt[0].ndjhwcl.replace("%", ""));
		$("#lrze").text(data.zt[0].ndlj);
		$("#lrzetbzf").text(data.zt[0].ndljtbzf);
		if (data.zt[0].ndljtbzf[0] == '-'){
			$("#lrzetbzf")
			.text(data.zt[0].ndljtbzf.substring(1))
			.next()
			.removeClass("fa-arrow-up")
			.addClass("fa-arrow-down");
		}
		
		$("#xssrwcl").text(data.zt[1].ndjhwcl)
		.parent().attr("data-percent", data.zt[1].ndjhwcl.replace("%", ""));
		$("#xssr").text(data.zt[1].ndlj);
		$("#xssrtbzf").text(data.zt[1].ndljtbzf);
		if (data.zt[1].ndljtbzf[0] == '-'){
			$("#xssrtbzf")
			.text(data.zt[1].ndljtbzf.substring(1))
			.next()
			.removeClass("fa-arrow-up")
			.addClass("fa-arrow-down");
		}
		
		$("#yszkwcl").text(data.zt[2].ndjhwcl)
		.parent().attr("data-percent", data.zt[2].ndjhwcl.replace("%", ""));
		$("#yszk").text(data.zt[2].ndlj);
		$("#yszktbzf").text(data.zt[2].ndljtbzf);
		if (data.zt[2].ndljtbzf[0] == '-'){
			$("#yszktbzf")
			.text(data.zt[2].ndljtbzf.substring(1))
			.next()
			.removeClass("fa-arrow-up")
			.addClass("fa-arrow-down");
		}
		
		$("#chwcl").text(data.zt[3].ndjhwcl)
		.parent().attr("data-percent", data.zt[3].ndjhwcl.replace("%", ""));
		$("#ch").text(data.zt[3].ndlj);
		$("#chtbzf").text(data.zt[3].ndljtbzf);
		if (data.zt[3].ndljtbzf[0] == '-'){
			$("#chtbzf")
			.text(data.zt[3].ndljtbzf.substring(1))
			.next()
			.removeClass("fa-arrow-up")
			.addClass("fa-arrow-down");
		}
		
		$("#gsqy").text(data.scqy.gszt);
		$("#zzyqy").text(data.scqy.zzy);
		$("#jcfwqy").text(data.scqy.jcfw);
		$("#qtqy").text(data.scqy.qt);
		
		$("#ydqy").text(data.scqy.ydzbs.join().replace(/--/g, "0"));
		
		$("#ztscqy").text(data.scqypm.sbdztqy);
		$("#scqygnzb").css("width", data.scqypm.gnzb)
		.attr("aria-valuenow", data.scqypm.gnzb.replace("%", ""));
		
		function forShort(name){
			if (name.indexOf("沈") >= 0){
				return "沈变";
			}
			if (name.indexOf("衡") >= 0){
				return "衡变";
			}
			if (name.indexOf("新疆变压器") >= 0){
				return "新变";
			}
			if (name.indexOf("鲁") >= 0){
				return "鲁缆";
			}
			if (name.indexOf("新疆线缆") >= 0){
				return "新缆";
			}
			if (name.indexOf("德") >= 0){
				return "德缆";
			}
		}
		
		for (var i = 0; i < data.scqypm.gnsc.length; ++i){
			$("#gnpm_gs" + (i + 1)).text(forShort(data.scqypm.gnsc[i][0]));
			$("#gnpm_val" + (i + 1)).text(data.scqypm.gnsc[i][1] + " 万元");
			$("#gjpm_gs" + (i + 1)).text(forShort(data.scqypm.gjsc[i][0]));
			$("#gjpm_val" + (i + 1)).text(data.scqypm.gjsc[i][1] + " 万美元");
		}
		

		//-------------------------Initiates Sparkline Chart instances in page------------------//
	    InitiateSparklineCharts.init();
	    
	    //-----------------------------Pie Charts-----------------------------------------//
	    InitiateEasyPieChart.init();
	}
	
	function bindPart2Data(){
		
	    //-------------------定义数据、维度------------------------------------------------//
	    var chartfirstcolor = "#57b5e3";
	    var chartsecondcolor = "#f4b400";
	    var chartthirdcolor = "#d73d32";
	    var chartfourthcolor = "#8cc474";
	    var chartfifthcolor = "#bc5679";
	    var gridbordercolor = "#eee";
	
	    function getChartOpt(index){
	    
		    var d1_year_plan = [];
		    //插入动态数据
		    for (var i = 1; i <= 13; i += 1)
		        d1_year_plan.push([i, parseFloat(data.jydw[index][i - 1].ndjh)]);
		
		    var d1_year_amount = [];
		    //插入动态数据
		    for (var i = 1; i <= 13; i += 1)
		        d1_year_amount.push([i, parseFloat(data.jydw[index][i - 1].ndlj)]);
		
		    var d1_last_year_amount = [];
		    //插入动态数据
		    for (var i = 1; i <= 13; i += 1)
		        d1_last_year_amount.push([i, parseFloat(data.jydw[index][i - 1].qntq)]);
		
		    var d1_ticks = [[1, '沈变'], [2, '衡变'], [3, '新变'], [4, '鲁缆'], [5, '新缆'], [6, '德缆'], [7, '新能源'],
		        [8, '新特能源'], [9, '天池能源'], [10, '能动'], [11, '众和'], [12, '进出口'], [13, '国际工程']];
		
		    var data_flot_bar_chart = [
		        {
		            label: "年度计划",
		            data: d1_year_plan,
		            bars: {
		                show: true,
		                order: 1,
		                fillColor: { colors: [{ color: chartthirdcolor }, { color: chartthirdcolor }] }
		            },
		            color: chartthirdcolor
		        },
		        {
		            label: "年度累计",
		            data: d1_year_amount,
		            bars: {
		                show: true,
		                order: 2,
		                fillColor: { colors: [{ color: chartsecondcolor }, { color: chartsecondcolor }] }
		            },
		            color: chartsecondcolor
		        },
		        {
		            label: "去年同期累计",
		            data: d1_last_year_amount,
		            bars: {
		                show: true,
		                order: 3,
		                fillColor: { colors: [{ color: chartfirstcolor }, { color: chartfirstcolor }] }
		            },
		            color: chartfirstcolor
		        }
		    ];
		    
	    
		    //-------------------Chart设置参数------------------------------------------------//
		    var options_flot_bar_chart = {
		        bars: {
		            barWidth: 0.2,
		            lineWidth: 0,
		            borderWidth: 0,
		            fillColor: { colors: [{ opacity: 0.4 }, { opacity: 1 }] }
		        },
		        xaxis: {
		            ticks: d1_ticks,
		            color: gridbordercolor
		        },
		        yaxis: {
		            color: gridbordercolor
		        },
		        grid: {
		            hoverable: true,
		            clickable: false,
		            borderWidth: 0,
		            aboveData: false
		        },
		        legend: {
		            noColumns: 4
		        },
		        tooltip: true,
		        tooltipOpts: {
		            defaultTheme: false,
		            content: "<b>%s</b> : <span>%y 万元</span>"
		        }
		    };
		    return [data_flot_bar_chart, options_flot_bar_chart];
	    }
	    
	    //------------------实例化Flot Chart-----------------------------------------------//
	   
	    var opt = getChartOpt(0);
	    var placeholder_total_profit = $("#dashboard-chart-total-profit");
	    var plot_total_profit = $.plot(placeholder_total_profit, opt[0], opt[1]);
	/*     opt = getChartOpt(1);
	    var placeholder_sales_income = $("#dashboard-chart-sales-income");
	    var plot_sales_income = $.plot(placeholder_sales_income, opt[0], opt[1]);
	    opt = getChartOpt(2);
	    var placeholder_accounts_receivable = $("#dashboard-chart-accounts-receivable");
	    var plot_accounts_receivable = $.plot(placeholder_accounts_receivable, opt[0], opt[1]);
	    opt = getChartOpt(3);
	    var placeholder_stock = $("#dashboard-chart-stock");
	    var plot_stock = $.plot(placeholder_stock, opt[0], opt[1]);
	 */    
	 	var plotIds = ["#dashboard-chart-total-profit", "#dashboard-chart-sales-income", "#dashboard-chart-accounts-receivable", "#dashboard-chart-stock"];
		var displayed = [true, false, false, false];
	    $("#myTab11").on("click", function(){
	    	setTimeout(function(){
	    		$("#myTab11 li").each(function(i, e){
	    			if ($(e).hasClass("active")){
	    				if (!displayed[i]){
	    					 var opt = getChartOpt(i);
	    					 var placeholder_plot = $(plotIds[i]);
	    					 var plot = $.plot(placeholder_plot, opt[0], opt[1]);
	    					 displayed[i] = true;
	    				}
	    				return false;
	    			}
	    		});
	    	}, 0);
	    });
	}
		
	ajaxInit = new Util.Ajax("/BusinessManagement/dashboard/dashboard_update.do", false);
	ajaxInit.get().then(function(data){
		window.data = data;
		bindPart2Data();
	});
</script>

</body>
<!--  /Body -->
</html>