/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cwcpdlmldef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.cpdlml = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var cwcpdlml;
(function (cwcpdlml) {
    var cpdlml;
    (function (cpdlml) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "产业", align: TextAlign.Center }),
                    Node.create({ name: "产品大类", align: TextAlign.Left }),
                    Node.create({ name: "本年累计" })
                        .append(Node.create({ name: "累计收入" }))
                        .append(Node.create({ name: "比重" }))
                        .append(Node.create({ name: "累计成本" }))
                        .append(Node.create({ name: "毛利额" }))
                        .append(Node.create({ name: "毛利贡献率" }))
                        .append(Node.create({ name: "毛利率" })),
                    Node.create({ name: "去年全年累计" })
                        .append(Node.create({ name: "去年全年收入" }))
                        .append(Node.create({ name: "去年全年成本" }))
                        .append(Node.create({ name: "上年平均毛利率" })),
                    Node.create({ name: "较毛利率均值增减比" })
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../cpdlml/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.cpdlml;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../cpdlml/export.do?" + Util.Ajax.toUrlParam({
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
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "产品大类毛利表");
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                tableAssist.mergeRow(0);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                this.$(name).jqGrid(tableAssist.decorate({
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 20,
                    data: tableAssist.getData(this.mData),
                    datatype: "local",
                    viewrecords: true
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(framework.basic.ShowPluginView);
    })(cpdlml = cwcpdlml.cpdlml || (cwcpdlml.cpdlml = {}));
})(cwcpdlml || (cwcpdlml = {}));
