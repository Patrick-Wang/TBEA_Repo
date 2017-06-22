var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
///<reference path="../framework/route/route.ts"/>
///<reference path="../jqgrid/jqassist.ts"/>
///<reference path="../framework/basic/basicdef.ts"/>
///<reference path="../framework/templates/singleDateReport/show.ts"/>
var framework;
(function (framework) {
    var templates;
    (function (templates) {
        var dateReport;
        (function (dateReport) {
            function createInstance() {
                return new ShowView();
            }
            dateReport.createInstance = createInstance;
            var ShowView = (function (_super) {
                __extends(ShowView, _super);
                function ShowView() {
                    _super.apply(this, arguments);
                }
                ShowView.prototype.renderItemSelector = function (itemId) {
                    var sels = $("#" + itemId + " select");
                    for (var i = 0; i < sels.length; ++i) {
                        var opts = $("#" + itemId + " select:eq(" + i + ") option");
                        var items = [];
                        for (var j = 0; j < opts.length; ++j) {
                            items.push(opts[j].text);
                        }
                        $(sels[i]).multiselect({
                            multiple: false,
                            header: false,
                            minWidth: Util.getUIWidth(items) + 40,
                            height: '100%',
                            // noneSelectedText: "请选择月份",
                            selectedList: 1
                        })
                            .css("padding", "2px 0 2px 4px")
                            .css("text-align", "left")
                            .css("font-size", "12px");
                    }
                };
                ShowView.prototype.renderXmmcSelector = function (itemId) {
                    var sels = $("#" + itemId + " select");
                    for (var i = 0; i < sels.length; ++i) {
                        var opts = $("#" + itemId + " select:eq(" + i + ") option");
                        var items = [];
                        for (var j = 0; j < opts.length; ++j) {
                            items.push(opts[j].text);
                        }
                        $(sels[i]).css("width", Math.max(Util.getUIWidth(items), 80))
                            .css("height", "20px")
                            .select2({
                            language: "cn"
                        });
                    }
                };
                ShowView.prototype.onInitialize = function (opt) {
                    var _this = this;
                    this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);
                    this.xmmcSel = new Util.UnitedSelector(opt.xmmcNodes, opt.xmmcId);
                    this.unitedSelector.change(function () {
                        _this.renderItemSelector(opt.itemId);
                    });
                    this.renderItemSelector(opt.itemId);
                    this.renderXmmcSelector(opt.xmmcId);
                    this.xmmcSel.change(function () {
                        _this.renderXmmcSelector(opt.xmmcId);
                    });
                    _super.prototype.onInitialize.call(this, opt);
                    $("#" + opt.dtId).hide();
                };
                ShowView.prototype.getParams = function (date) {
                    return {
                        //date: this.getDate(date),
                        item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id,
                        xmmc: this.xmmcSel.getDataNode(this.xmmcSel.getPath()).data.value,
                    };
                };
                ShowView.prototype.getDate = function (date) {
                    return "" + (date.year + "-" + (date.month == undefined ? 1 : date.month) + "-" + (date.day == undefined ? 1 : date.day));
                };
                ShowView.prototype.update = function (date) {
                    var _this = this;
                    this.mAjaxUpdate.get(this.getParams(date))
                        .then(function (jsonData) {
                        _this.resp = jsonData;
                        _this.updateTable();
                    });
                };
                ShowView.prototype.updateTable = function () {
                    var name = this.opt.host + "_jqgrid_uiframe";
                    var pagername = name + "pager";
                    this.mTableAssist = Util.createTable(name, this.resp);
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
                        shrinkToFit: this.resp.shrinkToFit == undefined ? true : this.resp.shrinkToFit == "true",
                        autoScroll: true
                    }));
                };
                ShowView.prototype.exportExcel = function (date, id) {
                    $("#" + id)[0].action = this.opt.exportUrl + "?" + Util.Ajax.toUrlParam(this.getParams(date));
                    $("#" + id)[0].submit();
                };
                return ShowView;
            })(framework.templates.singleDateReport.ShowView);
            dateReport.ShowView = ShowView;
        })(dateReport = templates.dateReport || (templates.dateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
