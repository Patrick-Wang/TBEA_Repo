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
    var zhb;
    (function (zhb) {
        var JQGridAssistantFactory = /** @class */ (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("上海马钢<br/>（元/吨）", "a1"),
                    new JQTable.Node("南京马钢<br/>（元/吨）", "a2"),
                    new JQTable.Node("广州韶钢<br/>（元/吨）", "a3"),
                    new JQTable.Node("长沙萍钢<br/>（元/吨）", "a4"),
                    new JQTable.Node("北京临钢<br/>（元/吨）", "a5"),
                    new JQTable.Node("沈阳天钢<br/>（元/吨）", "a6"),
                    new JQTable.Node("乌鲁木齐八钢<br/>（元/吨）", "a7"),
                    new JQTable.Node("平均价<br/>（元/吨）", "a8")
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var ZhbView = /** @class */ (function (_super) {
            __extends(ZhbView, _super);
            function ZhbView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjax = new Util.Ajax("/BusinessManagement/jcycljg/update.do?type=" + jcycljg.JcycljgType.ZHB, false);
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
                //            rowNum: 15,
                //            data: tableAssist.getData(this.mData),
                //            datatype: "local",
                //            viewrecords : true,
                //            pager : name + "pager"
                //        }));
                //}
            }
            ZhbView.newInstance = function () {
                return new ZhbView();
            };
            ZhbView.prototype.option = function () {
                return this.mOpt;
            };
            ZhbView.prototype.pluginUpdate = function (start, end) {
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
            ZhbView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                //if (this.mDispType == DisplayType.CHART) {
                this.updateTable();
                this.updateChart();
                this.adjustSize();
                //}else{
                //    this.updateTable();
                //}
            };
            ZhbView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("中厚板（Q235 20mm）", this);
            };
            ZhbView.prototype.updateChart = function () {
                var _this = this;
                var items = ["上海马钢", "南京马钢", "广州韶钢", "长沙萍钢", "北京临钢", "沈阳天钢", "乌鲁木齐八钢", "平均价"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][1 + j]);
                    }
                });
                this.updateEchart("中厚板（元/吨）", this.option().ct, items, data);
            };
            ZhbView.prototype.getDateType = function () {
                return jcycljg.DateType.DAY;
            };
            ZhbView.prototype.formateData = function (data) {
                for (var i = 0; i < data.length; ++i) {
                    for (var j = 0; j < data[i].length; ++j) {
                        if (data[i][j] == null) {
                            data[i][j] = '--';
                        }
                    }
                }
                return data;
            };
            ZhbView.prototype.updateEchart = function (title, echart, legend, data) {
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
                        data: data[i].length < 1 ? [0] : (data[i] == null ? 0 : data[i])
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
            ZhbView.prototype.adjustSize = function () {
                _super.prototype.adjustSize.call(this);
                this.updateChart();
            };
            ZhbView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'><div id='" + this.jqgridName() + "pager" + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
                return this.tableAssist;
            };
            ZhbView.prototype.updateTable = function () {
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
                this.adjustSize();
            };
            return ZhbView;
        }(jcycljg.BasePluginView));
        zhb.pluginView = ZhbView.newInstance();
    })(zhb = jcycljg.zhb || (jcycljg.zhb = {}));
})(jcycljg || (jcycljg = {}));
