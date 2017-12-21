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
                        this.dateSelect = new Util.DateSelectorProxy(opt.dtId, {
                            year: opt.date.year - 3,
                            month: opt.date.month,
                            day: opt.date.day
                        }, opt.dateEnd, opt.date, opt.asSeason, opt.asSeasonAcc, opt.jdName);
                        this.update(this.dateSelect.getDate());
                    }
                };
                EntryView.prototype.onEvent = function (e) {
                    switch (e.id) {
                        case FrameEvent.FE_UPDATE:
                            if (this.dateSelect == undefined) {
                                return this.update(({}));
                            }
                            else {
                                return this.update(this.dateSelect.getDate());
                            }
                        case FrameEvent.FE_SUBMIT:
                            if (this.dateSelect == undefined) {
                                return this.submit(({}));
                            }
                            else {
                                return this.submit(this.dateSelect.getDate());
                            }
                    }
                    return _super.prototype.onEvent.call(this, e);
                };
                EntryView.prototype.getDate = function (date) {
                    return "" + (date.year + "-" + (date.month == undefined ? 1 : date.month) + "-" + (date.day == undefined ? 1 : date.day));
                };
                EntryView.prototype.update = function (date) {
                    var _this = this;
                    this.mAjaxUpdate.get({
                        date: this.getDate(date)
                    })
                        .then(function (jsonData) {
                        _this.resp = jsonData;
                        _this.updateTable();
                    });
                };
                EntryView.prototype.updateTable = function () {
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
                        cellEdit: true,
                        // height: data.length > 25 ? 550 : '100%',
                        // width: titles.length * 200,
                        rowNum: 20,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        pager: '#' + pagername,
                        viewrecords: true
                    };
                    if (this.resp.pager == 'none') {
                        opt.pager = undefined;
                        opt.rowNum = 1000;
                    }
                    jqTable.jqGrid(this.mTableAssist.decorate(opt));
                };
                EntryView.prototype.onLoadSubmitData = function () {
                    if (this.resp.pager == 'none') {
                        return this.mTableAssist.getAllData();
                    }
                    return this.mTableAssist.getChangedData();
                };
                EntryView.prototype.submit = function (date) {
                    var _this = this;
                    this.mAjaxSubmit.get({
                        data: JSON.stringify(this.onLoadSubmitData()),
                        date: this.getDate(date)
                    })
                        .then(function (resp) {
                        if (Util.ErrorCode.OK == resp.errorCode) {
                            Util.MessageBox.tip("保存 成功", function () {
                                _this.update(date);
                            });
                        }
                        else {
                            Util.MessageBox.tip(resp.message);
                        }
                    });
                };
                return EntryView;
            }(BasicEndpoint));
            singleDateReport.EntryView = EntryView;
        })(singleDateReport = templates.singleDateReport || (templates.singleDateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
