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
                this.mAjax = new Util.Ajax("/BusinessManagement/dyjyxxjl/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.dyjyxxjl;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, cpType) {
                return "/BusinessManagement/dyjyxxjl/export.do?" + Util.Ajax.toUrlParam({
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
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "经营性现金流月度情况");
            };
            ShowView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.tableAssist.resizeHeight(maxTableBodyHeight);
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                //this.$(this.option().ct).css("height", "300px");
                //this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                //this.updateEchart(this.mFinalData);
            };
            ShowView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
                return this.tableAssist;
            };
            ShowView.prototype.updateTable = function () {
                this.createJqassist();
                //var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                //let tableAssist:JQTable.JQGridAssistant;
                //if (this.mCurCbfxType == CbfxType.dmcbfx){
                //    this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.getYear(), this.getMonth());
                //}else{
                //    this.tableAssist = JQGridAssistantFactory.createQsTable(this.jqgridName());
                //}
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
    })(dyjyxxjl = cwgbjyxxjl.dyjyxxjl || (cwgbjyxxjl.dyjyxxjl = {}));
})(cwgbjyxxjl || (cwgbjyxxjl = {}));
