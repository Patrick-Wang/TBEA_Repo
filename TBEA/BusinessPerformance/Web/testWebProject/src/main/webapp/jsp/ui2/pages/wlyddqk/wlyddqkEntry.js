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
                    $("#save").hide();
                    $("#submit").hide();
                }
                else {
                    $("#save").show();
                    $("#submit").show();
                }
            });
        };
        EntryView.prototype.unregister = function (name) {
            return undefined;
        };
        EntryView.prototype.init = function (opt) {
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
            $(window).resize(function () {
                _this.mCurrentPlugin.adjustSize();
                _this.adjustHeader();
            });
            this.updateTypeSelector();
            this.updateUI();
            this.adjustHeader();
        };
        EntryView.prototype.adjustHeader = function () {
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
        EntryView.prototype.getDate = function () {
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
        EntryView.prototype.updateTypeSelector = function (width) {
            if (width === void 0) { width = 325; }
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
            var dt = this.getDate();
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
            this.plugin(node).update(dt, this.mCurrentComp);
        };
        EntryView.prototype.submit = function () {
            this.plugin(this.getActiveNode()).submit(this.mCurrentDate, this.mCurrentComp);
        };
        EntryView.prototype.save = function () {
            this.plugin(this.getActiveNode()).save(this.mCurrentDate, this.mCurrentComp);
        };
        return EntryView;
    }());
    wlyddqk.EntryView = EntryView;
})(wlyddqk || (wlyddqk = {}));
var entryView = new wlyddqk.EntryView();
