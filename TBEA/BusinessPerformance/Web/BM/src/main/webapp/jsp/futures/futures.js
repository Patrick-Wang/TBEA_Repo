/// <reference path="../jqgrid/jqassist.ts" />
// / <reference path="../util.ts" />
///<reference path="../unitedSelector.ts"/>
///<reference path="../dateSelector.ts"/>
///<reference path="futuresdef.ts"/>
///<reference path="../messageBox.ts"/>
var futures;
(function (futures) {
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
            this.mItemSelector = new Util.UnitedSelector(this.mNodes, this.mOpt.type);
            this.mNodes = this.mItemSelector.getTopNodes();
            var inputs = $("#" + this.mOpt.contentType + " input");
            inputs.click(function (e) {
                for (var i = 0; i < inputs.length; i++) {
                    if (true == inputs[i].checked) {
                        if (inputs[i].id == 'rdct') {
                            _this.mDisplayType = futures.DisplayType.CHART;
                            _this.showPluginChart();
                        }
                        else {
                            _this.mDisplayType = futures.DisplayType.TABLE;
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
                        return futures.DisplayType.CHART;
                    }
                    else {
                        return futures.DisplayType.TABLE;
                    }
                }
            }
            return futures.DisplayType.CHART;
        };
        View.prototype.showPluginChart = function () {
            if (this.mCurrentPlugin.getContentType() == futures.ContentType.TABLE_CHART) {
                this.mCurrentPlugin.switchDisplayType(futures.DisplayType.CHART);
            }
        };
        View.prototype.showPluginTable = function () {
            if (this.mCurrentPlugin.getContentType() == futures.ContentType.TABLE_CHART) {
                this.mCurrentPlugin.switchDisplayType(futures.DisplayType.TABLE);
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
            this.mCurrentPlugin = this.plugin(node);
            for (var i = 0; i < this.mNodes.length; ++i) {
                if (node != this.mNodes[i]) {
                    this.plugin(this.mNodes[i]).hide();
                }
            }
            this.mCurrentPlugin.show();
            //$("#headertitle")[0].innerHTML = node.getData().value;
            if (this.mDisplayType == futures.DisplayType.CHART) {
                this.showPluginChart();
            }
            else {
                this.showPluginTable();
            }
            if (this.mCurrentPlugin.getContentType() == futures.ContentType.TABLE_CHART) {
                $("#" + this.mOpt.contentType).show();
            }
            else {
                $("#" + this.mOpt.contentType).hide();
            }
            this.plugin(node).update();
        };
        return View;
    })();
    futures.View = View;
})(futures || (futures = {}));
var view = new futures.View();
