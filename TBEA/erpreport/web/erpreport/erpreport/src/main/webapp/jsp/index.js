var index;
(function (index) {
    var ClassListener = (function () {
        function ClassListener() {
        }
        ClassListener.listen = function (condition, callback) {
            if (condition()) {
                callback();
            }
            else {
                setTimeout(function () {
                    ClassListener.listen(condition, callback);
                }, 5);
            }
        };
        return ClassListener;
    }());
    var NavbarType;
    (function (NavbarType) {
        NavbarType[NavbarType["TOP"] = 0] = "TOP";
        NavbarType[NavbarType["LEFT"] = 1] = "LEFT";
    })(NavbarType || (NavbarType = {}));
    var treeItemMap = {};
    var curTreeItem;
    function buildTreeItemMap(tree) {
        for (var i = 0; tree && i < tree.length; ++i) {
            treeItemMap[tree[i].id] = tree[i];
            if (tree[i].url) {
                var inheritList = tree[i].title;
                var item = tree[i];
                while (item.parent) {
                    item = treeItemMap[item.parent];
                    inheritList = item.title + "##" + inheritList;
                }
                tree[i].url = encodeURI(tree[i].url + "?breadcrumb=" +
                    JSON.stringify(inheritList.split("##")) +
                    "&item=" + tree[i].id);
            }
            var spread = tree[i].spread;
            tree[i].spread = (spread == '1');
            buildTreeItemMap(tree[i].children);
        }
    }
    buildTreeItemMap(context.navTree);
    function buildTopLevel(item) {
        var ret = {
            id: item.id,
            title: item.title,
            icon: item.icon
        };
        for (var i = 0; i < item.children.length; ++i) {
            if (item.children[i].type == 0) {
                if (!ret.children) {
                    ret.children = [];
                }
                ret.children.push(item.children[i]);
            }
        }
        return ret;
    }
    function getOnLevelData() {
        var data = [];
        for (var i = 0; i < context.navTree.length; ++i) {
            if (context.navTree[i].type == 0) {
                data.push(buildTopLevel(context.navTree[i]));
            }
        }
        return data;
    }
    function triggerOnClick(ids) {
        var navItem = $(".layui-side .layui-nav-item");
        for (var i = 0, j = 0; curTreeItem && i < curTreeItem.children.length; ++i) {
            if (!curTreeItem.children[i].url) {
                if ($(navItem[j]).hasClass('layui-nav-itemed')) {
                    curTreeItem.children[i].spread = true;
                }
                else {
                    curTreeItem.children[i].spread = false;
                }
                ++j;
            }
        }
        var treeItem = treeItemMap[ids[0]];
        layui.navbar.set({ data: treeItem.children }).render(function (data) {
            layui.tab.tabAdd(data);
        });
        curTreeItem = treeItem;
        if (ids.length > 1) {
            setTimeout(function () {
                treeItem = treeItemMap[ids[1]];
                if (!treeItem.url) {
                    var parent_1 = treeItemMap[treeItem.parent];
                    var liItem_1;
                    for (var i = 0, j = 0; i < parent_1.children.length; ++i) {
                        if (parent_1.children[i].id == treeItem.id) {
                            liItem_1 = $(".layui-side .layui-nav-item:eq(" + j + ")");
                            if (!liItem_1.hasClass('layui-nav-itemed')) {
                                liItem_1.find(">a").click();
                            }
                            break;
                        }
                        if (!parent_1.children[i].url) {
                            ++j;
                        }
                    }
                    if (ids.length > 2) {
                        setTimeout(function () {
                            for (var i = 0; i < treeItem.children.length; ++i) {
                                if (treeItem.children[i].id == ids[2]) {
                                    liItem_1.find("dd:eq(" + i + ") a").click();
                                    break;
                                }
                            }
                        }, 500);
                    }
                }
                else {
                    for (var i = 0; i < treeItem.children.length; ++i) {
                        if (treeItem.children[i].id == ids[2]) {
                            var ddItem = $(".layui-nav-tree").children().eq(i).find("a").click();
                            break;
                        }
                    }
                }
            }, 500);
        }
    }
    function findParents(itemId) {
        var parents = [itemId];
        var treeItem = treeItemMap[itemId];
        while (treeItem.parent) {
            parents = [treeItem.parent].concat(parents);
            treeItem = treeItemMap[treeItem.parent];
            if (treeItem.type == NavbarType.TOP) {
                break;
            }
        }
        return parents;
    }
    function init() {
        var lastId;
        var onelevelopt = {
            elem: "#navTop",
            data: getOnLevelData(),
            onClicked: function (id) {
                if (lastId != id) {
                    triggerOnClick([id]);
                    lastId = id;
                }
            }
        };
        layui.onelevel.set(onelevelopt).render();
        var items = [context.item];
        if (!items[0]) {
            if (onelevelopt.data[0].children) {
                items = [onelevelopt.data[0].children[0].id];
            }
            else {
                items = [context.item = onelevelopt.data[0].id];
            }
        }
        else {
            items = findParents(context.item);
        }
        lastId = items[0];
        triggerOnClick(items);
    }
    index.init = init;
})(index || (index = {}));
