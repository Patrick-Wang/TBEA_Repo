/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var gdw_zbhz_prediciton;
(function (gdw_zbhz_prediciton) {
    var FirstMonthZb;
    (function (FirstMonthZb) {
        FirstMonthZb[FirstMonthZb["ndjh"] = 0] = "ndjh";
        FirstMonthZb[FirstMonthZb["bjdjh"] = 1] = "bjdjh";
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
            this.mDataSet = new Util.Ajax("gdw_zbhz_prediction_update.do");
        }
        View.newInstance = function () {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        };
        View.prototype.init = function (tableId, year, zbId) {
            this.mYear = year;
            this.mTableId = tableId;
            //this.mZB = zbId;
            //this.mZBName = zbName;
            //$('h1').text(this.mYear + "年"  + "季度" + this.mZBName + "预测完成情况");
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
        View.prototype.onIndexSelected = function () {
            this.mZB = $("#indextype").val();
            this.mZBName = $("#indextype  option:selected").text();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mActualMonth = (this.mSeason - 1) * 3 + this.mDelegateMonth;
            this.onIndexSelected();
            this.mDataSet.get({ month: this.mActualMonth, year: this.mYear, zb: this.mZB })
                .then(function (dataArray) {
                _this.mData = dataArray;
                $('h1').text(_this.mYear + "年" + "季度" + _this.mZBName + "预测完成情况");
                document.title = _this.mYear + "年" + "季度" + _this.mZBName + "预测完成情况";
                _this.updateTable();
            });
        };
        View.prototype.exportExcel = function () {
            $("#export")[0].action = "gdw_zbhz_prediction_export.do?" + Util.Ajax.toUrlParam({ month: this.mActualMonth, year: this.mYear, top5index: this.mZB, zbName: this.mZBName });
            $("#export")[0].submit();
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
            precentList.push(FirstMonthZb.dyjhwcl);
            precentList.push(FirstMonthZb.dytbzf);
            precentList.push(FirstMonthZb.jdyjwcl);
            precentList.push(FirstMonthZb.jdtbzf);
            precentList.push(FirstMonthZb.ndzbwcl);
            precentList.push(FirstMonthZb.ndtbzf);
            return this.formatData(data, precentList);
        };
        View.prototype.formatSecondMonthData = function (data) {
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
        View.prototype.formatThirdMonthData = function (data) {
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
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name, this.mDelegateMonth);
            var data = [
                ["沈变公司"],
                ["衡变公司"],
                ["新变厂"],
                ["鲁缆公司"],
                ["新缆厂"],
                ["德缆公司"],
                ["输变电小计"],
                ["新特能源公司"],
                ["新能源"],
                ["新能源小计"],
                ["天池能源"],
                ["能动公司"],
                ["能源小计"],
                ["进出口公司"],
                ["国际工程公司"],
                ["工程小计"],
                ["股份公司小计"],
                ["众和公司"],
                ["集团合计"]
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
            for (var i = 0; i < data.length; ++i) {
                if (data[i][0].lastIndexOf("计") >= 0) {
                    tableAssist.setRowBgColor(i, 183, 222, 232);
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
                height: '100%',
                width: 1330,
                shrinkToFit: true,
                rowNum: 100,
                autoScroll: true
            }));
            $("#export").css('display', 'block');
        };
        return View;
    })();
    gdw_zbhz_prediciton.View = View;
})(gdw_zbhz_prediciton || (gdw_zbhz_prediciton = {}));
