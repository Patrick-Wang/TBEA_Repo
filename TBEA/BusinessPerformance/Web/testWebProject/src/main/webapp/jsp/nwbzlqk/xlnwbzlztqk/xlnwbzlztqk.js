/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../nwbzlqkdef.ts"/>
///<reference path="../nwbzlqk.ts"/>
///<reference path="../../jqgrid/jqgrid.d.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.xlnwbzlztqk = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var nwbzlqk;
(function (nwbzlqk) {
    var xlnwbzlztqk;
    (function (xlnwbzlztqk) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createZtTable = function (gridName, ydjd) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "单位", align: TextAlign.Center }),
                    Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "当月" : "当期" })
                        .append(Node.create({ name: "内部反馈质量问题数量" }))
                        .append(Node.create({ name: "外部反馈质量问题数量" }))
                        .append(Node.create({ name: "合计" })),
                    Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "年度累计" : "去年同期" })
                        .append(Node.create({ name: "内部反馈质量问题数量" }))
                        .append(Node.create({ name: "外部反馈质量问题数量" }))
                        .append(Node.create({ name: "合计" }))
                ], gridName);
            };
            JQGridAssistantFactory.createFdwTable = function (gridName, ydjd) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "月份", align: TextAlign.Center }),
                    Node.create({ name: "内部质量问题数量" }),
                    Node.create({ name: "外部质量问题数量" }),
                    Node.create({ name: "合计" })
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../xlnwbzlztqk/update.do", false);
                this.mCommentGet = new Util.Ajax("../report/zlfxUpdate.do", false);
                this.mCommentSubmit = new Util.Ajax("../report/zlfxSubmit.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.xlnwbzlztqk;
            };
            ShowView.prototype.isSupported = function (compType) {
                return compType == Util.CompanyType.LLGS || compType == Util.CompanyType.XLC
                    || compType == Util.CompanyType.DLGS || compType == Util.CompanyType.XLCY;
            };
            ShowView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case nwbzlqk.Event.ZLFE_IS_COMPANY_SUPPORTED:
                        return true;
                    case nwbzlqk.Event.ZLFE_SAVE_COMMENT:
                        var param = {
                            condition: Util.Ajax.toUrlParam({
                                url: this.mAjax.baseUrl(),
                                date: this.mDt,
                                companyId: this.mCompType,
                                ydjd: this.mYdjdType
                            }),
                            comment: e.data
                        };
                        this.mCommentSubmit.get({
                            data: JSON.stringify([[param.condition, param.comment]])
                        }).then(function (jsonData) {
                            Util.MessageBox.tip("保存成功", undefined);
                        });
                        break;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../xlnwbzlztqk/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType,
                    ydjd: this.mYdjdType
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mCompType = compType;
                this.mCommentGet.get({ condition: Util.Ajax.toUrlParam({
                        url: this.mAjax.baseUrl(),
                        date: date,
                        companyId: compType,
                        ydjd: this.mYdjdType
                    }), compId: compType }).then(function (jsonData) {
                    framework.router
                        .fromEp(_this)
                        .to(framework.basic.endpoint.FRAME_ID)
                        .send(nwbzlqk.Event.ZLFE_COMMENT_UPDATED, jsonData);
                });
                this.mAjax.get({
                    date: date,
                    companyId: compType,
                    ydjd: this.mYdjdType,
                    all: this.mCompType == Util.CompanyType.XLCY
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            ShowView.prototype.toCtVal = function (val) {
                var index = val.lastIndexOf('%');
                if (index >= 0) {
                    return val.substring(0, index);
                }
                return val == '--' ? 0 : val;
            };
            ShowView.prototype.updateEchart = function () {
                var title = "内外部质量问题整体情况";
                var legend = [];
                var echart = this.option().ct;
                var series = [];
                var xData = [];
                var tooltip = {
                    trigger: 'axis'
                };
                var yAxis = [
                    {
                        type: 'value'
                    }
                ];
                if (this.mYdjdType == nwbzlqk.YDJDType.YD) {
                    for (var i in this.mData.waveItems) {
                        legend.push(this.mData.waveItems[i].name);
                        var data = [];
                        for (var j = 0; j < this.mData.waveItems[i].data.length; ++j) {
                            data.push(this.mData.waveItems[i].data[j]);
                        }
                        series.push({
                            name: this.mData.waveItems[i].name,
                            type: 'line',
                            smooth: true,
                            // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                            data: data
                        });
                    }
                    for (var i = 0; i < 12; ++i) {
                        xData.push((i + 1) + "月");
                    }
                }
                else {
                    var dy = [];
                    var qntq = [];
                    if (this.mCompType == Util.CompanyType.XLCY) {
                        for (var i = 0; i < this.mData.tjjg.length; ++i) {
                            if (this.mData.tjjg[i][0].replace(/\s/g, "") == "合计") {
                                xData.push("内部质量问题");
                                dy.push(this.toCtVal(this.mData.tjjg[i][1]));
                                qntq.push(this.toCtVal(this.mData.tjjg[i][4]));
                                xData.push("外部质量问题");
                                dy.push(this.toCtVal(this.mData.tjjg[i][2]));
                                qntq.push(this.toCtVal(this.mData.tjjg[i][5]));
                            }
                        }
                    }
                    else {
                        xData.push("内部质量问题");
                        xData.push("外部质量问题");
                        dy.push(this.toCtVal(this.mData.tjjg[0][1]));
                        dy.push(this.toCtVal(this.mData.tjjg[0][2]));
                        dy.push(this.toCtVal(this.mData.tjjg[1][1]));
                        qntq.push(this.toCtVal(this.mData.tjjg[1][2]));
                    }
                    legend = ["当月", "去年同期"];
                    series.push({
                        name: legend[0],
                        type: 'bar',
                        smooth: true,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: dy
                    });
                    series.push({
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: qntq
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
                            boundaryGap: this.mYdjdType == nwbzlqk.YDJDType.YD ? false : true,
                            data: xData.length < 1 ? [0] : xData
                        }
                    ],
                    yAxis: yAxis,
                    series: series
                };
                echarts.init(this.$(echart)[0]).setOption(option);
            };
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
                this.$(this.option().ctarea).show();
                this.updateEchart();
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "内外部质量问题整体情况");
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist;
                if (this.mCompType == Util.CompanyType.XLCY) {
                    tableAssist = JQGridAssistantFactory.createZtTable(name, this.mYdjdType);
                }
                else {
                    tableAssist = JQGridAssistantFactory.createFdwTable(name, this.mYdjdType);
                }
                var pagername = name + "pager";
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                tableAssist.mergeTitle();
                this.$(name).jqGrid(tableAssist.decorate({
                    datatype: "local",
                    data: tableAssist.getData(this.mData.tjjg),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: this.mData.tjjg.length > 20 ? 20 * 22 : '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: this.mData.tjjg.length + 10,
                    viewrecords: true,
                    pager: '#' + pagername
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(nwbzlqk.ZlPluginView);
    })(xlnwbzlztqk = nwbzlqk.xlnwbzlztqk || (nwbzlqk.xlnwbzlztqk = {}));
})(nwbzlqk || (nwbzlqk = {}));
