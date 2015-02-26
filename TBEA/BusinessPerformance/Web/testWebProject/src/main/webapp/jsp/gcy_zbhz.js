var gcy_zbhz;
(function (gcy_zbhz) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, month) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("全年计划", "qnjh"),
                new JQTable.Node("月度", "yd").append(new JQTable.Node("当月计划", "y1")).append(new JQTable.Node("当月实际", "y2")).append(new JQTable.Node("计划完成率", "y3")).append(new JQTable.Node("去年同期", "y4")).append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度", "jd").append(new JQTable.Node("季度计划", "j1")).append(new JQTable.Node("季度累计", "j2")).append(new JQTable.Node("季度计划完成率", "j3")).append(new JQTable.Node("去年同期", "j4")).append(new JQTable.Node("同比增幅", "j5")),
                new JQTable.Node("年度", "nd").append(new JQTable.Node("年度累计", "n1")).append(new JQTable.Node("累计计划完成率", "n2")).append(new JQTable.Node("去年同期", "n3")).append(new JQTable.Node("同比增幅", "n4")),
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("gcy_zbhz_update.do");
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
            this.mDataSet.get({ month: this.mMonth, year: this.mYear }).then(function (jsonData) {
                _this.mData = jsonData;
                $('h1').text(_this.mYear + "年" + _this.mMonth + "月 各产业指标汇总");
                document.title = _this.mYear + "年" + _this.mMonth + "月 各产业指标汇总";
                _this.updateTable();
            });
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name, this.mMonth);
            tableAssist.mergeRow(0);
            for (var i = 0; i < 5; ++i) {
                tableAssist.setRowBgColor(i * 8 + 5, 183, 222, 232);
                tableAssist.setRowBgColor(i * 8 + 7, 183, 222, 232);
            }
            var data = [
                ["报表利润", "输变电产业"],
                ["报表利润", "新能源产业"],
                ["报表利润", "能源产业"],
                ["报表利润", "进出口公司"],
                ["报表利润", "国际工程公司"],
                ["报表利润", "股份合计"],
                ["报表利润", "众和公司"],
                ["报表利润", "集团合计"],
                ["销售收入", "输变电产业"],
                ["销售收入", "新能源产业"],
                ["销售收入", "能源产业"],
                ["销售收入", "进出口公司"],
                ["销售收入", "国际工程公司"],
                ["销售收入", "股份合计"],
                ["销售收入", "众和公司"],
                ["销售收入", "集团合计"],
                ["现金流", "输变电产业"],
                ["现金流", "新能源产业"],
                ["现金流", "能源产业"],
                ["现金流", "进出口公司"],
                ["现金流", "国际工程公司"],
                ["现金流", "股份合计"],
                ["现金流", "众和公司"],
                ["现金流", "集团合计"],
                ["应收账款", "输变电产业"],
                ["应收账款", "新能源产业"],
                ["应收账款", "能源产业"],
                ["应收账款", "进出口公司"],
                ["应收账款", "国际工程公司"],
                ["应收账款", "股份合计"],
                ["应收账款", "众和公司"],
                ["应收账款", "集团合计"],
                ["存 货", "输变电产业"],
                ["存 货", "新能源产业"],
                ["存 货", "能源产业"],
                ["存 货", "进出口公司"],
                ["存 货", "国际工程公司"],
                ["存 货", "股份合计"],
                ["存 货", "众和公司"],
                ["存 货", "集团合计"]
            ];
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
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
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
