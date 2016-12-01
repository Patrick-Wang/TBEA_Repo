/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../sddbdef.ts"/>
///<reference path="../sddb.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.sddb = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var sddb;
(function (sddb_1) {
    var sddb;
    (function (sddb) {
        var router = framework.router;
        var FRAME_ID = framework.basic.endpoint.FRAME_ID;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, headers) {
                var nodes = [];
                for (var i = 0; i < headers.length; ++i) {
                    var node = Util.parseHeader(headers[i]);
                    if (null != node) {
                        nodes.push(node);
                    }
                }
                return new JQTable.JQGridAssistant(nodes, gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
            }
            ShowView.prototype.getId = function () {
                return plugin.sddb;
            };
            ShowView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case framework.basic.FrameEvent.FE_UPDATE:
                        {
                            this.pluginUpdate(e.data.dStart, e.data.dEnd, e.data.compType, e.data.item, e.data.item0);
                        }
                        return;
                    case framework.basic.FrameEvent.FE_GET_EXPORTURL:
                        {
                            return this.pluginGetExportUrl(e.data.dStart, e.data.dEnd, e.data.compType, e.data.item, e.data.item0);
                        }
                    default:
                        break;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.pluginGetExportUrl = function (dStart, dEnd, compType, item, item0) {
                return this.option().exportUrl + "?" + Util.Ajax.toUrlParam({
                    dStart: dStart,
                    dEnd: dEnd,
                    item: compType,
                    model: item,
                    item0: item0
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.pluginUpdate = function (dStart, dEnd, compType, item, item0) {
                var _this = this;
                this.mCompType = compType;
                if (undefined != dStart) {
                    var dS = new Date(Date.parse(dStart.replace(/-/g, '/'))).getTime();
                    var dE = new Date(Date.parse(dEnd.replace(/-/g, '/'))).getTime();
                    if (dS < dE) {
                        this.mAjax.get({
                            dStart: dStart,
                            dEnd: dEnd,
                            item: compType,
                            model: item,
                            item0: item0
                        })
                            .then(function (jsonData) {
                            _this.mData = jsonData;
                            _this.refresh();
                        });
                    }
                    else {
                        Util.MessageBox.tip("起始时间不能晚于结束时间");
                    }
                }
                else {
                    this.mAjax.get({
                        dStart: dStart,
                        dEnd: dEnd,
                        item: compType,
                        model: item,
                        item0: item0
                    })
                        .then(function (jsonData) {
                        _this.mData = jsonData;
                        _this.refresh();
                    });
                }
            };
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                if (this.mData.showTime == false) {
                    router.to(FRAME_ID).send(sddb_1.SDDBEvent.FE_HIDETIME);
                }
                else {
                    router.to(FRAME_ID).send(sddb_1.SDDBEvent.FE_SHOWTIME);
                }
                this.updateTable();
                this.updateCharts();
            };
            ShowView.prototype.init = function (opt) {
                this.mAjax = new Util.Ajax(opt.updateUrl, false);
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, opt.title);
            };
            ShowView.prototype.updateCharts = function () {
                var display = "none";
                if (undefined != this.mData.charts) {
                    var validCount = 0;
                    for (var i = 0; i < this.mData.charts.length; ++i) {
                        if (this.mData.charts[i].isValid == true) {
                            ++validCount;
                        }
                    }
                    if (validCount != 0) {
                        display = "";
                        $("#" + this.mOpt.chartId).css("display", "")
                            .css("width", this.mData.width == undefined ? 1300 : this.mData.width)
                            .css("height", validCount / 2 * 300 + validCount % 2 * 300);
                        $("#chartName").css("display", "")[0].innerHTML = this.mData.chartName;
                    }
                    for (var i = 0; i < this.mData.charts.length; ++i) {
                        if (this.mData.charts[i].isValid == true) {
                            var ctSel = $("#" + this.mOpt.chartId + i);
                            if (ctSel.length == 0) {
                                $("#" + this.mOpt.chartId)
                                    .append("<div id='" + this.mOpt.chartId + i + "' style='float:left;width:49%;height:300px'/>");
                            }
                            if (validCount == 1) {
                                $("#" + this.mOpt.chartId + "0").css("width", "98%");
                            }
                            this.updateChart(this.mOpt.chartId + i, this.mData.charts[i]);
                        }
                    }
                }
                $("#" + this.mOpt.chartId).css("display", display);
                $("#chartName").css("display", display);
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                //var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mData.header);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var tableAssist = Util.createTable(name, this.mData);
                this.$(name).jqGrid(tableAssist.decorate({
                    datatype: "local",
                    data: tableAssist.getData(this.mData.data),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    autoScroll: true,
                    rowNum: 1000,
                    height: this.mData.data.length > 25 ? 550 : '100%',
                    width: this.mData.width == undefined ? 1300 : this.mData.width,
                    shrinkToFit: this.mData.shrinkToFit == "false" ? false : true
                }));
            };
            ShowView.prototype.updateChart = function (ctId, chart) {
                var series = [];
                for (var i in chart.yNames) {
                    series.push({
                        name: chart.yNames[i],
                        type: chart.type,
                        smooth: true,
                        //barCategoryGap: "50%",
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: chart.data[i].length < 1 ? [0] : Util.replaceNull(chart.data[i])
                    });
                }
                var tooltip = {
                    trigger: 'axis'
                };
                var yAxis = [
                    {
                        type: 'value'
                    }
                ];
                if (chart.percentage) {
                    tooltip.formatter = function (params) {
                        var ret = params[0][1];
                        for (var i = 0; i < params.length; ++i) {
                            ret += "<br/>" + params[i][0] + ' : ' + (params[i][2] * 1.0).toFixed(2) + "%";
                        }
                        return ret;
                    };
                    yAxis[0].axisLabel = {
                        formatter: '{value} %'
                    };
                }
                var option = {
                    title: {
                        text: chart.title
                    },
                    tooltip: tooltip,
                    legend: {
                        data: chart.yNames
                    },
                    toolbox: {
                        show: true
                    },
                    calculable: false,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: chart.boundaryGap,
                            data: chart.xNames.length < 1 ? [0] : chart.xNames
                        }
                    ],
                    yAxis: yAxis,
                    series: series
                };
                echarts.init($("#" + ctId)[0]).setOption(option);
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(framework.basic.ShowPluginView);
    })(sddb = sddb_1.sddb || (sddb_1.sddb = {}));
})(sddb || (sddb = {}));
