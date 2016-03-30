/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="jcycljgdef.ts" />
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
                        .append(new JQTable.Node("吕", "xhal"))
                        .append(new JQTable.Node("锌", "xhzn")),
                    new JQTable.Node("LME结算价（美元/吨）", "cjxh")
                        .append(new JQTable.Node("铜", "LEMcu"))
                        .append(new JQTable.Node("吕", "LEMal"))
                        .append(new JQTable.Node("锌", "LEMzn"))
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var YsjsView = (function () {
            function YsjsView() {
            }
            YsjsView.newInstance = function () {
                return new YsjsView();
            };
            YsjsView.prototype.hide = function () {
                $("#" + this.mOpt.al).hide();
                $("#" + this.mOpt.cu).hide();
                $("#" + this.mOpt.zn).hide();
                $("#" + this.mOpt.tb).hide();
            };
            YsjsView.prototype.show = function () {
                $("#" + this.mOpt.al).show();
                $("#" + this.mOpt.cu).show();
                $("#" + this.mOpt.zn).show();
                $("#" + this.mOpt.tb).show();
            };
            YsjsView.prototype.update = function (st, ed) {
                var _this = this;
                this.mDataSet.get({
                    start: st.year + "-" + st.month + "-" + st.day,
                    end: ed.year + "-" + ed.month + "-" + ed.day
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.updateTable();
                    _this.updateCuChart();
                    _this.updateAlChart();
                    _this.updateZnChart();
                });
            };
            YsjsView.prototype.init = function (opt) {
                this.mOpt = opt;
                this.mDataSet = new Util.Ajax("jcycljg/ysjs/update.do", false);
                view.register("有色金属类", this);
            };
            YsjsView.prototype.updateUI = function () {
                var _this = this;
                this.mDateSelector.toString();
                this.mDataSet.get({
                    start: this.mDateSelector.toString() + "-1",
                    end: this.mDateSelector.toString() + "-" + this.mDateSelector.monthDays()
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.updateTable();
                    _this.updateCuChart();
                    _this.updateAlChart();
                    _this.updateZnChart();
                });
            };
            YsjsView.prototype.updateCuChart = function () {
                var _this = this;
                var data = [];
                var lemData = [];
                $(this.mData).each(function (i) {
                    data.push(_this.mData[i][1]);
                    lemData.push(_this.mData[i][4]);
                });
                this.updateEchart("铜 结算价格趋势", this.mOpt.cu, data, lemData);
            };
            YsjsView.prototype.updateAlChart = function () {
                var _this = this;
                var data = [];
                var lemData = [];
                $(this.mData).each(function (i) {
                    data.push(_this.mData[i][2]);
                    lemData.push(_this.mData[i][5]);
                });
                this.updateEchart("铝 结算价格趋势", this.mOpt.al, data, lemData);
            };
            YsjsView.prototype.updateZnChart = function () {
                var _this = this;
                var data = [];
                var lemData = [];
                $(this.mData).each(function (i) {
                    data.push(_this.mData[i][3]);
                    lemData.push(_this.mData[i][6]);
                });
                this.updateEchart("锌 结算价格趋势", this.mOpt.zn, data, lemData);
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
                            // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                            data: data.length < 1 ? [0] : data
                        },
                        {
                            name: "LME结算价格",
                            type: 'line',
                            yAxisIndex: 1,
                            smooth: true,
                            // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                            data: lemData.length < 1 ? [0] : lemData
                        }
                    ]
                };
                echarts.init($('#' + echart)[0]).setOption(option);
            };
            YsjsView.prototype.updateTable = function () {
                var name = this.mOpt.tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                var parent = $("#" + this.mOpt.tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                $("#" + name).jqGrid(tableAssist.decorate({
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
            return YsjsView;
        })();
        ysjs.pluginView = YsjsView.newInstance();
    })(ysjs = jcycljg.ysjs || (jcycljg.ysjs = {}));
})(jcycljg || (jcycljg = {}));
