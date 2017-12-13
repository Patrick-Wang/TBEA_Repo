/// <reference path="../../../../jqgrid/jqassist.ts" />
/// <reference path="../../../../util.ts" />
/// <reference path="../../../../dateSelector.ts" />
/// <reference path="../../../basic/basicdef.ts"/>
/// <reference path="../../../route/route.ts"/>
/// <reference path="../basicReportdef.ts"/>
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
var plugin;
(function (plugin) {
    plugin.basic = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var basicReport;
(function (basicReport) {
    var basic;
    (function (basic) {
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjax = new Util.Ajax("../basic/update.do", false);
                return _this;
            }
            ShowView.prototype.getId = function () {
                return plugin.basic;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../basic/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.find = function (id) {
                for (var i = this.option().items.length - 1; i >= 0; --i) {
                    if (this.option().items[i].data.id == id) {
                        return i;
                    }
                }
                return -1;
            };
            ShowView.prototype.onEvent = function (e) {
                if (e.road != undefined) {
                    var index = this.find(e.road[e.road.length - 1]);
                    if (index >= 0) {
                        this.mCurEp = index;
                    }
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mCompType = compType;
                this.mAjax.get({
                    date: date,
                    companyId: compType,
                    item: this.mCurEp
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ShowView.prototype.init = function (opt) {
                for (var i = 0; i < opt.items.length; ++i) {
                    framework.router
                        .fromEp(new framework.basic.EndpointProxy((opt.items[i].data.id), this.getId()))
                        .to(framework.basic.endpoint.FRAME_ID)
                        .send(framework.basic.FrameEvent.FE_REGISTER, opt.items[i].data.value);
                }
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist = Util.createTable(name, this.m);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                this.$(name).jqGrid(tableAssist.decorate({
                    datatype: "local",
                    data: tableAssist.getData(this.mData),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 1000,
                    viewrecords: true
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        }(framework.basic.ShowPluginView));
    })(basic = basicReport.basic || (basicReport.basic = {}));
})(basicReport || (basicReport = {}));
