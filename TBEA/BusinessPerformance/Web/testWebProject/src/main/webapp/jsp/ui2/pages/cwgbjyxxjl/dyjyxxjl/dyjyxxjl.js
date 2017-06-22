/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cwgbjyxxjldef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.dyjyxxjl = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var cwgbjyxxjl;
(function (cwgbjyxxjl) {
    var dyjyxxjl;
    (function (dyjyxxjl) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                var data = [];
                var node;
                var titleNodes = [];
                node = new JQTable.Node("科目", "cwgbjyxxjl_cp", true, TextAlign.Left, 300);
                titleNodes.push(node);
                node = new JQTable.Node("月度", "cwgbjyxxjl_snd", true, TextAlign.Center);
                node.append(new JQTable.Node("当月计划", "dyjyxxjl_dyjh"));
                node.append(new JQTable.Node("当月实际", "dyjyxxjl_dysj"));
                node.append(new JQTable.Node("计划完成率", "dyjyxxjl_jhwcl"));
                node.append(new JQTable.Node("去年同期", "dyjyxxjl_qntq"));
                node.append(new JQTable.Node("同比增幅", "dyjyxxjl_tbzf"));
                titleNodes.push(node);
                node = new JQTable.Node("年度", "cwgbjyxxjl_bnd", true, TextAlign.Center);
                node.append(new JQTable.Node("年度累计", "dyjyxxjl_ndlj"));
                node.append(new JQTable.Node("去年同期", "dyjyxxjl_qntq"));
                node.append(new JQTable.Node("同比增幅", "dyjyxxjl_tbzf"));
                titleNodes.push(node);
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../dyjyxxjl/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.dyjyxxjl;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../dyjyxxjl/export.do?" + Util.Ajax.toUrlParam({
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
                    .send(framework.basic.FrameEvent.FE_REGISTER, "经营性现金流月度情况");
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                this.$(name).jqGrid(tableAssist.decorate({
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1400,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 40,
                    data: tableAssist.getData(this.mData),
                    datatype: "local",
                    viewrecords: true
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(framework.basic.ShowPluginView);
    })(dyjyxxjl = cwgbjyxxjl.dyjyxxjl || (cwgbjyxxjl.dyjyxxjl = {}));
})(cwgbjyxxjl || (cwgbjyxxjl = {}));
