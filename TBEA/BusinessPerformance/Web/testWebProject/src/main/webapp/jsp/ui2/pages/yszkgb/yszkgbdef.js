/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
///<reference path="yszkgb.ts"/>
var yszkgb;
(function (yszkgb) {
    var BasePluginView = (function () {
        function BasePluginView(id) {
            this.mId = id;
            framework.router.register(this);
        }
        BasePluginView.prototype.getId = function () {
            return this.mId;
        };
        BasePluginView.prototype.onEvent = function (e) {
            switch (e.id) {
                case Util.MSG_INIT:
                    this.init(e.data);
                    break;
            }
        };
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
        BasePluginView.prototype.update = function (date, cpType) {
            var st = date.year + "-" + date.month + "-" + date.day;
            this.pluginUpdate(st, cpType);
        };
        BasePluginView.prototype.getExportUrl = function (date, cpType) {
            var st = date.year + "-" + date.month + "-" + date.day;
            return this.pluginGetExportUrl(st, cpType);
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
    })();
    yszkgb.BasePluginView = BasePluginView;
    var BaseEntryPluginView = (function () {
        function BaseEntryPluginView(id) {
            this.mId = id;
            framework.router.register(this);
        }
        BaseEntryPluginView.prototype.setOnReadOnlyChangeListener = function (callBack) {
            this.mReadOnlyChange = callBack;
        };
        BaseEntryPluginView.prototype.getId = function () {
            return this.mId;
        };
        BaseEntryPluginView.prototype.onEvent = function (e) {
            switch (e.id) {
                case Util.MSG_INIT:
                    this.init(e.data);
                    break;
            }
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
    })();
    yszkgb.BaseEntryPluginView = BaseEntryPluginView;
})(yszkgb || (yszkgb = {}));
