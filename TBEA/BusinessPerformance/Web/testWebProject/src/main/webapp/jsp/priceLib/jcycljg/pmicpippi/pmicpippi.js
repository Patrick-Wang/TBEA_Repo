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
    var pmicpippi;
    (function (pmicpippi) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq"),
                    new JQTable.Node("PMI（制造业采购经理指数）", "a1"),
                    new JQTable.Node("CPI（居民消费价格指数(上年同月=100)）", "a2"),
                    new JQTable.Node("PPI（生产价格指数（(上年同月=100)）", "a3")
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var PmiCpiPpiView = (function (_super) {
            __extends(PmiCpiPpiView, _super);
            function PmiCpiPpiView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("jcycljg/update.do?type=" + jcycljg.JcycljgType.PMICPIPPI, false);
            }
            PmiCpiPpiView.newInstance = function () {
                return new PmiCpiPpiView();
            };
            PmiCpiPpiView.prototype.option = function () {
                return this.mOpt;
            };
            PmiCpiPpiView.prototype.pluginUpdate = function (start, end) {
                var _this = this;
                this.mAjax.get({
                    start: start,
                    end: end
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.updateTable();
                    _this.updatePmiChart();
                    _this.updateCpiChart();
                    _this.updatePpiChart();
                });
            };
            PmiCpiPpiView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("PMI、CPI、PPI", this);
            };
            PmiCpiPpiView.prototype.updatePmiChart = function () {
                var _this = this;
                var items = ["PMI"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][1 + j]);
                    }
                });
                this.updateEchart("PMI（制造业采购经理指数）", this.option().pmi, items, data);
            };
            PmiCpiPpiView.prototype.updateCpiChart = function () {
                var _this = this;
                var items = ["CPI"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][5 + j]);
                    }
                });
                this.updateEchart("CPI（居民消费价格指数(上年同月=100)", this.option().cpi, items, data);
            };
            PmiCpiPpiView.prototype.updatePpiChart = function () {
                var _this = this;
                var items = ["PPI"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][5 + j]);
                    }
                });
                this.updateEchart("PPI（生产价格指数（(上年同月=100)）", this.option().ppi, items, data);
            };
            PmiCpiPpiView.prototype.getDateType = function () {
                return jcycljg.DateType.DAY;
            };
            PmiCpiPpiView.prototype.updateEchart = function (title, echart, legend, data) {
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
            PmiCpiPpiView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                this.$(name).jqGrid(tableAssist.decorate({
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 100,
                    data: tableAssist.getData(this.mData),
                    datatype: "local"
                }));
            };
            return PmiCpiPpiView;
        })(jcycljg.BasePluginView);
        pmicpippi.pluginView = PmiCpiPpiView.newInstance();
    })(pmicpippi = jcycljg.pmicpippi || (jcycljg.pmicpippi = {}));
})(jcycljg || (jcycljg = {}));
