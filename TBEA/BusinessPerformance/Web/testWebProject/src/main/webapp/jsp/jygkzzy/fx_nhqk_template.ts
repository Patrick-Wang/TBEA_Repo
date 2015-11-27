/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="company_selector.ts" />
/// <reference path="bglx_selector.ts" />
declare var $;
module fx_nhqk_template {
    class JQGridAssistantFactory {
        public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                 new JQTable.Node("年度", "nd", true, JQTable.TextAlign.Left),
                    new JQTable.Node("产量", "cl", true),
                    new JQTable.Node("水（吨）", "s")
                    .append(new JQTable.Node("总消耗", "szhl"))
                    .append(new JQTable.Node("单位消耗", "sdwhl")),
                    new JQTable.Node("电（度）", "d")
                    .append(new JQTable.Node("总消耗", "dzhl"))
                    .append(new JQTable.Node("单位消耗", "ddwhl")),
                    new JQTable.Node("蒸汽（立方米）", "zq")
                    .append(new JQTable.Node("总消耗", "zqzhl"))
                    .append(new JQTable.Node("单位消耗", "zqdwhl"))
                ], gridName);
        }
        
        public static createTable1(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("年度", "nd", true, JQTable.TextAlign.Left),
                     new JQTable.Node("产值", "cl", true),
                     new JQTable.Node("水（水费）", "s")
                     .append(new JQTable.Node("总消耗（元）", "szhl"))
                     .append(new JQTable.Node("单位消耗", "sdwhl")),
                     new JQTable.Node("电（电费）", "d")
                     .append(new JQTable.Node("总消耗（元）", "dzhl"))
                     .append(new JQTable.Node("单位消耗", "ddwhl")),
                     new JQTable.Node("蒸汽（蒸汽费）", "zq")
                     .append(new JQTable.Node("总消耗（元）", "zqzhl"))
                     .append(new JQTable.Node("单位消耗", "zqdwhl"))
                ], gridName);
        }
    }

    
    
    interface IViewOption {
        tableId: string;
        tableId1: string;
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
        private mDataSet: Util.Ajax = new Util.Ajax("readview.do", false);       
        private mTableAssist: JQTable.JQGridAssistant;
         private mTableAssist1: JQTable.JQGridAssistant;
        private mTitleCompanyName : string;        
        public initInstance(opt : IViewOption): void {
           this.mOpt = opt;           
           if(opt.comps.length == 0){
               $('h1').text("没有任何可以查看的公司");
               $('input').css("display", "none");
           }else{
               this.mOpt = opt;            
               this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId);
               this.mCompanySelector = new Util.CompanySelectorZzy(opt.companyId, opt.comps,opt.isSbdcy);
               this.mBglxSelector=new Util.BglxViewSelector(opt.bglxId,opt.curbglx,opt.isByq,opt.isXl,opt.isSbdcy);               
               //this.updateTextandTitle(this.mDateSelector.getDate());
               this.updateUI();
           }
        }
        
        public exportExcel() {
            var date : Util.Date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            $("#export")[0].action = "export.do?" + Util.Ajax.toUrlParam({ month: date.month, year: date.year, companyId: compType});
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
            $('h1').text(date.year + "年能耗情况");
            document.title = date.year + "年能耗情况";
        }
        
        private updateTable(): void {
            if (this.mTableData.length == 0){
                $("#tips").css("display", "");
                return;
            }
            $("#tips").css("display", "none");
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
            var data = new Array();
            var data1 = new Array();
            data.push(this.mTableData[0]);
            data.push(this.mTableData[1]);
            data1.push(this.mTableData[2]);
            data1.push(this.mTableData[3]);            
            var name = this.mOpt.tableId + "_jqgrid_1234";
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='"+ name +"'></table>");
            this.mTableAssist = JQGridAssistantFactory.createTable(name)
            $("#" + name).jqGrid(
                this.mTableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: this.mTableAssist.getDataWithId(data),
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
            var name1 = this.mOpt.tableId1 + "_jqgrid_1234";
            var parent1 = $("#" + this.mOpt.tableId1);
            parent1.empty();
            parent1.append("<table id='"+ name1 +"'></table>");
            this.mTableAssist1 = JQGridAssistantFactory.createTable1(name1)
            $("#" + name1).jqGrid(
                this.mTableAssist1.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: this.mTableAssist.getDataWithId(data1),
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