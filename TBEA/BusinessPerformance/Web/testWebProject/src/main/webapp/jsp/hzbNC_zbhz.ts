/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
declare var echarts;

module hzbNC_zbhz{
    
    enum ZtId{
        zb, dysj, dyqntq, dytbzf, ndlj, ndqntq, ndtbzf   
    };
    
    class JQGridAssistantFactory {

         public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),  
                new JQTable.Node("当月实际", "dysj", true, JQTable.TextAlign.Left), 
                new JQTable.Node("去年同期", "qntq", true, JQTable.TextAlign.Left), 
                new JQTable.Node("同比增幅", "tbzf", true, JQTable.TextAlign.Left), 
                new JQTable.Node("年度累计", "ndlj", true, JQTable.TextAlign.Left), 
                new JQTable.Node("去年同期累计", "qnndlj", true, JQTable.TextAlign.Left), 
                new JQTable.Node("同比增幅", "ndtbzf", true, JQTable.TextAlign.Left)     
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

        private mData: Array<string[]> = [];
        private mDataSet : Util.Ajax = new Util.Ajax("AllCompanysNC_overview_update.do");
        private mTableId : string;
        private mType : number = 0;
        private mDs : Util.DateSelector;
        public init(tableId: string, dateId: string, month: number, year: number): void {
             this.mTableId = tableId;
             this.mDs = new Util.DateSelector(
                {year: year - 3, month : 1}, 
                {year: year, month: month},
                dateId);
            this.updateTable();
            //this.updateUI();

        }
                
        public updateUI() {
            var date : Util.Date = this.mDs.getDate();
            this.mDataSet.get({ month: date.month, year: date.year, type : this.mType })
                .then((dataArray: any) => {
                    this.mData = dataArray;
                    $('h1').text(date.year + "年" + date.month + "月公司整体财务指标完成情况(万元)");
                    document.title = date.year + "年" + date.month + "月公司整体财务指标完成情况(万元)";
                    this.updateTable();
                });
        }
       
        private initPercentList(): std.vector<number>
        {
            var precentList: std.vector<number> = new std.vector<number>();
            precentList.push(ZtId.dytbzf);
            precentList.push(ZtId.ndtbzf);
            return precentList;
        } 
        
        private updateTable(): void {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = null;
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='"+ name +"'></table>");
                        
            tableAssist = JQGridAssistantFactory.createTable(name);
            if (this.mData.length == 0){
                return;
            }
            var outputData: string[][] = [];
            Util.formatData(outputData, this.mData, this.initPercentList(), []);
            //data = this.formatZtData();

            
			
            $("#" + name).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(outputData),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
//                    cellsubmit: 'clientArray',
//                    cellEdit: true,
                    height: outputData.length > 23 ? 500 : '100%',
                    width: 1330,
                    shrinkToFit: true,
                    rowNum: 200,
                    autoScroll: true
                }));

        }
    }
}