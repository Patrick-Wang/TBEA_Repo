/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module gcy_zbhz {

    class JQGridAssistantFactory {

      public static createTable(gridName: string, month: number): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("全年计划", "qnjh"),
                new JQTable.Node("月度", "yd")
                    .append(new JQTable.Node("当月计划", "y1"))
                    .append(new JQTable.Node("当月实际", "y2"))
                    .append(new JQTable.Node("计划完成率", "y3"))
                    .append(new JQTable.Node("去年同期", "y4"))
                    .append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度", "jd")
                    .append(new JQTable.Node("季度计划", "j1"))
                    .append(new JQTable.Node("季度累计", "j2"))
                    .append(new JQTable.Node("季度计划完成率", "j3"))
                    .append(new JQTable.Node("去年同期", "j4"))
                    .append(new JQTable.Node("同比增幅", "j5")),
                new JQTable.Node("年度", "nd")
                    .append(new JQTable.Node("年度累计", "n1"))
                    .append(new JQTable.Node("累计计划完成率", "n2"))
                    .append(new JQTable.Node("去年同期", "n3"))
                    .append(new JQTable.Node("同比增幅", "n4")),
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
        private mData: Array<string[]> = [];
        private mDataSet : Util.Ajax = new Util.Ajax("gcy_zbhz_update.do");
        private mTableId : string;
        public init(tableId: string, month: number, year: number): void {
            this.mYear = year;
            this.mMonth = month;
            this.mTableId = tableId;
            this.updateTable();
            this.updateUI();
        }
        
        public onYearSelected(year : number){
        	this.mYear = year;
        }
        
        public onMonthSelected(month : number){
        	this.mMonth = month;
        }
        
        public updateUI() {
            this.mDataSet.get({ month: this.mMonth, year: this.mYear })
                .then((jsonData: any) => {
                    this.mData = jsonData;
                    $('h1').text(this.mYear + "年" + this.mMonth + "月 各产业指标汇总");
                    document.title = this.mYear + "年" + this.mMonth + "月 各产业指标汇总";
                    this.updateTable();
                });
        }

        private updateTable(): void {
       	    var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mMonth);
            tableAssist.mergeRow(0);
           
            for (var i = 0; i < 5; ++i) {
                tableAssist.setRowBgColor(i * 8 + 5, 183, 222, 232);
                tableAssist.setRowBgColor(i * 8 + 7, 183, 222, 232);
            }


            var data = [
                ["报表利润", "输变电产业"],
                ["报表利润", "新能源产业"],
                ["报表利润", "能源产业"],
                ["报表利润", "进出口公司"],
                ["报表利润", "国际工程公司"],
                ["报表利润", "股份合计"],
                ["报表利润", "众和公司"],
                ["报表利润", "集团合计"],
                ["销售收入", "输变电产业"],
                ["销售收入", "新能源产业"],
                ["销售收入", "能源产业"],
                ["销售收入", "进出口公司"],
                ["销售收入", "国际工程公司"],
                ["销售收入", "股份合计"],
                ["销售收入", "众和公司"],
                ["销售收入", "集团合计"],
                ["现金流", "输变电产业"],
                ["现金流", "新能源产业"],
                ["现金流", "能源产业"],
                                ["现金流", "进出口公司"],
                ["现金流", "国际工程公司"],
                ["现金流", "股份合计"],
                ["现金流", "众和公司"],
                ["现金流", "集团合计"],
                ["应收账款", "输变电产业"],
                ["应收账款", "新能源产业"],
                ["应收账款", "能源产业"],
                                                ["应收账款", "进出口公司"],
                ["应收账款", "国际工程公司"],
                ["应收账款", "股份合计"],
                ["应收账款", "众和公司"],
                ["应收账款", "集团合计"],
                ["存 货", "输变电产业"],
                ["存 货", "新能源产业"],
                ["存 货", "能源产业"],
                                                                ["存 货", "进出口公司"],
                ["存 货", "国际工程公司"],
                ["存 货", "股份合计"],
                ["存 货", "众和公司"],
                ["存 货", "集团合计"]];

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
                        if (col != '3' && col != '5' && col != '7' && col != '9' && col != '11') {
                            row[col] = Util.formatCurrency(row[col]);
                        }
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
                    height: 600,
                    width: 1200,
                    shrinkToFit: true,
                    rowNum: 200,
                    autoScroll: true
                }));

        }
    }
}