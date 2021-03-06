/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../chgbdef.ts"/>
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var plugin;
(function (plugin) {
    plugin.chxzqk = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var chgb;
(function (chgb) {
    var chxzqk;
    (function (chxzqk) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = /** @class */ (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "chxzqk_yd", true, TextAlign.Center),
                    new JQTable.Node("月度", "chxzqk_ydtwo", true, TextAlign.Center),
                    new JQTable.Node("原材料", "chxzqk_ycl"),
                    new JQTable.Node("半成品", "chxzqk_bcp"),
                    new JQTable.Node("实际库存商品", "chxzqk_sjkcsp"),
                    new JQTable.Node("已发货未开票", "chxzqk_yfhwkp"),
                    new JQTable.Node("期货浮动盈亏(盈+，亏-)", "chxzqk_qhfdyk"),
                    new JQTable.Node("期货平仓盈亏(盈-，亏+)", "chxzqk_qhpc"),
                    new JQTable.Node("未发货已开票", "chxzqk_wfhykp"),
                    new JQTable.Node("其他", "chxzqk_qt"),
                    new JQTable.Node("合计", "chxzqk_hj")
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var ShowView = /** @class */ (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjax = new Util.Ajax("/BusinessManagement/chgb/chxzqk/update.do", false);
                return _this;
            }
            ShowView.prototype.getId = function () {
                return plugin.chxzqk;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, cpType) {
                return "/BusinessManagement/chgb/chxzqk/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: cpType
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.pluginUpdate = function (date, cpType) {
                var _this = this;
                this.mDt = date;
                this.mAjax.get({
                    date: date,
                    companyId: cpType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.$(this.option().ctarea).show();
                this.mFinalData = this.updateTable();
                this.updateEchart(this.mFinalData);
                this.adjustSize();
            };
            ShowView.prototype.updateEchart = function (data) {
                var title = "存货性质情况";
                var legend = [
                    "原材料",
                    "半成品",
                    "实际库存商品"
                ];
                var xData = [];
                for (var i = 0; i < data.length; ++i) {
                    xData.push(data[i][1]);
                }
                var tooltip = {
                    trigger: 'axis',
                };
                var yAxis = [
                    {
                        type: 'value',
                    }
                ];
                var series = [];
                for (var i = 0; i < legend.length; ++i) {
                    var rData = [];
                    for (var j = 0; j < data.length; ++j) {
                        rData.push(data[j][i + 2] == "--" ? 0 : data[j][i + 2]);
                    }
                    series.push({
                        name: legend[i],
                        type: 'line',
                        smooth: true,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: rData
                    });
                }
                var option = {
                    title: {
                        text: title
                    },
                    tooltip: tooltip,
                    legend: {
                        data: legend
                    },
                    toolbox: {
                        show: true,
                    },
                    calculable: false,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: xData.length < 1 ? [0] : xData
                        }
                    ],
                    yAxis: yAxis,
                    series: series
                };
                echarts.init(this.$(this.option().ct)[0]).setOption(option);
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "存货性质情况");
            };
            ShowView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                //this.tableAssist.resizeHeight(maxTableBodyHeight);
                //if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                //    jqgrid.setGridWidth(this.jqgridHost().width());
                //}
                this.$(this.option().ct).css("height", "300px");
                this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                this.updateEchart(this.mFinalData);
            };
            ShowView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
                this.tableAssist.mergeRow(0);
                this.tableAssist.mergeTitle();
                return this.tableAssist;
            };
            ShowView.prototype.updateTable = function () {
                this.createJqassist();
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                var data = [];
                for (var i = month + 1; i <= 12; ++i) {
                    data.push(["上年度", i + "月"].concat(this.mData[i - month - 1]));
                }
                for (var i = 1; i <= month; ++i) {
                    data.push(["本年度", i + "月"].concat(this.mData[12 - month + i - 1]));
                }
                this.tableAssist.create({
                    data: data,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 2000,
                    autoScroll: true
                });
                return data;
            };
            ShowView.ins = new ShowView();
            return ShowView;
        }(framework.basic.ShowPluginView));
    })(chxzqk = chgb.chxzqk || (chgb.chxzqk = {}));
})(chgb || (chgb = {}));
