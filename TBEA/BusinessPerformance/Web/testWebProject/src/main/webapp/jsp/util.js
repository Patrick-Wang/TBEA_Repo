var Util;
(function (Util) {
    (function (CompanyType) {
        CompanyType[CompanyType["SB"] = 0] = "SB";
        CompanyType[CompanyType["HB"] = 1] = "HB";
        CompanyType[CompanyType["XB"] = 2] = "XB";
        CompanyType[CompanyType["TB"] = 3] = "TB";
        CompanyType[CompanyType["LL"] = 4] = "LL";
        CompanyType[CompanyType["XL"] = 5] = "XL";
        CompanyType[CompanyType["DL"] = 6] = "DL";
        CompanyType[CompanyType["XNY"] = 7] = "XNY";
        CompanyType[CompanyType["GY"] = 8] = "GY";
        CompanyType[CompanyType["TCNY"] = 9] = "TCNY";
        CompanyType[CompanyType["NDGS"] = 10] = "NDGS";
        CompanyType[CompanyType["ZJWL"] = 11] = "ZJWL";
        CompanyType[CompanyType["JCK"] = 12] = "JCK";
        CompanyType[CompanyType["GCGS"] = 13] = "GCGS";
        CompanyType[CompanyType["ZH"] = 14] = "ZH";
        CompanyType[CompanyType["SBDCY"] = 15] = "SBDCY";
        CompanyType[CompanyType["XNYCY"] = 16] = "XNYCY";
        CompanyType[CompanyType["NYCY"] = 17] = "NYCY";
        CompanyType[CompanyType["GCL"] = 18] = "GCL";
        CompanyType[CompanyType["JT"] = 19] = "JT";
        CompanyType[CompanyType["ALL"] = 100] = "ALL";
    })(Util.CompanyType || (Util.CompanyType = {}));
    var CompanyType = Util.CompanyType;

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

        DateDataSet.prototype.getDataByCompany = function (m, y, compId, callBack) {
            var _this = this;
            if (undefined == this.mDataMap[y + ""]) {
                this.mDataMap[y + ""] = {};
            }
            if (undefined == this.mDataMap[y + ""][m + ""]) {
                this.mDataMap[y + ""][m + ""] = {};
            }
            if (undefined == this.mDataMap[y + ""][m + ""][compId + ""]) {
                $.ajax({
                    type: "GET",
                    url: this.mBaseResUrl + "?month=" + m + "&year=" + y + "&companyId=" + compId,
                    success: function (data) {
                        _this.mDataMap[y + ""][m + ""][compId + ""] = data;
                        callBack(data);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        callBack(null);
                    }
                });
            } else {
                callBack(this.mDataMap[y + ""][m + ""][compId + ""]);
            }
        };
        return DateDataSet;
    })();
    Util.DateDataSet = DateDataSet;

    function formatCurrency(val) {
        if (val === "--" || val === "") {
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
