Array.prototype.pushAll = function (items) {
    for (var i = 0; i < items.length; ++i) {
        this.push(items[i]);
    }
    return this;
};
function createNode(value, url, icon, iconOpen) {
    if (url) {
        var protocol = "http://";
        if (url.length < protocol.length || url.substring(0, protocol.length) != protocol) {
            url = baseUrl + url;
        }
        icon = icon ? icon : "fa fa-dot-circle-o";
        iconOpen = iconOpen ? iconOpen : "fa fa-dot-circle-o";
    }
    else {
        url = undefined;
        icon = icon ? icon : "fa fa-plus-square-o";
        iconOpen = iconOpen ? iconOpen : "fa fa-minus-square-o";
    }
    var node = {
        data: {
            id: navi.uid(),
            value: value,
            url: url,
            icon: icon,
            iconOpen: iconOpen
        },
        subNodes: []
    };
    node.append = function (sub) {
        if (sub != undefined) {
            if (sub instanceof Array) {
                node.subNodes.pushAll(sub);
            }
            else {
                node.subNodes.push(sub);
            }
        }
        return node;
    };
    return node;
}
var navi;
(function (navi) {
    var Builder = (function () {
        function Builder() {
            this.builders = {};
        }
        Builder.prototype.register = function (group, builder) {
            if (this.builders[group] == undefined) {
                this.builders[group] = [];
            }
            this.builders[group].push(builder);
        };
        Builder.prototype.build = function (group) {
            var ret = [];
            if (this.builders[group] != undefined) {
                for (var i = 0; i < this.builders[group].length; ++i) {
                    var nodes = this.builders[group][i]();
                    if (nodes instanceof Array) {
                        ret.pushAll(nodes);
                    }
                    else {
                        ret.push(nodes);
                    }
                }
            }
            return ret;
        };
        Builder.build = function (naviItems) {
            var workMap = {};
            var changed = true;
            var ret = [];
            for (var i_1 = 0; i_1 < naviItems.length; ++i_1) {
                if (!naviItems[i_1].parent) {
                    var node = createNode(naviItems[i_1].name, naviItems[i_1].url, naviItems[i_1].iconOpen, naviItems[i_1].iconClose);
                    node.data.extracted = (naviItems[i_1].extend == 1);
                    ret.push(node);
                    workMap[naviItems[i_1].id] = node;
                }
            }
            while (changed) {
                changed = false;
                for (var i_2 = 0; i_2 < naviItems.length; ++i_2) {
                    if (naviItems[i_2].parent && workMap[naviItems[i_2].parent]) {
                        var node = createNode(naviItems[i_2].name, naviItems[i_2].url, naviItems[i_2].iconOpen, naviItems[i_2].iconClose);
                        node.data.extracted = (naviItems[i_2].extend == 1);
                        workMap[naviItems[i_2].parent].append(node);
                        workMap[naviItems[i_2].id] = node;
                        naviItems[i_2].parent = undefined;
                        changed = true;
                    }
                }
            }
            var isExtracted = false;
            function clean(node) {
                for (var i = 0; i < node.subNodes.length; ++i) {
                    if (clean(node.subNodes[i])) {
                        node.subNodes.splice(i, 1);
                        --i;
                    }
                }
                if (node.subNodes.length == 0 && !node.data.url) {
                    return true;
                }
                if (!isExtracted) {
                    if (node.data.extracted) {
                        isExtracted = true;
                    }
                }
                else {
                    node.data.extracted = undefined;
                }
                return false;
            }
            for (var i = 0; i < ret.length; ++i) {
                if (clean(ret[i])) {
                    ret.splice(i, 1);
                    --i;
                }
            }
            return ret;
        };
        return Builder;
    }());
    navi.Builder = Builder;
    navi.uid = (function (seed) {
        return function () {
            ++seed;
            return seed + "";
        };
    })(new Date().getTime());
})(navi || (navi = {}));
var builder = new navi.Builder();
