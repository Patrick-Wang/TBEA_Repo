﻿<%@ page language="java" contentType="text/html; charset=utf-8"
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

    <!--Basic Styles-->
    <link href="${pageContext.request.contextPath}/jsp/ui2/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/jsp/ui2/assets/css/font-awesome.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/jsp/ui2/assets/css/weather-icons.min.css" rel="stylesheet" />


    <!--Beyond styles-->
    <link id="beyond-link" href="${pageContext.request.contextPath}/jsp/ui2/assets/css/beyond.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/jsp/ui2/assets/css/demo.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/jsp/ui2/assets/css/typicons.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/jsp/ui2${pageContext.request.contextPath}/jsp/ui2/assets/t" />
    <link id="skin-link" href="" rel="stylesheet" type="text/css" />

    <!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
    <script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/skins.min.js"></script>
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


<!-- Main Container -->
<div class="main-container container-fluid">
    <!-- Page Container -->
    <div class="page-container">
            <!-- Page Body -->
            <div class="page-body">
                <div class="row">
                    <div class="col-lg-12 col-sm-12 col-xs-12">
                        <div class="row">
                            <div class="col-lg-4 col-sm-4 col-xs-4">
                                <div class="widget">
                                    <div class="widget-header bg-primary">
                                        <i class="widget-icon fa fa-bars"></i>
                                        <span class="widget-caption">应收账款 - 账龄分析</span>
                                        <div class="widget-buttons">
                                            <a href="#" data-toggle="maximize" data-value="yszkzl">
                                                <i class="fa fa-expand"></i>
                                            </a>
                                            <a href="#">
                                                <i class="fa fa-eye" data-value="1"></i>
                                            </a>
                                        </div><!--Widget Buttons-->
                                    </div><!--Widget Header-->
                                    <div class="widget-body">
                                        <div id="yszkzl" class="chart chart-lg no-margin"></div>
                                    </div><!--Widget Body-->
                                </div><!--Widget-->
                            </div>
                            <div class="col-lg-4 col-sm-4 col-xs-4">
                                <div class="widget">
                                    <div class="widget-header bg-primary">
                                        <i class="widget-icon fa fa-bars"></i>
                                        <span class="widget-caption">应收账款 - 性质分析</span>
                                        <div class="widget-buttons">
                                            <a href="#" data-toggle="maximize" data-value="kxxz">
                                                <i class="fa fa-expand"></i>
                                            </a>
                                            <a href="#">
                                                <i class="fa fa-eye" data-value="2"></i>
                                            </a>
                                        </div><!--Widget Buttons-->
                                    </div><!--Widget Header-->
                                    <div class="widget-body">
                                        <div id="kxxz" class="chart chart-lg no-margin"></div>
                                    </div><!--Widget Body-->
                                </div><!--Widget-->
                            </div>
                            <div class="col-lg-4 col-sm-4 col-xs-4">
                                <div class="widget">
                                    <div class="widget-header bg-primary">
                                        <i class="widget-icon fa fa-bars"></i>
                                        <span class="widget-caption">逾期应收账 - 产生原因分析</span>
                                        <div class="widget-buttons">
                                            <a href="#" data-toggle="maximize" data-value="yqysz">
                                                <i class="fa fa-expand"></i>
                                            </a>
                                            <a href="#">
                                                <i class="fa fa-eye" data-value="4"></i>
                                            </a>
                                        </div><!--Widget Buttons-->
                                    </div><!--Widget Header-->
                                    <div class="widget-body">
                                        <div id="yqysz" class="chart chart-lg no-margin"></div>
                                    </div><!--Widget Body-->
                                </div><!--Widget-->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget">
                                    <div class="widget-header bg-primary">
                                        <i class="widget-icon fa fa-bars"></i>
                                        <span class="widget-caption">应收账款账面与预警值变化情况分析</span>
                                        <div class="widget-buttons">
                                            <a href="#" data-toggle="maximize" data-value="yjtz">
                                                <i class="fa fa-expand"></i>
                                            </a>
                                            <a href="#">
                                                <i class="fa fa-eye" data-value="3"></i>
                                            </a>
                                        </div><!--Widget Buttons-->
                                    </div><!--Widget Header-->
                                    <div class="widget-body">
                                        <div class="row">
                                            <div class="col-sm-6 yjtz-bhqs">
                                                <div class="databox databox-xxlg">
                                                     <div id="chart-yjtz" class="chart chart-lg no-padding"></div>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 yjtz-bhqs">
                                                <div class="databox databox-xxlg">
                                                    <div id="chart-bhqs" class="chart chart-lg no-padding"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div><!--Widget Body-->
                                </div><!--Widget-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Page Body -->
    </div>
    <!-- /Page Container -->
    <!-- Main Container -->

</div>

<!--Basic Scripts-->
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/jsp/ui2/jquery/jqueryex.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/slimscroll/jquery.slimscroll.min.js"></script>

<!--Beyond Scripts-->
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/beyond.js"></script>

<!--Page Related Scripts-->
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/easypiechart/jquery.easypiechart.js"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/easypiechart/easypiechart-init.js"></script>

<!--Flot Charts Needed Scripts-->
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/flot/jquery.flot.js"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/flot/jquery.flot.resize.js"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/flot/jquery.flot.pie.js"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/flot/jquery.flot.tooltip.js"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/flot/jquery.flot.orderbars.js"></script>

<!--morris needed scripts-->
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/morris/raphael-2.0.2.min.js"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/morris/morris.js"></script>
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/morris/morris-init.js"></script>

<!--Sparkline Charts Needed Scripts-->
<script src="${pageContext.request.contextPath}/jsp/ui2/assets/js/charts/sparkline/jquery.sparkline.js"></script>
<script src="/BusinessManagement/jsp/ui2/pages/www2/js/echarts-plain-2-0-0.js"></script>
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
	
	$(".fa-eye").on("click", function(e){
		var count = $(e.currentTarget).attr("data-value");		
		window.parent ? window.parent.triggerClickClose('/BusinessManagement/yszkgb/v2/show.do?', function(){
			window.parent ? window.parent.triggerClickUrl("/BusinessManagement/yszkgb/v2/show.do?firstItem=" + count) : undefined;
		}) : undefined;
	});
	
	
	 var yqysLegendData = 
		["内部因素",
	     "客户资信",
	     "滚动付款",
	     "项目变化",
	     "合同因素",
	     "手续办理", 
	     "诉讼"]
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
        echarts.init(document.getElementById("chart-yjtz")).setOption(option);
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
        echarts.init(document.getElementById("chart-bhqs")).setOption(option);
    };
    
    window.data.yjtz = getYjtzData(window.data.yjtz);
    updateEchart(window.data.yjtz);
    updateEchart1(window.data.yjtz);

</script>

</body>
<!--  /Body -->
</html>