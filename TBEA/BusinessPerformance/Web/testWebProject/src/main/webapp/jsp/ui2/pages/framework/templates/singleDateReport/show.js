var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../basic/basicShow.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
var framework;
(function (framework) {
    var templates;
    (function (templates) {
        var singleDateReport;
        (function (singleDateReport) {
            var BasicEndpoint = framework.basic.BasicEndpoint;
            singleDateReport.FRAME_ID = framework.route.nextId();
            singleDateReport.FE_UPDATE = framework.route.nextId();
            singleDateReport.FE_EXPORTEXCEL = framework.route.nextId();
            singleDateReport.FE_INIT_EVENT = framework.route.nextId();
            function create() {
                return new ShowView();
            }
            singleDateReport.create = create;
            var ShowView = (function (_super) {
                __extends(ShowView, _super);
                function ShowView() {
                    _super.apply(this, arguments);
                }
                ShowView.prototype.getId = function () {
                    return singleDateReport.FRAME_ID;
                };
                ShowView.prototype.onInitialize = function (opt) {
                    var _this = this;
                    this.opt = opt;
                    this.mAjaxUpdate = new Util.Ajax(opt.updateUrl, false);
                    this.mAjaxExport = new Util.Ajax(opt.exportUrl, false);
                    if (opt.date == undefined) {
                        $("#" + opt.dtId).hide();
                        this.update(({}));
                    }
                    else {
                        if (opt.dateEnd == undefined) {
                            opt.dateEnd = $.extend({}, opt.date);
                        }
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
                        else if (!opt.date.month) {
                            fmt = "YYYY年";
                            seasonClass = "year";
                        }
                        else if (opt.date.day) {
                            fmt = "YYYY年MM月DD日";
                            seasonClass = "day";
                        }
                        $("#" + this.opt.dtId).jeDate({
                            skinCell: "jedatedeepgreen",
                            format: fmt,
                            isTime: false,
                            isinitVal: true,
                            isClear: false,
                            isToday: false,
                            minDate: Util.date2Str(Util.addYear(opt.date, -3)) + " 00:00:00",
                            maxDate: Util.date2Str(opt.dateEnd) + " 00:00:00",
                            seasonText: opt.jdName ? opt.jdName : undefined
                        }).removeCss("height")
                            .removeCss("padding")
                            .removeCss("margin-top")
                            .addClass(seasonClass);
                        this.update(this.getUDate());
                        $(window).resize(function () {
                            _this.adjustSize();
                        });
                    }
                };
                ShowView.prototype.getUDate = function () {
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
                ShowView.prototype.onEvent = function (e) {
                    switch (e.id) {
                        case singleDateReport.FE_UPDATE:
                            if (this.opt.date == undefined) {
                                return this.update(({}));
                            }
                            else {
                                return this.update(this.getUDate());
                            }
                        case singleDateReport.FE_EXPORTEXCEL:
                            if (this.opt.date == undefined) {
                                return this.exportExcel(({}), e.data);
                            }
                            else {
                                return this.exportExcel(this.getUDate(), e.data);
                            }
                    }
                    return _super.prototype.onEvent.call(this, e);
                };
                ShowView.prototype.getDate = function (date) {
                    return "" + (date.year + "-" + (date.month == undefined ? 1 : date.month) + "-" + (date.day == undefined ? 1 : date.day));
                };
                ShowView.prototype.getParams = function (date) {
                    return {
                        date: this.getDate(date)
                    };
                };
                ShowView.prototype.update = function (date) {
                    var _this = this;
                    this.mAjaxUpdate.get(this.getParams(this.getUDate()))
                        .then(function (jsonData) {
                        _this.resp = jsonData;
                        _this.updateTable();
                    });
                };
                ShowView.prototype.adjustSize = function () {
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
                ShowView.prototype.jqgrid = function () {
                    return $("#" + this.jqgridName());
                };
                ShowView.prototype.jqgridName = function () {
                    return this.opt.host + "_jqgrid_real";
                };
                ShowView.prototype.createJqassist = function () {
                    var parent = $("#" + this.opt.host);
                    var pagername = this.jqgridName() + "pager";
                    parent.empty();
                    parent.append("<table id='" + this.jqgridName() + "'><div id='" + pagername + "'></table>");
                    this.mTableAssist = Util.createTable(this.jqgridName(), this.resp);
                    return this.mTableAssist;
                };
                ShowView.prototype.updateTable = function () {
                    this.createJqassist();
                    if (this.resp.colorKey) {
                        for (var i = 0; i < this.resp.data.length; ++i) {
                            if (this.resp.data[i][0].lastIndexOf(this.resp.colorKey) >= 0) {
                                this.mTableAssist.setRowBgColor(i, 183, 222, 232);
                            }
                        }
                    }
                    this.mTableAssist.create({
                        data: this.resp.data,
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
                ShowView.prototype.exportExcel = function (date, id) {
                    $("#" + id)[0].action = this.opt.exportUrl + "?" + Util.Ajax.toUrlParam(this.getParams(this.getUDate()));
                    $("#" + id)[0].submit();
                };
                return ShowView;
            })(BasicEndpoint);
            singleDateReport.ShowView = ShowView;
        })(singleDateReport = templates.singleDateReport || (templates.singleDateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
