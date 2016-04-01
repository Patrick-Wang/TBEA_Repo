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
    var jt;
    (function (jt) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("山西吕梁<br/>（元/吨）", "sxll"),
                    new JQTable.Node("河北邢台<br/>（元/吨）", "hbxt"),
                    new JQTable.Node("山东青岛<br/>（元/吨）", "sdqd"),
                    new JQTable.Node("河南济源<br/>（元/吨）", "hnjy"),
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var JtView = (function (_super) {
            __extends(JtView, _super);
            function JtView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("jcycljg/update.do?type=" + jcycljg.JcycljgType.JT, false);
            }
            JtView.newInstance = function () {
                return new JtView();
            };
            JtView.prototype.option = function () {
                return this.mOpt;
            };
            JtView.prototype.pluginUpdate = function (start, end) {
                var _this = this;
                this.mAjax.get({
                    start: start,
                    end: end
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.updateTable();
                    _this.updateChart();
                });
            };
            JtView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("焦炭", this);
            };
            JtView.prototype.updateChart = function () {
                var _this = this;
                var items = ["山西吕梁", "河北邢台", "山东青岛", "河南济源"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][1 + j]);
                    }
                });
                this.updateEchart("焦炭价格趋势（元/吨）", this.option().ct, items, data);
            };
            JtView.prototype.getDateType = function () {
                return jcycljg.DateType.DAY;
            };
            JtView.prototype.updateEchart = function (title, echart, legend, data) {
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
            JtView.prototype.updateTable = function () {
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
            return JtView;
        })(jcycljg.BasePluginView);
        jt.pluginView = JtView.newInstance();
    })(jt = jcycljg.jt || (jcycljg.jt = {}));
})(jcycljg || (jcycljg = {}));
