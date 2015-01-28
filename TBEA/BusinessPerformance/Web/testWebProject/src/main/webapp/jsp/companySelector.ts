/// <reference path="util.ts" />
declare var $;
declare var String;
module Util {
    String.prototype.getWidth = function(fontSize)
    {
        var span = document.getElementById("__getwidth");
        if (span == null) {
            span = document.createElement("span");
            span.id = "__getwidth";
            document.body.appendChild(span);
            span.style.visibility = "hidden";
            span.style.whiteSpace = "nowrap";
        }
        span.innerText = this;
        span.style.fontSize = fontSize + "px";
    
        return span.offsetWidth;
    }

    
    export class ICompanySelectorOption{
        noneSelectedText : string = '项目公司';
        selectedText: string = '# 个项目公司被选中';       
    }
    
    export class CompanySelector {

        private mOpt : ICompanySelectorOption = {
            noneSelectedText : '项目公司',
            selectedText: '# 个项目公司被选中'    
        };
        private mCtrlId: string;
        private mTopComps: string[][];
        private mSubComps: Array<string[][]>;
        private mSelectedTop: string;
        private mMulti: boolean;
        
        private updateTopSel(itemCount : number) {
            
            var topSel = $("#" + this.mCtrlId + "_top");
            topSel.css("width", topSel.children('option:selected').text().getWidth(13) + 25);
            topSel.multiselect({
                multiple: $("#" + this.mCtrlId + "_sub").length > 0 ? false : this.mMulti,
                header: false,
                minWidth: 80,
                height　: itemCount * 27 > 600 ? 600 : itemCount * 27 + 3,
                // noneSelectedText: "请选择月份",
                selectedList: 1
            });
        }
        
        private updateSubSel(itemCount : number) {
            var subSel = $("#" + this.mCtrlId + "_sub");
            var width = subSel.children('option:selected').text().getWidth(13) + 25;
            var minWidth = 80;
            if (this.mMulti) {
                
                subSel.multiselect({
                    multiple: this.mMulti,
                    header: true,
                    minWidth: 80,
                    noneSelectedText : this.mOpt.noneSelectedText,
                    selectedText: this.mOpt.selectedText,
                    height　: itemCount * 27 > 600 ? 600 : itemCount * 27 + 3,
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                });
                 var text : any = "n个 项目公司被选中";
                minWidth = text.getWidth(13) + 50;
                if (subSel.multiselect("getChecked").length > 1) {
                    width = text.getWidth(13) + 25;
                }
            }
            subSel.css("width", width);
            subSel.multiselect({
                multiple: this.mMulti,
                header: true == this.mMulti ? true : false,
                minWidth: minWidth,
                height　: itemCount * 27 > 600 ? 600 : itemCount * 27 + 3,
                // noneSelectedText: "请选择月份",
                selectedList: 1
            });

        }
        
        private updateSubComps(firstComp?: string) {
            if (!isExist(this.mSubComps)) {
                return;
            }

            var subSel = $("#" + this.mCtrlId + "_sub");
            if (subSel.length > 0) {
                subSel.empty();
            }
            else {
                $("#" + this.mCtrlId + " tr").append('<td>' +
                    '<select id="' + this.mCtrlId + '_sub" ' +
                    'style="width: 100px;font-size:13px"></select>' +
                    '</td><td><div style="width:5px;"></div></td>')
                subSel = $("#" + this.mCtrlId + "_sub");
            }
            
            var compId = $("#" + this.mCtrlId + "_top").children('option:selected').val();
            var index = -1;
            if (undefined == firstComp){
                firstComp = compId;    
            }
            for (var i = 0; i < this.mTopComps[0].length; ++i) {
                for (var j = 0; j < this.mSubComps[i][1].length; ++j) {
                    if (firstComp == this.mSubComps[i][1][j]) {
                        index = i;
                        break;
                    }
                }
                
                if (index >= 0){
                    break;    
                }
                if (firstComp == this.mTopComps[1][i]) {
                    index = i;
                    break;
                }
                
            }
            
            index = index < 0 ? 0: index;

            $("#" + this.mCtrlId + "_top option")[index].selected = "selected";
            this.mSelectedTop = this.mTopComps[1][index];
            this.updateTopSel(this.mTopComps[0].length);
            
            var found: boolean = false;
            for (var i = 0; i < this.mSubComps[index][1].length; ++i) {
                if (firstComp == this.mSubComps[index][1][i]) {
                    subSel.append('<option value="' + this.mSubComps[index][1][i] + '" selected="selected">' + this.mSubComps[index][0][i] + '</option>');
                    found = true;

                } else {
                    subSel.append('<option value="' + this.mSubComps[index][1][i] + '">' + this.mSubComps[index][0][i] + '</option>');
                }
            }

            if (!this.mMulti){
                subSel.append('<option value="' + this.mTopComps[1][index] + '">' + this.mTopComps[0][index] + "本部" + '</option>');
            }

            if (!found) {
                $("#" + this.mCtrlId + "_sub option")[0].selected = "selected";
            }


            this.updateSubSel(this.mSubComps[index][0].length);
            
            subSel.change(() => {
                this.updateSubSel(this.mSubComps[index][0].length);
            })

        }

        public constructor(
        multi : boolean, 
        divId: string, 
        topComps: string[][], 
        firstComp?: string, 
        subComps?: Array<string[][]>, 
        opt ? : ICompanySelectorOption) {
            this.mMulti = multi;
            this.mCtrlId = divId + "_table";
            $("#" + divId).append('<table id="' + this.mCtrlId + '" cellspacing="0" cellpadding="0"><tr></tr></table>');
            this.mSubComps = subComps;
            this.mTopComps = topComps;
            if (isExist(opt)){
                this.mOpt = opt;    
            }

            $("#" + this.mCtrlId + " tr").append('<td>' +
                '<select id="' + this.mCtrlId + '_top" ' +
                'style="width: 10px; font-size:13px"></select>' +
                '</td><td><div style="width:5px;"></div></td>');
            var topSel = $("#" + this.mCtrlId + "_top");
            var found: boolean = false;
            for (var i = 0; i < topComps[0].length; ++i) {
                if (firstComp == topComps[1][i]) {
                    topSel.append('<option value="' + topComps[1][i] + '" selected="selected">' + topComps[0][i] + '</option>');
                    found = true;
                    this.mSelectedTop = topComps[1][i];
                } else {
                    topSel.append('<option value="' + topComps[1][i] + '">' + topComps[0][i] + '</option>');
                }
            }
            
            if (!found) {
                $("#" + this.mCtrlId + "_top option")[0].selected = "selected";
                this.mSelectedTop = topComps[1][0]
            }
            
            this.updateTopSel(topComps[0].length);

            $("#" + this.mCtrlId + "_top").change(() => {
                var newTop = topSel.children('option:selected').val();
                if (newTop != this.mSelectedTop) {
                    this.mSelectedTop = newTop;
                    this.updateTopSel(topComps[0].length);
                    this.updateSubComps();
                }
            })

            this.updateSubComps(firstComp);
        }

        public getCompany(): Util.CompanyType {
            if (isExist(this.mSubComps)) {
                return $("#" + this.mCtrlId + "_sub").children('option:selected').val();
            } else {
                return $("#" + this.mCtrlId + "_top").children('option:selected').val();
            }
        }

    }
}