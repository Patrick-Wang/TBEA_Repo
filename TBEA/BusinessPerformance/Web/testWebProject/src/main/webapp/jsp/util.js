var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="jqgrid/vector.ts" />
var Util;
(function (Util) {
    function indexOf(arr, val) {
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }
    var AbstractFormatHandler = (function () {
        function AbstractFormatHandler(zbs, cols) {
            this.mZbs = zbs;
            this.mCols = cols;
        }
        AbstractFormatHandler.prototype.handle = function (zb, col, val) {
            return "";
        };
        AbstractFormatHandler.prototype.match = function (zb, col) {
            if (this.mZbs.length == 0 || indexOf(this.mZbs, zb) >= 0) {
                if (this.mCols.length == 0 || indexOf(this.mCols, col) >= 0) {
                    return true;
                }
            }
            return false;
        };
        AbstractFormatHandler.prototype.callNext = function (zb, col, val) {
            if (this.mNextHandler != undefined) {
                return this.mNextHandler.handle(zb, col, val);
            }
            return "--";
        };
        AbstractFormatHandler.prototype.next = function (handler) {
            this.mNextHandler = handler;
            return handler;
        };
        return AbstractFormatHandler;
    })();
    var FormatIntHandler = (function (_super) {
        __extends(FormatIntHandler, _super);
        function FormatIntHandler(zbs, cols) {
            if (zbs === void 0) { zbs = []; }
            if (cols === void 0) { cols = []; }
            _super.call(this, zbs, cols);
        }
        FormatIntHandler.prototype.handle = function (zb, col, val) {
            if (this.match(zb, col)) {
                return Util.formatInt(val);
            }
            else {
                return this.callNext(zb, col, val);
            }
        };
        return FormatIntHandler;
    })(AbstractFormatHandler);
    Util.FormatIntHandler = FormatIntHandler;
    var FormatCurrencyHandler = (function (_super) {
        __extends(FormatCurrencyHandler, _super);
        function FormatCurrencyHandler(zbs, cols) {
            if (zbs === void 0) { zbs = []; }
            if (cols === void 0) { cols = []; }
            _super.call(this, zbs, cols);
        }
        FormatCurrencyHandler.prototype.handle = function (zb, col, val) {
            if (this.match(zb, col)) {
                return Util.formatCurrency(val);
            }
            else {
                return this.callNext(zb, col, val);
            }
        };
        return FormatCurrencyHandler;
    })(AbstractFormatHandler);
    Util.FormatCurrencyHandler = FormatCurrencyHandler;
    var FormatPercentHandler = (function (_super) {
        __extends(FormatPercentHandler, _super);
        function FormatPercentHandler(zbs, cols) {
            if (zbs === void 0) { zbs = []; }
            if (cols === void 0) { cols = []; }
            _super.call(this, zbs, cols);
        }
        FormatPercentHandler.prototype.handle = function (zb, col, val) {
            if (this.match(zb, col)) {
                return Util.formatPercent(val);
            }
            else {
                return this.callNext(zb, col, val);
            }
        };
        return FormatPercentHandler;
    })(AbstractFormatHandler);
    Util.FormatPercentHandler = FormatPercentHandler;
    var FormatPercentSignalHandler = (function (_super) {
        __extends(FormatPercentSignalHandler, _super);
        function FormatPercentSignalHandler(zbs, cols) {
            if (zbs === void 0) { zbs = []; }
            if (cols === void 0) { cols = []; }
            _super.call(this, zbs, cols);
        }
        FormatPercentSignalHandler.prototype.handle = function (zb, col, val) {
            if (this.match(zb, col)) {
                return Util.formatPercentSignal(val);
            }
            else {
                return this.callNext(zb, col, val);
            }
        };
        return FormatPercentSignalHandler;
    })(AbstractFormatHandler);
    Util.FormatPercentSignalHandler = FormatPercentSignalHandler;
    var FormatFordotHandler = (function (_super) {
        __extends(FormatFordotHandler, _super);
        function FormatFordotHandler(dotCount, zbs, cols) {
            if (dotCount === void 0) { dotCount = 1; }
            if (zbs === void 0) { zbs = []; }
            if (cols === void 0) { cols = []; }
            _super.call(this, zbs, cols);
            this.mDotCount = dotCount;
        }
        FormatFordotHandler.prototype.handle = function (zb, col, val) {
            if (this.match(zb, col)) {
                return Util.formatFordot(val, this.mDotCount);
            }
            else {
                return this.callNext(zb, col, val);
            }
        };
        return FormatFordotHandler;
    })(AbstractFormatHandler);
    Util.FormatFordotHandler = FormatFordotHandler;
    var ZBStatus = (function () {
        function ZBStatus() {
        }
        ZBStatus.NONE = "NONE";
        ZBStatus.APPROVED = "APPROVED";
        ZBStatus.SUBMITTED = "SUBMITTED";
        ZBStatus.SAVED = "SAVED";
        ZBStatus.APPROVED_2 = "APPROVED_2";
        ZBStatus.SUBMITTED_2 = "SUBMITTED_2";
        return ZBStatus;
    })();
    Util.ZBStatus = ZBStatus;
    (function (ZBType) {
        ZBType[ZBType["QNJH"] = 0] = "QNJH";
        ZBType[ZBType["YDJDMJH"] = 1] = "YDJDMJH";
        ZBType[ZBType["BY20YJ"] = 2] = "BY20YJ";
        ZBType[ZBType["BY28YJ"] = 3] = "BY28YJ";
        ZBType[ZBType["BYSJ"] = 4] = "BYSJ";
    })(Util.ZBType || (Util.ZBType = {}));
    var ZBType = Util.ZBType;
    (function (CompanyType) {
        CompanyType[CompanyType["SB"] = 0] = "SB";
        CompanyType[CompanyType["HB"] = 1] = "HB";
        CompanyType[CompanyType["XB"] = 2] = "XB";
        // TB      ,// ("天变公司"),   
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
    //    export function parse(jsstr: string): any {
    //        var jsonValue;
    //        eval('jsonValue = ' + jsstr); 
    //        return jsonValue;
    //    }
    //    
    //    export function stringify(json : any) : string{
    //        var s = '';
    //
    //        if (typeof (json) == "string") {
    //            s = '"' + json.replace(new RegExp('\\\\',"g"), '\\\\\\\\').replace(new RegExp('"', "g"), '\\"') + '"';
    //        } else if (typeof (json) == "object") {
    //            if (json instanceof Array) {
    //                for (var k in json) {
    //                    s += "," + stringify(json[k]);
    //                }
    //                s = '[' + s.substring(1) + ']';
    //            } else {
    //                for (var k in json) {
    //                    s += ',"' + k + '":' + stringify(json[k]);
    //                }
    //                s = '{' + s.substring(1) + '}';
    //            }
    //        } else {
    //            s += json;
    //        }
    //        return s;
    //    }
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
        Ajax.toUrlParam = function (option) {
            var keys = [];
            for (var key in option) {
                keys.push(key + "=" + option[key]);
            }
            keys.sort();
            return keys.join("&");
        };
        Ajax.prototype.setCache = function (option, data) {
            if (this.mUseCache && undefined != option) {
                this.mCache[Ajax.toUrlParam(option)] = data;
            }
        };
        Ajax.prototype.clean = function () {
            this.mCache = {};
        };
        Ajax.prototype.getCache = function (option) {
            if (undefined == option) {
                return undefined;
            }
            return this.mCache[Ajax.toUrlParam(option)];
        };
        Ajax.prototype.validate = function (data) {
            if (data["error"] == "invalidate session") {
                window.location.href = data["redirect"];
                return false;
            }
            return true;
        };
        Ajax.prototype.post = function (option) {
            var _this = this;
            var promise = new Promise();
            $.ajax({
                type: "POST",
                url: this.mBaseUrl,
                data: option,
                success: function (data) {
                    var jsonData = JSON.parse(data);
                    if (_this.validate(jsonData)) {
                        promise.succeed(jsonData);
                    }
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
                        if (_this.validate(jsonData)) {
                            _this.setCache(option, jsonData);
                            promise.succeed(jsonData);
                        }
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
    function formatData(outputData, inputData, precentList, specialsjzhCols, formatStartColumn) {
        if (formatStartColumn === void 0) { formatStartColumn = 1; }
        var zhZb = [
            '人均发电量（万度/人）',
            '外购电单位成本（元/度）',
            '铝杆棒一次综合成品率（%）',
            '其中：5154合金杆一次成品率（%）',
            '4043&8030&6201合金杆一次成品率（%）',
            '高纯铝杆产品一次成品率（%）',
            '铝棒产品一次成品率（%）',
            '铝电解高品质槽99.90%以上等级13项元素符合率（二级以上）（%）',
            '失败成本率1（%）',
            '外部客诉率（%）',
            '4N6精铝块一次成品率（%）',
            '精铝杆一次成品率（%）',
            '综合成品率（%）',
            '基材成品率（%）',
            '粉末喷涂成品率（%）',
            '隔热产品成品率（%）',
            '失败成本率（%）',
            '自产箔综合符单率（%）',
            '委托加工化成箔符单率（%）',
            '架空电缆（1KV、10KV）合格率（%）',
            '钢芯铝绞线合格率（%）',
            '布电线合格率（%）'];
        var formaterChain = new Util.FormatPercentHandler([], precentList.toArray());
        formaterChain.next(new Util.FormatIntHandler(['人数', '制造业人数', '工程、修试业务人数', '物流贸易人数']))
            .next(new Util.FormatPercentSignalHandler(['净资产收益率(%)']))
            .next(new Util.FormatPercentHandler(['三项费用率(%)', '销售利润率(%)', '负债率', '制造业三项费用率', '工程、修试业务三项费用率', '物流贸易三项费用率']))
            .next(new Util.FormatFordotHandler(1, ['人均利润', '人均收入', '精铝块13项元素和值（ppm）']))
            .next(new Util.FormatFordotHandler(2, ['标煤单耗（g/度）', '厂用电率（%）'], specialsjzhCols))
            .next(new Util.FormatFordotHandler(2, zhZb))
            .next(new Util.FormatFordotHandler(4, ['单位供电成本（元/度）']))
            .next(new Util.FormatCurrencyHandler());
        var row = [];
        for (var j = 0; j < inputData.length; ++j) {
            row = [].concat(inputData[j]);
            for (var i = formatStartColumn; i < row.length; ++i) {
                row[i] = formaterChain.handle(row[0], i, row[i]);
            }
            outputData.push(row);
        }
        return;
    }
    Util.formatData = formatData;
    function toNumber(val) {
        var numberTpe = new Number(val).valueOf();
        return numberTpe;
    }
    Util.toNumber = toNumber;
    function formatInt(val) {
        return formatFordot(val, 0);
    }
    Util.formatInt = formatInt;
    function formatCommaCurrency(val, dotCount) {
        if (dotCount === void 0) { dotCount = 0; }
        if (val === "--" || val === "" || val === "-") {
            return val;
        }
        val = toNumber(val).toFixed(dotCount);
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
    Util.formatCommaCurrency = formatCommaCurrency;
    function formatCurrency(val) {
        return formatFordot(val, 0);
    }
    Util.formatCurrency = formatCurrency;
    function formatPercent(val) {
        if (val === "--" || val === "" || val === "-") {
            return val;
        }
        return (toNumber(val) * 100).toFixed(1) + "%";
    }
    Util.formatPercent = formatPercent;
    function formatFordot(val, dotCount) {
        if (dotCount === void 0) { dotCount = 1; }
        if (val === "--" || val === "" || val === "-") {
            return val;
        }
        return toNumber(val).toFixed(dotCount);
    }
    Util.formatFordot = formatFordot;
    function formatPercentSignal(val) {
        if (val === "--" || val === "" || val === "-") {
            return val;
        }
        return toNumber(val).toFixed(1) + "%";
    }
    Util.formatPercentSignal = formatPercentSignal;
    function isExist(val) {
        return val != undefined;
    }
    Util.isExist = isExist;
    function isMSIE() {
        return navigator.appName == "Microsoft Internet Explorer";
    }
    Util.isMSIE = isMSIE;
    function replaceNull(arr) {
        for (var i = 0; i < arr.length; ++i) {
            if (arr[i] == null) {
                arr[i] = 0;
            }
        }
        return arr;
    }
    Util.replaceNull = replaceNull;
})(Util || (Util = {}));
