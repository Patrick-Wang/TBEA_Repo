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
        var endpoint;
        (function (endpoint) {
            endpoint.lastId = (function (idBase) {
                return function () {
                    return ++idBase;
                };
            })(100002);
            endpoint.FRAME_ID = endpoint.lastId();
        })(endpoint = basic.endpoint || (basic.endpoint = {}));
        var FrameEvent;
        (function (FrameEvent) {
            FrameEvent.lastEvent = (function (idBase) {
                return function () {
                    return ++idBase;
                };
            })(9988392);
            FrameEvent.FE_INIT_EVENT = FrameEvent.lastEvent();
            FrameEvent.FE_REGISTER = FrameEvent.lastEvent();
            FrameEvent.FE_SHOW = FrameEvent.lastEvent();
            FrameEvent.FE_HIDE = FrameEvent.lastEvent();
            FrameEvent.FE_REFRESH = FrameEvent.lastEvent();
            FrameEvent.FE_IS_COMPANY_SUPPORTED = FrameEvent.lastEvent();
            FrameEvent.FE_UPDATE = FrameEvent.lastEvent();
            FrameEvent.FE_GET_EXPORTURL = FrameEvent.lastEvent();
            FrameEvent.FE_SAVE = FrameEvent.lastEvent();
            FrameEvent.FE_SUBMIT = FrameEvent.lastEvent();
            FrameEvent.FE_PROXY = FrameEvent.lastEvent();
            FrameEvent.FE_APPROVE = FrameEvent.lastEvent();
            FrameEvent.FE_UNAPPROVE = FrameEvent.lastEvent();
            FrameEvent.FE_NOT_SUBMITTED = FrameEvent.lastEvent();
            FrameEvent.FE_SUBMITTED = FrameEvent.lastEvent();
            FrameEvent.FE_APPROVED = FrameEvent.lastEvent();
        })(FrameEvent = basic.FrameEvent || (basic.FrameEvent = {}));
        var BasicEndpoint = (function () {
            function BasicEndpoint() {
                framework.router.register(this);
            }
            BasicEndpoint.prototype.onEvent = function (e) {
                switch (e.id) {
                    case FrameEvent.FE_INIT_EVENT:
                        this.onInitialize(e.data);
                        break;
                }
                return true;
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
                return endpoint.FRAME_ID;
            };
            FrameView.prototype.onInitialize = function (opt) {
                this.init(opt);
            };
            FrameView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case FrameEvent.FE_REGISTER:
                        this.register(e.data, e.from);
                        break;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            return FrameView;
        })(BasicEndpoint);
        basic.FrameView = FrameView;
        var BasePluginView = (function (_super) {
            __extends(BasePluginView, _super);
            function BasePluginView() {
                _super.apply(this, arguments);
            }
            BasePluginView.prototype.onInitialize = function (opt) {
                this.mOpt = opt;
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
        })(BasePluginView);
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
        })(BasePluginView);
        basic.EntryPluginView = EntryPluginView;
        var ApprovePluginView = (function (_super) {
            __extends(ApprovePluginView, _super);
            function ApprovePluginView() {
                _super.apply(this, arguments);
            }
            ApprovePluginView.prototype.onEvent = function (e) {
                var val = _super.prototype.onEvent.call(this, e);
                switch (e.id) {
                    case FrameEvent.FE_APPROVE:
                        {
                            var date = e.data.date;
                            var st = date.year + "-" + date.month + "-" + date.day;
                            this.pluginApprove(st, e.data.compType);
                        }
                        break;
                    case FrameEvent.FE_UNAPPROVE:
                        {
                            var date = e.data.date;
                            var st = date.year + "-" + date.month + "-" + date.day;
                            this.pluginUnapprove(st, e.data.compType);
                        }
                        break;
                    default:
                        break;
                }
                return val;
            };
            return ApprovePluginView;
        })(BasePluginView);
        basic.ApprovePluginView = ApprovePluginView;
        var EndpointProxy = (function () {
            function EndpointProxy(id, stub) {
                this.mId = id;
                this.mStub = stub;
                framework.router.register(this);
            }
            EndpointProxy.prototype.getId = function () {
                return this.mId;
            };
            EndpointProxy.prototype.onEvent = function (e) {
                return framework.router.redirect(this.mStub, e);
            };
            return EndpointProxy;
        })();
        basic.EndpointProxy = EndpointProxy;
    })(basic = framework.basic || (framework.basic = {}));
})(framework || (framework = {}));
