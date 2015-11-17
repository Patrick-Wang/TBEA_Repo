/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="../companySelector.ts" />
/// <reference path="bglx_selector.ts" />
declare var echarts;
declare var $;
module fx_cpylsp_hqlyddzl_byq {
    class JQGridAssistantFactory {
        public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("产品类型", "cpdl"),
                new JQTable.Node("产值", "cz"),
                new JQTable.Node("产量", "cl"),
                new JQTable.Node("中标毛利率", "zbmll"),
                new JQTable.Node("预计优化后毛利率", "yjyhhmll"),
                new JQTable.Node("预计优化后毛利额", "yjyhhmle")
            ], gridName);
        }
    }

    
    
    interface IViewOption {
        tableId: string;
        dateId: string;
        date?: Util.Date;
        companyId: string;
        comps: Util.IDataNode[];
        bglxId:string;
        curbglx:string;
    }
    
    export class View {
        private static instance: View;
        public static getInstance(): View {
            if (View.instance == undefined){
                View.instance = new View();
            }
            return View.instance;
        }        
        private mTableData: Array<string[]>;
        private mDateSelector: Util.DateSelector;
        private mCompanySelector: Util.CompanySelector;
        private mBglxSelector:Util.BglxViewSelector;
        private mOpt: IViewOption;
        private mDataSet: Util.Ajax = new Util.Ajax("readviewbyq.do", false);       
        private mTableAssist: JQTable.JQGridAssistant;
        private mTitleCompanyName : string;        
        public initInstance(opt : IViewOption): void {
           this.mOpt = opt;           
           if(opt.comps.length == 0){
               $('h1').text("没有任何可以查看的公司");
               $('input').css("display", "none");
           }else{
               this.mOpt = opt;            
               this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId,true);
               this.mCompanySelector = new Util.CompanySelector(false, opt.companyId, opt.comps);
               this.mBglxSelector=new Util.BglxViewSelector(opt.bglxId,opt.curbglx);               
               //this.updateTextandTitle(this.mDateSelector.getDate());
               this.updateUI();
           }
        }
        
        public exportExcel(fName: string) {
            var date : Util.Date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            $("#export")[0].action = "fxcpylspdqddmlqk_exportbyq.do?" + Util.Ajax.toUrlParam({ month: date.month, year: date.year, companyId: compType});
            $("#export")[0].submit();
        }
        
        public updateUI() {
            var date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get({ year: date.year, month: date.month, companyId: compType})
                .then((dataArray: any) => {
                    this.mTableData = dataArray.values;
                    this.updateTextandTitle(date);
                    this.updateTable();

                });
        }
        
        private updateTextandTitle(date: Util.Date) {
            $('h1').text(date.year + "年" + date.month/3 + "季度后期履约订单质量");
            document.title = date.year + "年" + date.month/3 + "季度后期履约订单质量";
        }
        
        private updateTable(): void {
        	var name = this.mOpt.tableId + "_jqgrid_1234";
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='"+ name +"'></table>");
            if (this.mTableData.length == 0){
                $("#tips").css("display", "");
                return;
            } 
            $("#tips").css("display", "none");

            this.mTableAssist = JQGridAssistantFactory.createTable(name)            
            for (var i = 0; i < this.mTableData.length; ++i) {
                for (var j = 1; j < this.mTableData[i].length; ++j) {
                    if ("" != this.mTableData[i][j] && "--" != this.mTableData[i][j]) {
                        if(j == 3 || j == 4){
                            this.mTableData[i][j] = parseFloat(this.mTableData[i][j]) * 100 + "%";
                        } else {
                            this.mTableData[i][j] = parseFloat(this.mTableData[i][j]) + "";
                        }
                    }
                    else {
                        this.mTableData[i][j] = "--";
                    }
                }
            }
            $("#" + name).jqGrid(
                this.mTableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: this.mTableAssist.getData(this.mTableData),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
//                    cellsubmit: 'clientArray',
//                    cellEdit: true,
                    height: this.mTableData.length > 23 ? 500 : '100%',
                    width: this.mTableData[0].length*100,
                    shrinkToFit: true,
                    rowNum: 1000,
                    autoScroll: true
                }));
        }
    }
}