/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="wlyddqkdef.ts" />
/// <reference path="../unitedSelector.ts"/>
///<reference path="../messageBox.ts"/>
///<reference path="../companySelector.ts"/>
var wlyddqk;
(function (wlyddqk) {
    var EntryView = (function () {
        function EntryView() {
            this.mNodesAll = [];
        }
        EntryView.prototype.register = function (name, plugin) {
            var data = { id: this.mNodesAll.length, value: name, plugin: plugin };
            var node = new Util.DataNode(data);
            this.mNodesAll.push(node);
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
            var _this = this;
            this.mOpt = opt;
            this.mDtSec = new Util.DateSelector({ year: this.mOpt.date.year - 3, month: 1 }, {
                year: this.mOpt.date.year,
                month: this.mOpt.date.month
            }, this.mOpt.dt);
            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.mCompanySelector.change(function (selector, depth) {
                _this.updateTypeSelector();
            });
            this.updateTypeSelector();
            this.updateUI();
        };
        EntryView.prototype.updateTypeSelector = function (width) {
            if (width === void 0) { width = 285; }
            var type = this.mCompanySelector.getCompany();
            var nodes = [];
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                if (this.plugin(this.mNodesAll[i]).isSupported(type)) {
                    nodes.push(this.mNodesAll[i]);
                }
            }
            var curNodes = [];
            if (this.mItemSelector != undefined) {
                curNodes = this.mItemSelector.getTopNodes();
            }
            var typeChange = false;
            if (nodes.length != curNodes.length) {
                typeChange = true;
            }
            else {
                for (var i_1 = 0; i_1 < nodes.length; ++i_1) {
                    if (this.plugin(nodes[i_1]) != this.plugin(curNodes[i_1])) {
                        typeChange = true;
                        break;
                    }
                }
            }
            if (typeChange) {
                this.mItemSelector = new Util.UnitedSelector(nodes, this.mOpt.type);
                if (nodes.length == 1) {
                    this.mItemSelector.hide();
                }
                $("#" + this.mOpt.type + " select")
                    .multiselect({
                    multiple: false,
                    header: false,
                    minWidth: width,
                    height: '100%',
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                })
                    .css("padding", "2px 0 2px 4px")
                    .css("text-align", "left")
                    .css("font-size", "12px");
            }
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
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                if (this.plugin(node) != this.plugin(this.mNodesAll[i])) {
                    this.plugin(this.mNodesAll[i]).hide();
                }
            }
            this.mCurrentComp = this.mCompanySelector.getCompany();
            this.mCurrentDate = dt;
            this.mCurrentPlugin.show();
            $("#headertitle")[0].innerHTML = node.getData().value;
            this.plugin(node).update(dt, this.mCurrentComp);
        };
        EntryView.prototype.submit = function () {
            this.plugin(this.getActiveNode()).submit(this.mCurrentDate, this.mCurrentComp);
        };
        EntryView.prototype.save = function () {
            this.plugin(this.getActiveNode()).save(this.mCurrentDate, this.mCurrentComp);
        };
        return EntryView;
    })();
    wlyddqk.EntryView = EntryView;
})(wlyddqk || (wlyddqk = {}));
var entryView = new wlyddqk.EntryView();
