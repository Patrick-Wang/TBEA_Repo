/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="sbdddcbjpcqkdef.ts" />
/// <reference path="../unitedSelector.ts"/>
///<reference path="../messageBox.ts"/>
///<reference path="../companySelector.ts"/>
var sbdddcbjpcqk;
(function (sbdddcbjpcqk) {
    var EntryView = (function () {
        function EntryView() {
            this.mNodes = [];
        }
        EntryView.prototype.register = function (name, plugin) {
            var data = { id: this.mNodes.length, value: name, plugin: plugin };
            var node = new Util.DataNode(data);
            this.mNodes.push(node);
            plugin.setOnReadOnlyChangeListener(function (isReadOnly) {
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
        EntryView.prototype.unregister = function (name) {
            return undefined;
        };
        EntryView.prototype.init = function (opt) {
            this.mOpt = opt;
            this.mDtSec = new Util.DateSelector({ year: this.mOpt.date.year - 3, month: 1 }, {
                year: this.mOpt.date.year,
                month: this.mOpt.date.month
            }, this.mOpt.dt);
            this.mItemSelector = new Util.UnitedSelector(this.mNodes, this.mOpt.type);
            if (this.mNodes.length == 1) {
                this.mItemSelector.hide();
            }
            this.mNodes = this.mItemSelector.getTopNodes();
            this.updateUI();
        };
        EntryView.prototype.plugin = function (node) {
            return node.getData().plugin;
        };
        EntryView.prototype.getActiveNode = function () {
            return this.mItemSelector.getDataNode(this.mItemSelector.getPath());
        };
        EntryView.prototype.updateUI = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            var dt = this.mDtSec.getDate();
            dt.day = 1;
            this.mCurrentPlugin = this.plugin(node);
            for (var i = 0; i < this.mNodes.length; ++i) {
                if (node != this.mNodes[i]) {
                    this.plugin(this.mNodes[i]).hide();
                }
            }
            this.mCurrentDate = dt;
            this.mCurrentPlugin.show();
            $("#headertitle")[0].innerHTML = node.getData().value;
            this.plugin(node).update(dt);
        };
        EntryView.prototype.submit = function () {
            this.plugin(this.getActiveNode()).submit(this.mCurrentDate);
        };
        EntryView.prototype.save = function () {
            this.plugin(this.getActiveNode()).save(this.mCurrentDate);
        };
        return EntryView;
    }());
    sbdddcbjpcqk.EntryView = EntryView;
})(sbdddcbjpcqk || (sbdddcbjpcqk = {}));
var entryView = new sbdddcbjpcqk.EntryView();
