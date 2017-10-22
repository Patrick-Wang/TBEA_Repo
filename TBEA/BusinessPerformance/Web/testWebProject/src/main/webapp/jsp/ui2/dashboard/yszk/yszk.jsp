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
	
	<link href="${pageContext.request.contextPath}/jsp/ui2/dashboard/tree.css"
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
<link href="${pageContext.request.contextPath}/jsp/ui2/ui2.css"
	rel="stylesheet" />
<style>
.legendColorBox {
	padding-left: 10px;
	vertical-align: middle;
	padding-top: 0px;
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
		<i class="nav-btn fa fa-angle-double-left pull-right"></i>
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
			<div class="col-lg-12 col-sm-12 col-xs-12">
				<div class="row">
					<div class="col-lg-4 col-sm-4 col-xs-4">
						<div class="widget">
							<div class="widget-header bg-primary">
								<i class="widget-icon fa fa-bars"></i> <span
									class="widget-caption">应收账款 - 账龄分析</span>
								<div class="widget-buttons">
									<a href="#" data-toggle="maximize" data-value="yszkzl"> <i
										class="fa fa-expand"></i>
									</a> 
								</div>
								<!--Widget Buttons-->
							</div>
							<!--Widget Header-->
							<div class="widget-body">
								<div id="yszkzl" data-value="1" class="chart chart-lg no-margin"></div>
							</div>
							<!--Widget Body-->
						</div>
						<!--Widget-->
					</div>
					<div class="col-lg-4 col-sm-4 col-xs-4">
						<div class="widget">
							<div class="widget-header bg-primary">
								<i class="widget-icon fa fa-bars"></i> <span
									class="widget-caption">应收账款 - 性质分析</span>
								<div class="widget-buttons">
									<a href="#" data-toggle="maximize" data-value="kxxz"> <i
										class="fa fa-expand"></i>
									</a> 
								</div>
								<!--Widget Buttons-->
							</div>
							<!--Widget Header-->
							<div class="widget-body">
								<div id="kxxz" data-value="2" class="chart chart-lg no-margin"></div>
							</div>
							<!--Widget Body-->
						</div>
						<!--Widget-->
					</div>
					<div class="col-lg-4 col-sm-4 col-xs-4">
						<div class="widget">
							<div class="widget-header bg-primary">
								<i class="widget-icon fa fa-bars"></i> <span
									class="widget-caption">逾期应收账 - 产生原因分析</span>
								<div class="widget-buttons">
									<a href="#" data-toggle="maximize" data-value="yqysz"> <i
										class="fa fa-expand"></i>
									</a> 
								</div>
								<!--Widget Buttons-->
							</div>
							<!--Widget Header-->
							<div class="widget-body">
								<div id="yqysz" data-value="4" class="chart chart-lg no-margin"></div>
							</div>
							<!--Widget Body-->
						</div>
						<!--Widget-->
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="widget">
							<div class="widget-header bg-primary">
								<i class="widget-icon fa fa-bars"></i> <span
									class="widget-caption">应收账款账面与预警值变化情况分析</span>
								<div class="widget-buttons">
									<a href="#" data-toggle="maximize" data-value="yjtz"> <i
										class="fa fa-expand"></i>
									</a>
								</div>
								<!--Widget Buttons-->
							</div>
							<!--Widget Header-->
							<div class="widget-body">
								<div class="row">
									<div class="col-sm-6 yjtz-bhqs">
										<div class="databox databox-xxlg">
											<div id="chart-yjtz"  data-value="3" class="chart chart-lg no-padding"></div>
										</div>
									</div>
									<div class="col-sm-6 yjtz-bhqs">
										<div class="databox databox-xxlg">
											<div id="chart-bhqs"  data-value="3" class="chart chart-lg no-padding"></div>
										</div>
									</div>
								</div>
							</div>
							<!--Widget Body-->
						</div>
						<!--Widget-->
					</div>
				</div>
			</div>
		</div>
		<div class="side-bar side-bar-hide">
			<div class="side-header">
				经营单位
			</div>
			<div class="side-seperater"></div>
			<div id="tree" ></div>
			<div class="side-seperater"></div>
			<div id="update" class="side-update-btn">
					确定
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
	
	var zlData = [];
	var legendData = ['5年以上',	 
    	'4-5年',	 
    	'3-4年',	 
    	'2-3年',	 
    	'1-2年',	 
    	'1年以内'];
	for (var i = 0; i < legendData.length; ++i){
		zlData.push({value : data.zl[i], name: legendData[i]});
	}
	

	var optionZl = {
	   
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	  
	    series : [
	        {
	            name: '应收账款账龄',
	            type: 'pie',
	            radius : '70%',
	            center: ['50%', '50%'],
	            data: zlData,
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};

	echarts.init(document.getElementById("yszkzl")).setOption(optionZl);
	
	
	var xzLegendDataIn = ["逾期", "未到期"];
	var xzLegendDataOut = ['逾期0-1月',	 
		'逾期1-3月	',
		'逾期3-6月	', 
		'逾期6-12月',	 
		'逾期1年以上',
		'未到期(不含质保金)',	 
		'未到期质保金'];
	var xzDataIn = [{
			name :xzLegendDataIn[0],
			value : data.kxxz[5]
	},{
		name :xzLegendDataIn[1],
		value : parseFloat(data.kxxz[6]) + parseFloat(data.kxxz[7])
	}];
	
	var xzDataOut = [{
		name :xzLegendDataOut[0],
		value : data.kxxz[0]
	},{
		name :xzLegendDataOut[1],
		value : data.kxxz[1]
	},{
		name :xzLegendDataOut[2],
		value : data.kxxz[2]
	},{
		name :xzLegendDataOut[3],
		value : data.kxxz[3]
	},{
		name :xzLegendDataOut[4],
		value : data.kxxz[4]
	},{
		name :xzLegendDataOut[5],
		value : data.kxxz[6]
	},{
		name :xzLegendDataOut[6],
		value : data.kxxz[7]
	}];
	
	var optionXz = {
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    series: [
		        {
		            name:'款项性质',
		            type:'pie',
		            selectedMode: 'single',
		            radius: [0, '40%'],

		            label: {
		                normal: {
		                    position: 'inner'
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:xzDataIn
		        },
		        {
		            name:'款项性质',
		            type:'pie',
		            radius: ['55%', '70%'],
		            data:xzDataOut
		        }
		    ]
		};
	echarts.init(document.getElementById("kxxz")).setOption(optionXz);
	
	var chartLine1;
	var chartLine2;
	
	

	
	
	 var yqysLegendData = 
		["内部因素",
	     "客户资信",
	     "滚动付款",
	     "项目变化",
	     "合同因素",
	     "手续办理", 
	     "诉讼"];
	var months = (function (){
		var curDate = new Date();
		var month = curDate.getMonth() + 1;
		var data = [];
		for (var i = month + 1; i <= 12; ++i) {
		    data.push(i + "月 ");
		}
		for (var i = 1; i <= month; ++i) {
		    data.push(i + "月");
		}
		return data;
    })();
	
	function getDataRow(data){
		for (var i = data.length - 1; i  >= 0; --i){
			var count = 0;
			for (var k = 0; k < yqysLegendData.length; ++k){
				if (parseFloat(data[i][k]) != 0){
					++count;
				}
			}
			if (count >= 3){
				return data[i];
			}
		}
		return data[data.length - 1];
	}
	var yqysData = getDataRow(window.data.yqys); 
	
	function toNumber(list){
		var ret = [];
		for (var i = 0; i < list.length; ++i){
			ret.push(parseFloat("" + list[i]));
		}
		return ret;
	}
	 
	function max(yqys){
		var max = parseFloat(yqys[0][0]);
		for (var i = 0; i < yqys.length; ++i){
			for (var k = 0; k < yqysLegendData.length; ++k){
				if (max < parseFloat(yqys[i][k])){
					max = parseFloat(yqys[i][k]);
				}
			}
		}
		return max;
	}
	
	var maxData = max([yqysData]);
	
	var optionYq = {
		    tooltip: {
		        trigger: 'item',
		        backgroundColor : 'rgba(0,0,250,0.2)'
		    },
		    radar: {
		       indicator : (function (){
		            var list = [];
		            for (var i = 0; i < yqysLegendData.length; i++) {
		                list.push({ text: yqysLegendData[i], max : maxData});
		            }
		            return list;
		        })()
		    },
		   
		    series : (function (){
		        var series = [];
		        
	            series.push({
	                name: "逾期应收账",
	                type: 'radar',
	                symbol: 'none',
	                itemStyle: {
	                    normal: {
	                        lineStyle: {
	                          width:1
	                        }
	                    },
	                    emphasis : {
	                        areaStyle: {color:'rgba(0,250,0,0.3)'}
	                    }
	                },
	                data:[{ name : "逾期应收账", value : toNumber(yqysData.slice(0, 7))}]
	            });
		        return series;
		    })()
		};
	
	
	$('.widget-buttons *[data-toggle="maximize"]').on("click", function (event) {
		
		
		if ($(event.currentTarget).find(".fa-expand").length == 0){
			if ("yjtz" == $(event.currentTarget).attr("data-value")){
				$(".yjtz-bhqs").removeClass("col-sm-6").addClass("col-sm-12");
			}else{
				$("#" + $(event.currentTarget).attr("data-value")).css("height", "100%");
			}
		}else{
			if ("yjtz" == $(event.currentTarget).attr("data-value")){
				$(".yjtz-bhqs").removeClass("col-sm-12").addClass("col-sm-6");
			}else{
				$("#" + $(event.currentTarget).attr("data-value")).removeCss("height");
			}
		}
		
		echarts.init(document.getElementById("yszkzl")).setOption(optionZl);
		echarts.init(document.getElementById("kxxz")).setOption(optionXz);
		echarts.init(document.getElementById("yqysz")).setOption(optionYq);
	    updateEchart(window.data.yjtz);
	    updateEchart1(window.data.yjtz);
	});

	echarts.init(document.getElementById("yqysz")).setOption(optionYq);
	$(window).resize(function(){
		echarts.init(document.getElementById("yszkzl")).setOption(optionZl);
		echarts.init(document.getElementById("kxxz")).setOption(optionXz);
		echarts.init(document.getElementById("yqysz")).setOption(optionYq);
	    updateEchart(window.data.yjtz);
	    updateEchart1(window.data.yjtz);
	});
	
    //-----------------------------Pie Charts-----------------------------------------//
    InitiateEasyPieChart.init();

    //-------------------定义数据、维度------------------------------------------------//
  
    //-------------------------Initiates Sparkline Chart instances in page------------------//
    //InitiateSparklineCharts.init();
    
  
    
    getYjtzData = function (yjtz) {
        var curDate = new Date();
        var month = curDate.getMonth() + 1;
        var data = [];
        for (var i = month + 1; i <= 12; ++i) {
            data.push(["上年度", i + "月"].concat(yjtz[i - month - 1]));
        }
        for (var i = 1; i <= month; ++i) {
            data.push(["本年度", i + "月"].concat(yjtz[12 - month + i - 1]));
        }
        return data;
    };
    
    updateEchart = function (data) {
        var title = "应收账款账面与预警值变化情况";
        var legendOrg = [
            "财务账面应收净收余额",
            "",
            "",
            "",
            "",
            "",
            "",
            "预警台账应收账款余额"];
        var legend = [];
        var xData = [];
        for (var i = 0; i < data.length; ++i) {
            xData.push(data[i][1]);
        }
        var tooltip = {
            trigger: 'axis',
        };
        var yAxis = [
            {
                type: 'value',
            }
        ];
        var series = [];
        for (var i = 0; i < legendOrg.length; ++i) {
            if (legendOrg[i]) {
                legend.push(legendOrg[i]);
                var rData = [];
                for (var j = 0; j < data.length; ++j) {
                    rData.push(data[j][i + 2] == "--" ? 0 : data[j][i + 2]);
                }
                series.push({
                    name: legendOrg[i],
                    type: 'line',
                    smooth: true,
                    // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data: rData
                });
            }
        }
        var option = {
/*             title: {
                text: title
            }, */
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
                    data: xData.length < 1 ? [0] : xData
                }
            ],
            yAxis: yAxis,
            series: series
        };
        chartLine1 = echarts.init(document.getElementById("chart-yjtz"));
        chartLine1.setOption(option);
    };
    updateEchart1 = function (data) {
        var title = "因素变化趋势";
        var legendOrg = [
            "",
            "",
            "货发票未开金额（加项）",
            "票开货未发金额（减项）",
            "预收款冲减应收（加项）",
            "",
            "",
            ""];
        var legend = [];
        var xData = [];
        for (var i = 0; i < data.length; ++i) {
            xData.push(data[i][1]);
        }
        var tooltip = {
            trigger: 'axis',
        };
        var yAxis = [
            {
                type: 'value',
            }
        ];
        var series = [];
        for (var i = 0; i < legendOrg.length; ++i) {
            if (legendOrg[i]) {
                legend.push(legendOrg[i]);
                var rData = [];
                for (var j = 0; j < data.length; ++j) {
                    rData.push(data[j][i + 2] == "--" ? 0 : data[j][i + 2]);
                }
                series.push({
                    name: legendOrg[i],
                    type: 'line',
                    smooth: true,
                    // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data: rData
                });
            }
        }
        var option = {
/*             title: {
                text: title
            }, */
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
                    data: xData.length < 1 ? [0] : xData
                }
            ],
            yAxis: yAxis,
            series: series
        };
        chartLine2 = echarts.init(document.getElementById("chart-bhqs"));
        chartLine2.setOption(option);
    };
    
    window.data.yjtz = getYjtzData(window.data.yjtz);
    updateEchart(window.data.yjtz);
    updateEchart1(window.data.yjtz);

    
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
            value: '输变电产业',
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
            value: '新能源产业',
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
	
	chartLine1.on("legendselectchanged", function(event){
		onEchartsEvent("legendselectchanged", event);
	});
	chartLine2.on("legendselectchanged", function(event){
		onEchartsEvent("legendselectchanged", event);
	});
	
	$(".chart").parent().on("click", function(e){
		var count = $(e.currentTarget).children().eq(0).attr("data-value");
		if (!stopClick){
			window.open(encodeURI("/BusinessManagement/yszkgb/v2/show.do?firstItem=" + count + '&breads=[{"value":"应收账款管报"}]'));
			echarts.init(document.getElementById("yszkzl")).setOption(optionZl);
			echarts.init(document.getElementById("kxxz")).setOption(optionXz);
			echarts.init(document.getElementById("yqysz")).setOption(optionYq);
		}else{
			stopClick = false;
		}
	});
	
</script>

</body>
<!--  /Body -->
</html>
