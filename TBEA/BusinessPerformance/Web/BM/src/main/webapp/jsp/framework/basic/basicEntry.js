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
        var EntryFrameView = (function (_super) {
            __extends(EntryFrameView, _super);
            function EntryFrameView() {
                _super.apply(this, arguments);
            }
            EntryFrameView.prototype.onEvent = function (e) {
                _super.prototype.onEvent.call(this, e);
                switch (e.id) {
                    case basic.FrameEvent.FE_SAVE:
                        this.save();
                        break;
                    case basic.FrameEvent.FE_SUBMIT:
                        this.submit();
                        break;
                }
            };
            EntryFrameView.prototype.submit = function () {
                router.to(this.mCurrentPlugin).send(basic.FrameEvent.FE_SUBMIT, {
                    date: this.mCurrentDate,
                    compType: this.mCurrentComp
                });
            };
            EntryFrameView.prototype.save = function () {
                router.to(this.mCurrentPlugin).send(basic.FrameEvent.FE_SAVE, {
                    date: this.mCurrentDate,
                    compType: this.mCurrentComp
                });
            };
            return EntryFrameView;
        })(basic.BasicFrameView);
        basic.EntryFrameView = EntryFrameView;
    })(basic = framework.basic || (framework.basic = {}));
})(framework || (framework = {}));
