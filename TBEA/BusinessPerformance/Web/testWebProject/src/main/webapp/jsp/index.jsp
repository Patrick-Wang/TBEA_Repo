<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="ECharts">
<meta name="author" content="">
<title>TBEA 经营管控系统</title>
<style type="text/css">
</style>
<script type="text/javascript">
	document.createElement("footer");
</script>

<link rel="shortcut icon" href="../images/logo.png">

<!--
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://echarts.baidu.com/doc/asset/css/bootstrap.css" rel="stylesheet">
    <link href="http://echarts.baidu.com/doc/asset/css/carousel.css" rel="stylesheet">
    <link href="http://echarts.baidu.com/doc/asset/css/echartsHome.css" rel="stylesheet">
-->
<link href="../css/font-awesome.min.css" rel="stylesheet">
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/carousel.css" rel="stylesheet">
<link href="../css/echartsHome.css" rel="stylesheet">
<link href="../css/index.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.min.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
<TITLE></TITLE>
<META charset=utf-8>
<META content=IE=edge http-equiv=X-UA-Compatible>
<META name=viewport content="width=device-width, initial-scale=1.0">
<META name=description content=ECharts>
<META name=author content="">
<LINK rel=stylesheet href="../css/font-awesome.min.css">
<LINK rel=stylesheet href="../css/bootstrap.css">
<LINK rel=stylesheet href="../css/carousel.css">
<LINK rel=stylesheet href="../css/echartsHome.css">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
</head>

<body>
	<!-- Fixed navbar -->
	<div class="navbar navbar-default navbar-fixed-top" role="navigation"
		id="head"></div>

	<div class="container" style="padding-top: 10px;">
		<div class="row-fluid">
			<div class="col-md-2">
				<div class="affix" style="margin-left: -20px; margin-top: auto;"
					id="chartsTypeNav">
					<!-- 所有公司都显示-->
					<ul id="navlist" style="padding: 10px 0; width: 180px;">
					<c:if test="${admin}">
					<li style="background-color: transparent;"><i
						class="ec-icon ec-icon-line"></i> <a href="#dashboard"
						style="color: rgb(62, 152, 197);">Dashboard</a></li>
					</c:if>
					<li style="background-color: transparent;"><i
						class="ec-icon ec-icon-line"></i> <a href="#zbhz"
						style="color: rgb(62, 152, 197);">经营指标完成情况</a></li>

					<!-- 只有公司权限显示-->
					<%-- <c:if test="${CorpAuth}"> --%>
						<li style="background-color: transparent;"><i
							class="ec-icon ec-icon-line"></i> <a href="#zbhz"
							style="color: rgb(62, 152, 197);">经营指标预测情况</a></li>
					<%-- </c:if> --%>
					<c:if test="${CorpAuth}">
				<!-- 	<li style="background-color: transparent;"><i
						class="ec-icon ec-icon-line"></i> <a href="#ranking"
						style="color: rgb(62, 152, 197);">经营单位指标排名</a></li> -->
					</c:if>
					<!-- 只有sbd公司权限显示-->
					<c:if test="${SbdAuth}">
							<li style="background-color: transparent;"><i
								class="ec-icon ec-icon-pie"></i> <a href="#yszk"
								style="color: rgb(62, 152, 197);">应收账款</a></li>
							<li style="background-color: transparent;"><i
								class="ec-icon ec-icon-pie"></i> <a href="#bl"
								style="color: rgb(62, 152, 197);">保理状态</a></li>
							<li style="background-color: transparent;"><i
								class="ec-icon ec-icon-pie"></i> <a href="#ht"
								style="color: rgb(62, 152, 197);">合同付款</a></li>
							<li style="background-color: transparent;"><i
								class="ec-icon ec-icon-radar"></i> <a href="#hk"
								style="color: rgb(62, 152, 197);">回款</a></li>
							<li style="background-color: transparent;"><i
								class="ec-icon ec-icon-chord"></i> <a href="#cb"
								style="color: rgb(62, 152, 197);">成本管控</a></li>
							<li style="background-color: transparent;"><i
								class="ec-icon ec-icon-force"></i> <a href="#tbbzj"
								style="color: rgb(62, 152, 197);">投标保证金</a></li>
					</c:if>
					</ul>

					<ul id="navlist1" style="padding: 10px 0; display: none">
						<c:if test="${entryPlan}">
							<li style="background-color: transparent"><i
								class="ec-icon ec-icon-force"></i> <a href="#inputPlan"
								style="color: rgb(62, 152, 197);">计划指标录入</a></li>
						</c:if>

						<c:if test="${entryPredict}">
							<li style="background-color: transparent"><i
								class="ec-icon ec-icon-force"></i> <a href="#inputPrediction"
								style="color: rgb(62, 152, 197);">预计指标录入</a></li>
						</c:if>
					</ul>

					<ul id="navlist2" style="padding: 10px 0; display: none">
						<c:if test="${approvePlan}">
							<li style="background-color: transparent"><i
								class="ec-icon ec-icon-force"></i> <a href="#approvePlan"
								style="color: rgb(62, 152, 197);">计划指标审核</a></li>
						</c:if>

						<c:if test="${approvePredict}">
							<li style="background-color: transparent"><i
								class="ec-icon ec-icon-force"></i> <a href="#approvePrediction"
								style="color: rgb(62, 152, 197);">预计指标审核</a></li>
						</c:if>
					</ul>

<!-- 					<ul id="navlist3" style="padding: 10px 0; display: none">
						<li style="background-color: transparent; diplay: none"><i
							class="ec-icon ec-icon-force"></i> <a href="#finincial"
							style="color: rgb(62, 152, 197);">财务指标汇总</a></li>
					</ul> -->
				</div>
			</div>
			<div id="IndexSummary" class="col-md-10">
				<%@include file="index_IndexSummary.jsp"%>
			</div>
			<div id="InputList" class="col-md-10" style="display: none">
				<%@include file="index_InputList.jsp"%>
			</div>

			<div id="approveList" class="col-md-10" style="display: none">
				<%@include file="index_approveList.jsp"%>
			</div>

			<%-- <div id="financeList" class="col-md-10" style="display: none">
				<%@include file="index_financeList.jsp"%>
			</div> --%>

		</div>
	</div>
	<!-- /container -->

	<footer id="footer" style="margin-top: 30px;"></footer>
	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/index.js"></script>
	<script type="text/javascript" src="../js/h.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../jsp/json2.js" type="text/javascript"></script>
	<script src="../jsp/util.js" type="text/javascript"></script>
	<script type="text/javascript">
	
	function logout(){
		var logoutAjax = new Util.Ajax("logout.do");
		logoutAjax.get().then(function onSuccess(){
			
		}, function onFailed(){
			alert("网络错误");
		});
	}
	
	
		var funResize;
		$(window)
				.load(
						function() {
							var section = $('[class=section]');
							function loadImage(i) {
								setTimeout(
										function() {
											var list = $('div>ul>li>a',
													section[i]);
											var nav = $('ol>li>img', section[i]);
											var href;
											var src;
											if (list.length > 0) {
												for (var j = 0, k = list.length; j < k; j++) {
													list[j].target = '_blank';
													href = list[j].href
															.slice(
																	list[j].href
																			.lastIndexOf('/') + 1,
																	-5);
													src = list[j].firstChild.src
															.replace('cache',
																	href);
													list[j].firstChild.alt = 'ECharts '
															+ href;
													list[j].firstChild.src = src;
												}
											}
										}, i * 100);
							}
							for (var i = 0, l = section.length; i < l; i++) {
								loadImage(i);
							}

							var chartsTypeNav = document
									.getElementById('chartsTypeNav');
							function _scroll() {
								var navHeight = chartsTypeNav.offsetHeight;
								var viewHeight = document.documentElement.clientHeight;
								var scrollHeight = document.documentElement.scrollTop
										|| document.body.scrollTop;
								var offsetHeight = document.body.offsetHeight;
								var footHole = offsetHeight - scrollHeight
										- viewHeight;
								var maxHeight = viewHeight - (230 - footHole)
										- 80;
								if (footHole < 320 && maxHeight < navHeight) {
									// 见footer
									//chartsTypeNav.style.marginTop = footHole - 150 + 'px';
									chartsTypeNav.style.marginTop = maxHeight
											- navHeight + 'px';
								} else {
									// 未见footer
									chartsTypeNav.style.marginTop = 'auto';
								}

								var len = offsetList.length;
								var height = [];
								for (var i = 0; i < len - 1; i++) {
									height.push(offsetList[i + 1].offsetTop
											- offsetList[i].offsetTop);
								}

								var hasLightItem = false;
								for (var i = 0; i < len - 1; i++) {
									if (Math.abs(scrollHeight
											- offsetList[i].offsetTop) < (height[i] / 2)
											&& navDom[i] && !hasLightItem) {
										navDom[i].style.backgroundColor = '#3E98C5';
										hasLightItem = true;
										$(navDom[i]).children('a')[0].style.color = '#fff';
									} else {
										if (navDom[i] != undefined) {
											navDom[i].style.backgroundColor = 'transparent';
											$(navDom[i]).children('a')[0].style.color = '#3E98C5';
										}
									}
								}
							}
							var offsetList = [];
							var navDom = $('#chartsTypeNav ul:visible li');

							$('#chartsTypeNav ul').each(function(i, e) {
								$(e).find("li a").each(function(idx, dom) {
									$(dom).on('click', scroll2Pos(idx));
								});
							});

							function _resize() {

								offsetList = [];
								navDom = $('#chartsTypeNav ul:visible li');
								$('.col-md-10:visible h3')
										.each(
												function(idx, dom) {
													offsetList[idx] = {
														name : dom.childNodes[1].name,
														offsetTop : dom.childNodes[1].offsetTop
													};
												});

								if (1 == navDom.length) {
									$("#footer").css("marginTop", "300px");
								} else if (2 == navDom.length) {
									$("#footer").css("marginTop", "220px");
								} else {
									$("#footer").css("marginTop", "180px");
								}

								offsetList
										.push({
											name : 'topic',
											offsetTop : document.documentElement.scrollHeight
													- parseInt($("#footer")
															.css("marginTop")
															.replace("px", ""))
													- $("#footer").height()
													- parseInt($("#footer")
															.css("paddingTop")
															.replace("px", ""))
										})
								setTimeout(_scroll, 500);
							}
							funResize = _resize;
							function scroll2Pos(idx) {
								return function() {
									$("body,html")
											.animate(
													{
														scrollTop : offsetList[idx].offsetTop + 30
													}, 500);
									return false;
								}
							}

							$(window).on('scroll', _scroll);
							$(window).on('resize', _resize);
							_resize();
						});

		var iPlan = ${entryPlan};
		var iPredict = ${entryPredict};
		var aplan = ${approvePlan};
		var aPredict = ${approvePredict};

		init(iPlan, iPredict, aPredict, aplan, "${userName}");

		function delegateCall(obj) {
			clickli(obj);
			funResize();
		}
	</script>


</body>
</html>