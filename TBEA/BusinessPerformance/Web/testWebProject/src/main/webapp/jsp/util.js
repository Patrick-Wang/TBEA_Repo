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
