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
    (function (JcycljgType) {
        JcycljgType[JcycljgType["YSJS"] = 0] = "YSJS";
        JcycljgType[JcycljgType["GGP"] = 1] = "GGP";
        JcycljgType[JcycljgType["GJYY"] = 2] = "GJYY";
        JcycljgType[JcycljgType["TKS"] = 3] = "TKS";
        JcycljgType[JcycljgType["JT"] = 4] = "JT";
        JcycljgType[JcycljgType["FGC"] = 5] = "FGC";
        JcycljgType[JcycljgType["LZBB"] = 6] = "LZBB";
        JcycljgType[JcycljgType["ZHB"] = 7] = "ZHB";
        JcycljgType[JcycljgType["GX"] = 8] = "GX";
        JcycljgType[JcycljgType["PVCSZ"] = 9] = "PVCSZ";
        JcycljgType[JcycljgType["DMDJYX"] = 10] = "DMDJYX";
        JcycljgType[JcycljgType["EVA"] = 11] = "EVA";
        JcycljgType[JcycljgType["JKZJ"] = 12] = "JKZJ";
        JcycljgType[JcycljgType["MYZS"] = 13] = "MYZS";
        JcycljgType[JcycljgType["LWG"] = 14] = "LWG";
    })(jcycljg.JcycljgType || (jcycljg.JcycljgType = {}));
    var JcycljgType = jcycljg.JcycljgType;
})(jcycljg || (jcycljg = {}));
