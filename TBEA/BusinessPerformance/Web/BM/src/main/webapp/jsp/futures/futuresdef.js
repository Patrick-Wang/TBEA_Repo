/// <reference path="../util.ts" />
/// <reference path="../../js/jquery/jquery.d.ts" />
var futures;
(function (futures) {
    (function (ContentType) {
        ContentType[ContentType["TABLE_CHART"] = 0] = "TABLE_CHART";
        ContentType[ContentType["TABLE"] = 1] = "TABLE";
    })(futures.ContentType || (futures.ContentType = {}));
    var ContentType = futures.ContentType;
    (function (DisplayType) {
        DisplayType[DisplayType["TABLE"] = 0] = "TABLE";
        DisplayType[DisplayType["CHART"] = 1] = "CHART";
    })(futures.DisplayType || (futures.DisplayType = {}));
    var DisplayType = futures.DisplayType;
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
        BasePluginView.prototype.hide = function () {
            $("#" + this.mOpt.host).hide();
        };
        BasePluginView.prototype.show = function () {
            $("#" + this.mOpt.host).show();
        };
        BasePluginView.prototype.$ = function (id) {
            return $("#" + this.mOpt.host + " #" + id);
        };
        BasePluginView.prototype.update = function () {
            this.pluginUpdate();
        };
        return BasePluginView;
    })();
    futures.BasePluginView = BasePluginView;
})(futures || (futures = {}));
