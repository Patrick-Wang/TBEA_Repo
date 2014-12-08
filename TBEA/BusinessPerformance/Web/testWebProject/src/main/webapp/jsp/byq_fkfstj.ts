/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;
module byq_fkfstj {

    class JQGridAssistantFactory {

        private static createSubNode(parent: JQTable.Node): JQTable.Node{
            return parent
                .append(new JQTable.Node("笔数", "bs"))
                .append(new JQTable.Node("金额", "je"));
        }

        public static createFdwTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("非电网合同订单总量", "fdwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无预付款合同", "wyfkht")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("预付款<10%合同", "yfkxy10")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("预付款10%-30%之间的合同", "yfk1030zj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("货物交付后付款比例小于80%的合同", "hwjfhfkblxy80")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无兜底时间合同", "wddsj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保期大于>12个月合同", "zbqdy12")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("现款现货合同", "xkxh"))
            ], gridName);
        }
        
         public static createGwTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("国网合同订单总量", "gwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:4:2:1", "3421")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:4:2.5:0.5", "342505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0:9:0:1", "0901")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:4:4:1", "1414")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1：4:4.5:0.5", "144505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0：10：0:0", "01000")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("9.5:0.5", "9505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他", "qt"))
            ], gridName);
        }
        
        public static createNwTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("南网合同订单总量", "gwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:3:3:1", "3331")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:4:4:0.5:0.5", "1440505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:2:6.5:0.5", "126505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:4:4.5:0.5", "144505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他1", "qt1")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他2", "qt2"))
            ], gridName);
        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }

// private mfdwData : string[];
// private mgwData : string[];
// private mnwData : string[];
// private mfdwTableId : string;
// private mgwTableId : string;
// private mnwTableId : string;

        public init(echartIdFDW: string, 
        	echartIdGW: string, 
        	echartIdNW: string, 
	        fdwTableId: string, 
	        gwTableId: string, 
	        nwTableId: string, 
	        fdwData :  Array<string[]>,
			gwData : Array<string[]>,
			nwData :  Array<string[]>): void {
			
			var rowData = [
               	["沈变"],
				["衡变"],
				["新变"],
				["合计"]];
            this.updateTable(
            	fdwTableId, 
            	fdwTableId + "_jqgrid_1234",
            	JQGridAssistantFactory.createFdwTable(fdwTableId + "_jqgrid_1234"),
            	rowData, 
            	fdwData);
            	
            this.updateTable(
            	gwTableId, 
            	gwTableId + "_jqgrid_1234",
            	JQGridAssistantFactory.createGwTable(gwTableId + "_jqgrid_1234"),
            	rowData, 
            	gwData);
            	
            this.updateTable(
            	nwTableId, 
            	nwTableId + "_jqgrid_1234",
            	JQGridAssistantFactory.createNwTable(nwTableId + "_jqgrid_1234"),
            	rowData, 
            	nwData);
            
            this.updateEchart(echartIdFDW, "非电网合同订单总量",
            		[{value : 651654.32, name : '沈变'},
            		 {value : 514613.95, name : '衡变'},
            		 {value : 564895.41, name : '新变'}]);
            this.updateEchart(echartIdGW, "国网合同订单总量",
            		[{value : 466446.34, name : '沈变'},
            		 {value : 111984.61, name : '衡变'},
            		 {value : 487519.32, name : '新变'}]);
            this.updateEchart(echartIdNW, "南网合同订单总量",
            		[{value : 865146.13, name : '沈变'},
            		 {value : 955648.95, name : '衡变'},
            		 {value : 416516.54, name : '新变'}]);
        }

// private initEchart(echart): void {
// var ysyq_payment_Chart = echarts.init(echart)
// var ysyq_payment_Option = {
// animation: true,
// tooltip: {
// trigger: 'axis',
// /* formatter : "{b}<br/>{a} : {c} 万元<br/>{a1} : {c1} 万元", */
//
// axisPointer: { // 坐标轴指示器，坐标轴触发有效
// type: 'line' // 默认为直线，可选为：'line' | 'shadow'
// }
// },
// legend: {
// x: 'right',
// data: ["合同金额", "预期阶段", "中标阶段", "完工阶段"],
//
// },
// xAxis: [{
// type: 'category',
// data: ['沈变', '衡变', '新变', '天变']
// }],
// yAxis: [{
// type: 'value'
//
// }],
//
// calculable: true,
// series: [{
// name: '合同金额',
// type: 'bar',
//
// barCategoryGap: "50%",
// data: [63363.11, 55628.27, 58521.55, 69100.58]
// }, {
// name: '预期阶段',
// type: 'bar',
//
// stack: '阶段',
// data: [9098.58, 1240.13, 1140.61, 3154.82]
// }, {
// name: '中标阶段',
//
// type: 'bar',
// stack: '阶段',
// data: [3934.13, 3200.22, 1382.52, 3934.13]
// }, {
// name: '完工阶段',
// type: 'bar',
//
// stack: '阶段',
// data: [11980.74, 2240.18, 3487.11, 6980.74]
// }]
// };
// ysyq_payment_Chart.setOption(ysyq_payment_Option);
// }

			private updateEchart(chartId: string, tileTex: string, data: any[]): void{
	        	var chart = echarts.init($("#" + chartId)[0]);
	            var legend = ["沈变", "衡变", "新变"];

	            var option = {
	            	title : {
					    text: tileTex,
					    x: 'center'
					},
	                tooltip: {
	                    trigger: 'item'
	                },
	                legend: {
	                    x: "left",
	                    data: legend,
	                    orient: "vertical"
	                },
	                toolbox: {
	                    show: true,
	                },
	                calculable: false,
	                series: [
	                    {
	                        type: 'pie',
	                        radius: '50%',
	                        data: data
	                    }
	                ]
	            }

	            chart.setOption(option);
	        }
        private updateTable(      
		        parentName: string, 
		        childName : string, 
		        tableAssist : JQTable.JQGridAssistant, 
		        data : Array<string[]>, 
		        rawData : Array<string[]>): void {

  			var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (rawData[i] instanceof Array) {
                    row = [].concat(rawData[i]);
                    for (var col in row) {
                        if (col % 2 == 1){
                            row[col] = Util.formatCurrency(row[col]);
                        }
                    }
                    data[i] = data[i].concat(row);
                }
            }
            
            
			var parent = $("#" + parentName);
			parent.empty();
			parent.append("<table id='"+ childName +"'></table>");
			
            $("#" + childName).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(data),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    // autowidth : false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: 1250,
                    shrinkToFit: true,
                    autoScroll: true,
// userData: {
// 'title': "合计"
// },
// footerrow: true,
// userDataOnFooter: true
                }));

        }
    }
}