/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="yszkgbdef.ts" />
/// <reference path="../unitedSelector.ts"/>
///<reference path="../messageBox.ts"/>
///<reference path="../companySelector.ts"/>
///<reference path="yszkgb.ts"/>

module yszkgb {

    export class EntryView extends View {

        public register(name:string, plugin:EntryPluginView):void {
            super.register(name, plugin);
            plugin.setReadOnlyCallBack((isReadOnly:boolean)=>{
                if (isReadOnly){
                    $("#gbsv").hide();
                    $("#gbsm").hide();
                }else{
                    $("#gbsv").show();
                    $("#gbsm").show();
                }
            });
        }

        public submit(){
            let dt:Util.Date = this.mDtSec.getDate();
            dt.day = 1;
            let plugin : any = this.plugin(this.getActiveNode());
            <EntryPluginView>plugin.submit(dt, this.mCompanySelector.getCompany());
        }

        public save(){
            let dt:Util.Date = this.mDtSec.getDate();
            dt.day = 1;
            let plugin : any = this.plugin(this.getActiveNode());
            <EntryPluginView>plugin.save(dt, this.mCompanySelector.getCompany());
        }
    }
}

var entryView:yszkgb.EntryView = new yszkgb.EntryView();