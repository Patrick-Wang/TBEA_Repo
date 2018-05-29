///<reference path="treeNode.ts"/>
var tree;
(function (tree_1) {
    var Tree = /** @class */ (function () {
        function Tree(id) {
            this.extractedNodes = [];
            this.treeId = id;
            this.q().addClass("tree");
        }
        Tree.prototype.q = function (query) {
            if (query == undefined) {
                return $("#" + this.treeId);
            }
            else {
                return $("#" + this.treeId + " " + query);
            }
        };
        Tree.prototype.setOnClickListener = function (click) {
            var oldFn = this.onClick;
            this.onClick = click;
            return oldFn;
        };
        Tree.prototype.refresh = function (treeNode) {
            if (treeNode.data.icon && treeNode.data.iconOpen) {
                this.q("#" + treeNode.data.id + ">div i").removeClass(treeNode.data.icon_);
                this.q("#" + treeNode.data.id + ">div i").addClass(this.getIcon(treeNode, (treeNode.getData().extracted ? treeNode.data.iconOpen : treeNode.data.icon)));
            }
        };
        Tree.prototype.triggerClicked = function (treeNode, ignoreStop) {
            var stop = false;
            if (treeNode.data.click != undefined) {
                stop = treeNode.data.click(treeNode);
            }
            if (undefined != this.onClick) {
                stop = this.onClick(treeNode);
            }
            if (!ignoreStop && stop) {
                return;
            }
            if (treeNode.hasChildren()) {
                var ul_1 = this.q("#" + treeNode.data.id + ">ul");
                if (treeNode.getData().extracted) {
                    treeNode.getData().extracted = false;
                    treeNode.data.height = ul_1.css("height");
                    if (treeNode.data.icon && treeNode.data.iconOpen) {
                        this.q("#" + treeNode.data.id + ">div i").removeClass(treeNode.data.icon_);
                        this.q("#" + treeNode.data.id + ">div i").addClass(this.getIcon(treeNode, treeNode.data.icon));
                    }
                    ul_1.animate({
                        height: "0px"
                    }, 'fast');
                }
                else {
                    treeNode.getData().extracted = true;
                    if (treeNode.data.icon && treeNode.data.iconOpen) {
                        this.q("#" + treeNode.data.id + ">div i").removeClass(treeNode.data.icon_);
                        this.q("#" + treeNode.data.id + ">div i").addClass(this.getIcon(treeNode, treeNode.data.iconOpen));
                    }
                    ul_1.animate({
                        height: treeNode.getData().height
                    }, 'fast', function () {
                        ul_1.removeCss("height");
                    });
                }
            }
            this.q("div").removeClass("active");
            this.q("#" + treeNode.data.id + ">div").addClass("active");
        };
        Tree.prototype.getIcon = function (curNode, icon) {
            if (icon) {
                if (typeof icon === 'function') {
                    return curNode.data.icon_ = icon(curNode);
                }
                else {
                    return curNode.data.icon_ = icon;
                }
            }
        };
        Tree.prototype.getIconHtml = function (curNode, icon) {
            var iconHtml = "";
            var iconClass = this.getIcon(curNode, icon);
            if (iconClass) {
                iconHtml = '<i class="' + iconClass + '"></i> ';
            }
            return iconHtml;
        };
        Tree.prototype.render = function (tree) {
            var _this = this;
            this.q().empty();
            this.treeNodes = tree_1.TreeNode.valueOfAll(tree);
            this.q().empty();
            this.q().append('<ul></ul>');
            for (var i = 0; i < this.treeNodes.length; ++i) {
                var curNode = this.treeNodes[i];
                var iconHtml = this.getIconHtml(curNode, curNode.getData().icon);
                this.q('>ul')
                    .append('<li id="' + curNode.getData().id + '"><div class="tree-item0">' +
                    iconHtml +
                    curNode.getData().value + '</div></li>');
                this.q('>ul>li:last>div').click(curNode, function (event) {
                    _this.triggerClicked(event.data);
                });
                if (curNode.hasChildren()) {
                    this.q('>ul>li:last').append('<ul></ul>');
                    this.renderChildren(this.q('>ul>li:last>ul'), curNode, 1);
                    if (curNode.data.extracted) {
                        this.extractedNodes.push(curNode);
                    }
                    curNode.data.extracted = false;
                    curNode.data.height = this.q('>ul>li:last>ul').css("height");
                    this.q('>ul>li:last>ul').css("height", "0px");
                }
            }
            for (var i = 0; i < this.extractedNodes.length; ++i) {
                this.triggerClicked(this.extractedNodes[i], true);
            }
            this.extractedNodes = [];
            return this.treeNodes;
        };
        Tree.prototype.renderChildren = function (ul, parent, depth) {
            var _this = this;
            var children = parent.subNodes;
            for (var i = 0; i < children.length; ++i) {
                var curNode = children[i];
                var iconHtml = this.getIconHtml(curNode, curNode.getData().icon);
                ul.append('<li id="' + curNode.getData().id + '"><div class="tree-item' + depth + '">' +
                    iconHtml +
                    curNode.getData().value + '</div></li>');
                ul.children('li:last').children('div').click(curNode, function (event) {
                    _this.triggerClicked(event.data);
                });
                if (curNode.hasChildren()) {
                    ul.children('li:last').append('<ul></ul>');
                    this.renderChildren(ul.children('li:last').children('ul'), curNode, depth + 1);
                    if (curNode.data.extracted) {
                        this.extractedNodes.push(curNode);
                    }
                    curNode.data.extracted = false;
                    curNode.data.height = ul.children('li:last').children('ul').css("height");
                    ul.children('li:last').children('ul').css("height", "0px");
                }
            }
        };
        return Tree;
    }());
    tree_1.Tree = Tree;
})(tree || (tree = {}));
