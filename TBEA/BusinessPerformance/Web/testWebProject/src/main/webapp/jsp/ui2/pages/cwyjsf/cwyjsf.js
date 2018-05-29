var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="cwyjsfdef.ts"/>
var cwyjsf;
(function (cwyjsf) {
    var router = framework.router;
    var CwyjsfFrameView = /** @class */ (function (_super) {
        __extends(CwyjsfFrameView, _super);
        function CwyjsfFrameView() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        CwyjsfFrameView.prototype.updateDate = function () {
            this.date = $.extend(this.date, this.getDate());
            if (router.to(this.plugin(this.mItemSelector.getDataNode(this.mItemSelector.getPath()))).send(cwyjsf.Event.CW_ISMONTH_SUPPORTED)) {
                this.createInternalDate(this.mOpt.dt, { year: this.mOpt.date.year, month: this.mOpt.date.month }, { nowDate: Util.date2Str(this.date) });
                //this.createDate({nowDate: Util.date2Str(this.date)});
            }
            else {
                this.createInternalDate(this.mOpt.dt, { year: this.mOpt.date.year }, { nowDate: Util.date2Str(this.date) });
            }
        };
        CwyjsfFrameView.prototype.updateUI = function () {
            _super.prototype.updateUI.call(this);
        };
        CwyjsfFrameView.prototype.updateTypeSelector = function (width) {
            var _this = this;
            if (width === void 0) { width = 285; }
            var ret = _super.prototype.updateTypeSelector.call(this, width);
            this.mItemSelector.change(function () {
                _this.updateDate();
            });
            return ret;
        };
        CwyjsfFrameView.prototype.init = function (opt) {
            this.date = $.extend({}, opt.date);
            _super.prototype.init.call(this, opt);
            this.updateDate();
        };
        return CwyjsfFrameView;
    }(framework.basic.ShowFrameView));
    var ins = new CwyjsfFrameView();
})(cwyjsf || (cwyjsf = {}));
