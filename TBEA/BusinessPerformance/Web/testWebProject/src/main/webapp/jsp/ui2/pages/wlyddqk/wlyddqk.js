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
            $("#" + this.mOpt.dt).jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年MM月",
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(Util.addYear(this.mOpt.date, -3)),
                maxDate: Util.date2Str(this.mOpt.date)
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top");
            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.mCompanySelector.change(function (selector, depth) {
                _this.updateTypeSelector();
            });
            this.updateTypeSelector();
            this.updateUI();
            $(window).resize(function () {
                _this.mCurrentPlugin.adjustSize();
                _this.adjustHeader();
            });
            this.adjustHeader();
        };
        View.prototype.adjustHeader = function () {
            $("#headerHost").removeCss("width");
            if ($("#headerHost").height() > 40) {
                $(".page-header").addClass("page-header-double");
                $("#headerHost").css("width", $("#sels").width() + "px");
            }
            else {
                $(".page-header").removeClass("page-header-double");
            }
            return false;
        };
        View.prototype.getDate = function () {
            var ret = {};
            if (this.mOpt.date) {
                var curDate = $("#" + this.mOpt.dt).getDate();
                ret = {
                    year: curDate.getFullYear(),
                    month: curDate.getMonth() + 1,
                    day: curDate.getDate()
                };
            }
            return ret;
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
            }
        };
        View.prototype.plugin = function (node) {
            return node.getData().plugin;
        };
        View.prototype.updateUI = function () {
            var node = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            var dt = this.getDate();
            dt.day = 1;
            this.mCurrentPlugin = this.plugin(node);
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                if (this.plugin(node) != this.plugin(this.mNodesAll[i])) {
                    this.plugin(this.mNodesAll[i]).hide();
                }
            }
            var unit = this.mCurrentPlugin.pluginGetUnit();
            if (undefined != unit) {
                $("#unit").text(unit);
            }
            else {
                $("#unit").text("");
            }
            this.mCurrentComp = this.mCompanySelector.getCompany();
            this.mCurrentDate = dt;
            this.mCurrentPlugin.show();
            this.plugin(node).update(dt, this.mCurrentComp);
        };
        return View;
    }());
    wlyddqk.View = View;
})(wlyddqk || (wlyddqk = {}));
var view = new wlyddqk.View();
