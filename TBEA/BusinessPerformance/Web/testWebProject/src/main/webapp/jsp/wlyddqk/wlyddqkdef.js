/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="../../js/jquery/jquery.d.ts" />
var wlyddqk;
(function (wlyddqk) {
    (function (KglyddType) {
        KglyddType[KglyddType["SCDY"] = 0] = "SCDY";
        KglyddType[KglyddType["SCLB"] = 1] = "SCLB";
    })(wlyddqk.KglyddType || (wlyddqk.KglyddType = {}));
    var KglyddType = wlyddqk.KglyddType;
    var BasePluginView = (function () {
        function BasePluginView() {
        }
        BasePluginView.prototype.init = function (opt) {
            this.mOpt = opt;
        };
        BasePluginView.prototype.setType = function (type) {
            this.mType = type;
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
        BasePluginView.prototype.update = function (date) {
            var st = date.year + "-" + date.month + "-" + date.day;
            this.pluginUpdate(st);
        };
        BasePluginView.prototype.getExportUrl = function (date) {
            var st = date.year + "-" + date.month + "-" + date.day;
            return this.pluginGetExportUrl(st);
        };
        return BasePluginView;
    })();
    wlyddqk.BasePluginView = BasePluginView;
    var TypeViewProxy = (function () {
        function TypeViewProxy(stub, type) {
            this.mStub = stub;
            this.mType = type;
        }
        TypeViewProxy.prototype.hide = function () {
            this.mStub.hide();
        };
        TypeViewProxy.prototype.show = function () {
            this.mStub.show();
        };
        TypeViewProxy.prototype.update = function (date) {
            this.mStub.setType(this.mType);
            this.mStub.update(date);
        };
        TypeViewProxy.prototype.refresh = function () {
            this.mStub.refresh();
        };
        TypeViewProxy.prototype.getExportUrl = function (date) {
            return this.mStub.getExportUrl(date);
        };
        return TypeViewProxy;
    })();
    wlyddqk.TypeViewProxy = TypeViewProxy;
    var BaseEntryPluginView = (function () {
        function BaseEntryPluginView() {
        }
        BaseEntryPluginView.prototype.setOnReadOnlyChangeListener = function (callBack) {
            this.mReadOnlyChange = callBack;
        };
        BaseEntryPluginView.prototype.init = function (opt) {
            this.mOpt = opt;
        };
        BaseEntryPluginView.prototype.setType = function (type) {
            this.mType = type;
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
        BaseEntryPluginView.prototype.update = function (start) {
            var st = start.year + "-" + start.month + "-" + start.day;
            this.pluginUpdate(st);
        };
        BaseEntryPluginView.prototype.save = function (date) {
            var dt = date.year + "-" + date.month + "-" + date.day;
            this.pluginSave(dt);
        };
        BaseEntryPluginView.prototype.submit = function (date) {
            var dt = date.year + "-" + date.month + "-" + date.day;
            this.pluginSubmit(dt);
        };
        return BaseEntryPluginView;
    })();
    wlyddqk.BaseEntryPluginView = BaseEntryPluginView;
    var TypeEntryViewProxy = (function () {
        function TypeEntryViewProxy(stub, type) {
            this.mStub = stub;
            this.mType = type;
        }
        TypeEntryViewProxy.prototype.hide = function () {
            this.mStub.hide();
        };
        TypeEntryViewProxy.prototype.show = function () {
            this.mStub.show();
        };
        TypeEntryViewProxy.prototype.update = function (date) {
            this.mStub.setType(this.mType);
            this.mStub.update(date);
        };
        TypeEntryViewProxy.prototype.refresh = function () {
            this.mStub.refresh();
        };
        TypeEntryViewProxy.prototype.setOnReadOnlyChangeListener = function (callBack) {
            this.mStub.setOnReadOnlyChangeListener(callBack);
        };
        TypeEntryViewProxy.prototype.save = function (date) {
            this.mStub.save(date);
        };
        TypeEntryViewProxy.prototype.submit = function (date) {
            this.mStub.submit(date);
        };
        return TypeEntryViewProxy;
    })();
    wlyddqk.TypeEntryViewProxy = TypeEntryViewProxy;
})(wlyddqk || (wlyddqk = {}));
