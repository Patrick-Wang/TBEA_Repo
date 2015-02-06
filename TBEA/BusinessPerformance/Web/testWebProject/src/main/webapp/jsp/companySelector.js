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
    })();
    Util.ICompanySelectorOption = ICompanySelectorOption;
    var CompanySelector = (function () {
        function CompanySelector(multi, divId, comps, firstComp, opt) {
            var _this = this;
            this.mOpt = {
                noneSelectedText: '项目公司',
                selectedText: '# 个项目公司被选中'
            };
            this.mMulti = multi;
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
            this.mUnitedSelector.change(function (sel) {
                _this.useMultiSelect();
            });
        }
        CompanySelector.prototype.updateSelect = function (sel, itemCount, multi) {
            sel = $(sel);
            var width = sel.children('option:selected').text().getWidth(13) + 25;
            var minWidth = 80;
            if (multi) {
                sel.multiselect({
                    multiple: multi,
                    header: true,
                    minWidth: 80,
                    noneSelectedText: this.mOpt.noneSelectedText,
                    selectedText: this.mOpt.selectedText,
                    height: itemCount * 27 > 600 ? 600 : itemCount * 27 + 3,
                    selectedList: 1
                });
                var text = "n个 项目公司被选中";
                minWidth = text.getWidth(13) + 50;
                if (sel.multiselect("getChecked").length > 1) {
                    width = text.getWidth(13) + 25;
                }
            }
            sel.css("width", width);
            sel.multiselect({
                multiple: multi,
                header: multi,
                minWidth: minWidth,
                height: itemCount * 27 > 600 ? 600 : itemCount * 27 + 3,
                selectedList: 1
            });
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
        CompanySelector.prototype.getCompanys = function () {
            var ret = [];
            var checkedOpt = $(this.mUnitedSelector.getSelect()[1]).multiselect("getChecked");
            checkedOpt.each(function (i) {
                ret.push(checkedOpt[i].value);
            });
            return ret;
        };
        return CompanySelector;
    })();
    Util.CompanySelector = CompanySelector;
})(Util || (Util = {}));
