var hzb_zbhz;
(function (hzb_zbhz) {
    var SrqyId;
    (function (SrqyId) {
        SrqyId[SrqyId["zb"] = 0] = "zb";
        SrqyId[SrqyId["qnjh"] = 1] = "qnjh";
        SrqyId[SrqyId["dyjh"] = 2] = "dyjh";
        SrqyId[SrqyId["dysj"] = 3] = "dysj";
        SrqyId[SrqyId["jhwcl"] = 4] = "jhwcl";
        SrqyId[SrqyId["ljwc"] = 5] = "ljwc";
        SrqyId[SrqyId["ljwcl"] = 6] = "ljwcl";
        SrqyId[SrqyId["qntqz"] = 7] = "qntqz";
        SrqyId[SrqyId["tbzzl"] = 8] = "tbzzl";
        SrqyId[SrqyId["qntqlj"] = 9] = "qntqlj";
        SrqyId[SrqyId["ljtbzzl"] = 10] = "ljtbzzl";
    })(SrqyId || (SrqyId = {}));
    ;
    var ZtId;
    (function (ZtId) {
        ZtId[ZtId["zb"] = 0] = "zb";
        ZtId[ZtId["qnjh"] = 1] = "qnjh";
        ZtId[ZtId["dyjh"] = 2] = "dyjh";
        ZtId[ZtId["dysj"] = 3] = "dysj";
        ZtId[ZtId["dyjhwcl"] = 4] = "dyjhwcl";
        ZtId[ZtId["dyqntq"] = 5] = "dyqntq";
        ZtId[ZtId["dytbzf"] = 6] = "dytbzf";
        ZtId[ZtId["jdjh"] = 7] = "jdjh";
        ZtId[ZtId["jdlj"] = 8] = "jdlj";
        ZtId[ZtId["jdjhwcl"] = 9] = "jdjhwcl";
        ZtId[ZtId["jdqntq"] = 10] = "jdqntq";
        ZtId[ZtId["jdtbzf"] = 11] = "jdtbzf";
        ZtId[ZtId["ndlj"] = 12] = "ndlj";
        ZtId[ZtId["ndljjhwcl"] = 13] = "ndljjhwcl";
        ZtId[ZtId["ndqntq"] = 14] = "ndqntq";
        ZtId[ZtId["ndtbzf"] = 15] = "ndtbzf";
    })(ZtId || (ZtId = {}));
    ;
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createSrqyTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, 0 /* Left */),
                new JQTable.Node("当期", "dq").append(new JQTable.Node("全年计划", "1")).append(new JQTable.Node("当月计划", "2")).append(new JQTable.Node("当月实际", "3")).append(new JQTable.Node("计划完成率", "4")).append(new JQTable.Node("累计完成", "5")).append(new JQTable.Node("累计完成率", "6")),
                new JQTable.Node("去年", "qn").append(new JQTable.Node("去年同期值", "7")).append(new JQTable.Node("同比增长率", "8")).append(new JQTable.Node("去年同期累计", "9")).append(new JQTable.Node("同比增长率", "10"))
            ], gridName);
        };
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, 0 /* Left */),
                new JQTable.Node("全年计划", "qnjh"),
                new JQTable.Node("月度", "yd").append(new JQTable.Node("当月计划", "y1")).append(new JQTable.Node("当月实际", "y2")).append(new JQTable.Node("计划完成率", "y3")).append(new JQTable.Node("去年同期", "y4")).append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度", "jd").append(new JQTable.Node("季度计划", "j1")).append(new JQTable.Node("季度累计", "j2")).append(new JQTable.Node("季度计划完成率", "j3")).append(new JQTable.Node("去年同期", "j4")).append(new JQTable.Node("同比增幅", "j5")),
                new JQTable.Node("年度", "nd").append(new JQTable.Node("年度累计", "n1")).append(new JQTable.Node("累计计划完成率", "n2")).append(new JQTable.Node("去年同期", "n3")).append(new JQTable.Node("同比增幅", "n4"))
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("hzb_zbhz_update.do");
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
            this.updateUI();
        };
        View.prototype.onTypeSelected = function (ty) {
            this.mType = ty;
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var date = this.mDs.getDate();
            this.mDataSet.get({ month: date.month, year: date.year, type: this.mType }).then(function (dataArray) {
                _this.mData = dataArray;
                $('h1').text(date.year + "年" + date.month + "月公司整体指标完成情况");
                document.title = date.year + "年" + date.month + "月公司整体指标完成情况";
                _this.updateTable();
                _this.updateButton();
            });
        };
        View.prototype.updateButton = function () {
            if (this.mType != 0) {
                $("#exportxmgs").hide();
            }
            else {
                $("#exportxmgs").show();
            }
        };
        View.prototype.initZTPercentList = function () {
            var precentList = new std.vector();
            precentList.push(4 /* dyjhwcl */);
            precentList.push(6 /* dytbzf */);
            precentList.push(9 /* jdjhwcl */);
            precentList.push(11 /* jdtbzf */);
            precentList.push(13 /* ndljjhwcl */);
            precentList.push(15 /* ndtbzf */);
            return precentList;
        };
        View.prototype.initSrqyPercentList = function () {
            var precentList = new std.vector();
            precentList.push(4 /* jhwcl */);
            precentList.push(6 /* ljwcl */);
            precentList.push(8 /* tbzzl */);
            precentList.push(10 /* ljtbzzl */);
            return precentList;
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = null;
            var data = [];
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            if (this.mData.length == 0) {
                return;
            }
            var outputData = [];
            if (0 == this.mType) {
                tableAssist = JQGridAssistantFactory.createTable(name);
                Util.formatData(outputData, this.mData, this.initZTPercentList(), []);
            }
            else {
                tableAssist = JQGridAssistantFactory.createSrqyTable(name);
                Util.formatData(outputData, this.mData, this.initSrqyPercentList(), []);
            }
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(outputData),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: data.length > 23 ? 500 : '100%',
                width: 1330,
                shrinkToFit: true,
                rowNum: 200,
                autoScroll: true
            }));
        };
        return View;
    })();
    hzb_zbhz.View = View;
})(hzb_zbhz || (hzb_zbhz = {}));
