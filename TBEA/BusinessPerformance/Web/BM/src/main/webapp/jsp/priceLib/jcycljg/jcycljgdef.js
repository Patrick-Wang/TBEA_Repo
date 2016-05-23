var jcycljg;
(function (jcycljg) {
    (function (DateType) {
        DateType[DateType["DAY"] = 0] = "DAY";
        DateType[DateType["MONTH"] = 1] = "MONTH";
    })(jcycljg.DateType || (jcycljg.DateType = {}));
    var DateType = jcycljg.DateType;
    (function (ContentType) {
        ContentType[ContentType["TABLE_CHART"] = 0] = "TABLE_CHART";
        ContentType[ContentType["TABLE"] = 1] = "TABLE";
    })(jcycljg.ContentType || (jcycljg.ContentType = {}));
    var ContentType = jcycljg.ContentType;
    (function (DisplayType) {
        DisplayType[DisplayType["TABLE"] = 0] = "TABLE";
        DisplayType[DisplayType["CHART"] = 1] = "CHART";
    })(jcycljg.DisplayType || (jcycljg.DisplayType = {}));
    var DisplayType = jcycljg.DisplayType;
    var BasePluginView = (function () {
        function BasePluginView() {
        }
        BasePluginView.prototype.init = function (opt) {
            this.mOpt = opt;
        };
        BasePluginView.prototype.switchDisplayType = function (type) {
            this.mDispType = type;
            switch (type) {
                case DisplayType.TABLE:
                    this.$(this.mOpt.ctarea).css("display", "none");
                    this.$(this.mOpt.tbarea).css("display", "");
                    this.refresh();
                    break;
                case DisplayType.CHART:
                    this.$(this.mOpt.tbarea).css("display", "none");
                    this.$(this.mOpt.ctarea).css("display", "");
                    this.refresh();
                    break;
                default:
                    break;
            }
        };
        BasePluginView.prototype.getContentType = function () {
            return ContentType.TABLE_CHART;
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
    }());
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
        JcycljgType[JcycljgType["PMICPIPPI"] = 15] = "PMICPIPPI";
        JcycljgType[JcycljgType["YHJZLL"] = 16] = "YHJZLL";
    })(jcycljg.JcycljgType || (jcycljg.JcycljgType = {}));
    var JcycljgType = jcycljg.JcycljgType;
})(jcycljg || (jcycljg = {}));
