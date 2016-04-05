/// <reference path="jqgrid/vector.ts" />
/// <reference path="util.ts" />
var Util;
(function (Util) {
    var DataNode = (function () {
        function DataNode(data) {
            this.subNodes = [];
            this.data = data;
        }
        DataNode.prototype.setParent = function (parent) {
            this.parent = parent;
        };
        DataNode.prototype.append = function (node) {
            if (Util.isExist(node)) {
                this.subNodes.push(node);
                node.setParent(this);
            }
        };
        DataNode.prototype.appendAll = function (nodes) {
            for (var i in nodes) {
                this.append(nodes[i]);
            }
        };
        DataNode.prototype.getParent = function () {
            return this.parent;
        };
        DataNode.prototype.hasChildren = function () {
            return !(this.subNodes.length == 0);
        };
        DataNode.prototype.getData = function () {
            return this.data;
        };
        DataNode.prototype.childCount = function () {
            return this.subNodes.length;
        };
        DataNode.prototype.childAt = function (i) {
            return this.subNodes[i];
        };
        DataNode.prototype.leavesCount = function () {
            if (!this.hasChildren()) {
                return 1;
            }
            var count = 0;
            for (var i = this.subNodes.length - 1; i >= 0; --i) {
                count += this.subNodes[i].leavesCount();
            }
            return count;
        };
        DataNode.prototype.mostLeftLeaf = function () {
            var node = this;
            if (this.hasChildren()) {
                node = this.subNodes[0].mostLeftLeaf();
            }
            return node;
        };
        DataNode.prototype.leaves = function () {
            var list = [];
            if (this.hasChildren()) {
                for (var i = this.subNodes.length - 1; i >= 0; --i) {
                    if (this.subNodes[i].hasChildren()) {
                        list = list.concat(this.subNodes[i].leaves());
                    }
                    else {
                        list.push(this.subNodes[i]);
                    }
                }
            }
            else {
                list.push(this);
            }
            return list;
        };
        DataNode.prototype.depth = function () {
            var childDepth = 0;
            var depthTmp = 0;
            for (var i = this.subNodes.length - 1; i >= 0; --i) {
                depthTmp = this.subNodes[i].depth();
                if (depthTmp > childDepth) {
                    childDepth = depthTmp;
                }
            }
            return childDepth + 1;
        };
        DataNode.prototype.find = function (node) {
            for (var i = this.subNodes.length - 1; i >= 0; --i) {
                if (node == this.subNodes[i]) {
                    return i;
                }
            }
            return -1;
        };
        DataNode.prototype.children = function (depth) {
            var children = new std.vector();
            children.push(this);
            for (var j = 0; j < depth; j++) {
                var len = children.size();
                for (var k = 0; k < len; k++) {
                    var sub = children.get(0);
                    children.erase(0);
                    for (var i = 0; i < sub.childCount(); i++) {
                        children.push(sub.childAt(i));
                    }
                }
            }
            return children.toArray();
        };
        DataNode.prototype.accept = function (visitor) {
            var stop = visitor.visit(this);
            for (var i = this.subNodes.length - 1; !stop && i >= 0; --i) {
                stop = this.subNodes[i].accept(visitor);
            }
            return stop;
        };
        DataNode.valueOf = function (node) {
            var _this = this;
            var retNode = new DataNode(node.data);
            $(node.subNodes).each(function (i) {
                retNode.append(_this.valueOf(node.subNodes[i]));
            });
            return retNode;
        };
        DataNode.valueOfAll = function (nodes) {
            var _this = this;
            var retNodes = [];
            $(nodes).each(function (i) {
                retNodes.push(_this.valueOf(nodes[i]));
            });
            return retNodes;
        };
        DataNode.prototype.remove = function (i) {
            this.subNodes[i].setParent(null);
            return this.subNodes.splice(i, 1)[0];
        };
        DataNode.prototype.removeAll = function () {
            var _this = this;
            $(this.subNodes).each(function (i) {
                _this.subNodes[i].setParent(null);
            });
            this.subNodes = [];
        };
        return DataNode;
    })();
    Util.DataNode = DataNode;
    var UnitedSelector = (function () {
        function UnitedSelector(data, ctrlId, path) {
            this.mRoot = new DataNode(null);
            this.mPath = [];
            this.mRoot.appendAll(DataNode.valueOfAll(data));
            this.mCtrlId = ctrlId + "_unitedSelector";
            $("#" + ctrlId).append('<table id="' + this.mCtrlId + '" cellspacing="0" cellpadding="0"><tr></tr></table>');
            if (Util.isExist(data) && this.mRoot.childCount() > 0) {
                this.update(path);
            }
        }
        UnitedSelector.prototype.refresh = function () {
            $("#" + this.mCtrlId).empty().append("<tr></tr>");
            this.mPath = [];
            if (this.mRoot.childCount() > 0) {
                this.update(this.mPath);
            }
        };
        UnitedSelector.prototype.hide = function () {
            $("#" + this.mCtrlId).css("display", "none");
        };
        UnitedSelector.prototype.show = function () {
            $("#" + this.mCtrlId).css("display", "");
        };
        UnitedSelector.prototype.change = function (fnChange) {
            this.mFnChange = fnChange;
        };
        //selected nodes
        UnitedSelector.prototype.getNodes = function () {
            var ret = [];
            var node = this.mRoot;
            for (var i = 0; i < this.mPath.length; ++i) {
                node = node.childAt(this.mPath[i]);
                ret.push(node);
            }
            return ret;
        };
        UnitedSelector.prototype.getTopNodes = function () {
            return this.mRoot.subNodes;
        };
        UnitedSelector.prototype.getPath = function () {
            return this.mPath;
        };
        UnitedSelector.prototype.getSelect = function () {
            return $("#" + this.mCtrlId + " tr select");
        };
        UnitedSelector.prototype.initPath = function (path) {
            var node = this.mRoot.childAt(0);
            this.mPath.push(0);
            while (node.hasChildren()) {
                node = node.childAt(0);
                this.mPath.push(0);
            }
        };
        UnitedSelector.prototype.getDataNode = function (path, depth) {
            if (!Util.isExist(depth)) {
                depth = path.length;
            }
            var node = this.mRoot;
            for (var i = 0; i < depth; ++i) {
                node = node.childAt(path[i]);
            }
            return node;
        };
        UnitedSelector.prototype.appendChildren = function (select, depth) {
            var _this = this;
            var data;
            var parent = this.getDataNode(this.mPath, depth - 1);
            var sel = this.mPath[depth - 1];
            for (var i = 0; i < parent.childCount(); ++i) {
                data = parent.childAt(i).getData();
                if (i == sel) {
                    select.append('<option index = "' + i + '" value="' + data.id + '" selected="selected">' + data.value + '</option>');
                }
                else {
                    select.append('<option index = "' + i + '" value="' + data.id + '">' + data.value + '</option>');
                }
            }
            select = $(select);
            select.change(function (s) {
                var selOpt = select.children('option:selected');
                if (selOpt.length > 0) {
                    var path = [];
                    for (var i = 0; i < depth - 1; ++i) {
                        path.push(_this.mPath[i]);
                    }
                    path[depth - 1] = parseInt(selOpt.eq(0).attr("index"));
                    var node = _this.getDataNode(path, depth);
                    while (node.hasChildren()) {
                        node = node.childAt(0);
                        path.push(0);
                    }
                    _this.update(path);
                }
                if (Util.isExist(_this.mFnChange)) {
                    _this.mFnChange(select, depth);
                }
            });
        };
        UnitedSelector.prototype.updatePath = function (path) {
            var pos = 0;
            if (Util.isExist(path)) {
                pos = path.length > this.mPath.length ? this.mPath.length : path.length;
                for (var i = 0; i < pos; ++i) {
                    if (path[i] != this.mPath[i]) {
                        pos = i + 1;
                        break;
                    }
                }
                this.mPath = path;
            }
            return pos;
        };
        UnitedSelector.prototype.empty = function (ctrlTr, begin) {
            for (var i = ctrlTr.children().length - 1; i >= begin * 2; --i) {
                ctrlTr.children().eq(i).remove();
            }
        };
        UnitedSelector.prototype.add = function (ctrlTr, begin) {
            for (var i = begin; i < this.mPath.length; ++i) {
                ctrlTr.append('<td><select id="united_' + i + '" style="font-size:13px"></select></td>' +
                    '<td><div style="width:5px;"></div></td>');
                this.appendChildren(ctrlTr.children().eq(i * 2).children().eq(0), i + 1);
            }
        };
        UnitedSelector.prototype.update = function (path) {
            if (this.mRoot.childCount() > 0) {
                var ctrlTr = $("#" + this.mCtrlId + " tr");
                var start = 0;
                if (Util.isExist(path)) {
                    start = this.updatePath(path);
                }
                else {
                    this.initPath();
                }
                this.empty(ctrlTr, start);
                this.add(ctrlTr, start);
            }
        };
        return UnitedSelector;
    })();
    Util.UnitedSelector = UnitedSelector;
})(Util || (Util = {}));
