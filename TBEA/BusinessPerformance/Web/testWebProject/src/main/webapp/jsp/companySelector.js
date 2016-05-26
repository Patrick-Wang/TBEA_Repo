/// <reference path="util.ts" />
/// <reference path="unitedSelector.ts" />
var Util;
(function (Util) {
    String.prototype["getWidth"] = function (fontSize) {
        var span = document.getElementById("__getwidth");
        if (span == null) {
            span = document.createElement("span");
            span.id = "__getwidth";
            document.body.appendChild(span);
            span.style.visibility = "hidden";
            span.style.whiteSpace = "nowrap";
        }
        span.innerText = this;
        span.style.fontSize = fontSize + "px";
        return span.offsetWidth;
    };
    var ICompanySelectorOption = (function () {
        function ICompanySelectorOption() {
            this.noneSelectedText = '项目公司';
            this.selectedText = '# 个项目公司被选中';
        }
        return ICompanySelectorOption;
    }());
    Util.ICompanySelectorOption = ICompanySelectorOption;
    var CompanySelector = (function () {
        function CompanySelector(multi, divId, comps, firstComp, opt) {
            var _this = this;
            this.mOpt = {
                noneSelectedText: '项目公司',
                selectedText: '# 个项目公司被选中'
            };
            this.mMulti = multi;
            this.mDivId = divId;
            if (Util.isExist(opt)) {
                this.mOpt = opt;
            }
            var virtualRoot = new Util.DataNode(null);
            var firstCompNode;
            virtualRoot.appendAll(Util.DataNode.valueOfAll(comps));
            virtualRoot.accept({ visit: function (node) {
                    if (node != virtualRoot && node.getData().id + "" == firstComp + "") {
                        firstCompNode = node;
                        return true;
                    }
                    return false;
                } });
            if (Util.isExist(firstCompNode)) {
                var path = new std.vector();
                var parent = firstCompNode.getParent();
                while (null != parent) {
                    path.insert(0, parent.find(firstCompNode));
                    firstCompNode = parent;
                    parent = firstCompNode.getParent();
                }
                this.mUnitedSelector = new Util.UnitedSelector(comps, divId, path.toArray());
            }
            else {
                this.mUnitedSelector = new Util.UnitedSelector(comps, divId);
            }
            this.useMultiSelect();
            this.mUnitedSelector.change(function (sel, depth) {
                var compDepth = _this.mUnitedSelector.getPath().length;
                if (_this.mMulti && depth == compDepth) {
                }
                else {
                    _this.useMultiSelect();
                }
                _this.mFnChange(sel, depth);
            });
        }
        CompanySelector.prototype.hide = function () {
            this.mUnitedSelector.hide();
        };
        CompanySelector.prototype.show = function () {
            this.mUnitedSelector.show();
        };
        CompanySelector.prototype.change = function (fnChange) {
            this.mFnChange = fnChange;
        };
        CompanySelector.prototype.getMaxWidth = function (opts) {
            var max = 0;
            var tmp = 0;
            var fontSize = Util.isMSIE() ? 14 : 13;
            for (var i = 0; i < opts.length; ++i) {
                tmp = $(opts[i]).text().getWidth(fontSize) + 25;
                if (max < tmp) {
                    max = tmp;
                }
            }
            return max;
        };
        CompanySelector.prototype.updateSelect = function (sel, itemCount, multi) {
            sel = $(sel);
            var width = this.getMaxWidth(sel.children());
            var minWidth = 120;
            var itemHeight = Util.isMSIE() ? 27.5 : 27;
            sel.css("width", width);
            if (multi) {
                var text = "n个 项目公司被选中";
                minWidth = text.getWidth(13) + 80;
                sel.multiselect({
                    multiple: multi,
                    header: true,
                    minWidth: minWidth,
                    minHeight: 20,
                    noneSelectedText: this.mOpt.noneSelectedText,
                    selectedText: this.mOpt.selectedText,
                    height: '100%',
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                });
            }
            else {
                sel.multiselect({
                    multiple: multi,
                    header: multi,
                    minWidth: minWidth,
                    height: '100%',
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                });
            }
        };
        CompanySelector.prototype.useMultiSelect = function () {
            var sels = this.mUnitedSelector.getSelect();
            for (var i = 0; i < sels.length - 1; ++i) {
                this.updateSelect(sels[i], $(sels[i]).children().length, false);
            }
            this.updateSelect(sels[sels.length - 1], $(sels[sels.length - 1]).children().length, this.mMulti);
        };
        CompanySelector.prototype.getCompany = function () {
            var selNodes = this.mUnitedSelector.getNodes();
            if (selNodes.length > 0) {
                return selNodes[selNodes.length - 1].getData().id;
            }
            return null;
        };
        CompanySelector.prototype.getCompanyName = function () {
            var selNodes = this.mUnitedSelector.getNodes();
            if (selNodes.length > 0) {
                return selNodes[selNodes.length - 1].getData().value;
            }
            return null;
        };
        CompanySelector.prototype.checkAll = function () {
            $(this.mUnitedSelector.getSelect()[1]).multiselect("checkAll");
        };
        CompanySelector.prototype.getRawCompanyData = function () {
            var ret = [];
            var path = this.mUnitedSelector.getPath();
            var parent = this.mUnitedSelector.getDataNode(path, path.length - 1);
            var node;
            var sels = this.mUnitedSelector.getSelect();
            var checkedOpt = $(sels[sels.length - 1]).multiselect("getChecked");
            for (var i = 0; i < parent.childCount(); ++i) {
                node = parent.childAt(i);
                checkedOpt.each(function (j) {
                    if (node.getData().id == checkedOpt[j].value) {
                        ret.push(node.getData());
                    }
                });
            }
            return ret;
        };
        CompanySelector.prototype.getCompanyNames = function () {
            var ret = [];
            var path = this.mUnitedSelector.getPath();
            var parent = this.mUnitedSelector.getDataNode(path, path.length - 1);
            var node;
            var sels = this.mUnitedSelector.getSelect();
            var checkedOpt = $(sels[sels.length - 1]).multiselect("getChecked");
            for (var i = 0; i < parent.childCount(); ++i) {
                node = parent.childAt(i);
                checkedOpt.each(function (j) {
                    if (node.getData().id == checkedOpt[j].value) {
                        ret.push(node.getData().value);
                    }
                });
            }
            return ret;
        };
        CompanySelector.prototype.getCompanys = function () {
            var ret = [];
            var sels = this.mUnitedSelector.getSelect();
            var checkedOpt = $(sels[sels.length - 1]).multiselect("getChecked");
            checkedOpt.each(function (i) {
                ret.push(parseInt(checkedOpt[i].value));
            });
            return ret;
        };
        return CompanySelector;
    }());
    Util.CompanySelector = CompanySelector;
})(Util || (Util = {}));
