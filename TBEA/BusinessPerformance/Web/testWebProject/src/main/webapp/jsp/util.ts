declare var $;
module Util {

	export enum CompanyType {
		SB, HB, XB, TB, LL, XL, DL, 
		XNY, GY,
		TCNY, NDGS, ZJWL,
		JCK,   GCGS,  
		ZH, 
		SBDCY, XNYCY, NYCY, GCL, JT,
		ALL = 100
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
