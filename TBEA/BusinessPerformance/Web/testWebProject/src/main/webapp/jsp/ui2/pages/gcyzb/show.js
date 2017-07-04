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
            var gcyzb;
            (function (gcyzb) {
                function createInstance() {
                    return new GCYZBView();
                }
                gcyzb.createInstance = createInstance;
                var GCYZBView = (function (_super) {
                    __extends(GCYZBView, _super);
                    function GCYZBView() {
                        _super.apply(this, arguments);
                    }
                    GCYZBView.prototype.renderItemSelector = function (itemId) {
                        var sels = $("#" + itemId + " select");
                        for (var i = 0; i < sels.length; ++i) {
                            var opts = $("#" + itemId + " select:eq(" + i + ") option");
                            var items = [];
                            for (var j = 0; j < opts.length; ++j) {
                                items.push(opts[j].text);
                            }
                        }
                    };
                    GCYZBView.prototype.renderXmmcSelector = function (itemId) {
                        var sels = $("#" + itemId + " select");
                        for (var i = 0; i < sels.length; ++i) {
                            var opts = $("#" + itemId + " select:eq(" + i + ") option");
                            var items = [];
                            for (var j = 0; j < opts.length; ++j) {
                                items.push(opts[j].text);
                            }
                            $(sels[i])
                                .select2({
                                language: "zh-CN"
                            });
                        }
                    };
                    GCYZBView.prototype.onInitialize = function (opt) {
                        var _this = this;
                        this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);
                        this.xmmcSel = new Util.UnitedSelector(opt.xmmcNodes, opt.xmmcId);
                        this.unitedSelector.change(function () {
                            _this.renderItemSelector(opt.itemId);
                            var id = _this.unitedSelector.getDataNode(_this.unitedSelector.getPath()).data.id;
                            if (id >= 3 && id <= 7) {
                                $("#" + opt.dtId).parent().show();
                            }
                        });
                        this.renderItemSelector(opt.itemId);
                        this.renderXmmcSelector(opt.xmmcId);
                        this.xmmcSel.change(function () {
                            _this.renderXmmcSelector(opt.xmmcId);
                        });
                        _super.prototype.onInitialize.call(this, opt);
                        $("#" + opt.dtId).parent().hide();
                    };
                    GCYZBView.prototype.getParams = function (date) {
                        return {
                            date: this.getDate(date),
                            item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id,
                            xmmc: this.xmmcSel.getDataNode(this.xmmcSel.getPath()).data.value,
                        };
                    };
                    GCYZBView.prototype.update = function (date) {
                        var _this = this;
                        this.mAjaxUpdate.get(this.getParams(date))
                            .then(function (jsonData) {
                            _this.resp = jsonData;
                            _this.updateTable();
                        });
                    };
                    GCYZBView.prototype.createJqassist = function () {
                        var parent = $("#" + this.opt.host);
                        parent.empty();
                        parent.append("<table id='" + this.jqgridName() + "'></table>");
                        this.mTableAssist = Util.createTable(this.jqgridName(), this.resp);
                        return this.mTableAssist;
                    };
                    GCYZBView.prototype.updateTable = function () {
                        this.createJqassist();
                        this.mTableAssist.create({
                            data: this.resp.data,
                            datatype: "local",
                            multiselect: false,
                            drag: false,
                            resize: false,
                            height: '100%',
                            width: '100%',
                            shrinkToFit: this.resp.shrinkToFit == undefined ? true : this.resp.shrinkToFit == "true",
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
                    //    parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                    //    let jqTable = $("#" + name);
                    //    jqTable.jqGrid(
                    //        this.mTableAssist.decorate({
                    //            datatype: "local",
                    //            data: this.mTableAssist.getData(this.resp.data),
                    //            multiselect: false,
                    //            drag: false,
                    //            resize: false,
                    //            assistEditable:false,
                    //            //autowidth : false,
                    //            cellsubmit: 'clientArray',
                    //            //editurl: 'clientArray',
                    //            cellEdit: false,
                    //            // height: data.length > 25 ? 550 : '100%',
                    //            // width: titles.length * 200,
                    //            rowNum: 1000,
                    //            height: this.resp.data.length > 25 ? 550 : '100%',
                    //            width: this.resp.width == undefined ? 1300 : this.resp.width,
                    //            shrinkToFit: this.resp.shrinkToFit == undefined ? true : this.resp.shrinkToFit == "true",
                    //            autoScroll: true
                    //        }));
                    //}
                    GCYZBView.prototype.exportExcel = function (date, id) {
                        $("#" + id)[0].action = this.opt.exportUrl + "?" + Util.Ajax.toUrlParam(this.getParams(date));
                        $("#" + id)[0].submit();
                    };
                    return GCYZBView;
                })(framework.templates.singleDateReport.ShowView);
                gcyzb.GCYZBView = GCYZBView;
            })(gcyzb = dateReport.gcyzb || (dateReport.gcyzb = {}));
        })(dateReport = templates.dateReport || (templates.dateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
