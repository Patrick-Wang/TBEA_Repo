/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var hzb_zbhz_prediciton;
(function (hzb_zbhz_prediciton) {
    var FirstMonthZb;
    (function (FirstMonthZb) {
        FirstMonthZb[FirstMonthZb["zb"] = 0] = "zb";
        FirstMonthZb[FirstMonthZb["ndjh"] = 1] = "ndjh";
        FirstMonthZb[FirstMonthZb["bjdjh"] = 2] = "bjdjh";
        FirstMonthZb[FirstMonthZb["byjhz"] = 3] = "byjhz";
        FirstMonthZb[FirstMonthZb["dyyjz"] = 4] = "dyyjz";
        FirstMonthZb[FirstMonthZb["dyjhwcl"] = 5] = "dyjhwcl";
        FirstMonthZb[FirstMonthZb["dyqntq"] = 6] = "dyqntq";
        FirstMonthZb[FirstMonthZb["dytbzf"] = 7] = "dytbzf";
        FirstMonthZb[FirstMonthZb["cyyj"] = 8] = "cyyj";
        FirstMonthZb[FirstMonthZb["myyj"] = 9] = "myyj";
        FirstMonthZb[FirstMonthZb["jdyjhj"] = 10] = "jdyjhj";
        FirstMonthZb[FirstMonthZb["jdyjwcl"] = 11] = "jdyjwcl";
        FirstMonthZb[FirstMonthZb["jdqntq"] = 12] = "jdqntq";
        FirstMonthZb[FirstMonthZb["jdtbzf"] = 13] = "jdtbzf";
        FirstMonthZb[FirstMonthZb["ndljwcz"] = 14] = "ndljwcz";
        FirstMonthZb[FirstMonthZb["ndzbwcl"] = 15] = "ndzbwcl";
        FirstMonthZb[FirstMonthZb["ndqntqz"] = 16] = "ndqntqz";
        FirstMonthZb[FirstMonthZb["ndtbzf"] = 17] = "ndtbzf";
    })(FirstMonthZb || (FirstMonthZb = {}));
    var SecondMonthZb;
    (function (SecondMonthZb) {
        SecondMonthZb[SecondMonthZb["zb"] = 0] = "zb";
        SecondMonthZb[SecondMonthZb["ndjh"] = 1] = "ndjh";
        SecondMonthZb[SecondMonthZb["jdjh"] = 2] = "jdjh";
        SecondMonthZb[SecondMonthZb["byjhz"] = 3] = "byjhz";
        SecondMonthZb[SecondMonthZb["dyyjz"] = 4] = "dyyjz";
        SecondMonthZb[SecondMonthZb["dyjhwcl"] = 5] = "dyjhwcl";
        SecondMonthZb[SecondMonthZb["dyqntq"] = 6] = "dyqntq";
        SecondMonthZb[SecondMonthZb["dytbzf"] = 7] = "dytbzf";
        SecondMonthZb[SecondMonthZb["jdlj"] = 8] = "jdlj";
        SecondMonthZb[SecondMonthZb["jdjhwcl"] = 9] = "jdjhwcl";
        SecondMonthZb[SecondMonthZb["jdqntqz"] = 10] = "jdqntqz";
        SecondMonthZb[SecondMonthZb["jdtbzf"] = 11] = "jdtbzf";
        SecondMonthZb[SecondMonthZb["jdmyyj"] = 12] = "jdmyyj";
        SecondMonthZb[SecondMonthZb["jdyjhj"] = 13] = "jdyjhj";
        SecondMonthZb[SecondMonthZb["jdyjwcl"] = 14] = "jdyjwcl";
        SecondMonthZb[SecondMonthZb["jdyjqntq"] = 15] = "jdyjqntq";
        SecondMonthZb[SecondMonthZb["jdyjtbzf"] = 16] = "jdyjtbzf";
        SecondMonthZb[SecondMonthZb["ndljwcz"] = 17] = "ndljwcz";
        SecondMonthZb[SecondMonthZb["ndzbwcl"] = 18] = "ndzbwcl";
        SecondMonthZb[SecondMonthZb["ndqntqz"] = 19] = "ndqntqz";
        SecondMonthZb[SecondMonthZb["ndtbzf"] = 20] = "ndtbzf";
    })(SecondMonthZb || (SecondMonthZb = {}));
    var ThirdMonthZb;
    (function (ThirdMonthZb) {
        ThirdMonthZb[ThirdMonthZb["zb"] = 0] = "zb";
        ThirdMonthZb[ThirdMonthZb["ndjh"] = 1] = "ndjh";
        ThirdMonthZb[ThirdMonthZb["bjdjh"] = 2] = "bjdjh";
        ThirdMonthZb[ThirdMonthZb["xjdjh"] = 3] = "xjdjh";
        ThirdMonthZb[ThirdMonthZb["dyjhz"] = 4] = "dyjhz";
        ThirdMonthZb[ThirdMonthZb["dyyjz"] = 5] = "dyyjz";
        ThirdMonthZb[ThirdMonthZb["dyjhwcl"] = 6] = "dyjhwcl";
        ThirdMonthZb[ThirdMonthZb["dyqntq"] = 7] = "dyqntq";
        ThirdMonthZb[ThirdMonthZb["dytbzf"] = 8] = "dytbzf";
        ThirdMonthZb[ThirdMonthZb["jdlj"] = 9] = "jdlj";
        ThirdMonthZb[ThirdMonthZb["jdjhwcl"] = 10] = "jdjhwcl";
        ThirdMonthZb[ThirdMonthZb["jdqntqz"] = 11] = "jdqntqz";
        ThirdMonthZb[ThirdMonthZb["jdtbzf"] = 12] = "jdtbzf";
        ThirdMonthZb[ThirdMonthZb["ndljwcz"] = 13] = "ndljwcz";
        ThirdMonthZb[ThirdMonthZb["ndzbwcl"] = 14] = "ndzbwcl";
        ThirdMonthZb[ThirdMonthZb["ndqntqz"] = 15] = "ndqntqz";
        ThirdMonthZb[ThirdMonthZb["ndtbzf"] = 16] = "ndtbzf";
        ThirdMonthZb[ThirdMonthZb["xjdsyyj"] = 17] = "xjdsyyj";
        ThirdMonthZb[ThirdMonthZb["xjdcyyj"] = 18] = "xjdcyyj";
        ThirdMonthZb[ThirdMonthZb["xjdmyyj"] = 19] = "xjdmyyj";
        ThirdMonthZb[ThirdMonthZb["xjdyjhj"] = 20] = "xjdyjhj";
        ThirdMonthZb[ThirdMonthZb["xjdyjwcl"] = 21] = "xjdyjwcl";
        ThirdMonthZb[ThirdMonthZb["xjdndlj"] = 22] = "xjdndlj";
        ThirdMonthZb[ThirdMonthZb["xjdndljwcl"] = 23] = "xjdndljwcl";
        ThirdMonthZb[ThirdMonthZb["xjdqntq"] = 24] = "xjdqntq";
        ThirdMonthZb[ThirdMonthZb["xjdtbzf"] = 25] = "xjdtbzf";
    })(ThirdMonthZb || (ThirdMonthZb = {}));
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, gridStyle) {
            if (1 == gridStyle) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
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
    var View = (function () {
        function View() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("hzb_zbhz_prediction_update.do");
            this.mJydwDataSet = new Util.Ajax("hzb_zbhz_prediction_jydw_compute.do", false);
            this.mXmgsDataSet = new Util.Ajax("hzb_zbhz_prediction_xmgs_compute.do", false);
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
            $('h1').text(this.mYear + "年" + "季度指标预测完成情况");
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
        View.prototype.exportExcelJydw = function () {
            this.mActualMonth = (this.mSeason - 1) * 3 + this.mDelegateMonth;
            $("#loadingText").text("经营单位整体指标预测完成情况导出中。。。");
            this.mJydwDataSet.get({ month: this.mActualMonth, year: this.mYear }).then(function (tmStamp) {
                $("#exportJydw")[0].action = "general_export.do?" + Util.Ajax.toUrlParam({ timeStamp: tmStamp.timeStamp });
                $("#exportJydw")[0].submit();
            });
        };
        View.prototype.exportExcelXmgs = function () {
            this.mActualMonth = (this.mSeason - 1) * 3 + this.mDelegateMonth;
            $("#loadingText").text("项目公司整体指标预测完成情况导出中。。。");
            this.mXmgsDataSet.get({ month: this.mActualMonth, year: this.mYear }).then(function (tmStamp) {
                $("#exportxmgs")[0].action = "general_export.do?" + Util.Ajax.toUrlParam({ timeStamp: tmStamp.timeStamp });
                $("#exportxmgs")[0].submit();
            });
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mActualMonth = (this.mSeason - 1) * 3 + this.mDelegateMonth;
            this.mDataSet.get({ month: this.mActualMonth, year: this.mYear })
                .then(function (dataArray) {
                _this.mData = dataArray;
                $('h1').text(_this.mYear + "年" + "季度指标预测完成情况");
                //document.title = this.mYear + "年" + this.mMonth + "月 指标汇总";
                _this.updateTable();
            });
        };
        View.prototype.formatFirstMonthData = function (outputData) {
            var precentList = new std.vector();
            precentList.push(FirstMonthZb.dyjhwcl);
            precentList.push(FirstMonthZb.dytbzf);
            precentList.push(FirstMonthZb.jdyjwcl);
            precentList.push(FirstMonthZb.jdtbzf);
            precentList.push(FirstMonthZb.ndzbwcl);
            precentList.push(FirstMonthZb.ndtbzf);
            return Util.formatData(outputData, this.mData, precentList, []);
        };
        View.prototype.formatSecondMonthData = function (outputData) {
            var precentList = new std.vector();
            precentList.push(SecondMonthZb.dyjhwcl);
            precentList.push(SecondMonthZb.dytbzf);
            precentList.push(SecondMonthZb.jdjhwcl);
            precentList.push(SecondMonthZb.jdyjtbzf);
            precentList.push(SecondMonthZb.jdyjwcl);
            precentList.push(SecondMonthZb.jdtbzf);
            precentList.push(SecondMonthZb.ndzbwcl);
            precentList.push(SecondMonthZb.ndtbzf);
            return Util.formatData(outputData, this.mData, precentList, []);
        };
        View.prototype.formatThirdMonthData = function (outputData) {
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
            return Util.formatData(outputData, this.mData, precentList, []);
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name, this.mDelegateMonth);
            var outputData = [];
            if (1 == this.mDelegateMonth) {
                this.formatFirstMonthData(outputData);
            }
            else if (2 == this.mDelegateMonth) {
                this.formatSecondMonthData(outputData);
            }
            else if (3 == this.mDelegateMonth) {
                this.formatThirdMonthData(outputData);
            }
            for (var i = 0; i < outputData.length; ++i) {
                if (outputData[i][0].lastIndexOf("计") >= 0) {
                    tableAssist.setRowBgColor(i, 183, 222, 232);
                }
            }
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: tableAssist.getData(outputData),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
                //                    cellsubmit: 'clientArray',
                //                    cellEdit: true,
                height: '100%',
                width: 1330,
                shrinkToFit: true,
                rowNum: 100,
                autoScroll: true
            }));
            $("#exportJydw").css('display', 'block');
            $("#exportxmgs").css('display', 'block');
        };
        return View;
    })();
    hzb_zbhz_prediciton.View = View;
})(hzb_zbhz_prediciton || (hzb_zbhz_prediciton = {}));
