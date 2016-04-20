var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../../js/jquery/jquery.d.ts" />
/// <reference path="../route/route.ts" />
///<reference path="../../unitedSelector.ts"/>
var framework;
(function (framework) {
    var basic;
    (function (basic) {
        var FrameEvent;
        (function (FrameEvent) {
            var eventBase = 9988392;
            FrameEvent.FE_INIT_EVENT = eventBase++;
            FrameEvent.FE_REGISTER = eventBase++;
            FrameEvent.FE_EXPORT_EXCEL = eventBase++;
        })(FrameEvent = basic.FrameEvent || (basic.FrameEvent = {}));
        var BasicEndpoint = (function () {
            function BasicEndpoint() {
                framework.route.router.register(this);
            }
            BasicEndpoint.prototype.onEvent = function (e) {
                switch (e.id) {
                    case FrameEvent.FE_INIT_EVENT:
                        this.onInitialize(e.data);
                        break;
                }
            };
            return BasicEndpoint;
        })();
        basic.BasicEndpoint = BasicEndpoint;
        var FrameView = (function (_super) {
            __extends(FrameView, _super);
            function FrameView() {
                _super.apply(this, arguments);
            }
            FrameView.prototype.getId = function () {
                return FrameView.FRAME_ID;
            };
            FrameView.prototype.onInitialize = function (opt) {
                this.init(opt);
            };
            FrameView.prototype.onEvent = function (e) {
                _super.prototype.onEvent.call(this, e);
                switch (e.id) {
                    case FrameEvent.FE_REGISTER:
                        this.register(e.data.name, e.data.pluginView);
                        break;
                    case FrameEvent.FE_EXPORT_EXCEL:
                        this.exportExcel(e.data);
                        break;
                }
            };
            FrameView.FRAME_ID = 0;
            return FrameView;
        })(BasicEndpoint);
        basic.FrameView = FrameView;
        var BasePluginView = (function () {
            function BasePluginView() {
                framework.route.router.register(this);
            }
            BasePluginView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case BasePluginView.INIT_EVENT:
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
            BasePluginView.INIT_EVENT = 9988392;
            return BasePluginView;
        })();
        basic.BasePluginView = BasePluginView;
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
            return BaseEntryPluginView;
        })();
        basic.BaseEntryPluginView = BaseEntryPluginView;
        var TypeEntryViewProxy = (function () {
            function TypeEntryViewProxy(stub, type) {
                this.mStub = stub;
                this.mType = type;
            }
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
        })();
        basic.TypeEntryViewProxy = TypeEntryViewProxy;
    })(basic = framework.basic || (framework.basic = {}));
})(framework || (framework = {}));
