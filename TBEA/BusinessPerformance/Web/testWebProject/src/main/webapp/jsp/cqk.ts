/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module cqk {

    class JQGridAssistantFactory {

        public static createTable(gridName: string, year : number): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("客户所属行业", "khsshy", true, JQTable.TextAlign.Left),
                new JQTable.Node("客户所属行业", "khsshy_1", true, JQTable.TextAlign.Left),
                new JQTable.Node(year - 4 + "年及以前", "n4n"),
                new JQTable.Node(year - 3 + "年", "n3n"),
                new JQTable.Node(year - 2 + "年", "n2n"),
                new JQTable.Node("合计", "hj")
            ], gridName);
        }
    }

    export class View {
        private static ins: View;

        public static newInstance(): View {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        }
        private currentSelected : number = 0;
        private mEchartIdPie: string;
        private mMonth: number;
        private mEchartIdSquire: string;
        private mEchartIdLine: string;
        private mYear: number;
        private mComp: Util.CompanyType = Util.CompanyType.SB;
        private mLineData: Array<string[]>;
        private mTableData: Array<string[]>;
        private mDataSet : Util.DateDataSet;
        private mTableId : string;
        public init(echartIdPie: string, echartIdSquire: string, echartIdLine: string, tableId: string, args: any[]): void {
            this.mMonth = args[0];
            this.mYear = args[1];
            this.mTableId = tableId;
            this.mEchartIdLine = echartIdLine;
            this.mEchartIdPie = echartIdPie;
            this.mEchartIdSquire = echartIdSquire;
            this.mDataSet = new Util.DateDataSet("cqk_update.do");
            this.updateTable(this.mTableId);
            this.updateUI();
        }
        
        

 		public onYearSelected(year : number){
        	this.mYear = year;
        }
        
        public onMonthSelected(month : number){
        	this.mMonth = month;
        }
        
        public onCompanySelected(comp : Util.CompanyType){
        	this.mComp = comp;
        }
        
		public updateUI(){
		 	this.mDataSet.getDataByCompany(this.mMonth, this.mYear, this.mComp, (data : string) =>{
				if (null != data){
					var arr = data.split("##");
					this.mTableData = JSON.parse(arr[0]);
					this.mLineData = JSON.parse(arr[1]);
					$('h1').text(this.mYear + "年" + this.mMonth + "月  陈欠款");
					document.title = this.mYear + "年" + this.mMonth + "月  陈欠款";
					this.updateTable(this.mTableId);
		            this.updatePieEchart(this.mEchartIdPie);
		            this.updateLineEchart(this.mEchartIdLine);
		            this.updateSquareEchart(this.mEchartIdSquire);
				}
			});
		}
        
        

        public onSelected(i: number) {
        	this.currentSelected = i;
            this.updateLineEchart(this.mEchartIdLine);
            this.updateSquareEchart(this.mEchartIdSquire);
        }

        private updateSquareEchart(echart: string): void {
			var data = [];
            var month: string[] = [];
            if (data == null) {
                data = [];
                data.push([]);
                data.push([]);
                data.push([]);
                for (var i = 1; i <= this.mMonth; ++i) {
                    month.push(i + "月");
                    data[0].push(Math.floor(Math.random() * (1000 + 1)) + "");
                    data[1].push(Math.floor(Math.random() * (1000 + 1)) + "");
                    data[2].push(Math.floor(Math.random() * (1000 + 1)) + "");
                }
            }
            else {
            	data.push(this.mLineData[this.currentSelected * 5 + 2]);
            	data.push(this.mLineData[this.currentSelected * 5 + 3]);
            	data.push(this.mLineData[this.currentSelected * 5 + 4]);
                for (var i = 1; i <= this.mMonth; ++i) {
                    month.push(i + "月");
                }
            }

      
            var legend = ["陈欠4年及以上", "陈欠3年", "陈欠2年"];
            var ser = [];
            for (var i = 0; i < legend.length; ++i) {
                ser.push({
                    name: legend[i],
                    type: 'line',
                    smooth: true,
                    stack: "金额",
                    itemStyle: { normal: { areaStyle: { type: 'default' } } },
                    data: data[i]
                })
            }

            var option = {
				title : {
        			text: '行业陈欠款趋势'
    			},
                tooltip: {
                    trigger: 'axis'
                },
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
                        data: month
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: ser
            }

            echarts.init($('#' + echart)[0]).setOption(option);

        }

        private updateLineEchart(echart: string): void {
			var data = [];
            var month: string[] = [];
            if (data == null) {
                data = [];
                data.push([]);
                data.push([]);

                for (var i = 1; i <= this.mMonth; ++i) {
                    month.push(i + "月");
                    data[0].push(Math.floor(Math.random() * (1000 + 1)) + "");
                    data[1].push(Math.floor(Math.random() * (1000 + 1)) + "");
                }
            }
            else {
            	data.push(this.mLineData[this.currentSelected * 5]);
            	data.push(this.mLineData[this.currentSelected * 5 + 1]);
                for (var i = 1; i <= this.mMonth; ++i) {
                    month.push(i + "月");
                }
            }
            var legend = [this.mYear - 1 + "年", this.mYear + "年"];
            //var chart: ECharts.Chart = new ECharts.Chart(new ECharts.XAxis(month), new ECharts.YAxis());
           // chart.setLegend(new ECharts.Legend([this.mYear - 1 + "年", this.mYear + "年"], ECharts.LegendX.center));

            //var ser: ECharts.Line.SeriesImpl = new ECharts.Line.SeriesImpl(this.mYear - 1 + '年', data[0]);
            //chart.addSeries(ser);

            //ser = new ECharts.Line.SeriesImpl(this.mYear + '年', data[1]);
            //chart.addSeries(ser);

            //chart.update(echart);

            var ser = [];
            for (var i = 0; i < legend.length; ++i) {
                ser.push({
                    name: legend[i],
                    type: 'line',
                    smooth: true,
                    data: data[i]
                })
            }


            var option = {
				title : {
        			text: '行业陈欠款同期对比'
    			},
                tooltip: {
                    trigger: 'axis'
                },
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
                        data: month
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: ser
            }

            echarts.init($('#' + echart)[0]).setOption(option);

        }
        private updatePieEchart(echart: string): void {
       	 	var data = this.mTableData;

            var legend = ["国网、南网", "省、市电力公司", "五大发电", "其他电源", "石油石化", "轨道交通","出口合同", "其他"];
   			var dljpt = 0;
    		for (var i = 0; i < 4; ++i) {
                	dljpt += parseInt(this.mTableData[i][3])
            }
   			var dataIn = [
                { name: "电力 \r\n及配套",  value: dljpt},
                { name: "", value: parseInt(this.mTableData[4][3]) },
                { name: " ", value: parseInt(this.mTableData[5][3]) },
                { name: "  ", value: parseInt(this.mTableData[6][3]) },
                { name: "   ",  value: parseInt(this.mTableData[7][3]) }
            ];
//            var dataIn = [
//                { name: " 电力 \r\n及配套", value: Math.random() * (1000 + 1) },
//                { name: "石油\r\n石化", value: Math.random() * (1000 + 1) },
//                { name: "制造\r\n行业", value: Math.random() * (1000 + 1) },
//                { name: "    铁路    \r\n（轨道交通）", value: Math.random() * (1000 + 1) },
//                { name: " 出口\r\n合同", value: Math.random() * (1000 + 1) },
//                { name: "其它", value: Math.random() * (1000 + 1) }
//            ];

            var dataOut = [];
            for (var i = 0; i < legend.length; ++i) {
                //dataOut.push({ name: legend[i], value: Math.random() * (1000 + 1) });
                dataOut.push({ name: legend[i], value: this.mTableData[i][3] });
            }

            //var dataIn = [{ name: "  电力\r\n及配套", value: Math.random() * (1000 + 1) },
            //    { name: "出口\r\n合同", value: Math.random() * (1000 + 1) },
            //    { name: "其它", value: Math.random() * (1000 + 1) }];

            var option = {
            	title : {
        			text: '行业占比'
    			},
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    x: "left",
                    y: '40',
                    data: legend,
                    orient: "vertical"
                },
                toolbox: {
                    show: true,
                },
                calculable: false,
                series: [
                    {
                        name: "1",
                        type: 'pie',
                        radius: [100, 130],
                        data: dataOut
                    }, {
                        name: "2",
                        type: 'pie',
                        radius: [0, 60],
                        itemStyle : {
			                normal : {
			                    label : {
			                        position : 'inner'
			                    },
			                    labelLine : {
			                        show : false
			                    }
			                }
		                },
                        data: dataIn
                    }
                ]
            }

            echarts.init($('#' + echart)[0]).setOption(option);

        }


        //private initEchart(echart): void {
        //    var ysyq_payment_Chart = echarts.init(echart)
        //    var ysyq_payment_Option = {
        //        animation: true,
        //        tooltip: {
        //            trigger: 'axis',
        //            /* formatter : "{b}<br/>{a} : {c} 万元<br/>{a1} : {c1} 万元", */

        //            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
        //                type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
        //            }
        //        },
        //        legend: {
        //            x: 'right',
        //            data: ["合同金额", "预期阶段", "中标阶段", "完工阶段"],

        //        },
        //        xAxis: [{
        //            type: 'category',
        //            data: ['沈变', '衡变', '新变', '天变']
        //        }],
        //        yAxis: [{
        //            type: 'value'

        //        }],

        //        calculable: true,
        //        series: [{
        //            name: '合同金额',
        //            type: 'bar',

        //            barCategoryGap: "50%",
        //            data: [63363.11, 55628.27, 58521.55, 69100.58]
        //        }, {
        //                name: '预期阶段',
        //                type: 'bar',

        //                stack: '阶段',
        //                data: [9098.58, 1240.13, 1140.61, 3154.82]
        //            }, {
        //                name: '中标阶段',

        //                type: 'bar',
        //                stack: '阶段',
        //                data: [3934.13, 3200.22, 1382.52, 3934.13]
        //            }, {
        //                name: '完工阶段',
        //                type: 'bar',

        //                stack: '阶段',
        //                data: [11980.74, 2240.18, 3487.11, 6980.74]
        //            }]
        //    };
        //    ysyq_payment_Chart.setOption(ysyq_payment_Option);
        //}

        private updateTable(name: string): void {
        	var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mYear);
            tableAssist.mergeTitle();
         
            tableAssist.mergeRow(0);
           tableAssist.mergeColum(0, 4);
           tableAssist.mergeColum(0, 5);
           tableAssist.mergeColum(0, 6);
           tableAssist.mergeColum(0, 7);
           tableAssist.mergeColum(0, 8);
            var data = [
               	["电力及配套","国网、南网"],
				["电力及配套","省、市电力系统"],
				["电力及配套","五大发电"],
				["电力及配套","其它"],
				["石油","石化"],
				["轨道","交通"],
				["出口","合同"],
				["其","他"],
				["合","计"]
			];

//			for (var i = 0; i < data.length; ++i){
//				data[i] = data[i].concat(this.mTableData[i]);
//			}
		
			if (undefined != this.mTableData)
			{
	            var row = [];
	            for (var i = 0; i < data.length; ++i) {
	                row = [].concat(this.mTableData[i]);
	                for (var col in row) {
	                    row[col] = Util.formatCurrency(row[col]);
	                }
	                data[i] = data[i].concat(row);
	            }
            }

			var parent = $("#" + this.mTableId);
			parent.empty();
			parent.append("<table id='"+ name +"'></table>");
			
            $("#" + name).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(data),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
//                    cellsubmit: 'clientArray',
//                    cellEdit: true,
                    height: '100%',
                    width: 1000,
               
                    autoScroll: true
                }));

        }
    }
}