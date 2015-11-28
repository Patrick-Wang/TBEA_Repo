/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="company_selector.ts" />
/// <reference path="bglx_selector.ts" />
var jygk_zzy_ch_yclch;
(function (jygk_zzy_ch_yclch) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, date) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位名称", "dwmc"),
                new JQTable.Node("截止" + date.month + "月底库存金额", "ydkcje"),
                new JQTable.Node(date.year + "年初库存金额", "nckcye"),
                new JQTable.Node("较年初库存增幅", "jnckc"),
                new JQTable.Node((date.year - 1) + "年" + date.month + "月底库存金额", "ydkcye"),
                new JQTable.Node("较去年同期库存增幅", "jqn")
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mDataSet = new Util.Ajax("readview.do", false);
        }
        View.getInstance = function () {
            if (View.instance == undefined) {
                View.instance = new View();
            }
            return View.instance;
        };
        View.prototype.initInstance = function (opt) {
            this.mOpt = opt;
            if (opt.comps.length == 0) {
                $('h1').text("没有任何可以查看的公司");
                $('input').css("display", "none");
            }
            else {
                this.mOpt = opt;
                this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId);
                this.mCompanySelector = new Util.CompanySelectorZzy(opt.companyId, opt.comps, opt.isSbdcy);
                this.mBglxSelector = new Util.BglxViewSelector(opt.bglxId, opt.curbglx, opt.isByq, opt.isXl, opt.isSbdcy);
                //this.updateTextandTitle(this.mDateSelector.getDate());
                if (opt.comps.length == 1) {
                    this.mCompanySelector.hide();
                }
                this.updateUI();
            }
        };
        View.prototype.exportExcel = function () {
            var date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            $("#export")[0].action = "export.do?" + Util.Ajax.toUrlParam({ month: date.month, year: date.year, companyId: compType });
            $("#export")[0].submit();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get({ year: date.year, month: date.month, companyId: compType })
                .then(function (dataArray) {
                _this.mTableData = dataArray.values;
                _this.updateTextandTitle(date);
                _this.updateTable();
            });
        };
        View.prototype.updateTextandTitle = function (date) {
            var compName = this.mCompanySelector.getCompanyName();
            $('h1').text(date.year + "年" + date.month + "月" + compName + "原材料存货");
            document.title = date.year + "年" + date.month + "月" + compName + "原材料存货";
        };
        View.prototype.updateTable = function () {
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
                        if (j == 3 || j == 5) {
                            this.mTableData[i][j] = (parseFloat(this.mTableData[i][j]) * 100).toFixed(2) + "%";
                        }
                        else {
                            this.mTableData[i][j] = (parseFloat(this.mTableData[i][j])).toFixed(2) + "";
                        }
                    }
                    else {
                        this.mTableData[i][j] = "--";
                    }
                }
            }
            $("#" + name).jqGrid(this.mTableAssist.decorate({
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
        };
        return View;
    })();
    jygk_zzy_ch_yclch.View = View;
})(jygk_zzy_ch_yclch || (jygk_zzy_ch_yclch = {}));
