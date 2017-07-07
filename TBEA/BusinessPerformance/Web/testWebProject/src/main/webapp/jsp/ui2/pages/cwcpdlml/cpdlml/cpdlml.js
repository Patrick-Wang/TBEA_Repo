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
                this.mAjax = new Util.Ajax("/BusinessManagement/cpdlml/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.cpdlml;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, cpType) {
                return "/BusinessManagement/cpdlml/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: cpType
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
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
                this.adjustSize();
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "产品大类毛利表");
            };
            ShowView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
                this.tableAssist.mergeRow(0);
                this.tableAssist.mergeColum(0);
                return this.tableAssist;
            };
            ShowView.prototype.adjustSize = function () {
                //                var jqgrid = this.jqgrid();
                //                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                //                    jqgrid.setGridWidth(this.jqgridHost().width());
                //                }
                //
                //                let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                //                this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);
                //
                //                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                //                    jqgrid.setGridWidth(this.jqgridHost().width());
                //                }
                //this.$(this.option().ct).css("height", "300px");
                //this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                //this.updateEchart(this.mFinalData);
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
                    rowNum: 2000,
                    autoScroll: true
                });
                return;
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(framework.basic.ShowPluginView);
    })(cpdlml = cwcpdlml.cpdlml || (cwcpdlml.cpdlml = {}));
})(cwcpdlml || (cwcpdlml = {}));
