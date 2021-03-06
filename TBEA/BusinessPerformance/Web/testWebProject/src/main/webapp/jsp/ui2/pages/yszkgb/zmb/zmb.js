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
/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../messageBox.ts" />
///<reference path="../../dateSelector.ts"/>
var yszkgb;
(function (yszkgb) {
    var zmb;
    (function (zmb) {
        var JQGridAssistantFactory = /** @class */ (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("账面净额", "rq"),
                    new JQTable.Node("坏账准备", "aa"),
                    new JQTable.Node("原值", "ab")
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var SimpleView = /** @class */ (function (_super) {
            __extends(SimpleView, _super);
            function SimpleView(id) {
                var _this = _super.call(this, id) || this;
                _this.mAjax = new Util.Ajax("/BusinessManagement/yszkgb/zmb/update.do", false);
                return _this;
            }
            SimpleView.prototype.pluginGetExportUrl = function (date, cpType) {
                return "/BusinessManagement/yszkgb/zmb/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: cpType
                });
            };
            SimpleView.prototype.option = function () {
                return this.mOpt;
            };
            SimpleView.prototype.pluginUpdate = function (date, cpType) {
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
            SimpleView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            SimpleView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                framework.router.to(Util.FAMOUS_VIEW).send(Util.MSG_REG, { name: "应收帐款账面表", plugin: this });
            };
            SimpleView.prototype.adjustSize = function () {
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
            SimpleView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
                return this.tableAssist;
            };
            SimpleView.prototype.updateTable = function () {
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
            SimpleView.ins = new SimpleView("zmb");
            return SimpleView;
        }(yszkgb.BasePluginView));
        zmb.SimpleView = SimpleView;
    })(zmb = yszkgb.zmb || (yszkgb.zmb = {}));
})(yszkgb || (yszkgb = {}));
