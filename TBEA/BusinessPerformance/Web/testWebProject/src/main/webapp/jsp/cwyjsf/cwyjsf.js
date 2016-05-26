var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="cwyjsfdef.ts"/>
var cwyjsf;
(function (cwyjsf) {
    var router = framework.router;
    var CwyjsfFrameView = (function (_super) {
        __extends(CwyjsfFrameView, _super);
        function CwyjsfFrameView() {
            _super.apply(this, arguments);
        }
        CwyjsfFrameView.prototype.updateUI = function () {
            _super.prototype.updateUI.call(this);
            this.date = $.extend(this.date, this.mDtSec.getDate());
            if (router.to(this.mCurrentPlugin).send(cwyjsf.Event.CW_ISMONTH_SUPPORTED)) {
                this.mDtSec.select(this.date);
            }
            else {
                this.mDtSec.select({ year: this.date.year });
            }
        };
        CwyjsfFrameView.prototype.init = function (opt) {
            this.date = $.extend({}, opt.date);
            _super.prototype.init.call(this, opt);
        };
        return CwyjsfFrameView;
    }(framework.basic.ShowFrameView));
    var ins = new CwyjsfFrameView();
})(cwyjsf || (cwyjsf = {}));
