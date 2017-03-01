var navi;
(function (navi) {
    Array.prototype.pushAll = function (items) {
        for (var i = 0; i < items.length; ++i) {
            this.push(items[i]);
        }
        return this;
    };
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
        return Builder;
    })();
    navi.Builder = Builder;
    navi.uid = (function (seed) {
        return function () {
            ++seed;
            return seed + "";
        };
    })(new Date().getTime());
})(navi || (navi = {}));
var builder = new navi.Builder();
