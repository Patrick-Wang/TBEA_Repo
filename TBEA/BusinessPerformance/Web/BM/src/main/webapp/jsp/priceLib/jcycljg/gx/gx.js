/// <reference path="../../../jqgrid/jqassist.ts" />
/// <reference path="../../../util.ts" />
/// <reference path="../../../dateSelector.ts" />
/// <reference path="../jcycljgdef.ts" />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var jcycljg;
(function (jcycljg) {
    var gx;
    (function (gx) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("上海沙钢<br/>（元/吨）", "a1"),
                    new JQTable.Node("南京济源<br/>（元/吨）", "a2"),
                    new JQTable.Node("郑州济源<br/>（元/吨）", "a3"),
                    new JQTable.Node("天津唐钢<br/>（元/吨）", "a4"),
                    new JQTable.Node("成都酒钢<br/>（元/吨）", "a5"),
                    new JQTable.Node("平均价<br/>（元/吨）", "a6")
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var GxView = (function (_super) {
            __extends(GxView, _super);
            function GxView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("update.do?type=" + jcycljg.JcycljgType.GX, false);
            }
            GxView.newInstance = function () {
                return new GxView();
            };
            GxView.prototype.option = function () {
                return this.mOpt;
            };
            GxView.prototype.pluginUpdate = function (start, end) {
                var _this = this;
                this.mAjax.get({
                    start: start,
                    end: end
                })
                    .then(function (jsonData) {
                    _this.mData = _this.formateData(jsonData);
                    _this.refresh();
                });
            };
            GxView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                if (this.mDispType == jcycljg.DisplayType.CHART) {
                    this.updateChart();
                }
                else {
                    this.updateTable();
                }
            };
            GxView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("高线（45-70# Φ6.5）", this);
            };
            GxView.prototype.updateChart = function () {
                var _this = this;
                var items = ["上海沙钢", "南京济源", "郑州济源", "天津唐钢", "成都酒钢", "平均价"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][1 + j]);
                    }
                });
                this.updateEchart("高线价格趋势（元/吨）", this.option().ct, items, data);
            };
            GxView.prototype.getDateType = function () {
                return jcycljg.DateType.DAY;
            };
            GxView.prototype.formateData = function (data) {
                for (var i = 0; i < data.length; ++i) {
                    for (var j = 0; j < data[i].length; ++j) {
                        if (data[i][j] == null) {
                            data[i][j] = '--';
                        }
                    }
                }
                return data;
            };
            GxView.prototype.updateEchart = function (title, echart, legend, data) {
                var _this = this;
                var xData = [];
                $(this.mData).each(function (i) {
                    xData.push(_this.mData[i][0]);
                });
                var series = [];
                for (var i in legend) {
                    series.push({
                        name: legend[i],
                        type: 'line',
                        smooth: true,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: data[i].length < 1 ? [0] : Util.replaceNull(data[i])
                    });
                }
                var option = {
                    title: {
                        text: title
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: legend
                    },
                    toolbox: {
                        show: true
                    },
                    calculable: false,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: xData.length < 1 ? [0] : xData
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: series
                };
                echarts.init(this.$(echart)[0]).setOption(option);
            };
            GxView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + name + "pager" + "'></div>");
                this.$(name).jqGrid(tableAssist.decorate({
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 800,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 20,
                    data: tableAssist.getData(this.mData),
                    datatype: "local",
                    viewrecords: true,
                    pager: name + "pager"
                }));
            };
            return GxView;
        })(jcycljg.BasePluginView);
        gx.pluginView = GxView.newInstance();
    })(gx = jcycljg.gx || (jcycljg.gx = {}));
})(jcycljg || (jcycljg = {}));
