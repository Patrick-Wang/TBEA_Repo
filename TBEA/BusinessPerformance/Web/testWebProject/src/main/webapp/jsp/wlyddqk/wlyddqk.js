/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="wlyddqkdef.ts" />
/// <reference path="../unitedSelector.ts"/>
///<reference path="../messageBox.ts"/>
///<reference path="../companySelector.ts"/>
var wlyddqk;
(function (wlyddqk) {
    var View = (function () {
        function View() {
            this.mNodesAll = [];
        }
        View.prototype.register = function (name, plugin) {
            var data = { id: this.mNodesAll.length, value: name, plugin: plugin };
            var node = new Util.DataNode(data);
            this.mNodesAll.push(node);
        };
        View.prototype.unregister = function (name) {
            var nod;
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                this.mNodesAll[i].accept({
                    visit: function (node) {
                        if (node.getData().value == name) {
                            nod = node;
                            return true;
                        }
                        return false;
                    }
                });
                if (nod != undefined) {
                    break;
                }
            }
            return this.plugin(nod);
        };
        //不可以起名叫做export 在IE中有冲突
        View.prototype.exportExcel = function (elemId) {
            var url = this.mCurrentPlugin.getExportUrl(this.mCurrentDate, this.mCurrentComp);
            $("#" + elemId)[0].action = url;
            $("#" + elemId)[0].submit();
        };
        View.prototype.init = function (opt) {
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
        View.prototype.updateTypeSelector = function () {
            var type = this.mCompanySelector.getCompany();
            var nodes = [];
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                if (this.plugin(this.mNodesAll[i]).isSupported(type)) {
                    nodes.push(this.mNodesAll[i]);
                }
            }
            var typeChange = false;
            var curNodes = [];
            if (this.mItemSelector != undefined) {
                curNodes = this.mItemSelector.getTopNodes();
            }
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
                    minWidth: 285,
                    height: '100%',
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                })
                    .css("padding", "2px 0 2px 4px")
                    .css("text-align", "left")
                    .css("font-size", "12px");
            }
        };
        View.prototype.plugin = function (node) {
            return node.getData().plugin;
        };
        View.prototype.getActiveNode = function () {
            return this.mItemSelector.getDataNode(this.mItemSelector.getPath());
        };
        View.prototype.updateUI = function () {
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
            $("#headertitle")[0].innerHTML = this.mCompanySelector.getCompanyName() + " " + node.getData().value;
            this.plugin(node).update(dt, this.mCurrentComp);
        };
        return View;
    })();
    wlyddqk.View = View;
})(wlyddqk || (wlyddqk = {}));
var view = new wlyddqk.View();
