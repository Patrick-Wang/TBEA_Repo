/// <reference path="../../../jqgrid/jqassist.ts" />
/// <reference path="../../../util.ts" />
/// <reference path="../../../dateSelector.ts" />
/// <reference path="../jcycljgdef.ts" />
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
var jcycljg;
(function (jcycljg) {
    var fgc;
    (function (fgc) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("北京<br/>（元/吨）", "bj"),
                    new JQTable.Node("天津<br/>（元/吨）", "tj"),
                    new JQTable.Node("大连<br/>（元/吨）", "dl"),
                    new JQTable.Node("唐山<br/>（元/吨）", "ts"),
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var FgcView = (function (_super) {
            __extends(FgcView, _super);
            function FgcView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjax = new Util.Ajax("/BusinessManagement/jcycljg/update.do?type=" + jcycljg.JcycljgType.FGC, false);
                return _this;
                //private updateTable():void {
                //    var name = this.option().host + this.option().tb + "_jqgrid_1234";
                //    var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
                //    var parent = this.$(this.option().tb);
                //    parent.empty();
                //    parent.append("<table id='" + name + "'></table><div id='" + name + "pager" + "'></div>");
                //    this.$(name).jqGrid(
                //        tableAssist.decorate({
                //            multiselect: false,
                //            drag: false,
                //            resize: false,
                //            height: '100%',
                //            width: 1200,
                //            shrinkToFit: true,
                //            autoScroll: true,
                //            rowNum: 20,
                //            data: tableAssist.getData(this.mData),
                //            datatype: "local",
                //            viewrecords : true,
                //            pager : name + "pager"
                //        }));
                //}
            }
            FgcView.newInstance = function () {
                return new FgcView();
            };
            FgcView.prototype.option = function () {
                return this.mOpt;
            };
            FgcView.prototype.pluginUpdate = function (start, end) {
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
            FgcView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                //if (this.mDispType == DisplayType.CHART) {
                this.updateChart();
                //}else{
                this.updateTable();
                //}
                this.adjustSize();
            };
            FgcView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("废钢材", this);
            };
            FgcView.prototype.updateChart = function () {
                var _this = this;
                var items = ["北京", "天津", "大连", "唐山"];
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
            FgcView.prototype.getDateType = function () {
                return jcycljg.DateType.DAY;
            };
            FgcView.prototype.formateData = function (data) {
                for (var i = 0; i < data.length; ++i) {
                    for (var j = 0; j < data[i].length; ++j) {
                        if (data[i][j] == null) {
                            data[i][j] = '--';
                        }
                    }
                }
                return data;
            };
            FgcView.prototype.updateEchart = function (title, echart, legend, data) {
                var _this = this;
                var xData = [];
                this.formateData(data);
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
            FgcView.prototype.adjustSize = function () {
                _super.prototype.adjustSize.call(this);
                this.updateChart();
            };
            FgcView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'><div id='" + this.jqgridName() + "pager" + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
                return this.tableAssist;
            };
            FgcView.prototype.updateTable = function () {
                this.createJqassist();
                this.tableAssist.create({
                    data: this.mData,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 15,
                    autoScroll: true,
                    pager: this.jqgridName() + "pager"
                });
            };
            return FgcView;
        }(jcycljg.BasePluginView));
        fgc.pluginView = FgcView.newInstance();
    })(fgc = jcycljg.fgc || (jcycljg.fgc = {}));
})(jcycljg || (jcycljg = {}));
