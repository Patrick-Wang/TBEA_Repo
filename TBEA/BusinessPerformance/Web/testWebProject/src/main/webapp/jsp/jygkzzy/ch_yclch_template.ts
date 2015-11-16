/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="../companySelector.ts" />
/// <reference path="bglx_selector.ts" />
declare var echarts;
declare var $;
module jygk_zzy_ch_yclch {

    class JQGridAssistantFactory {
        public static createTable(gridName: string, date: Util.Date): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                //               titles = ["单位名称", "截止" + date.month + "月底库存金额", date.year + "年初库存金额", "较年初库存增幅", (date.year - 1) + "年" + date.month + "月底库存金额", "较去年同期库存增幅"];
                new JQTable.Node("单位名称", "dwmc"),
                new JQTable.Node("截止" + date.month + "月底库存金额", "ydkcje"),
                new JQTable.Node(date.year + "年初库存金额", "nckcye"),
                new JQTable.Node("较年初库存增幅", "jnckc"),
                new JQTable.Node((date.year - 1) + "年" + date.month + "月底库存金额", "ydkcye"),
                new JQTable.Node("较去年同期库存增幅", "jqn")
            ], gridName);
        }
    }

    interface IViewOption {
        tableId: string;
        dateId: string;
        date?: Util.Date;
        companyId: string;
        comps: Util.IDataNode[];
        bglxId: string;
        curbglx: string;
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
        private mCompanySelector: Util.CompanySelector;
        private mBglxSelector: Util.BglxViewSelector;
        private mOpt: IViewOption;
        private mDataSet: Util.Ajax = new Util.Ajax("readview.do", false);
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
                this.mCompanySelector = new Util.CompanySelector(false, opt.companyId, opt.comps);
                this.mBglxSelector = new Util.BglxViewSelector(opt.bglxId, opt.curbglx);
                this.updateUI();
            }
        }

        public exportExcel(fName: string) {
            var date: Util.Date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            $("#export")[0].action = "export.do?" + Util.Ajax.toUrlParam({ month: date.month, year: date.year, companyId: compType });
            $("#export")[0].submit();
        }

        public updateUI() {
            var date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get({ year: date.year, month: date.month, companyId: compType })
                .then((dataArray: any) => {
                    this.mTableData = dataArray.values;
                    this.updateTextandTitle();
                    this.updateTable();

                });
        }

        private updateTextandTitle() {
            var header = "";
            var date = this.mDateSelector.getDate();
            var compName = this.mCompanySelector.getCompanyName();
            header = date.year + "年" + date.month + "月 " + compName + " 原材料存货查看";

            $('h1').text(header);
            document.title = header;
            //            $('h1').text(date.year + "年" + date.month + "月可供履约订单储备情况");
            //            document.title = date.year + "年" + date.month + "月可供履约订单储备情况";
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

            this.mTableAssist = JQGridAssistantFactory.createTable(name, date);
            for (var i = 0; i < this.mTableData.length; ++i) {
                for (var j = 1; j < this.mTableData[i].length; ++j) {
                    if ("" != this.mTableData[i][j] && "--" != this.mTableData[i][j]) {
                        if(j == 3 ||j == 5){
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
                    height: this.mTableData.length > 23 ? 500 : '100%',
                    width: this.mTableData[0].length*100,
                    shrinkToFit: true,
                    rowNum: 1000,
                    autoScroll: true
                }));
        }
    }
}