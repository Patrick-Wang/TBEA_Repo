/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="jcycljgdef.ts" />
///<reference path="../../unitedSelector.ts"/>
var jcycljg;
(function (jcycljg) {
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
            return nod.getData().plugin;
        };
        View.prototype.init = function (opt) {
            this.mOpt = opt;
            this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3, month: 1 }, {
                year: this.mOpt.date.year,
                month: this.mOpt.date.month
            }, this.mOpt.dt);
            this.mDateSelector.select(this.mOpt.date);
            this.mUnitedSelector = new Util.UnitedSelector(this.mNodes, this.mOpt.type);
            this.mNodes = this.mUnitedSelector.getNodes();
            this.updateUI();
        };
        View.prototype.updateUI = function () {
            var node = this.mUnitedSelector.getDataNode(this.mUnitedSelector.getPath());
            for (var i = 0; i < this.mNodes.length; ++i) {
                if (node != this.mNodes[i]) {
                    this.mNodes[i].getData().plugin.hide();
                }
            }
            node.getData().plugin.show();
            var dts = this.mDateSelector.getDate();
            var dte = this.mDateSelector.getDate();
            dts.day = 1;
            dte.day = this.mDateSelector.monthDays();
            node.getData().plugin.update(dts, dte);
        };
        return View;
    })();
    jcycljg.View = View;
})(jcycljg || (jcycljg = {}));
var view = new jcycljg.View();
