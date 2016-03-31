/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../../js/jquery/jquery.d.ts" />
var jcycljg;
(function (jcycljg) {
    (function (DateType) {
        DateType[DateType["DAY"] = 0] = "DAY";
        DateType[DateType["MONTH"] = 1] = "MONTH";
    })(jcycljg.DateType || (jcycljg.DateType = {}));
    var DateType = jcycljg.DateType;
    var BasePluginView = (function () {
        function BasePluginView() {
        }
        BasePluginView.prototype.init = function (opt) {
            this.mOpt = opt;
        };
        BasePluginView.prototype.getDateType = function () {
            return DateType.DAY;
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
        BasePluginView.prototype.update = function (start, end) {
            var st = start.year + "-" + start.month + "-" + start.day;
            var ed = end.year + "-" + end.month + "-" + end.day;
            this.pluginUpdate(st, ed);
        };
        return BasePluginView;
    })();
    jcycljg.BasePluginView = BasePluginView;
})(jcycljg || (jcycljg = {}));
