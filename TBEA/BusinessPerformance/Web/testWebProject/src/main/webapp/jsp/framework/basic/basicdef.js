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
            function lastEvent() {
                return ++eventBase;
            }
            FrameEvent.lastEvent = lastEvent;
            FrameEvent.FE_INIT_EVENT = lastEvent();
            FrameEvent.FE_REGISTER = lastEvent();
            FrameEvent.FE_EXPORT_EXCEL = lastEvent();
            FrameEvent.FE_SHOW = lastEvent();
            FrameEvent.FE_HIDE = lastEvent();
            FrameEvent.FE_REFRESH = lastEvent();
            FrameEvent.FE_IS_COMPANY_SUPPORTED = lastEvent();
            FrameEvent.FE_UPDATE = lastEvent();
            FrameEvent.FE_GET_EXPORTURL = lastEvent();
            FrameEvent.FE_SAVE = lastEvent();
            FrameEvent.FE_SUBMIT = lastEvent();
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
                        this.register(e.data.name, e.data.plugin);
                        break;
                }
            };
            FrameView.FRAME_ID = 0;
            return FrameView;
        })(BasicEndpoint);
        basic.FrameView = FrameView;
        var BasePluginView = (function (_super) {
            __extends(BasePluginView, _super);
            function BasePluginView() {
                _super.apply(this, arguments);
            }
            BasePluginView.prototype.onInitialize = function (opt) {
                this.init(opt);
            };
            BasePluginView.prototype.onEvent = function (e) {
                var val = _super.prototype.onEvent.call(this, e);
                switch (e.id) {
                    case FrameEvent.FE_SHOW:
                        this.show();
                        break;
                    case FrameEvent.FE_HIDE:
                        this.hide();
                        break;
                    case FrameEvent.FE_REFRESH:
                        this.refresh();
                        break;
                    case FrameEvent.FE_UPDATE:
                        {
                            var date = e.data.date;
                            var st = date.year + "-" + date.month + "-" + date.day;
                            this.pluginUpdate(st, e.data.compType);
                        }
                        break;
                    case FrameEvent.FE_IS_COMPANY_SUPPORTED:
                        val = this.isSupported(e.data);
                        break;
                    default:
                        break;
                }
                return val;
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
            BasePluginView.prototype.isSupported = function (compType) {
                return true;
            };
            return BasePluginView;
        })(BasicEndpoint);
        basic.BasePluginView = BasePluginView;
        var ShowPluginView = (function (_super) {
            __extends(ShowPluginView, _super);
            function ShowPluginView() {
                _super.apply(this, arguments);
            }
            ShowPluginView.prototype.onEvent = function (e) {
                var val = _super.prototype.onEvent.call(this, e);
                switch (e.id) {
                    case FrameEvent.FE_GET_EXPORTURL:
                        {
                            var date = e.data.date;
                            var st = date.year + "-" + date.month + "-" + date.day;
                            val = this.pluginGetExportUrl(st, e.data.compType);
                        }
                        break;
                    default:
                        break;
                }
                return val;
            };
            return ShowPluginView;
        })(BasicEndpoint);
        basic.ShowPluginView = ShowPluginView;
        var EntryPluginView = (function (_super) {
            __extends(EntryPluginView, _super);
            function EntryPluginView() {
                _super.apply(this, arguments);
            }
            EntryPluginView.prototype.onEvent = function (e) {
                var val = _super.prototype.onEvent.call(this, e);
                switch (e.id) {
                    case FrameEvent.FE_SAVE:
                        {
                            var date = e.data.date;
                            var st = date.year + "-" + date.month + "-" + date.day;
                            this.pluginSave(st, e.data.compType);
                        }
                        break;
                    case FrameEvent.FE_SUBMIT:
                        {
                            var date = e.data.date;
                            var st = date.year + "-" + date.month + "-" + date.day;
                            this.pluginSubmit(st, e.data.compType);
                        }
                        break;
                    default:
                        break;
                }
                return val;
            };
            return EntryPluginView;
        })(BasicEndpoint);
        basic.EntryPluginView = EntryPluginView;
    })(basic = framework.basic || (framework.basic = {}));
})(framework || (framework = {}));
