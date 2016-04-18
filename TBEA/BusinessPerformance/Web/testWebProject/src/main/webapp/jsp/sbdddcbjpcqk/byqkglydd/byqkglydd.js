/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../wlyddqk/wlyddqkdef.ts" />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var sbdddcbjpcqk;
(function (sbdddcbjpcqk) {
    var byqkglydd;
    (function (byqkglydd) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, type) {
                var nodeFirst;
                if (type == wlyddqk.KglyddType.SCDY) {
                    nodeFirst = new JQTable.Node("生产单元（项目公司）", "scdy", true, TextAlign.Center);
                }
                else {
                    nodeFirst = new JQTable.Node("产品类别", "sclb", true, TextAlign.Center);
                }
                return new JQTable.JQGridAssistant([
                    nodeFirst,
                    new JQTable.Node("月产出能力", "rqa")
                        .append(new JQTable.Node("产值", "ba"))
                        .append(new JQTable.Node("产量", "bb")),
                    new JQTable.Node("所有可供履约订单总量产值", "ab")
                        .append(new JQTable.Node("产值", "cca"))
                        .append(new JQTable.Node("产量", "ccb")),
                    new JQTable.Node("当年可供履约订单总量产值", "ac")
                        .append(new JQTable.Node("产值", "da"))
                        .append(new JQTable.Node("产量", "db")),
                    new JQTable.Node("n+1月订单量", "ada")
                        .append(new JQTable.Node("产值", "ea"))
                        .append(new JQTable.Node("产量", "eb"))
                        .append(new JQTable.Node("产能发挥率", "ec")),
                    new JQTable.Node("n+2月订单量", "aeb")
                        .append(new JQTable.Node("产值", "fc"))
                        .append(new JQTable.Node("产量", "fb"))
                        .append(new JQTable.Node("产能发挥率", "fd")),
                    new JQTable.Node("n+3月订单量", "aec")
                        .append(new JQTable.Node("产值", "fc"))
                        .append(new JQTable.Node("产量", "fb"))
                        .append(new JQTable.Node("产能发挥率", "fd")),
                    new JQTable.Node("n+4月订单量", "aed")
                        .append(new JQTable.Node("产值", "fc"))
                        .append(new JQTable.Node("产量", "fb"))
                        .append(new JQTable.Node("产能发挥率", "fd")),
                    new JQTable.Node("n+5月订单量", "aef")
                        .append(new JQTable.Node("产值", "fc"))
                        .append(new JQTable.Node("产量", "fb"))
                        .append(new JQTable.Node("产能发挥率", "fd")),
                    new JQTable.Node("n+6月订单量", "aeg")
                        .append(new JQTable.Node("产值", "fc"))
                        .append(new JQTable.Node("产量", "fb"))
                        .append(new JQTable.Node("产能发挥率", "fd")),
                    new JQTable.Node("n+6月以后可供履约订单", "aeh")
                        .append(new JQTable.Node("产值", "fc"))
                        .append(new JQTable.Node("产量", "fb"))
                        .append(new JQTable.Node("产能发挥率", "fd")),
                    new JQTable.Node("次年及以后可供履约订单排产值", "af")
                        .append(new JQTable.Node("产值", "ga"))
                        .append(new JQTable.Node("产量", "gb")),
                    new JQTable.Node("交货期待定产值", "ag")
                        .append(new JQTable.Node("产值", "ga"))
                        .append(new JQTable.Node("产量", "gb"))
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ByqkglyddView = (function (_super) {
            __extends(ByqkglyddView, _super);
            function ByqkglyddView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../sbdddcbjpcqk/byqkglydd/update.do", false);
            }
            ByqkglyddView.newInstance = function () {
                return new ByqkglyddView();
            };
            ByqkglyddView.prototype.pluginGetExportUrl = function (date) {
                return "../sbdddcbjpcqk/byqkglydd/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    type: this.mType
                });
            };
            ByqkglyddView.prototype.option = function () {
                return this.mOpt;
            };
            ByqkglyddView.prototype.pluginUpdate = function (date) {
                var _this = this;
                this.mDt = date;
                this.mAjax.get({
                    type: this.mType,
                    date: date
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            ByqkglyddView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ByqkglyddView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("变压器可供履约订单变化情况按生产类别", new wlyddqk.TypeViewProxy(this, wlyddqk.KglyddType.SCLB));
                view.register("变压器可供履约订单变化情况按生产单元", new wlyddqk.TypeViewProxy(this, wlyddqk.KglyddType.SCDY));
            };
            ByqkglyddView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name, this.mType);
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
            return ByqkglyddView;
        })(wlyddqk.BasePluginView);
        byqkglydd.pluginView = ByqkglyddView.newInstance();
    })(byqkglydd = sbdddcbjpcqk.byqkglydd || (sbdddcbjpcqk.byqkglydd = {}));
})(sbdddcbjpcqk || (sbdddcbjpcqk = {}));
