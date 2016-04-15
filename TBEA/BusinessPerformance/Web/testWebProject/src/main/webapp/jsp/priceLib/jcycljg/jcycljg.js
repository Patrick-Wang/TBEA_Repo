/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="jcycljgdef.ts" />
/// <reference path="../../unitedSelector.ts"/>
///<reference path="../../messageBox.ts"/>
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
            return this.plugin(nod);
        };
        View.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            this.mDSStart = new Util.DateSelector({ year: this.mOpt.date.year - 3, month: 1 }, {
                year: this.mOpt.date.year,
                month: this.mOpt.date.month
            }, this.mOpt.dts);
            this.mDSEnd = new Util.DateSelector({ year: this.mOpt.date.year - 3, month: 1 }, {
                year: this.mOpt.date.year,
                month: this.mOpt.date.month
            }, this.mOpt.dte);
            this.mDSStart.select(this.mOpt.date);
            this.mDSEnd.select(this.mOpt.date);
            this.mItemSelector = new Util.UnitedSelector(this.mNodes, this.mOpt.type);
            this.mNodes = this.mItemSelector.getTopNodes();
            if (this.plugin(this.getActiveNode()).getDateType() == jcycljg.DateType.DAY) {
                $("#" + this.mOpt.dte).hide();
            }
            this.mItemSelector.change(function (sel, depth) {
                if (_this.plugin(_this.getActiveNode()).getDateType() == jcycljg.DateType.MONTH) {
                    $("#" + _this.mOpt.dte).show();
                }
                else {
                    $("#" + _this.mOpt.dte).hide();
                }
            });
            var inputs = $("#" + this.mOpt.contentType + " input");
            inputs.click(function (e) {
                for (var i = 0; i < inputs.length; i++) {
                    if (true == inputs[i].checked) {
                        if (inputs[i].id == 'rdct') {
                            _this.mDisplayType = jcycljg.DisplayType.CHART;
                            _this.showPluginChart();
                        }
                        else {
                            _this.mDisplayType = jcycljg.DisplayType.TABLE;
                            _this.showPluginTable();
                        }
                    }
                }
            });
            this.mDisplayType = this.getDisplayType();
            this.updateUI();
        };
        View.prototype.getDisplayType = function () {
            var inputs = $("#" + this.mOpt.contentType + " input");
            for (var i = 0; i < inputs.length; i++) {
                if (true == inputs[i].checked) {
                    if (inputs[i].id == 'rdct') {
                        return jcycljg.DisplayType.CHART;
                    }
                    else {
                        return jcycljg.DisplayType.TABLE;
                    }
                }
            }
            return jcycljg.DisplayType.CHART;
        };
        View.prototype.showPluginChart = function () {
            if (this.mCurrentPlugin.getContentType() == jcycljg.ContentType.TABLE_CHART) {
                this.mCurrentPlugin.switchDisplayType(jcycljg.DisplayType.CHART);
            }
        };
        View.prototype.showPluginTable = function () {
            if (this.mCurrentPlugin.getContentType() == jcycljg.ContentType.TABLE_CHART) {
                this.mCurrentPlugin.switchDisplayType(jcycljg.DisplayType.TABLE);
            }
        };
        View.prototype.plugin = function (node) {
            return node.getData().plugin;
        };
        View.prototype.getActiveNode = function () {
            return this.mItemSelector.getDataNode(this.mItemSelector.getPath());
        };
        View.prototype.checkDate = function (dts, dse) {
            var start = new Date(dts.year + "/" + dts.month + "/" + dts.day);
            var end = new Date(dse.year + "/" + dse.month + "/" + dse.day);
            if (start > end) {
                Util.MessageBox.tip("开始日期不可以大于结束日期");
                return false;
            }
            return true;
        };
        View.prototype.updateUI = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            var dts = this.mDSStart.getDate();
            dts.day = 1;
            var dte = this.mDSStart.getDate();
            if (this.plugin(node).getDateType() == jcycljg.DateType.MONTH) {
                dte = this.mDSEnd.getDate();
                dte.day = this.mDSEnd.monthDays();
            }
            else {
                dte.day = this.mDSStart.monthDays();
            }
            if (!this.checkDate(dts, dte)) {
                return;
            }
            this.mCurrentPlugin = this.plugin(node);
            for (var i = 0; i < this.mNodes.length; ++i) {
                if (node != this.mNodes[i]) {
                    this.plugin(this.mNodes[i]).hide();
                }
            }
            this.mCurrentPlugin.show();
            $("#headertitle")[0].innerHTML = node.getData().value;
            if (this.mDisplayType == jcycljg.DisplayType.CHART) {
                this.showPluginChart();
            }
            else {
                this.showPluginTable();
            }
            if (this.mCurrentPlugin.getContentType() == jcycljg.ContentType.TABLE_CHART) {
                $("#" + this.mOpt.contentType).show();
            }
            else {
                $("#" + this.mOpt.contentType).hide();
            }
            this.plugin(node).update(dts, dte);
        };
        return View;
    }());
    jcycljg.View = View;
})(jcycljg || (jcycljg = {}));
var view = new jcycljg.View();
