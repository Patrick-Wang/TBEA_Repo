/**
 * Created by Floyd on 2016/3/28.
 */
/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var yearTotal;
(function (yearTotal) {
    var JQGridAssistantFactory = /** @class */ (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("月度", "yd", true, JQTable.TextAlign.Center),
                new JQTable.Node("月度", "yd1", true, JQTable.TextAlign.Center),
                new JQTable.Node("5年以上", "wnys", true, JQTable.TextAlign.Left),
                new JQTable.Node("4-5年", "sdwn", true, JQTable.TextAlign.Left),
                new JQTable.Node("3-4年", "sdsn", true, JQTable.TextAlign.Left),
                new JQTable.Node("2-3年", "edsn", true, JQTable.TextAlign.Left),
                new JQTable.Node("1-2年", "yden", true, JQTable.TextAlign.Left),
                new JQTable.Node("1年以内", "ynyn", true, JQTable.TextAlign.Left),
                new JQTable.Node("合计", "hj", true, JQTable.TextAlign.Left),
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
    var View = /** @class */ (function () {
        function View() {
            this.mComp = Util.CompanyType.HBGS;
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (tableId, year, month) {
            this.mYear = year;
            this.mMonth = month;
            this.mTableId = tableId;
            this.mDataSet = new Util.Ajax("blhtdqqkhz_update.do");
            this.updateTable(this.mTableId);
            this.updateUI();
        };
        View.prototype.onYearSelected = function (year) {
            this.mYear = year;
        };
        View.prototype.onCompanySelected = function (comp) {
            this.mComp = comp;
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.get({
                year: this.mYear,
                companyId: this.mComp
            })
                .then(function (jsonData) {
                _this.mTableData = jsonData;
                document.title = _this.mYear + "年";
                _this.updateTable(_this.mTableId);
            });
        };
        View.prototype.updateTable = function (name) {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            var data = [];
            for (var i = this.mMonth + 1; i <= 12; ++i) {
                data.push(["上年度", i]);
            }
            for (var i = 1; i <= this.mMonth; ++i) {
                data.push(["本年度", i]);
            }
            if (undefined != this.mTableData) {
                var row = [];
                for (var i = 0; i < data.length; ++i) {
                    row = [].concat(this.mTableData[i]);
                    for (var col in row) {
                        row[col] = Util.formatCurrency(row[col]);
                    }
                    data[i] = data[i].concat(row);
                }
            }
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: 1200,
                shrinkToFit: true,
                autoScroll: true,
                data: tableAssist.getData(data),
                datatype: "local"
            }));
        };
        return View;
    }());
    yearTotal.View = View;
})(yearTotal || (yearTotal = {}));
var view = yearTotal.View.newInstance();
