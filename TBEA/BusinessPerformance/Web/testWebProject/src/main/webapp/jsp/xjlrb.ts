/// <reference path="jqgrid/jqassist.ts" />
declare var echarts;

module xjlrb {

    class JQGridAssistantFactory {

        public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位名称", "dwnc"),
                new JQTable.Node("本日流入", "brlr"),
                new JQTable.Node("本月累计流入", "byljlr"),
                new JQTable.Node("本年累计流入", "bnljlr"),
                new JQTable.Node("本日流出", "brlc"),
                new JQTable.Node("本月累计流出", "byljlc"),
                new JQTable.Node("本年累计流出", "bnljlc"),
                new JQTable.Node("本日净流量", "brjll"),
                new JQTable.Node("本月累计净流量", "byljjll"),
                new JQTable.Node("报表本月调整数", "bbbytzs"),
                new JQTable.Node("本年累计净流量", "bnljjll")
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
        private mEchartId: string;
        private mMonth: number;

        private mYear: number;
        private mData: Array<string[]>;
        public init(echartIdPie: string, tableId: string, month: number, year: number, data: Array<string[]>): void {
            this.mYear = year;
            this.mMonth = month;
            this.mEchartId = echartIdPie;
            this.mData = data;
            this.updateTable(tableId);
        }


        private updateTable(name: string): void {
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);

            var data = [["沈变公司"],
                ["衡变公司"],
                ["新变厂"],
                ["天变公司"],
                ["鲁缆公司"],
                ["新缆厂"],
                ["德缆公司"],
                ["输变电小计"],
                ["新能源公司"],
                ["新特能源公司"],
                ["新能源小计"],
                ["天池能源公司"],
                ["能动公司"],
                ["能源小计"],
                ["进出口"],
                ["国际工程公司"],
                ["工程小计"],
                ["机关本部"],
                ["采购中心"],
                ["资金中心"],
                ["公司机关小计"],
                ["香港公司"],
                ["股份公司合计"],
                ["众和公司"],
                ["合计"]];

            for (var i = 0; i < data.length; ++i) {
                if (data[i][0].lastIndexOf("计") >= 0) {
                    tableAssist.setRowBgColor(i, 183, 222, 232);
                }

                if (this.mData[i] instanceof Array) {
                    data[i] = data[i].concat(this.mData[i]);
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
                    shrinkToFit: false,
                    rowNum: 100,
                    autoScroll: true
                }));

        }
    }
}