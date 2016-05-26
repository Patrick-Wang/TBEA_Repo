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
    var router = framework.router;
    var CpzlqkApproveFrameView = (function (_super) {
        __extends(CpzlqkApproveFrameView, _super);
        function CpzlqkApproveFrameView() {
            _super.apply(this, arguments);
        }
        CpzlqkApproveFrameView.prototype.onEvent = function (e) {
            switch (e.id) {
                case cpzlqk.Event.ZLFE_GET_BHGLX:
                    var node = this.mBhglxSelector.getDataNode(this.mBhglxSelector.getPath());
                    return node.getData().id;
            }
            return _super.prototype.onEvent.call(this, e);
        };
        CpzlqkApproveFrameView.prototype.updateTypeSelector = function (width) {
            var _this = this;
            if (width === void 0) { width = 285; }
            var ret = _super.prototype.updateTypeSelector.call(this, width);
            if (ret) {
                this.mItemSelector.change(function () {
                    var node = _this.mItemSelector.getDataNode(_this.mItemSelector.getPath());
                    if (!router.to(_this.plugin(node)).send(cpzlqk.Event.ZLFE_IS_BHGLX_SUPPORTED)) {
                        _this.mBhglxSelector.hide();
                    }
                    else {
                        _this.mBhglxSelector.show();
                    }
                });
                var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
                if (!router.to(this.plugin(node)).send(cpzlqk.Event.ZLFE_IS_BHGLX_SUPPORTED)) {
                    this.mBhglxSelector.hide();
                }
                else {
                    this.mBhglxSelector.show();
                }
            }
            return ret;
        };
        CpzlqkApproveFrameView.prototype.init = function (opt) {
            this.mBhglxSelector = new Util.UnitedSelector([{
                    data: { id: cpzlqk.ByqBhgType.YBYSQFJYS, value: "110kV及以上产品" }
                }, {
                    data: { id: cpzlqk.ByqBhgType.PBCP, value: "配变产品" }
                }], opt.bhglx);
            $("#" + opt.bhglx + " select")
                .multiselect({
                multiple: false,
                header: false,
                minWidth: 140,
                height: '100%',
                // noneSelectedText: "请选择月份",
                selectedList: 1
            })
                .css("padding", "2px 0 2px 4px")
                .css("text-align", "left")
                .css("font-size", "12px");
            _super.prototype.init.call(this, opt);
        };
        return CpzlqkApproveFrameView;
    }(framework.basic.ApproveFrameView));
    var ZlApprovePluginView = (function (_super) {
        __extends(ZlApprovePluginView, _super);
        function ZlApprovePluginView() {
            _super.apply(this, arguments);
        }
        ZlApprovePluginView.prototype.onEvent = function (e) {
            switch (e.id) {
                case cpzlqk.Event.ZLFE_IS_BHGLX_SUPPORTED:
                    return this.isSupportBhglb();
            }
            return _super.prototype.onEvent.call(this, e);
        };
        ZlApprovePluginView.prototype.isSupportBhglb = function () {
            return false;
        };
        ZlApprovePluginView.prototype.getBhglx = function () {
            return router.from(this.getId()).to(framework.basic.endpoint.FRAME_ID).send(cpzlqk.Event.ZLFE_GET_BHGLX);
        };
        return ZlApprovePluginView;
    }(framework.basic.ApprovePluginView));
    cpzlqk.ZlApprovePluginView = ZlApprovePluginView;
    var ins = new CpzlqkApproveFrameView();
})(cpzlqk || (cpzlqk = {}));
