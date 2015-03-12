/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module gdw_zbhz {

    enum DwZb{
        qnjh, dyjh, dysj, dyjhwcl, dyqntq, dytbzf, 
        jdjh, jdlj, jdjhwcl, jdqntq, jdtbzf,
        ndlj, ndljjhwcl, ndqntq, ndtbzf    
    }
    
    class JQGridAssistantFactory {

        public static createTable(gridName: string): JQTable.JQGridAssistant {
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
                    .append(new JQTable.Node("同比增幅", "n4"))
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

        private mZBName: string;
        private mZBId : number;
        private mData: Array<string[]> = [];
        private mDataSet : Util.Ajax = new Util.Ajax("gdw_zbhz_update.do");
        private mTableId : string;
        private mDs : Util.DateSelector;
        public init(tableId: string, dateId: string, month: number, year: number, zbId: number, zbName: string): void {
            this.mZBName = zbName;
            this.mZBId = zbId;
            this.mTableId = tableId;
            this.mDs = new Util.DateSelector(
                {year: year - 2, month : 1}, 
                {year: year, month: month},
                dateId);
            this.updateTable();
            this.updateUI();
        }
        
              
        public updateUI() {
            var date : Util.Date = this.mDs.getDate();
            this.mDataSet.get({ month: date.month, year: date.year, zbId: this.mZBId})
                .then((dataArray: any) => {
                    this.mData = dataArray;
                    $('h1').text(date.year + "年" + date.month + "月各单位" + this.mZBName + "完成情况");
                    document.title = date.year + "年" + date.month + "月各单位" + this.mZBName + "完成情况";
                    this.updateTable();
                });
        }

        private updateTable(): void {
       		var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
            tableAssist.mergeRow(0);
           
            var data = [
                ["沈变公司"],
                ["衡变公司"],
                ["新变厂"],
                ["鲁缆公司"],
                ["新缆厂"],
                ["德缆公司"],
                ["输变电小计"],
                ["新特能源公司"],
                ["新能源"],
                ["新能源小计"],
                ["天池能源"],
                ["能动公司"],
                ["能源小计"],
                ["进出口公司"],
                ["国际工程公司"],
                ["工程小计"],
                ["股份公司小计"],
                ["众和公司"],
                ["集团合计"]
             ];

            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (data[i][0].lastIndexOf("计") >= 0) {
                    tableAssist.setRowBgColor(i, 183, 222, 232);
                }
                if (this.mData[i] instanceof Array) {
                    row = [].concat(this.mData[i]);
                    for (var j = 0; j < row.length; ++j){
                        if (j == DwZb.dyjhwcl || j == DwZb.dytbzf || 
                        j == DwZb.jdjhwcl || j == DwZb.jdtbzf || 
                        j == DwZb.ndljjhwcl || j == DwZb.ndtbzf){
                            row[j] = Util.formatPercent(row[j]);
                        }else {
                            row[j] = Util.formatCurrency(row[j]);
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
                    data: tableAssist.getData(data),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: "100%",
                    width: 1200,
                    shrinkToFit: true,
                    rowNum: 200,
                    autoScroll: true
                }));

        }
    }
}