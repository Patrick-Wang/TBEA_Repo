/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module hzb_zbhz {

    class JQGridAssistantFactory {

        public static createTable(gridName: string, month: number): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("序号", "xh", true, 60),
                new JQTable.Node("指标", "zb"),
                new JQTable.Node("当期", "dq")
                    .append(new JQTable.Node(month + "月计划", "yjh"))
                    .append(new JQTable.Node(month + "月完成", "ywc"))
                    .append(new JQTable.Node(month + "月完成率", "ywcl"))
                    .append(new JQTable.Node("季度累计", "jdlj"))
                    .append(new JQTable.Node("季度完成率", "jdwcl"))
                    .append(new JQTable.Node("年度累计", "ndlj"))
                    .append(new JQTable.Node("年度完成率", "ndwcl")),
                new JQTable.Node("去年", "qn")
                    .append(new JQTable.Node("去年同期", "qntq"))
                    .append(new JQTable.Node("同比增长", "tbzz"))
                    .append(new JQTable.Node("去年同期累计", "qntqlj"))
                    .append(new JQTable.Node("同比增长", "tbzz_1"))
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
        private mEchartIdPie: string;
        private mMonth: number;
        private mYear: number;
        private mData: Array<string[]>;
        public init(echartIdPie: string, tableId: string, month: number, year: number, data: Array<string[]>): void {
            this.mYear = year;
            this.mMonth = month;
            this.mEchartIdPie = echartIdPie;
            this.mData = data;
            this.updateTable(tableId);
        }

        private updateTable(name: string): void {
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mMonth);
            var data = [["1", "利润总额"],
						["2", "经营性净现金流"],
						["3", "应收账款"],
						["4", "其中：逾期款"],
						["5", "存货 "],
						["6", "其中：积压物资"],
						["7", "合同签约额"],
						["8", "资金回笼"],
						["9", "不含税产值"],
						["10", "产量：变压器"],
						["11", "产量：用铜量"],
						["12", "产量：用铝量"],
						["13", "人数"],
						["14", "人均利润"],
						["15", "人均收入"],
						["16", "三项费用"],
						["17", "三项费用率"]];


//            for (var i = 0; i < data.length; ++i) {
//                if (this.mData[i] instanceof Array) {
//                    data[i] = data[i].concat(this.mData[i]);
//                }
//            }

 			var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (this.mData[i] instanceof Array) {
                    row = [].concat(this.mData[i]);
                    for (var col in row) {
                        if (col != '2' && col != '4' && col != '6' && col != '8' && col != '10') {
                            row[col] = Util.formatCurrency(row[col]);
                        }
                    }
                    data[i] = data[i].concat(row);
                }
            }


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
                    width: 1200,
                    shrinkToFit: true,
                    rowNum: 100,
                    autoScroll: true
                }));

        }
    }
}