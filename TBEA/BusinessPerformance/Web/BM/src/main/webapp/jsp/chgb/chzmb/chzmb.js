var __extends = this.__extends || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    __.prototype = b.prototype;
    d.prototype = new __();
};
var chgb;
(function (chgb) {
    var chzmb;
    (function (chzmb) {
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
        var CHZMBView = (function (_super) {
            __extends(CHZMBView, _super);
            function CHZMBView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("chzmb/update.do", false);
            }
            CHZMBView.newInstance = function () {
                return new CHZMBView();
            };
            CHZMBView.prototype.pluginGetExportUrl = function (date, cpType) {
                return "chzmb/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: cpType
                });
            };
            CHZMBView.prototype.option = function () {
                return this.mOpt;
            };
            CHZMBView.prototype.pluginUpdate = function (date, cpType) {
                var _this = this;
                this.mAjax.get({
                    date: date,
                    companyId: cpType
                }).then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            CHZMBView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            CHZMBView.prototype.init = function (opt) {
                super.init.call(this, opt);
                view.register("存货账面表", this);
            };
            CHZMBView.prototype.updateTable = function () {
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
            return CHZMBView;
        })(BasePluginView);
        chzmb.pluginView = CHZMBView.newInstance();
    })(chzmb = chgb.chzmb || (chgb.chzmb = {}));
})(chgb || (chgb = {}));
