/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="wlyddqkdef.ts" />
/// <reference path="../unitedSelector.ts"/>
///<reference path="../messageBox.ts"/>
///<reference path="../companySelector.ts"/>

module wlyddqk {

    interface Option {
        dt:string;
        type:string;
        comp:string;
        comps: Util.IDataNode[];
        date: Util.Date;
    }

    interface PluginData extends Util.IData {
        plugin : EntryPluginView;
    }

    export class EntryView implements EntryFrameView {

        protected mOpt:Option;
        protected mDtSec:Util.DateSelector;
        protected mItemSelector:Util.UnitedSelector;
        protected mNodesAll:Util.DataNode[] = [];
        protected mCurrentPlugin:EntryPluginView;
        protected mCurrentDate:Util.Date;
        protected mCompanySelector:Util.CompanySelector;
        protected mCurrentComp:Util.CompanyType;

        public register(name:string, plugin:EntryPluginView):void {
            var data:PluginData = {id: this.mNodesAll.length, value: name, plugin: plugin};
            var node:Util.DataNode = new Util.DataNode(data);
            this.mNodesAll.push(node);
            plugin.setOnReadOnlyChangeListener((isReadOnly:boolean)=> {
                if (isReadOnly) {
                    $("#gbsv").hide();
                    $("#gbsm").hide();
                } else {
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

            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }

            this.mCompanySelector.change((selector:any, depth:number) => {
                this.updateTypeSelector();
            });

            this.updateTypeSelector();
            this.updateUI();
        }

        protected updateTypeSelector(width : number = 325) {
            let type:Util.CompanyType = this.mCompanySelector.getCompany();
            let nodes = [];
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                if (this.plugin(this.mNodesAll[i]).isSupported(type)) {
                    nodes.push(this.mNodesAll[i]);
                }
            }

            let curNodes = [];
            if (this.mItemSelector != undefined) {
                curNodes = this.mItemSelector.getTopNodes()
            }
            let typeChange = false;
            if (nodes.length != curNodes.length) {
                typeChange = true;
            } else {
                for (let i = 0; i < nodes.length; ++i) {
                    if (this.plugin(nodes[i]) != this.plugin(curNodes[i])) {
                        typeChange = true;
                        break;
                    }
                }
            }

            if (typeChange) {
                this.mItemSelector = new Util.UnitedSelector(nodes, this.mOpt.type);
                if (nodes.length == 1) {
                    this.mItemSelector.hide();
                }
                $("#" + this.mOpt.type + " select")
                    .multiselect({
                        multiple: false,
                        header: false,
                        minWidth: width,
                        height: '100%',
                        // noneSelectedText: "请选择月份",
                        selectedList: 1
                    })
                    .css("padding", "2px 0 2px 4px")
                    .css("text-align", "left")
                    .css("font-size", "12px");

            }
        }

        protected plugin(node:Util.DataNode):EntryPluginView {
            return (<PluginData>node.getData()).plugin;
        }

        protected getActiveNode():Util.DataNode {
            return this.mItemSelector.getDataNode(this.mItemSelector.getPath());
        }

        public updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());

            let dt:Util.Date = this.mDtSec.getDate();
            dt.day = 1;

            this.mCurrentPlugin = this.plugin(node);
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                if (this.plugin(node) != this.plugin(this.mNodesAll[i])) {
                    this.plugin(this.mNodesAll[i]).hide();
                }
            }
            this.mCurrentComp = this.mCompanySelector.getCompany();
            this.mCurrentDate = dt;
            this.mCurrentPlugin.show();
            $("#headertitle")[0].innerHTML = node.getData().value;
            this.plugin(node).update(dt, this.mCurrentComp);
        }

        public submit() {
            this.plugin(this.getActiveNode()).submit(this.mCurrentDate, this.mCurrentComp);
        }

        public save() {
            this.plugin(this.getActiveNode()).save(this.mCurrentDate, this.mCurrentComp);
        }
    }
}

var entryView:wlyddqk.EntryView = new wlyddqk.EntryView();