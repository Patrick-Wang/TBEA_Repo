/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="company_selector.ts" />
/// <reference path="bglx_selector.ts" />
declare var $;
module fx_jkcb_ztnhqk_xl_template {

    class JQGridAssistantFactory {

        public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                    new JQTable.Node("", "empty", true, JQTable.TextAlign.Left),
                    new JQTable.Node("水（吨）", "sd")
                        .append(new JQTable.Node("用量", "syl"))
                        .append(new JQTable.Node("金额（元）", "sje")),
                    new JQTable.Node("电（度）", "dd")
                        .append(new JQTable.Node("用量", "dyl"))
                        .append(new JQTable.Node("金额（元）", "dje")),
                    new JQTable.Node("蒸汽（立方米）", "zqlfm")
                        .append(new JQTable.Node("用量", "zqyl"))
                        .append(new JQTable.Node("金额（元）", "zqje")),
                    new JQTable.Node("燃气（线缆）（立方米）", "rqxllfm")
                        .append(new JQTable.Node("用量", "rqxlyl"))
                        .append(new JQTable.Node("金额（元）", "rqxlje")),
                    new JQTable.Node("产值", "cz"),
                    new JQTable.Node("产量", "cl")
                ], gridName);
        }
    }

     interface IViewOption {
        tableId: string;
        dateId: string;
        date?: Util.Date;
        companyId: string;
        comps: Util.Dwxx[];
        bglxId:string;
        curbglx:string;
        isByq:boolean, 
        isXl:boolean, 
        isSbdcy:boolean
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
        private mCompanySelector: Util.CompanySelectorZzy;
        private mBglxSelector:Util.BglxViewSelector;
        private mOpt: IViewOption;
        private mDataSet: Util.Ajax = new Util.Ajax("readviewxl.do", false);       
        private mTableAssist: JQTable.JQGridAssistant;
        private mTitleCompanyName : string;        
        public initInstance(opt : IViewOption): void {
           this.mOpt = opt;           
           if(opt.comps.length == 0){
               $('h1').text("没有任何可以查看的公司");
               $('input').css("display", "none");
           }else{
               this.mOpt = opt;            
               this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId);
               this.mCompanySelector = new Util.CompanySelectorZzy(opt.companyId, opt.comps,opt.isSbdcy,'03');
               this.mBglxSelector=new Util.BglxViewSelector(opt.bglxId,opt.curbglx,opt.isByq,opt.isXl,opt.isSbdcy);               
               //this.updateTextandTitle(this.mDateSelector.getDate());
               if (opt.comps.length == 1){
                    this.mCompanySelector.hide();
               }
               this.updateUI();
           }
        }
        
        public exportExcel() {
            var date : Util.Date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            $("#export")[0].action = "exportxl.do?" + Util.Ajax.toUrlParam({ year: date.year, month: date.month, companyId: compType});
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
            var compName = this.mCompanySelector.getCompanyName()
            $('h1').text(date.year + "年" + date.month + "月"+compName+"整体能耗情况");
            document.title = date.year + "年" + date.month + "月"+compName+"整体能耗情况";
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
                for (var j = 2; j < this.mTableData[i].length; ++j) {
                    if ("" != this.mTableData[i][j] && "--" != this.mTableData[i][j]) {
                        this.mTableData[i][j] = (parseFloat(this.mTableData[i][j])).toFixed(2) + "";
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
                    data: this.mTableAssist.getDataWithId(this.mTableData),
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