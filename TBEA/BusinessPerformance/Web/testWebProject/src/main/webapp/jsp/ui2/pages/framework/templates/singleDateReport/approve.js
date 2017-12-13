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
                    return _super !== null && _super.apply(this, arguments) || this;
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
                        //this.dateSelect = new Util.DateSelectorProxy(opt.dtId, {
                        //    year: opt.date.year - 3,
                        //    month: opt.date.month,
                        //    day: opt.date.day
                        //}, opt.date, opt.date);
                        var seasonClass = "";
                        var fmt = "YYYY年MM月";
                        if (opt.asSeason) {
                            fmt = "YYYY年 && $$MM月";
                            seasonClass = "season-month";
                        }
                        else if (opt.asSeasonAcc) {
                            fmt = "YYYY年 &&MM月";
                            seasonClass = "season";
                        }
                        $("#" + this.opt.dtId).jeDate({
                            skinCell: "jedatedeepgreen",
                            format: fmt,
                            isTime: false,
                            isinitVal: true,
                            isClear: false,
                            isToday: false,
                            minDate: Util.date2Str(Util.addYear(opt.date, -3)),
                            maxDate: Util.date2Str(opt.dateEnd),
                            seasonText: opt.jdName ? opt.jdName : undefined
                        }).removeCss("height")
                            .removeCss("padding")
                            .removeCss("margin-top")
                            .addClass(seasonClass);
                        this.update(this.getUDate());
                    }
                };
                ApproveView.prototype.getUDate = function () {
                    var ret = {};
                    if (this.opt.date) {
                        var curDate = $("#" + this.opt.dtId).getDate();
                        ret = {
                            year: curDate.getFullYear(),
                            month: curDate.getMonth() + 1,
                            day: curDate.getDate()
                        };
                    }
                    return ret;
                };
                ApproveView.prototype.onEvent = function (e) {
                    switch (e.id) {
                        case FrameEvent.FE_UPDATE:
                            if (this.opt.date == undefined) {
                                return this.update(({}));
                            }
                            else {
                                return this.update(this.getUDate());
                            }
                        case FrameEvent.FE_APPROVE:
                            if (this.opt.date == undefined) {
                                return this.approve(({}));
                            }
                            else {
                                return this.approve(this.getUDate());
                            }
                        case FrameEvent.FE_UNAPPROVE:
                            if (this.opt.date == undefined) {
                                return this.unapprove(({}));
                            }
                            else {
                                return this.unapprove(this.getUDate());
                            }
                    }
                    return _super.prototype.onEvent.call(this, e);
                };
                ApproveView.prototype.getDate = function (date) {
                    return "" + (date.year + "-" + (date.month == undefined ? 1 : date.month) + "-" + (date.day == undefined ? 1 : date.day));
                };
                ApproveView.prototype.getParams = function (date) {
                    return {
                        date: this.getDate(date)
                    };
                };
                ApproveView.prototype.update = function (date) {
                    var _this = this;
                    this.mAjaxUpdate.get(this.getParams(this.getUDate()))
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
                ApproveView.prototype.adjustSize = function () {
                    var jqgrid = this.jqgrid();
                    if ($("#" + this.opt.host).width() != $("#" + this.opt.host + " .ui-jqgrid").width()) {
                        jqgrid.setGridWidth($("#" + this.opt.host).width());
                    }
                    var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                    this.mTableAssist && this.mTableAssist.resizeHeight(maxTableBodyHeight);
                    if ($("#" + this.opt.host).width() != $("#" + this.opt.host + " .ui-jqgrid").width()) {
                        jqgrid.setGridWidth($("#" + this.opt.host).width());
                    }
                };
                ApproveView.prototype.jqgrid = function () {
                    return $("#" + this.jqgridName());
                };
                ApproveView.prototype.jqgridName = function () {
                    return this.opt.host + "_jqgrid_real";
                };
                ApproveView.prototype.createJqassist = function () {
                    var parent = $("#" + this.opt.host);
                    var pagername = this.jqgridName() + "pager";
                    parent.empty();
                    parent.append("<table id='" + this.jqgridName() + "'><div id='" + pagername + "'></table>");
                    this.mTableAssist = Util.createTable(this.jqgridName(), this.resp);
                    return this.mTableAssist;
                };
                ApproveView.prototype.updateTable = function () {
                    this.createJqassist();
                    this.mTableAssist.create({
                        dataWithId: this.resp.data,
                        datatype: "local",
                        multiselect: false,
                        drag: false,
                        resize: false,
                        height: '100%',
                        width: $("#" + this.opt.host).width(),
                        shrinkToFit: this.resp.shrinkToFit == "false" ? false : true,
                        rowNum: 2000,
                        autoScroll: true
                    });
                    this.adjustSize();
                };
                //updateTable():void {
                //    var name = this.opt.host + "_jqgrid_uiframe";
                //    var pagername = name + "pager";
                //    this.mTableAssist = Util.createTable(name, this.resp);
                //
                //    var parent = $("#" + this.opt.host);
                //    parent.empty();
                //    parent.append("<table id='" + name + "'></div>");
                //    let jqTable = $("#" + name);
                //    let opt = {
                //        datatype: "local",
                //        data: this.mTableAssist.getDataWithId(this.resp.data),
                //        multiselect: false,
                //        drag: false,
                //        resize: false,
                //        assistEditable:true,
                //        //autowidth : false,
                //        cellsubmit: 'clientArray',
                //        //editurl: 'clientArray',
                //        cellEdit: false,
                //        // height: data.length > 25 ? 550 : '100%',
                //        // width: titles.length * 200,
                //        rowNum: 1000,
                //        height: '100%',
                //        width: 1200,
                //        shrinkToFit: true,
                //        autoScroll: true,
                //       // pager: '#' + pagername,
                //       // viewrecords: true
                //    };
                //    //if (this.resp.pager == 'none'){
                //    //    opt.pager = undefined;
                //    //}
                //    jqTable.jqGrid(this.mTableAssist.decorate(opt));
                //}
                ApproveView.prototype.onLoadSubmitData = function () {
                    return this.mTableAssist.getAllData();
                };
                ApproveView.prototype.approve = function (date) {
                    var _this = this;
                    this.mAjaxApprove.get($.extend(this.getParams(this.getUDate()), {
                        data: JSON.stringify(this.onLoadSubmitData())
                    }))
                        .then(function (resp) {
                        if (Util.ErrorCode.OK == resp.errorCode) {
                            _this.update(date);
                            Util.Toast.success("审核 成功");
                        }
                        else {
                            Util.Toast.failed(resp.message);
                        }
                    });
                };
                ApproveView.prototype.unapprove = function (date) {
                    var _this = this;
                    this.mAjaxUnApprove.get($.extend(this.getParams(this.getUDate()), {
                        data: JSON.stringify(this.onLoadSubmitData())
                    }))
                        .then(function (resp) {
                        if (Util.ErrorCode.OK == resp.errorCode) {
                            _this.update(date);
                            Util.Toast.success("反审核 成功");
                        }
                        else {
                            Util.Toast.failed(resp.message);
                        }
                    });
                };
                return ApproveView;
            }(BasicEndpoint));
            singleDateReport.ApproveView = ApproveView;
        })(singleDateReport = templates.singleDateReport || (templates.singleDateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
