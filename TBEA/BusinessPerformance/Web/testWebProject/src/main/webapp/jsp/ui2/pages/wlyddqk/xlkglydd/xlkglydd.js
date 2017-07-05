/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../wlyddqk/wlyddqkdef.ts" />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var wlyddqk;
(function (wlyddqk) {
    var xlkglydd;
    (function (xlkglydd) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, type) {
                var nodeFirst;
                if (type == wlyddqk.WlyddType.SCDY) {
                    nodeFirst = new JQTable.Node("生产单元（项目公司）", "scdy", true, TextAlign.Center);
                }
                else {
                    nodeFirst = new JQTable.Node("产品类别", "sclb", true, TextAlign.Center);
                }
                return new JQTable.JQGridAssistant([
                    nodeFirst,
                    new JQTable.Node("月产出能力（产值）", "rqa"),
                    new JQTable.Node("可供履约订单总额", "ab"),
                    new JQTable.Node("当年可供履约订单总量", "ac"),
                    new JQTable.Node("n+1月订单量", "ada")
                        .append(new JQTable.Node("已排产", "ba"))
                        .append(new JQTable.Node("未排产", "bc"))
                        .append(new JQTable.Node("产能发挥率", "bb")),
                    new JQTable.Node("n+2月订单量", "adb")
                        .append(new JQTable.Node("已排产", "ba"))
                        .append(new JQTable.Node("未排产", "bc"))
                        .append(new JQTable.Node("产能发挥率", "bb")),
                    new JQTable.Node("n+3月订单量", "adc")
                        .append(new JQTable.Node("已排产", "ba"))
                        .append(new JQTable.Node("未排产", "bc"))
                        .append(new JQTable.Node("产能发挥率", "bb")),
                    new JQTable.Node("n+3月以后履约订单", "ae"),
                    new JQTable.Node("次年及以后可供履约订单排产值", "af"),
                    new JQTable.Node("交货期待定产值", "ag"),
                    new JQTable.Node("外协", "ah")
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var XlkglyddView = (function (_super) {
            __extends(XlkglyddView, _super);
            function XlkglyddView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("/BusinessManagement/sbdddcbjpcqk/xlkglydd/update.do", false);
            }
            XlkglyddView.prototype.isSupported = function (compType) {
                if (compType == Util.CompanyType.LLGS || compType == Util.CompanyType.XLC || compType == Util.CompanyType.DLGS) {
                    return true;
                }
                return false;
            };
            XlkglyddView.newInstance = function () {
                return new XlkglyddView();
            };
            XlkglyddView.prototype.pluginGetExportUrl = function (date, compType) {
                return "/BusinessManagement/sbdddcbjpcqk/xlkglydd/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    type: this.mType,
                    companyId: compType
                });
            };
            XlkglyddView.prototype.option = function () {
                return this.mOpt;
            };
            XlkglyddView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mAjax.get({
                    type: this.mType,
                    date: date,
                    companyId: compType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            XlkglyddView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            XlkglyddView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("可供履约订单情况(产品类别口径)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.SCLB));
                view.register("可供履约订单情况(生产单元口径)", new wlyddqk.TypeViewProxy(this, wlyddqk.WlyddType.SCDY));
            };
            XlkglyddView.prototype.adjustSize = function () {
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
            XlkglyddView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mType);
                return this.tableAssist;
            };
            XlkglyddView.prototype.updateTable = function () {
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
            return XlkglyddView;
        })(wlyddqk.BasePluginView);
        xlkglydd.pluginView = XlkglyddView.newInstance();
    })(xlkglydd = wlyddqk.xlkglydd || (wlyddqk.xlkglydd = {}));
})(wlyddqk || (wlyddqk = {}));
