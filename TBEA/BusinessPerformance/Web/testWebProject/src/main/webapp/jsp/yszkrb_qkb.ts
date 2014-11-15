/// <reference path="jqgrid/jqassist.ts" />
declare var echarts;

module yszkrb_qkb {

    class JQGridAssistantFactory {

        public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dw"),
                new JQTable.Node("集团下达月度资金回笼指标", "jtxdydzjhlzb"),
                new JQTable.Node("各单位自行制定的回款计划", "gdwzxzddhkjh"),
                new JQTable.Node("今日回款", "jrhk"),
                new JQTable.Node("月累计（截止今日）", "ylejzjr"),
                new JQTable.Node("资金回笼指标完成", "zjhlzbwc"),
                new JQTable.Node("回款计划完成率", "hkjhwcl"),
                new JQTable.Node("已回款中可降应收的回款金额", "yhkzkjyshkje"),
                new JQTable.Node("目前-月底回款计划", "mqydhkjh")
                    .append(new JQTable.Node("确保办出", "qbbc"))
                    .append(new JQTable.Node("争取办出", "zqbc"))
                    .append(new JQTable.Node("两者合计", "lzhj")),
                new JQTable.Node("全月确保", "qyqb"),
                new JQTable.Node("预计全月计划完成率", "yjqyjhwcl"),
                new JQTable.Node("截止月底应收账款账面余额", "jzydyszkzmye")
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
        private mDataSet : Util.DateDataSet;
        private mTableId : string;
        public init(tableId: string, month: number, year: number): void {
            this.mYear = year;
            this.mMonth = month;
			this.mDataSet = new Util.DateDataSet("yszkrb_qkb_update.do");
            this.mTableId = tableId;
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
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);


            var data = [
                ["沈变公司"],
                ["衡变公司"],
                ["新变厂"],
                ["其中：天变公司"],
                ["鲁缆公司"],
                ["新缆厂"],
                ["德缆公司"],
                ["合计"]];

            for (var i = 0; i < data.length; ++i) {
                if (this.mData[i] instanceof Array) {
                    data[i] = data[i].concat(this.mData[i]);
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
                    height: '100%',
                    width: 1200,
                    shrinkToFit: false,
                    rowNum: 100,
                    autoScroll: true
                }));

        }
    }
}