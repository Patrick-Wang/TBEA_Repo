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
    plugin.pdwbzlwtfl = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var nwbzlqk;
(function (nwbzlqk) {
    var pdwbzlwtfl;
    (function (pdwbzlwtfl) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createZtTable = function (gridName, ydjd) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "问题类别", align: TextAlign.Center }),
                    Node.create({ name: "沈变中特公司" })
                        .append(Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "当月" : "当期" }))
                        .append(Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "年度累计" : "去年同期" })),
                    Node.create({ name: "衡变电气公司" })
                        .append(Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "当月" : "当期" }))
                        .append(Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "年度累计" : "去年同期" })),
                    Node.create({ name: "新变中特" })
                        .append(Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "当月" : "当期" }))
                        .append(Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "年度累计" : "去年同期" })),
                    Node.create({ name: "新变智能电气" })
                        .append(Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "当月" : "当期" }))
                        .append(Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "年度累计" : "去年同期" })),
                    Node.create({ name: "天变公司" })
                        .append(Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "当月" : "当期" }))
                        .append(Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "年度累计" : "去年同期" })),
                    Node.create({ name: "合计" })
                        .append(Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "当月" : "当期" }))
                        .append(Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "年度累计" : "去年同期" }))
                ], gridName);
            };
            JQGridAssistantFactory.createFdwTable = function (gridName, ydjd) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "问题类别", align: TextAlign.Center }),
                    Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "当月" : "当期" }),
                    Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "年度累计" : "去年同期" })
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../pdwbzlwtfl/update.do", false);
                this.mCommentGet = new Util.Ajax("../report/zlfxUpdate.do", false);
                this.mCommentSubmit = new Util.Ajax("../report/zlfxSubmit.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.pdwbzlwtfl;
            };
            ShowView.prototype.isSupported = function (compType) {
                return compType == Util.CompanyType.SBZTFGS || compType == Util.CompanyType.HBDQFGS
                    || compType == Util.CompanyType.XBZTGS || compType == Util.CompanyType.TBGS
                    || compType == Util.CompanyType.XBXBGS || compType == Util.CompanyType.PDCY;
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
                return "../pdwbzlwtfl/export.do?" + Util.Ajax.toUrlParam({
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
                    all: this.mCompType == Util.CompanyType.PDCY
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
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
                if (this.mData.tjjg.length > 0) {
                    this.$(this.option().ctarea).show();
                    if (this.mYdjdType == nwbzlqk.YDJDType.JD) {
                        this.$(this.option().ct1).hide();
                        this.$(this.option().ct).css("width", "100%");
                        this.updateJDEchart();
                    }
                    else {
                        this.$(this.option().ct).show();
                        this.$(this.option().ct1).show();
                        this.$(this.option().ct).css("width", "50%");
                        this.$(this.option().ct1).css("width", "50%");
                        this.updateYDEchart();
                    }
                }
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "外部质量问题分类统计情况");
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist;
                if (this.mCompType == Util.CompanyType.PDCY) {
                    tableAssist = JQGridAssistantFactory.createZtTable(name, this.mYdjdType);
                }
                else {
                    tableAssist = JQGridAssistantFactory.createFdwTable(name, this.mYdjdType);
                }
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                tableAssist.mergeColum(0);
                tableAssist.mergeTitle();
                tableAssist.mergeRow(0);
                this.$(name).jqGrid(tableAssist.decorate({
                    datatype: "local",
                    data: tableAssist.getData(this.mData.tjjg),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 1000,
                    viewrecords: true,
                    caption: "外部质量问题分类统计情况"
                }));
            };
            ShowView.prototype.updateYDEchart = function () {
                var title = "外部质量问题分类统计情况";
                var wtlb = [];
                for (var i = 0; i < this.mData.tjjg.length; ++i) {
                    wtlb.push(this.mData.tjjg[0]);
                }
                var legend = {
                    orient: 'vertical',
                    x: 'left',
                    data: wtlb
                };
                var tooltip = {
                    trigger: 'item'
                };
                var series = [{
                        name: "问题类别",
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: []
                    }];
                var series1 = [{
                        name: "问题类别",
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: []
                    }];
                if (this.mCompType == Util.CompanyType.PDCY) {
                    for (var i = 0; i < this.mData.tjjg.length; ++i) {
                        series[0].data.push({
                            name: wtlb[i],
                            value: this.toCtVal(this.mData.tjjg[7] + "")
                        });
                        series1[0].data.push({
                            name: wtlb[i],
                            value: this.toCtVal(this.mData.tjjg[8] + "")
                        });
                    }
                }
                else {
                    for (var i = 0; i < this.mData.tjjg.length; ++i) {
                        series[0].data.push({
                            name: wtlb[i],
                            value: this.toCtVal(this.mData.tjjg[1] + "")
                        });
                        series1[0].data.push({
                            name: wtlb[i],
                            value: this.toCtVal(this.mData.tjjg[2] + "")
                        });
                    }
                }
                var option = {
                    title: {
                        text: "当月",
                        x: 'center'
                    },
                    tooltip: tooltip,
                    legend: legend,
                    toolbox: {
                        show: true
                    },
                    calculable: true,
                    series: series
                };
                var option1 = {
                    title: {
                        text: "年度累计",
                        x: 'center'
                    },
                    tooltip: tooltip,
                    legend: legend,
                    toolbox: {
                        show: true
                    },
                    calculable: true,
                    series: series1
                };
                if (this.mData.tjjg.length != 0) {
                    echarts.init(this.$(this.option().ct)[0]).setOption(option);
                    echarts.init(this.$(this.option().ct1)[0]).setOption(option1);
                }
                else {
                    this.$(this.option().ct).hide();
                    this.$(this.option().ct1).hide();
                }
            };
            ShowView.prototype.updateJDEchart = function () {
                var dq = 0;
                var qn = 0;
                if (this.mCompType == Util.CompanyType.PDCY) {
                    for (var i = 0; i < this.mData.tjjg.length; ++i) {
                        dq += parseInt(this.toCtVal(this.mData.tjjg[7] + ""));
                        qn += parseInt(this.toCtVal(this.mData.tjjg[8] + ""));
                    }
                }
                else {
                    for (var i = 0; i < this.mData.tjjg.length; ++i) {
                        dq += parseInt(this.toCtVal(this.mData.tjjg[1] + ""));
                        qn += parseInt(this.toCtVal(this.mData.tjjg[2] + ""));
                    }
                }
                var title = "外部质量问题分类统计情况";
                var legend = ["当期", "去年同期"];
                var echart = this.option().ct;
                var series = [];
                var xData = ["内部质量问题分类统计"];
                var tooltip = {
                    trigger: 'axis'
                };
                var yAxis = [
                    {
                        type: 'value'
                    }
                ];
                series.push({
                    name: legend[0],
                    type: 'bar',
                    smooth: true,
                    // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data: [dq]
                });
                series.push({
                    name: legend[1],
                    type: 'bar',
                    smooth: true,
                    // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data: [qn]
                });
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
                            boundaryGap: true,
                            data: xData.length < 1 ? [0] : xData
                        }
                    ],
                    yAxis: yAxis,
                    series: series
                };
                echarts.init(this.$(echart)[0]).setOption(option);
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(nwbzlqk.ZlPluginView);
    })(pdwbzlwtfl = nwbzlqk.pdwbzlwtfl || (nwbzlqk.pdwbzlwtfl = {}));
})(nwbzlqk || (nwbzlqk = {}));
