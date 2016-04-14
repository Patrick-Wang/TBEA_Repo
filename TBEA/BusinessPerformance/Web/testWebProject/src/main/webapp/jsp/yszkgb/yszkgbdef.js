var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="../../js/jquery/jquery.d.ts" />
var yszkgb;
(function (yszkgb) {
    var BasePluginView = (function () {
        function BasePluginView() {
        }
        BasePluginView.prototype.init = function (opt) {
            this.mOpt = opt;
        };
        BasePluginView.prototype.hide = function () {
            $("#" + this.mOpt.host).hide();
        };
        BasePluginView.prototype.show = function () {
            $("#" + this.mOpt.host).show();
        };
        BasePluginView.prototype.$ = function (id) {
            return $("#" + this.mOpt.host + " #" + id);
        };
        BasePluginView.prototype.update = function (start, cpType) {
            var st = start.year + "-" + start.month + "-" + start.day;
            this.pluginUpdate(st, cpType);
        };
        return BasePluginView;
    })();
    yszkgb.BasePluginView = BasePluginView;
    var EntryPluginView = (function (_super) {
        __extends(EntryPluginView, _super);
        function EntryPluginView() {
            _super.apply(this, arguments);
        }
        EntryPluginView.prototype.save = function (date, cpType) {
            var dt = date.year + "-" + date.month + "-" + date.day;
            this.pluginSave(dt, cpType);
        };
        EntryPluginView.prototype.submit = function (date, cpType) {
            var dt = date.year + "-" + date.month + "-" + date.day;
            this.pluginSubmit(dt, cpType);
        };
        return EntryPluginView;
    })(BasePluginView);
    yszkgb.EntryPluginView = EntryPluginView;
})(yszkgb || (yszkgb = {}));
