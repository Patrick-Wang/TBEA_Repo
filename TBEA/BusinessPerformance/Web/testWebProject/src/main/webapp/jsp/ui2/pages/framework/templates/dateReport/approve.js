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
///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
///<reference path="../singleDateReport/entry.ts"/>
///<reference path="../singleDateReport/approve.ts"/>
var framework;
(function (framework) {
    var templates;
    (function (templates) {
        var dateReport;
        (function (dateReport) {
            function createInstance() {
                return new ApproveView();
            }
            dateReport.createInstance = createInstance;
            var ApproveView = /** @class */ (function (_super) {
                __extends(ApproveView, _super);
                function ApproveView() {
                    return _super !== null && _super.apply(this, arguments) || this;
                }
                ApproveView.prototype.onInitialize = function (opt) {
                    this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);
                    if (opt.itemNodes.length == 1) {
                        this.unitedSelector.hide();
                        $("#headertitle").text(opt.itemNodes[0].data.value + " " + $("#headertitle").text());
                    }
                    _super.prototype.onInitialize.call(this, opt);
                };
                ApproveView.prototype.getParams = function (date) {
                    return {
                        date: this.getDate(date),
                        item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id,
                        zt: window.gZt
                    };
                };
                return ApproveView;
            }(framework.templates.singleDateReport.ApproveView));
            dateReport.ApproveView = ApproveView;
        })(dateReport = templates.dateReport || (templates.dateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
