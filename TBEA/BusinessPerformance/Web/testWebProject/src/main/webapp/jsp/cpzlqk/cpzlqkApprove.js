///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicApprove.ts"/>
///<reference path="cpzlqkdef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var cpzlqk;
(function (cpzlqk) {
    var CpzlqkApproveFrameView = (function (_super) {
        __extends(CpzlqkApproveFrameView, _super);
        function CpzlqkApproveFrameView() {
            _super.apply(this, arguments);
        }
        CpzlqkApproveFrameView.prototype.onEvent = function (e) {
            return _super.prototype.onEvent.call(this, e);
        };
        CpzlqkApproveFrameView.prototype.updateTypeSelector = function (width) {
            if (width === void 0) { width = 285; }
            var ret = _super.prototype.updateTypeSelector.call(this, width);
            return ret;
        };
        CpzlqkApproveFrameView.prototype.init = function (opt) {
            _super.prototype.init.call(this, opt);
        };
        return CpzlqkApproveFrameView;
    })(framework.basic.ApproveFrameView);
    var ZlApprovePluginView = (function (_super) {
        __extends(ZlApprovePluginView, _super);
        function ZlApprovePluginView() {
            _super.apply(this, arguments);
        }
        ZlApprovePluginView.prototype.onEvent = function (e) {
            return _super.prototype.onEvent.call(this, e);
        };
        return ZlApprovePluginView;
    })(framework.basic.ApprovePluginView);
    cpzlqk.ZlApprovePluginView = ZlApprovePluginView;
    var ins = new CpzlqkApproveFrameView();
})(cpzlqk || (cpzlqk = {}));
