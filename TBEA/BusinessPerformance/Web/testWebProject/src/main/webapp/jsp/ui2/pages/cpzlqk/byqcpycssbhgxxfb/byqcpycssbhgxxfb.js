/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqk.ts"/>
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
var cpzlqk;
(function (cpzlqk) {
    var byqcpycssbhgxxfb;
    (function (byqcpycssbhgxxfb) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = /** @class */ (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, title, type) {
                var nodes;
                if (cpzlqk.YDJDType.YD == type) {
                    nodes = [Node.create({ name: "单位", align: TextAlign.Center })];
                }
                else {
                    nodes = [
                        Node.create({ name: "单位", align: TextAlign.Center }),
                        Node.create({ name: "单位", align: TextAlign.Center })
                    ];
                }
                for (var i in title) {
                    nodes.push(Node.create({ name: title[i] }));
                }
                nodes.push(Node.create({ name: "合计" }));
                return new JQTable.JQGridAssistant(nodes, gridName);
            };
            return JQGridAssistantFactory;
        }());
        var ShowView = /** @class */ (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjax = new Util.Ajax("/BusinessManagement/byqcpycssbhgxxfb/update.do", false);
                _this.mAjaxStatus = new Util.Ajax("/BusinessManagement/byqcpycssbhgwtmx/updateStatus.do", false);
                return _this;
            }
            ShowView.prototype.getId = function () {
                return plugin.byqcpycssbhgxxfb;
            };
            ShowView.prototype.isSupported = function (compType) {
                return compType == Util.CompanyType.SBGS || compType == Util.CompanyType.HBGS
                    || compType == Util.CompanyType.XBC || compType == Util.CompanyType.BYQCY;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "/BusinessManagement/byqcpycssbhgxxfb/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType,
                    ydjd: this.mYdjdType,
                    all: this.mCompType == Util.CompanyType.BYQCY
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mCompType = compType;
                var comment;
                var bhgxxfbResp;
                var complete = function (jsonData) {
                    if (undefined != jsonData.result) {
                        bhgxxfbResp = jsonData;
                    }
                    else {
                        comment = jsonData;
                    }
                    if (comment != undefined && bhgxxfbResp != undefined) {
                        _this.mData = bhgxxfbResp;
                        _this.refresh();
                        if (pageType == cpzlqk.PageType.APPROVE) {
                            framework.router
                                .fromEp(_this)
                                .to(framework.basic.endpoint.FRAME_ID)
                                .send(cpzlqk.Event.ZLFE_APPROVEAUTH_UPDATED);
                        }
                        framework.router
                            .fromEp(_this)
                            .to(framework.basic.endpoint.FRAME_ID)
                            .send(cpzlqk.Event.ZLFE_COMMENT_UPDATED, {
                            comment: comment,
                            zt: bhgxxfbResp.zt
                        });
                    }
                };
                this.mAjax.get({
                    date: date,
                    companyId: compType,
                    ydjd: this.mYdjdType,
                    pageType: pageType
                }).then(complete);
                this.mCommentGet.get({
                    condition: Util.Ajax.toUrlParam({
                        url: this.mAjax.baseUrl(),
                        date: date,
                        companyId: compType,
                        ydjd: this.mYdjdType
                    }),
                    compId: compType
                }).then(complete);
                //this.mCommentGet.get({condition:Util.Ajax.toUrlParam({
                //    url : this.mAjax.baseUrl(),
                //    date: date,
                //    companyId:compType,
                //    ydjd:this.mYdjdType
                //}),compId:compType}).then((jsonData:any)=>{
                //    framework.router
                //        .fromEp(this)
                //        .to(framework.basic.endpoint.FRAME_ID)
                //        .send(Event.ZLFE_COMMENT_UPDATED, jsonData);
                //});
                //
                //this.mAjax.get({
                //        date: date,
                //        companyId:compType,
                //        ydjd:this.mYdjdType,
                //        all: this.mCompType == Util.CompanyType.BYQCY,
                //        pageType:pageType
                //    })
                //    .then((jsonData:any) => {
                //        this.mData = jsonData;
                //        this.refresh();
                //    });
            };
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
                this.$(this.option().ctarea).show();
                this.adjustSize();
            };
            ShowView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case cpzlqk.Event.ZLFE_IS_COMPANY_SUPPORTED:
                        return true;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "产品送试不合格现象分布");
            };
            ShowView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                if (this.mYdjdType == cpzlqk.YDJDType.YD) {
                    if (this.mData.waveItems.length == 0) {
                        this.$(this.option().ctarea).hide();
                    }
                    else {
                        this.$(this.option().ct1).parent().hide();
                        this.$(this.option().ct).parent().css("width", "100%");
                        this.updateYDEchart();
                    }
                }
                else {
                    if (this.mData.result.length == 0) {
                        this.$(this.option().ctarea).hide();
                    }
                    else {
                        this.$(this.option().ct).parent().show();
                        this.$(this.option().ct1).parent().show();
                        this.$(this.option().ct).parent().css("width", "50%");
                        this.$(this.option().ct1).parent().css("width", "50%");
                        this.updateJDEchart();
                    }
                }
                framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_ADJUST_HEADER);
            };
            ShowView.prototype.createJqassist = function () {
                var pagername = this.jqgridName() + "pager";
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table><div id='" + pagername + "'></div>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mData.bhglbs, this.mYdjdType);
                this.tableAssist.mergeColum(0);
                this.tableAssist.mergeTitle(0);
                this.tableAssist.mergeRow();
                return this.tableAssist;
            };
            ShowView.prototype.updateTable = function () {
                this.createJqassist();
                var data = [];
                if (this.mYdjdType == cpzlqk.YDJDType.YD) {
                    data = this.mData.result;
                }
                else {
                    var i = 0;
                    for (; i < this.mData.result.length; ++i) {
                        data.push(["当期"].concat(this.mData.result[i]));
                        if (this.mData.result[i][0].replace(/\s/g, "") == "合计") {
                            break;
                        }
                    }
                    ++i;
                    for (; i < this.mData.result.length; ++i) {
                        data.push(["去年同期"].concat(this.mData.result[i]));
                    }
                }
                this.tableAssist.create({
                    data: data,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 10000,
                    autoScroll: true
                });
            };
            ShowView.prototype.updateYDEchart = function () {
                var title = "按产单位计结果";
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
                for (var i in this.mData.waveItems) {
                    legend.push(this.mData.waveItems[i].name);
                    series.push({
                        name: this.mData.waveItems[i].name,
                        type: 'line',
                        smooth: true,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: this.mData.waveItems[i].data
                    });
                }
                for (var i = 1; i <= 12; ++i) {
                    xData.push(i + "月");
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
                    yAxis: yAxis,
                    series: series
                };
                echarts.init(this.$(this.option().ct)[0]).setOption(option);
            };
            ShowView.prototype.toCtVal = function (val) {
                var index = val.lastIndexOf('%');
                if (index >= 0) {
                    return val.substring(0, index);
                }
                return val == '--' ? 0 : val;
            };
            ShowView.prototype.updateJDEchart = function () {
                var legend = {
                    orient: 'vertical',
                    x: 'left',
                    data: this.mData.bhglbs
                };
                var tooltip = {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                };
                var series = [{
                        name: "不合格类型",
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: []
                    }];
                var i = 0;
                for (; i < this.mData.result.length; ++i) {
                    if (this.mData.result[i][0].replace(/\s/g, "") == "合计") {
                        for (var j = 0; j < this.mData.bhglbs.length; ++j) {
                            series[0].data.push({
                                name: this.mData.bhglbs[j],
                                value: this.mData.result[i][j + 1]
                            });
                        }
                        break;
                    }
                }
                var option = {
                    title: {
                        text: "当期",
                        x: 'center'
                    },
                    tooltip: tooltip,
                    legend: legend,
                    toolbox: {
                        show: true,
                    },
                    calculable: true,
                    series: series
                };
                series = [{
                        name: "不合格类型",
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: []
                    }];
                ++i;
                for (; i < this.mData.result.length; ++i) {
                    if (this.mData.result[i][0].replace(/\s/g, "") == "合计") {
                        for (var j = 0; j < this.mData.bhglbs.length; ++j) {
                            series[0].data.push({
                                name: this.mData.bhglbs[j],
                                value: this.mData.result[i][j + 1]
                            });
                        }
                        break;
                    }
                }
                if (series[0].data.length != 0) {
                    var option1 = {
                        title: {
                            text: "去年同期",
                            x: 'center'
                        },
                        tooltip: tooltip,
                        legend: legend,
                        toolbox: {
                            show: true,
                        },
                        calculable: true,
                        series: series
                    };
                    echarts.init(this.$(this.option().ct1)[0]).setOption(option1);
                }
                else {
                    this.$(this.option().ct).css("width", "100%");
                    this.$(this.option().ct1).hide();
                }
                echarts.init(this.$(this.option().ct)[0]).setOption(option);
            };
            ShowView.prototype.onSaveComment = function (comment) {
                var param = {
                    condition: Util.Ajax.toUrlParam({
                        url: this.mAjax.baseUrl(),
                        date: this.mDt,
                        companyId: this.mCompType,
                        ydjd: this.mYdjdType
                    }),
                    comment: comment
                };
                this.mAjaxStatus.get({
                    date: this.mDt,
                    companyId: this.mCompType,
                    zt: Util.IndiStatus.SUBMITTED
                }).then(function () {
                });
                this.mCommentSubmit.get({
                    data: JSON.stringify([[param.condition, param.comment]])
                }).then(function (jsonData) {
                    Util.Toast.tip("提交成功", undefined);
                });
            };
            ShowView.ins = new ShowView();
            return ShowView;
        }(cpzlqk.ZlPluginView));
    })(byqcpycssbhgxxfb = cpzlqk.byqcpycssbhgxxfb || (cpzlqk.byqcpycssbhgxxfb = {}));
})(cpzlqk || (cpzlqk = {}));
