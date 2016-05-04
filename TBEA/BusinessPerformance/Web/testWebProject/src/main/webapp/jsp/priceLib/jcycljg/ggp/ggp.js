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
        }());
        var GgpView = (function (_super) {
            __extends(GgpView, _super);
            function GgpView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("update.do?type=" + jcycljg.JcycljgType.GGP, false);
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
                    _this.mData = _this.formateData(jsonData);
                    _this.refresh();
                });
            };
            GgpView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                if (this.mDispType == jcycljg.DisplayType.CHART) {
                    this.updateWgChart();
                    this.updateBgChart();
                }
                else {
                    this.updateTable();
                }
            };
            GgpView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("硅钢片", this);
            };
            GgpView.prototype.updateWgChart = function () {
                var _this = this;
                var items = ["30Q120", "30RK100", "27RK095", "23RK085"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][1 + j]);
                    }
                });
                this.updateEchart("武钢结算价格趋势（元/吨）", this.option().wg, items, data);
            };
            GgpView.prototype.updateBgChart = function () {
                var _this = this;
                var items = ["B30P120", "B30P100", "B27R095", "B27R085"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][5 + j]);
                    }
                });
                this.updateEchart("宝钢结算价格趋势（元/吨）", this.option().bg, items, data);
            };
            GgpView.prototype.getDateType = function () {
                return jcycljg.DateType.MONTH;
            };
            GgpView.prototype.formateData = function (data) {
                for (var i = 0; i < data.length; ++i) {
                    for (var j = 0; j < data[i].length; ++j) {
                        if (data[i][j] == null) {
                            data[i][j] = '--';
                        }
                    }
                }
                return data;
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
            GgpView.prototype.updateTable = function () {
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
            return GgpView;
        }(jcycljg.BasePluginView));
        ggp.pluginView = GgpView.newInstance();
    })(ggp = jcycljg.ggp || (jcycljg.ggp = {}));
})(jcycljg || (jcycljg = {}));
