/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module gdw_zbhz {

    class JQGridAssistantFactory {

        public static createTable(gridName: string, month: number): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("企业名称", "qymc", true, JQTable.TextAlign.Left),
                new JQTable.Node("年度计划", "ndjh"),
                new JQTable.Node("当期", "dq")
                    .append(new JQTable.Node("月度计划", "yjh"))
                    .append(new JQTable.Node("月度完成", "ywc"))
                    .append(new JQTable.Node("月计划完成率", "yjhwcl", true))
                    .append(new JQTable.Node("季度计划", "jdjh"))
                    .append(new JQTable.Node("季度累计", "jdlj"))
                    .append(new JQTable.Node("季度完成率", "jdwcl"))
                    .append(new JQTable.Node("年度累计", "ndlj"))
                    .append(new JQTable.Node("累计完成率", "ljwcl")),
                new JQTable.Node("去年同期", "qntq_1")
                    .append(new JQTable.Node("去年同期", "qntq"))
                    .append(new JQTable.Node("同比增长", "tbzz"))
                    .append(new JQTable.Node("去年同期累计", "qntqlj", true))
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

        private mMonth: number;
        private mYear: number;
        private mData: Array<string[]> = [];
        private mDataSet : Util.DateDataSet;
        private mTableId : string;
        public init(tableId: string, month: number, year: number): void {
            this.mYear = year;
            this.mMonth = month;
            this.mDataSet = new Util.DateDataSet("gdw_zbhz_update.do");
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
        
		public updateUI(){
			this.mDataSet.getData(this.mMonth, this.mYear, (dataArray : Array<string[]>) =>{
				if (null != dataArray){
					this.mData = dataArray;
					$('h1').text(this.mYear + "年" + this.mMonth + "月 各单位指标汇总");
					$('title').text(this.mYear + "年" + this.mMonth + "月 各单位指标汇总");
					this.updateTable();
				}
			});
		}

        private updateTable(): void {
       		var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mMonth);
            tableAssist.mergeRow(0);
           
            var data = [
                ["报表利润", "沈变公司"],
                ["报表利润", "衡变公司"],
                ["报表利润", "新变厂"],
                ["报表利润", "天变公司"],
                ["报表利润", "鲁缆公司"],
                ["报表利润", "新缆厂"],
                ["报表利润", "德缆公司"],
                ["报表利润", "输变电小计"],
                ["报表利润", "新特能源公司"],
                ["报表利润", "新能源"],
                ["报表利润", "新能源小计"],
                ["报表利润", "天池能源"],
                ["报表利润", "能动公司"],
                ["报表利润", "中疆物流"],
                ["报表利润", "能源小计"],
                ["报表利润", "进出口公司"],
                ["报表利润", "国际工程公司"],
                ["报表利润", "工程小计"],
                ["报表利润", "股份公司小计"],
                ["报表利润", "众和公司"],
                ["报表利润", "集团合计"],
                ["销售收入", "沈变公司"],
                ["销售收入", "衡变公司"],
                ["销售收入", "新变厂"],
                ["销售收入", "天变公司"],
                ["销售收入", "鲁缆公司"],
                ["销售收入", "新缆厂"],
                ["销售收入", "德缆公司"],
                ["销售收入", "输变电小计"],
                ["销售收入", "新特能源公司"],
                ["销售收入", "新能源"],
                ["销售收入", "新能源小计"],
                ["销售收入", "天池能源"],
                ["销售收入", "能动公司"],
                ["销售收入", "中疆物流"],
                ["销售收入", "能源小计"],
                ["销售收入", "进出口公司"],
                ["销售收入", "国际工程公司"],
                ["销售收入", "工程小计"],
                ["销售收入", "股份公司小计"],
                ["销售收入", "众和公司"],
                ["销售收入", "集团合计"],
                ["现金流", "沈变公司"],
                ["现金流", "衡变公司"],
                ["现金流", "新变厂"],
                ["现金流", "天变公司"],
                ["现金流", "鲁缆公司"],
                ["现金流", "新缆厂"],
                ["现金流", "德缆公司"],
                ["现金流", "输变电小计"],
                ["现金流", "新特能源公司"],
                ["现金流", "新能源"],
                ["现金流", "新能源小计"],
                ["现金流", "天池能源"],
                ["现金流", "能动公司"],
                ["现金流", "中疆物流"],
                ["现金流", "能源小计"],
                ["现金流", "进出口公司"],
                ["现金流", "国际工程公司"],
                ["现金流", "工程小计"],
                ["现金流", "股份公司小计"],
                ["现金流", "众和公司"],
                ["现金流", "集团合计"],
                ["应收账款", "沈变公司"],
                ["应收账款", "衡变公司"],
                ["应收账款", "新变厂"],
                ["应收账款", "天变公司"],
                ["应收账款", "鲁缆公司"],
                ["应收账款", "新缆厂"],
                ["应收账款", "德缆公司"],
                ["应收账款", "输变电小计"],
                ["应收账款", "新特能源公司"],
                ["应收账款", "新能源"],
                ["应收账款", "新能源小计"],
                ["应收账款", "天池能源"],
                ["应收账款", "能动公司"],
                ["应收账款", "中疆物流"],
                ["应收账款", "能源小计"],
                ["应收账款", "进出口公司"],
                ["应收账款", "国际工程公司"],
                ["应收账款", "工程小计"],
                ["应收账款", "股份公司小计"],
                ["应收账款", "众和公司"],
                ["应收账款", "集团合计"],
                ["存货", "沈变公司"],
                ["存货", "衡变公司"],
                ["存货", "新变厂"],
                ["存货", "天变公司"],
                ["存货", "鲁缆公司"],
                ["存货", "新缆厂"],
                ["存货", "德缆公司"],
                ["存货", "输变电小计"],
                ["存货", "新特能源公司"],
                ["存货", "新能源"],
                ["存货", "新能源小计"],
                ["存货", "天池能源"],
                ["存货", "能动公司"],
                ["存货", "中疆物流"],
                ["存货", "能源小计"],
                ["存货", "进出口公司"],
                ["存货", "国际工程公司"],
                ["存货", "工程小计"],
                ["存货", "股份公司小计"],
                ["存货", "众和公司"],
                ["存货", "集团合计"]];

            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (data[i][1].lastIndexOf("计") >= 0) {
                    tableAssist.setRowBgColor(i, 183, 222, 232);
                }
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