var hzb_zbhz;
(function (hzb_zbhz) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, month) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("序号", "xh", true, JQTable.TextAlign.Left, 60),
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("当期", "dq").append(new JQTable.Node("月度计划", "yjh")).append(new JQTable.Node("月度完成", "ywc")).append(new JQTable.Node("月完成率", "ywcl")).append(new JQTable.Node("季度累计", "jdlj")).append(new JQTable.Node("季度完成率", "jdwcl")).append(new JQTable.Node("年度累计", "ndlj")).append(new JQTable.Node("年度完成率", "ndwcl")),
                new JQTable.Node("去年", "qn").append(new JQTable.Node("去年同期", "qntq")).append(new JQTable.Node("同比增长", "tbzz")).append(new JQTable.Node("去年同期累计", "qntqlj")).append(new JQTable.Node("同比增长", "tbzz_1"))
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("hzb_zbhz_update.do");
        }
        View.newInstance = function () {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        };
        View.prototype.init = function (tableId, month, year) {
            this.mYear = year;
            this.mMonth = month;
            this.mTableId = tableId;
            this.updateTable();
            this.updateUI();
        };
        View.prototype.onYearSelected = function (year) {
            this.mYear = year;
        };
        View.prototype.onMonthSelected = function (month) {
            this.mMonth = month;
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.get({ month: this.mMonth, year: this.mYear }).then(function (dataArray) {
                _this.mData = dataArray;
                $('h1').text(_this.mYear + "年" + _this.mMonth + "月 指标汇总");
                document.title = _this.mYear + "年" + _this.mMonth + "月 指标汇总";
                _this.updateTable();
            });
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name, this.mMonth);
            var data = [["1", "报表利润"], ["2", "经营性净现金流"], ["3", "应收账款"], ["4", "其中：逾期款"], ["5", "存货 "], ["6", "其中：积压物资"], ["7", "合同签约额"], ["8", "资金回笼"], ["9", "不含税产值"], ["10", "产量：变压器"], ["11", "产量：用铜量"], ["12", "产量：用铝量"], ["13", "人数"], ["14", "人均利润"], ["15", "人均收入"], ["16", "三项费用"], ["17", "三项费用率"]];
            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (this.mData[i] instanceof Array) {
                    row = [].concat(this.mData[i]);
                    for (var col in row) {
                        if (col != '2' && col != '4' && col != '6' && col != '8' && col != '10') {
                            row[col] = Util.formatCurrency(row[col]);
                        }
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
                height: '100%',
                width: 1200,
                shrinkToFit: true,
                rowNum: 100,
                autoScroll: true
            }));
        };
        return View;
    })();
    hzb_zbhz.View = View;
})(hzb_zbhz || (hzb_zbhz = {}));
