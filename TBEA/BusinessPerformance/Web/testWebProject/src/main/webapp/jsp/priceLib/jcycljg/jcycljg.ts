/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="jcycljgdef.ts" />
///<reference path="../../unitedSelector.ts"/>

declare var echarts;


module jcycljg {

    interface Option {
        dt:string;
        type:string;
        date : Util.Date;
    }

    interface PluginData extends Util.IData {
        plugin : PluginView;
    }

    export class View implements FrameView{
        private mOpt:Option;
        private mDateSelector:Util.DateSelector;
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

            return (<PluginData>nod.getData()).plugin;
        }

        public init(opt:Option):void {
            this.mOpt = opt;
            this.mDateSelector = new Util.DateSelector({year: this.mOpt.date.year - 3, month: 1}, {
                year: this.mOpt.date.year,
                month: this.mOpt.date.month
            }, this.mOpt.dt);
            this.mDateSelector.select(this.mOpt.date);
            this.mUnitedSelector = new Util.UnitedSelector(this.mNodes, this.mOpt.type);
            this.mNodes = this.mUnitedSelector.getNodes();
            this.updateUI();
        }


        public updateUI() {
            var node:Util.DataNode = this.mUnitedSelector.getDataNode(this.mUnitedSelector.getPath());
            for (var i = 0; i < this.mNodes.length; ++i) {
                if (node != this.mNodes[i]){
                    (<PluginData>this.mNodes[i].getData()).plugin.hide();
                }
            }
            (<PluginData>node.getData()).plugin.show();
            var dts : Util.Date = this.mDateSelector.getDate();
            var dte : Util.Date = this.mDateSelector.getDate();
            dts.day = 1;
            dte.day = this.mDateSelector.monthDays();
            (<PluginData>node.getData()).plugin.update(dts, dte);
        }
    }
}

var view:jcycljg.FrameView = new jcycljg.View();