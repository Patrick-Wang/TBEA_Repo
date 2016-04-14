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
        }

        public submit(){
            (<EntryPluginView>this.plugin(this.getActiveNode())).submit();
        }

        public save(){
            (<EntryPluginView>this.plugin(this.getActiveNode())).save();
        }
    }
}

var entryView:yszkgb.EntryView = new yszkgb.EntryView();