/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="company_selector.ts" />
/// <reference path="bglx_selector.ts" />
declare var $;
module jygk_zzy_cc_kglyddcbqk_xl {

    class JQGridAssistantFactory {

        public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dwid", true, JQTable.TextAlign.Left),
                new JQTable.Node("月产出能力（产值）", "yccnlcz"),
                new JQTable.Node("未履约订单总额", "yy"),
                new JQTable.Node("可供履约订单（不含税）", "yd")
                    .append(new JQTable.Node("本年度未履约订单总量", "yd0"))
                    .append(new JQTable.Node("n+1月订单量", "yd1").append(new JQTable.Node("已排产", "n1cz")).append(new JQTable.Node("未排产", "n1czn")).append(new JQTable.Node("产能发挥率", "n1fhl")))
                    .append(new JQTable.Node("n+2月订单量", "yd2").append(new JQTable.Node("已排产", "n2cz")).append(new JQTable.Node("未排产", "n2czn")).append(new JQTable.Node("产能发挥率", "n2fhl")))
                    .append(new JQTable.Node("n+3月订单量", "yd3").append(new JQTable.Node("已排产", "n3cz")).append(new JQTable.Node("未排产", "n3czn")).append(new JQTable.Node("产能发挥率", "n3fhl")))
                    .append(new JQTable.Node("n+3月及以后履约订单", "yd4")),
                new JQTable.Node("交货期待定", "ddcz"),
                new JQTable.Node("外协", "wxcz")
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
        private mDataSet: Util.Ajax = new Util.Ajax("readviewxl.do", false);
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
                this.mCompanySelector = new Util.CompanySelectorZzy(opt.companyId, opt.comps, opt.isSbdcy,'03');
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
            $("#export")[0].action = "exportxl.do?" + Util.Ajax.toUrlParam({ year: date.year, month: date.month, companyId: compType });
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
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            if (this.mTableData.length == 0) {
                $("#tips").css("display", "");
                return;
            }
            $("#tips").css("display", "none");

            this.mTableAssist = JQGridAssistantFactory.createTable(name)
            for (var i = 0; i < this.mTableData.length; ++i) {
                for (var j = 1; j < this.mTableData[i].length; ++j) {
                    if ("" != this.mTableData[i][j] && "--" != this.mTableData[i][j]) {
                        if (j == 6 || j == 9 || j == 12) {
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