var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cwyjsfdef.ts"/>
var plugin;
(function (plugin) {
    plugin.yjsfljs = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var cwyjsf;
(function (cwyjsf) {
    var yjsfljs;
    (function (yjsfljs) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, year) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "税种", align: TextAlign.Left }),
                    Node.create({ name: "期初余额" }),
                    Node.create({ name: year + "年应交税额" })
                        .append(Node.create({ name: "本月数" }))
                        .append(Node.create({ name: "累计数" })),
                    Node.create({ name: year + "年已交税额" })
                        .append(Node.create({ name: "本月数" }))
                        .append(Node.create({ name: "累计数" })),
                    Node.create({ name: year + "年未交税额" })
                        .append(Node.create({ name: "本月数" }))
                        .append(Node.create({ name: "累计数" })),
                    Node.create({ name: "期末余额" })
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../yjsfljs/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.yjsfljs;
            };
            ShowView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case cwyjsf.Event.CW_ISMONTH_SUPPORTED:
                        return true;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../yjsfljs/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType
                });
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
                    _this.updateTable(_this.option().tb, _this.getYear(), jsonData);
                });
                this.mAjax.get({
                    date: (this.getYear() - 1) + "-" + this.getMonth() + "-1",
                    companyId: compType
                })
                    .then(function (jsonData) {
                    _this.updateTable(_this.option().tb1, _this.getYear() - 1, jsonData);
                });
            };
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "应交税费累计对比");
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
            ShowView.prototype.updateTable = function (tbid, year, data) {
                var name = this.option().host + tbid + "_jqgrid_uiframe";
                var tableAssist = JQGridAssistantFactory.createTable(name, year);
                var parent = this.$(tbid);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                this.$(name).jqGrid(tableAssist.decorate({
                    datatype: "local",
                    data: tableAssist.getData(data),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1400,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 20,
                    viewrecords: true
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(framework.basic.ShowPluginView);
    })(yjsfljs = cwyjsf.yjsfljs || (cwyjsf.yjsfljs = {}));
})(cwyjsf || (cwyjsf = {}));
