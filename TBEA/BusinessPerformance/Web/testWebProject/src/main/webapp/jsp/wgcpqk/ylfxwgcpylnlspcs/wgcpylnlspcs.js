/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../wgcpqkdef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.wgcpylnlspcs = framework.basic.endpoint.lastId();
    plugin.byq_zh = framework.basic.endpoint.lastId();
    plugin.byq_mll = framework.basic.endpoint.lastId();
    plugin.xl_zh = framework.basic.endpoint.lastId();
    plugin.xl_cpfl = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var ylfxwgcpylnlspcs;
(function (ylfxwgcpylnlspcs) {
    var wgcpylnlspcs;
    (function (wgcpylnlspcs) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, date) {
                var curDate = new Date(Date.parse(date.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                var data = [];
                var node;
                var titleNodes = [];
                node = new JQTable.Node("产品", "wgcpylnlspcs_cp", true, TextAlign.Left);
                titleNodes.push(node);
                node = new JQTable.Node("上年度", "wgcpylnlspcs_snd", true, TextAlign.Center);
                for (var i = month; i <= 12; ++i) {
                    node.append(new JQTable.Node(i + "月", "wgcpylnlspcs_snd_" + i));
                }
                // if (month != 12) {
                titleNodes.push(node);
                //  }
                node = new JQTable.Node("本年度", "wgcpylnlspcs_bnd", true, TextAlign.Center);
                for (var i = 1; i <= month; ++i) {
                    node.append(new JQTable.Node(i + "月", "wgcpylnlspcs_bnd_" + i));
                }
                titleNodes.push(node);
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../wgcpylnlspcs/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.wgcpylnlspcs;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../wgcpylnlspcs/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType,
                    wgcpqkType: this.mWgcpqkType
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
                    wgcpqkType: this.mWgcpqkType
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
            ShowView.prototype.isSupported = function (compType) {
                if (this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_MLL) {
                    if (compType == Util.CompanyType.BYQCY) {
                        return true;
                    }
                }
                if (this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_XL_ZH ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_XL_CPFL) {
                    if (compType == Util.CompanyType.XLCY) {
                        return true;
                    }
                }
                if (this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_XL_ZH) {
                    if (compType == Util.CompanyType.SBDCYJT) {
                        return true;
                    }
                }
                if (this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_MLL) {
                    if (compType == Util.CompanyType.SBGS ||
                        compType == Util.CompanyType.HBGS ||
                        compType == Util.CompanyType.XBC ||
                        compType == Util.CompanyType.TBGS) {
                        return true;
                    }
                }
                else {
                    if (compType == Util.CompanyType.LLGS ||
                        compType == Util.CompanyType.XLC ||
                        compType == Util.CompanyType.DLGS) {
                        return true;
                    }
                }
                return false;
            };
            ShowView.prototype.pluginGetUnit = function () {
                return "单位：百分比";
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.byq_zh, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "完工产品盈利能力变化趋势-综合");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.byq_mll, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "完工产品盈利能力变化趋势（毛利率）");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.xl_zh, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "完工产品盈利能力变化趋势-综合");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.xl_cpfl, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "完工产品盈利能力变化趋势（毛利率）");
            };
            ShowView.prototype.onEvent = function (e) {
                if (e.road != undefined) {
                    switch (e.road[e.road.length - 1]) {
                        case plugin.byq_zh:
                            this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH;
                            break;
                        case plugin.byq_mll:
                            this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_MLL;
                            break;
                        case plugin.xl_zh:
                            this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_XL_ZH;
                            break;
                        case plugin.xl_cpfl:
                            this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_XL_CPFL;
                            break;
                        default:
                            this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH;
                    }
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name, this.mDt);
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
                    rowNum: 1000,
                    data: tableAssist.getData(this.mData),
                    datatype: "local",
                    viewrecords: true
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(framework.basic.ShowPluginView);
    })(wgcpylnlspcs = ylfxwgcpylnlspcs.wgcpylnlspcs || (ylfxwgcpylnlspcs.wgcpylnlspcs = {}));
})(ylfxwgcpylnlspcs || (ylfxwgcpylnlspcs = {}));
