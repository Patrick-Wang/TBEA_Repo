/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqk.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.byqcpycssbhgxxfb = framework.basic.endpoint.lastId();
    plugin.byqybysqfysxxfb = framework.basic.endpoint.lastId();
    plugin.byqybyspbcpxxfb = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var cpzlqk;
(function (cpzlqk) {
    var byqcpycssbhgxxfb;
    (function (byqcpycssbhgxxfb) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, title) {
                var nodes = [Node.create({ name: "单位", align: TextAlign.Center })];
                for (var i in title) {
                    nodes.push(Node.create({ name: title[i] }));
                }
                return new JQTable.JQGridAssistant(nodes, gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../byqcpycssbhgxxfb/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.byqcpycssbhgxxfb;
            };
            ShowView.prototype.isSupported = function (compType) {
                return compType == Util.CompanyType.SBGS || compType == Util.CompanyType.HBGS
                    || compType == Util.CompanyType.XBC || compType == Util.CompanyType.TBGS;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../byqcpycssbhgxxfb/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType,
                    bhgType: this.mBhgmxType,
                    ydjd: this.mYdjdType
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
                    bhgType: this.mBhgmxType,
                    ydjd: this.mYdjdType
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
            ShowView.prototype.onEvent = function (e) {
                if (e.road != undefined) {
                    if (plugin.byqybysqfysxxfb == e.road[e.road.length - 1]) {
                        this.mBhgmxType = cpzlqk.ByqBhgType.YBYSQFJYS;
                    }
                    else {
                        this.mBhgmxType = cpzlqk.ByqBhgType.PBCP;
                    }
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.byqybysqfysxxfb, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "110kV及以上产品送试不合格现象分布");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.byqybyspbcpxxfb, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "配变产品送试不合格现象分布");
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist = JQGridAssistantFactory.createTable(name, this.mData.bhglbs);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                tableAssist.mergeRow(0);
                tableAssist.mergeColum(0);
                this.$(name).jqGrid(tableAssist.decorate({
                    datatype: "local",
                    data: tableAssist.getData(this.mData.result),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 20,
                    viewrecords: true
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(cpzlqk.ZlPluginView);
    })(byqcpycssbhgxxfb = cpzlqk.byqcpycssbhgxxfb || (cpzlqk.byqcpycssbhgxxfb = {}));
})(cpzlqk || (cpzlqk = {}));
