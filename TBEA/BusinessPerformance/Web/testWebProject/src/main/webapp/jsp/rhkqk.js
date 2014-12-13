var rhkqk;
(function (rhkqk) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dw", true, 0 /* Left */),
                new JQTable.Node("集团下达月度资金回笼指标", "dwxdydzjhlzb"),
                new JQTable.Node("各单位自行制定的回款计划", "gdwzxdzdhkjh"),
                new JQTable.Node("今日回款", "jrhk"),
                new JQTable.Node("月累计(截止今日)", "ylj"),
                new JQTable.Node("资金回笼指标完成", "zjhlzbwc"),
                new JQTable.Node("回款计划完成率", "hkjhwcl"),
                new JQTable.Node("已回款中可降应收的回款金额", "yhkzkjysdhkje"),
                new JQTable.Node("目前-月底回款计划", "mqydhkjh").append(new JQTable.Node("确保搬出", "qbbc")).append(new JQTable.Node("争取办出", "zqbc")).append(new JQTable.Node("两者合计", "lzhj")),
                new JQTable.Node("全月确保", "qyqb"),
                new JQTable.Node("预计全月计划完成率", "yjqyjhwcl"),
                new JQTable.Node("截止月底应收账款账面余额", "jzydyszkzmye")
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mData = [];
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (tableId, month, year, day) {
            this.mYear = year;
            this.mMonth = month;
            this.mDataSet = new Util.DateDataSet("rhkqk_update.do");
            this.mTableId = tableId;
            this.mDay = day;
            this.updateTable();
            this.updateUI();
        };
        View.prototype.onDaySelected = function (day) {
            this.mDay = day;
        };
        View.prototype.onYearSelected = function (year) {
            this.mYear = year;
        };
        View.prototype.onMonthSelected = function (month) {
            this.mMonth = month;
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.getDataByDay(this.mMonth, this.mYear, this.mDay, function (dataArray) {
                if (null != dataArray) {
                    _this.mData = dataArray;
                    $('h1').text(_this.mYear + "年" + _this.mMonth + "月" + _this.mDay + "日  回款情况");
                    document.title = _this.mYear + "年" + _this.mMonth + "月" + _this.mDay + "日  回款情况";
                    _this.updateTable();
                }
            });
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name);
            var data = [["沈变公司"], ["衡变公司"], ["新变厂"], ["其中：天变公司"], ["鲁缆公司"], ["新缆厂"], ["德缆公司"], ["合计"]];
            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (this.mData[i] instanceof Array) {
                    row = [].concat(this.mData[i]);
                    for (var col in row) {
                        if (col != 4 && col != 11)
                            row[col] = Util.formatCurrency(row[col]);
                    }
                    data[i] = data[i].concat(row);
                }
            }
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: '100%',
                width: 1200,
                shrinkToFit: false,
                autoScroll: true
            }));
        };
        return View;
    })();
    rhkqk.View = View;
})(rhkqk || (rhkqk = {}));
