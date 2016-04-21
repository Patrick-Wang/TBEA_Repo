/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../unitedSelector.ts"/>
///<reference path="../../messageBox.ts"/>
///<reference path="../../companySelector.ts"/>
///<reference path="basicdef.ts"/>
///<reference path="../route/route.ts"/>
module framework.basic {

    import router = framework.router;

    interface Option {
        dt:string;
        type:string;
        comp:string;
        comps: Util.IDataNode[];
        date: Util.Date;
    }

    interface PluginData extends Util.IData {
        plugin : number;
    }

    export class BasicFrameView extends FrameView {
        protected mOpt:Option;
        protected mDtSec:Util.DateSelector;
        protected mItemSelector:Util.UnitedSelector;
        protected mNodesAll:Util.DataNode[] = [];
        protected mCurrentPlugin:number;
        protected mCurrentDate:Util.Date;
        protected mCompanySelector:Util.CompanySelector;
        protected mCurrentComp:Util.CompanyType;

        protected register(name:string, plugin:number):void {
            var data:PluginData = {id: this.mNodesAll.length, value: name, plugin: plugin};
            var node:Util.DataNode = new Util.DataNode(data);
            this.mNodesAll.push(node);
        }

        protected unregister(name:string):number {
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

        protected init(opt:Option):void {
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

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case FrameEvent.FE_UPDATE:
                    this.updateUI();
                    break;
            }
            return super.onEvent(e);
        }

        protected updateTypeSelector(width:number = 285) {
            let type:Util.CompanyType = this.mCompanySelector.getCompany();
            let nodes = [];
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                if (router.to(this.plugin(this.mNodesAll[i])).send(FrameEvent.FE_IS_COMPANY_SUPPORTED, {type:type})) {
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


        protected plugin(node:Util.DataNode):number {
            return (<PluginData>node.getData()).plugin;
        }

        protected updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());

            let dt:Util.Date = this.mDtSec.getDate();
            if (dt.month == undefined){
                dt.month = 1;
            }

            if (dt.day == undefined) {
                dt.day = 1;
            }

            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);

            this.mCurrentComp = this.mCompanySelector.getCompany();
            this.mCurrentDate = dt;
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);
            $("#headertitle")[0].innerHTML = this.mCompanySelector.getCompanyName() + " " + node.getData().value;
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                date:dt,
                compType:this.mCurrentComp
            });
        }
    }
}

