/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="sbdddcbjpcqkdef.ts" />
/// <reference path="../unitedSelector.ts"/>
///<reference path="../messageBox.ts"/>
///<reference path="../companySelector.ts"/>

module sbdddcbjpcqk {

    interface Option {
        dt:string;
        type:string;
        date: Util.Date;
    }

    interface PluginData extends Util.IData {
        plugin : PluginView;
    }

    export class View implements FrameView {
        protected mOpt:Option;
        protected mDtSec:Util.DateSelector;
        protected mItemSelector:Util.UnitedSelector;
        protected mNodes:Util.DataNode[] = [];
        protected mCurrentPlugin:PluginView;
        protected mCurrentDate:Util.Date;

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

            this.mItemSelector = new Util.UnitedSelector(this.mNodes, this.mOpt.type);
            if (this.mNodes.length == 1) {
                this.mItemSelector.hide();
            }
            this.mNodes = this.mItemSelector.getTopNodes();
            this.updateUI();
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
            for (var i = 0; i < this.mNodes.length; ++i) {
                if (node != this.mNodes[i]) {
                    this.plugin(this.mNodes[i]).hide();
                }
            }

            this.mCurrentDate = dt;
            this.mCurrentPlugin.show();
            $("#headertitle")[0].innerHTML = node.getData().value;
            this.plugin(node).update(dt);
        }
    }
}

var view:sbdddcbjpcqk.FrameView = new sbdddcbjpcqk.View();