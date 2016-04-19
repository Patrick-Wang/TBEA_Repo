/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module yszkjgqkb {

    class JQGridAssistantFactory {
        public static createTable(gridName): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("客户所属行业", "khsshy", true, JQTable.TextAlign.Left),
                new JQTable.Node("客户所属行业", "khsshy_1", true, JQTable.TextAlign.Left),
                new JQTable.Node("应收账款情况", "zqkh")
                    .append(new JQTable.Node("金额", "je"))
                    .append(new JQTable.Node("占全部比例", "zqbbl")),
                new JQTable.Node("欠款构成", "qkgc")
                    .append(new JQTable.Node("应收未收(包括到期质保金）", "ysws")
                        .append(new JQTable.Node("逾期1个月以内", "yq1yn"))
                        .append(new JQTable.Node("逾期1-3月", "yq13y"))
                        .append(new JQTable.Node("逾期3-6月", "yq36y"))
                        .append(new JQTable.Node("逾期6-12月", "yq612y"))
                        .append(new JQTable.Node("逾期1年以上", "yqynys")))
                    .append(new JQTable.Node("未到期款", "wdqk"))
                    .append(new JQTable.Node("未到期质保金", "wdqzbj"))
                    .append(new JQTable.Node("应收账款合计", "yszkhj"))
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

        private mMonth: number;
        private mYear: number;
        private mEchartIdPie: string;
        private mEchartIdBar: string;
        private mEchartIdSquire: string;
        private mEchartIdLine: string;
        private mTableData: string;
        private mBarData: string;
        private mCurrentSelected: number = 0;
        private mSquireData: string;
        private mLineData: string;
        private mDataSet : Util.Ajax = new Util.Ajax("yszkjgqk_update.do");
        private mTableId : string;
        private mComp: Util.CompanyType = Util.CompanyType.SBGS;
        public init(echartIdPie: string, echartIdSquire: string, echartIdBar: string, echartIdLine: string, tableId: string, args: any[]): void {
            this.mMonth = args[0];
            this.mYear = args[1];
            this.mTableId = tableId;
            
            this.mEchartIdBar = echartIdBar;
            this.mEchartIdLine = echartIdLine;
            this.mEchartIdPie = echartIdPie;
            this.mEchartIdSquire = echartIdSquire;
            
            this.updateTable(tableId);
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
        
        public updateUI() {
            this.mDataSet.get({ month: this.mMonth, year: this.mYear, companyId: this.mComp})
                .then((data: any) => {

                    this.mTableData = data[0];
                    this.mBarData = data[1]
                    this.mLineData = data[2]
                    $('h1').text(this.mYear + "年" + this.mMonth + "月  应收账款结构情况表");
                    document.title = this.mYear + "年" + this.mMonth + "月  应收账款结构情况表";
                    this.updateTable(this.mTableId);
                    this.updatePieEchart(this.mEchartIdPie);
                    this.updateLineEchart(this.mEchartIdLine);
                    this.updateBarEchart(this.mEchartIdBar);
                    this.updateSquareEchart(this.mEchartIdSquire);

                });
        }


        public onSelected(i: number) {
            this.mCurrentSelected = i;
            this.updateLineEchart(this.mEchartIdLine);
            this.updateBarEchart(this.mEchartIdBar);
            this.updateSquareEchart(this.mEchartIdSquire);
        }
        private updateBarEchart(echart: string): void {
            var legend = [this.mYear + "应收未收", this.mYear + "未到期款", this.mYear + "未到期质保金"];
            var month: string[] = [];
            var data = [];
            for (var i = 1; i <= 12; ++i) {
                month.push(i + "月");
            }
            var row = [];
            for (var i = 3; i < 6; ++i) {
                row = [];
                data.push(row);
                for (var j = 0; j < this.mBarData[i + this.mCurrentSelected * 6].length; ++j){
                     row.push(parseInt(this.mBarData[i + this.mCurrentSelected * 6][j] + ""));
                }
               
            }

            var ser = [];
            var rgba = [
                [193, 35, 43, 1], [181, 195, 52, 1], [252, 206, 16, 1]];

            var temp;
            for (var j = 0; j < legend.length; ++j) {
              
                var k = j;
                if (j > 3) {
                    k = j - 1;
                }
                temp = {
                    name: legend[j],
                    type: 'bar',
                    smooth: true,

                    itemStyle: {
                        normal: {
                            color: "rgba(" + rgba[j][0] + "," + rgba[j][1] + "," + rgba[j][2] + "," + rgba[j][3] + ")",
                            label: {
                                show: true
                            }
                        }
                    },
                    data: data[k]
                }


                ser.push(temp);
            }

            var option = {

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
                xAxis: [{
                    type: 'category',
                    boundaryGap: true,
                    data: month
                }, {
                        type: 'category',
                        axisLine: { show: false },
                        axisTick: { show: false },
                        axisLabel: { show: false },
                        splitArea: { show: false },
                        splitLine: { show: false },
                        data: month
                    }],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: ser
            }
            try {
                echarts.init($('#' + echart)[0]).setOption(option);
            } catch (e) {
            }


        }

        private updateSquareEchart(echart: string): void {

            var month: string[] = [];
            var data = [];
            for (var i = 1; i <= 12; ++i) {
                month.push(i + "月");
            }

            for (var i = 0; i < 3; ++i) {
                data.push(this.mBarData[i + this.mCurrentSelected * 6 + 3]);
                for (var j = 0; j < data[i].length; ++j){
                    data[i][j] = parseFloat(data[i][j]).toFixed(2);   
                }
            }


            var legend = ["应收未收", "未到期款", "未到期质保金"];


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

            var month: string[] = [];
            var data = [];
            for (var i = 1; i <= 12; ++i) {
                month.push(i + "月");
            }

            for (var i = 0; i < 2; ++i) {
                data.push(this.mLineData[i + this.mCurrentSelected * 2]);
                for (var j = 0; j < data[i].length; ++j){
                    data[i][j] = parseFloat(data[i][j]).toFixed(2);   
                }
            }

            var legend = [this.mYear - 1 + "年", this.mYear + "年"];

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
            var legend = ["国网", "南网", "省、市电力公司", "五大发电", "其他电源", "出口合同", "其它"];
            var legendData = [];
            var dataOut = [];
            var total = 0;
            for (var i = 0; i < legend.length; ++i) {
                if (parseInt(this.mTableData[i][0]) > 0){
                    legendData.push(legend[i]);
                    dataOut.push({ name: legend[i], value: parseInt(this.mTableData[i][0]) });
                    if (i < 5) {
                        total += parseInt(this.mTableData[i][0]);
                    }
                }
            }

            var dataIn = [];
            if (total > 0){
                legendData.push("电力及配套");
                dataIn.push({ name: "电力及配套", value: total });
            }
            
            if (parseInt(this.mTableData[5][0]) > 0){
                dataIn.push( { name: "出口合同", value: parseInt(this.mTableData[5][0]) });
            }
            
            if (parseInt(this.mTableData[6][0]) > 0){
                dataIn.push( { name: "其它", value: parseInt(this.mTableData[6][0]) });
            }

            var option = {
                 tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    x: "left",
                    data: legendData,
                    orient: "vertical"
                },

                calculable: false,
                series: [
                    {
                        name: "行业占比",
                        type: 'pie',
                        radius: [100, 130],
                        data: dataOut
                    }, {
                        name: "行业占比",
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

        private updateTable(name: string): void {
       	 	var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            tableAssist.mergeColum(0, 5);
            tableAssist.mergeColum(0, 6);
            tableAssist.mergeColum(0, 7);
            var data = [
                ["电力及配套", "国网"],
                ["电力及配套", "南网"],
                ["电力及配套", "省、市电力公司"],
                ["电力及配套", "五大发电"],
                ["电力及配套", "其他电源"],
                ["出口", "合同"],
                ["其", "它"],
                ["合", "计"]];
            
            if (undefined != this.mTableData) {
                var row = [];
                for (var i = 0; i < data.length; ++i) {
                    row = [].concat(this.mTableData[i]);
                    for (var col in row) {
                        if (col != '1') {
                            row[col] = Util.formatCurrency(row[col]);
                        }
                    }
                    data[i] = data[i].concat(row);
                }
            }


			var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            
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
                    width: 1300,
                    shrinkToFit: true,
                    autoScroll: true,
                    //userData: userdata,
                    //footerrow: true,
                    //userDataOnFooter: true
                }));

        }
    }
}