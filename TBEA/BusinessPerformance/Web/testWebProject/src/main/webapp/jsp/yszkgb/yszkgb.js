/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="yszkgbdef.ts" />
/// <reference path="../unitedSelector.ts"/>
///<reference path="../messageBox.ts"/>
///<reference path="../companySelector.ts"/>
var yszkgb;
(function (yszkgb) {
    var View = (function () {
        function View() {
            this.mNodes = [];
        }
        View.prototype.register = function (name, plugin) {
            var data = { id: this.mNodes.length, value: name, plugin: plugin };
            var node = new Util.DataNode(data);
            this.mNodes.push(node);
        };
        View.prototype.unregister = function (name) {
            var nod;
            for (var i = 0; i < this.mNodes.length; ++i) {
                this.mNodes[i].accept({
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
        View.prototype.init = function (opt) {
            this.mOpt = opt;
            this.mDtSec = new Util.DateSelector({ year: this.mOpt.date.year - 3, month: 1 }, {
                year: this.mOpt.date.year,
                month: this.mOpt.date.month
            }, this.mOpt.dt);
            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.mItemSelector = new Util.UnitedSelector(this.mNodes, this.mOpt.type);
            this.mNodes = this.mItemSelector.getTopNodes();
            this.updateUI();
        };
        View.prototype.plugin = function (node) {
            return node.getData().plugin;
        };
        View.prototype.getActiveNode = function () {
            return this.mItemSelector.getDataNode(this.mItemSelector.getPath());
        };
        View.prototype.updateUI = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            var dts = this.mDtSec.getDate();
            dts.day = 1;
            this.mCurrentPlugin = this.plugin(node);
            for (var i = 0; i < this.mNodes.length; ++i) {
                if (node != this.mNodes[i]) {
                    this.plugin(this.mNodes[i]).hide();
                }
            }
            this.mCurrentPlugin.show();
            $("#headertitle")[0].innerHTML = node.getData().value;
            this.plugin(node).update(dts, this.mCompanySelector.getCompany());
        };
        return View;
    })();
    yszkgb.View = View;
})(yszkgb || (yszkgb = {}));
var view = new yszkgb.View();
