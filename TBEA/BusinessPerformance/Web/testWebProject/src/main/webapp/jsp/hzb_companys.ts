/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module hzb_companys {

    enum AllZb{
        zb, qnjh, dyjh, dysj, dyjhwcl, dyqntq, dytbzf, jdjh, jdlj, jdjhwcl, jdqntq, jdtbzf, ndlj, ndljjhwcl, ndqntq, ndtbzf
    };
    
    enum HbZb{
         zb, qnjh, dyjh, dysj, dyjhwcl, dysytq, dyhbzf, dytbzf, jdjh, jdlj, jdjhwcl, jdqntq, jdtbzf, ndlj, ndljjhwcl, ndqntq, ndtbzf       
    }
    
    
    
    class JQGridAssistantFactory {

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
        
        public static createHbTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("全年计划", "qnjh"),
                new JQTable.Node("月度", "yd")
                    .append(new JQTable.Node("当月计划", "y1"))
                    .append(new JQTable.Node("当月实际", "y2"))
                    .append(new JQTable.Node("计划完成率", "y3"))
                    .append(new JQTable.Node("上月同期", "y4"))
                    .append(new JQTable.Node("环比增幅", "y4"))
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
        private mDataSet : Util.Ajax = new Util.Ajax("hzb_companys_update.do");
        public init(opt : IViewOption): void {
           this.mOpt = opt;
           this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 1 }, this.mOpt.date, this.mOpt.dateId);
           this.mCompanySelector = new Util.CompanySelector(false, opt.companyId, opt.comps);
            
           this.updateTable();
           this.updateUI();
        }
        

        
        public updateUI() {
            var date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get({ year: date.year, month: date.month, companyId: compType})
                .then((dataArray: any) => {

                    this.mData = dataArray;
                    $('h1').text(date.year + "年" + date.month + "月 指标汇总");
                    document.title = date.year + "年" + date.month + "月 指标汇总";
                    this.updateTable();

                });
        }
        
        private formatAllData() {
            var data = [];
            var row = [];
            var isRs = false;
            for (var j = 0; j < this.mData.length; ++j) {
                row = [].concat(this.mData[j]);
                isRs = row[AllZb.zb] == '人数';
                for (var i = 0; i < row.length; ++i) {
                    if (i == AllZb.dyjhwcl || i == AllZb.dytbzf || i == AllZb.jdjhwcl || i == AllZb.jdtbzf ||
                        i == AllZb.ndljjhwcl ||　i == AllZb.ndtbzf) {
                        row[i] = Util.formatPercent(row[i]);
                    } else if (i != AllZb.zb) {
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
        
        private formatHbData() {
            var data = [];
            var row = [];
            var isRs = false;
            for (var j = 0; j < this.mData.length; ++j) {
                row = [].concat(this.mData[j]);
                isRs = row[HbZb.zb] == '人数';
                for (var i = 0; i < row.length; ++i) {
                   if (i == HbZb.dyjhwcl || i == HbZb.dyhbzf ||i == HbZb.dytbzf || i == HbZb.jdjhwcl || i == HbZb.jdtbzf ||
                        i == HbZb.ndljjhwcl ||　i == HbZb.ndtbzf) {
                        row[i] = Util.formatPercent(row[i]);
                    } else if (i != HbZb.zb) {
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
        	var name = this.mOpt.tableId + "_jqgrid_1234";
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='"+ name +"'></table>");
            if (this.mData.length == 0){
                return;
            } 
            
            var data = [];
            var tableAssist: JQTable.JQGridAssistant = null;
            if (this.mData[0].length > 16){
                tableAssist = JQGridAssistantFactory.createHbTable(name)
                data = this.formatHbData();
            } else {
                tableAssist = JQGridAssistantFactory.createTable(name)
                data = this.formatAllData();
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
                    width: 1200,
                    shrinkToFit: true,
                    rowNum: 1000,
                    autoScroll: true
                }));

        }
    }
}