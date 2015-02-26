/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var hzb_zbhz_prediciton;
(function (hzb_zbhz_prediciton) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("年度计划", "ndjh"),
                new JQTable.Node("季度计划", "jdjh"),
                new JQTable.Node("当月完成", "dywc").append(new JQTable.Node("本月计划值", "y1")).append(new JQTable.Node("当月预计值", "y2")).append(new JQTable.Node("计划完成率", "y3")).append(new JQTable.Node("去年同期", "y4")).append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度预计完成", "jdyjwc").append(new JQTable.Node("次月预计", "j1")).append(new JQTable.Node("末月预计", "j2")).append(new JQTable.Node("季度预计合计", "j3")).append(new JQTable.Node("季度预计完成率", "j4")).append(new JQTable.Node("去年同期", "j5")).append(new JQTable.Node("同比增幅", "j6")),
                new JQTable.Node("年度累计完成", "nd").append(new JQTable.Node("累计完成值", "n1")).append(new JQTable.Node("年度指标完成率", "n2")).append(new JQTable.Node("去年同期值", "n3")).append(new JQTable.Node("同比增幅", "n4")),
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
        View.prototype.init = function (tableId, year) {
            this.mYear = year;
            this.mTableId = tableId;
            $('h1').text(this.mYear + "年" + "季度指标预测汇总");
            //this.updateTable();
            //this.updateUI();
        };
        View.prototype.onYearSelected = function (year) {
            this.mYear = year;
        };
        View.prototype.onSeasonSelected = function (season) {
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
                //$('h1').text(this.mYear + "年" + this.mMonth + "季度" + "月 指标汇总");
                //document.title = this.mYear + "年" + this.mMonth + "月 指标汇总";
                _this.updateTable();
            });
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name);
            var data = [];
            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (this.mData[i] instanceof Array) {
                    //row = [].concat(this.mData[i]);
                    //                    for (var col in row) {
                    //                        if (col != '2' && col != '4' && col != '6' && col != '8' && col != '10') {
                    //                            row[col] = Util.formatCurrency(row[col]);
                    //                        }
                    //                    }
                    data.push(row);
                }
            }
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: tableAssist.getData(this.mData),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
                //                    cellsubmit: 'clientArray',
                //                    cellEdit: true,
                height: '100%',
                width: 1200,
                shrinkToFit: true,
                rowNum: 100,
                autoScroll: true
            }));
        };
        return View;
    })();
    hzb_zbhz_prediciton.View = View;
})(hzb_zbhz_prediciton || (hzb_zbhz_prediciton = {}));
