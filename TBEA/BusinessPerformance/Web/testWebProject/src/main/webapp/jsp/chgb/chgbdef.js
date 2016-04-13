/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="../../js/jquery/jquery.d.ts" />
var chgb;
(function (chgb) {
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
    }());
    chgb.BasePluginView = BasePluginView;
})(chgb || (chgb = {}));
