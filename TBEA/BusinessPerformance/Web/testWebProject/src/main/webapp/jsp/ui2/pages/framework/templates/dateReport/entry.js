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
var framework;
(function (framework) {
    var templates;
    (function (templates) {
        var dateReport;
        (function (dateReport) {
            function createInstance() {
                return new EntryView();
            }
            dateReport.createInstance = createInstance;
            var EntryView = /** @class */ (function (_super) {
                __extends(EntryView, _super);
                function EntryView() {
                    return _super !== null && _super.apply(this, arguments) || this;
                }
                EntryView.prototype.onInitialize = function (opt) {
                    this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);
                    if (opt.itemNodes.length == 1) {
                        this.unitedSelector.hide();
                        if ($("#" + opt.dtId).parent().hasClass("hidden")) {
                            $("#grid-update").hide();
                            $("#" + opt.dtId).hide();
                        }
                    }
                    _super.prototype.onInitialize.call(this, opt);
                };
                EntryView.prototype.getParams = function (date) {
                    return {
                        date: this.getDate(date),
                        item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id
                    };
                };
                return EntryView;
            }(framework.templates.singleDateReport.EntryView));
            dateReport.EntryView = EntryView;
        })(dateReport = templates.dateReport || (templates.dateReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
