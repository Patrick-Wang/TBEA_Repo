/// <reference path="../jqgrid/jqassist.ts" />
// / <reference path="../util.ts" />
///<reference path="../unitedSelector.ts"/>
///<reference path="../dateSelector.ts"/>
///<reference path="futuresdef.ts"/>
///<reference path="../messageBox.ts"/>

module futures {

    import DataNode = Util.DataNode;
    interface Option {
        type:string;
        contentType:string;
    }

    interface PluginData extends Util.IData {
        plugin : PluginView;
    }

    export class View implements FrameView {
        private mOpt:Option;
        private mItemSelector:Util.UnitedSelector;
        private mNodes:Util.DataNode[] = [];
        private mCurrentPlugin: PluginView;
        private mDisplayType:DisplayType;
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
            this.mItemSelector = new Util.UnitedSelector(this.mNodes, this.mOpt.type);
            this.mNodes = this.mItemSelector.getTopNodes();

            let inputs = $("#" + this.mOpt.contentType + " input");
            inputs.click((e)=>{
                for(let i=0;i<inputs.length;i++){
                    if(true == inputs[i].checked){
                        if(inputs[i].id=='rdct'){
                            this.mDisplayType = DisplayType.CHART;
                            this.showPluginChart();
                        }else{
                            this.mDisplayType = DisplayType.TABLE;
                            this.showPluginTable();
                        }
                    }
                }
            });
            this.mDisplayType = this.getDisplayType();
            this.updateUI();
        }

        private getDisplayType() : DisplayType{
            let inputs = $("#" + this.mOpt.contentType + " input");
            for(let i=0;i<inputs.length;i++){
                if(true == inputs[i].checked){
                    if(inputs[i].id=='rdct'){
                        return DisplayType.CHART;
                    }else{
                        return DisplayType.TABLE;
                    }
                }
            }
            return DisplayType.CHART;
        }

        private showPluginChart():void{
            if (this.mCurrentPlugin.getContentType() == ContentType.TABLE_CHART){
                this.mCurrentPlugin.switchDisplayType(DisplayType.CHART);
            }
        }

        private showPluginTable():void{
            if (this.mCurrentPlugin.getContentType() == ContentType.TABLE_CHART){
                this.mCurrentPlugin.switchDisplayType(DisplayType.TABLE);
            }
        }

        private plugin(node:DataNode):PluginView{
            return  (<PluginData>node.getData()).plugin;
        }

        private getActiveNode():DataNode{
            return this.mItemSelector.getDataNode(this.mItemSelector.getPath());
        }

        public updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());

            this.mCurrentPlugin = this.plugin(node);
            for (var i = 0; i < this.mNodes.length; ++i) {
                if (node != this.mNodes[i]) {
                    this.plugin(this.mNodes[i]).hide();
                }
            }
            this.mCurrentPlugin.show();
            //$("#headertitle")[0].innerHTML = node.getData().value;
            if ( this.mDisplayType == DisplayType.CHART){
                this.showPluginChart();
            }else {
                this.showPluginTable();
            }

            if (this.mCurrentPlugin.getContentType() == ContentType.TABLE_CHART){
                $("#" + this.mOpt.contentType).show();
            }else{
                $("#" + this.mOpt.contentType).hide();
            }
            this.plugin(node).update();
        }
    }
}

var view:futures.FrameView = new futures.View();