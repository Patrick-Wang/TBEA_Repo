declare var $;

module Util {

    function indexOf(arr, val) {
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }


    export interface FormatHandler {
        handle(zb: string, col: number, val: string): string;
        next(handler: FormatHandler);
    }


    class AbstractFormatHandler implements FormatHandler {
        private mNextHandler: AbstractFormatHandler;
        private mZbs: string[];
        private mCols: number[];

        handle(zb: string, col: number, val: string): string {
            return "";
        }

        match(zb: string, col: number): boolean {
            if (this.mZbs.length == 0 || indexOf(this.mZbs, zb) >= 0) {
                if (this.mCols.length == 0 || indexOf(this.mCols, col) >= 0) {
                    return true;
                }
            }
            return false;
        }

        constructor(zbs: string[], cols: number[]) {
            this.mZbs = zbs;
            this.mCols = cols;
        }

        callNext(zb: string, col: number, val: string): string {
            if (this.mNextHandler != undefined) {
                return this.mNextHandler.handle(zb, col, val);
            }
            return "--";
        }

        next(handler: AbstractFormatHandler): AbstractFormatHandler {
            this.mNextHandler = handler;
            return handler;
        }
    }


    export class FormatIntHandler extends AbstractFormatHandler {
        handle(zb: string, col: number, val: string): string {
            if (this.match(zb, col)) {
                return Util.formatInt(val);
            } else {
                return this.callNext(zb, col, val);
            }
        }

        constructor(zbs: string[] = [], cols: number[] = []) {
            super(zbs, cols);
        }
    }

    export class FormatCurrencyHandler extends AbstractFormatHandler {
        handle(zb: string, col: number, val: string): string {
            if (this.match(zb, col)) {
                return Util.formatCurrency(val);
            } else {
                return this.callNext(zb, col, val);
            }
        }

        constructor(zbs: string[] = [], cols: number[] = []) {
            super(zbs, cols);
        }
    }

    export class FormatPercentHandler extends AbstractFormatHandler {
        handle(zb: string, col: number, val: string): string {
            if (this.match(zb, col)) {
                return Util.formatPercent(val);
            } else {
                return this.callNext(zb, col, val);
            }
        }

        constructor(zbs: string[] = [], cols: number[] = []) {
            super(zbs, cols);
        }
    }

    export class FormatPercentSignalHandler extends AbstractFormatHandler {
        handle(zb: string, col: number, val: string): string {
            if (this.match(zb, col)) {
                return Util.formatPercentSignal(val);
            } else {
                return this.callNext(zb, col, val);
            }
        }

        constructor(zbs: string[] = [], cols: number[] = []) {
            super(zbs, cols);
        }
    }

    export class FormatFordotHandler extends AbstractFormatHandler {

        private mDotCount: number;
        handle(zb: string, col: number, val: string): string {
            if (this.match(zb, col)) {
                return Util.formatFordot(val, this.mDotCount);
            } else {
                return this.callNext(zb, col, val);
            }
        }

        constructor(dotCount: number = 1, zbs: string[] = [], cols: number[] = []) {
            super(zbs, cols);
            this.mDotCount = dotCount;
        }
    }

    export class ZBStatus {
        static NONE: string = "NONE";
        static APPROVED: string = "APPROVED";
        static SUBMITTED: string = "SUBMITTED";
        static SAVED: string = "SAVED";
        static APPROVED_2: string = "APPROVED_2";
        static SUBMITTED_2: string = "SUBMITTED_2"

    }

    export enum ZBType {
        QNJH,
        YDJDMJH,
        BY20YJ,
        BY28YJ,
        BYSJ
    }

    export interface Permission {
        entryPlan: boolean;
    }

    export enum CompanyType {
        SB,// ("沈变公司"),
        HB,// ("衡变公司"),
        XB,// ("新变厂"),
        // TB      ,// ("天变公司"),   
        LL,// ("鲁缆公司"),
        XL,// ("新缆厂"),
        DL,// ("德缆公司"),
        XNY,// ("新特能源公司"),
        GY,// ("新能源"),
        TCNY,// ("天池能源"),
        NDGS,// ("能动公司"),
        ZJWL,// ("中疆物流"),
        JCK,// ("进出口公司"),
        ZH,// ("众和公司"),
        SBDCY,// ("输变电产业"),
        XNYCY,// ("新能源产业"),
        NYCY,// ("能源产业"),
        GCL,// ("工程类"),
        JT,// ("集团"),
        BYQC,// ("沈变变压器厂"),
        GJMYCTGS,// ("国际贸易成套公司"),
        ZTFGS,// ("中特分公司"),
        KJHGQ,// ("康嘉互感器"),
        DQZJFGS,// ("电气组件分公司"),
        DLZDHGS,// ("电力自动化公司"),
        SKGS,// ("上开公司"),
        XSZX,// ("修试中心"),
        XDWLGS,// ("现代物流公司"),
        DLKCSJGS,// ("电力勘测设计公司"),
        XLGGS,// ("新利钢公司"),
        GNCTB,// ("国内成套部"),
        DLAZB,// ("电力安装部"),
        ZXGS,// ("中型公司"),
        HXGS,// ("和新公司"),
        TBDG_YD_NYYXGS,// ("特变电工（印度）能源有限公司"),
        SBWYGS,// ("沈变物业公司"),
        DQFGS,// ("电气分公司"),      
        HNGJWLGS,// ("湖南国际物流公司"),       
        HNGCGS,// ("湖南工程公司"),     
        ZYGS,// ("众业公司"),       
        HNZNDQGS,// ("湖南智能电气公司"),       
        NJZNDQGS,// ("南京智能电气公司"),       
        HNYLGS,// ("湖南园林公司"),
        TBGS,// ("天变公司"),       
        ZTGS,// ("中特公司"),       
        XBGS,// ("箱变公司"),       
        GJCTGCGS,// ("国际成套工程公司"),       
        GNGCJXGS,// ("国内工程检修公司"),       
        XJXTGJWLMYGS,// ("新疆新特国际物流贸易公司"),
        XSZGS,// ("销售总公司"),
        SDFGS,// ("山东分公司"),
        GJCTZGS,// ("国际成套总公司"),
        LL_TYDXXMGS,// ("通用电线项目公司"),
        LL_JNDXXMGS,// ("节能导线项目公司"),
        DLCLFGS,// ("电缆材料分公司"),
        TZDLYFXMGS,// ("特种电缆研发项目公司"),
        DLJXSYB,// ("电缆经销事业部"),
        XNYSYB,// ("新能源事业部"),
        DLGCGS,// ("电缆工程公司"),
        SDDLGCGS,// ("山东电力工程公司"),
        KYDLXMGS,// ("矿用电缆项目公司"),
        XL_TYDXXMGS,// ("通用电线项目公司"),
        XL_JNDXXMGS,// ("节能导线项目公司"),
        TZDLXMGS,// ("特种电缆项目公司"),
        DLDLXMGS,// ("电力电缆项目公司"),
        TZDGXJDGCLYXGS,// ("特变电工新疆电工材料有限公司"),
        DKHYB,// ("大客户一部"),
        DKHEB,// ("大客户二部"),
        GJMYB,// ("国际贸易部"),
        GNGCGS,// ("国内工程公司"),
        GJGCGS,// ("国际工程公司"),
        ZJZTGJWLYXGS,// ("新疆中特国际物流有限公司"),
        TLGS,// ("特缆公司"),
        XTGS,// ("新特公司"),
        GCGS,// ("工程公司"),
        BB,// ("本部"),
        XSGS,// ("销售公司"),
        GJYWB,// ("国际业务部"),
        FLSWB,// ("法律事务部"),
        DYCJGJWLMYYXGS,// ("德阳川疆国际物流贸易有限公司"),
        XNDQGS,// ("西南电气公司");
        ALL = 1000
    }

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

    export class Promise {

        private mSuccessList: Array<(data: any) => void> = [];
        private mFailedList: Array<(err: string) => void> = [];

        public succeed(data?: any) {
            for (var i = 0; i < this.mSuccessList.length; ++i) {
                this.mSuccessList[i](data);
            }
        }

        public failed(data?: string) {
            for (var i = 0; i < this.mFailedList.length; ++i) {
                this.mFailedList[i](data);
            }
        }

        public then(
            success: (data: any) => void,
            failed?: (err: string) => void): Promise {
            if (null != success && undefined != success) {
                this.mSuccessList.push(success);
            }

            if (failed != null && failed != undefined) {
                this.mFailedList.push(failed);
            }

            return this;
        }
    }

    export interface IAjaxOption {
        year?: number;
        month?: number;
        day?: number;
        companyId?: number;
        entryType?: ZBType;
    }

    export class Ajax {
        private mBaseUrl: string;
        private mCache: any = {};
        private mUseCache: boolean;
        public constructor(baseUrl: string, useCache: boolean = true) {
            this.mBaseUrl = baseUrl;
            this.mUseCache = useCache;
        }

        public static toUrlParam(option: IAjaxOption) {
            var keys = [];
            for (var key in option) {
                keys.push(key + "=" + option[key]);
            }
            keys.sort();
            return keys.join("&");
        }

        private setCache(option: IAjaxOption, data: string): void {
            if (this.mUseCache && undefined != option) {
                this.mCache[Ajax.toUrlParam(option)] = data;
            }
        }

        public clean(): void {
            this.mCache = {};
        }

        private getCache(option: IAjaxOption): string {
            if (undefined == option) {
                return undefined;
            }
            return this.mCache[Ajax.toUrlParam(option)];
        }

        private validate(data: string): boolean {
            if (data["error"] == "invalidate session") {
                window.location.href = data["redirect"];
                return false;
            }
            return true;
        }

        public post(option: IAjaxOption): Promise {
            var promise: Promise = new Promise();

            $.ajax({
                type: "POST",
                url: this.mBaseUrl,
                data: option,
                success: (data: any) => {
                    var jsonData = JSON.parse(data);
                    if (this.validate(jsonData)) {
                        promise.succeed(jsonData);
                    }
                },
                error: (XMLHttpRequest, textStatus, errorThrown) => {
                    promise.failed(textStatus);
                }
            });
            return promise;
        }

        public get(option: IAjaxOption): Promise {
            var promise: Promise = new Promise();
            var cacheData: string = this.getCache(option);
            if (undefined == cacheData) {

                $.ajax({
                    type: "GET",
                    url: this.mBaseUrl,
                    data: option,
                    success: (data: any) => {
                        var jsonData = JSON.parse(data);
                        if (this.validate(jsonData)) {

                            this.setCache(option, jsonData);
                            promise.succeed(jsonData);
                        }
                    },
                    error: (XMLHttpRequest, textStatus, errorThrown) => {
                        promise.failed(textStatus);
                    }
                });
            } else {
                setTimeout(() => {
                    promise.succeed(cacheData);
                }, 0);
            }
            return promise;
        }
    }



    export function formatInt(val: string): string {
        if (val === "--" || val === "") {
            return val;
        }
        return parseInt(val) + "";
    }


    export function formatCurrency(val: string): string {

        if (val === "--" || val === "") {
            return val;
        }
        return parseFloat(val).toFixed(0) + "";
        //        val = parseFloat(val).toFixed(0) + "";
        //        var dot: number = val.lastIndexOf('.');
        //        var intPart: string = "";
        //        var parts: string[] = [];
        //        var positive: boolean = (val.charAt(0) != '-');
        //        if (dot > 0) {
        //            if (positive) {
        //                intPart = val.substring(0, dot);
        //            } else {
        //                intPart = val.substring(1, dot);
        //            }
        //            parts.push(val.substring(dot));
        //        }
        //        else {
        //            if (positive) {
        //                intPart = val;
        //            } else {
        //                intPart = val.substring(1);
        //            }
        //        }
        //
        //        var leftLength: number = intPart.length;
        //
        //        while (leftLength > 3) {
        //            parts.push("," + intPart.substring(leftLength - 3, leftLength));
        //            leftLength -= 3;
        //        }
        //
        //        parts.push(intPart.substring(0, leftLength));
        //
        //        if (!positive) {
        //            parts.push("-");
        //        }
        //
        //        parts = parts.reverse();
        //
        //        return parts.join("");
    }


    export function formatPercent(val: string): string {
        if (val === "--" || val === "" || val === "-") {
            return val;
        }
        return (parseFloat(val) * 100).toFixed(1) + "%"
    }

    export function formatFordot(val: string, dotCount: number = 1): string {
        if (val === "--" || val === "" || val === "-") {
            return val;
        }
        return (parseFloat(val)).toFixed(dotCount);
    }

    export function formatPercentSignal(val: string): string {
        if (val === "--" || val === "" || val === "-") {
            return val;
        }
        return (parseFloat(val)).toFixed(1) + "%"
    }

    export function isExist(val: any): boolean {
        return val != undefined;
    }

    export function isMSIE() {
        return navigator.appName == "Microsoft Internet Explorer";
    }
}
