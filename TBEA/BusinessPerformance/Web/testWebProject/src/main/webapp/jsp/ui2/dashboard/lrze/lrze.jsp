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

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/jsp/ui2/img/logo.png"
	type="image/x-icon">
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
<link id="beyond-link"
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/beyond.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/demo.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2/assets/css/typicons.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/jsp/ui2${pageContext.request.contextPath}/jsp/ui2/assets/t" />
<link id="skin-link" href="" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/jsp/ui2/tree/tree.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/jsp/ui2/dashboard/tree.css"
	rel="stylesheet">
<!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
<script
	src="${pageContext.request.contextPath}/jsp/ui2/assets/js/skins.min.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/assets/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery.easing.min.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jqueryex.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/pages/util.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/pages/dateSelector.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/jsp/ui2/ui2.css"
	rel="stylesheet" />
<style>
.legendColorBox {
	padding-left: 10px;
	vertical-align: middle;
	padding-top: 0px;
}

.chart-lg2 {
	height: 590px;
}
</style>
<title></title>
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
	<script>
	var breads = JSON.parse('${param.breads}');
	Util.Breadcrumb.render(breads);
	if (Util.isIframe()) {
		Util.Breadcrumb.setOnClickListener(function(breadNode) {
			/* if (breadNode.url){
				for (var i = 0; i < breads.length; ++i){
					if (breads[i] == breadNode){
						breads = breads.slice(0, i + 1);
						break;
					}
				}
				var paramCon = breadNode.url.indexOf("?") > 0 ? "&" : "?";
				window.location.href = breadNode.url + paramCon + "breads=" + JSON.stringify(breads);
			}else{
				window.parent['onClickBreadcrumb'] && window.parent['onClickBreadcrumb'](breadNode);
			} */
		});
	}
	$("title").text(breads[1].value + "-" + (breads.length - 2));
</script>
	<!-- Page Body -->
	<div class="page-body">
		<div class="row">
			<div class="col-lg-7 col-sm-7 col-xs-7">

				<div class="widget">
					<div class="widget-header bg-primary">
						<i class="widget-icon fa fa-bars"></i> <span
							class="widget-caption">利润总额-趋势分析</span>
						<div class="widget-buttons">
							<a href="#" data-toggle="maximize" data-value="1"> <i
								class="fa fa-expand"></i>
							</a>
						</div>
						<!--Widget Buttons-->
					</div>
					<!--Widget Header-->
					<div class="widget-body">
						<div id="chart-qsfx" data-value="1" class="chart chart-lg no-margin"></div>
					</div>
					<!--Widget Body-->
				</div>
				<!--Widget-->

				<div class="widget">
					<div class="widget-header bg-primary">
						<i class="widget-icon fa fa-bars"></i> <span
							class="widget-caption">利润总额-行业对标</span>
						<div class="widget-buttons">
							<i	class="fa fa-refresh" style="cursor:pointer" data-value="byq"></i>
							<a href="#" data-toggle="maximize" data-value="2"> <i
								class="fa fa-expand"></i>
							</a>
						</div>
						<!--Widget Buttons-->
					</div>
					<!--Widget Header-->
					<div class="widget-body">
						<div id="chart-hydb" data-value="2" class="chart chart-lg no-margin"></div>
					</div>
					<!--Widget Body-->
				</div>
				<!--Widget-->

			</div>
			<div class="col-lg-5 col-sm-5 col-xs-5">
				<div class="widget">
					<div class="widget-header bg-primary">
						<i class="widget-icon fa fa-bars"></i> <span
							class="widget-caption">累计利润占比分析</span>
						<div class="widget-buttons">
							<a href="#" data-toggle="maximize" data-value="3"> <i
								class="fa fa-expand"></i>
							</a>
						</div>
						<!--Widget Buttons-->
					</div>
					<!--Widget Header-->
					<div class="widget-body">
						<div id="chart-lrfx" data-value="2" class="chart chart-lg2 no-margin"></div>
					</div>
					<!--Widget Body-->
				</div>
				<!--Widget-->
			</div>
		</div>
	</div>
	<!-- /Page Body -->




	<!--Basic Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/slimscroll/jquery.slimscroll.min.js"></script>

	<!--Beyond Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/beyond.js"></script>

	<!--Page Related Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/easypiechart/jquery.easypiechart.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/easypiechart/easypiechart-init.js"></script>

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

	<!--morris needed scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/morris/raphael-2.0.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/morris/morris.js"></script>
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/morris/morris-init.js"></script>

	<!--Sparkline Charts Needed Scripts-->
	<script
		src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/sparkline/jquery.sparkline.js"></script>
	<script
		src="/BusinessManagement/jsp/ui2/pages/www2/js/echarts-plain-2-0-0.js"></script>


	<script
		src="${pageContext.request.contextPath}/jsp/ui2/tree/treeNode.js"></script>
	<script src="${pageContext.request.contextPath}/jsp/ui2/tree/tree.js"></script>
	<script>
	
	
	window.data = JSON.parse('${data}');
	
	var dtLastYear = Util.addYear(Util.now(), -1);
	var xData = [];
	
	for (var i = 12; i >= 0; --i){
		xData.push((dtLastYear.year + "-" + dtLastYear.month).substring(2));
		dtLastYear = Util.addMonth(dtLastYear, 1);
	}
	
	

     var legend = ["计划", "实际"];

     var tooltip = {
         trigger: 'axis',
     };
     var yAxis = [
         {
             type: 'value',
         }
     ];
     var series = [{
         name: legend[0],
         type: 'line',
         smooth: true,
         // itemStyle: {normal: {areaStyle: {type: 'default'}}},
         data: data.qsfx.jh
     },{
         name: legend[1],
         type: 'line',
         smooth: true,
         // itemStyle: {normal: {areaStyle: {type: 'default'}}},
         data: data.qsfx.sj
     }];
    
     var optionQsfx = {
         tooltip: tooltip,
         legend: {
             data: legend
         },
         toolbox: {
             show: true,
         },
         calculable: false,
         xAxis: [
             {
                 type: 'category',
                 boundaryGap: false,
                 data: xData
             }
         ],
         yAxis: yAxis,
         series: series
     }; 
    var qsfx = echarts.init(document.getElementById("chart-qsfx"));
    qsfx.setOption(optionQsfx);
	
    
    var large = false;
    function setLrfx(){
	    var optionLrfx = {
	    	    tooltip: {
	    	        trigger: 'item',
	    	        formatter: "{a} <br/>{b}: {c} ({d}%)"
	    	    },
	    	    legend: {
	    	    	orient: large ? 'vertical': 'horizontal',
	    	        x: 'left',
	    	        data: data.lrfx.zbmcs
	    	    },
	    	    series: [
	    	        {
	    	            name:'累计利润',
	    	            type:'pie',
	    	            radius: ['50%', '65%'],
	    	            data: (function(){
	    	            	var retData = [];
	    	            	for (var i = 0; i < data.lrfx.zbmcs.length; ++i){
	    	            		retData.push({
	    	            			value : data.lrfx.zbfx[i],
	    	            			name : data.lrfx.zbmcs[i]
	    	            		});
	    	            	}
	    	            	return retData;
	    	            })()
	    	        }
	    	    ]
	    	};
	    
	    var lrfx = echarts.init(document.getElementById("chart-lrfx"));
	    lrfx.setOption(optionLrfx);
    }
    
    setLrfx();
    
    $(".fa-refresh").on("click", function(){
    	if ('byq' == $(".fa-refresh").attr("data-value")){
    		$(".fa-refresh").attr("data-value", 'xl');
    	}else{
    		$(".fa-refresh").attr("data-value", 'byq');
    	}
    	switchHy();
    });

    
    function switchHy(){
    	if ('byq' == $(".fa-refresh").attr("data-value")){
    		var legend = ["行业均值", "沈变公司", "衡变公司", "新变厂", "变压器产业"];

    	     var tooltip = {
    	         trigger: 'axis',
    	     };
    	     var yAxis = [
    	         {
    	             type: 'value',
    	         }
    	     ];
    	     var series = [{
    	         name: legend[0],
    	         type: 'line',
    	         smooth: true,
    	         data: data.hydb.byqhyjzs
    	     },{
    	         name: legend[1],
    	         type: 'bar',
    	         smooth: true,
    	         data: data.hydb.sblrs
    	     },{
    	         name: legend[2],
    	         type: 'bar',
    	         smooth: true,
    	         data: data.hydb.hblrs
    	     },{
    	         name: legend[3],
    	         type: 'bar',
    	         smooth: true,
    	         data: data.hydb.xblrs
    	     },{
    	         name: legend[4],
    	         type: 'bar',
    	         smooth: true,
    	         data: data.hydb.byqlrs
    	     }];
    	    
    	     var optionHydb = {
    	    		 title: {
    	    		        text: '变压器产业'
    	    		    },
    	         tooltip: tooltip,
    	         legend: {
    	             data: legend
    	         },
    	         toolbox: {
    	             show: true,
    	         },
    	         calculable: false,
    	         xAxis: [
    	             {
    	                 type: 'category',
    	                 boundaryGap: true,
    	                 data: data.hydb.rqs
    	             }
    	         ],
    	         yAxis: yAxis,
    	         series: series
    	     }; 
    	    var hydb = echarts.init(document.getElementById("chart-hydb"));
    	    hydb.setOption(optionHydb);
    	}else{
    		var legend = ["行业均值", "鲁缆公司", "新缆厂", "德缆公司", "线缆产业"];

   	     var tooltip = {
   	         trigger: 'axis',
   	     };
   	     var yAxis = [
   	         {
   	             type: 'value',
   	         }
   	     ];
   	     var series = [{
   	         name: legend[0],
   	         type: 'line',
   	         smooth: true,
   	         data: data.hydb.xlhyjzs
   	     },{
   	         name: legend[1],
   	         type: 'bar',
   	         smooth: true,
   	         data: data.hydb.lllrs
   	     },{
   	         name: legend[2],
   	         type: 'bar',
   	         smooth: true,
   	         data: data.hydb.xllrs
   	     },{
   	         name: legend[3],
   	         type: 'bar',
   	         smooth: true,
   	         data: data.hydb.dllrs
   	     },{
   	         name: legend[4],
   	         type: 'bar',
   	         smooth: true,
   	         data: data.hydb.xlcylrs
   	     }];
   	    
   	     var optionHydb = {
   	    		 title: {
	    		        text: '线缆产业'
	    		    },
   	         tooltip: tooltip,
   	         legend: {
   	             data: legend
   	         },
   	         toolbox: {
   	             show: true,
   	         },
   	         calculable: false,
   	         xAxis: [
   	             {
   	                 type: 'category',
   	                 boundaryGap: true,
   	                 data: data.hydb.rqs
   	             }
   	         ],
   	         yAxis: yAxis,
   	         series: series
   	     }; 
   	    var hydb = echarts.init(document.getElementById("chart-hydb"));
   	    hydb.setOption(optionHydb);
    	}
    }
    
	
    switchHy();
    
    
    $(window).resize(function(){
    	qsfx = echarts.init(document.getElementById("chart-qsfx"));
        qsfx.setOption(optionQsfx);
        setLrfx();
        switchHy();
	});
    
$('.widget-buttons *[data-toggle="maximize"]').on("click", function (event) {
		
		
 		if ($(event.currentTarget).find(".fa-expand").length == 0){
 			large = true;
		}else{
			large = false;
		} 
		qsfx = echarts.init(document.getElementById("chart-qsfx"));
	    qsfx.setOption(optionQsfx);
        setLrfx();
	    switchHy();
	});

    
    $(".page-body").on("click", function(){
		if ($(".nav-btn").hasClass("nav-btn-active")){
			$(".nav-btn").trigger("click");
		}
	});
	
	$(".side-bar").css("margin-right", -$(".side-bar").width() + "px")
	.on("click", function(e){
		e.stopPropagation();
	})
	.on("mouseover", function(e){
		e.stopPropagation();
	});
	
	$(".nav-btn").on("click", function() {
		if ($(".nav-btn").hasClass("nav-btn-active")) {
			$(".nav-btn").removeClass("nav-btn-active");
			$(".nav-btn").removeClass("fa-angle-double-right");
			$(".nav-btn").addClass("fa-angle-double-left");
			$(".side-bar").animate({
				marginRight : -$(".side-bar").width() + "px"
			}, {
				duration : 'fast',
				easing : 'easeInQuart',
				done : function() {
					$(".side-bar").addClass("side-bar-hide");
				},
				step : function() {

				}
			});
		} else {
			$(".nav-btn").addClass("nav-btn-active");
			$(".side-bar").removeClass("side-bar-hide");
			$(".nav-btn").addClass("fa-angle-double-right");
			$(".nav-btn").removeClass("fa-angle-double-left");

			$(".side-bar").animate({
				marginRight : "0px"
			}, {
				duration : 'fast',
				easing : 'easeOutQuart',
				done : function() {

				},
				step : function() {

				}
			});
		}
	});
	
	var id = 1000;
	
	function nextId(){
		return ++id;
	}
	
	
	var getIcon = function(treeNode){
		if (treeNode.data.checked === true){
			return "fa fa-check-square-o tree-checkbox";
		}
		
		if (treeNode.data.checked === false){
			return "fa fa-square-o tree-checkbox";
		}
		
		return "fa fa-square tree-checkbox";
	}
	
	
	var leftTree = new tree.Tree("tree");
	var treeNodes = leftTree.render([{
        data : {
            id : nextId(),
            value: '变压器产业',
            icon : getIcon,
            iconOpen : getIcon,
            extracted:true,
            checked : true
        },
        subNodes:[
        	{
    	        data : {
    	            id : nextId(),
    	            value: '沈变公司',
    	            icon : getIcon,
    	            iconOpen : getIcon,
    	            checked : true
    	        }
    	    },
        	{
    	        data : {
    	            id : nextId(),
    	            value: '衡变公司',
    	            icon : getIcon,
    	            iconOpen : getIcon,
    	            checked : true
    	        }
    	    },
        	{
    	        data : {
    	            id : nextId(),
    	            value: '新变厂',
    	            icon : getIcon,
    	            iconOpen : getIcon,
    	            checked : true
    	        }
    	    },
        	{
    	        data : {
    	            id : nextId(),
    	            value: '鲁缆公司',
    	            icon : getIcon,
    	            iconOpen : getIcon,
    	            checked : true
    	        }
    	    },
        	{
    	        data : {
    	            id : nextId(),
    	            value: '新缆厂',
    	            icon : getIcon,
    	            iconOpen : getIcon,
    	            checked : true
    	        }
    	    },
        	{
    	        data : {
    	            id : nextId(),
    	            value: '德缆公司',
    	            icon : getIcon,
    	            iconOpen : getIcon,
    	            checked : true
    	        }
    	    }
        ]
    },{
        data : {
            id : nextId(),
            value: '能源产业',
            icon : getIcon,
            iconOpen : getIcon,
            extracted:true,
            checked : true
        },
        subNodes:[
        	{
    	        data : {
    	            id : nextId(),
    	            value: '新能源公司',
    	            icon : getIcon,
    	            iconOpen : getIcon,
    	            checked : true
    	        }
    	    },
        	{
    	        data : {
    	            id : nextId(),
    	            value: '新特能源公司',
    	            icon : getIcon,
    	            iconOpen : getIcon,
    	            checked : true
    	        }
    	    },
        	{
    	        data : {
    	            id : nextId(),
    	            value: '天池能源公司',
    	            icon : getIcon,
    	            iconOpen : getIcon,
    	            checked : true
    	        }
    	    },
        	{
    	        data : {
    	            id : nextId(),
    	            value: '能动公司',
    	            icon : getIcon,
    	            iconOpen : getIcon,
    	            checked : true
    	        }
    	    }
        ]
    },
	{
        data : {
            id : nextId(),
            value: '众和公司',
            icon : getIcon,
            iconOpen : getIcon,
            checked : true
        }
    },
	{
        data : {
            id : nextId(),
            value: '进出口公司',
            icon : getIcon,
            iconOpen : getIcon,
            checked : true
        }
    },
	{
        data : {
            id : nextId(),
            value: '国际工程公司',
            icon : getIcon,
            iconOpen : getIcon,
            checked : true
        }
    }]);
	leftTree.setOnClickListener(function(treeNode){
		
		if (true === treeNode.data.checked){
			treeNode.accept({
	            visit: function(node){
	            	node.getData().checked = false;
	            	leftTree.refresh(node);
	                return false;
	            }
	        });
	        
			var node = treeNode;
            while (node.getParent()) {
                node = node.getParent();
                var checked = false;
                for (var i = 0; i < node.subNodes.length; ++i){
                	if (false !== node.subNodes[i].data.checked){
                		checked = "part";
                		break;
                	}
                }
                node.data.checked = checked;
                leftTree.refresh(node);
            }
            
		}else{
			treeNode.accept({
	            visit: function(node){
	            	node.getData().checked = true;
	            	leftTree.refresh(node);
	                return false;
	            }
	        });
	        
			var node = treeNode;
            while (node.getParent()) {
                node = node.getParent();
                var checked = true;
                for (var i = 0; i < node.subNodes.length; ++i){
                	if (true !== node.subNodes[i].data.checked){
                		checked = "part";
                		break;
                	}
                }
                node.data.checked = checked;
                leftTree.refresh(node);
            }
		}
		return true;
	});
	
	var stopClick = false;
	function onEchartsEvent(name, event){
		stopClick = true;
	}
	
/* 	chartLine1.on("legendselectchanged", function(event){
		onEchartsEvent("legendselectchanged", event);
	});
	chartLine2.on("legendselectchanged", function(event){
		onEchartsEvent("legendselectchanged", event);
	});
	 */
/* 	$(".chart").parent().on("click", function(e){
		var count = $(e.currentTarget).children().eq(0).attr("data-value");
		if (!stopClick){
			window.open("/BusinessManagement/yszkgb/v2/show.do?firstItem=" + count + '&breads=[{"value":"应收账款管报"}]');
			echarts.init(document.getElementById("yszkzl")).setOption(optionZl);
			echarts.init(document.getElementById("kxxz")).setOption(optionXz);
			echarts.init(document.getElementById("yqysz")).setOption(optionYq);
		}else{
			stopClick = false;
		}
	}); */
	
</script>

</body>
<!--  /Body -->
</html>
