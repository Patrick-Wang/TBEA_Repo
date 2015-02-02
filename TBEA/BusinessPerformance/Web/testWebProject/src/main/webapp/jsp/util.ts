declare var $;
module Util {
    
    export enum ZBType {
        QNJH,
        YDJDMJH,
        BY20YJ,
        BY28YJ,
        BYSJ
    }
    
    export interface Permission{
        entryPlan : boolean;    
    }
    
	export enum CompanyType {
        SB       ,// ("沈变公司"),
        HB      ,// ("衡变公司"),
        XB      ,// ("新变厂"),
       // TB      ,// ("天变公司"),   
        LL      ,// ("鲁缆公司"),
        XL      ,// ("新缆厂"),
        DL      ,// ("德缆公司"),
        XNY     ,// ("新特能源公司"),
        GY      ,// ("新能源"),
        TCNY    ,// ("天池能源"),
        NDGS    ,// ("能动公司"),
        ZJWL    ,// ("中疆物流"),
        JCK     ,// ("进出口公司"),
        ZH      ,// ("众和公司"),
        SBDCY   ,// ("输变电产业"),
        XNYCY   ,// ("新能源产业"),
        NYCY    ,// ("能源产业"),
        GCL     ,// ("工程类"),
        JT      ,// ("集团"),
        BYQC    ,// ("沈变变压器厂"),
        GJMYCTGS    ,// ("国际贸易成套公司"),
        ZTFGS   ,// ("中特分公司"),
        KJHGQ   ,// ("康嘉互感器"),
        DQZJFGS ,// ("电气组件分公司"),
        DLZDHGS ,// ("电力自动化公司"),
        SKGS    ,// ("上开公司"),
        XSZX    ,// ("修试中心"),
        XDWLGS  ,// ("现代物流公司"),
        DLKCSJGS    ,// ("电力勘测设计公司"),
        XLGGS   ,// ("新利钢公司"),
        GNCTB   ,// ("国内成套部"),
        DLAZB   ,// ("电力安装部"),
        ZXGS    ,// ("中型公司"),
        HXGS    ,// ("和新公司"),
        TBDG_YD_NYYXGS  ,// ("特变电工（印度）能源有限公司"),
        SBWYGS  ,// ("沈变物业公司"),
        DQFGS   ,// ("电气分公司"),      
        HNGJWLGS    ,// ("湖南国际物流公司"),       
        HNGCGS  ,// ("湖南工程公司"),     
        ZYGS    ,// ("众业公司"),       
        HNZNDQGS    ,// ("湖南智能电气公司"),       
        NJZNDQGS    ,// ("南京智能电气公司"),       
        HNYLGS  ,// ("湖南园林公司"),
        TBGS    ,// ("天变公司"),       
        ZTGS    ,// ("中特公司"),       
        XBGS    ,// ("箱变公司"),       
        GJCTGCGS    ,// ("国际成套工程公司"),       
        GNGCJXGS    ,// ("国内工程检修公司"),       
        XJXTGJWLMYGS    ,// ("新疆新特国际物流贸易公司"),
        XSZGS   ,// ("销售总公司"),
        SDFGS   ,// ("山东分公司"),
        GJCTZGS ,// ("国际成套总公司"),
        LL_TYDXXMGS ,// ("通用电线项目公司"),
        LL_JNDXXMGS ,// ("节能导线项目公司"),
        DLCLFGS ,// ("电缆材料分公司"),
        TZDLYFXMGS  ,// ("特种电缆研发项目公司"),
        DLJXSYB ,// ("电缆经销事业部"),
        XNYSYB  ,// ("新能源事业部"),
        DLGCGS  ,// ("电缆工程公司"),
        SDDLGCGS    ,// ("山东电力工程公司"),
        KYDLXMGS    ,// ("矿用电缆项目公司"),
        XL_TYDXXMGS ,// ("通用电线项目公司"),
        XL_JNDXXMGS ,// ("节能导线项目公司"),
        TZDLXMGS    ,// ("特种电缆项目公司"),
        DLDLXMGS    ,// ("电力电缆项目公司"),
        TZDGXJDGCLYXGS  ,// ("特变电工新疆电工材料有限公司"),
        DKHYB   ,// ("大客户一部"),
        DKHEB   ,// ("大客户二部"),
        GJMYB   ,// ("国际贸易部"),
        GNGCGS  ,// ("国内工程公司"),
        GJGCGS  ,// ("国际工程公司"),
        ZJZTGJWLYXGS    ,// ("新疆中特国际物流有限公司"),
        TLGS    ,// ("特缆公司"),
        XTGS    ,// ("新特公司"),
        GCGS    ,// ("工程公司"),
        BB  ,// ("本部"),
        XSGS    ,// ("销售公司"),
        GJYWB   ,// ("国际业务部"),
        FLSWB   ,// ("法律事务部"),
        DYCJGJWLMYYXGS  ,// ("德阳川疆国际物流贸易有限公司"),
        XNDQGS  ,// ("西南电气公司");
        ALL = 1000
	}

    $.ajaxSetup({cache:false});
    
    export class Promise{
        
        private mSuccessList : Array<(data : any) => void> = [];
        private mFailedList : Array<(err : string) => void> = [];
        
        public succeed(data : any){
            for (var i = 0; i < this.mSuccessList.length; ++i){
                this.mSuccessList[i](data);
            }
        }
        
         public failed(data : string){
            for (var i = 0; i < this.mFailedList.length; ++i){
                this.mFailedList[i](data);
            }
        }
        
        public then(
            success : (data : any) => void, 
            failed ? :(err : string) => void) : Promise{
            if (null != success && undefined != success){
                this.mSuccessList.push(success);
            }    

            if (failed != null && failed != undefined){
                this.mFailedList.push(failed);
            }
            
            return this;
        }    
    }
    
    export interface IAjaxOption{
        year ? : number;
        month ? : number;
        day ? : number;
        companyId ? : number;
        entryType ?: ZBType;
    }
    
    export class Ajax {
        private mBaseUrl: string;
        private mCache : any = {};
        private mUseCache: boolean;
        public constructor(baseUrl: string, useCache : boolean = true) {
            this.mBaseUrl = baseUrl;
            this.mUseCache = useCache;
        }

        private generateKey(option: IAjaxOption){
            var keys = [];
            for(var key in option){
                keys.push(key + option[key]);
            }
            keys.sort();
            return keys.join("&");
        }
        
        private setCache(option: IAjaxOption, data: string) : void{
            if (this.mUseCache){
                this.mCache[this.generateKey(option)] = data;
            }
        }
        
        public clean() : void{
            this.mCache = {};    
        }
        
        private getCache(option: IAjaxOption) : string{
            return this.mCache[this.generateKey(option)];
        }
        
        public post(option: IAjaxOption): Promise {
            var promise: Promise = new Promise();
            $.ajax({
                type: "POST",
                url: this.mBaseUrl,
                data: option,
                success: (data: any) => {
                    var jsonData = JSON.parse(data);
                    promise.succeed(jsonData);
                },
                error: (XMLHttpRequest, textStatus, errorThrown) => {
                    promise.failed(textStatus);
                }
            });
            return promise;
        }
        
        public get(option: IAjaxOption): Promise {
            var promise: Promise = new Promise();
            var cacheData : string = this.getCache(option);
            if (undefined == cacheData){
                $.ajax({
                    type: "GET",
                    url: this.mBaseUrl,
                    data: option,
                    success: (data: any) => {
                        var jsonData = JSON.parse(data);
                        this.setCache(option, jsonData);
                        promise.succeed(jsonData);
                    },
                    error: (XMLHttpRequest, textStatus, errorThrown) => {
                        promise.failed(textStatus);
                    }
                });
            } else{
                setTimeout(()=>{
                    promise.succeed(cacheData);    
                }, 0);
            }
            return promise;
        }
    }
    
//	export class DateDataSet{
//		private mBaseResUrl : string;
//		private mDataMap : any = {};
//		public constructor(baseResUrl : string){
//			this.mBaseResUrl = baseResUrl;
//		}
//		
//        public getData(m: number, y: number, callBack: (arrayData : Array<string[]>) => void): void{
//			if (undefined == this.mDataMap[y + ""]) {
//                this.mDataMap[y + ""] = {};
//            }
//            if (undefined == this.mDataMap[y + ""][m + ""]){
//                $.ajax({
//                    type: "GET",
//                    url: this.mBaseResUrl + "?month=" + m + "&year=" + y + "",
//	                success: (data: any) =>{
//		                    var jsnData = JSON.parse(data);
//							this.mDataMap[y + ""][m + ""] = jsnData;
//		                    callBack(jsnData);
//			        }, 
//			        error: (XMLHttpRequest, textStatus, errorThrown) => {
//	                    callBack(null);
//	                }
//         		});
//			}
//			else {
//        		callBack(this.mDataMap[y + ""][m + ""]);
//    		}
//		}
//		
//		public getDataByDay(m: number, y: number, d: number, callBack: (arrayData : Array<string[]>) => void): void{
//            if (undefined == this.mDataMap[y + ""]) {
//                this.mDataMap[y + ""] = {};
//            }
//            if (undefined == this.mDataMap[y + ""][m + ""]){
//            	this.mDataMap[y + ""][m + ""] = {}
//            }
//            if (undefined == this.mDataMap[y + ""][m + ""][d + ""]){
//                $.ajax({
//                    type: "GET",
//                    url: this.mBaseResUrl + "?month=" + m + "&year=" + y + "&day=" + d,
//	                success: (data: any) =>{
//		                    var jsnData = JSON.parse(data);
//		                    this.mDataMap[y + ""][m + ""][d + ""] = jsnData;
//		                    callBack(jsnData);
//			        },
//			        error: (XMLHttpRequest, textStatus, errorThrown) => {
//	                    callBack(null);
//	                }
//         		});
//			}
//			else {
//        		callBack(this.mDataMap[y + ""][m + ""][d + ""]);
//    		}
//		}
//		
//		
//		public getDataByYear(y: number, compId: CompanyType, callBack: (arrayData : string) => void): void{
//            if (undefined == this.mDataMap[y + ""]) {
//                this.mDataMap[y + ""] = {};
//            }
//           
//            if (undefined == this.mDataMap[y + ""][compId + ""]){
//                $.ajax({
//                    type: "GET",
//                    url: this.mBaseResUrl + "?year=" + y + "&companyId=" + compId,
//	                success: (data: any) =>{
//		                  this.mDataMap[y + ""][compId + ""] = data;
//		                  callBack(data);
//			        },
//			        error: (XMLHttpRequest, textStatus, errorThrown) => {
//	                    callBack(null);
//	                }
//         		});
//			}
//			else {
//        		callBack(this.mDataMap[y + ""][compId + ""]);
//    		}
//		}
//		
//	   public getDataByYearOnly(y: number, callBack: (arrayData : Array<string[]>) => void): void{
//            if (undefined == this.mDataMap[y + ""]){
//                $.ajax({
//                    type: "GET",
//                    url: this.mBaseResUrl + "?year=" + y,
//	                success: (data: any) =>{
//		                  this.mDataMap[y + ""] = JSON.parse(data);
//		                  callBack(this.mDataMap[y + ""]);
//			        },
//			        error: (XMLHttpRequest, textStatus, errorThrown) => {
//	                    callBack(null);
//	                }
//         		});
//			}
//			else {
//        		callBack(this.mDataMap[y + ""]);
//    		}
//		}
//		
//		public getDataByCompany(m: number, y: number, compId: CompanyType, callBack: (arrayData : string) => void): void{
//            if (undefined == this.mDataMap[y + ""]) {
//                this.mDataMap[y + ""] = {};
//            }
//            if (undefined == this.mDataMap[y + ""][m + ""]){
//            	this.mDataMap[y + ""][m + ""] = {}
//            }
//            if (undefined == this.mDataMap[y + ""][m + ""][compId + ""]){
//                $.ajax({
//                    type: "GET",
//                    url: this.mBaseResUrl + "?month=" + m + "&year=" + y + "&companyId=" + compId,
//	                success: (data: any) =>{
//		                  this.mDataMap[y + ""][m + ""][compId + ""] = data;
//		                  callBack(data);
//			        },
//			        error: (XMLHttpRequest, textStatus, errorThrown) => {
//	                    callBack(null);
//	                }
//         		});
//			}
//			else {
//        		callBack(this.mDataMap[y + ""][m + ""][compId + ""]);
//    		}
//		}
//	}

 
	export function formatCurrency (val: string): string{
		
		if (val === "--" || val === ""){
			return val;
		}
		val = parseFloat(val).toFixed(2) + "";
		var dot : number = val.lastIndexOf('.');
		var intPart : string = "";
		var parts : string[] = [];
		var positive : boolean = (val.charAt(0) != '-');
		if (dot > 0){
			if (positive){
				intPart = val.substring(0, dot);
			} else{
				intPart = val.substring(1, dot);
			}
			parts.push(val.substring(dot));
		}
		else {
			if (positive){
				intPart = val;
			} else{
				intPart = val.substring(1);
			}
		}
		
		var leftLength : number = intPart.length;
		
		while (leftLength > 3){
			parts.push("," + intPart.substring(leftLength - 3, leftLength));
			leftLength -= 3;
		}
	
		parts.push(intPart.substring(0, leftLength));
		
		if (!positive){
			parts.push("-");
		}
		
		parts = parts.reverse();
		
		return parts.join("");
	}
    
    export function isExist(val: any): boolean{
        return val != undefined;    
    }
}
