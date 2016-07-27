<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]> <html class="ie6 oldie"> <![endif]-->
<!--[if IE 7]>    <html class="ie7 oldie"> <![endif]-->
<!--[if IE 8]>    <html class="ie8 oldie"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>经营分析平台</title>
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" type="text/css">
<!-- 
要详细了解文件顶部 html 标签周围的条件注释:
paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/

如果您使用的是 modernizr (http://www.modernizr.com/) 的自定义内部版本，请执行以下操作:
* 在此处将链接插入 js 
* 将下方链接移至 html5shiv
* 将“no-js”类添加到顶部的 html 标签
* 如果 modernizr 内部版本中包含 MQ Polyfill，您也可以将链接移至 respond.min.js 
-->
<!--[if lt IE 9]>
<script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<script src="${pageContext.request.contextPath}/css/respond.min.js"></script>
</head>
<body>
	<div class="gridContainer clearfix">
		<div id="LayoutDiv1">
			<!--<div id="title_bg_div"><img id="title_img" src="img/detailstitle_center.png" max-width: 99%;></div>-->
			<div id="func_div">
				<div class="children">
					<a
						href="${pageContext.request.contextPath}/ydzb/hzb_zbhz_mobile.do"><img
						src="${pageContext.request.contextPath}/img/func1.png"
						name="func_img1" id="func_img1"></a>
					<div class="func_span">经营指标完成情况</div>
				</div>
				<div class="children">
					<a href="${pageContext.request.contextPath}/futures/show.do"><img
						src="${pageContext.request.contextPath}/img/func2.png"
						name="func_img2" id="func_img2"></a>
					<div class="func_span">期货套保</div>
				</div>
				<div class="children">
					<a href="${pageContext.request.contextPath}/yszkrb/yszk_mobile.do"><img
						src="${pageContext.request.contextPath}/img/func3.png"
						name="func_img2" id="func_img2"></a>
					<div class="func_span">应收账款日报</div>
				</div>
				<!-- <div class="children">
                    <img src="img/func3.png">
                    <div class="func_span">现金流日报</div>
                </div> -->
			</div>
			<!--<div id="bottom_bg_div"></div>-->
		</div>
	</div>
</body>
</html>
