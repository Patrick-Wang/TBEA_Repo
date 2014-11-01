var std;
(function (std) {
    var vector = (function () {
        function vector() {
            this.mContainer = [];
        }
        vector.prototype.back = function () {
            if (this.mContainer.length > 0) {
                return this.mContainer[this.mContainer.length - 1];
            }
            return null;
        };

        vector.prototype.pop = function () {
            if (this.mContainer.length > 0) {
                return this.erase(this.mContainer.length - 1);
            }
            return null;
        };

        vector.prototype.push = function (e) {
            this.mContainer.push(e);
            return this;
        };

        vector.prototype.erase = function (index) {
            if (index >= this.mContainer.length) {
                return null;
            }
            return this.mContainer.splice(index, 1)[0];
        };

        vector.prototype.find = function (item) {
            for (var i = 0; i < this.mContainer.length; i++) {
                if (this.mContainer[i] == item) {
                    return i;
                }
            }
            return -1;
        };

        vector.prototype.reverse = function () {
            var newContainer = new vector();
            for (var i = 0; i < this.mContainer.length; i++) {
                newContainer.insert(0, this.mContainer[i]);
            }
            this.mContainer = newContainer.toArray();
            return this;
        };

        vector.prototype.insert = function (index, item) {
            this.mContainer.splice(index, 0, item);
            return this;
        };

        vector.prototype.size = function () {
            return this.mContainer.length;
        };

        vector.prototype.get = function (index) {
            return this.mContainer[index];
        };

        vector.prototype.set = function (index, e) {
            this.mContainer[index] = e;
            return this;
        };

        vector.prototype.sort = function (callback) {
            this.mContainer.sort(callback);
            return this;
        };

        vector.prototype.contains = function (obj) {
            return this.find(obj) >= 0;
        };

        vector.prototype.concat = function (arr) {
            if (arr instanceof Array) {
                this.mContainer = this.mContainer.concat(arr);
            } else if (arr instanceof vector) {
                this.mContainer = this.mContainer.concat(arr.container);
            }
            return this;
        };

        vector.prototype.toArray = function () {
            return this.mContainer;
        };

        vector.prototype.isEmpty = function () {
            return this.mContainer.length == 0;
        };
        return vector;
    })();
    std.vector = vector;
})(std || (std = {}));
//# sourceMappingURL=vector.js.map
