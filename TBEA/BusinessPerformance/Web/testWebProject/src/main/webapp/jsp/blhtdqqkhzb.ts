/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module blhtdqqkhzb {

    class JQGridAssistantFactory {

        private static createPreNode(month: number): JQTable.Node {
            var node: JQTable.Node;
            if (month >= 1) {
                node = new JQTable.Node(month + "月末保理余额", "ymblye" + month);
            }
            else {
                node = new JQTable.Node(12 + "月末保理余额", "ymblye" + 12);
            }
    		
    		return node.append(new JQTable.Node("非客户付息式保理余额", "fkhfxsblye"))
                .append(new JQTable.Node("客户付息式保理余额", "khfxsblye"))
    	}

        private static createNextNode(month: number, andlater: boolean = false): JQTable.Node {
            var node: JQTable.Node;
            var title: string = andlater ? "月及以后" : "月";
            if (month > 12) {
                node = new JQTable.Node((month - 12) + title, "y" + (month - 12));
            }
            else {
                node = new JQTable.Node(month + title, "y" + month);
            }
    		
    		return node.append(new JQTable.Node("到期保理金额", "dqblje"))
                .append(new JQTable.Node("到期保理中已回款金额", "dqblzyhkje"))
    	}

        public static createTable(gridName: string, month: number): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("保理到期月份", "bldqyf"),
                new JQTable.Node("保理到期月份", "bldqyf_1"),
                JQGridAssistantFactory.createPreNode(month - 1),
                JQGridAssistantFactory.createNextNode(month),
                JQGridAssistantFactory.createNextNode(month + 1),
                JQGridAssistantFactory.createNextNode(month + 2),
                JQGridAssistantFactory.createNextNode(month + 3),
                JQGridAssistantFactory.createNextNode(month + 4, true)
            ], gridName);
        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }

        private mMonth: number;
        private mYear: number;
        private mChartData: Array<string[]>;
        private mTableData: Array<string[]>;
        public init(echartId: string, tableId: string, args: any[]): void {
            this.mMonth = args[0];
            this.mYear = args[1];
            this.mChartData = args[2];
            this.mTableData = args[3];
            this.updateEchart(echartId);
            this.updateTable(tableId);
        }

        private fillData(month: string[]) {
 
            if (this.mChartData == undefined) {
                this.mChartData.push([]);
                this.mChartData.push([]);
                for (var i = 1; i <= this.mMonth; ++i) {
                    month.push(i + "月");
                    this.mChartData[0].push(Math.floor(Math.random() * (1000 + 1)) + "");
                    this.mChartData[1].push(Math.floor(Math.random() * (1000 + 1)) + "");
                }
            }
            else {
                for (var i = 1; i <= this.mMonth; ++i) {
                    month.push(i + "月");
                }
            }
        }

        private updateEchart(echart: string): void {
            var month: string[] = [];
           
            this.fillData(month);
            var data = this.mChartData;
            var option = {

                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: [this.mYear - 1 + "年", this.mYear + "年"]
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
                series: [
                    {
                        name: this.mYear - 1 + '年',
                        type: 'line',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[0]
                    },
                    {
                        name: this.mYear + '年',
                        type: 'line',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[1]
                    },
                ]
            }

            echarts.init($('#' + echart)[0]).setOption(option);

        }

        private updateTable(name: string): void {
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mMonth);
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            var data = [
                ["保理合同到期情况", "金额"],
                ["保理合同到期情况", "份数"]
            ];
//            for (var i = 0; i < data.length; ++i){
//                data[i] = data[i].concat(this.mTableData[i]);
//            }

            var row = [];
            for (var i = 0; i < data.length; ++i) {
                row = [].concat(this.mTableData[i]);
                for (var col in row){
                	row[col] = Util.formatCurrency(row[col]);
                }
                data[i] = data[i].concat(row);
            }

            $("#" + name).jqGrid(
                tableAssist.decorate({
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
//                    cellsubmit: 'clientArray',
//                    cellEdit: true,
                    height: '100%',
                    width: 1000,
                    shrinkToFit: false,
                    autoScroll: true,
                    data: tableAssist.getData(data),
                    datatype: "local"                 
                }));

        }
    }
}