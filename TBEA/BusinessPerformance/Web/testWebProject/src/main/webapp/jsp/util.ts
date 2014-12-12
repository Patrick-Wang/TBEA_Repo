declare var $;
module Util {

	export enum CompanyType {
//		SB, HB, XB, TB, LL, XL, DL, 
//		XNY, GY,
//		TCNY, NDGS, ZJWL,
//		JCK,   GCGS,  
//		ZH, 
//		SBDCY, XNYCY, NYCY, GCL, JT,
//		ALL = 100
    SB      ,//("沈变公司"),
        HB      ,//("衡变公司"),
        XB      ,//("新变厂"),
        TB      ,//("天变公司"),   
        LL      ,//("鲁缆公司"),
        XL      ,//("新缆厂"),
        DL      ,//("德缆公司"),
        XNY     ,//("新特能源公司"),
        GY      ,//("新能源"),
        TCNY    ,//("天池能源"),
        NDGS    ,//("能动公司"),
        ZJWL    ,//("中疆物流"),
        JCK     ,//("进出口公司"),
        GCGS    ,//("国际工程公司"),
        ZH      ,//("众和公司"),
        SBDCY   ,//("输变电产业"),
        XNYCY   ,//("新能源产业"),
        NYCY    ,//("能源产业"),
        GCL     ,//("工程类"),
        JT      ,//("集团"),
        BYQC    ,//("变压器厂"),
        GJMYCTGS    ,//("国际贸易成套公司"),
        ZTFGS   ,//("中特分公司"),
        KJHGQ   ,//("康嘉互感器"),
        DQZJFGS ,//("电气组件分公司"),
        DLZDHGS ,//("电力自动化公司"),
        SKGS    ,//("上开公司"),
        XSZX    ,//("修试中心"),
        XDWLGS  ,//("现代物流公司"),
        DLKCSJGS    ,//("电力勘测设计公司"),
        XLGGS   ,//("新利钢公司"),
        GNCTB   ,//("国内成套部"),
        DLAZB   ,//("电力安装部"),
        ZXGS    ,//("中型公司"),
        HXGS    ,//("和新公司"),
        TBDG_YD_NYYXGS  ,//("特变电工（印度）能源有限公司"),
        SBWYGS  ,//("沈变物业公司"),
        DQFGS   ,//("电气分公司"),      
        HNGJWLGS    ,//("湖南国际物流公司"),       
        HNGCGS  ,//("湖南工程公司"),     
        ZYGS    ,//("众业公司"),       
        HNZNDQGS    ,//("湖南智能电气公司"),       
        NJZNDQGS    ,//("南京智能电气公司"),       
        HNYLGS  ,//("湖南园林公司"),
        TBGS    ,//("天变公司"),       
        ZTGS    ,//("中特公司"),       
        XBGS    ,//("箱变公司"),       
        GJCTGCGS    ,//("国际成套工程公司"),       
        GNGCJXGS    ,//("国内工程检修公司"),       
        XJXTGJWLMYGS    ,//("新疆新特国际物流贸易公司"),
        ALL = 1000
	}

	export class DateDataSet{
		private mBaseResUrl : string;
		private mDataMap : any = {};
		public constructor(baseResUrl : string){
			this.mBaseResUrl = baseResUrl;
		}
		
        public getData(m: number, y: number, callBack: (arrayData : Array<string[]>) => void): void{
			if (undefined == this.mDataMap[y + ""]) {
                this.mDataMap[y + ""] = {};
            }
            if (undefined == this.mDataMap[y + ""][m + ""]){
                $.ajax({
                    type: "GET",
                    url: this.mBaseResUrl + "?month=" + m + "&year=" + y + "",
	                success: (data: any) =>{
		                    var jsnData = JSON.parse(data);
							this.mDataMap[y + ""][m + ""] = jsnData;
		                    callBack(jsnData);
			        }, 
			        error: (XMLHttpRequest, textStatus, errorThrown) => {
	                    callBack(null);
	                }
         		});
			}
			else {
        		callBack(this.mDataMap[y + ""][m + ""]);
    		}
		}
		
		public getDataByDay(m: number, y: number, d: number, callBack: (arrayData : Array<string[]>) => void): void{
            if (undefined == this.mDataMap[y + ""]) {
                this.mDataMap[y + ""] = {};
            }
            if (undefined == this.mDataMap[y + ""][m + ""]){
            	this.mDataMap[y + ""][m + ""] = {}
            }
            if (undefined == this.mDataMap[y + ""][m + ""][d + ""]){
                $.ajax({
                    type: "GET",
                    url: this.mBaseResUrl + "?month=" + m + "&year=" + y + "&day=" + d,
	                success: (data: any) =>{
		                    var jsnData = JSON.parse(data);
		                    this.mDataMap[y + ""][m + ""][d + ""] = jsnData;
		                    callBack(jsnData);
			        },
			        error: (XMLHttpRequest, textStatus, errorThrown) => {
	                    callBack(null);
	                }
         		});
			}
			else {
        		callBack(this.mDataMap[y + ""][m + ""][d + ""]);
    		}
		}
		
		
		public getDataByYear(y: number, compId: CompanyType, callBack: (arrayData : string) => void): void{
            if (undefined == this.mDataMap[y + ""]) {
                this.mDataMap[y + ""] = {};
            }
           
            if (undefined == this.mDataMap[y + ""][compId + ""]){
                $.ajax({
                    type: "GET",
                    url: this.mBaseResUrl + "?year=" + y + "&companyId=" + compId,
	                success: (data: any) =>{
		                  this.mDataMap[y + ""][compId + ""] = data;
		                  callBack(data);
			        },
			        error: (XMLHttpRequest, textStatus, errorThrown) => {
	                    callBack(null);
	                }
         		});
			}
			else {
        		callBack(this.mDataMap[y + ""][compId + ""]);
    		}
		}
		
	   public getDataByYearOnly(y: number, callBack: (arrayData : Array<string[]>) => void): void{
            if (undefined == this.mDataMap[y + ""]){
                $.ajax({
                    type: "GET",
                    url: this.mBaseResUrl + "?year=" + y,
	                success: (data: any) =>{
		                  this.mDataMap[y + ""] = JSON.parse(data);
		                  callBack(this.mDataMap[y + ""]);
			        },
			        error: (XMLHttpRequest, textStatus, errorThrown) => {
	                    callBack(null);
	                }
         		});
			}
			else {
        		callBack(this.mDataMap[y + ""]);
    		}
		}
		
		public getDataByCompany(m: number, y: number, compId: CompanyType, callBack: (arrayData : string) => void): void{
            if (undefined == this.mDataMap[y + ""]) {
                this.mDataMap[y + ""] = {};
            }
            if (undefined == this.mDataMap[y + ""][m + ""]){
            	this.mDataMap[y + ""][m + ""] = {}
            }
            if (undefined == this.mDataMap[y + ""][m + ""][compId + ""]){
                $.ajax({
                    type: "GET",
                    url: this.mBaseResUrl + "?month=" + m + "&year=" + y + "&companyId=" + compId,
	                success: (data: any) =>{
		                  this.mDataMap[y + ""][m + ""][compId + ""] = data;
		                  callBack(data);
			        },
			        error: (XMLHttpRequest, textStatus, errorThrown) => {
	                    callBack(null);
	                }
         		});
			}
			else {
        		callBack(this.mDataMap[y + ""][m + ""][compId + ""]);
    		}
		}
	}

 
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
}
