/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />

var gcy_zbhz;
(function (gcy_zbhz) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, month) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, 0 /* Left */),
                new JQTable.Node("产业", "cy", true, 0 /* Left */),
                new JQTable.Node("全年", "qn"),
                new JQTable.Node("当期", "dq").append(new JQTable.Node("月度计划", "yjh")).append(new JQTable.Node("月度完成", "ywc")).append(new JQTable.Node("月计划完成率", "yjhwcl", true, 220)).append(new JQTable.Node("季度计划", "jdjh")).append(new JQTable.Node("季度累计", "jdlj")).append(new JQTable.Node("季度完成率", "jdwcl")).append(new JQTable.Node("年度累计", "ndlj")).append(new JQTable.Node("累计完成率", "ljwcl")),
                new JQTable.Node("去年同期", "qntq_1").append(new JQTable.Node("去年同期", "qntq")).append(new JQTable.Node("同比增长", "tbzz")).append(new JQTable.Node("去年同期累计", "qntqlj", true, 200)).append(new JQTable.Node("同比增长", "tbzz_1"))
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();

    var View = (function () {
        function View() {
            this.mData = [];
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
            this.mDataSet = new Util.DateDataSet("gcy_zbhz_update.do");
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
            this.mDataSet.getData(this.mMonth, this.mYear, function (dataArray) {
                if (null != dataArray) {
                    _this.mData = dataArray;
                    $('h1').text(_this.mYear + "年" + _this.mMonth + "月 各产业指标汇总");
                    $('title').text(_this.mYear + "年" + _this.mMonth + "月 各产业指标汇总");
                    _this.updateTable();
                }
            });
        };

        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name, this.mMonth);
            tableAssist.mergeRow(0);

            for (var i = 0; i < 5; ++i) {
                tableAssist.setRowBgColor(i * 7 + 4, 183, 222, 232);
                tableAssist.setRowBgColor(i * 7 + 6, 183, 222, 232);
            }

            var data = [
                ["报表利润", "输变电产业"],
                ["报表利润", "新能源产业"],
                ["报表利润", "能源产业"],
                ["报表利润", "工程类"],
                ["报表利润", "股份合计"],
                ["报表利润", "众和公司"],
                ["报表利润", "集团合计"],
                ["销售收入", "输变电产业"],
                ["销售收入", "新能源产业"],
                ["销售收入", "能源产业"],
                ["销售收入", "工程类"],
                ["销售收入", "股份合计"],
                ["销售收入", "众和公司"],
                ["销售收入", "集团合计"],
                ["现金流", "输变电产业"],
                ["现金流", "新能源产业"],
                ["现金流", "能源产业"],
                ["现金流", "工程类"],
                ["现金流", "股份合计"],
                ["现金流", "众和公司"],
                ["现金流", "集团合计"],
                ["应收账款", "输变电产业"],
                ["应收账款", "新能源产业"],
                ["应收账款", "能源产业"],
                ["应收账款", "工程类"],
                ["应收账款", "股份合计"],
                ["应收账款", "众和公司"],
                ["应收账款", "集团合计"],
                ["存 货", "输变电产业"],
                ["存 货", "新能源产业"],
                ["存 货", "能源产业"],
                ["存 货", "工程类"],
                ["存 货", "股份合计"],
                ["存 货", "众和公司"],
                ["存 货", "集团合计"]];

            //            for (var i = 0; i < data.length; ++i) {
            //                if (this.mData[i] instanceof Array) {
            //                    data[i] = data[i].concat(this.mData[i]);
            //                }
            //            }
            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (this.mData[i] instanceof Array) {
                    row = [].concat(this.mData[i]);
                    for (var col in row) {
                        if (col != '3' && col != '5' && col != '7' && col != '9' && col != '11') {
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
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
                //                    cellsubmit: 'clientArray',
                //                    cellEdit: true,
                height: 600,
                width: 1200,
                shrinkToFit: true,
                rowNum: 200,
                autoScroll: true
            }));
        };
        return View;
    })();
    gcy_zbhz.View = View;
})(gcy_zbhz || (gcy_zbhz = {}));
//# sourceMappingURL=gcy_zbhz.js.map
