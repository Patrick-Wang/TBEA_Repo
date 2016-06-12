/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../dzwzgbdef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.dzclcb = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var dzwzgb;
(function (dzwzgb) {
    var dzclcb;
    (function (dzclcb) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, isByq) {
                if (isByq) {
                    return new JQTable.JQGridAssistant([
                        new JQTable.Node("月份", "rqa", true, TextAlign.Center),
                        new JQTable.Node("材料", "ab", true, TextAlign.Center),
                        new JQTable.Node("期货盈亏（万元）", "ac"),
                        new JQTable.Node("市场现货月均价（元/吨）", "ada"),
                        new JQTable.Node("采购月均价（元/吨）（摊入当月期货盈亏）", "adb"),
                        new JQTable.Node("目标利润倒算价（元/吨）", "ae"),
                        new JQTable.Node("采购量（吨）", "af"),
                        new JQTable.Node("期现货合计盈亏（万元）", "ag")
                    ], gridName);
                }
                else {
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
                }
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("dzclcb/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.dzclcb;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "dzclcb/export.do?" + Util.Ajax.toUrlParam({
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
            ShowView.prototype.updateTable = function () {
                var isByq = false;
                if (this.mCompType == Util.CompanyType.SBGS ||
                    this.mCompType == Util.CompanyType.HBGS ||
                    this.mCompType == Util.CompanyType.TBGS ||
                    this.mCompType == Util.CompanyType.XBC) {
                    isByq = true;
                }
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name, isByq);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var data = [];
                if (isByq) {
                    for (var i = 0; i < 12; ++i) {
                        var arr = this.mData[i];
                        data.push([i + 1, "铜"].concat(arr));
                    }
                }
                else {
                    for (var i = 0, j = 0; i < 12; ++i, j += 2) {
                        var arr = this.mData[j];
                        data.push([i + 1, "铜"].concat(arr));
                        arr = this.mData[j + 1];
                        data.push([i + 1, "铝"].concat(arr));
                    }
                }
                tableAssist.mergeRow(0);
                this.$(name).jqGrid(tableAssist.decorate({
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 20,
                    data: tableAssist.getData(data),
                    datatype: "local",
                    viewrecords: true
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(framework.basic.ShowPluginView);
    })(dzclcb = dzwzgb.dzclcb || (dzwzgb.dzclcb = {}));
})(dzwzgb || (dzwzgb = {}));
