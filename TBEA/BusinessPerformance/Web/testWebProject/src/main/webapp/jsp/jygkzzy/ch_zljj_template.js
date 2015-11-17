/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="../companySelector.ts" />
/// <reference path="bglx_selector.ts" />
var jygk_zzy_ch_zljj;
(function (jygk_zzy_ch_zljj) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, date) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("5年以上", "yd")
                    .append(new JQTable.Node("原材料", "n5sycl"))
                    .append(new JQTable.Node("半成品", "n5sbcp"))
                    .append(new JQTable.Node("产成品", "n5sccp"))
                    .append(new JQTable.Node("其他", "n5sqt")),
                new JQTable.Node("4-5年", "yd")
                    .append(new JQTable.Node("原材料", "n4z5ycl"))
                    .append(new JQTable.Node("半成品", "n4z5bcp"))
                    .append(new JQTable.Node("产成品", "n4z5ccp"))
                    .append(new JQTable.Node("其他", "n4z5qt")),
                new JQTable.Node("3-4年", "yd")
                    .append(new JQTable.Node("原材料", "n3z4ycl"))
                    .append(new JQTable.Node("半成品", "n3z4bcp"))
                    .append(new JQTable.Node("产成品", "n3z4ccp"))
                    .append(new JQTable.Node("其他", "n3z4qt")),
                new JQTable.Node("2-3年", "yd")
                    .append(new JQTable.Node("原材料", "n2z3ycl"))
                    .append(new JQTable.Node("半成品", "n2z3bcp"))
                    .append(new JQTable.Node("产成品", "n2z3ccp"))
                    .append(new JQTable.Node("其他", "n2z3qt")),
                new JQTable.Node("1-2年", "yd")
                    .append(new JQTable.Node("原材料", "n1z2ycl"))
                    .append(new JQTable.Node("半成品", "n1z2bcp"))
                    .append(new JQTable.Node("产成品", "n1z2ccp"))
                    .append(new JQTable.Node("其他", "n1z2qt")),
                new JQTable.Node("1年以内", "yd")
                    .append(new JQTable.Node("原材料", "n1ycl"))
                    .append(new JQTable.Node("半成品", "n1bcp"))
                    .append(new JQTable.Node("产成品", "n1ccp"))
                    .append(new JQTable.Node("其他", "n1qt")),
                new JQTable.Node("合计", "hj", true, JQTable.TextAlign.Right)
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
                this.mCompanySelector = new Util.CompanySelector(false, opt.companyId, opt.comps);
                this.mBglxSelector = new Util.BglxViewSelector(opt.bglxId, opt.curbglx);
                this.updateUI();
            }
        };
        View.prototype.exportExcel = function (fName) {
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
                _this.updateTextandTitle();
                _this.updateTable();
            });
        };
        View.prototype.updateTextandTitle = function () {
            var header = "";
            var date = this.mDateSelector.getDate();
            var compName = this.mCompanySelector.getCompanyName();
            header = date.year + "年" + date.month + "月 " + compName + " 账龄结构查看";
            $('h1').text(header);
            document.title = header;
            //            $('h1').text(date.year + "年" + date.month + "月可供履约订单储备情况");
            //            document.title = date.year + "年" + date.month + "月可供履约订单储备情况";
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
                        this.mTableData[i][j] = parseFloat(this.mTableData[i][j]) + "";
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
                height: this.mTableData.length > 23 ? 500 : '100%',
                width: this.mTableData[0].length * 100,
                shrinkToFit: true,
                rowNum: 1000,
                autoScroll: true
            }));
        };
        return View;
    })();
    jygk_zzy_ch_zljj.View = View;
})(jygk_zzy_ch_zljj || (jygk_zzy_ch_zljj = {}));
