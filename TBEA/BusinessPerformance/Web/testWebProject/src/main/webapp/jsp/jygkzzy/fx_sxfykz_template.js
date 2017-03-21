/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="company_selector.ts" />
/// <reference path="bglx_selector.ts" />
var fx_sxfykz;
(function (fx_sxfykz) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, year) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "fl", true, JQTable.TextAlign.Left),
                new JQTable.Node((year - 1) + "年", "snd")
                    .append(new JQTable.Node("年度计划", "sndndjh"))
                    .append(new JQTable.Node("实际发生", "sndsjfs"))
                    .append(new JQTable.Node("占销售收入比例", "sndzxxsrbl")),
                new JQTable.Node(year + "年", "bnd")
                    .append(new JQTable.Node("年度计划", "bndndjh"))
                    .append(new JQTable.Node("年度计划费用率", "bndndjhfyl"))
                    .append(new JQTable.Node(year + "年预算对" + (year - 1) + "实际发生增减额", "sbj")),
                new JQTable.Node("当期", "dq")
                    .append(new JQTable.Node("本月计划", "dqbyjh"))
                    .append(new JQTable.Node("实际完成", "dqsjwc"))
                    .append(new JQTable.Node("预算完成率", "dqyswcl"))
                    .append(new JQTable.Node("占销售收入比", "dqzxssrb")),
                new JQTable.Node("当期累计", "dqlj")
                    .append(new JQTable.Node("累计完成", "dqljwc"))
                    .append(new JQTable.Node("年度预算完成率", "dqljndyswcl"))
                    .append(new JQTable.Node("占销售收入比", "dqljzxssrb")),
                new JQTable.Node((year - 1) + "年同期累计", "qnlj")
                    .append(new JQTable.Node("实际发生", "qnljsjfs"))
                    .append(new JQTable.Node("占年度预算比例", "qnljndysbl"))
                    .append(new JQTable.Node("占销售收入比例", "qnljxssrbl")),
                new JQTable.Node("同比", "tb")
                    .append(new JQTable.Node("增减额", "tbzje"))
                    .append(new JQTable.Node("增减率", "tbzjl"))
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
            $('h1').text(date.year + "年" + date.month + "月" + compName + "三项费用管控");
            document.title = date.year + "年" + date.month + "月" + compName + "三项费用管控";
        };
        View.prototype.updateTable = function () {
            var name = this.mOpt.tableId + "_jqgrid_1234";
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            if (this.mTableData.length == 0) {
                $("#tips").css("display", "");
                return;
            }
            $("#tips").css("display", "none");
            this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mDateSelector.getDate().year);
            for (var i = 0; i < this.mTableData.length; ++i) {
                for (var j = 1; j < this.mTableData[i].length; ++j) {
                    if ("" != this.mTableData[i][j] && "--" != this.mTableData[i][j]) {
                        if (j == 3 || j == 9 || j == 10 || j == 12 || j == 13 || j == 15 || j == 16 || j == 18) {
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
    fx_sxfykz.View = View;
})(fx_sxfykz || (fx_sxfykz = {}));
