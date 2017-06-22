var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../unitedSelector.ts"/>
///<reference path="../../messageBox.ts"/>
///<reference path="../../companySelector.ts"/>
///<reference path="basicdef.ts"/>
///<reference path="../route/route.ts"/>
///<reference path="basic.ts"/>
var framework;
(function (framework) {
    var basic;
    (function (basic) {
        var router = framework.router;
        var FrameEvent;
        (function (FrameEvent) {
            FrameEvent.FE_EXPORTEXCEL = FrameEvent.lastEvent();
        })(FrameEvent = basic.FrameEvent || (basic.FrameEvent = {}));
        var ShowFrameView = (function (_super) {
            __extends(ShowFrameView, _super);
            function ShowFrameView() {
                _super.apply(this, arguments);
            }
            ShowFrameView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case FrameEvent.FE_EXPORTEXCEL:
                        this.exportExcel(e.data);
                        break;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            //不可以起名叫做export 在IE中有冲突
            ShowFrameView.prototype.exportExcel = function (elemId) {
                var url = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GET_EXPORTURL, {
                    date: this.mCurrentDate,
                    compType: this.mCurrentComp
                });
                $("#" + elemId)[0].action = url;
                $("#" + elemId)[0].submit();
            };
            return ShowFrameView;
        })(basic.BasicFrameView);
        basic.ShowFrameView = ShowFrameView;
    })(basic = framework.basic || (framework.basic = {}));
})(framework || (framework = {}));
