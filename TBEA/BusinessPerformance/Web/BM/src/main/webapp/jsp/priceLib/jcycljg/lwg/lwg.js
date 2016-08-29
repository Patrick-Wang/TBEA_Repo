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
    var lwg;
    (function (lwg) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq"),
                    new JQTable.Node("螺纹钢(HRB400 Φ12-14mm)（元/吨）", "l1214")
                        .append(new JQTable.Node("上海", "a1"))
                        .append(new JQTable.Node("杭州", "a2"))
                        .append(new JQTable.Node("南京", "a3"))
                        .append(new JQTable.Node("天津", "a4")),
                    new JQTable.Node("螺纹钢(HRB400 Φ16-25mm）（元/吨）", "1625")
                        .append(new JQTable.Node("上海", "b1"))
                        .append(new JQTable.Node("杭州", "b2"))
                        .append(new JQTable.Node("南京", "b3"))
                        .append(new JQTable.Node("天津", "b4"))
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var LwgView = (function (_super) {
            __extends(LwgView, _super);
            function LwgView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("update.do?type=" + jcycljg.JcycljgType.LWG, false);
            }
            LwgView.newInstance = function () {
                return new LwgView();
            };
            LwgView.prototype.option = function () {
                return this.mOpt;
            };
            LwgView.prototype.pluginUpdate = function (start, end) {
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
            LwgView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                if (this.mDispType == jcycljg.DisplayType.CHART) {
                    this.update1214Chart();
                    this.update1625Chart();
                }
                else {
                    this.updateTable();
                }
            };
            LwgView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("螺纹钢", this);
            };
            LwgView.prototype.update1214Chart = function () {
                var _this = this;
                var items = ["上海", "杭州", "南京", "天津"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][1 + j]);
                    }
                });
                this.updateEchart("螺纹钢(HRB400 Φ12-14mm)（元/吨）", this.option().ct1214, items, data);
            };
            LwgView.prototype.update1625Chart = function () {
                var _this = this;
                var items = ["上海", "杭州", "南京", "天津"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][5 + j]);
                    }
                });
                this.updateEchart("螺纹钢(HRB400 Φ16-25mm）（元/吨）", this.option().ct1625, items, data);
            };
            LwgView.prototype.getDateType = function () {
                return jcycljg.DateType.DAY;
            };
            LwgView.prototype.formateData = function (data) {
                for (var i = 0; i < data.length; ++i) {
                    for (var j = 0; j < data[i].length; ++j) {
                        if (data[i][j] == null) {
                            data[i][j] = '--';
                        }
                    }
                }
                return data;
            };
            LwgView.prototype.updateEchart = function (title, echart, legend, data) {
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
            LwgView.prototype.updateTable = function () {
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
            return LwgView;
        })(jcycljg.BasePluginView);
        lwg.pluginView = LwgView.newInstance();
    })(lwg = jcycljg.lwg || (jcycljg.lwg = {}));
})(jcycljg || (jcycljg = {}));
