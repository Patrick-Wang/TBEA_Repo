var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var jcycljg;
(function (jcycljg) {
    var pvcsz;
    (function (pvcsz) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq"),
                    new JQTable.Node("电石法PVC树脂  （元/吨）", "dsf")
                        .append(new JQTable.Node("泰州盐化", "a1"))
                        .append(new JQTable.Node("湖南株化", "a2"))
                        .append(new JQTable.Node("山西榆社", "a3"))
                        .append(new JQTable.Node("黑龙江昊华化工", "a4"))
                        .append(new JQTable.Node("河南宇航", "a5"))
                        .append(new JQTable.Node("陕西北元化工", "a6"))
                        .append(new JQTable.Node("宜宾天原", "a7")),
                    new JQTable.Node("电乙烯法PVC树脂（元/吨）", "yxf")
                        .append(new JQTable.Node("天津大沽化", "a8"))
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var PVCSzView = (function (_super) {
            __extends(PVCSzView, _super);
            function PVCSzView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("update.do?type=" + jcycljg.JcycljgType.PVCSZ, false);
            }
            PVCSzView.newInstance = function () {
                return new PVCSzView();
            };
            PVCSzView.prototype.option = function () {
                return this.mOpt;
            };
            PVCSzView.prototype.pluginUpdate = function (start, end) {
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
            PVCSzView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                if (this.mDispType == jcycljg.DisplayType.CHART) {
                    this.updateDsfChart();
                    this.updateYxfChart();
                }
                else {
                    this.updateTable();
                }
            };
            PVCSzView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("PVC树脂", this);
            };
            PVCSzView.prototype.updateDsfChart = function () {
                var _this = this;
                var items = ["泰州盐化", "湖南株化", "山西榆社", "黑龙江昊华化工", "河南宇航", "陕西北元化工", "宜宾天原"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][1 + j]);
                    }
                });
                this.updateEchart("电石法PVC树脂（元/吨）", this.option().dsf, items, data);
            };
            PVCSzView.prototype.updateYxfChart = function () {
                var _this = this;
                var items = ["天津大沽化"];
                var data = [];
                for (var k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each(function (i) {
                    for (var j = 0; j < items.length; ++j) {
                        data[j].push(_this.mData[i][8 + j]);
                    }
                });
                this.updateEchart("乙烯法PVC树脂（元/吨）", this.option().yxf, items, data);
            };
            PVCSzView.prototype.getDateType = function () {
                return jcycljg.DateType.DAY;
            };
            PVCSzView.prototype.formateData = function (data) {
                for (var i = 0; i < data.length; ++i) {
                    for (var j = 0; j < data[i].length; ++j) {
                        if (data[i][j] == null) {
                            data[i][j] = '--';
                        }
                    }
                }
                return data;
            };
            PVCSzView.prototype.updateEchart = function (title, echart, legend, data) {
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
            PVCSzView.prototype.updateTable = function () {
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
            return PVCSzView;
        }(jcycljg.BasePluginView));
        pvcsz.pluginView = PVCSzView.newInstance();
    })(pvcsz = jcycljg.pvcsz || (jcycljg.pvcsz = {}));
})(jcycljg || (jcycljg = {}));
