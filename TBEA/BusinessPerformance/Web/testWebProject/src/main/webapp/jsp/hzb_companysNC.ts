/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module hzb_companysNC {

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

    
    
    interface IViewOption {
        tableId: string;
        dateId: string;
        date?: Util.Date;
        companyId: string;
        comps: Util.IDataNode[];
    }
    
    export class View {
        private static ins: View;

        public static newInstance(): View {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        }
        
        private mDateSelector: Util.DateSelector;
        private mCompanySelector: Util.CompanySelector;
        private mOpt: IViewOption;
        private mData: Array<string[]> = [];
        private mDataSet : Util.Ajax = new Util.Ajax("CompanysNC_update.do");
        public init(opt : IViewOption): void {
           this.mOpt = opt;
            
           if (opt.comps.length == 0){
               $('h1').text("没有任何可以查看的公司");
               $('input').css("display", "none");
           } else{
               var month = this.mOpt.date.month + (2 - (this.mOpt.date.month - 1) % 3);
               this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, { year: this.mOpt.date.year, month: month }, this.mOpt.dateId);
               this.mDateSelector.select(this.mOpt.date);
               this.mCompanySelector = new Util.CompanySelector(false, opt.companyId, opt.comps);
               //this.updateUI();
           } 
        }
               
        public updateUI() {
            var date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get({ year: date.year, month: date.month, companyId: compType})
                .then((dataArray: any) => {

                    this.mData = dataArray;
                    this.updateTextandTitle(date);
                    this.updateTable();

                });
        }
        
        private updateTextandTitle(date: Util.Date) {
            $('h1').text(date.year + "年" + date.month + "月经营单位财务指标完成情况");
            document.title = date.year + "年" + date.month + "月经营单位财务指标完成情况";
        }
        
        private initPercentList(): std.vector<number>
        {
            var precentList: std.vector<number> = new std.vector<number>();
            precentList.push(ZtId.dytbzf);
            precentList.push(ZtId.ndtbzf);
            return precentList;
        }
         
        private updateTable(): void {
        	var name = this.mOpt.tableId + "_jqgrid_1234";
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='"+ name +"'></table>");
            if (this.mData.length == 0){
                $("#tips").css("display", "");
                return;
            } 
            $("#tips").css("display", "none");

            var tableAssist: JQTable.JQGridAssistant = null;
            tableAssist = JQGridAssistantFactory.createTable(name)
            var outputData: string[][] = [];
            Util.formatData(outputData, this.mData, this.initPercentList(), []);
            
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
                    width: 1300,
                    shrinkToFit: true,
                    rowNum: 1000,
                    autoScroll: true
                }));

        }
        
    }
}