/// <reference path="jqgrid/jqassist.ts" />
declare var echarts;

module gcy_zbhz {

    class JQGridAssistantFactory {

        public static createTable(gridName: string, month: number): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb"),
                new JQTable.Node("产业", "cy"),
                new JQTable.Node("全年", "qn"),
                new JQTable.Node("当期", "dq")
                    .append(new JQTable.Node(month + "月计划", "yjh"))
                    .append(new JQTable.Node(month + "月完成", "ywc"))
                    .append(new JQTable.Node(month + "月计划完成率", "yjhwcl", true, 180))
                    .append(new JQTable.Node("季度累计", "jdlj"))
                    .append(new JQTable.Node("季度完成率", "jdwcl"))
                    .append(new JQTable.Node(month + "年度累计", "ndlj"))
                    .append(new JQTable.Node(month + "累计完成率", "ljwcl")),
                new JQTable.Node("去年同期", "qntq_1")
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
        private mEchartIdSquire: string;
        private mEchartIdLine: string;
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
            tableAssist.mergeRow(0);
           
            for (var i = 0; i < 5; ++i) {
                tableAssist.setRowBgColor(i * 7 + 4, 183, 222, 232);
                tableAssist.setRowBgColor(i * 7 + 6, 183, 222, 232);
            }


            var data = [
                ["利润总额", "输变电产业"],
                ["利润总额", "新能源产业"],
                ["利润总额", "能源产业"],
                ["利润总额", "工程类"],
                ["利润总额", "股份合计"],
                ["利润总额", "众和公司"],
                ["利润总额", "集团合计"],
                ["销售收入", "输变电产业"],
                ["销售收入", "新能源产业"],
                ["销售收入", "能源产业"],
                ["销售收入", "工程类"],
                ["销售收入", "股份合计"],
                ["销售收入", "众和公司"],
                ["销售收入", "集团合计"],
                ["现金流", "输变电产业"],
                ["现金流", "新能源产业"],
                ["现金流", "能源产业"],
                ["现金流", "工程类"],
                ["现金流", "股份合计"],
                ["现金流", "众和公司"],
                ["现金流", "集团合计"],
                ["应收账款", "输变电产业"],
                ["应收账款", "新能源产业"],
                ["应收账款", "能源产业"],
                ["应收账款", "工程类"],
                ["应收账款", "股份合计"],
                ["应收账款", "众和公司"],
                ["应收账款", "集团合计"],
                ["存 货", "输变电产业"],
                ["存 货", "新能源产业"],
                ["存 货", "能源产业"],
                ["存 货", "工程类"],
                ["存 货", "股份合计"],
                ["存 货", "众和公司"],
                ["存 货", "集团合计"]];

            for (var i = 0; i < data.length; ++i) {
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
                    height: 600,
                    width: 1200,
                    shrinkToFit: true,
                    rowNum: 200,
                    autoScroll: true
                }));

        }
    }
}