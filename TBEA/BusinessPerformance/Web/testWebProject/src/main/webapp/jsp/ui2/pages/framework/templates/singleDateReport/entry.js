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
                return new EntryView();
            }
            singleDateReport.createInstance = createInstance;
            var EntryView = (function (_super) {
                __extends(EntryView, _super);
                function EntryView() {
                    return _super !== null && _super.apply(this, arguments) || this;
                }
                EntryView.prototype.getId = function () {
                    return framework.basic.endpoint.FRAME_ID;
                };
                EntryView.prototype.onInitialize = function (opt) {
                    this.opt = opt;
                    this.mAjaxUpdate = new Util.Ajax(opt.updateUrl, false);
                    this.mAjaxSubmit = new Util.Ajax(opt.submitUrl, false);
                    if (opt.date == undefined) {
                        $("#" + opt.dtId).hide();
                        this.update(({}));
                    }
                    else {
                        if (opt.dateEnd == undefined) {
                            opt.dateEnd = $.extend({}, opt.date);
                        }
                        //this.dateSelect = new Util.DateSelectorProxy(opt.dtId, {
                        //    year:opt.date.year - 3,
                        //    month:opt.date.month,
                        //    day:opt.date.day
                        //}, opt.dateEnd, opt.date, opt.asSeason, opt.asSeasonAcc, opt.jdName);
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
                EntryView.prototype.getUDate = function () {
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
                EntryView.prototype.onEvent = function (e) {
                    switch (e.id) {
                        case FrameEvent.FE_UPDATE:
                            if (this.opt.date == undefined) {
                                return this.update(({}));
                            }
                            else {
                                return this.update(this.getUDate());
                            }
                        case FrameEvent.FE_SUBMIT:
                            if (this.opt.date == undefined) {
                                return this.submit(({}));
                            }
                            else {
                                return this.submit(this.getUDate());
                            }
                    }
                    return _super.prototype.onEvent.call(this, e);
                };
                EntryView.prototype.getDate = function (date) {
                    return "" + (date.year + "-" + (date.month == undefined ? 1 : date.month) + "-" + (date.day == undefined ? 1 : date.day));
                };
                EntryView.prototype.getParams = function (date) {
                    return {
                        date: this.getDate(date)
                    };
                };
                EntryView.prototype.update = function (date) {
                    var _this = this;
                    this.mAjaxUpdate.get(this.getParams(this.getUDate()))
                        .then(function (jsonData) {
                        _this.resp = jsonData;
                        _this.updateTable();
                    });
                };
                EntryView.prototype.adjustSize = function () {
                    var jqgrid = this.jqgrid();
                    if ($("#" + this.opt.host).width() != $("#" + this.opt.host + " .ui-jqgrid").width()) {
                        jqgrid.setGridWidth($("#" + this.opt.host).width());
                    }
                    //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                    //this.mTableAssist && this.mTableAssist.resizeHeight(maxTableBodyHeight);
                    //
                    //if ($("#" + this.opt.host).width() != $("#" + this.opt.host + " .ui-jqgrid").width()) {
                    //    jqgrid.setGridWidth($("#" + this.opt.host).width());
                    //}
                };
                EntryView.prototype.jqgrid = function () {
                    return $("#" + this.jqgridName());
                };
                EntryView.prototype.jqgridName = function () {
                    return this.opt.host + "_jqgrid_real";
                };
                EntryView.prototype.createJqassist = function () {
                    var parent = $("#" + this.opt.host);
                    var pagername = this.jqgridName() + "pager";
                    parent.empty();
                    parent.append("<table id='" + this.jqgridName() + "'><div id='" + pagername + "'></table>");
                    this.mTableAssist = Util.createTable(this.jqgridName(), this.resp);
                    return this.mTableAssist;
                };
                EntryView.prototype.updateTable = function () {
                    this.createJqassist();
                    var opt = {
                        datatype: "local",
                        dataWithId: this.resp.data,
                        multiselect: false,
                        drag: false,
                        resize: false,
                        assistEditable: true,
                        //autowidth : false,
                        cellsubmit: 'clientArray',
                        //editurl: 'clientArray',
                        cellEdit: true,
                        // height: data.length > 25 ? 550 : '100%',
                        // width: titles.length * 200,
                        rowNum: 15,
                        height: '100%',
                        width: $("#" + this.opt.host).width(),
                        shrinkToFit: this.resp.shrinkToFit == "false" ? false : true,
                        autoScroll: true,
                        pager: '#' + this.jqgridName() + "pager",
                        viewrecords: true
                    };
                    if (this.resp.pager == 'none') {
                        opt.pager = undefined;
                        opt.rowNum = 2000;
                    }
                    this.mTableAssist.create(opt);
                    this.adjustSize();
                };
                //updateTable():void {
                //    var name = this.opt.host + "_jqgrid_uiframe";
                //    var pagername = name + "pager";
                //    this.mTableAssist = Util.createTable(name, this.resp);
                //
                //    var parent = $("#" + this.opt.host);
                //    parent.empty();
                //    parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
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
                //        cellEdit: true,
                //        // height: data.length > 25 ? 550 : '100%',
                //        // width: titles.length * 200,
                //        rowNum: 20,
                //        height: '100%',
                //        width: 1200,
                //        shrinkToFit: true,
                //        autoScroll: true,
                //        pager: '#' + pagername,
                //        viewrecords: true
                //    };
                //    if (this.resp.pager == 'none'){
                //        opt.pager = undefined;
                //        opt.rowNum = 1000;
                //    }
                //    jqTable.jqGrid(this.mTableAssist.decorate(opt));
                //}
                EntryView.prototype.onLoadSubmitData = function () {
                    if (this.resp.pager == 'none') {
                        return this.mTableAssist.getAllData();
                    }
                    return this.mTableAssist.getChangedData();
                };
                EntryView.prototype.submit = function (date) {
                    this.mAjaxSubmit.post($.extend(this.getParams(this.getUDate()), {
                        data: JSON.stringify(this.onLoadSubmitData())
                    }))
                        .then(function (resp) {
                        if (Util.ErrorCode.OK == resp.errorCode) {
                            Util.Toast.success("提交 成功");
                        }
                        else {
                            Util.Toast.failed(resp.message);
                        }
                    }, function (text) {
                        Util.Toast.failed('提交 失败');
                    });
                };
                return EntryView;
            }(BasicEndpoint));
            singleDateReport.EntryView = EntryView;
        })(singleDateReport = templates.singleDateReport || (templates.singleDateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
