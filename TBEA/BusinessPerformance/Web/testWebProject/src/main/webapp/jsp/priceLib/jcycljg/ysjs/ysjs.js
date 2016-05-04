var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var jcycljg;
(function (jcycljg) {
    var ysjs;
    (function (ysjs) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("长江现货（元/吨）", "cjxh")
                        .append(new JQTable.Node("铜", "xhcu"))
                        .append(new JQTable.Node("铝", "xhal"))
                        .append(new JQTable.Node("锌", "xhzn")),
                    new JQTable.Node("LME结算价（美元/吨）", "cjxh")
                        .append(new JQTable.Node("铜", "LEMcu"))
                        .append(new JQTable.Node("铝", "LEMal"))
                        .append(new JQTable.Node("锌", "LEMzn"))
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var YsjsView = (function (_super) {
            __extends(YsjsView, _super);
            function YsjsView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("update.do?type=" + jcycljg.JcycljgType.YSJS, false);
            }
            YsjsView.newInstance = function () {
                return new YsjsView();
            };
            YsjsView.prototype.option = function () {
                return this.mOpt;
            };
            YsjsView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                if (this.mDispType == jcycljg.DisplayType.CHART) {
                    this.updateCuChart();
                    this.updateAlChart();
                    this.updateZnChart();
                }
                else {
                    this.updateTable();
                }
            };
            YsjsView.prototype.pluginUpdate = function (start, end) {
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
            YsjsView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("有色金属类", this);
            };
            YsjsView.prototype.updateCuChart = function () {
                var _this = this;
                var data = [];
                var lemData = [];
                $(this.mData).each(function (i) {
                    data.push(_this.mData[i][1]);
                    lemData.push(_this.mData[i][4]);
                });
                this.updateEchart("铜 结算价格趋势", this.option().cu, data, lemData);
            };
            YsjsView.prototype.updateAlChart = function () {
                var _this = this;
                var data = [];
                var lemData = [];
                $(this.mData).each(function (i) {
                    data.push(_this.mData[i][2]);
                    lemData.push(_this.mData[i][5]);
                });
                this.updateEchart("铝 结算价格趋势", this.option().al, data, lemData);
            };
            YsjsView.prototype.updateZnChart = function () {
                var _this = this;
                var data = [];
                var lemData = [];
                $(this.mData).each(function (i) {
                    data.push(_this.mData[i][3]);
                    lemData.push(_this.mData[i][6]);
                });
                this.updateEchart("锌 结算价格趋势", this.option().zn, data, lemData);
            };
            YsjsView.prototype.formateData = function (data) {
                for (var i = 0; i < data.length; ++i) {
                    for (var j = 0; j < data[i].length; ++j) {
                        if (data[i][j] == null) {
                            data[i][j] = '--';
                        }
                    }
                }
                return data;
            };
            YsjsView.prototype.updateEchart = function (title, echart, data, lemData) {
                var _this = this;
                var xData = [];
                $(this.mData).each(function (i) {
                    xData.push(_this.mData[i][0]);
                });
                var option = {
                    title: {
                        text: title
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ["长江现货价格", "LME结算价格"]
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
                        },
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: "长江现货价格",
                            type: 'line',
                            smooth: true,
                            yAxisIndex: 0,
                            data: data.length < 1 ? [0] : data
                        },
                        {
                            name: "LME结算价格",
                            type: 'line',
                            yAxisIndex: 1,
                            smooth: true,
                            data: lemData.length < 1 ? [0] : lemData
                        }
                    ]
                };
                echarts.init(this.$(echart)[0]).setOption(option);
            };
            YsjsView.prototype.updateTable = function () {
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
            return YsjsView;
        }(jcycljg.BasePluginView));
        ysjs.pluginView = YsjsView.newInstance();
    })(ysjs = jcycljg.ysjs || (jcycljg.ysjs = {}));
})(jcycljg || (jcycljg = {}));
