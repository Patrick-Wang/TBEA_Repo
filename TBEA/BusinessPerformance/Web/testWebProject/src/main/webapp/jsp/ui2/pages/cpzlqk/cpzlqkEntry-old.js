///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicEntry.ts"/>
///<reference path="cpzlqk.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var cpzlqk;
(function (cpzlqk) {
    var CpzlqkEntryFrameView = (function (_super) {
        __extends(CpzlqkEntryFrameView, _super);
        function CpzlqkEntryFrameView() {
            _super.apply(this, arguments);
        }
        CpzlqkEntryFrameView.prototype.onEvent = function (e) {
            switch (e.id) {
                case cpzlqk.Event.ZLFE_DATA_STATUS:
                    $("#submit").show();
                    break;
            }
            return _super.prototype.onEvent.call(this, e);
        };
        CpzlqkEntryFrameView.prototype.updateTypeSelector = function (width) {
            if (width === void 0) { width = 285; }
            var ret = _super.prototype.updateTypeSelector.call(this, width);
            return ret;
        };
        CpzlqkEntryFrameView.prototype.init = function (opt) {
            _super.prototype.init.call(this, opt);
        };
        return CpzlqkEntryFrameView;
    })(framework.basic.EntryFrameView);
    var ZlEntryPluginView = (function (_super) {
        __extends(ZlEntryPluginView, _super);
        function ZlEntryPluginView() {
            _super.apply(this, arguments);
        }
        ZlEntryPluginView.prototype.onEvent = function (e) {
            return _super.prototype.onEvent.call(this, e);
        };
        return ZlEntryPluginView;
    })(framework.basic.EntryPluginView);
    cpzlqk.ZlEntryPluginView = ZlEntryPluginView;
    var ins = new CpzlqkEntryFrameView();
})(cpzlqk || (cpzlqk = {}));
