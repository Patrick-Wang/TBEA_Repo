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
    var lzbb;
    (function (lzbb) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("上海鞍钢<br/>（1.0mmST12）<br/>（元/吨）", "shan"),
                    new JQTable.Node("南昌武钢<br/>（1.0mmST12）<br/>（元/吨）", "ncwg"),
                    new JQTable.Node("沈阳鞍钢<br/>（1.0mmST12）<br/>（元/吨）", "syag"),
                    new JQTable.Node("西安鞍钢<br/>（1.0mmST12）<br/>（元/吨）", "xaag"),
                    new JQTable.Node("乌鲁木齐八钢<br/>（1.0mmSPCC）<br/>（元/吨）", "wlmqbg"),
                    new JQTable.Node("市场均价<br/>（元/吨）", "scjj"),
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var LzbbView = (function (_super) {
            __extends(LzbbView, _super);
            function LzbbView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("update.do?type=" + jcycljg.JcycljgType.LZBB, false);
            }
            LzbbView.newInstance = function () {
                return new LzbbView();
            };
            LzbbView.prototype.option = function () {
                return this.mOpt;
            };
            LzbbView.prototype.pluginUpdate = function (start, end) {
                var _this = this;
                this.mAjax.get({
                    start: start,
                    end: end
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            LzbbView.prototype.refresh = function () {
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
            LzbbView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("冷轧薄板", this);
            };
            LzbbView.prototype.updateChart = function () {
                var _this = this;
                var items = ["上海鞍钢", "南昌武钢", "沈阳鞍钢", "西安鞍钢", "乌鲁木齐八钢", "市场均价"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][1 + j]);
                    }
                });
                this.updateEchart("废钢材价格趋势（元/吨）", this.option().ct, items, data);
            };
            LzbbView.prototype.getDateType = function () {
                return jcycljg.DateType.DAY;
            };
            LzbbView.prototype.updateEchart = function (title, echart, legend, data) {
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
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: series
                };
                echarts.init(this.$(echart)[0]).setOption(option);
            };
            LzbbView.prototype.updateTable = function () {
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
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 20,
                    data: tableAssist.getData(this.mData),
                    datatype: "local",
                    viewrecords: true,
                    pager: name + "pager"
                }));
            };
            return LzbbView;
        }(jcycljg.BasePluginView));
        lzbb.pluginView = LzbbView.newInstance();
    })(lzbb = jcycljg.lzbb || (jcycljg.lzbb = {}));
})(jcycljg || (jcycljg = {}));
