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
            function createInstance() {
                return new ShowView();
            }
            singleDateReport.createInstance = createInstance;
            var ShowView = (function (_super) {
                __extends(ShowView, _super);
                function ShowView() {
                    return _super !== null && _super.apply(this, arguments) || this;
                }
                ShowView.prototype.getId = function () {
                    return singleDateReport.FRAME_ID;
                };
                ShowView.prototype.onInitialize = function (opt) {
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
                        this.dateSelect = new Util.DateSelectorProxy(opt.dtId, {
                            year: opt.date.year - 3,
                            month: opt.date.month,
                            day: opt.date.day
                        }, opt.dateEnd, opt.date, opt.asSeason, opt.asSeasonAcc, opt.jdName);
                        this.update(this.dateSelect.getDate());
                    }
                };
                ShowView.prototype.onEvent = function (e) {
                    switch (e.id) {
                        case singleDateReport.FE_UPDATE:
                            if (this.dateSelect == undefined) {
                                return this.update(({}));
                            }
                            else {
                                return this.update(this.dateSelect.getDate());
                            }
                        case singleDateReport.FE_EXPORTEXCEL:
                            if (this.dateSelect == undefined) {
                                return this.exportExcel(({}), e.data);
                            }
                            else {
                                return this.exportExcel(this.dateSelect.getDate(), e.data);
                            }
                    }
                    return _super.prototype.onEvent.call(this, e);
                };
                ShowView.prototype.getDate = function (date) {
                    return "" + (date.year + "-" + (date.month == undefined ? 1 : date.month) + "-" + (date.day == undefined ? 1 : date.day));
                };
                ShowView.prototype.update = function (date) {
                    var _this = this;
                    this.mAjaxUpdate.get({
                        date: this.getDate(date)
                    })
                        .then(function (jsonData) {
                        _this.resp = jsonData;
                        _this.updateTable();
                    });
                };
                ShowView.prototype.updateTable = function () {
                    var name = this.opt.host + "_jqgrid_uiframe";
                    var pagername = name + "pager";
                    this.mTableAssist = Util.createTable(name, this.resp);
                    if (this.resp.colorKey) {
                        for (var i = 0; i < this.resp.data.length; ++i) {
                            if (this.resp.data[i][0].lastIndexOf(this.resp.colorKey) >= 0) {
                                this.mTableAssist.setRowBgColor(i, 183, 222, 232);
                            }
                        }
                    }
                    var parent = $("#" + this.opt.host);
                    parent.empty();
                    parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                    var jqTable = $("#" + name);
                    jqTable.jqGrid(this.mTableAssist.decorate({
                        datatype: "local",
                        data: this.mTableAssist.getData(this.resp.data),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        assistEditable: false,
                        //autowidth : false,
                        cellsubmit: 'clientArray',
                        //editurl: 'clientArray',
                        cellEdit: false,
                        // height: data.length > 25 ? 550 : '100%',
                        // width: titles.length * 200,
                        rowNum: 1000,
                        height: this.resp.data.length > 25 ? 550 : '100%',
                        width: this.resp.width == undefined ? 1300 : this.resp.width,
                        shrinkToFit: true,
                        autoScroll: true
                    }));
                };
                ShowView.prototype.exportExcel = function (date, id) {
                    $("#" + id)[0].action = this.opt.exportUrl + "?" + Util.Ajax.toUrlParam({
                        date: this.getDate(date)
                    });
                    $("#" + id)[0].submit();
                };
                return ShowView;
            }(BasicEndpoint));
            singleDateReport.ShowView = ShowView;
        })(singleDateReport = templates.singleDateReport || (templates.singleDateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
