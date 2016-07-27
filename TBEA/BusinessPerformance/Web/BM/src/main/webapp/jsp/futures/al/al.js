/// <reference path="../futuresdef.ts" />
///<reference path="../../jqgrid/jqassist.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var futures;
(function (futures) {
    var al;
    (function (al) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    JQTable.Node.create({ name: "企业名称", width: 100 }),
                    JQTable.Node.create({ name: "类型", width: 100 }),
                    JQTable.Node.create({ name: "持仓量（吨）", width: 100 }),
                    JQTable.Node.create({ name: "持仓均价（元）", width: 150 }),
                    JQTable.Node.create({ name: "现价（元）", width: 100 }),
                    JQTable.Node.create({ name: "盈亏比例", width: 100 }),
                    JQTable.Node.create({ name: "盈亏金额（元）", width: 150 })
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var AlView = (function (_super) {
            __extends(AlView, _super);
            function AlView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("update.do?type=2", false);
            }
            AlView.newInstance = function () {
                return new AlView();
            };
            AlView.prototype.option = function () {
                return this.mOpt;
            };
            AlView.prototype.pluginUpdate = function () {
                var _this = this;
                this.mAjax.get({})
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.mChartData = _this.mData.chartData;
                    _this.mTableData = _this.mData.tableData;
                    _this.refresh();
                });
            };
            AlView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                if (this.mDispType == futures.DisplayType.CHART) {
                    this.updateChart();
                }
                else {
                    this.updateTable();
                }
            };
            AlView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("铝", this);
            };
            AlView.prototype.updateChart = function () {
                var _this = this;
                var legends = [];
                var xData = [];
                var data = [];
                //for (let k = 0; k < items.length; ++k){
                //    data.push([]);
                //}
                $(this.mChartData).each(function (i) {
                    legends.push(_this.mChartData[i].companyName);
                    xData = _this.mChartData[i].dateSet;
                    //for (let j = 0; j < legends.length; ++j) {
                    data[i] = _this.mChartData[i].valueList;
                    //}
                });
                this.updateEchart("期货利润（元）", this.option().ct, legends, xData, data);
            };
            AlView.prototype.updateEchart = function (title, echart, legend, xData, data) {
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
            AlView.prototype.updateTable = function () {
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
                    width: '100%',
                    shrinkToFit: false,
                    autoScroll: true,
                    rowNum: 20,
                    data: tableAssist.getData(this.mTableData),
                    datatype: "local",
                    viewrecords: true,
                    pager: name + "pager"
                }));
            };
            return AlView;
        })(futures.BasePluginView);
        al.pluginView = AlView.newInstance();
    })(al = futures.al || (futures.al = {}));
})(futures || (futures = {}));
