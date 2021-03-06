var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../messageBox.ts"/>
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqkApprove.ts"/>
var pluginApprove;
(function (pluginApprove) {
    pluginApprove.xlbhgcpmx = framework.basic.endpoint.lastId();
})(pluginApprove || (pluginApprove = {}));
var cpzlqk;
(function (cpzlqk) {
    var xlbhgcpmxApprove;
    (function (xlbhgcpmxApprove) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "产品类型", align: TextAlign.Center }),
                    Node.create({ name: "生产号", align: TextAlign.Center }),
                    Node.create({ name: "产品型号", align: TextAlign.Center }),
                    Node.create({ name: "不合格数量", align: TextAlign.Center, isReadOnly: false }),
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
        var ApproveView = (function (_super) {
            __extends(ApproveView, _super);
            function ApproveView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("../xlbhgcpmx/approve/update.do", false);
                this.mAjaxApprove = new Util.Ajax("../xlbhgcpmx/approve/approve.do", false);
                this.mAjaxUnapprove = new Util.Ajax("../xlbhgcpmx/approve/unapprove.do", false);
            }
            ApproveView.prototype.getId = function () {
                return pluginApprove.xlbhgcpmx;
            };
            ApproveView.prototype.isSupported = function (compType) {
                if (compType == Util.CompanyType.LLGS ||
                    compType == Util.CompanyType.XLC ||
                    compType == Util.CompanyType.DLGS) {
                    return true;
                }
                return false;
            };
            ApproveView.prototype.option = function () {
                return this.mOpt;
            };
            ApproveView.prototype.pluginApprove = function (dt, compType) {
                var _this = this;
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([allData[i][0]]);
                }
                this.mAjaxApprove.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("审核 成功", function () {
                            _this.pluginUpdate(dt, compType);
                        });
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            ApproveView.prototype.pluginUnapprove = function (dt, compType) {
                var _this = this;
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([allData[i][0]]);
                }
                this.mAjaxUnapprove.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("反审核 成功", function () {
                            _this.pluginUpdate(dt, compType);
                        });
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            ApproveView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mCompType = compType;
                this.mAjaxUpdate.get({
                    date: date,
                    companyId: compType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            ApproveView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                if (this.mData.status == Util.ZBStatus.APPROVED) {
                    this.$(this.option().tbarea).show();
                    this.$(this.option().tips).text("数据已审核");
                    framework.router
                        .fromEp(this)
                        .to(framework.basic.endpoint.FRAME_ID)
                        .send(framework.basic.FrameEvent.FE_APPROVED);
                    this.updateTable();
                }
                else if (this.mData.status == Util.ZBStatus.SUBMITTED) {
                    this.$(this.option().tbarea).show();
                    this.$(this.option().tips).text("数据未审核");
                    framework.router
                        .fromEp(this)
                        .to(framework.basic.endpoint.FRAME_ID)
                        .send(framework.basic.FrameEvent.FE_SUBMITTED);
                    this.updateTable();
                }
                else {
                    this.$(this.option().tbarea).hide();
                    this.$(this.option().tips).text("数据尚未提交");
                    framework.router
                        .fromEp(this)
                        .to(framework.basic.endpoint.FRAME_ID)
                        .send(framework.basic.FrameEvent.FE_NOT_SUBMITTED);
                }
            };
            ApproveView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "不合格产品明细");
            };
            ApproveView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, false);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                var jqTable = this.$(name);
                jqTable.jqGrid(this.mTableAssist.decorate({
                    datatype: "local",
                    data: this.mTableAssist.getDataWithId(this.mData.tjjg),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //assistEditable:true,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    //editurl: 'clientArray',
                    cellEdit: true,
                    // height: data.length > 25 ? 550 : '100%',
                    // width: titles.length * 200,
                    rowNum: 1000,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    //pager: '#' + pagername,
                    viewrecords: true
                }));
            };
            ApproveView.ins = new ApproveView();
            return ApproveView;
        })(cpzlqk.ZlApprovePluginView);
    })(xlbhgcpmxApprove = cpzlqk.xlbhgcpmxApprove || (cpzlqk.xlbhgcpmxApprove = {}));
})(cpzlqk || (cpzlqk = {}));
