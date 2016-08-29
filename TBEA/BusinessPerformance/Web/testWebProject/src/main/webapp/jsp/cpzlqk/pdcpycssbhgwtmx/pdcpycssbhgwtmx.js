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
var plugin;
(function (plugin) {
    plugin.pdcpycssbhgwtmx = framework.basic.endpoint.lastId();
    plugin.pdybysqfyswtmx = framework.basic.endpoint.lastId();
    plugin.pdybyspbcpwtmx = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var cpzlqk;
(function (cpzlqk) {
    var pdcpycssbhgwtmx;
    (function (pdcpycssbhgwtmx) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "单位", align: TextAlign.Center }),
                    Node.create({ name: "发生日期", align: TextAlign.Center }),
                    Node.create({ name: "产品类型", align: TextAlign.Center }),
                    Node.create({ name: "生产号", align: TextAlign.Center }),
                    Node.create({ name: "产品型号", align: TextAlign.Center }),
                    Node.create({ name: "试验不合格现象", align: TextAlign.Center }),
                    Node.create({ name: "不合格类别", align: TextAlign.Center }),
                    Node.create({ name: "原因分析", align: TextAlign.Center }),
                    Node.create({ name: "处理措施", align: TextAlign.Center }),
                    Node.create({ name: "处理结果", align: TextAlign.Center }),
                    Node.create({ name: "责任类别", align: TextAlign.Center })
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../pdcpycssbhgwtmx/update.do", false);
                this.mCommentGet = new Util.Ajax("../report/zlfxUpdate.do", false);
                this.mCommentSubmit = new Util.Ajax("../report/zlfxSubmit.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.pdcpycssbhgwtmx;
            };
            ShowView.prototype.isSupported = function (compType) {
                return compType == Util.CompanyType.SBZTFGS || compType == Util.CompanyType.HBDQFGS
                    || compType == Util.CompanyType.XBZTGS || compType == Util.CompanyType.TBGS
                    || compType == Util.CompanyType.XBXBGS || compType == Util.CompanyType.PDCY;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../pdcpycssbhgwtmx/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType,
                    ydjd: this.mYdjdType,
                    all: this.mCompType == Util.CompanyType.PDCY
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
                    if (jsonData.deny == "deny") {
                        framework.router
                            .fromEp(_this)
                            .to(framework.basic.endpoint.FRAME_ID)
                            .send(cpzlqk.Event.ZLFE_COMMENT_DENY);
                    }
                    else {
                        framework.router
                            .fromEp(_this)
                            .to(framework.basic.endpoint.FRAME_ID)
                            .send(cpzlqk.Event.ZLFE_COMMENT_UPDATED, jsonData.comment);
                    }
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
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ShowView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case cpzlqk.Event.ZLFE_IS_COMPANY_SUPPORTED:
                        return true;
                    case cpzlqk.Event.ZLFE_SAVE_COMMENT:
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
                            Util.MessageBox.tip("保存成功", undefined, 1000);
                        });
                        break;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "产品一次送试不合格问题明细");
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var pagername = name + "pager";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                tableAssist.mergeColum(0);
                tableAssist.mergeTitle();
                tableAssist.mergeRow(0);
                this.$(name).jqGrid(tableAssist.decorate({
                    datatype: "local",
                    data: tableAssist.getData(this.mData),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 20,
                    viewrecords: true,
                    pager: '#' + pagername
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(cpzlqk.ZlPluginView);
    })(pdcpycssbhgwtmx = cpzlqk.pdcpycssbhgwtmx || (cpzlqk.pdcpycssbhgwtmx = {}));
})(cpzlqk || (cpzlqk = {}));
