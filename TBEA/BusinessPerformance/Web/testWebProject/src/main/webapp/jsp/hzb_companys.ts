/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module hzb_companys {

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
        private updateTable(): void {
        	var name = this.mOpt.tableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
            var data = [];


 			var row = [];
            for (var i = 0; i < this.mData.length; ++i) {
//                if (this.mData[i] instanceof Array) {
                    row = [].concat(this.mData[i]);
//                    for (var col in row) {
//                        if (col != '2' && col != '4' && col != '6' && col != '8' && col != '10') {
//                            row[col] = Util.formatCurrency(row[col]);
//                        }
//                    }
                    data.push(row);
                  //  data[i] = data[i].concat(row);
//                }
            }

			var parent = $("#" + this.mOpt.tableId);
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
                    shrinkToFit: true,
                    rowNum: 100,
                    autoScroll: true
                }));

        }
    }
}