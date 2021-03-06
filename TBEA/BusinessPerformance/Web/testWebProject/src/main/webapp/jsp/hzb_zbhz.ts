/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
declare var echarts;

module hzb_zbhz {

    enum SrqyId{
        zb, qnjh, dyjh, dysj, jhwcl, ljwc, ljwcl, qntqz, tbzzl, qntqlj, ljtbzzl  
    };
    
    enum ZtId{
        zb, qnjh, dyjh, dysj, dyjhwcl, dyqntq, dytbzf, jdjh, jdlj, jdjhwcl, jdqntq, jdtbzf, ndlj, ndljjhwcl, ndqntq, ndtbzf   
    };
    
    class JQGridAssistantFactory {

         public static createSrqyTable(gridName: string): JQTable.JQGridAssistant {
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

        private mData: Array<string[]> = [];
        private mDataSet : Util.Ajax = new Util.Ajax("hzb_zbhz_update.do");
        private mXmgsDataSet : Util.Ajax = new Util.Ajax("hzb_zbhz_xmgs_compute.do", false);
        private mJydwDataSet: Util.Ajax = new Util.Ajax("hzb_zbhz_jydw_compute.do", false);
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
            this.updateUI();

        }
        
        public onTypeSelected(ty : number){
            this.mType = ty;
           
                
        }
        
        public updateUI() {
            var date : Util.Date = this.mDs.getDate();
            this.mDataSet.get({ month: date.month, year: date.year, type : this.mType })
                .then((dataArray: any) => {
                    this.mData = dataArray;
                    $('h1').text(date.year + "年" + date.month + "月公司整体指标完成情况");
                    document.title = date.year + "年" + date.month + "月公司整体指标完成情况";
                    this.updateTable();
                    this.updateButton();
                });
        }
        private updateButton() {
            if (this.mType != 0) {
                $("#exportxmgs").hide();
            }
            else {
                $("#exportxmgs").show();
            }
        }
        
        public exportExcelJydw(fName: string) {
            var date : Util.Date = this.mDs.getDate();
            $("#loadingText").text("经营单位整体指标完成情况导出中。。。");
            this.mJydwDataSet.get({ month: date.month, year: date.year, type : this.mType, fileName: fName}).then((tmStamp)=>{
                  $("#exportJydw")[0].action = "general_export.do?" + Util.Ajax.toUrlParam({ timeStamp: tmStamp.timeStamp });
                  $("#exportJydw")[0].submit();
                
            })
        }
        
        public exportExcelXmgs(fName: string) {
            var date : Util.Date = this.mDs.getDate();
            var fName = date.year + "年" + date.month + "月项目公司整体指标完成情况"; 
            $("#loadingText").text("项目公司整体指标完成情况导出中。。。");
            this.mXmgsDataSet.get({ month: date.month, year: date.year, type : this.mType, fileName: fName}).then((tmStamp)=>{
                  $("#exportxmgs")[0].action = "general_export.do?" + Util.Ajax.toUrlParam({ timeStamp: tmStamp.timeStamp });
                  $("#exportxmgs")[0].submit();
            })
        }
        
        //收入签约
//        private formatSrqyData() {
//            var data = [];
//            var row = [];
//            var isRs = false;
//            for (var j = 0; j < this.mData.length; ++j) {
//                row = [].concat(this.mData[j]);
//                isRs = row[SrqyId.zb] == '人数';
//                for (var i = 0; i < row.length; ++i) {
//                    if (i == SrqyId.jhwcl || i == SrqyId.ljwcl || i == SrqyId.tbzzl || i == SrqyId.ljtbzzl) {
//                        row[i] = Util.formatPercent(row[i]);
//                    } else if (i != SrqyId.zb) 
//                    { 
//                        if (isRs) {
//                            row[i] = Util.formatInt(row[i]);
//                        } else {
//                            row[i] = Util.formatCurrency(row[i]);
//                        }
//                    }
//                }
//                data.push(row);
//            }
//            return data;
//        }
//        //整体指标数据
//        private formatZtData() {
//            var data = [];
//            var row = [];
//            var isRs = false;
//            var isSxfyl = false;
//            var isRjlr = false;
//            var isRjsr = false;
//            for (var j = 0; j < this.mData.length; ++j) {
//                row = [].concat(this.mData[j]);
//                isRs = row[ZtId.zb] == '人数';
//                isSxfyl = row[ZtId.zb] == '三项费用率(%)';
//                isRjlr = row[ZtId.zb] == '人均利润';
//                isRjsr = row[ZtId.zb] == '人均收入';
//                for (var i = 0; i < row.length; ++i) {
//                    if (i == ZtId.dyjhwcl || i == ZtId.jdjhwcl || i == ZtId.dytbzf || i == ZtId.jdtbzf || i == ZtId.ndljjhwcl || i == ZtId.ndtbzf) {
//                        row[i] = Util.formatPercent(row[i]);
//                    } else if (i != ZtId.zb) {
//                        if (isRs) {
//                            row[i] = Util.formatInt(row[i]);
//                        } 
//                        else if (isRjlr)
//                        {
//                            row[i] = Util.formatFordot(row[i], 1);
//                        }
//                        else if (isRjsr)
//                        {
//                            row[i] = Util.formatFordot(row[i], 1);
//                        }  
//                        else if (isSxfyl){
//                             row[i] = Util.formatPercent(row[i]);
//                        }else {
//                            row[i] = Util.formatCurrency(row[i]);
//                        }
//                    }
//                }
//                data.push(row);
//            }
//            return data;
//        }
        
        private initZTPercentList(): std.vector<number>
        {
            var precentList: std.vector<number> = new std.vector<number>();
            precentList.push(ZtId.dyjhwcl);
            precentList.push(ZtId.dytbzf);
            precentList.push(ZtId.jdjhwcl);
            precentList.push(ZtId.jdtbzf);
            precentList.push(ZtId.ndljjhwcl);
            precentList.push(ZtId.ndtbzf);
            return precentList;
        }
        
         private initSrqyPercentList(): std.vector<number>
        {
            var precentList: std.vector<number> = new std.vector<number>();
            precentList.push(SrqyId.jhwcl);
            precentList.push(SrqyId.ljwcl);
            precentList.push(SrqyId.tbzzl);
            precentList.push(SrqyId.ljtbzzl);
            return precentList;
        }
        
        private updateTable(): void {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = null;
            var data = [];
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='"+ name +"'></table>");
            if (this.mData.length == 0){
                return;
            }
            
            var outputData: string[][] = [];
            if (0 == this.mType) {
                tableAssist = JQGridAssistantFactory.createTable(name);
                Util.formatData(outputData, this.mData, this.initZTPercentList(), []);
            } else {
                tableAssist = JQGridAssistantFactory.createSrqyTable(name);
                Util.formatData(outputData, this.mData, this.initSrqyPercentList(), []);
            }
            
			
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
                    height: data.length > 23 ? 500 : '100%',
                    width: 1330,
                    shrinkToFit: true,
                    rowNum: 200,
                    autoScroll: true
                }));

        }
    }
}