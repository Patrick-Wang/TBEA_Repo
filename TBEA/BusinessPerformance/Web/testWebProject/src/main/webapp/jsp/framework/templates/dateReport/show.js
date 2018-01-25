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
///<reference path="../singleDateReport/show.ts"/>
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
                    return _super !== null && _super.apply(this, arguments) || this;
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
                            minWidth: Util.getUIWidth(items) * 1.2,
                            height: '100%',
                            // noneSelectedText: "请选择月份",
                            selectedList: 1
                        })
                            .css("padding", "2px 0 2px 4px")
                            .css("text-align", "left")
                            .css("font-size", "12px");
                    }
                };
                ShowView.prototype.onInitialize = function (opt) {
                    var _this = this;
                    this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);
                    this.unitedSelector.change(function () {
                        _this.renderItemSelector(opt.itemId);
                    });
                    this.renderItemSelector(opt.itemId);
                    if (opt.itemNodes2 != undefined) {
                        this.unitedSelector2 = new Util.UnitedSelector(opt.itemNodes2, opt.itemId2);
                        this.unitedSelector2.change(function () {
                            _this.renderItemSelector(opt.itemId2);
                        });
                        this.renderItemSelector(opt.itemId2);
                    }
                    if (opt.itemNodes3 != undefined) {
                        this.unitedSelector3 = new Util.UnitedSelector(opt.itemNodes3, opt.itemId3);
                        this.unitedSelector3.change(function () {
                            _this.renderItemSelector(opt.itemId3);
                        });
                        this.renderItemSelector(opt.itemId3);
                    }
                    _super.prototype.onInitialize.call(this, opt);
                };
                ShowView.prototype.getParams = function (date) {
                    return {
                        date: this.getDate(date),
                        item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id,
                        item2: this.unitedSelector2 != undefined ? this.unitedSelector2.getDataNode(this.unitedSelector2.getPath()).data.id : undefined,
                        item3: this.unitedSelector3 != undefined ? this.unitedSelector3.getDataNode(this.unitedSelector3.getPath()).data.id : undefined
                    };
                };
                ShowView.prototype.getDate = function (date) {
                    return "" + (date.year + "-" + (date.month == undefined ? 1 : date.month) + "-" + (date.day == undefined ? 1 : date.day));
                };
                ShowView.prototype.update = function (date) {
                    var _this = this;
                    var opt = (this.opt);
                    var title = orgTitle;
                    if (opt.itemNodes2 && opt.itemNodes2.length == 1) {
                        $("#" + opt.itemId).hide();
                        title = opt.itemNodes2[0].data.value + " " + title;
                    }
                    if (opt.itemNodes && opt.itemNodes.length == 1) {
                        $("#" + opt.itemId).hide();
                        title = opt.itemNodes[0].data.value + " " + title;
                    }
                    $("#headertitle").text(title);
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
                        width: this.resp.width == undefined ? 1200 : this.resp.width,
                        shrinkToFit: this.resp.shrinkToFit == "false" ? false : true,
                        autoScroll: true
                    }));
                };
                ShowView.prototype.exportExcel = function (date, id) {
                    $("#" + id)[0].action = this.opt.exportUrl + "?" + Util.Ajax.toUrlParam(this.getParams(date));
                    $("#" + id)[0].submit();
                };
                return ShowView;
            }(framework.templates.singleDateReport.ShowView));
            dateReport.ShowView = ShowView;
        })(dateReport = templates.dateReport || (templates.dateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
