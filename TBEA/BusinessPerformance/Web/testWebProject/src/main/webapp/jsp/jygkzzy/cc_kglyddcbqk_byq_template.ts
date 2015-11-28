/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="company_selector.ts" />
/// <reference path="bglx_selector.ts" />
declare var $;
module jygk_zzy_cc_kglyddcbqk {

    class JQGridAssistantFactory {

        public static createTable(gridName: string, date: Util.Date): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dwid", true, JQTable.TextAlign.Left),
                new JQTable.Node("月产出能力", "yscnl")
                //  .append(new JQTable.Node("", "yscnl1")
                    .append(new JQTable.Node("产值", "yccnlcz"))
                    .append(new JQTable.Node("产量", "yccnlcl")),
                new JQTable.Node("所有可供履约订单总量", "yy")
                //                    .append(new JQTable.Node("", "yy1")
                    .append(new JQTable.Node("产值", "kglyddzcz"))
                    .append(new JQTable.Node("产量", "kglyddzcl")),
                //                new JQTable.Node("可供履约订单（不含税）", "yd")
                new JQTable.Node("可供履约订单（不含税）" + date.year + "年可供履约订单总量", "yd0")
                    .append(new JQTable.Node("产值", "ndkglyddzcz"))
                    .append(new JQTable.Node("产量", "ndkglyddzcl")),
                new JQTable.Node("可供履约订单（不含税）n+1月", "yd1")
                    .append(new JQTable.Node("产值", "n1cz"))
                    .append(new JQTable.Node("产量", "n1cl"))
                    .append(new JQTable.Node("产能发挥率", "n1fhl")),
                new JQTable.Node("可供履约订单（不含税）n+2月", "yd2")
                    .append(new JQTable.Node("产值", "n2cz"))
                    .append(new JQTable.Node("产量", "n2cl"))
                    .append(new JQTable.Node("产能发挥率", "n2fhl")),
                new JQTable.Node("可供履约订单（不含税）n+3月", "yd3")
                    .append(new JQTable.Node("产值", "n3cz"))
                    .append(new JQTable.Node("产量", "n3cl"))
                    .append(new JQTable.Node("产能发挥率", "n3fhl")),
                new JQTable.Node("可供履约订单（不含税）n+4月", "yd4")
                    .append(new JQTable.Node("产值", "n4cz"))
                    .append(new JQTable.Node("产量", "n4cl"))
                    .append(new JQTable.Node("产能发挥率", "n4fhl")),
                new JQTable.Node("可供履约订单（不含税）n+5月", "yd5")
                    .append(new JQTable.Node("产值", "n5cz"))
                    .append(new JQTable.Node("产量", "n5cl"))
                    .append(new JQTable.Node("产能发挥率", "n5fhl")),
                new JQTable.Node("可供履约订单（不含税）n+6月", "yd6")
                    .append(new JQTable.Node("产值", "n6cz"))
                    .append(new JQTable.Node("产量", "n6cl"))
                    .append(new JQTable.Node("产能发挥率", "n6fhl")),
                new JQTable.Node("n+6月以后可供履约订单", "hyd")
                //                    .append(new JQTable.Node("", "hyd1")
                    .append(new JQTable.Node("产值", "n6hcz"))
                    .append(new JQTable.Node("产量", "n6hcl")),
                new JQTable.Node("交货期待定", "dd")
                //                    .append(new JQTable.Node("", "dd1")
                    .append(new JQTable.Node("产值", "ddcl"))
                    .append(new JQTable.Node("产量", "ddcz"))
            ], gridName);
        }
    }

    interface IViewOption {
        tableId: string;
        dateId: string;
        date?: Util.Date;
        companyId: string;
        comps: Util.Dwxx[];
        bglxId: string;
        curbglx: string;
        isByq: boolean,
        isXl: boolean,
        isSbdcy: boolean
    }


    export class View {
        private static instance: View;
        public static getInstance(): View {
            if (View.instance == undefined) {
                View.instance = new View();
            }
            return View.instance;
        }
        private mTableData: Array<string[]>;
        private mDateSelector: Util.DateSelector;
        private mCompanySelector: Util.CompanySelectorZzy;
        private mBglxSelector: Util.BglxViewSelector;
        private mOpt: IViewOption;
        private mDataSet: Util.Ajax = new Util.Ajax("readviewbyq.do", false);
        private mTableAssist: JQTable.JQGridAssistant;
        private mTitleCompanyName: string;
        public initInstance(opt: IViewOption): void {
            this.mOpt = opt;
            if (opt.comps.length == 0) {
                $('h1').text("没有任何可以查看的公司");
                $('input').css("display", "none");
            } else {
                this.mOpt = opt;
                this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId);
                this.mCompanySelector = new Util.CompanySelectorZzy(opt.companyId, opt.comps, opt.isSbdcy);
                this.mBglxSelector = new Util.BglxViewSelector(opt.bglxId, opt.curbglx, opt.isByq, opt.isXl, opt.isSbdcy);               
                //this.updateTextandTitle(this.mDateSelector.getDate());
                if (opt.comps.length == 1){
                    this.mCompanySelector.hide();
                }
                this.updateUI();
            }
        }

        public exportExcel() {
            var date: Util.Date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            $("#export")[0].action = "exportbyq.do?" + Util.Ajax.toUrlParam({ year: date.year, month: date.month, companyId: compType });
            $("#export")[0].submit();
        }

        public updateUI() {
            var date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get({ year: date.year, month: date.month, companyId: compType })
                .then((dataArray: any) => {
                    this.mTableData = dataArray.values;
                    this.updateTextandTitle(date);
                    this.updateTable();

                });
        }

        private updateTextandTitle(date: Util.Date) {
            var compName = this.mCompanySelector.getCompanyName()
            $('h1').text(date.year + "年" + date.month + "月"+compName+"可供履约订单储备情况");
            document.title = date.year + "年" + date.month + "月"+compName+"可供履约订单储备情况";
        }

        private updateTable(): void {
            var name = this.mOpt.tableId + "_jqgrid_1234";
            var parent = $("#" + this.mOpt.tableId);
            var date = this.mDateSelector.getDate();
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            if (this.mTableData.length == 0) {
                $("#tips").css("display", "");
                return;
            }
            $("#tips").css("display", "none");

            this.mTableAssist = JQGridAssistantFactory.createTable(name, date)
            for (var i = 0; i < this.mTableData.length; ++i) {
                for (var j = 1; j < this.mTableData[i].length; ++j) {
                    if ("" != this.mTableData[i][j] && "--" != this.mTableData[i][j]) {
                        if (j == 9 || j == 12 || j == 15 || j == 18 || j == 21 || j == 24) {
                            this.mTableData[i][j] = (parseFloat(this.mTableData[i][j]) * 100).toFixed(2) + "%";
                        } else {
                            this.mTableData[i][j] = (parseFloat(this.mTableData[i][j])).toFixed(2) + "";
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
                    width: this.mTableData[0].length * 100,
                    shrinkToFit: true,
                    rowNum: 1000,
                    autoScroll: true
                }));
        }
    }
}