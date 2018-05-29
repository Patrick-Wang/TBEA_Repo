var tree;
(function (tree) {
    var TreeNode = /** @class */ (function () {
        function TreeNode(data) {
            this.subNodes = [];
            this.data = data;
        }
        TreeNode.prototype.setParent = function (parent) {
            this.parent = parent;
        };
        TreeNode.prototype.append = function (node) {
            if (node != null && node != undefined) {
                this.subNodes.push(node);
                node.setParent(this);
            }
        };
        TreeNode.prototype.appendAll = function (nodes) {
            for (var i = 0; i < nodes.length; ++i) {
                this.append(nodes[i]);
            }
        };
        TreeNode.prototype.getParent = function () {
            return this.parent;
        };
        TreeNode.prototype.hasChildren = function () {
            return !(this.subNodes.length == 0);
        };
        TreeNode.prototype.getData = function () {
            return this.data;
        };
        TreeNode.prototype.childCount = function () {
            return this.subNodes.length;
        };
        TreeNode.prototype.childAt = function (i) {
            return this.subNodes[i];
        };
        TreeNode.prototype.leavesCount = function () {
            if (!this.hasChildren()) {
                return 1;
            }
            var count = 0;
            for (var i = this.subNodes.length - 1; i >= 0; --i) {
                count += this.subNodes[i].leavesCount();
            }
            return count;
        };
        TreeNode.prototype.mostLeftLeaf = function () {
            var node = this;
            if (this.hasChildren()) {
                node = this.subNodes[0].mostLeftLeaf();
            }
            return node;
        };
        TreeNode.prototype.leaves = function () {
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
        TreeNode.prototype.depth = function () {
            var depth = 0;
            var p = this.parent;
            while (p != undefined) {
                p = p.parent;
                ++depth;
            }
            ;
            return depth;
            //var childDepth:number = 0;
            //var depthTmp:number = 0;
            //for (var i:number = this.subNodes.length - 1; i >= 0; --i) {
            //    depthTmp = this.subNodes[i].depth();
            //    if (depthTmp > childDepth) {
            //        childDepth = depthTmp;
            //    }
            //}
            //return childDepth + 1;
        };
        TreeNode.prototype.find = function (node) {
            for (var i = this.subNodes.length - 1; i >= 0; --i) {
                if (node == this.subNodes[i]) {
                    return i;
                }
            }
            return -1;
        };
        TreeNode.prototype.children = function (depth) {
            var children = [];
            children.push(this);
            for (var j = 0; j < depth; j++) {
                var len = children.length;
                for (var k = 0; k < len; k++) {
                    var sub = children[0];
                    children.splice(0, 1);
                    for (var i = 0; i < sub.childCount(); i++) {
                        children.push(sub.childAt(i));
                    }
                }
            }
            return children;
        };
        TreeNode.prototype.accept = function (visitor) {
            var stop = visitor.visit(this);
            for (var i = 0; !stop && i < this.subNodes.length; ++i) {
                stop = this.subNodes[i].accept(visitor);
            }
            return stop;
        };
        TreeNode.valueOf = function (node) {
            var _this = this;
            var retNode = new TreeNode(node.data);
            $(node.subNodes).each(function (i) {
                retNode.append(_this.valueOf(node.subNodes[i]));
            });
            return retNode;
        };
        TreeNode.valueOfAll = function (nodes) {
            var _this = this;
            var retNodes = [];
            $(nodes).each(function (i) {
                retNodes.push(_this.valueOf(nodes[i]));
            });
            return retNodes;
        };
        TreeNode.prototype.remove = function (i) {
            this.subNodes[i].setParent(null);
            return this.subNodes.splice(i, 1)[0];
        };
        TreeNode.prototype.removeAll = function () {
            var _this = this;
            $(this.subNodes).each(function (i) {
                _this.subNodes[i].setParent(null);
            });
            this.subNodes = [];
        };
        return TreeNode;
    }());
    tree.TreeNode = TreeNode;
})(tree || (tree = {}));
