/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cwyjsfdef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.yjsf = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var cwyjsf;
(function (cwyjsf) {
    var yjsf;
    (function (yjsf) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, year) {
                var dyyjs = Node.create({ name: year + "年当月应交数" });
                for (var i = 1; i <= 12; ++i) {
                    dyyjs.append(Node.create({ name: i + "" }));
                }
                var dyyijs = Node.create({ name: year + "年当已交数" });
                for (var i = 1; i <= 12; ++i) {
                    dyyijs.append(Node.create({ name: i + "" }));
                }
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "税种", align: TextAlign.Left }),
                    Node.create({ name: "期初数" }),
                    dyyjs,
                    dyyijs
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("/BusinessManagement/yjsf/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.yjsf;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, cpType) {
                return "/BusinessManagement/yjsf/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: cpType
                });
            };
            ShowView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case cwyjsf.Event.CW_ISMONTH_SUPPORTED:
                        return false;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mCompType = compType;
                this.mAjax.get({
                    date: date,
                    companyId: compType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.updateTable();
                });
                this.mAjax.get({
                    date: (this.getYear() - 1) + "-" + this.getMonth() + "-1",
                    companyId: compType
                })
                    .then(function (jsonData) {
                    _this.mData1 = jsonData;
                    _this.updateTable1();
                });
            };
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.adjustSize();
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "应交税费");
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.getYear = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                return curDate.getFullYear();
            };
            ShowView.prototype.jqgridHost1 = function () {
                return this.$(this.mOpt.tb1);
            };
            ShowView.prototype.jqgridName1 = function () {
                return this.mOpt.host + this.mOpt.tb1 + "_jqgrid_real";
            };
            ShowView.prototype.jqgrid1 = function () {
                return this.$(this.jqgridName1());
            };
            ShowView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                var jqgrid1 = this.jqgrid1();
                if (this.jqgridHost1().width() != this.jqgridHost1().children().eq(0).width()) {
                    jqgrid1.setGridWidth(this.jqgridHost1().width());
                }
                //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                //this.tableAssist.resizeHeight(maxTableBodyHeight);
                //
                //if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                //    jqgrid.setGridWidth(this.jqgridHost().width());
                //}
                //this.$(this.option().ct).css("height", "300px");
                //this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                //this.updateEchart(this.mFinalData);
            };
            ShowView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.getYear());
                return this.tableAssist;
            };
            ShowView.prototype.createJqassist1 = function () {
                var parent = this.$(this.option().tb1);
                parent.empty();
                parent.append("<table id='" + this.jqgridName1() + "'></table>");
                this.tableAssist1 = JQGridAssistantFactory.createTable(this.jqgridName1(), this.getYear() - 1);
                return this.tableAssist1;
            };
            ShowView.prototype.updateTable = function () {
                this.createJqassist();
                this.tableAssist.create({
                    data: this.mData,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 100,
                    autoScroll: true
                });
                return;
            };
            ShowView.prototype.updateTable1 = function () {
                this.createJqassist1();
                this.tableAssist1.create({
                    data: this.mData1,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.jqgridHost1().width(),
                    shrinkToFit: true,
                    rowNum: 100,
                    autoScroll: true
                });
                return;
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(framework.basic.ShowPluginView);
    })(yjsf = cwyjsf.yjsf || (cwyjsf.yjsf = {}));
})(cwyjsf || (cwyjsf = {}));
