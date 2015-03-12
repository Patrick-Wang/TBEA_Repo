/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var financial_zbhz_prediciton;
(function (financial_zbhz_prediciton) {
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
                    new JQTable.Node("当月完成", "dywc").append(new JQTable.Node("本月计划值", "y1")).append(new JQTable.Node("当月预计值", "y2")).append(new JQTable.Node("计划完成率", "y3")).append(new JQTable.Node("去年同期", "y4")).append(new JQTable.Node("同比增幅", "y5")),
                    new JQTable.Node("季度预计完成", "jdyjwc").append(new JQTable.Node("次月预计", "j1")).append(new JQTable.Node("末月预计", "j2")).append(new JQTable.Node("季度预计合计", "j3")).append(new JQTable.Node("季度预计完成率", "j4")).append(new JQTable.Node("去年同期", "j5")).append(new JQTable.Node("同比增幅", "j6")),
                    new JQTable.Node("年度累计完成", "nd").append(new JQTable.Node("累计完成值", "n1")).append(new JQTable.Node("年度指标完成率", "n2")).append(new JQTable.Node("去年同期值", "n3")).append(new JQTable.Node("同比增幅", "n4"))
                ], gridName);
            }
            if (2 == gridStyle) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                    new JQTable.Node("产业", "cy"),
                    new JQTable.Node("年度计划", "ndjh"),
                    new JQTable.Node("本季度计划", "jdjh"),
                    new JQTable.Node("当月完成", "dywc").append(new JQTable.Node("本月计划值", "y1")).append(new JQTable.Node("当月预计值", "y2")).append(new JQTable.Node("计划完成率", "y3")).append(new JQTable.Node("去年同期", "y4")).append(new JQTable.Node("同比增幅", "y5")),
                    new JQTable.Node("季度累计完成", "jdljwc").append(new JQTable.Node("季度累计", "jl1")).append(new JQTable.Node("季度计划完成率", "jl2")).append(new JQTable.Node("去年同期值", "jl3")).append(new JQTable.Node("同比增幅", "jl4")),
                    new JQTable.Node("季度预计完成", "jdyjwc").append(new JQTable.Node("末月预计", "jy1")).append(new JQTable.Node("季度预计合计", "jy2")).append(new JQTable.Node("季度预计完成率", "jy3")).append(new JQTable.Node("去年同期", "jy4")).append(new JQTable.Node("同比增幅", "jy5")),
                    new JQTable.Node("年度累计完成", "nd").append(new JQTable.Node("累计完成值", "n1")).append(new JQTable.Node("年度指标完成率", "n2")).append(new JQTable.Node("去年同期值", "n3")).append(new JQTable.Node("同比增幅", "n4"))
                ], gridName);
            }
            if (3 == gridStyle) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                    new JQTable.Node("产业", "cy"),
                    new JQTable.Node("年度计划", "ndjh"),
                    new JQTable.Node("本季度计划", "jdjh"),
                    new JQTable.Node("下季度计划", "xjdjh"),
                    new JQTable.Node("当月完成", "dywc").append(new JQTable.Node("本月计划值", "y1")).append(new JQTable.Node("当月预计值", "y2")).append(new JQTable.Node("计划完成率", "y3")).append(new JQTable.Node("去年同期", "y4")).append(new JQTable.Node("同比增幅", "y5")),
                    new JQTable.Node("季度累计完成", "jdljwc").append(new JQTable.Node("季度累计", "jl1")).append(new JQTable.Node("季度计划完成率", "jl2")).append(new JQTable.Node("去年同期值", "jl3")).append(new JQTable.Node("同比增幅", "jl4")),
                    new JQTable.Node("年度累计完成", "nd").append(new JQTable.Node("累计完成值", "n1")).append(new JQTable.Node("年度指标完成率", "n2")).append(new JQTable.Node("去年同期值", "n3")).append(new JQTable.Node("同比增幅", "n4")),
                    new JQTable.Node("下季度预计完成", "xjdyjwc").append(new JQTable.Node("下季度首月预计", "xjy1")).append(new JQTable.Node("下季度次月预计", "xjy2")).append(new JQTable.Node("下季度末月预计", "xjy3")).append(new JQTable.Node("季度预计合计", "xjy4")).append(new JQTable.Node("季度预计完成率", "xjy5")).append(new JQTable.Node("年度累计", "xjy6")).append(new JQTable.Node("年度累计完成率", "xjy7")).append(new JQTable.Node("去年同期", "xjy8")).append(new JQTable.Node("同比增幅", "xjy9"))
                ], gridName);
            }
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("financial_zbhz_prediction_update.do");
        }
        View.newInstance = function () {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        };
        View.prototype.init = function (tableId, year) {
            this.mYear = year;
            this.mTableId = tableId;
            $('h1').text(this.mYear + "年" + "季度五大经营指标预测完成情况");
            //this.updateTable();
            //this.updateUI();
        };
        View.prototype.onYearSelected = function (year) {
            this.mYear = year;
        };
        View.prototype.onSeasonChange = function (season) {
            this.mSeason = parseInt(season);
        };
        View.prototype.onMonthDelegateSelected = function (month) {
            this.mDelegateMonth = parseInt(month);
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mActualMonth = (this.mSeason - 1) * 3 + this.mDelegateMonth;
            this.mDataSet.get({ month: this.mActualMonth, year: this.mYear }).then(function (dataArray) {
                _this.mData = dataArray;
                $('h1').text(_this.mYear + "年" + "季度五大经营指标预测完成情况");
                document.title = _this.mYear + "年" + "季度五大经营指标预测完成情况";
                _this.updateTable();
            });
        };
        View.prototype.formatData = function (data, precentList) {
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
        View.prototype.formatFirstMonthData = function (data) {
            var precentList = new std.vector();
            precentList.push(4 /* dyjhwcl */);
            precentList.push(6 /* dytbzf */);
            precentList.push(10 /* jdyjwcl */);
            precentList.push(12 /* jdtbzf */);
            precentList.push(14 /* ndzbwcl */);
            precentList.push(16 /* ndtbzf */);
            return this.formatData(data, precentList);
        };
        View.prototype.formatSecondMonthData = function (data) {
            var precentList = new std.vector();
            precentList.push(4 /* dyjhwcl */);
            precentList.push(6 /* dytbzf */);
            precentList.push(8 /* jdjhwcl */);
            precentList.push(15 /* jdyjtbzf */);
            precentList.push(13 /* jdyjwcl */);
            precentList.push(10 /* jdtbzf */);
            precentList.push(17 /* ndzbwcl */);
            precentList.push(19 /* ndtbzf */);
            return this.formatData(data, precentList);
        };
        View.prototype.formatThirdMonthData = function (data) {
            var precentList = new std.vector();
            precentList.push(5 /* dyjhwcl */);
            precentList.push(7 /* dytbzf */);
            precentList.push(9 /* jdjhwcl */);
            precentList.push(11 /* jdtbzf */);
            precentList.push(13 /* ndzbwcl */);
            precentList.push(15 /* ndtbzf */);
            precentList.push(20 /* xjdyjwcl */);
            precentList.push(22 /* xjdndljwcl */);
            precentList.push(24 /* xjdtbzf */);
            return this.formatData(data, precentList);
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name, this.mDelegateMonth);
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
            if (1 == this.mDelegateMonth) {
                data = this.formatFirstMonthData(data);
            }
            else if (2 == this.mDelegateMonth) {
                data = this.formatSecondMonthData(data);
            }
            else if (3 == this.mDelegateMonth) {
                data = this.formatThirdMonthData(data);
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
                height: 550,
                width: 1300,
                shrinkToFit: true,
                rowNum: 200,
                autoScroll: true
            }));
        };
        return View;
    })();
    financial_zbhz_prediciton.View = View;
})(financial_zbhz_prediciton || (financial_zbhz_prediciton = {}));
