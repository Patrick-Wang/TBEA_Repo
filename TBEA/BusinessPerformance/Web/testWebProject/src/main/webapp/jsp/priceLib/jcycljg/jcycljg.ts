/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="jcycljgdef.ts" />
/// <reference path="../../unitedSelector.ts"/>

module jcycljg {

    import DataNode = Util.DataNode;
    interface Option {
        dts:string;
        dte:string;
        type:string;
        date : Util.Date;
    }

    interface PluginData extends Util.IData {
        plugin : PluginView;
    }

    export class View implements FrameView {
        private mOpt:Option;
        private mDSStart:Util.DateSelector;
        private mDSEnd:Util.DateSelector;
        private mUnitedSelector:Util.UnitedSelector;
        private mNodes:Util.DataNode[] = [];

        public register(name:string, plugin:PluginView):void {
            var data:PluginData = {id: this.mNodes.length, value: name, plugin: plugin};
            var node:Util.DataNode = new Util.DataNode(data);
            this.mNodes.push(node);
        }

        public unregister(name:string):PluginView {
            var nod:Util.DataNode;

            for (var i = 0; i < this.mNodes.length; ++i) {
                this.mNodes[i].accept({
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

        public init(opt:Option):void {
            this.mOpt = opt;
            this.mDSStart = new Util.DateSelector({year: this.mOpt.date.year - 3, month: 1}, {
                year: this.mOpt.date.year,
                month: this.mOpt.date.month
            }, this.mOpt.dts);
            this.mDSEnd = new Util.DateSelector({year: this.mOpt.date.year - 3, month: 1}, {
                year: this.mOpt.date.year,
                month: this.mOpt.date.month
            }, this.mOpt.dte);
            this.mDSStart.select(this.mOpt.date);
            this.mDSEnd.select(this.mOpt.date);
            this.mUnitedSelector = new Util.UnitedSelector(this.mNodes, this.mOpt.type);
            this.mNodes = this.mUnitedSelector.getNodes();
            if (this.plugin(this.getActiveNode()).getDateType() == DateType.DAY){
                $("#" + this.mOpt.dte).hide();
            }
            this.mUnitedSelector.change((sel:any, depth:number)=>{
                if (this.plugin(this.getActiveNode()).getDateType() == DateType.MONTH){
                    $("#" + this.mOpt.dte).show();
                }else {
                    $("#" + this.mOpt.dte).hide();
                }
            });
            this.updateUI();
        }

        private plugin(node:DataNode):PluginView{
            return  (<PluginData>node.getData()).plugin;
        }

        private getActiveNode():DataNode{
            return this.mUnitedSelector.getDataNode(this.mUnitedSelector.getPath());
        }

        public updateUI() {
            let node:Util.DataNode = this.mUnitedSelector.getDataNode(this.mUnitedSelector.getPath());
            for (var i = 0; i < this.mNodes.length; ++i) {
                if (node != this.mNodes[i]) {
                    this.plugin(this.mNodes[i]).hide();
                }
            }
            this.plugin(node).show();
            let dts:Util.Date = this.mDSStart.getDate();
            dts.day = 1;
            let dte:Util.Date = this.mDSStart.getDate();
            if (this.plugin(node).getDateType() == DateType.MONTH){
                dte = this.mDSEnd.getDate();
                dte.day = this.mDSEnd.monthDays();
            }else {
                dte.day = this.mDSStart.monthDays();
            }

            this.plugin(node).update(dts, dte);
        }
    }
}

var view:jcycljg.FrameView = new jcycljg.View();