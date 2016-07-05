///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var xnyzb;
(function (xnyzb) {
    var router = framework.router;
    var FrameEvent = framework.basic.FrameEvent;
    var XnyzbShowView = (function (_super) {
        __extends(XnyzbShowView, _super);
        function XnyzbShowView() {
            _super.apply(this, arguments);
        }
        XnyzbShowView.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.mCompanySelector.change(function (selector, depth) {
                _this.updateTypeSelector();
            });
            this.mCurrentDate = { year: 2010, month: 1, day: 1 };
            this.updateTypeSelector();
            this.updateUI();
        };
        XnyzbShowView.prototype.updateUI = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);
            this.mCurrentComp = this.mCompanySelector.getCompany();
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);
            if (null != this.mCurrentComp) {
                $("#headertitle")[0].innerHTML = this.mCompanySelector.getCompanyName() + " " + node.getData().value;
            }
            else {
                $("#headertitle")[0].innerHTML = node.getData().value;
            }
            var unit = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GETUNIT);
            if (undefined != unit) {
                $("#unit").text(unit);
            }
            else {
                $("#unit").text("");
            }
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                date: this.mCurrentDate,
                compType: this.mCurrentComp
            });
        };
        return XnyzbShowView;
    })(framework.basic.ShowFrameView);
    var ins = new XnyzbShowView();
})(xnyzb || (xnyzb = {}));
