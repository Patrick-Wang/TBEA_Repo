/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module ztyszkfx {

    class JQGridAssistantFactory {

        private static createCurrentYearNode(year: number) : JQTable.Node{
            return new JQTable.Node(year + "年", "n" + year)
                .append(new JQTable.Node("本月账面应收账款余额", "byzmyszkye"))
                .append(new JQTable.Node("本月保理控制余额", "byblkzye"))
                .append(new JQTable.Node("本月应收账款实际数", "byyszksjs"))
                .append(new JQTable.Node("本月收入", "bysr"))
                .append(new JQTable.Node("账面应收占收入比例", "zmyszsrbl"));
        }

        private static createPreYearNode(year: number): JQTable.Node {
            return new JQTable.Node(year + "年", "n" + year)
                .append(new JQTable.Node("去年同期账面应收账款余额", "qntqzmyszkye"))
                .append(new JQTable.Node("去年同期保理余额", "qntqblye"))
                .append(new JQTable.Node("去年同期应收账款实际数", "qntqyszksjs"))
                .append(new JQTable.Node("去年同期收入", "qntqsr"))
                .append(new JQTable.Node("账面应收占收入比", "zmyszsrb"));
        }

        public static createTable(gridName : string, year : number): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dw", true, JQTable.TextAlign.Left),
                JQGridAssistantFactory.createCurrentYearNode(year),
                JQGridAssistantFactory.createPreYearNode(year - 1),
                new JQTable.Node("同比增长", "tbzz")
                    .append(new JQTable.Node("账面余额较去年同期增长比", "zmyejqntqzzb"))
                    .append(new JQTable.Node("保理较去年同期增长比", "bljqntqzzb"))
                    .append(new JQTable.Node("实际应收较去年同期增长比", "sjysjqntqzzb"))
                    .append(new JQTable.Node("收入较去年同期增长比", "sujqntqzzb"))
            ], gridName);
        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }

      
        private mYear: number;
        private mData: Array<string[]>;
        private mDataSet: Util.DateDataSet;
        private mTableId: string;
        public init(tableId: string, year: number): void {
            this.mYear = year;
            this.mDataSet = new Util.DateDataSet("ztyszkfx_update.do");
            this.mTableId = tableId;
            this.updateTable();
            this.updateUI();

        }
        public onYearSelected(year: number) {
            this.mYear = year;
        }


        public updateUI() {
               this.mDataSet.getDataByYearOnly(this.mYear, (arrayData : Array<string[]>) => {
                if (null != arrayData) {
                    this.mData = arrayData;
                    $('h1').text(this.mYear + "年 整体应收账款分析表");
                    document.title = this.mYear + "年 整体应收账款分析表";
                    this.updateTable();
                }
            });
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

        private updateTable(): void {
          	var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mYear);

            var data = [["沈变"],
                ["衡变"],
                ["新变"],
                ["天变"],
                ["变压器合计"],
                ["鲁缆"],
                ["新缆"],
                ["德缆"],
                ["线缆合计"],
                ["产业集团合计"]
            ];


			if (undefined != this.mData){
			 	for (var i = 0; i < this.mData.length && i < data.length; ++i){
			 		var row = [];
			 		for (var j = 0; j < this.mData[i].length; ++j){
			 			row.push(Util.formatCurrency(this.mData[i][j]));
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
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: 1000,
                    shrinkToFit: false,
                    autoScroll: true,
                    //userData: {
                    //    'dw': "产业集团合计"
                    //},
                    //footerrow: true,
                    //userDataOnFooter: true
                }));

        }
    }
}