/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="jcycljgdef.ts" />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var jcycljg;
(function (jcycljg) {
    var ggp;
    (function (ggp) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("武钢（元/吨）", "wg")
                        .append(new JQTable.Node("30Q120", "w"))
                        .append(new JQTable.Node("30RK100", "ww"))
                        .append(new JQTable.Node("27RK095", "www"))
                        .append(new JQTable.Node("23RK085", "wwww")),
                    new JQTable.Node("宝钢（元/吨）", "bg")
                        .append(new JQTable.Node("B30P120", "b"))
                        .append(new JQTable.Node("B30P100", "bb"))
                        .append(new JQTable.Node("B27R095", "bbb"))
                        .append(new JQTable.Node("B27R085", "bbbb"))
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var GgpView = (function (_super) {
            __extends(GgpView, _super);
            function GgpView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("jcycljg/ggp/update.do", false);
            }
            GgpView.newInstance = function () {
                return new GgpView();
            };
            GgpView.prototype.option = function () {
                return this.mOpt;
            };
            GgpView.prototype.pluginUpdate = function (start, end) {
                var _this = this;
                this.mAjax.get({
                    start: start,
                    end: end
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.updateTable();
                    _this.updateWgChart();
                    _this.updateBgChart();
                });
            };
            GgpView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("硅钢片", this);
            };
            GgpView.prototype.updateWgChart = function () {
                var _this = this;
                var data = [[], [], [], []];
                $(this.mData).each(function (i) {
                    data[0].push(_this.mData[i][1]);
                    data[1].push(_this.mData[i][2]);
                    data[2].push(_this.mData[i][3]);
                    data[3].push(_this.mData[i][4]);
                });
                this.updateEchart("武钢结算价格趋势", this.option().wg, ["30Q120", "30RK100", "27RK095", "23RK085"], data);
            };
            GgpView.prototype.updateBgChart = function () {
                var _this = this;
                var data = [[], [], [], []];
                $(this.mData).each(function (i) {
                    data[0].push(_this.mData[i][5]);
                    data[1].push(_this.mData[i][6]);
                    data[2].push(_this.mData[i][7]);
                    data[3].push(_this.mData[i][8]);
                });
                this.updateEchart("宝钢结算价格趋势", this.option().bg, ["B30P120", "B30P100", "B27R095", "B27R085"], data);
            };
            GgpView.prototype.getDateType = function () {
                return jcycljg.DateType.MONTH;
            };
            GgpView.prototype.updateEchart = function (title, echart, legend, data) {
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
                        yAxisIndex: 0,
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
            GgpView.prototype.updateTable = function () {
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
                    data: tableAssist.getData(this.mData),
                    datatype: "local"
                }));
            };
            return GgpView;
        })(jcycljg.BasePluginView);
        ggp.pluginView = GgpView.newInstance();
    })(ggp = jcycljg.ggp || (jcycljg.ggp = {}));
})(jcycljg || (jcycljg = {}));
