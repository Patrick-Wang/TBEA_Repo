/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../wlyddqk/wlyddqkdef.ts" />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var ylfxwlyddmlspcs;
(function (ylfxwlyddmlspcs) {
    var byqProductSummary;
    (function (byqProductSummary) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                var curDate = new Date();
                var month = curDate.getMonth() + 1;
                var data = [];
                var node;
                var titleNodes = [];
                node = new JQTable.Node("产品", "byqProductSummary_cp", true, TextAlign.Center);
                titleNodes.push(node);
                node = new JQTable.Node("上年度", "byqProductSummary_snd", true, TextAlign.Center);
                for (var i = month + 1; i <= 12; ++i) {
                    node.append(new JQTable.Node(i + "月", "ylfxwlyddmlspcs_snd_" + i));
                }
                titleNodes.push(node);
                node = new JQTable.Node("本年度", "byqProductSummary_bnd", true, TextAlign.Center);
                for (var i = 1; i <= month; ++i) {
                    node.append(new JQTable.Node(i + "月", "ylfxwlyddmlspcs_bnd_" + i));
                }
                titleNodes.push(node);
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            };
            return JQGridAssistantFactory;
        }());
        var ByqProductSummaryView = (function (_super) {
            __extends(ByqProductSummaryView, _super);
            function ByqProductSummaryView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("byqProductSummary/update.do", false);
            }
            ByqProductSummaryView.newInstance = function () {
                return new ByqProductSummaryView();
            };
            ByqProductSummaryView.prototype.pluginGetExportUrl = function (date, cpType) {
                return "byqProductSummary/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: cpType
                });
            };
            ByqProductSummaryView.prototype.option = function () {
                return this.mOpt;
            };
            ByqProductSummaryView.prototype.pluginUpdate = function (date, cpType) {
                var _this = this;
                this.mDt = date;
                this.mAjax.get({
                    date: date,
                    companyId: cpType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            ByqProductSummaryView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ByqProductSummaryView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("未履约订单毛利水平测算 -产品综合", this);
            };
            ByqProductSummaryView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var curDate = new Date(Date.parse(this.mDt));
                var month = curDate.getMonth() + 1;
                var data = [];
                for (var i = month + 1; i <= 12; ++i) {
                    data.push(["上年度", i + "月"].concat(this.mData[i - month - 1]));
                }
                for (var i = 1; i <= month; ++i) {
                    data.push(["本年度", i + "月"].concat(this.mData[12 - month + i - 1]));
                }
                tableAssist.mergeRow(0);
                tableAssist.mergeTitle();
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
            return ByqProductSummaryView;
        }(BasePluginView));
        byqProductSummary.pluginView = ByqProductSummaryView.newInstance();
    })(byqProductSummary = ylfxwlyddmlspcs.byqProductSummary || (ylfxwlyddmlspcs.byqProductSummary = {}));
})(ylfxwlyddmlspcs || (ylfxwlyddmlspcs = {}));
