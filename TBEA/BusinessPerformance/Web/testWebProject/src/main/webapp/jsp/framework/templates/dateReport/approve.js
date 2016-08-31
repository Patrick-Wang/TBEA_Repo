var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
///<reference path="../singleDateReport/entry.ts"/>
///<reference path="../singleDateReport/approve.ts"/>
var framework;
(function (framework) {
    var templates;
    (function (templates) {
        var dateReport;
        (function (dateReport) {
            function createInstance() {
                return new ApproveView();
            }
            dateReport.createInstance = createInstance;
            var ApproveView = (function (_super) {
                __extends(ApproveView, _super);
                function ApproveView() {
                    _super.apply(this, arguments);
                }
                ApproveView.prototype.onInitialize = function (opt) {
                    this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);
                    $("#" + opt.itemId + " select")
                        .multiselect({
                        multiple: false,
                        header: false,
                        minWidth: 100,
                        height: '100%',
                        // noneSelectedText: "请选择月份",
                        selectedList: 1
                    })
                        .css("padding", "2px 0 2px 4px")
                        .css("text-align", "left")
                        .css("font-size", "12px");
                    if (opt.itemNodes.length == 1) {
                        this.unitedSelector.hide();
                        $("#headertitle").text(opt.itemNodes[0].data.value + " " + $("#headertitle").text());
                    }
                    _super.prototype.onInitialize.call(this, opt);
                };
                ApproveView.prototype.getParams = function (date) {
                    return {
                        date: this.getDate(date),
                        item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id
                    };
                };
                ApproveView.prototype.update = function (date) {
                    var _this = this;
                    this.mAjaxUpdate.get(this.getParams(date))
                        .then(function (jsonData) {
                        _this.resp = jsonData;
                        _this.resp = jsonData;
                        if (jsonData.zt == 0) {
                            $("#approve").hide();
                            $("#unapprove").hide();
                        }
                        else if (jsonData.zt == 1) {
                            $("#approve").hide();
                            $("#unapprove").show();
                        }
                        else if (jsonData.zt == 2) {
                            $("#approve").show();
                            $("#unapprove").hide();
                        }
                        _this.updateTable();
                    });
                };
                ApproveView.prototype.approve = function (date) {
                    var _this = this;
                    this.mAjaxApprove.get($.extend(this.getParams(date), {
                        data: JSON.stringify(this.onLoadSubmitData())
                    }))
                        .then(function (resp) {
                        if (Util.ErrorCode.OK == resp.errorCode) {
                            _this.update(date);
                            Util.MessageBox.tip("审核 成功");
                        }
                        else {
                            Util.MessageBox.tip(resp.message);
                        }
                    });
                };
                ApproveView.prototype.unapprove = function (date) {
                    var _this = this;
                    this.mAjaxUnApprove.get($.extend(this.getParams(date), {
                        data: JSON.stringify(this.onLoadSubmitData())
                    }))
                        .then(function (resp) {
                        if (Util.ErrorCode.OK == resp.errorCode) {
                            _this.update(date);
                            Util.MessageBox.tip("反审核 成功");
                        }
                        else {
                            Util.MessageBox.tip(resp.message);
                        }
                    });
                };
                return ApproveView;
            })(framework.templates.singleDateReport.ApproveView);
            dateReport.ApproveView = ApproveView;
        })(dateReport = templates.dateReport || (templates.dateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
