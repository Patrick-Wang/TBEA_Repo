/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="company_selector.ts" />
/// <reference path="bglx_selector.ts" />
var fx_nhqk_template;
(function (fx_nhqk_template) {
    var JQGridAssistantFactory = /** @class */ (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
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
        };
        JQGridAssistantFactory.createTable1 = function (gridName) {
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
        };
        return JQGridAssistantFactory;
    }());
    var View = /** @class */ (function () {
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
                this.mCompanySelector = new Util.CompanySelectorZzy(opt.companyId, opt.comps, opt.isSbdcy, '01');
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
            $('h1').text(date.year + "年" + compName + "整体能耗情况");
            document.title = date.year + "年" + compName + "整体能耗情况";
        };
        View.prototype.updateTable = function () {
            if (this.mTableData.length == 0) {
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
            parent.append("<table id='" + name + "'></table>");
            this.mTableAssist = JQGridAssistantFactory.createTable(name);
            $("#" + name).jqGrid(this.mTableAssist.decorate({
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
                width: this.mTableData[0].length * 100,
                shrinkToFit: true,
                rowNum: 1000,
                autoScroll: true
            }));
            var name1 = this.mOpt.tableId1 + "_jqgrid_1234";
            var parent1 = $("#" + this.mOpt.tableId1);
            parent1.empty();
            parent1.append("<table id='" + name1 + "'></table>");
            this.mTableAssist1 = JQGridAssistantFactory.createTable1(name1);
            $("#" + name1).jqGrid(this.mTableAssist1.decorate({
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
                width: this.mTableData[0].length * 100,
                shrinkToFit: true,
                rowNum: 1000,
                autoScroll: true
            }));
        };
        return View;
    }());
    fx_nhqk_template.View = View;
})(fx_nhqk_template || (fx_nhqk_template = {}));
