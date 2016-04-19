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
        plugin : PluginView;
    }

    export class View implements FrameView {
        protected mOpt:Option;
        protected mDtSec:Util.DateSelector;
        protected mItemSelector:Util.UnitedSelector;
        protected mNodesAll:Util.DataNode[] = [];
        protected mCurrentPlugin:PluginView;
        protected mCurrentDate:Util.Date;
        protected mCompanySelector:Util.CompanySelector;
        protected mCurrentComp:Util.CompanyType;

        public register(name:string, plugin:PluginView):void {
            var data:PluginData = {id: this.mNodesAll.length, value: name, plugin: plugin};
            var node:Util.DataNode = new Util.DataNode(data);
            this.mNodesAll.push(node);
        }

        public unregister(name:string):PluginView {
            var nod:Util.DataNode;

            for (var i = 0; i < this.mNodesAll.length; ++i) {
                this.mNodesAll[i].accept({
                    visit: (node:Util.DataNode) => {
                        if (node.getData().value == name) {
                            nod = node;
                            return true;
                        }
                        return false;
                    }
                })
                if (nod != undefined) {
                    break;
                }
            }

            return this.plugin(nod);
        }

        //不可以起名叫做export 在IE中有冲突
        public exportExcel(elemId:string) {
            let url:string = this.mCurrentPlugin.getExportUrl(this.mCurrentDate);
            $("#" + elemId)[0].action = url;
            $("#" + elemId)[0].submit();
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


        private updateTypeSelector() {
            let type:Util.CompanyType = this.mCompanySelector.getCompany();
            let nodes = [];
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                if (this.plugin(this.mNodesAll[i]).isSupported(type)) {
                    nodes.push(this.mNodesAll[i]);
                }
            }

            let typeChange = false;
            let curNodes = [];
            if (this.mItemSelector != undefined){
                curNodes = this.mItemSelector.getTopNodes()
            }
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

            if (typeChange){
                this.mItemSelector = new Util.UnitedSelector(nodes, this.mOpt.type);
                if (nodes.length == 1) {
                    this.mItemSelector.hide();
                }
                $("#" + this.mOpt.type + " select")
                    .multiselect({
                        multiple: false,
                        header: false,
                        minWidth: 250,
                        height: '100%',
                        // noneSelectedText: "请选择月份",
                        selectedList: 1
                    })
                    .css("padding", "2px 0 2px 4px")
                    .css("text-align", "left")
                    .css("font-size", "12px");
            }
        }


        protected plugin(node:Util.DataNode):PluginView {
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
            $("#headertitle")[0].innerHTML = this.mCompanySelector.getCompanyName() + " " + node.getData().value;
            this.plugin(node).update(dt, this.mCurrentComp);
        }
    }
}

var view:wlyddqk.FrameView = new wlyddqk.View();