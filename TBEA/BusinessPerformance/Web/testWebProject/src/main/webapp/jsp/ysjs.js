/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="dateSelector.ts" />
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
    var View = (function () {
        function View() {
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (opt) {
            this.mOpt = opt;
            this.mDataSet = new Util.Ajax("update.do");
            this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3, month: 1, day: 1 }, {
                year: this.mOpt.date.year,
                month: this.mOpt.date.month,
                day: this.mOpt.date.day
            }, this.mOpt.dt);
            this.updateUI();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.get({ start: "2013-12-12", end: "2019-9-8" })
                .then(function (jsonData) {
                _this.mData = jsonData;
                _this.updateTable();
            });
        };
        View.prototype.fillData = function (month) {
            if (this.mChartData == undefined) {
                this.mChartData.push([]);
                this.mChartData.push([]);
                for (var i = 1; i <= this.mMonth; ++i) {
                    month.push(i + "月");
                    this.mChartData[0].push(Math.floor(Math.random() * (1000 + 1)) + "");
                    this.mChartData[1].push(Math.floor(Math.random() * (1000 + 1)) + "");
                }
            }
            else {
                for (var i = 1; i <= 12; ++i) {
                    month.push(i + "月");
                }
            }
        };
        View.prototype.updateEchart = function (echart) {
            var month = [];
            this.fillData(month);
            var data = this.mChartData;
            var option = {
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: [this.mYear - 1 + "年", this.mYear + "年"]
                },
                toolbox: {
                    show: true
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: month
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: this.mYear - 1 + '年',
                        type: 'line',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[0]
                    },
                    {
                        name: this.mYear + '年',
                        type: 'line',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[1]
                    }
                ]
            };
            echarts.init($('#' + echart)[0]).setOption(option);
        };
        View.prototype.updateTable = function () {
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
        return View;
    })();
    ysjs.View = View;
})(ysjs || (ysjs = {}));
var view = ysjs.View.newInstance();
