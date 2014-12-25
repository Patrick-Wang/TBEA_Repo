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
        CompanyType[CompanyType["ZH"] = 13] = "ZH";
        CompanyType[CompanyType["SBDCY"] = 14] = "SBDCY";
        CompanyType[CompanyType["XNYCY"] = 15] = "XNYCY";
        CompanyType[CompanyType["NYCY"] = 16] = "NYCY";
        CompanyType[CompanyType["GCL"] = 17] = "GCL";
        CompanyType[CompanyType["JT"] = 18] = "JT";
        CompanyType[CompanyType["BYQC"] = 19] = "BYQC";
        CompanyType[CompanyType["GJMYCTGS"] = 20] = "GJMYCTGS";
        CompanyType[CompanyType["ZTFGS"] = 21] = "ZTFGS";
        CompanyType[CompanyType["KJHGQ"] = 22] = "KJHGQ";
        CompanyType[CompanyType["DQZJFGS"] = 23] = "DQZJFGS";
        CompanyType[CompanyType["DLZDHGS"] = 24] = "DLZDHGS";
        CompanyType[CompanyType["SKGS"] = 25] = "SKGS";
        CompanyType[CompanyType["XSZX"] = 26] = "XSZX";
        CompanyType[CompanyType["XDWLGS"] = 27] = "XDWLGS";
        CompanyType[CompanyType["DLKCSJGS"] = 28] = "DLKCSJGS";
        CompanyType[CompanyType["XLGGS"] = 29] = "XLGGS";
        CompanyType[CompanyType["GNCTB"] = 30] = "GNCTB";
        CompanyType[CompanyType["DLAZB"] = 31] = "DLAZB";
        CompanyType[CompanyType["ZXGS"] = 32] = "ZXGS";
        CompanyType[CompanyType["HXGS"] = 33] = "HXGS";
        CompanyType[CompanyType["TBDG_YD_NYYXGS"] = 34] = "TBDG_YD_NYYXGS";
        CompanyType[CompanyType["SBWYGS"] = 35] = "SBWYGS";
        CompanyType[CompanyType["DQFGS"] = 36] = "DQFGS";
        CompanyType[CompanyType["HNGJWLGS"] = 37] = "HNGJWLGS";
        CompanyType[CompanyType["HNGCGS"] = 38] = "HNGCGS";
        CompanyType[CompanyType["ZYGS"] = 39] = "ZYGS";
        CompanyType[CompanyType["HNZNDQGS"] = 40] = "HNZNDQGS";
        CompanyType[CompanyType["NJZNDQGS"] = 41] = "NJZNDQGS";
        CompanyType[CompanyType["HNYLGS"] = 42] = "HNYLGS";
        CompanyType[CompanyType["TBGS"] = 43] = "TBGS";
        CompanyType[CompanyType["ZTGS"] = 44] = "ZTGS";
        CompanyType[CompanyType["XBGS"] = 45] = "XBGS";
        CompanyType[CompanyType["GJCTGCGS"] = 46] = "GJCTGCGS";
        CompanyType[CompanyType["GNGCJXGS"] = 47] = "GNGCJXGS";
        CompanyType[CompanyType["XJXTGJWLMYGS"] = 48] = "XJXTGJWLMYGS";
        CompanyType[CompanyType["XSZGS"] = 49] = "XSZGS";
        CompanyType[CompanyType["SDFGS"] = 50] = "SDFGS";
        CompanyType[CompanyType["GJCTZGS"] = 51] = "GJCTZGS";
        CompanyType[CompanyType["LL_TYDXXMGS"] = 52] = "LL_TYDXXMGS";
        CompanyType[CompanyType["LL_JNDXXMGS"] = 53] = "LL_JNDXXMGS";
        CompanyType[CompanyType["DLCLFGS"] = 54] = "DLCLFGS";
        CompanyType[CompanyType["TZDLYFXMGS"] = 55] = "TZDLYFXMGS";
        CompanyType[CompanyType["DLJXSYB"] = 56] = "DLJXSYB";
        CompanyType[CompanyType["XNYSYB"] = 57] = "XNYSYB";
        CompanyType[CompanyType["DLGCGS"] = 58] = "DLGCGS";
        CompanyType[CompanyType["SDDLGCGS"] = 59] = "SDDLGCGS";
        CompanyType[CompanyType["KYDLXMGS"] = 60] = "KYDLXMGS";
        CompanyType[CompanyType["XL_TYDXXMGS"] = 61] = "XL_TYDXXMGS";
        CompanyType[CompanyType["XL_JNDXXMGS"] = 62] = "XL_JNDXXMGS";
        CompanyType[CompanyType["TZDLXMGS"] = 63] = "TZDLXMGS";
        CompanyType[CompanyType["DLDLXMGS"] = 64] = "DLDLXMGS";
        CompanyType[CompanyType["TZDGXJDGCLYXGS"] = 65] = "TZDGXJDGCLYXGS";
        CompanyType[CompanyType["DKHYB"] = 66] = "DKHYB";
        CompanyType[CompanyType["DKHEB"] = 67] = "DKHEB";
        CompanyType[CompanyType["GJMYB"] = 68] = "GJMYB";
        CompanyType[CompanyType["GNGCGS"] = 69] = "GNGCGS";
        CompanyType[CompanyType["GJGCGS"] = 70] = "GJGCGS";
        CompanyType[CompanyType["ZJZTGJWLYXGS"] = 71] = "ZJZTGJWLYXGS";
        CompanyType[CompanyType["TLGS"] = 72] = "TLGS";
        CompanyType[CompanyType["XTGS"] = 73] = "XTGS";
        CompanyType[CompanyType["GCGS"] = 74] = "GCGS";
        CompanyType[CompanyType["BB"] = 75] = "BB";
        CompanyType[CompanyType["XSGS"] = 76] = "XSGS";
        CompanyType[CompanyType["GJYWB"] = 77] = "GJYWB";
        CompanyType[CompanyType["FLSWB"] = 78] = "FLSWB";
        CompanyType[CompanyType["DYCJGJWLMYYXGS"] = 79] = "DYCJGJWLMYYXGS";
        CompanyType[CompanyType["XNDQGS"] = 80] = "XNDQGS";
        CompanyType[CompanyType["ALL"] = 1000] = "ALL";
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
            }
            else {
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
            }
            else {
                callBack(this.mDataMap[y + ""][m + ""][d + ""]);
            }
        };
        DateDataSet.prototype.getDataByYear = function (y, compId, callBack) {
            var _this = this;
            if (undefined == this.mDataMap[y + ""]) {
                this.mDataMap[y + ""] = {};
            }
            if (undefined == this.mDataMap[y + ""][compId + ""]) {
                $.ajax({
                    type: "GET",
                    url: this.mBaseResUrl + "?year=" + y + "&companyId=" + compId,
                    success: function (data) {
                        _this.mDataMap[y + ""][compId + ""] = data;
                        callBack(data);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        callBack(null);
                    }
                });
            }
            else {
                callBack(this.mDataMap[y + ""][compId + ""]);
            }
        };
        DateDataSet.prototype.getDataByYearOnly = function (y, callBack) {
            var _this = this;
            if (undefined == this.mDataMap[y + ""]) {
                $.ajax({
                    type: "GET",
                    url: this.mBaseResUrl + "?year=" + y,
                    success: function (data) {
                        _this.mDataMap[y + ""] = JSON.parse(data);
                        callBack(_this.mDataMap[y + ""]);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        callBack(null);
                    }
                });
            }
            else {
                callBack(this.mDataMap[y + ""]);
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
            }
            else {
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
            }
            else {
                intPart = val.substring(1, dot);
            }
            parts.push(val.substring(dot));
        }
        else {
            if (positive) {
                intPart = val;
            }
            else {
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
