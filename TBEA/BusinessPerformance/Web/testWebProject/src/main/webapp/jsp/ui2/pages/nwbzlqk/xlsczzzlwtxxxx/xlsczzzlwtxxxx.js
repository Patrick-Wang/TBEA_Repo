/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../nwbzlqkdef.ts"/>
///<reference path="../nwbzlqk.ts"/>
///<reference path="../../jqgrid/jqgrid.d.ts"/>
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
var plugin;
(function (plugin) {
    plugin.xlsczzzlwtxxxx = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var nwbzlqk;
(function (nwbzlqk) {
    var xlsczzzlwtxxxx;
    (function (xlsczzzlwtxxxx) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = /** @class */ (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createZtTable = function (gridName, ydjd) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "单位", align: TextAlign.Center }),
                    Node.create({ name: "项目公司", align: TextAlign.Center }),
                    Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "当月" : "季度" })
                        .append(Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "月度" : "季度" }))
                        .append(Node.create({ name: "年度累计" })),
                    Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "去年同期" : "去年同期" })
                        .append(Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "月度" : "季度" }))
                        .append(Node.create({ name: "年度累计" }))
                ], gridName);
            };
            JQGridAssistantFactory.createFdwTable = function (gridName, ydjd) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "单位", align: TextAlign.Center }),
                    Node.create({ name: "项目公司", align: TextAlign.Center }),
                    Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "当月" : "当期" })
                        .append(Node.create({ name: "内部问题" }))
                        .append(Node.create({ name: "外部问题" }))
                        .append(Node.create({ name: "合计" })),
                    Node.create({ name: ydjd == nwbzlqk.YDJDType.YD ? "年度累计" : "去年同期" })
                        .append(Node.create({ name: "内部问题" }))
                        .append(Node.create({ name: "外部问题" }))
                        .append(Node.create({ name: "合计" }))
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var ShowView = /** @class */ (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjax = new Util.Ajax("/BusinessManagement/xlsczzzlwtxxxx/update.do", false);
                return _this;
                //
                //private updateTable():void {
                //    var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                //    var tableAssist:JQTable.JQGridAssistant;
                //    if (this.mCompType == Util.CompanyType.XLCY){
                //        tableAssist = JQGridAssistantFactory.createZtTable(name, this.mYdjdType);
                //    }else{
                //        tableAssist = JQGridAssistantFactory.createFdwTable(name, this.mYdjdType);
                //    }
                //
                //    let pagername = name + "pager"
                //    var parent = this.$(this.option().tb);
                //    parent.empty();
                //    parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                //    tableAssist.mergeTitle();
                //    this.$(name).jqGrid(
                //        tableAssist.decorate({
                //            datatype: "local",
                //            data: tableAssist.getData(this.mData.tjjg),
                //            multiselect: false,
                //            drag: false,
                //            resize: false,
                //            height: this.mData.tjjg.length > 20 ? 20 * 22 : '100%',
                //            width: 1200,
                //            shrinkToFit: true,
                //            autoScroll: true,
                //            rowNum: this.mData.tjjg.length + 10,
                //            viewrecords : true,
                //            pager:'#' + pagername,
                //        }));
                //}
            }
            ShowView.prototype.getId = function () {
                return plugin.xlsczzzlwtxxxx;
            };
            ShowView.prototype.isSupported = function (compType) {
                return compType == Util.CompanyType.LLGS || compType == Util.CompanyType.XLC
                    || compType == Util.CompanyType.DLGS || compType == Util.CompanyType.XLCY;
            };
            ShowView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case nwbzlqk.Event.ZLFE_IS_COMPANY_SUPPORTED:
                        return true;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "/BusinessManagement/xlsczzzlwtxxxx/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType,
                    ydjd: this.mYdjdType
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
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
                this.mCommentSubmit.get({
                    data: JSON.stringify([[param.condition, param.comment]])
                }).then(function (jsonData) {
                    Util.Toast.success("提交成功", undefined);
                });
            };
            ShowView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mCompType = compType;
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
                        if (pageType == nwbzlqk.PageType.APPROVE) {
                            framework.router
                                .fromEp(_this)
                                .to(framework.basic.endpoint.FRAME_ID)
                                .send(nwbzlqk.Event.ZLFE_APPROVEAUTH_UPDATED);
                        }
                        framework.router
                            .fromEp(_this)
                            .to(framework.basic.endpoint.FRAME_ID)
                            .send(nwbzlqk.Event.ZLFE_COMMENT_UPDATED, {
                            comment: comment,
                            zt: cpzlqkResp.zt
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
            };
            ShowView.prototype.toCtVal = function (val) {
                var index = val.lastIndexOf('%');
                if (index >= 0) {
                    return val.substring(0, index);
                }
                return val == '--' ? 0 : val;
            };
            //private updateEchart():void {
            //    let title = "对应项目公司生产制造质量问题数量";
            //    let legend:Array<string> = [];
            //    let echart = this.option().ct;
            //
            //    let series = [];
            //    var xData:string[] = [];
            //    let tooltip : any = {
            //        trigger: 'axis'
            //    };
            //    let yAxis : any = [
            //        {
            //            type: 'value'
            //        }
            //    ];
            //    if (this.mYdjdType == YDJDType.YD){
            //        for (let i in this.mData.waveItems){
            //            legend.push(this.mData.waveItems[i].name);
            //
            //            let data = [];
            //            for (let j = 0; j < this.mData.waveItems[i].data.length; ++j){
            //                data.push((parseFloat("" + this.mData.waveItems[i].data[j]) * 100).toFixed(1));
            //            }
            //
            //            series.push({
            //                name: this.mData.waveItems[i].name,
            //                type: 'line',
            //                smooth: true,
            //                // itemStyle: {normal: {areaStyle: {type: 'default'}}},
            //                data: data
            //            });
            //        }
            //        for (let i = 0; i < 12; ++i){
            //            xData.push((i + 1) + "月");
            //        }
            //    }else{
            //        let dy = [];
            //        let qntq = [];
            //
            //        if (this.mCompType == Util.CompanyType.XLCY){
            //            for (let i = 0; i < this.mData.tjjg.length; ++i){
            //                if (this.mData.tjjg[i][0].replace(/\s/g, "") == "合计"){
            //                    xData.push("内部质量问题");
            //                    dy.push(this.toCtVal(this.mData.tjjg[i][1]));
            //                    qntq.push(this.toCtVal(this.mData.tjjg[i][4]));
            //                    xData.push("外部质量问题");
            //                    dy.push(this.toCtVal(this.mData.tjjg[i][2]));
            //                    qntq.push(this.toCtVal(this.mData.tjjg[i][5]));
            //                }
            //            }
            //        }else{
            //            xData.push( "内部质量问题");
            //            xData.push( "外部质量问题");
            //            dy.push(this.toCtVal(this.mData.tjjg[0][1]));
            //            dy.push(this.toCtVal(this.mData.tjjg[0][2]));
            //            dy.push(this.toCtVal(this.mData.tjjg[1][1]));
            //            qntq.push(this.toCtVal(this.mData.tjjg[1][2]));
            //        }
            //
            //        legend = ["当月", "去年同期"];
            //        series.push({
            //            name: legend[0],
            //            type: 'bar',
            //            smooth: true,
            //            // itemStyle: {normal: {areaStyle: {type: 'default'}}},
            //            data: dy
            //        });
            //        series.push({
            //            name: legend[1],
            //            type: 'bar',
            //            smooth: true,
            //            // itemStyle: {normal: {areaStyle: {type: 'default'}}},
            //            data: qntq
            //        });
            //    }
            //
            //
            //    var option = {
            //        title: {
            //            text: title
            //        },
            //        tooltip: tooltip,
            //        legend: {
            //            data: legend
            //        },
            //        toolbox: {
            //            show: true,
            //        },
            //        calculable: false,
            //        xAxis: [
            //            {
            //                type: 'category',
            //                boundaryGap: this.mYdjdType == YDJDType.YD ? false : true,
            //                data: xData.length < 1 ? [0] : xData
            //            }
            //        ],
            //        yAxis: yAxis,
            //        series: series
            //    };
            //
            //    echarts.init(this.$(echart)[0]).setOption(option);
            //
            //}
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
                this.adjustSize();
                //this.$(this.option().ctarea).show();
                //this.updateEchart();
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "对应项目公司生产制造质量问题数量");
            };
            ShowView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                //this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                //this.updateEchart();
            };
            ShowView.prototype.createJqassist = function () {
                var pagername = this.jqgridName() + "pager";
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table><div id='" + pagername + "'></div>");
                if (this.mCompType == Util.CompanyType.XLCY) {
                    this.tableAssist = JQGridAssistantFactory.createZtTable(this.jqgridName(), this.mYdjdType);
                }
                else {
                    this.tableAssist = JQGridAssistantFactory.createFdwTable(this.jqgridName(), this.mYdjdType);
                }
                this.tableAssist.mergeTitle();
                return this.tableAssist;
            };
            ShowView.prototype.updateTable = function () {
                this.createJqassist();
                this.tableAssist.create({
                    data: this.mData.tjjg,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 15,
                    autoScroll: true,
                    pager: '#' + this.jqgridName() + "pager",
                });
            };
            ShowView.ins = new ShowView();
            return ShowView;
        }(nwbzlqk.ZlPluginView));
    })(xlsczzzlwtxxxx = nwbzlqk.xlsczzzlwtxxxx || (nwbzlqk.xlsczzzlwtxxxx = {}));
})(nwbzlqk || (nwbzlqk = {}));
