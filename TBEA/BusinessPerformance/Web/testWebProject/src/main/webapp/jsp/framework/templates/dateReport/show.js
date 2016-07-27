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
///<reference path="../singleDateReport/show.ts"/>
var framework;
(function (framework) {
    var templates;
    (function (templates) {
        var DateReport;
        (function (DateReport) {
            var UnitedSelector = Util.UnitedSelector;
            var ShowView = (function (_super) {
                __extends(ShowView, _super);
                function ShowView() {
                    _super.apply(this, arguments);
                }
                ShowView.prototype.getId = function () {
                    return framework.basic.endpoint.FRAME_ID;
                };
                ShowView.prototype.onInitialize = function (opt) {
                    this.unitedSelector = new UnitedSelector(opt.itemNodes, opt.itemId);
                    $("#" + opt.itemId).multiselect({
                        multiple: false,
                        header: false,
                        minWidth: 100,
                        height: '100%',
                        // noneSelectedText: "请选择月份",
                        selectedList: 1
                    }).css("text-align:center");
                    _super.prototype.onInitialize.call(this, opt);
                };
                ShowView.prototype.getDate = function (date) {
                    return "" + (date.year + "-" + (date.month == undefined ? 1 : date.month) + "-" + (date.day == undefined ? 1 : date.day));
                };
                ShowView.prototype.update = function (date) {
                    var _this = this;
                    this.mAjaxUpdate.get({
                        date: this.getDate(date),
                        item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id
                    })
                        .then(function (jsonData) {
                        _this.resp = jsonData;
                        _this.updateTable();
                    });
                };
                ShowView.prototype.updateTable = function () {
                    var name = this.opt.host + "_jqgrid_uiframe";
                    var pagername = name + "pager";
                    this.mTableAssist = Util.JQGridAssistantFactory.createTable(name, this.resp);
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
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true
                    }));
                };
                ShowView.prototype.exportExcel = function (date, id) {
                    $("#" + id)[0].action = this.opt.exportUrl + "?" + Util.Ajax.toUrlParam({
                        date: this.getDate(date),
                        item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id
                    });
                    $("#" + id)[0].submit();
                };
                return ShowView;
            })(framework.templates.singleDateReport.ShowView);
            DateReport.ShowView = ShowView;
        })(DateReport = templates.DateReport || (templates.DateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
