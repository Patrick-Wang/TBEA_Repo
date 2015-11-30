/// <reference path="../util.ts" />
declare var $;
module Util {
    export interface Dwxx {
        id: string;
        name: string;
    }    
    export class CompanySelectorZzy {
        private mCtrlId: string;
        private companyId: string;
        private tableId: string;
        public constructor(mCtrlId: string,dwxxs:Array<Dwxx>,isSbdcy:boolean,viewType:string ) {//viewType显示类型01全部，02变压器，03线缆
            this.mCtrlId=mCtrlId;
            this.companyId = mCtrlId + "Id";
            this.tableId= mCtrlId + "Table";
            $("#" + this.mCtrlId).append('<table id="' + this.tableId + '" cellspacing="0" cellpadding="0"><tr></tr></table>');
            $("#" + this.tableId + " tr").append('<td>' +
                    '<select id="' + this.companyId +
                    '" style="width: 100px;"></select>' +
                    '</td><td><div style="width:5px;"></div></td>');
            var Sel = $("#" + this.companyId);  
            if(dwxxs.length>=1){
                for(var i=0;i<dwxxs.length;i++){
                    if(viewType=='01'||viewType=='02'){
                        if(dwxxs[i].id=='1'||dwxxs[i].id=='2'||dwxxs[i].id=='3'){
                            Sel.append('<option value= "'+dwxxs[i].id+'">'+dwxxs[i].name+'</option>');
                        }
                    }
                    if(viewType=='01'||viewType=='03'){
                        if(dwxxs[i].id=='4'||dwxxs[i].id=='5'||dwxxs[i].id=='6'){
                            Sel.append('<option value= "'+dwxxs[i].id+'">'+dwxxs[i].name+'</option>');
                        }
                    }
                }
                if(isSbdcy){
                    if(viewType=='01'||viewType=='02'){
                        Sel.append('<option value= 900000>变压器产业</option>');         
                    }else if(viewType=='01'||viewType=='03'){
                        Sel.append('<option value= 800000>线缆产业</option>');
                    }                    
                }
            }
            Sel.multiselect({
                multiple: false,
                header: false,
                minWidth: 150,
                height: '100%',//(this.mEndDate.year - this.mStartDate.year + 1) * 28,
                // noneSelectedText: "请选择月份",
                selectedList: 1
            }); 
        }
        public getCompany(): number{
           return $("#" + this.companyId).val();
        }      
        public getCompanyName(): String{
           return $("#" + this.companyId).find("option:selected").text();            
        }
        public hide(){
           $("#" + this.mCtrlId).hide();
        }
    }
}