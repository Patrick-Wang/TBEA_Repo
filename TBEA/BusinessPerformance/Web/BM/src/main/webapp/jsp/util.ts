/// <reference path="jqgrid/vector.ts" />
declare var $;

module Util {

    export enum ErrorCode {
        OK,
        DATABASE_EXCEPTION,
        PREMARY_KEY_CONFILICT,
        PREMARY_KEY_NULL,
        HAVE_NO_RIGHT,
        PRICELIB_JCYCLJG_IMPORT_ERROR
    }

    export interface IResponse {
        errorCode:ErrorCode;
        message:string;
    }

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
        BB		,//("本部"),
        BBXSGS		,//("本部销售公司"),
        BJFGS		,//("北京分公司"),
        BYQC		,//("沈变变压器厂"),
        DBGS		,//("电材公司"),
        DJBGS		,//("电极箔公司"),
        DJGEGS		,//("多晶硅二公司"),
        DJGGS		,//("多晶硅公司"),
        DJGYFGS		,//("多晶硅一分公司"),
        DKHEB		,//("大客户二部"),
        DKHYB		,//("大客户一部"),
        DLAZB		,//("电力安装部"),
        DLBYGS		,//("动力保障公司"),
        DLC		,//("动力厂"),
        DLCLFGS		,//("电缆材料分公司"),
        DLDLXMGS		,//("电力电缆项目公司"),
        DLGCGS		,//("电缆工程公司"),
        DLGCSYB		,//("电力工程事业部"),
        DLGS		,//("徳缆公司"),
        DLJSGCGS		,//("电力建设工程公司"),
        DLJXSYB		,//("电缆经销事业部"),
        DLKCSJGS		,//("电力勘测设计公司"),
        DLZDHGS		,//("电力自动化公司"),
        DQFGS		,//("电气分公司"),
        DQZJFGS		,//("电气组件分公司"),
        DYCJGJWLMYYXGS		,//("德阳川疆国际物流贸易有限公司"),
        FDGCSYB		,//("风电工程事业部"),
        FLSWB		,//("法律事务部"),
        FNSYB		,//("风能事业部"),
        FWGS		,//("服务公司"),
        GCFWGS		,//("工程服务公司"),
        GCGS_DL		,//("工程公司"),
        GCGS_GCL		,//("工程公司"),
        GCGS_ND		,//("工程公司"),
        GCL		,//("工程类"),
        GCLYPGS		,//("高纯铝制品公司"),
        GFGS		,//("股份公司"),
        GJB		,//("国际部"),
        GJCTGCGS		,//("国际成套工程公司"),
        GJCTZGS		,//("国际成套总公司"),
        GJGCGS_SYB		,//("国际工程公司"),
        GJGCGS_GFGS		,//("国际工程公司"),
        GJGCGS_XL 		,//("国际工程公司"),
        GJMYB		,//("国际贸易部"),
        GJMYCTGS		,//("国际贸易成套公司"),
        GJSYB		,//("国际事业部"),
        GJYWB		,//("国际业务部"),
        GMB		,//("国贸部"),
        GNCTB		,//("国内成套部"),
        GNFGS		,//("国内分公司"),
        GNGCGS		,//("国内工程公司"),
        GNGCJXGS		,//("国内工程检修公司"),
        GPSYB		,//("硅片事业部"),
        HBDQFGS		,//("衡变电气分公司"),
        HBGS		,//("衡变公司"),
        HBYYGS		,//("衡变众业公司"),
        HJBLGS		,//("合金材料公司"),
        HNGCGS		,//("湖南工程公司"),
        HNGJWLGS		,//("湖南国际物流公司"),
        HNYLGS		,//("湖南园林公司"),
        HNYNDQGS		,//("湖南智能电气公司"),
        HNZNDQGS		,//("湖南智能电气公司"),
        HXGS		,//("和新公司"),
        HXTG		,//("和新套管"),
        JCK		,//("进出口公司"),
        JCKGS_JYDW		,//("进出口公司"),
        JCKGS_SYB		,//("进出口公司"),
        JJWL		,//("津疆物流"),
        JNDXXMGS		,//("节能导线项目公司"),
        JNDXXMGS_LL		,//("节能导线项目公司"),
        JNDXXMGS_XL		,//("节能导线项目公司"),
        JSJGYTSBLGS		,//("金属结构与碳素材料公司"),
        JT		,//("集团"),
        JWYHGS		,//("经纬众和公司"),
        JYGS		,//("佳阳公司"),
        KGYJS		,//("开关研究所"),
        KJHGQ		,//("康嘉互感器"),
        KYDLXMGS		,//("矿用电缆项目公司"),
        KYGS		,//("矿业公司"),
        LBGS		,//("铝箔公司"),
        LHB		,//("绿化部"),
        LLGS		,//("鲁缆公司"),
        LYWYGS		,//("铝苑物业公司"),
        MYGS_TCNY		,//("贸易公司"),
        NDGS		,//("能动公司"),
        NJZNDQGS		,//("南京智能电气公司"),
        NLTK		,//("南露天矿"),
        NYCY		,//("能源产业"),
        NYSYB		,//("能源事业部"),
        RDGS		,//("热电公司"),
        SBDCY		,//("输变电产业"),
        SBDCYJT		,//("输变电产业集团"),
        SBDLKBSJGS		,//("沈变电力勘测设计公司"),
        SBDLYDHGS		,//("沈变电力自动化公司"),
        SBGJMYCTGS		,//("沈变国际贸易成套公司"),
        SBGS		,//("沈变公司"),
        SBKJHGQ		,//("沈变康嘉互感器"),
        SBSKGS		,//("沈变上开公司"),
        SBWYGS		,//("沈变物业公司"),
        SBXDWLGS		,//("沈变现代物流公司"),
        SBXNY		,//("沈变新能源"),
        SBXSYX		,//("沈变修试中心"),
        SBZTFGS		,//("沈变中特分公司"),
        SBZXGS		,//("沈变中型公司"),
        SDDLGCGS		,//("山东电力工程公司"),
        SDFGS		,//("山东分公司"),
        SKGS		,//("上开公司"),
        TBDG_YD_NYYXGS		,//("特变电工（印度）能源有限公司"),
        TBGS		,//("天变公司"),
        TCNY		,//("天池能源"),
        TLGS		,//("特缆公司"),
        TLXMGS		,//("特缆项目公司"),
        TYDXXMGS		,//("通用电线项目公司"),
        TYDXXMGS_LL		,//("通用电线项目公司"),
        TYDXXMGS_XL		,//("通用电线项目公司"),
        TYXMGS		,//("通用项目公司"),
        TZDGXJDGCLYXGS		,//("特变电工新疆电工材料有限公司"),
        TZDLXMGS		,//("特种电缆项目公司"),
        TZDLYFXMGS		,//("特种电缆研发项目公司"),
        WLGS		,//("物流公司"),
        WYDXDLC		,//("五元电线电缆厂"),
        WYGS		,//("物业公司"),
        XADLSJY		,//("西安电力设计院"),
        XBC		,//("新变厂"),
        XBCBB		,//("新变厂（本部）"),
        XBGJCTGCGS		,//("新变国际成套工程公司"),
        XBGNGCJXGS		,//("新变国内工程检修公司"),
        XBGS		,//("箱变公司"),
        XBXBGS		,//("新变箱变公司"),
        XBYTGS		,//("新变中特公司"),
        XDWLGS		,//("现代物流公司"),
        XJFGS		,//("新疆分公司"),
        XJNY		,//("新疆能源"),
        XJXTGJWLMYGS		,//("新疆新特国际物流贸易公司"),
        XJZXGS		,//("新疆知信公司"),
        XKGS		,//("西科公司"),
        XLC		,//("新缆厂"),
        XLGGS		,//("新利钢公司"),
        XNDQGCGS		,//("西南电气工程公司"),
        XNDQGS		,//("西南电气公司"),
        XNY		,//("新能源"),
        XNYCY		,//("新能源产业"),
        XNYGS		,//("新能源公司"),
        XNYSYB		,//("新能源事业部"),
        XNYYJY		,//("新能源研究院"),
        XSGS		,//("销售公司"),
        XSZGS		,//("销售总公司"),
        XSZX		,//("修试中心"),
        XTBLGS		,//("新特材料公司"),
        XTDLXMGS		,//("橡套电缆项目公司"),
        XTGS		,//("新特公司"),
        XTJCSYB		,//("系统集成事业部"),
        XTNYGS		,//("新特能源公司"),
        XTWLGS		,//("新特物流公司"),
        ZBDC		,//("自备电厂"),
        ZHGS		,//("众和公司"),
        ZHGS_SYB	,//("众和公司"),
        YJJSGCGS		,//("冶金建设工程公司"),
        ZPDCJ		,//("总配电车间"),
        ZTWLGS		,//("中特物流公司"),
        YXGS		,//("运销公司"),
        ZHGS_MYGS		,//("贸易公司"),
        ZJWL		,//("中疆物流"),
        ZJZTGJWLYXGS		,//("新疆中特国际物流有限公司"),
        ZTFGS		,//("中特分公司"),
        ZTGS		,//("中特公司"),
        ZXGS		,//("中型公司"),
        ZYGS		,//("众业公司"),
        //三期新增项目公司
        SBDQSBGS	,//("特变电工山东沈变电气设备有限公司"),
        CTGYGS		,//("特变电工超高压电气有限公司"),
        DXDLJCZXGS	,//("西北电线电缆检测中心有限公司"),

        //非正式公司
        DBSBDCYJT		,//("东北输变电产业集团"),
        NFSBDCYJT		,//("南方输变电产业集团"),
        GCCY			,//("工程产业"),
        XBCZT			,//("新变厂整体"),
        BYQCY			,//("变压器产业"),
        XLCY			,//("线缆产业"),
        UNKNOWN			,//("未知"),

        RSGS			,//("柔输公司");
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

    interface AjaxRedirect {
        error : string;
        redirect : string;
    }

    export class Ajax {
        private mBaseUrl: string;
        private mCache: any = {};
        private mUseCache: boolean;
        public constructor(baseUrl: string, useCache: boolean = true) {
            this.mBaseUrl = baseUrl;
            this.mUseCache = useCache;
        }

        public static toUrlParam(option: any) {
            var keys = [];
            for (var key in option) {
                keys.push(key + "=" + option[key]);
            }
            keys.sort();
            return keys.join("&");
        }

        private setCache(option: any, data: string): void {
            if (this.mUseCache && undefined != option) {
                this.mCache[Ajax.toUrlParam(option)] = data;
            }
        }

        public clean(): void {
            this.mCache = {};
        }

        private getCache(option: any): string {
            if (undefined == option) {
                return undefined;
            }
            return this.mCache[Ajax.toUrlParam(option)];
        }

        private validate(data: AjaxRedirect): boolean {
            if (data.error == "invalidate session") {
                window.location.href = data.redirect;
                return false;
            }
            return true;
        }

        public post(option: any): Promise {
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

        public get(option: any): Promise {
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

    export function formatData(outputData: string[][], inputData: string[][], precentList: std.vector<number>, specialsjzhCols: number[], formatStartColumn: number = 1) {
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

        var formaterChain: Util.FormatHandler = new Util.FormatPercentHandler([], precentList.toArray());
        formaterChain.next(new Util.FormatIntHandler(['人数','制造业人数','工程、修试业务人数','物流贸易人数']))
            .next(new Util.FormatPercentSignalHandler(['净资产收益率(%)']))
            .next(new Util.FormatPercentHandler(['三项费用率(%)', '销售利润率(%)', '负债率','制造业三项费用率','工程、修试业务三项费用率','物流贸易三项费用率']))
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

	export function toNumber(val: string): number{
        var numberTpe: any = new Number(val).valueOf();
		return numberTpe;
	}

    export function formatInt(val: string): string {
        return formatFordot(val, 0);
    }

	export function formatCommaCurrency(val: string, dotCount : number = 0){
		  if (val === "--" || val === "" || val === "-") {
            return val;
        }
		        val = toNumber(val).toFixed(dotCount);
                var dot: number = val.lastIndexOf('.');
                var intPart: string = "";
                var parts: string[] = [];
                var positive: boolean = (val.charAt(0) != '-');
                if (dot > 0) {
                    if (positive) {
                        intPart = val.substring(0, dot);
                    } else {
                        intPart = val.substring(1, dot);
                    }
                    parts.push(val.substring(dot));
                }
                else {
                    if (positive) {
                        intPart = val;
                    } else {
                        intPart = val.substring(1);
                    }
                }
        
                var leftLength: number = intPart.length;
        
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

    export function formatCurrency(val: string): string {
        return formatFordot(val, 0);      
    }


    export function formatPercent(val: string): string {
        if (val === "--" || val === "" || val === "-") {
            return val;
        }
        return (toNumber(val) * 100).toFixed(1) + "%"
    }

    export function formatFordot(val: string, dotCount: number = 1): string {
        if (val === "--" || val === "" || val === "-") {
            return val;
        }
        return toNumber(val).toFixed(dotCount);
    }

    export function formatPercentSignal(val: string): string {
        if (val === "--" || val === "" || val === "-") {
            return val;
        }
        return toNumber(val).toFixed(1) + "%"
    }

    export function isExist(val: any): boolean {
        return val != undefined;
    }

    export function isMSIE() {
        return navigator.appName == "Microsoft Internet Explorer";
    }

    export function replaceNull(arr:any[]) : any[]{
        for (let i = 0; i < arr.length; ++i){
            if (arr[i] == null){
                arr[i] = 0;
            }
        }
        return arr;
    }
}
