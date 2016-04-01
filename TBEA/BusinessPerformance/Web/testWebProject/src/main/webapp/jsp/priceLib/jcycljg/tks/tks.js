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
    var tks;
    (function (tks) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("国产矿（元/吨）", "gck")
                        .append(new JQTable.Node("山西代县<br/>64品位", "sxdx"))
                        .append(new JQTable.Node("辽宁辽阳<br/>65品位", "lnly"))
                        .append(new JQTable.Node("山东淄博<br/>65品位", "sdzb"))
                        .append(new JQTable.Node("安徽霍邱<br/>64品位", "anhq")),
                    new JQTable.Node("进口矿（元/吨）", "jkk")
                        .append(new JQTable.Node("青岛港巴西粉矿<br/>63.5品位", "qdgbxfk"))
                        .append(new JQTable.Node("印度粉矿<br/>60品位", "ydfk"))
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var TksView = (function (_super) {
            __extends(TksView, _super);
            function TksView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("jcycljg/update.do?type=" + jcycljg.JcycljgType.TKS, false);
            }
            TksView.newInstance = function () {
                return new TksView();
            };
            TksView.prototype.option = function () {
                return this.mOpt;
            };
            TksView.prototype.pluginUpdate = function (start, end) {
                var _this = this;
                this.mAjax.get({
                    start: start,
                    end: end
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.updateTable();
                    _this.updateGcChart();
                    _this.updateJkChart();
                });
            };
            TksView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("铁矿石", this);
            };
            TksView.prototype.updateJkChart = function () {
                var _this = this;
                var items = ["山西代县(64品位)", "辽宁辽阳(65品位)", "山东淄博(65品位)", "安徽霍邱(64品位)"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][1 + j]);
                    }
                });
                this.updateEchart("国产矿价格趋势（元/吨）", this.option().jk, items, data);
            };
            TksView.prototype.updateGcChart = function () {
                var _this = this;
                var items = ["青岛港巴西粉矿(63.5品位)", "印度粉矿(60品位)"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][5 + j]);
                    }
                });
                this.updateEchart("进口矿价格趋势（元/吨）", this.option().gc, items, data);
            };
            TksView.prototype.getDateType = function () {
                return jcycljg.DateType.DAY;
            };
            TksView.prototype.updateEchart = function (title, echart, legend, data) {
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
            TksView.prototype.updateTable = function () {
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
            return TksView;
        })(jcycljg.BasePluginView);
        tks.pluginView = TksView.newInstance();
    })(tks = jcycljg.tks || (jcycljg.tks = {}));
})(jcycljg || (jcycljg = {}));
