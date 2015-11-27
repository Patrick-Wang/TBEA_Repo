/// <reference path="../util.ts" />
declare var $;
module Util {
    export interface Bglx {
        bglxid: string;
        wvtype: string;
    }
    export class BglxSelector {
        private mCtrlId: string;
        private bglxId: string;
        private tableId: string;
        private mView: any;
        private bglxArray: Array<Bglx>;
        public constructor(mCtrlId: string,curbglx:string,view:any,isByq:boolean,isXl:boolean) {
            this.mCtrlId=mCtrlId;
            this.mView = view;
            this.bglxId = mCtrlId + "Id";
            this.tableId= mCtrlId + "Table";
            $("#" + this.mCtrlId).append('<table id="' + this.tableId + '" cellspacing="0" cellpadding="0"><tr></tr></table>');
            $("#" + this.tableId + " tr").append('<td>' +
                    '<select id="' + this.bglxId + '"' +
                    'style="width: 200px;"></select>' +
                    '</td><td><div style="width:5px;"></div></td>');
            var bglxSel = $("#" + this.bglxId );            
            bglxSel.append('<option value= "10001" '+(curbglx=='10001'?'selected="selected"':'')+'>当期订单毛利情况</option>');
            if(isByq){
                bglxSel.append('<option value= "10002" '+(curbglx=='10002'?'selected="selected"':'')+'>后期履约订单质量（变压器）</option>');
            }
            if(isXl){
                bglxSel.append('<option value= "10003" '+(curbglx=='10003'?'selected="selected"':'')+'>后期履约订单质量（线缆）</option>');
            }
            bglxSel.append('<option value= "10004" '+(curbglx=='10004'?'selected="selected"':'')+'>降控指标完成情况 </option>');
            if(isByq){
                bglxSel.append('<option value= "10005" '+(curbglx=='10005'?'selected="selected"':'')+'>技术降本（变压器）</option>');
            }
            if(isXl){
                bglxSel.append('<option value= "10006" '+(curbglx=='10006'?'selected="selected"':'')+'>技术降本（线缆）</option>');
            }
            bglxSel.append('<option value= "10007" '+(curbglx=='10007'?'selected="selected"':'')+'>采购降本</option>');
            bglxSel.append('<option value= "10008" '+(curbglx=='10008'?'selected="selected"':'')+'>生产降本</option>');
            if(isByq){
                bglxSel.append('<option value= "10009" '+(curbglx=='10009'?'selected="selected"':'')+'>整体能耗情况（变压器）</option>');
            }
            if(isXl){
                bglxSel.append('<option value= "10010" '+(curbglx=='10010'?'selected="selected"':'')+'>整体能耗情况（线缆）</option>');
            }
            bglxSel.append('<option value= "10011" '+(curbglx=='10011'?'selected="selected"':'')+'>销售降本</option>');
            if(isByq){
                bglxSel.append('<option value= "10012" '+(curbglx=='10012'?'selected="selected"':'')+'>产出完成情况（变压器）</option>');
            }
            if(isXl){
                bglxSel.append('<option value= "10013" '+(curbglx=='10013'?'selected="selected"':'')+'>产出完成情况（线缆）</option>');
                bglxSel.append('<option value= "10014" '+(curbglx=='10014'?'selected="selected"':'')+'>产出完成情况（线缆）工时</option>');
            }
            if(isByq){
                bglxSel.append('<option value= "10015" '+(curbglx=='10015'?'selected="selected"':'')+'>可供履约订单储备情况（变压器）</option>');
            }
            if(isXl){
                bglxSel.append('<option value= "10016" '+(curbglx=='10016'?'selected="selected"':'')+'>可供履约订单储备情况（线缆）</option>');
            }
            bglxSel.append('<option value= "10017" '+(curbglx=='10017'?'selected="selected"':'')+'>存货结构及内涵 </option>');
            bglxSel.append('<option value= "10018" '+(curbglx=='10018'?'selected="selected"':'')+'>账龄结构 </option>');
            bglxSel.append('<option value= "10019" '+(curbglx=='10019'?'selected="selected"':'')+'>原材料存货</option>');
            bglxSel.append('<option value= "10020" '+(curbglx=='10020'?'selected="selected"':'')+'>三项费用管控</option>');                    
            
            bglxSel.change(() => {
                var changebglx=bglxSel.children('option:selected').val();
                this.mView.updateEntry(changebglx);
            });

            bglxSel.multiselect({
                multiple: false,
                header: false,
                minWidth: 150,
                height: '100%',//(this.mEndDate.year - this.mStartDate.year + 1) * 28,
                // noneSelectedText: "请选择月份",
                selectedList: 1
            });  
            var changebglx=bglxSel.children('option:selected').val();
            this.mView.updateEntry(changebglx);          
        }
        public getBglx(): number{
           return $("#" + this.bglxId).val();
        } 
    }
    
    export class BglxViewSelector {
        private mCtrlId: string;
        private bglxId: string;
        private tableId: string;
        public constructor(mCtrlId: string,curbglx:string,isByq:boolean,isXl:boolean,isSbdcy:boolean) {
            this.mCtrlId=mCtrlId;
            this.bglxId = mCtrlId + "Id";
            this.tableId= mCtrlId + "Table";
            $("#" + this.mCtrlId).append('<table id="' + this.tableId + '" cellspacing="0" cellpadding="0"><tr></tr></table>');
            $("#" + this.tableId + " tr").append('<td>' +
                    '<select id="' + this.bglxId + '"' +
                    'style="width: 220px;"></select>' +
                    '</td><td><div style="width:5px;"></div></td>');
            var bglxSel = $("#" + this.bglxId);            
            bglxSel.append('<option value= "20001" '+(curbglx=='20001'?'selected="selected"':'')+'>当期订单毛利情况</option>');
            if(isByq||isSbdcy){
                bglxSel.append('<option value= "20002" '+(curbglx=='20002'?'selected="selected"':'')+'>后期履约订单质量（变压器）</option>');            
            }
            if(isXl||isSbdcy){
                bglxSel.append('<option value= "20003" '+(curbglx=='20003'?'selected="selected"':'')+'>后期履约订单质量（线缆）</option>');            
            }
            bglxSel.append('<option value= "20004" '+(curbglx=='20004'?'selected="selected"':'')+'>降控指标完成情况 </option>');
            if(isByq||isSbdcy){
                bglxSel.append('<option value= "20005" '+(curbglx=='20005'?'selected="selected"':'')+'>技术降本（变压器）</option>');
            }
            if(isXl||isSbdcy){
                bglxSel.append('<option value= "20006" '+(curbglx=='20006'?'selected="selected"':'')+'>技术降本（线缆）</option>');
            }
            bglxSel.append('<option value= "20007" '+(curbglx=='20007'?'selected="selected"':'')+'>采购降本</option>');
            bglxSel.append('<option value= "20008" '+(curbglx=='20008'?'selected="selected"':'')+'>生产降本</option>');
            if(isByq||isSbdcy){
                bglxSel.append('<option value= "20009" '+(curbglx=='20009'?'selected="selected"':'')+'>整体能耗情况（变压器）</option>');
            }
            if(isXl||isSbdcy){
                bglxSel.append('<option value= "20010" '+(curbglx=='20010'?'selected="selected"':'')+'>整体能耗情况（线缆）</option>');
            }
            bglxSel.append('<option value= "20011" '+(curbglx=='20011'?'selected="selected"':'')+'>销售降本</option>');
            if(isByq||isSbdcy){
                bglxSel.append('<option value= "20012" '+(curbglx=='20012'?'selected="selected"':'')+'>产出完成情况（变压器）</option>');
            }
            if(isXl||isSbdcy){
                bglxSel.append('<option value= "20013" '+(curbglx=='20013'?'selected="selected"':'')+'>产出完成情况（线缆）</option>');
                bglxSel.append('<option value= "20014" '+(curbglx=='20014'?'selected="selected"':'')+'>产出完成情况（线缆）工时</option>');
            }
            if(isByq||isSbdcy){
                bglxSel.append('<option value= "20015" '+(curbglx=='20015'?'selected="selected"':'')+'>可供履约订单储备情况（变压器）</option>');
            }
            if(isXl||isSbdcy){
                bglxSel.append('<option value= "20016" '+(curbglx=='20016'?'selected="selected"':'')+'>可供履约订单储备情况（线缆）</option>');
            }
            bglxSel.append('<option value= "20017" '+(curbglx=='20017'?'selected="selected"':'')+'>存货结构及内涵 </option>');
            bglxSel.append('<option value= "20018" '+(curbglx=='20018'?'selected="selected"':'')+'>账龄结构 </option>');
            bglxSel.append('<option value= "20019" '+(curbglx=='20019'?'selected="selected"':'')+'>原材料存货</option>');
            bglxSel.append('<option value= "20020" '+(curbglx=='20020'?'selected="selected"':'')+'>三项费用管控</option>');
            bglxSel.append('<option value= "20021" '+(curbglx=='20021'?'selected="selected"':'')+'>能耗情况</option>');
            
            bglxSel.change(() => {
                var changebglx=bglxSel.children('option:selected').val();
                this.tobglxLocation(changebglx);
            });

            bglxSel.multiselect({
                multiple: false,
                header: false,
                minWidth: 150,
                height: '100%',//(this.mEndDate.year - this.mStartDate.year + 1) * 28,
                // noneSelectedText: "请选择月份",
                selectedList: 1
            });            
        }
        
        public tobglxLocation(bglx:string):void{
            if(bglx=='20001'){
               window.location.href='../fxcpylspdqddmlqk/openview.do';
            }else if(bglx=='20002'){
               window.location.href='../fxcpylsphqlyddzl/openviewbyq.do';
            }else if(bglx=='20003'){
               window.location.href='../fxcpylsphqlyddzl/openviewxl.do';
            }else if(bglx=='20004'){
               window.location.href='../fxjkcbzbwcqk/openview.do';
            }else if(bglx=='20005'){
               window.location.href='../fxjkcbjsjb/openviewbyq.do';
            }else if(bglx=='20006'){
               window.location.href='../fxjkcbjsjb/openviewxl.do';
            }else if(bglx=='20007'){
               window.location.href='../fxjkcbcgjb/openview.do';
            }else if(bglx=='20008'){
               window.location.href='../fxjkcbscjb/openview.do';
            }else if(bglx=='20009'){
               window.location.href='../fxjkcbztnhqk/openviewbyq.do';
            }else if(bglx=='20010'){
               window.location.href='../fxjkcbztnhqk/openviewxl.do';
            }else if(bglx=='20011'){
               window.location.href='../fxjkcbxsjb/openview.do';
            }else if(bglx=='20012'){
               window.location.href='../ccccwcqk/openviewbyq.do';
            }else if(bglx=='20013'){
               window.location.href='../ccccwcqk/openviewxl.do';
            }else if(bglx=='20014'){
               window.location.href='../ccccwcqkgs/openview.do';
            }else if(bglx=='20015'){
               window.location.href='../kglyddcbqk/openviewbyq.do';
            }else if(bglx=='20016'){
               window.location.href='../kglyddcbqk/openviewxl.do';
            }else if(bglx=='20017'){
               window.location.href='../chjgjnh/openview.do';
            }else if(bglx=='20018'){
               window.location.href='../zljj/openview.do';
            }else if(bglx=='20019'){
               window.location.href='../yclch/openview.do';
            }else if(bglx=='20020'){
               window.location.href='../fxsxfykz/openview.do';
            }else if(bglx=='20021'){
               window.location.href='../fxnhqk/openview.do';
            }
        }
        public getBglx(): number{
           return $("#" + this.bglxId).val();
        }
    }
}