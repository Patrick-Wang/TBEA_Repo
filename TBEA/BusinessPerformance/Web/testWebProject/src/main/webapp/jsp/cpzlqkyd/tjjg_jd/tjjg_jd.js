/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkyddef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.tjjg_jd = framework.basic.endpoint.lastId();
    plugin.byq_cp_jd = framework.basic.endpoint.lastId();
    plugin.byq_dw_jd = framework.basic.endpoint.lastId();
    plugin.xl_cp_jd = framework.basic.endpoint.lastId();
    plugin.xl_dydj_jd = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var cpzlqkyd;
(function (cpzlqkyd) {
    var tjjg_jd;
    (function (tjjg_jd) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, date) {
                var curDate = new Date(date);
                var data = [];
                var node;
                var titleNodes = [];
                node = new JQTable.Node("产品类别", "cpzlqkyd_jd_cplb", true, TextAlign.Left);
                titleNodes.push(node);
                node = new JQTable.Node("当月", "cpzlqkyd_jd_dy", true, TextAlign.Center);
                node.append(new JQTable.Node("不合格数(台)", "cpzlqkyd_jd_dy_bhg"));
                node.append(new JQTable.Node("总数(台)", "cpzlqkyd_jd_dy_zs"));
                node.append(new JQTable.Node("合格率", "cpzlqkyd_jd_dy_hgl"));
                titleNodes.push(node);
                node = new JQTable.Node("年度累计", "cpzlqkyd_jd_ndlj", true, TextAlign.Center);
                node.append(new JQTable.Node("不合格数(台)", "cpzlqkyd_jd_ndlj_bhg"));
                node.append(new JQTable.Node("总数(台)", "cpzlqkyd_jd_ndlj_zs"));
                node.append(new JQTable.Node("合格率", "cpzlqkyd_jd_ndlj_hgl"));
                titleNodes.push(node);
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../tjjg/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.tjjg_jd;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../tjjg/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType,
                    type: this.mType
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
                    type: this.mType
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
                if (this.mType == cpzlqkyd.CpzlqkType.CpzlqkType_TJJG_XL_CP ||
                    this.mType == cpzlqkyd.CpzlqkType.CpzlqkType_TJJG_XL_DYDJ) {
                    if (compType == Util.CompanyType.LLGS ||
                        compType == Util.CompanyType.XLC ||
                        compType == Util.CompanyType.DLGS) {
                        return true;
                    }
                }
                else {
                    if (this.mType == cpzlqkyd.CpzlqkType.CpzlqkType_TJJG_BYQ_CP ||
                        this.mType == cpzlqkyd.CpzlqkType.CpzlqkType_TJJG_BYQ_DW) {
                        if (compType == Util.CompanyType.SBGS ||
                            compType == Util.CompanyType.HBGS ||
                            compType == Util.CompanyType.XBC ||
                            compType == Util.CompanyType.TBGS) {
                            return true;
                        }
                    }
                }
                return false;
            };
            ShowView.prototype.init = function (opt) {
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.byq_cp_jd, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "季度按产品统计结果");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.byq_dw_jd, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "季度按单位统计结果");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.xl_cp_jd, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "季度按产品类型统计结果");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.xl_dydj_jd, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "季度按电压等级统计结果");
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist = JQGridAssistantFactory.createTable(name, this.mDt);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                this.$(name).jqGrid(tableAssist.decorate({
                    datatype: "local",
                    data: tableAssist.getData(this.mData),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1400,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 20,
                    viewrecords: true
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(framework.basic.ShowPluginView);
    })(tjjg_jd = cpzlqkyd.tjjg_jd || (cpzlqkyd.tjjg_jd = {}));
})(cpzlqkyd || (cpzlqkyd = {}));
