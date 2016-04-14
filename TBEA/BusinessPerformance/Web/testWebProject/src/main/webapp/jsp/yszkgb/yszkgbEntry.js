/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="yszkgbdef.ts" />
/// <reference path="../unitedSelector.ts"/>
///<reference path="../messageBox.ts"/>
///<reference path="../companySelector.ts"/>
///<reference path="yszkgb.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var yszkgb;
(function (yszkgb) {
    var EntryView = (function (_super) {
        __extends(EntryView, _super);
        function EntryView() {
            _super.apply(this, arguments);
        }
        EntryView.prototype.register = function (name, plugin) {
            _super.prototype.register.call(this, name, plugin);
            plugin.setReadOnlyCallBack(function (isReadOnly) {
                if (isReadOnly) {
                    $("#gbsv").hide();
                    $("#gbsm").hide();
                }
                else {
                    $("#gbsv").show();
                    $("#gbsm").show();
                }
            });
        };
        EntryView.prototype.submit = function () {
            var dt = this.mDtSec.getDate();
            dt.day = 1;
            var plugin = this.plugin(this.getActiveNode());
            plugin.submit(dt, this.mCompanySelector.getCompany());
        };
        EntryView.prototype.save = function () {
            var dt = this.mDtSec.getDate();
            dt.day = 1;
            var plugin = this.plugin(this.getActiveNode());
            plugin.save(dt, this.mCompanySelector.getCompany());
        };
        return EntryView;
    })(yszkgb.View);
    yszkgb.EntryView = EntryView;
})(yszkgb || (yszkgb = {}));
var entryView = new yszkgb.EntryView();
