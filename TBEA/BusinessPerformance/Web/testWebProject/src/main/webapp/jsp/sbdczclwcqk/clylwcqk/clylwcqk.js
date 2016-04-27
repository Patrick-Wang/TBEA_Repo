/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../sbdczclwcqkdef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.clylwcqk = framework.basic.endpoint.lastId();
    plugin.clylwcqk_xl = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var sbdczclwcqk;
(function (sbdczclwcqk) {
    var clylwcqk;
    (function (clylwcqk) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, date) {
                var curDate = new Date(date);
                var month = curDate.getMonth() + 1;
                var data = [];
                var node;
                var titleNodes = [];
                node = new JQTable.Node("产品", "clylwcqk_cp", true, TextAlign.Left);
                titleNodes.push(node);
                node = new JQTable.Node("上年度", "clylwcqk_snd", true, TextAlign.Center);
                for (var i = month + 1; i <= 12; ++i) {
                    node.append(new JQTable.Node(i + "月", "clylwcqk_snd_" + i));
                }
                if (month != 12) {
                    titleNodes.push(node);
                }
                node = new JQTable.Node("本年度", "clylwcqk_bnd", true, TextAlign.Center);
                for (var i = 1; i <= month; ++i) {
                    node.append(new JQTable.Node(i + "月", "clylwcqk_bnd_" + i));
                }
                titleNodes.push(node);
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            };
            return JQGridAssistantFactory;
        }());
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../clylwcqk/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.clylwcqk;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../clylwcqk/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType,
                    sbdczclwcqkType: this.mSbdczclwcqkType
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
                    sbdczclwcqkType: this.mSbdczclwcqkType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData.data;
                    _this.refresh();
                });
            };
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ShowView.prototype.isSupported = function (compType) {
                if (this.mSbdczclwcqkType == sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_CLYLWCQK_XL) {
                    if (compType == Util.CompanyType.LLGS ||
                        compType == Util.CompanyType.XLC ||
                        compType == Util.CompanyType.DLGS) {
                        return true;
                    }
                }
                else {
                    if (compType == Util.CompanyType.SBGS ||
                        compType == Util.CompanyType.HBGS ||
                        compType == Util.CompanyType.XBC ||
                        compType == Util.CompanyType.TBGS) {
                        return true;
                    }
                }
                return false;
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.clylwcqk_xl, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "铜铝用量情况");
            };
            ShowView.prototype.onEvent = function (e) {
                if (e.road != undefined) {
                    switch (e.road[e.road.length - 1]) {
                        case plugin.clylwcqk_xl:
                            this.mSbdczclwcqkType = sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_CLYLWCQK_XL;
                            break;
                        default:
                            this.mSbdczclwcqkType = sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_CLYLWCQK_XL;
                    }
                }
                return _super.prototype.onEvent.call(this, e);
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
        }(framework.basic.ShowPluginView));
    })(clylwcqk = sbdczclwcqk.clylwcqk || (sbdczclwcqk.clylwcqk = {}));
})(sbdczclwcqk || (sbdczclwcqk = {}));
