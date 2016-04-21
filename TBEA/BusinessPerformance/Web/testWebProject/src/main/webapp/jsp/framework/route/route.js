/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../../js/jquery/jquery.d.ts" />
var framework;
(function (framework) {
    var route;
    (function (route) {
        var Router = (function () {
            function Router() {
                this.mEndpoints = {};
            }
            Router.prototype.register = function (endpoint) {
                this.mEndpoints[endpoint.getId()] = endpoint;
            };
            Router.prototype.unregister = function (endpoint) {
                this.mEndpoints[endpoint.getId()] = undefined;
            };
            Router.prototype.sendInternal = function (e) {
                var toEndpoint = this.mEndpoints[e.to];
                if (toEndpoint != undefined) {
                    return toEndpoint.onEvent(e);
                }
                return Router.FAILED;
            };
            Router.prototype.fromEp = function (from) {
                return this.from(from.getId());
            };
            Router.prototype.toEp = function (to) {
                return this.to(to.getId());
            };
            Router.prototype.from = function (from) {
                this.mCurEvent = {};
                this.mCurEvent.from = from;
                return this;
            };
            Router.prototype.to = function (target) {
                if (this.mCurEvent == undefined) {
                    this.mCurEvent = {};
                }
                this.mCurEvent.to = target;
                return this;
            };
            Router.prototype.broadcast = function (id, data) {
                if (this.mCurEvent != undefined) {
                    for (var i in this.mEndpoints) {
                        var event_1 = {
                            from: this.mCurEvent.from,
                            to: undefined,
                            id: id,
                            data: data
                        };
                        this.mEndpoints[i].onEvent(event_1);
                    }
                    this.mCurEvent = undefined;
                    return Router.OK;
                }
                return Router.FAILED;
            };
            Router.prototype.send = function (id, data) {
                if (this.mCurEvent != undefined) {
                    this.mCurEvent.id = id;
                    this.mCurEvent.data = data;
                    var event_2 = this.mCurEvent;
                    this.mCurEvent = undefined;
                    return this.sendInternal(event_2);
                }
                return Router.FAILED;
            };
            Router.prototype.redirect = function (to, event) {
                if (to != undefined) {
                    if (event.redirects == undefined) {
                        event.redirects = [];
                    }
                    event.redirects.push(event.to);
                    event.to = to;
                    return this.sendInternal(event);
                }
                return Router.FAILED;
            };
            Router.prototype.getEndpoint = function (id) {
                return this.mEndpoints[id];
            };
            Router.OK = 887291;
            Router.FAILED = 887292;
            return Router;
        })();
        route.Router = Router;
        route.router = new Router();
    })(route = framework.route || (framework.route = {}));
})(framework || (framework = {}));
