/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../sbdczclwcqkdef.ts"/>
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var plugin;
(function (plugin) {
    plugin.cpclwcqk = framework.basic.endpoint.lastId();
    plugin.cpclwcqk_byq = framework.basic.endpoint.lastId();
    plugin.cpclwcqk_xl = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var sbdczclwcqk;
(function (sbdczclwcqk) {
    var cpclwcqk;
    (function (cpclwcqk) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = /** @class */ (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, date) {
                var curDate = new Date(Date.parse(date.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                var data = [];
                var node;
                var titleNodes = [];
                node = new JQTable.Node("产品", "cpclwcqk_cp", true, TextAlign.Left);
                titleNodes.push(node);
                node = new JQTable.Node("上年度", "cpclwcqk_snd", true, TextAlign.Center);
                for (var i = month; i <= 12; ++i) {
                    node.append(new JQTable.Node(i + "月", "cpclwcqk_snd_" + i));
                }
                //if (month != 12) {
                titleNodes.push(node);
                //}
                node = new JQTable.Node("本年度", "cpclwcqk_bnd", true, TextAlign.Center);
                for (var i = 1; i <= month; ++i) {
                    node.append(new JQTable.Node(i + "月", "cpclwcqk_bnd_" + i));
                }
                titleNodes.push(node);
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            };
            return JQGridAssistantFactory;
        }());
        var ShowView = /** @class */ (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjax = new Util.Ajax("/BusinessManagement/cpclwcqk/update.do", false);
                return _this;
            }
            ShowView.prototype.getId = function () {
                return plugin.cpclwcqk;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "/BusinessManagement/cpclwcqk/export.do?" + Util.Ajax.toUrlParam({
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
            ShowView.prototype.pluginGetUnit = function () {
                if (this.mSbdczclwcqkType == sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_CPCLWCQK_BYQ) {
                    return "单位：万kVA（其中电抗器产量万kvar）";
                }
                return "导线：吨；电缆：千米；电缆附件：件";
            };
            ShowView.prototype.isSupported = function (compType) {
                if (this.mSbdczclwcqkType == sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_CPCLWCQK_BYQ) {
                    if (compType == Util.CompanyType.SBGS ||
                        compType == Util.CompanyType.HBGS ||
                        compType == Util.CompanyType.XBC ||
                        compType == Util.CompanyType.TBGS ||
                        compType == Util.CompanyType.BYQCY) {
                        return true;
                    }
                }
                else if (this.mSbdczclwcqkType == sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_CPCLWCQK_XL) {
                    if (compType == Util.CompanyType.LLGS ||
                        compType == Util.CompanyType.XLC ||
                        compType == Util.CompanyType.DLGS ||
                        compType == Util.CompanyType.XLCY) {
                        return true;
                    }
                }
                return false;
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.cpclwcqk_byq, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "产量完成情况");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.cpclwcqk_xl, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "产量完成情况");
            };
            ShowView.prototype.onEvent = function (e) {
                if (e.road != undefined) {
                    switch (e.road[e.road.length - 1]) {
                        case plugin.cpclwcqk_byq:
                            this.mSbdczclwcqkType = sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_CPCLWCQK_BYQ;
                            break;
                        case plugin.cpclwcqk_xl:
                            this.mSbdczclwcqkType = sbdczclwcqk.SbdczclwcqkType.SBDCZCLWCQK_CPCLWCQK_XL;
                            break;
                    }
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150 - 23;
                this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
            };
            ShowView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mDt);
                return this.tableAssist;
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
                this.adjustSize();
            };
            ShowView.ins = new ShowView();
            return ShowView;
        }(framework.basic.ShowPluginView));
    })(cpclwcqk = sbdczclwcqk.cpclwcqk || (sbdczclwcqk.cpclwcqk = {}));
})(sbdczclwcqk || (sbdczclwcqk = {}));
