/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module gszt_srqy {

    class JQGridAssistantFactory {

        public static createTable(gridName: string, month: number): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),  
                new JQTable.Node("当期", "dq")
                    .append(new JQTable.Node("全年计划", "1"))
                    .append(new JQTable.Node("当月计划", "2"))
                    .append(new JQTable.Node("当月实际", "3"))
                    .append(new JQTable.Node("计划完成率", "4"))
                    .append(new JQTable.Node("累计完成", "5"))
                    .append(new JQTable.Node("累计完成率", "6")),
                new JQTable.Node("去年", "qn")
                    .append(new JQTable.Node("去年同期值", "7"))
                    .append(new JQTable.Node("同比增长率", "8"))
                    .append(new JQTable.Node("去年同期累计", "9"))
                    .append(new JQTable.Node("同比增长率", "10"))
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
        private mDataSet : Util.Ajax = new Util.Ajax("gszt_srqy_update.do");
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
                .then((dataArray: any) => {

                    this.mData = dataArray;
                    $('h1').text(this.mYear + "年" + this.mMonth + "月 指标汇总2");
                    document.title = this.mYear + "年" + this.mMonth + "月 指标汇总2";
                    this.updateTable();

                });
        }
        private updateTable(): void {
        	var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mMonth);
            var data = [["营业收入"],        
                        ["  制造业收入"],    
                        ["  工程项目收入"],   
                        ["  运营商收入"],    
                        ["  煤炭销售收入"],   
                        ["  物流贸易收入"],   
                        ["  服务类收入"],    
                        ["合同签约额"],      
                        ["  输变电、进出口国际签约"],  
                        ["    单机签约"],    
                        ["    成套签约"],   
                        ["  输变电国内签约"],  
                        ["    单机签约"],    
                        ["    成套签约"],    
                        ["  新能源产业签约"],  
                        ["  能源产业签约"],   
                        ["  众和公司签约"],   
                        ["产量"],
                        ["  变压器(万KVA)"],    
                        ["  线缆用铜量(吨)"],
                        ["  线缆用铝量(吨)"], 
                        ["  多晶硅(吨)"],   
                        ["  硅片(片)"],    
                        ["  发电量（万度）"],    
                        ["  逆变器（MW）"],  
                        ["  煤炭(万吨)"],
                        ["  电子铝箔（吨）"],
                        ["  电极箔化成量（平米）"],
                        ["  铝箔（吨）"],
                        ["  电极箔（平米）"]];


 			var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (this.mData[i] instanceof Array) {
                    row = [].concat(this.mData[i]);
//                    for (var col in row) {
//                        if (col != '2' && col != '4' && col != '6' && col != '8' && col != '10') {
//                            row[col] = Util.formatCurrency(row[col]);
//                        }
//                    }
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
                    data: tableAssist.getData(this.mData),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
//                    cellsubmit: 'clientArray',
//                    cellEdit: true,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    rowNum: 100,
                    autoScroll: true
                }));

        }
    }
}