var hzbNC_zbhz;
(function (hzbNC_zbhz) {
    var ZtId;
    (function (ZtId) {
        ZtId[ZtId["zb"] = 0] = "zb";
        ZtId[ZtId["dysj"] = 1] = "dysj";
        ZtId[ZtId["dyqntq"] = 2] = "dyqntq";
        ZtId[ZtId["dytbzf"] = 3] = "dytbzf";
        ZtId[ZtId["ndlj"] = 4] = "ndlj";
        ZtId[ZtId["ndqntq"] = 5] = "ndqntq";
        ZtId[ZtId["ndtbzf"] = 6] = "ndtbzf";
    })(ZtId || (ZtId = {}));
    ;
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, 0 /* Left */),
                new JQTable.Node("当月实际", "dysj", true, 0 /* Left */),
                new JQTable.Node("去年同期", "qntq", true, 0 /* Left */),
                new JQTable.Node("同比增幅", "tbzf", true, 0 /* Left */),
                new JQTable.Node("年度累计", "ndlj", true, 0 /* Left */),
                new JQTable.Node("去年同期累计", "qnndlj", true, 0 /* Left */),
                new JQTable.Node("同比增幅", "ndtbzf", true, 0 /* Left */)
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("AllCompanysNC_overview_update.do");
            this.mType = 0;
        }
        View.newInstance = function () {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        };
        View.prototype.init = function (tableId, dateId, month, year) {
            this.mTableId = tableId;
            this.mDs = new Util.DateSelector({ year: year - 3, month: 1 }, { year: year, month: month }, dateId);
            this.updateTable();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var date = this.mDs.getDate();
            this.mDataSet.get({ month: date.month, year: date.year, type: this.mType }).then(function (dataArray) {
                _this.mData = dataArray;
                $('h1').text(date.year + "年" + date.month + "月公司整体财务指标完成情况(万元)");
                document.title = date.year + "年" + date.month + "月公司整体财务指标完成情况(万元)";
                _this.updateTable();
            });
        };
        View.prototype.initPercentList = function () {
            var precentList = new std.vector();
            precentList.push(3 /* dytbzf */);
            precentList.push(6 /* ndtbzf */);
            return precentList;
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = null;
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            tableAssist = JQGridAssistantFactory.createTable(name);
            if (this.mData.length == 0) {
                return;
            }
            var outputData = [];
            Util.formatData(outputData, this.mData, this.initPercentList(), []);
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(outputData),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: outputData.length > 23 ? 500 : '100%',
                width: 1330,
                shrinkToFit: true,
                rowNum: 200,
                autoScroll: true
            }));
        };
        return View;
    })();
    hzbNC_zbhz.View = View;
})(hzbNC_zbhz || (hzbNC_zbhz = {}));