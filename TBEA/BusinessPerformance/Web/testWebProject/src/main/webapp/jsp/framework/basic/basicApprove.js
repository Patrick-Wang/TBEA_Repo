var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="basic.ts" />
/// <reference path="../../unitedSelector.ts"/>
///<reference path="../../messageBox.ts"/>
///<reference path="../../companySelector.ts"/>
///<reference path="../route/route.ts"/>
var framework;
(function (framework) {
    var basic;
    (function (basic) {
        var router = framework.router;
        var ApproveFrameView = (function (_super) {
            __extends(ApproveFrameView, _super);
            function ApproveFrameView() {
                _super.apply(this, arguments);
            }
            ApproveFrameView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case basic.FrameEvent.FE_APPROVE:
                        this.approve();
                        break;
                    case basic.FrameEvent.FE_UNAPPROVE:
                        this.unapprove();
                        break;
                    case basic.FrameEvent.FE_NOT_SUBMITTED:
                        $("#" + (this.mOpt).approveBtn).hide();
                        $("#" + (this.mOpt).unapproveBtn).hide();
                        break;
                    case basic.FrameEvent.FE_SUBMITTED:
                        $("#" + (this.mOpt).approveBtn).show();
                        $("#" + (this.mOpt).unapproveBtn).hide();
                        break;
                    case basic.FrameEvent.FE_APPROVED:
                        $("#" + (this.mOpt).approveBtn).hide();
                        $("#" + (this.mOpt).unapproveBtn).show();
                        break;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ApproveFrameView.prototype.approve = function () {
                router.to(this.mCurrentPlugin).send(basic.FrameEvent.FE_APPROVE, {
                    date: this.mCurrentDate,
                    compType: this.mCurrentComp
                });
            };
            ApproveFrameView.prototype.unapprove = function () {
                router.to(this.mCurrentPlugin).send(basic.FrameEvent.FE_UNAPPROVE, {
                    date: this.mCurrentDate,
                    compType: this.mCurrentComp
                });
            };
            return ApproveFrameView;
        }(basic.BasicFrameView));
        basic.ApproveFrameView = ApproveFrameView;
    })(basic = framework.basic || (framework.basic = {}));
})(framework || (framework = {}));
