/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
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
        private mTableId : string;
        private mType : number = 0;
        private mDs : Util.DateSelector;
        public init(tableId: string, dateId: string, month: number, year: number): void {
              this.mTableId = tableId;
             this.mDs = new Util.DateSelector(
                {year: year - 2, month : 1}, 
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
                    $('h1').text(date.year + "年" + date.month + "月公司整体指标汇总");
                    document.title = date.year + "年" + date.month + "月公司整体指标汇总";
                    this.updateTable();

                });
        }
        
        private formatSrqyData() {
            var data = [];
            var row = [];
            var isRs = false;
            for (var j = 0; j < this.mData.length; ++j) {
                row = [].concat(this.mData[j]);
                isRs = row[SrqyId.zb] == '人数';
                for (var i = 0; i < row.length; ++i) {
                    if (i == SrqyId.jhwcl || i == SrqyId.ljwcl || i == SrqyId.tbzzl || i == SrqyId.ljtbzzl) {
                        row[i] = Util.formatPercent(row[i]);
                    } else if (i != SrqyId.zb) {
                        if (isRs) {
                            row[i] = Util.formatInt(row[i]);
                        } else {
                            row[i] = Util.formatCurrency(row[i]);
                        }
                    }
                }
                data.push(row);
            }
            return data;
        }
        
        private formatZtData() {
            var data = [];
            var row = [];
            var isRs = false;
            for (var j = 0; j < this.mData.length; ++j) {
                row = [].concat(this.mData[j]);
                isRs = row[ZtId.zb] == '人数';
                for (var i = 0; i < row.length; ++i) {
                    if (i == ZtId.dyjhwcl || i == ZtId.jdjhwcl || i == ZtId.dytbzf || i == ZtId.jdtbzf || i == ZtId.ndljjhwcl || i == ZtId.ndtbzf) {
                        row[i] = Util.formatPercent(row[i]);
                    } else if (i != ZtId.zb) {
                        if (isRs) {
                            row[i] = Util.formatInt(row[i]);
                        } else {
                            row[i] = Util.formatCurrency(row[i]);
                        }
                    }
                }
                data.push(row);
            }
            return data;
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
            
            if (0 == this.mType) {
                tableAssist = JQGridAssistantFactory.createTable(name);
                data = this.formatZtData();
            } else {
                tableAssist = JQGridAssistantFactory.createSrqyTable(name);
                data = this.formatSrqyData();
            }
            
			
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
                    height: data.length > 23 ? 500 : '100%',
                    width: 1330,
                    shrinkToFit: true,
                    rowNum: 200,
                    autoScroll: true
                }));

        }
    }
}