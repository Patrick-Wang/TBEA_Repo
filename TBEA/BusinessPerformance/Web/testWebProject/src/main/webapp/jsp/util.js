var Util;
(function (Util) {
    var RestDateDataSet = (function () {
        function RestDateDataSet(baseResUrl) {
            this.mDataMap = {};
            this.mBaseResUrl = baseResUrl;
        }
        RestDateDataSet.prototype.getData = function (m, y, callBack) {
            var _this = this;
            if (undefined == this.mDataMap[y + ""]) {
                this.mDataMap[y + ""] = {};
            }
            if (undefined == this.mDataMap[y + ""][m + ""]) {
                $.ajax({
                    type: "GET",
                    url: this.mBaseResUrl + "/" + m + "/" + y + "",
                    success: function (data) {
                        var jsnData = JSON.parse(data);
                        _this.mDataMap[y + ""][m + ""] = jsnData;
                        callBack(jsnData);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        callBack(null);
                    }
                });
            } else {
                callBack(this.mDataMap[y + ""][m + ""]);
            }
        };
        return RestDateDataSet;
    })();
    Util.RestDateDataSet = RestDateDataSet;

    function formatCurrency(val) {
        if (val == "--" || val == "") {
            return val;
        }

        var dot = val.lastIndexOf('.');
        var intPart = "";
        var parts = [];
        var positive = (val.charAt(0) != '-');
        if (dot > 0) {
            if (positive) {
                intPart = val.substring(0, dot);
            } else {
                intPart = val.substring(1, dot);
            }
            parts.push(val.substring(dot));
        } else {
            if (positive) {
                intPart = val;
            } else {
                intPart = val.substring(1);
            }
        }

        var leftLength = intPart.length;

        while (leftLength > 3) {
            parts.push("," + intPart.substring(leftLength - 3, leftLength));
            leftLength -= 3;
        }

        parts.push(intPart.substring(0, leftLength));

        if (!positive) {
            parts.push("-");
        }

        parts = parts.reverse();

        return parts.join("");
    }
    Util.formatCurrency = formatCurrency;
})(Util || (Util = {}));
//# sourceMappingURL=util.js.map
