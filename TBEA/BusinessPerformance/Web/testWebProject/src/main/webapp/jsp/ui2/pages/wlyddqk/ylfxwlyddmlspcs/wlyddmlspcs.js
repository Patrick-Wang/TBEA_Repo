/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../wlyddqkdef.ts" />
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
var wlyddqk;
(function (wlyddqk) {
    var wlyddmlspcs;
    (function (wlyddmlspcs) {
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
                node = new JQTable.Node("产品", "wlyddmlspcs_cp", true, TextAlign.Left);
                titleNodes.push(node);
                node = new JQTable.Node("上年度", "wlyddmlspcs_snd", true, TextAlign.Center);
                for (var i = month; i <= 12; ++i) {
                    node.append(new JQTable.Node(i + "月", "wlyddmlspcs_snd_" + i));
                }
                //if (month != 12) {
                titleNodes.push(node);
                //}
                node = new JQTable.Node("本年度", "wlyddmlspcs_bnd", true, TextAlign.Center);
                for (var i = 1; i <= month; ++i) {
                    node.append(new JQTable.Node(i + "月", "wlyddmlspcs_bnd_" + i));
                }
                titleNodes.push(node);
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            };
            return JQGridAssistantFactory;
        }());
        var WLYDDMLSPCSView = /** @class */ (function (_super) {
            __extends(WLYDDMLSPCSView, _super);
            function WLYDDMLSPCSView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjax = new Util.Ajax("/BusinessManagement/wlydd/wlyddmlspcs/update.do", false);
                return _this;
                //private updateTable():void {
                //    var name = this.option().host + this.option().tb + "_jqgrid_1234";
                //    var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mDt);
                //    var parent = this.$(this.option().tb);
                //    parent.empty();
                //    parent.append("<table id='" + name + "'></table>");
                //
                //
                //    this.$(name).jqGrid(
                //        tableAssist.decorate({
                //            multiselect: false,
                //            drag: false,
                //            resize: false,
                //            height: '100%',
                //            width: 1200,
                //            shrinkToFit: true,
                //            autoScroll: true,
                //            rowNum: 100,
                //            data: tableAssist.getData(this.mData),
                //            datatype: "local",
                //            viewrecords : true
                //        }));
                //}
            }
            WLYDDMLSPCSView.newInstance = function () {
                return new WLYDDMLSPCSView();
            };
            WLYDDMLSPCSView.prototype.pluginGetExportUrl = function (date, cpType) {
                return "/BusinessManagement/wlydd/wlyddmlspcs/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: cpType,
                    type: this.mType
                });
            };
            WLYDDMLSPCSView.prototype.option = function () {
                return this.mOpt;
            };
            WLYDDMLSPCSView.prototype.pluginUpdate = function (date, cpType) {
                var _this = this;
                this.mDt = date;
                this.mAjax.get({
                    date: date,
                    companyId: cpType,
                    type: this.mType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            WLYDDMLSPCSView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            WLYDDMLSPCSView.prototype.pluginGetUnit = function () {
                return "单位：百分比";
            };
            WLYDDMLSPCSView.prototype.isSupported = function (compType) {
                if (this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZH) {
                    if (compType == Util.CompanyType.BYQCY) {
                        return true;
                    }
                }
                if (this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_XL_ZH) {
                    if (compType == Util.CompanyType.XLCY ||
                        compType == Util.CompanyType.SBDCYJT) {
                        return true;
                    }
                }
                if (this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_XL_CPFL) {
                    if (compType == Util.CompanyType.XLCY) {
                        return true;
                    }
                }
                if (this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZZY) {
                    if (compType == Util.CompanyType.BYQCY) {
                        return true;
                    }
                }
                if (this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZH ||
                    this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_DYDJ ||
                    this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_CPFL ||
                    this.mType == wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZZY) {
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
            WLYDDMLSPCSView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("未履约订单毛利水平测算(转型业务口径)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZH));
                //view.register("未履约订单毛利水平测算(制造主业-电压等级口径)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_DYDJ));
                //view.register("未履约订单毛利水平测算(制造主业-产品类别口径)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_CPFL));
                view.register("未履约订单毛利水平测算(制造业)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_BYQ_ZZY));
                view.register("未履约订单毛利水平测算(转型业务口径)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_XL_ZH));
                view.register("未履约订单毛利水平测算(制造业)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.YLFX_WLYMLSP_XL_CPFL));
            };
            WLYDDMLSPCSView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
            };
            WLYDDMLSPCSView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mDt);
                return this.tableAssist;
            };
            WLYDDMLSPCSView.prototype.updateTable = function () {
                this.createJqassist();
                this.tableAssist.create({
                    data: this.mData,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 2000,
                    autoScroll: true
                });
                this.adjustSize();
            };
            return WLYDDMLSPCSView;
        }(wlyddqk.BasePluginView));
        wlyddmlspcs.pluginView = WLYDDMLSPCSView.newInstance();
    })(wlyddmlspcs = wlyddqk.wlyddmlspcs || (wlyddqk.wlyddmlspcs = {}));
})(wlyddqk || (wlyddqk = {}));
