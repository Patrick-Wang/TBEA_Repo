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
    var xlbhgcpmx;
    (function (xlbhgcpmx) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "单位", align: TextAlign.Center }),
                    Node.create({ name: "时间", align: TextAlign.Center, }),
                    Node.create({ name: "产品类型", align: TextAlign.Center, }),
                    Node.create({ name: "生产号", align: TextAlign.Center, }),
                    Node.create({ name: "产品型号", align: TextAlign.Center, }),
                    Node.create({ name: "不合格数量", align: TextAlign.Center, }),
                    Node.create({ name: "试验不合格现象", align: TextAlign.Center, }),
                    Node.create({ name: "不合格类别", align: TextAlign.Center, }),
                    Node.create({ name: "原因分析", align: TextAlign.Center, }),
                    Node.create({ name: "处理措施", align: TextAlign.Center, }),
                    Node.create({ name: "处理结果", align: TextAlign.Center, }),
                    Node.create({ name: "责任类别", align: TextAlign.Center, })
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjax = new Util.Ajax("/BusinessManagement/xlbhgcpmx/update.do", false);
                _this.mAjaxStatus = new Util.Ajax("/BusinessManagement/xlbhgcpmx/updateStatus.do", false);
                return _this;
            }
            ShowView.prototype.isSupported = function (compType) {
                return compType == Util.CompanyType.LLGS || compType == Util.CompanyType.DLGS
                    || compType == Util.CompanyType.XLC || compType == Util.CompanyType.XLCY;
            };
            ShowView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case cpzlqk.Event.ZLFE_IS_COMPANY_SUPPORTED:
                        return true;
                    case cpzlqk.Event.ZLFE_IS_YDJD_SUPPORTED:
                        return false;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.getId = function () {
                return plugin.xlbhgcpmx;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "/BusinessManagement/xlbhgcpmx/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType,
                    all: this.mCompSize > 1
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                framework.router.to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_ADJUST_HEADER);
                //this.$(this.option().ct).css("height", "300px");
                //this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                //this.updateEchart(this.mFinalData);
            };
            ShowView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mCompType = compType;
                //this.mCommentGet.get({condition:Util.Ajax.toUrlParam({
                //    url : window.location.href + this.mAjax.baseUrl(),
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
                //        all: this.mCompSize > 1
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
                        _this.mData = cpzlqkResp.tjjg;
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
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "不合格产品明细");
            };
            ShowView.prototype.createJqassist = function () {
                var pagername = this.jqgridName() + "pager";
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table><div id='" + pagername + "'></div>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
                this.tableAssist.mergeColum(0);
                this.tableAssist.mergeTitle();
                this.tableAssist.mergeRow(0);
                return this.tableAssist;
            };
            ShowView.prototype.updateTable = function () {
                this.createJqassist();
                this.tableAssist.create({
                    data: this.mData,
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
                this.adjustSize();
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
                    Util.Toast.success("提交成功", undefined);
                });
            };
            ShowView.ins = new ShowView();
            return ShowView;
        }(cpzlqk.ZlPluginView));
    })(xlbhgcpmx = cpzlqk.xlbhgcpmx || (cpzlqk.xlbhgcpmx = {}));
})(cpzlqk || (cpzlqk = {}));
