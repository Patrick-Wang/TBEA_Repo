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
    var eva;
    (function (eva) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq"),
                    new JQTable.Node("北京有机（14-2）<br/>元/吨", "a1"),
                    new JQTable.Node("北京有机（18-3）<br/>元/吨", "a2"),
                    new JQTable.Node("扬巴V5110J<br/>元/吨", "a3")
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var EVAView = (function (_super) {
            __extends(EVAView, _super);
            function EVAView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("/BusinessManagement/jcycljg/update.do?type=" + jcycljg.JcycljgType.EVA, false);
            }
            EVAView.newInstance = function () {
                return new EVAView();
            };
            EVAView.prototype.option = function () {
                return this.mOpt;
            };
            EVAView.prototype.pluginUpdate = function (start, end) {
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
            EVAView.prototype.refresh = function () {
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
            EVAView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("EVA", this);
            };
            EVAView.prototype.updateChart = function () {
                var _this = this;
                var items = ["北京有机（14-2）", "北京有机（18-3）", "扬巴V5110J"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][1 + j]);
                    }
                });
                this.updateEchart("EVA价格趋势（元/吨）", this.option().ct, items, data);
            };
            EVAView.prototype.getDateType = function () {
                return jcycljg.DateType.DAY;
            };
            EVAView.prototype.formateData = function (data) {
                for (var i = 0; i < data.length; ++i) {
                    for (var j = 0; j < data[i].length; ++j) {
                        if (data[i][j] == null) {
                            data[i][j] = '--';
                        }
                    }
                }
                return data;
            };
            EVAView.prototype.updateEchart = function (title, echart, legend, data) {
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
            EVAView.prototype.adjustSize = function () {
                _super.prototype.adjustSize.call(this);
                this.updateChart();
            };
            EVAView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'><div id='" + this.jqgridName() + "pager" + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
                return this.tableAssist;
            };
            EVAView.prototype.updateTable = function () {
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
            return EVAView;
        })(jcycljg.BasePluginView);
        eva.pluginView = EVAView.newInstance();
    })(eva = jcycljg.eva || (jcycljg.eva = {}));
})(jcycljg || (jcycljg = {}));
