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
    var dmdjyx;
    (function (dmdjyx) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq"),
                    new JQTable.Node("上海石化（DJ210）<br/>元/吨", "a1"),
                    new JQTable.Node("燕山石化（LD100BW）<br/>元/吨", "a2")
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var DmdjyxView = (function (_super) {
            __extends(DmdjyxView, _super);
            function DmdjyxView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("jcycljg/update.do?type=" + jcycljg.JcycljgType.DMDJYX, false);
            }
            DmdjyxView.newInstance = function () {
                return new DmdjyxView();
            };
            DmdjyxView.prototype.option = function () {
                return this.mOpt;
            };
            DmdjyxView.prototype.pluginUpdate = function (start, end) {
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
            DmdjyxView.prototype.refresh = function () {
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
            DmdjyxView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("低密度聚乙烯（LDPE）", this);
            };
            DmdjyxView.prototype.updateChart = function () {
                var _this = this;
                var items = ["上海石化（DJ210）", "燕山石化（LD100BW）"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][1 + j]);
                    }
                });
                this.updateEchart("低密度聚乙烯价格趋势（元/吨）", this.option().ct, items, data);
            };
            DmdjyxView.prototype.getDateType = function () {
                return jcycljg.DateType.DAY;
            };
            DmdjyxView.prototype.updateEchart = function (title, echart, legend, data) {
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
                        data: data[i].length < 1 ? [0] : data[i]
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
            DmdjyxView.prototype.updateTable = function () {
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
            return DmdjyxView;
        })(jcycljg.BasePluginView);
        dmdjyx.pluginView = DmdjyxView.newInstance();
    })(dmdjyx = jcycljg.dmdjyx || (jcycljg.dmdjyx = {}));
})(jcycljg || (jcycljg = {}));
