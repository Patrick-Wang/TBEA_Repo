/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqk.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var cpzlqk;
(function (cpzlqk) {
    var xlacptjjg;
    (function (xlacptjjg) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, ydjd) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "考核项目", align: TextAlign.Center }),
                    Node.create({ name: "考核项目", align: TextAlign.Center }),
                    Node.create({ name: ydjd == cpzlqk.YDJDType.YD ? "当月" : "当季度" })
                        .append(Node.create({ name: "不合格数" }))
                        .append(Node.create({ name: "总数" }))
                        .append(Node.create({ name: "合格率%" })),
                    Node.create({ name: ydjd == cpzlqk.YDJDType.YD ? "年度累计" : "去年同期" })
                        .append(Node.create({ name: "不合格数" }))
                        .append(Node.create({ name: "总数" }))
                        .append(Node.create({ name: "合格率%" }))
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../xlacptjjg/update.do", false);
                this.mAjaxStatus = new Util.Ajax("../xlacptjjg/updateStatus.do", false);
            }
            ShowView.prototype.isSupported = function (compType) {
                return compType == Util.CompanyType.LLGS || compType == Util.CompanyType.DLGS
                    || compType == Util.CompanyType.XLC || compType == Util.CompanyType.XLCY;
            };
            ShowView.prototype.getId = function () {
                return plugin.xlacptjjg;
            };
            ShowView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case cpzlqk.Event.ZLFE_IS_COMPANY_SUPPORTED:
                        return true;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../xlacptjjg/export.do?" + Util.Ajax.toUrlParam({
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
                //        ydjd:this.mYdjdType
                //    })
                //    .then((jsonData:any) => {
                //        this.mData = jsonData;
                //        this.refresh();
                //    });
                var comment;
                var cpzlqkResp;
                var complete = function (jsonData) {
                    if (undefined != jsonData.tjjg) {
                        cpzlqkResp = jsonData;
                    }
                    else {
                        comment = jsonData;
                    }
                    if (comment != undefined && cpzlqkResp != undefined) {
                        _this.mData = cpzlqkResp;
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
                            zt: cpzlqkResp.zt });
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
                        ydjd: this.mYdjdType }),
                    compId: compType
                }).then(complete);
            };
            ShowView.prototype.toCtVal = function (val) {
                var index = val.lastIndexOf('%');
                if (index >= 0) {
                    return val.substring(0, index);
                }
                return val == '--' ? 0 : val;
            };
            ShowView.prototype.splitLongString = function (val) {
                var alphaCount = 0;
                var tmp;
                for (var i = 0; i < val.length; ++i) {
                    tmp = val.substr(i, 1);
                    if (tmp.match(/[0-9a-zA-Z\/]/g)) {
                        ++alphaCount;
                    }
                    else {
                        alphaCount += 2;
                    }
                    if (alphaCount >= 6) {
                        return val.substr(0, i + 1) + "...";
                    }
                }
                return val;
            };
            ShowView.prototype.findTotal = function (valShort) {
                for (var i = 0; i < this.mData.tjjg.length; ++i) {
                    if (this.mData.tjjg[i][1].indexOf(valShort.replace(/\.\.\./g, "")) >= 0) {
                        return this.mData.tjjg[i][1];
                    }
                }
                return valShort;
            };
            ShowView.prototype.updateEchart = function () {
                var _this = this;
                var title = "";
                var legend = [];
                var echart = this.option().ct;
                var series = [];
                var xData = [];
                var tooltip = {
                    trigger: 'axis',
                    formatter: function (params) {
                        var ret = params[0][1];
                        for (var i = 0; i < params.length; ++i) {
                            ret += "<br/>" + params[i][0] + ' : ' + (params[i][2] * 100.0).toFixed(2) + "%";
                        }
                        return ret;
                    }
                };
                var yAxis = [
                    {
                        type: 'value'
                    }
                ];
                if (this.mYdjdType == cpzlqk.YDJDType.YD) {
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
                    for (var i = 0; i < 12; ++i) {
                        xData.push((i + 1) + "月");
                    }
                }
                else {
                    var dy = [];
                    var qntq = [];
                    for (var i = 0; i < this.mData.tjjg.length; ++i) {
                        xData.push(this.splitLongString(this.mData.tjjg[i][1]));
                        dy.push(this.toCtVal(this.mData.tjjg[i][4]));
                        qntq.push(this.toCtVal(this.mData.tjjg[i][7]));
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
                    yAxis[0].axisLabel = {
                        formatter: '{value} %'
                    };
                    tooltip.formatter = function (params) {
                        var ret = _this.findTotal(params[0][1]);
                        for (var i = 0; i < params.length; ++i) {
                            ret += "<br/>" + params[i][0] + ' : ' + params[i][2] + "%";
                        }
                        return ret;
                    };
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
                            boundaryGap: this.mYdjdType == cpzlqk.YDJDType.YD ? false : true,
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
                    .send(framework.basic.FrameEvent.FE_REGISTER, "按产品类型统计结果");
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist = JQGridAssistantFactory.createTable(name, this.mYdjdType);
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
                    viewrecords: true
                }));
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
                    Util.MessageBox.tip("提交成功", undefined);
                });
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(cpzlqk.ZlPluginView);
    })(xlacptjjg = cpzlqk.xlacptjjg || (cpzlqk.xlacptjjg = {}));
})(cpzlqk || (cpzlqk = {}));
