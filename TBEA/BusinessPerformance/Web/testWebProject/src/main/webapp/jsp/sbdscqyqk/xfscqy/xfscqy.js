/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../sbdscqyqkdef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.xfscqy = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var sbdscqyqk;
(function (sbdscqyqk) {
    var xfscqy;
    (function (xfscqy) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月份", "rqa", true, TextAlign.Center),
                    new JQTable.Node("材料", "ab", true, TextAlign.Center),
                    new JQTable.Node("期货盈亏（万元）", "ac"),
                    new JQTable.Node("市场现货月均价（元/吨）", "ada"),
                    new JQTable.Node("采购月均价（元/吨）（摊入当月期货盈亏）", "adb"),
                    new JQTable.Node("三项费用保本价（元/吨）", "adc"),
                    new JQTable.Node("目标利润倒算价（元/吨）", "ae"),
                    new JQTable.Node("采购量（吨）", "af"),
                    new JQTable.Node("期现货合计盈亏", "ag")
                        .append(new JQTable.Node("指导价格按照保本价（万元）", "ah"))
                        .append(new JQTable.Node("指导价格按照目标利润价（万元）", "ai"))
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../xfscqy/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.xfscqy;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../xfscqy/export.do?" + Util.Ajax.toUrlParam({
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
                framework.router.fromEp(this).to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_REGISTER, "大宗材料控成本");
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
                    rowNum: 20,
                    data: tableAssist.getData(this.mData),
                    datatype: "local",
                    viewrecords: true
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(framework.basic.ShowPluginView);
    })(xfscqy = sbdscqyqk.xfscqy || (sbdscqyqk.xfscqy = {}));
})(sbdscqyqk || (sbdscqyqk = {}));
