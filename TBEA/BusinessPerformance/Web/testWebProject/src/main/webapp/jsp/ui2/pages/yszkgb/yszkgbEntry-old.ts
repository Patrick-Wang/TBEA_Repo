/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="yszkgbdef.ts" />
/// <reference path="../unitedSelector.ts"/>
///<reference path="../messageBox.ts"/>
///<reference path="../companySelector.ts"/>

module yszkgb {

    interface Option {
        dt:string;
        comp:string;
        comps: Util.IDataNode[];
        type:string;
        date: Util.Date;
    }

    interface PluginData extends Util.IData {
        plugin : EntryPluginView;
    }

    export class EntryView implements EntryFrameView {

        protected mOpt:Option;
        protected mDtSec:Util.DateSelector;
        protected mItemSelector:Util.UnitedSelector;
        protected mCompanySelector: Util.CompanySelector;
        protected mNodes:Util.DataNode[] = [];
        protected mCurrentPlugin: EntryPluginView;
        protected mCurrentDate:Util.Date;
        protected mCurrentComp:Util.CompanyType;
        public register(name:string, plugin:EntryPluginView):void {
            var data:PluginData = {id: this.mNodes.length, value: name, plugin: plugin};
            var node:Util.DataNode = new Util.DataNode(data);
            this.mNodes.push(node);
            plugin.setOnReadOnlyChangeListener((isReadOnly:boolean)=>{
                if (isReadOnly){
                    $("#gbsv").hide();
                    $("#gbsm").hide();
                }else{
                    $("#gbsv").show();
                    $("#gbsm").show();
                }
            });
        }

        unregister(name:string):EntryPluginView {
            return undefined;
        }

        public init(opt:Option):void {
            this.mOpt = opt;
            this.mDtSec = new Util.DateSelector({year: this.mOpt.date.year - 3, month: 1}, {
                year: this.mOpt.date.year,
                month: this.mOpt.date.month
            }, this.mOpt.dt);

            this.mCompanySelector = new Util.CompanySelector(false,  this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.mItemSelector = new Util.UnitedSelector(this.mNodes, this.mOpt.type);
            if (this.mNodes.length == 1) {
                this.mItemSelector.hide();
            }
            this.mNodes = this.mItemSelector.getTopNodes();
            this.updateUI();
        }

        protected plugin(node:Util.DataNode):EntryPluginView{
            return  (<PluginData>node.getData()).plugin;
        }

        protected getActiveNode():Util.DataNode{
            return this.mItemSelector.getDataNode(this.mItemSelector.getPath());
        }

        public updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());

            let dt:Util.Date = this.mDtSec.getDate();
            dt.day = 1;

            this.mCurrentPlugin = this.plugin(node);
            for (var i = 0; i < this.mNodes.length; ++i) {
                if (node != this.mNodes[i]) {
                    this.plugin(this.mNodes[i]).hide();
                }
            }
            this.mCurrentComp = this.mCompanySelector.getCompany();
            this.mCurrentDate = dt;
            this.mCurrentPlugin.show();
            $("#headertitle")[0].innerHTML = this.mCompanySelector.getCompanyName() + " " + node.getData().value;
            this.plugin(node).update(dt,  this.mCurrentComp);
        }

        public submit(){
            this.plugin(this.getActiveNode()).submit(this.mCurrentDate, this.mCurrentComp);
        }

        public save(){
            this.plugin(this.getActiveNode()).save(this.mCurrentDate, this.mCurrentComp);
        }
    }
}

var entryView:yszkgb.EntryView = new yszkgb.EntryView();