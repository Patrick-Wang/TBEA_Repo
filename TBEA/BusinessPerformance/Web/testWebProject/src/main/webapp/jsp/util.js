var Util;
(function (Util) {
    (function (EntyType) {
        EntyType[EntyType["QNJH"] = 0] = "QNJH";
        EntyType[EntyType["BY20"] = 1] = "BY20";
        EntyType[EntyType["BY28"] = 2] = "BY28";
        EntyType[EntyType["BYSJ"] = 3] = "BYSJ";
    })(Util.EntyType || (Util.EntyType = {}));
    var EntyType = Util.EntyType;
    (function (ZBType) {
        ZBType[ZBType["QNJH"] = 0] = "QNJH";
        ZBType[ZBType["BY20JH"] = 1] = "BY20JH";
        ZBType[ZBType["BY28JH"] = 2] = "BY28JH";
        ZBType[ZBType["BY20YJ"] = 3] = "BY20YJ";
        ZBType[ZBType["BY28YJ"] = 4] = "BY28YJ";
        ZBType[ZBType["BYSJ"] = 5] = "BYSJ";
    })(Util.ZBType || (Util.ZBType = {}));
    var ZBType = Util.ZBType;
    (function (CompanyType) {
        CompanyType[CompanyType["SB"] = 0] = "SB";
        CompanyType[CompanyType["HB"] = 1] = "HB";
        CompanyType[CompanyType["XB"] = 2] = "XB";
        CompanyType[CompanyType["LL"] = 3] = "LL";
        CompanyType[CompanyType["XL"] = 4] = "XL";
        CompanyType[CompanyType["DL"] = 5] = "DL";
        CompanyType[CompanyType["XNY"] = 6] = "XNY";
        CompanyType[CompanyType["GY"] = 7] = "GY";
        CompanyType[CompanyType["TCNY"] = 8] = "TCNY";
        CompanyType[CompanyType["NDGS"] = 9] = "NDGS";
        CompanyType[CompanyType["ZJWL"] = 10] = "ZJWL";
        CompanyType[CompanyType["JCK"] = 11] = "JCK";
        CompanyType[CompanyType["ZH"] = 12] = "ZH";
        CompanyType[CompanyType["SBDCY"] = 13] = "SBDCY";
        CompanyType[CompanyType["XNYCY"] = 14] = "XNYCY";
        CompanyType[CompanyType["NYCY"] = 15] = "NYCY";
        CompanyType[CompanyType["GCL"] = 16] = "GCL";
        CompanyType[CompanyType["JT"] = 17] = "JT";
        CompanyType[CompanyType["BYQC"] = 18] = "BYQC";
        CompanyType[CompanyType["GJMYCTGS"] = 19] = "GJMYCTGS";
        CompanyType[CompanyType["ZTFGS"] = 20] = "ZTFGS";
        CompanyType[CompanyType["KJHGQ"] = 21] = "KJHGQ";
        CompanyType[CompanyType["DQZJFGS"] = 22] = "DQZJFGS";
        CompanyType[CompanyType["DLZDHGS"] = 23] = "DLZDHGS";
        CompanyType[CompanyType["SKGS"] = 24] = "SKGS";
        CompanyType[CompanyType["XSZX"] = 25] = "XSZX";
        CompanyType[CompanyType["XDWLGS"] = 26] = "XDWLGS";
        CompanyType[CompanyType["DLKCSJGS"] = 27] = "DLKCSJGS";
        CompanyType[CompanyType["XLGGS"] = 28] = "XLGGS";
        CompanyType[CompanyType["GNCTB"] = 29] = "GNCTB";
        CompanyType[CompanyType["DLAZB"] = 30] = "DLAZB";
        CompanyType[CompanyType["ZXGS"] = 31] = "ZXGS";
        CompanyType[CompanyType["HXGS"] = 32] = "HXGS";
        CompanyType[CompanyType["TBDG_YD_NYYXGS"] = 33] = "TBDG_YD_NYYXGS";
        CompanyType[CompanyType["SBWYGS"] = 34] = "SBWYGS";
        CompanyType[CompanyType["DQFGS"] = 35] = "DQFGS";
        CompanyType[CompanyType["HNGJWLGS"] = 36] = "HNGJWLGS";
        CompanyType[CompanyType["HNGCGS"] = 37] = "HNGCGS";
        CompanyType[CompanyType["ZYGS"] = 38] = "ZYGS";
        CompanyType[CompanyType["HNZNDQGS"] = 39] = "HNZNDQGS";
        CompanyType[CompanyType["NJZNDQGS"] = 40] = "NJZNDQGS";
        CompanyType[CompanyType["HNYLGS"] = 41] = "HNYLGS";
        CompanyType[CompanyType["TBGS"] = 42] = "TBGS";
        CompanyType[CompanyType["ZTGS"] = 43] = "ZTGS";
        CompanyType[CompanyType["XBGS"] = 44] = "XBGS";
        CompanyType[CompanyType["GJCTGCGS"] = 45] = "GJCTGCGS";
        CompanyType[CompanyType["GNGCJXGS"] = 46] = "GNGCJXGS";
        CompanyType[CompanyType["XJXTGJWLMYGS"] = 47] = "XJXTGJWLMYGS";
        CompanyType[CompanyType["XSZGS"] = 48] = "XSZGS";
        CompanyType[CompanyType["SDFGS"] = 49] = "SDFGS";
        CompanyType[CompanyType["GJCTZGS"] = 50] = "GJCTZGS";
        CompanyType[CompanyType["LL_TYDXXMGS"] = 51] = "LL_TYDXXMGS";
        CompanyType[CompanyType["LL_JNDXXMGS"] = 52] = "LL_JNDXXMGS";
        CompanyType[CompanyType["DLCLFGS"] = 53] = "DLCLFGS";
        CompanyType[CompanyType["TZDLYFXMGS"] = 54] = "TZDLYFXMGS";
        CompanyType[CompanyType["DLJXSYB"] = 55] = "DLJXSYB";
        CompanyType[CompanyType["XNYSYB"] = 56] = "XNYSYB";
        CompanyType[CompanyType["DLGCGS"] = 57] = "DLGCGS";
        CompanyType[CompanyType["SDDLGCGS"] = 58] = "SDDLGCGS";
        CompanyType[CompanyType["KYDLXMGS"] = 59] = "KYDLXMGS";
        CompanyType[CompanyType["XL_TYDXXMGS"] = 60] = "XL_TYDXXMGS";
        CompanyType[CompanyType["XL_JNDXXMGS"] = 61] = "XL_JNDXXMGS";
        CompanyType[CompanyType["TZDLXMGS"] = 62] = "TZDLXMGS";
        CompanyType[CompanyType["DLDLXMGS"] = 63] = "DLDLXMGS";
        CompanyType[CompanyType["TZDGXJDGCLYXGS"] = 64] = "TZDGXJDGCLYXGS";
        CompanyType[CompanyType["DKHYB"] = 65] = "DKHYB";
        CompanyType[CompanyType["DKHEB"] = 66] = "DKHEB";
        CompanyType[CompanyType["GJMYB"] = 67] = "GJMYB";
        CompanyType[CompanyType["GNGCGS"] = 68] = "GNGCGS";
        CompanyType[CompanyType["GJGCGS"] = 69] = "GJGCGS";
        CompanyType[CompanyType["ZJZTGJWLYXGS"] = 70] = "ZJZTGJWLYXGS";
        CompanyType[CompanyType["TLGS"] = 71] = "TLGS";
        CompanyType[CompanyType["XTGS"] = 72] = "XTGS";
        CompanyType[CompanyType["GCGS"] = 73] = "GCGS";
        CompanyType[CompanyType["BB"] = 74] = "BB";
        CompanyType[CompanyType["XSGS"] = 75] = "XSGS";
        CompanyType[CompanyType["GJYWB"] = 76] = "GJYWB";
        CompanyType[CompanyType["FLSWB"] = 77] = "FLSWB";
        CompanyType[CompanyType["DYCJGJWLMYYXGS"] = 78] = "DYCJGJWLMYYXGS";
        CompanyType[CompanyType["XNDQGS"] = 79] = "XNDQGS";
        CompanyType[CompanyType["ALL"] = 1000] = "ALL";
    })(Util.CompanyType || (Util.CompanyType = {}));
    var CompanyType = Util.CompanyType;
    $.ajaxSetup({ cache: false });
    var Promise = (function () {
        function Promise() {
            this.mSuccessList = [];
            this.mFailedList = [];
        }
        Promise.prototype.succeed = function (data) {
            for (var i = 0; i < this.mSuccessList.length; ++i) {
                this.mSuccessList[i](data);
            }
        };
        Promise.prototype.failed = function (data) {
            for (var i = 0; i < this.mFailedList.length; ++i) {
                this.mFailedList[i](data);
            }
        };
        Promise.prototype.then = function (success, failed) {
            if (null != success && undefined != success) {
                this.mSuccessList.push(success);
            }
            if (failed != null && failed != undefined) {
                this.mFailedList.push(failed);
            }
            return this;
        };
        return Promise;
    })();
    Util.Promise = Promise;
    var Ajax = (function () {
        function Ajax(baseUrl, useCache) {
            if (useCache === void 0) { useCache = true; }
            this.mCache = {};
            this.mBaseUrl = baseUrl;
            this.mUseCache = useCache;
        }
        Ajax.prototype.generateKey = function (option) {
            var keys = [];
            for (var key in option) {
                keys.push(key + option[key]);
            }
            keys.sort();
            return keys.join("&");
        };
        Ajax.prototype.setCache = function (option, data) {
            if (this.mUseCache) {
                this.mCache[this.generateKey(option)] = data;
            }
        };
        Ajax.prototype.clean = function () {
            this.mCache = {};
        };
        Ajax.prototype.getCache = function (option) {
            return this.mCache[this.generateKey(option)];
        };
        Ajax.prototype.post = function (option) {
            var promise = new Promise();
            $.ajax({
                type: "POST",
                url: this.mBaseUrl,
                data: option,
                success: function (data) {
                    var jsonData = JSON.parse(data);
                    promise.succeed(jsonData);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    promise.failed(textStatus);
                }
            });
            return promise;
        };
        Ajax.prototype.get = function (option) {
            var _this = this;
            var promise = new Promise();
            var cacheData = this.getCache(option);
            if (undefined == cacheData) {
                $.ajax({
                    type: "GET",
                    url: this.mBaseUrl,
                    data: option,
                    success: function (data) {
                        var jsonData = JSON.parse(data);
                        _this.setCache(option, jsonData);
                        promise.succeed(jsonData);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        promise.failed(textStatus);
                    }
                });
            }
            else {
                setTimeout(function () {
                    promise.succeed(cacheData);
                }, 0);
            }
            return promise;
        };
        return Ajax;
    })();
    Util.Ajax = Ajax;
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
    function isExist(val) {
        return val != undefined;
    }
    Util.isExist = isExist;
})(Util || (Util = {}));
