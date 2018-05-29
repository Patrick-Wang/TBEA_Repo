/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
var wlyddqk;
(function (wlyddqk) {
    var WlyddType;
    (function (WlyddType) {
        WlyddType[WlyddType["SCDY"] = 0] = "SCDY";
        WlyddType[WlyddType["SCLB"] = 1] = "SCLB";
        WlyddType[WlyddType["YLFX_WLYMLSP_BYQ_ZH"] = 11] = "YLFX_WLYMLSP_BYQ_ZH";
        WlyddType[WlyddType["YLFX_WLYMLSP_BYQ_DYDJ"] = 12] = "YLFX_WLYMLSP_BYQ_DYDJ";
        WlyddType[WlyddType["YLFX_WLYMLSP_BYQ_CPFL"] = 13] = "YLFX_WLYMLSP_BYQ_CPFL";
        WlyddType[WlyddType["YLFX_WLYMLSP_XL_ZH"] = 14] = "YLFX_WLYMLSP_XL_ZH";
        WlyddType[WlyddType["YLFX_WLYMLSP_XL_CPFL"] = 15] = "YLFX_WLYMLSP_XL_CPFL";
        WlyddType[WlyddType["YLFX_WLYMLSP_BYQ_ZZY"] = 16] = "YLFX_WLYMLSP_BYQ_ZZY";
    })(WlyddType = wlyddqk.WlyddType || (wlyddqk.WlyddType = {}));
    var BasePluginView = /** @class */ (function () {
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
        BasePluginView.prototype.pluginGetUnit = function () {
            return undefined;
        };
        BasePluginView.prototype.$ = function (id) {
            return $("#" + this.mOpt.host + " #" + id);
        };
        BasePluginView.prototype.update = function (date, compType) {
            var st = date.year + "-" + date.month + "-" + date.day;
            this.pluginUpdate(st, compType);
        };
        BasePluginView.prototype.getExportUrl = function (date, compType) {
            var st = date.year + "-" + date.month + "-" + date.day;
            return this.pluginGetExportUrl(st, compType);
        };
        BasePluginView.prototype.isSupported = function (compType) {
            return true;
        };
        BasePluginView.prototype.jqgrid = function () {
            return this.$(this.jqgridName());
        };
        BasePluginView.prototype.jqgridHost = function () {
            return this.$(this.mOpt.tb);
        };
        BasePluginView.prototype.jqgridName = function () {
            return this.mOpt.host + this.mOpt.tb + "_jqgrid_real";
        };
        return BasePluginView;
    }());
    wlyddqk.BasePluginView = BasePluginView;
    var TypeViewProxy = /** @class */ (function () {
        function TypeViewProxy(stub, type) {
            this.mStub = stub;
            this.mType = type;
        }
        TypeViewProxy.prototype.adjustSize = function () {
            this.mStub.adjustSize();
        };
        TypeViewProxy.prototype.hide = function () {
            this.mStub.hide();
        };
        TypeViewProxy.prototype.show = function () {
            this.mStub.show();
        };
        TypeViewProxy.prototype.pluginGetUnit = function () {
            this.mStub.setType(this.mType);
            return this.mStub.pluginGetUnit();
        };
        TypeViewProxy.prototype.isSupported = function (compType) {
            this.mStub.setType(this.mType);
            return this.mStub.isSupported(compType);
        };
        TypeViewProxy.prototype.update = function (date, compType) {
            this.mStub.setType(this.mType);
            this.mStub.update(date, compType);
        };
        TypeViewProxy.prototype.refresh = function () {
            this.mStub.refresh();
        };
        TypeViewProxy.prototype.getExportUrl = function (date, compType) {
            return this.mStub.getExportUrl(date, compType);
        };
        return TypeViewProxy;
    }());
    wlyddqk.TypeViewProxy = TypeViewProxy;
    var BaseEntryPluginView = /** @class */ (function () {
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
        BaseEntryPluginView.prototype.update = function (start, compType) {
            var st = start.year + "-" + start.month + "-" + start.day;
            this.pluginUpdate(st, compType);
        };
        BaseEntryPluginView.prototype.save = function (date, compType) {
            var dt = date.year + "-" + date.month + "-" + date.day;
            this.pluginSave(dt, compType);
        };
        BaseEntryPluginView.prototype.submit = function (date, compType) {
            var dt = date.year + "-" + date.month + "-" + date.day;
            this.pluginSubmit(dt, compType);
        };
        BaseEntryPluginView.prototype.isSupported = function (compType) {
            return true;
        };
        BaseEntryPluginView.prototype.jqgrid = function () {
            return this.$(this.jqgridName());
        };
        BaseEntryPluginView.prototype.jqgridHost = function () {
            return this.$(this.mOpt.tb);
        };
        BaseEntryPluginView.prototype.jqgridName = function () {
            return this.mOpt.host + this.mOpt.tb + "_jqgrid_real";
        };
        return BaseEntryPluginView;
    }());
    wlyddqk.BaseEntryPluginView = BaseEntryPluginView;
    var TypeEntryViewProxy = /** @class */ (function () {
        function TypeEntryViewProxy(stub, type) {
            this.mStub = stub;
            this.mType = type;
        }
        TypeEntryViewProxy.prototype.adjustSize = function () {
            this.mStub.adjustSize();
        };
        TypeEntryViewProxy.prototype.isSupported = function (compType) {
            this.mStub.setType(this.mType);
            return this.mStub.isSupported(compType);
        };
        TypeEntryViewProxy.prototype.hide = function () {
            this.mStub.hide();
        };
        TypeEntryViewProxy.prototype.show = function () {
            this.mStub.show();
        };
        TypeEntryViewProxy.prototype.update = function (date, compType) {
            this.mStub.setType(this.mType);
            this.mStub.update(date, compType);
        };
        TypeEntryViewProxy.prototype.refresh = function () {
            this.mStub.refresh();
        };
        TypeEntryViewProxy.prototype.setOnReadOnlyChangeListener = function (callBack) {
            this.mStub.setOnReadOnlyChangeListener(callBack);
        };
        TypeEntryViewProxy.prototype.save = function (date, compType) {
            this.mStub.save(date, compType);
        };
        TypeEntryViewProxy.prototype.submit = function (date, compType) {
            this.mStub.submit(date, compType);
        };
        return TypeEntryViewProxy;
    }());
    wlyddqk.TypeEntryViewProxy = TypeEntryViewProxy;
})(wlyddqk || (wlyddqk = {}));
