/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../sbdscqyqkdef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.xfscqy = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var sbdscqyqk;
(function (sbdscqyqk) {
    var xfscqy;
    (function (xfscqy) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, date) {
                var curDate = new Date(Date.parse(date.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                var data = [];
                var node;
                var titleNodes = [];
                node = new JQTable.Node("行业", "hy1", true, TextAlign.Center);
                titleNodes.push(node);
                node = new JQTable.Node("行业", "hy2", true, TextAlign.Center);
                titleNodes.push(node);
                node = new JQTable.Node("上年度", "snd", true, TextAlign.Center);
                for (var i = month; i <= 12; ++i) {
                    node.append(new JQTable.Node(i + "月", "snd_" + i));
                }
                titleNodes.push(node);
                //if (month != 12) {
                //    titleNodes.push(node);
                //}
                node = new JQTable.Node("本年度", "wlyddmlspcs_bnd", true, TextAlign.Center);
                for (var i = 1; i <= month; ++i) {
                    node.append(new JQTable.Node(i + "月", "bnd_" + i));
                }
                titleNodes.push(node);
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../xfscqy/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.xfscqy;
            };
            ShowView.prototype.pluginGetUnit = function () {
                return "单位：万元";
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../xfscqy/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mCompType = compType;
                this.mAjax.get({
                    date: date,
                    companyId: compType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.$(this.option().ctarea).show();
                this.updateEchart(this.updateTable());
            };
            ShowView.prototype.updateEchart = function (data) {
                var title = "行业领域签约趋势";
                var legend = [
                    "传统电力市场",
                    "新能源市场",
                    "重点领域市场",
                    "其它"];
                var xData = [];
                var month = this.getMonth();
                for (var i = month; i <= 12; ++i) {
                    xData.push(i + "月");
                }
                for (var i = 1; i <= month; ++i) {
                    xData.push(i + "月");
                }
                var tooltip = {
                    trigger: 'axis'
                };
                var yAxis = [
                    {
                        type: 'value'
                    }
                ];
                var series = [];
                for (var i = 0; i < legend.length; ++i) {
                    var rData = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
                    for (var j = 0; j < data.length; ++j) {
                        if (legend[i] == data[j][0]) {
                            for (var k = 0; k < 13; ++k) {
                                rData[k] += data[j][k + 2] == "--" ? 0 : parseFloat(data[j][k + 2]);
                            }
                        }
                    }
                    for (var k = 0; k < 13; ++k) {
                        rData[k] = rData[k].toFixed(1);
                    }
                    series.push({
                        name: legend[i],
                        type: 'line',
                        smooth: true,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: rData
                    });
                }
                var option = {
                    title: {
                        text: title
                    },
                    tooltip: tooltip,
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
                    yAxis: yAxis,
                    series: series
                };
                echarts.init(this.$(this.option().ct)[0]).setOption(option);
            };
            ShowView.prototype.init = function (opt) {
                framework.router.fromEp(this).to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_REGISTER, "细分市场签约（国内市场制造业签约）");
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist = JQGridAssistantFactory.createTable(name, this.mDt);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var data = [["传统电力市场"],
                    ["传统电力市场"],
                    ["传统电力市场"],
                    ["传统电力市场"],
                    ["新能源市场"],
                    ["新能源市场"],
                    ["新能源市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["连锁经营"],
                    ["其它"],
                    ["合计"]];
                for (var i_1 = 0; i_1 < data.length; ++i_1) {
                    if (i_1 == data.length - 3) {
                        data[i_1] = data[i_1].concat(this.mData[i_1 + 1]);
                    }
                    else if (i_1 == data.length - 2) {
                        data[i_1] = data[i_1].concat(this.mData[i_1 - 1]);
                    }
                    else {
                        data[i_1] = data[i_1].concat(this.mData[i_1]);
                    }
                }
                var dOut = [];
                var vec = new std.vector();
                vec.push(-1);
                var formaterChain = new Util.FormatFordotHandler(1, []);
                var row = [];
                for (var j = 0; j < data.length; ++j) {
                    row = [].concat(data[j]);
                    for (var i = 2; i < row.length; ++i) {
                        row[i] = formaterChain.handle(row[0], i, row[i]);
                    }
                    dOut.push(row);
                }
                tableAssist.mergeColum(0);
                tableAssist.mergeRow(0);
                tableAssist.mergeTitle();
                this.$(name).jqGrid(tableAssist.decorate({
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 1000,
                    data: tableAssist.getData(dOut),
                    datatype: "local",
                    viewrecords: true
                }));
                return data;
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(framework.basic.ShowPluginView);
    })(xfscqy = sbdscqyqk.xfscqy || (sbdscqyqk.xfscqy = {}));
})(sbdscqyqk || (sbdscqyqk = {}));
