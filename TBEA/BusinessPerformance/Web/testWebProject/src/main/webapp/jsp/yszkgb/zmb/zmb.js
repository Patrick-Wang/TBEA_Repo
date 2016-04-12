/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../yszkgbdef.ts" />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var yszkgb;
(function (yszkgb) {
    var zmb;
    (function (zmb) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("账面净额", "rq"),
                    new JQTable.Node("坏账准备", "a1"),
                    new JQTable.Node("原值", "a2")
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ZMBView = (function (_super) {
            __extends(ZMBView, _super);
            function ZMBView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("zmb/update.do", false);
            }
            ZMBView.newInstance = function () {
                return new ZMBView();
            };
            ZMBView.prototype.option = function () {
                return this.mOpt;
            };
            ZMBView.prototype.pluginUpdate = function (date, cpType) {
                var _this = this;
                this.mAjax.get({
                    date: date,
                    companyId: cpType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            ZMBView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ZMBView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("应收帐款账面表", this);
            };
            ZMBView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name);
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
                    rowNum: 20,
                    data: tableAssist.getData(this.mData),
                    datatype: "local",
                    viewrecords: true
                }));
            };
            return ZMBView;
        })(yszkgb.BasePluginView);
        zmb.pluginView = ZMBView.newInstance();
    })(zmb = yszkgb.zmb || (yszkgb.zmb = {}));
})(yszkgb || (yszkgb = {}));
