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
        BasePluginView.prototype.getExportUrl = function (date, cpType) {
            var st = date.year + "-" + date.month + "-" + date.day;
            return this.pluginGetExportUrl(st, cpType);
        };
        return BasePluginView;
    }());
    chgb.BasePluginView = BasePluginView;
    var BaseEntryPluginView = (function () {
        function BaseEntryPluginView() {
        }
        BaseEntryPluginView.prototype.setOnReadOnlyChangeListener = function (callBack) {
            this.mReadOnlyChange = callBack;
        };
        BaseEntryPluginView.prototype.init = function (opt) {
            this.mOpt = opt;
        };
        BaseEntryPluginView.prototype.hide = function () {
            $("#" + this.mOpt.host).hide();
        };
        BaseEntryPluginView.prototype.show = function () {
            $("#" + this.mOpt.host).show();
        };
        BaseEntryPluginView.prototype.raiseReadOnlyChangeEvent = function (isReadOnly) {
            if (undefined != this.mReadOnlyChange) {
                this.mReadOnlyChange(isReadOnly);
            }
        };
        BaseEntryPluginView.prototype.$ = function (id) {
            return $("#" + this.mOpt.host + " #" + id);
        };
        BaseEntryPluginView.prototype.update = function (start, cpType) {
            var st = start.year + "-" + start.month + "-" + start.day;
            this.pluginUpdate(st, cpType);
        };
        BaseEntryPluginView.prototype.save = function (date, cpType) {
            var dt = date.year + "-" + date.month + "-" + date.day;
            this.pluginSave(dt, cpType);
        };
        BaseEntryPluginView.prototype.submit = function (date, cpType) {
            var dt = date.year + "-" + date.month + "-" + date.day;
            this.pluginSubmit(dt, cpType);
        };
        return BaseEntryPluginView;
    }());
    chgb.BaseEntryPluginView = BaseEntryPluginView;
})(chgb || (chgb = {}));
