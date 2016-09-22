var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
var framework;
(function (framework) {
    var templates;
    (function (templates) {
        var singleDateReport;
        (function (singleDateReport) {
            var BasicEndpoint = framework.basic.BasicEndpoint;
            var FrameEvent = framework.basic.FrameEvent;
            function createInstance() {
                return new ApproveView();
            }
            singleDateReport.createInstance = createInstance;
            var ApproveView = (function (_super) {
                __extends(ApproveView, _super);
                function ApproveView() {
                    _super.apply(this, arguments);
                }
                ApproveView.prototype.getId = function () {
                    return framework.basic.endpoint.FRAME_ID;
                };
                ApproveView.prototype.onInitialize = function (opt) {
                    this.opt = opt;
                    this.mAjaxUpdate = new Util.Ajax(opt.updateUrl, false);
                    this.mAjaxApprove = new Util.Ajax(opt.approveUrl, false);
                    this.mAjaxUnApprove = new Util.Ajax(opt.unapproveUrl, false);
                    if (opt.date == undefined) {
                        $("#" + opt.dtId).hide();
                        this.update(({}));
                    }
                    else {
                        this.dateSelect = new Util.DateSelectorProxy(opt.dtId, {
                            year: opt.date.year - 3,
                            month: opt.date.month,
                            day: opt.date.day
                        }, opt.date, opt.date);
                        this.update(this.dateSelect.getDate());
                    }
                };
                ApproveView.prototype.onEvent = function (e) {
                    switch (e.id) {
                        case FrameEvent.FE_UPDATE:
                            if (this.dateSelect == undefined) {
                                return this.update(({}));
                            }
                            else {
                                return this.update(this.dateSelect.getDate());
                            }
                        case FrameEvent.FE_APPROVE:
                            if (this.dateSelect == undefined) {
                                return this.approve(({}));
                            }
                            else {
                                return this.approve(this.dateSelect.getDate());
                            }
                        case FrameEvent.FE_UNAPPROVE:
                            if (this.dateSelect == undefined) {
                                return this.unapprove(({}));
                            }
                            else {
                                return this.unapprove(this.dateSelect.getDate());
                            }
                    }
                    return _super.prototype.onEvent.call(this, e);
                };
                ApproveView.prototype.getDate = function (date) {
                    return "" + (date.year + "-" + (date.month == undefined ? 1 : date.month) + "-" + (date.day == undefined ? 1 : date.day));
                };
                ApproveView.prototype.update = function (date) {
                    var _this = this;
                    this.mAjaxUpdate.get({
                        date: this.getDate(date)
                    })
                        .then(function (jsonData) {
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
                ApproveView.prototype.updateTable = function () {
                    var name = this.opt.host + "_jqgrid_uiframe";
                    var pagername = name + "pager";
                    this.mTableAssist = Util.createTable(name, this.resp);
                    var parent = $("#" + this.opt.host);
                    parent.empty();
                    parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                    var jqTable = $("#" + name);
                    var opt = {
                        datatype: "local",
                        data: this.mTableAssist.getDataWithId(this.resp.data),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        assistEditable: true,
                        //autowidth : false,
                        cellsubmit: 'clientArray',
                        //editurl: 'clientArray',
                        cellEdit: false,
                        // height: data.length > 25 ? 550 : '100%',
                        // width: titles.length * 200,
                        rowNum: 1000,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                    };
                    //if (this.resp.pager == 'none'){
                    //    opt.pager = undefined;
                    //}
                    jqTable.jqGrid(this.mTableAssist.decorate(opt));
                };
                ApproveView.prototype.onLoadSubmitData = function () {
                    return this.mTableAssist.getAllData();
                };
                ApproveView.prototype.approve = function (date) {
                    var _this = this;
                    this.mAjaxApprove.get({
                        data: JSON.stringify(this.onLoadSubmitData()),
                        date: this.getDate(date)
                    })
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
                    this.mAjaxUnApprove.get({
                        data: JSON.stringify(this.onLoadSubmitData()),
                        date: this.getDate(date)
                    })
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
            }(BasicEndpoint));
            singleDateReport.ApproveView = ApproveView;
        })(singleDateReport = templates.singleDateReport || (templates.singleDateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
