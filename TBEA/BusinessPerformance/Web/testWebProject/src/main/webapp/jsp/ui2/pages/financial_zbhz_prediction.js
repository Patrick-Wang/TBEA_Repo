/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
var financial_zbhz_prediciton;
(function (financial_zbhz_prediciton) {
    var router = framework.router;
    var FirstMonthZb;
    (function (FirstMonthZb) {
        FirstMonthZb[FirstMonthZb["ndjh"] = 0] = "ndjh";
        FirstMonthZb[FirstMonthZb["jdjh"] = 1] = "jdjh";
        FirstMonthZb[FirstMonthZb["byjhz"] = 2] = "byjhz";
        FirstMonthZb[FirstMonthZb["dyyjz"] = 3] = "dyyjz";
        FirstMonthZb[FirstMonthZb["dyjhwcl"] = 4] = "dyjhwcl";
        FirstMonthZb[FirstMonthZb["dyqntq"] = 5] = "dyqntq";
        FirstMonthZb[FirstMonthZb["dytbzf"] = 6] = "dytbzf";
        FirstMonthZb[FirstMonthZb["cyyj"] = 7] = "cyyj";
        FirstMonthZb[FirstMonthZb["myyj"] = 8] = "myyj";
        FirstMonthZb[FirstMonthZb["jdyjhj"] = 9] = "jdyjhj";
        FirstMonthZb[FirstMonthZb["jdyjwcl"] = 10] = "jdyjwcl";
        FirstMonthZb[FirstMonthZb["jdqntq"] = 11] = "jdqntq";
        FirstMonthZb[FirstMonthZb["jdtbzf"] = 12] = "jdtbzf";
        FirstMonthZb[FirstMonthZb["ndljwcz"] = 13] = "ndljwcz";
        FirstMonthZb[FirstMonthZb["ndzbwcl"] = 14] = "ndzbwcl";
        FirstMonthZb[FirstMonthZb["ndqntqz"] = 15] = "ndqntqz";
        FirstMonthZb[FirstMonthZb["ndtbzf"] = 16] = "ndtbzf";
    })(FirstMonthZb || (FirstMonthZb = {}));
    ;
    var SecondMonthZb;
    (function (SecondMonthZb) {
        SecondMonthZb[SecondMonthZb["ndjh"] = 0] = "ndjh";
        SecondMonthZb[SecondMonthZb["jdjh"] = 1] = "jdjh";
        SecondMonthZb[SecondMonthZb["byjhz"] = 2] = "byjhz";
        SecondMonthZb[SecondMonthZb["dyyjz"] = 3] = "dyyjz";
        SecondMonthZb[SecondMonthZb["dyjhwcl"] = 4] = "dyjhwcl";
        SecondMonthZb[SecondMonthZb["dyqntq"] = 5] = "dyqntq";
        SecondMonthZb[SecondMonthZb["dytbzf"] = 6] = "dytbzf";
        SecondMonthZb[SecondMonthZb["jdlj"] = 7] = "jdlj";
        SecondMonthZb[SecondMonthZb["jdjhwcl"] = 8] = "jdjhwcl";
        SecondMonthZb[SecondMonthZb["jdqntqz"] = 9] = "jdqntqz";
        SecondMonthZb[SecondMonthZb["jdtbzf"] = 10] = "jdtbzf";
        SecondMonthZb[SecondMonthZb["jdmyyj"] = 11] = "jdmyyj";
        SecondMonthZb[SecondMonthZb["jdyjhj"] = 12] = "jdyjhj";
        SecondMonthZb[SecondMonthZb["jdyjwcl"] = 13] = "jdyjwcl";
        SecondMonthZb[SecondMonthZb["jdyjqntq"] = 14] = "jdyjqntq";
        SecondMonthZb[SecondMonthZb["jdyjtbzf"] = 15] = "jdyjtbzf";
        SecondMonthZb[SecondMonthZb["ndljwcz"] = 16] = "ndljwcz";
        SecondMonthZb[SecondMonthZb["ndzbwcl"] = 17] = "ndzbwcl";
        SecondMonthZb[SecondMonthZb["ndqntqz"] = 18] = "ndqntqz";
        SecondMonthZb[SecondMonthZb["ndtbzf"] = 19] = "ndtbzf";
    })(SecondMonthZb || (SecondMonthZb = {}));
    ;
    var ThirdMonthZb;
    (function (ThirdMonthZb) {
        ThirdMonthZb[ThirdMonthZb["ndjh"] = 0] = "ndjh";
        ThirdMonthZb[ThirdMonthZb["bjdjh"] = 1] = "bjdjh";
        ThirdMonthZb[ThirdMonthZb["xjdjh"] = 2] = "xjdjh";
        ThirdMonthZb[ThirdMonthZb["dyjhz"] = 3] = "dyjhz";
        ThirdMonthZb[ThirdMonthZb["dyyjz"] = 4] = "dyyjz";
        ThirdMonthZb[ThirdMonthZb["dyjhwcl"] = 5] = "dyjhwcl";
        ThirdMonthZb[ThirdMonthZb["dyqntq"] = 6] = "dyqntq";
        ThirdMonthZb[ThirdMonthZb["dytbzf"] = 7] = "dytbzf";
        ThirdMonthZb[ThirdMonthZb["jdlj"] = 8] = "jdlj";
        ThirdMonthZb[ThirdMonthZb["jdjhwcl"] = 9] = "jdjhwcl";
        ThirdMonthZb[ThirdMonthZb["jdqntqz"] = 10] = "jdqntqz";
        ThirdMonthZb[ThirdMonthZb["jdtbzf"] = 11] = "jdtbzf";
        ThirdMonthZb[ThirdMonthZb["ndljwcz"] = 12] = "ndljwcz";
        ThirdMonthZb[ThirdMonthZb["ndzbwcl"] = 13] = "ndzbwcl";
        ThirdMonthZb[ThirdMonthZb["ndqntqz"] = 14] = "ndqntqz";
        ThirdMonthZb[ThirdMonthZb["ndtbzf"] = 15] = "ndtbzf";
        ThirdMonthZb[ThirdMonthZb["xjdsyyj"] = 16] = "xjdsyyj";
        ThirdMonthZb[ThirdMonthZb["xjdcyyj"] = 17] = "xjdcyyj";
        ThirdMonthZb[ThirdMonthZb["xjdmyyj"] = 18] = "xjdmyyj";
        ThirdMonthZb[ThirdMonthZb["xjdyjhj"] = 19] = "xjdyjhj";
        ThirdMonthZb[ThirdMonthZb["xjdyjwcl"] = 20] = "xjdyjwcl";
        ThirdMonthZb[ThirdMonthZb["xjdndlj"] = 21] = "xjdndlj";
        ThirdMonthZb[ThirdMonthZb["xjdndljwcl"] = 22] = "xjdndljwcl";
        ThirdMonthZb[ThirdMonthZb["xjdqntq"] = 23] = "xjdqntq";
        ThirdMonthZb[ThirdMonthZb["xjdtbzf"] = 24] = "xjdtbzf";
    })(ThirdMonthZb || (ThirdMonthZb = {}));
    ;
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, gridStyle) {
            if (1 == gridStyle) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                    new JQTable.Node("产业", "cy"),
                    new JQTable.Node("年度计划", "ndjh"),
                    new JQTable.Node("本季度计划", "jdjh"),
                    new JQTable.Node("当月完成", "dywc")
                        .append(new JQTable.Node("本月计划值", "y1"))
                        .append(new JQTable.Node("当月预计值", "y2"))
                        .append(new JQTable.Node("计划完成率", "y3"))
                        .append(new JQTable.Node("去年同期", "y4"))
                        .append(new JQTable.Node("同比增幅", "y5")),
                    new JQTable.Node("季度预计完成", "jdyjwc")
                        .append(new JQTable.Node("次月预计", "j1"))
                        .append(new JQTable.Node("末月预计", "j2"))
                        .append(new JQTable.Node("季度预计合计", "j3"))
                        .append(new JQTable.Node("季度预计完成率", "j4"))
                        .append(new JQTable.Node("去年同期", "j5"))
                        .append(new JQTable.Node("同比增幅", "j6")),
                    new JQTable.Node("年度累计完成", "nd")
                        .append(new JQTable.Node("累计完成值", "n1"))
                        .append(new JQTable.Node("年度指标完成率", "n2"))
                        .append(new JQTable.Node("去年同期值", "n3"))
                        .append(new JQTable.Node("同比增幅", "n4"))
                ], gridName);
            }
            if (2 == gridStyle) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                    new JQTable.Node("产业", "cy"),
                    new JQTable.Node("年度计划", "ndjh"),
                    new JQTable.Node("本季度计划", "jdjh"),
                    new JQTable.Node("当月完成", "dywc")
                        .append(new JQTable.Node("本月计划值", "y1"))
                        .append(new JQTable.Node("当月预计值", "y2"))
                        .append(new JQTable.Node("计划完成率", "y3"))
                        .append(new JQTable.Node("去年同期", "y4"))
                        .append(new JQTable.Node("同比增幅", "y5")),
                    new JQTable.Node("季度累计完成", "jdljwc")
                        .append(new JQTable.Node("季度累计", "jl1"))
                        .append(new JQTable.Node("季度计划完成率", "jl2"))
                        .append(new JQTable.Node("去年同期值", "jl3"))
                        .append(new JQTable.Node("同比增幅", "jl4")),
                    new JQTable.Node("季度预计完成", "jdyjwc")
                        .append(new JQTable.Node("末月预计", "jy1"))
                        .append(new JQTable.Node("季度预计合计", "jy2"))
                        .append(new JQTable.Node("季度预计完成率", "jy3"))
                        .append(new JQTable.Node("去年同期", "jy4"))
                        .append(new JQTable.Node("同比增幅", "jy5")),
                    new JQTable.Node("年度累计完成", "nd")
                        .append(new JQTable.Node("累计完成值", "n1"))
                        .append(new JQTable.Node("年度指标完成率", "n2"))
                        .append(new JQTable.Node("去年同期值", "n3"))
                        .append(new JQTable.Node("同比增幅", "n4"))
                ], gridName);
            }
            if (3 == gridStyle) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                    new JQTable.Node("产业", "cy"),
                    new JQTable.Node("年度计划", "ndjh"),
                    new JQTable.Node("本季度计划", "jdjh"),
                    new JQTable.Node("下季度计划", "xjdjh"),
                    new JQTable.Node("当月完成", "dywc")
                        .append(new JQTable.Node("本月计划值", "y1"))
                        .append(new JQTable.Node("当月预计值", "y2"))
                        .append(new JQTable.Node("计划完成率", "y3"))
                        .append(new JQTable.Node("去年同期", "y4"))
                        .append(new JQTable.Node("同比增幅", "y5")),
                    new JQTable.Node("季度累计完成", "jdljwc")
                        .append(new JQTable.Node("季度累计", "jl1"))
                        .append(new JQTable.Node("季度计划完成率", "jl2"))
                        .append(new JQTable.Node("去年同期值", "jl3"))
                        .append(new JQTable.Node("同比增幅", "jl4")),
                    new JQTable.Node("年度累计完成", "nd")
                        .append(new JQTable.Node("累计完成值", "n1"))
                        .append(new JQTable.Node("年度指标完成率", "n2"))
                        .append(new JQTable.Node("去年同期值", "n3"))
                        .append(new JQTable.Node("同比增幅", "n4")),
                    new JQTable.Node("下季度预计完成", "xjdyjwc")
                        .append(new JQTable.Node("下季度首月预计", "xjy1"))
                        .append(new JQTable.Node("下季度次月预计", "xjy2"))
                        .append(new JQTable.Node("下季度末月预计", "xjy3"))
                        .append(new JQTable.Node("季度预计合计", "xjy4"))
                        .append(new JQTable.Node("季度预计完成率", "xjy5"))
                        .append(new JQTable.Node("年度累计", "xjy6"))
                        .append(new JQTable.Node("年度累计完成率", "xjy7"))
                        .append(new JQTable.Node("去年同期", "xjy8"))
                        .append(new JQTable.Node("同比增幅", "xjy9"))
                ], gridName);
            }
        };
        return JQGridAssistantFactory;
    })();
    var SimpleView = (function () {
        function SimpleView() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("/BusinessManagement/ydzb/financial_zbhz_prediction_update.do");
            router.register(this);
        }
        SimpleView.prototype.getId = function () {
            return Util.FAMOUS_VIEW;
        };
        SimpleView.prototype.onEvent = function (e) {
            switch (e.id) {
                case Util.MSG_INIT:
                    this.init(e.data);
                    break;
                case Util.MSG_UPDATE:
                    this.updateUI();
                    break;
            }
        };
        SimpleView.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            var minDate = Util.addYear(opt.date, -3);
            minDate.month = 1;
            $("#grid-date").jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年",
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(minDate),
                maxDate: Util.date2Str(opt.date),
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top");
            $(window).resize(function () {
                _this.adjustSize();
            });
            $("#grid-update").on("click", function () {
                _this.updateUI();
            });
            $("#grid-export").on("click", function () {
                _this.exportExcel();
            });
            this.updateUI();
        };
        SimpleView.prototype.getDate = function () {
            var rq = $("#grid-date").val().replace("年", "-").replace("月", "-").replace("日", "-").split("-");
            return {
                year: rq[0] ? parseInt(rq[0]) : undefined,
                month: rq[1] ? parseInt(rq[1]) : undefined,
                day: rq[2] ? parseInt(rq[2]) : undefined
            };
        };
        SimpleView.prototype.updateUI = function () {
            var _this = this;
            this.mActualMonth = (parseInt($("#grid-season").val()) - 1) * 3 + parseInt($("#grid-season-month").val());
            this.mDataSet.get({ month: this.mActualMonth, year: parseInt($("#grid-date").val()) })
                .then(function (dataArray) {
                _this.mData = dataArray;
                _this.updateTable();
            });
        };
        SimpleView.prototype.exportExcel = function () {
            $("#exportExcel")[0].action = "/BusinessManagement/ydzb/gcy_zbhz_prediction_export.do?" + Util.Ajax.toUrlParam({ month: this.mActualMonth, year: parseInt($("#grid-date").val()) });
            $("#exportExcel")[0].submit();
        };
        SimpleView.prototype.adjustSize = function () {
            var jqgrid = this.jqgrid();
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
            var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            this.tableAssist.resizeHeight(maxTableBodyHeight);
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
        };
        SimpleView.prototype.jqgrid = function () {
            return $("#" + this.jqgridName());
        };
        SimpleView.prototype.jqgridName = function () {
            return this.mOpt.tableId + "_jqgrid_real";
        };
        SimpleView.prototype.formatData = function (data, precentList) {
            var row = [];
            for (var j = 0; j < this.mData.length; ++j) {
                row = [].concat(this.mData[j]);
                for (var i = 0; i < row.length; ++i) {
                    if (precentList.contains(i)) {
                        row[i] = Util.formatPercent(row[i]);
                    }
                    else {
                        row[i] = Util.formatCurrency(row[i]);
                    }
                }
                data[j] = data[j].concat(row);
            }
            return data;
        };
        SimpleView.prototype.formatFirstMonthData = function (data) {
            var precentList = new std.vector();
            precentList.push(FirstMonthZb.dyjhwcl);
            precentList.push(FirstMonthZb.dytbzf);
            precentList.push(FirstMonthZb.jdyjwcl);
            precentList.push(FirstMonthZb.jdtbzf);
            precentList.push(FirstMonthZb.ndzbwcl);
            precentList.push(FirstMonthZb.ndtbzf);
            return this.formatData(data, precentList);
        };
        SimpleView.prototype.formatSecondMonthData = function (data) {
            var precentList = new std.vector();
            precentList.push(SecondMonthZb.dyjhwcl);
            precentList.push(SecondMonthZb.dytbzf);
            precentList.push(SecondMonthZb.jdjhwcl);
            precentList.push(SecondMonthZb.jdyjtbzf);
            precentList.push(SecondMonthZb.jdyjwcl);
            precentList.push(SecondMonthZb.jdtbzf);
            precentList.push(SecondMonthZb.ndzbwcl);
            precentList.push(SecondMonthZb.ndtbzf);
            return this.formatData(data, precentList);
        };
        SimpleView.prototype.formatThirdMonthData = function (data) {
            var precentList = new std.vector();
            precentList.push(ThirdMonthZb.dyjhwcl);
            precentList.push(ThirdMonthZb.dytbzf);
            precentList.push(ThirdMonthZb.jdjhwcl);
            precentList.push(ThirdMonthZb.jdtbzf);
            precentList.push(ThirdMonthZb.ndzbwcl);
            precentList.push(ThirdMonthZb.ndtbzf);
            precentList.push(ThirdMonthZb.xjdyjwcl);
            precentList.push(ThirdMonthZb.xjdndljwcl);
            precentList.push(ThirdMonthZb.xjdtbzf);
            return this.formatData(data, precentList);
        };
        SimpleView.prototype.createJqassist = function () {
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='" + this.jqgridName() + "'></table>");
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), parseInt($("#grid-season-month").val()));
            this.tableAssist.mergeRow(0);
            for (var i = 0; i < 5; ++i) {
                this.tableAssist.setRowBgColor(i * 8 + 5, 183, 222, 232);
                this.tableAssist.setRowBgColor(i * 8 + 7, 183, 222, 232);
            }
            return this.tableAssist;
        };
        SimpleView.prototype.updateTable = function () {
            this.createJqassist();
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
                ["存 货", "集团合计"]];
            if (1 == parseInt($("#grid-season-month").val())) {
                data = this.formatFirstMonthData(data);
            }
            else if (2 == parseInt($("#grid-season-month").val())) {
                data = this.formatSecondMonthData(data);
            }
            else if (3 == parseInt($("#grid-season-month").val())) {
                data = this.formatThirdMonthData(data);
            }
            this.tableAssist.create({
                data: data,
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: $("#" + this.mOpt.tableId).width(),
                shrinkToFit: true,
                rowNum: 2000,
                autoScroll: true
            });
            this.adjustSize();
        };
        SimpleView.ins = new SimpleView();
        return SimpleView;
    })();
    financial_zbhz_prediciton.SimpleView = SimpleView;
})(financial_zbhz_prediciton || (financial_zbhz_prediciton = {}));
