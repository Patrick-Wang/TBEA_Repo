var Util;
(function (Util) {
    var DateDataSet = (function () {
        function DateDataSet(baseResUrl) {
            this.mDataMap = {};
            this.mBaseResUrl = baseResUrl;
        }
        DateDataSet.prototype.getData = function (m, y, callBack) {
            var _this = this;
            if (undefined == this.mDataMap[y + ""]) {
                this.mDataMap[y + ""] = {};
            }
            if (undefined == this.mDataMap[y + ""][m + ""]) {
                $.ajax({
                    type: "GET",
                    url: this.mBaseResUrl + "?month=" + m + "&year=" + y + "",
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

        DateDataSet.prototype.getDataByDay = function (m, y, d, callBack) {
            var _this = this;
            if (undefined == this.mDataMap[y + ""]) {
                this.mDataMap[y + ""] = {};
            }
            if (undefined == this.mDataMap[y + ""][m + ""]) {
                this.mDataMap[y + ""][m + ""] = {};
            }
            if (undefined == this.mDataMap[y + ""][m + ""][d + ""]) {
                $.ajax({
                    type: "GET",
                    url: this.mBaseResUrl + "?month=" + m + "&year=" + y + "&day=" + d,
                    success: function (data) {
                        var jsnData = JSON.parse(data);
                        _this.mDataMap[y + ""][m + ""][d + ""] = jsnData;
                        callBack(jsnData);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        callBack(null);
                    }
                });
            } else {
                callBack(this.mDataMap[y + ""][m + ""][d + ""]);
            }
        };
        return DateDataSet;
    })();
    Util.DateDataSet = DateDataSet;

    function formatCurrency(val) {
        if (val == "--" || val == "") {
            return val;
        }
        val = parseFloat(val).toFixed(2) + "";
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
