/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../cbfxdef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.dmcbfx = framework.basic.endpoint.lastId();
    plugin.dmcbfxProxy = framework.basic.endpoint.lastId();
    plugin.dmcbqsfxProxy = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var cbfx;
(function (cbfx) {
    var dmcbfx;
    (function (dmcbfx) {
        var TextAlign = JQTable.TextAlign;
        var EndpointProxy = framework.basic.EndpointProxy;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            //吨煤成本分析表
            JQGridAssistantFactory.createTable = function (gridName, year, month) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("成本构成", "rqa", true, TextAlign.Center),
                    new JQTable.Node(year + "年" + month + "月度完成情况", "ab")
                        .append(new JQTable.Node("计划", "jh"))
                        .append(new JQTable.Node("实际", "sj"))
                        .append(new JQTable.Node("完成比", "wcb")),
                    new JQTable.Node("同期对比", "ac", true, TextAlign.Center)
                        .append(new JQTable.Node("上年同期", "ad"))
                        .append(new JQTable.Node("增减额", "ae"))
                        .append(new JQTable.Node("完成比", "af"))
                ], gridName);
            };
            //吨煤成本趋势分析表
            JQGridAssistantFactory.createQsTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("成本构成", "aa", true, TextAlign.Center),
                    new JQTable.Node("1月", "ab"),
                    new JQTable.Node("2月", "ac"),
                    new JQTable.Node("3月", "ad"),
                    new JQTable.Node("一季度加权（账面累计）", "ae"),
                    new JQTable.Node("4月", "af"),
                    new JQTable.Node("5月", "ag"),
                    new JQTable.Node("6月", "ah"),
                    new JQTable.Node("上半年加权", "ai"),
                    new JQTable.Node("7月", "aj"),
                    new JQTable.Node("8月", "ak"),
                    new JQTable.Node("9月", "al"),
                    new JQTable.Node("前三季度加权", "am"),
                    new JQTable.Node("10月", "an"),
                    new JQTable.Node("11月", "ao"),
                    new JQTable.Node("12月", "ap"),
                    new JQTable.Node("全年加权", "aq")
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../dmcbfx/update.do", false);
            }
            ShowView.prototype.onEvent = function (e) {
                if (e.road != undefined) {
                    if (e.road[e.road.length - 1] == plugin.dmcbfxProxy) {
                        this.mCurCbfxType = cbfx.CbfxType.dmcbfx;
                    }
                    else {
                        this.mCurCbfxType = cbfx.CbfxType.dmcbqsfx;
                    }
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.getId = function () {
                return plugin.dmcbfx;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../dmcbfx/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType,
                    type: this.mCurCbfxType
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
                    companyId: compType,
                    type: this.mCurCbfxType
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
                    .fromEp(new EndpointProxy(plugin.dmcbfxProxy, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "吨煤成本分析表");
                framework.router
                    .fromEp(new EndpointProxy(plugin.dmcbqsfxProxy, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "吨煤成本趋势分析表");
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
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist;
                if (this.mCurCbfxType == cbfx.CbfxType.dmcbfx) {
                    tableAssist = JQGridAssistantFactory.createTable(name, this.getYear(), this.getMonth());
                }
                else {
                    tableAssist = JQGridAssistantFactory.createQsTable(name);
                }
                //let data : string[][] = [
                //    ["土方剥离爆破成本"],
                //    ["原煤爆破成本"],
                //    ["原煤采运成本"],
                //    ["回筛倒运成本"],
                //    ["装车成本"],
                //    ["直接成本合计"],
                //    ["非可控成本"],
                //    ["可控成本"],
                //    ["制造费用小计"],
                //    ["技改财务费用"],
                //    ["生产成本合计"]
                //];
                //
                //for (let i = 0; i < data.length; ++i){
                //    data[i] = data[i].concat(this.mData[i]);
                //}
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
    })(dmcbfx = cbfx.dmcbfx || (cbfx.dmcbfx = {}));
})(cbfx || (cbfx = {}));
